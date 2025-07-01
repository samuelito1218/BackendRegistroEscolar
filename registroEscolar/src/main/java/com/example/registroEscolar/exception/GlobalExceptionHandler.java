package com.example.registroEscolar.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolationException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> manejarNoEncontrado(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresDeValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarErroresGenerales(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error interno del servidor: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> manejarViolaciones(ConstraintViolationException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String campo = violation.getPropertyPath().toString();
            String mensaje = violation.getMessage();
            errores.put(campo, mensaje);
        });
        
        return ResponseEntity.badRequest().body(errores);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    
    public ResponseEntity<Map<String, String>> manejarViolacionDeUnicidad(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();

        String mensaje = ex.getMostSpecificCause().getMessage().toLowerCase();
        
        if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("email")) {
            error.put("email", "Ya existe una persona registrada con ese correo electrónico.");
        } else if (mensaje.contains("estudiante.primary")) {
            error.put("error", "Esta persona ya está registrada como estudiante. Si desea cambiar el grado, actualice el estudiante existente.");

        } else if (mensaje.contains ("numero_matricula")){
            error.put("eror", "El número de matrícula ya está asignado a otro estudiante.");
        }
        
        else {
            error.put("error", "Error de integridad de datos: " + ex.getMostSpecificCause().getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PersonaConRolExistenteException.class)

    public ResponseEntity<Map<String, String>> manejarRolExistente(PersonaConRolExistenteException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    
    }
    @ExceptionHandler(CursoConProfesorAsignadoException.class)
    
    public ResponseEntity<Map<String, String>> manejarCursoConProfesorAsignado(CursoConProfesorAsignadoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarArgumentoIlegal(IllegalArgumentException ex) {
        
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



    




}
