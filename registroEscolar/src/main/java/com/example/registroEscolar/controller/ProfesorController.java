package com.example.registroEscolar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.registroEscolar.dto.ProfesorDTO;
import com.example.registroEscolar.service.ProfesorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }


    //Ruta para listar todos los profesores
    @GetMapping
    public List<ProfesorDTO> listarProfesores() {
        return profesorService.listarProfesores();
    }


    // Ruta para registrar un nuevo profesor
    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarProfesor(@Valid @RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO creado = profesorService.registrarProfesor(profesorDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Profesor registrado con éxito");
        respuesta.put("profesor", creado);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    // Ruta para obtener un profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerProfesorPorId(@PathVariable Integer id) {
        ProfesorDTO dto = profesorService.obtenerProfesorPorId(id);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Profesor encontrado");
        respuesta.put("profesor", dto);

        return ResponseEntity.ok(respuesta);
    }

    // Ruta para eliminar un profesor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarProfesor(@PathVariable Integer id) {
        profesorService.eliminarProfesor(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Profesor eliminado con éxito");

        return ResponseEntity.ok(respuesta);
    }

    // Ruta para actualizar la especialidad de un profesor
    @PutMapping("/{id}/especialidad")
    public ResponseEntity<Map<String, Object>> actualizarEspecialidadProfesor(@PathVariable Integer id,
            @RequestBody String nuevaEspecialidad) {
        ProfesorDTO actualizado = profesorService.actualizarEspecialidadProfesor(id, nuevaEspecialidad);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Especialidad del profesor actualizada con éxito");
        respuesta.put("profesor", actualizado);

        return ResponseEntity.ok(respuesta);
    }

    // Filtros
    @GetMapping("/filtro")
    public ResponseEntity<Map<String, Object>> filtrarProfesores(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String apellido,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String especialidad,
        @RequestParam(required = false) Integer anoContratacion
        ){
        
            List<ProfesorDTO> profesoresFiltrados = profesorService.filtrar(especialidad, nombre, apellido, email, anoContratacion);
            Map<String, Object> respuesta = new HashMap<>();
            if (profesoresFiltrados.isEmpty()){
                respuesta.put("mensaje", "No se encontraron profesores con los criterios proporcionados");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);   
            }

            respuesta.put("mensaje", "El filtro se aplicó correctamente");
            respuesta.put("resultado", profesoresFiltrados);
            return ResponseEntity.ok(respuesta);
        }


    
    
}
