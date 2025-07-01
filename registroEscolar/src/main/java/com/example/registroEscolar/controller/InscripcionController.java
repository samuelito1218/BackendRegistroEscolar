package com.example.registroEscolar.controller;

import com.example.registroEscolar.dto.InscripcionDTO;
import com.example.registroEscolar.service.InscripcionService;

import jakarta.validation.Valid;

import java.time.LocalDate;
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


@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }


    //Ruta para registrar una inscripción
    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@Valid @RequestBody InscripcionDTO dto) {
        InscripcionDTO creada = inscripcionService.registrar(dto);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Inscripción registrada con éxito");
        respuesta.put("inscripcion", creada);
        return ResponseEntity.ok(respuesta);
    }

    //Ruta para listar todas las inscripciones
    @GetMapping
    public List<InscripcionDTO> listar() {
        return inscripcionService.listar();
    }

    //Ruta para obtener una inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(inscripcionService.obtenerPorId(id));
    }

    //Ruta para eliminar una inscripción por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        inscripcionService.eliminar(id);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Inscripción eliminada exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    //Ruta para actualizar la fecha de inscripción
    @PutMapping("/{id}/fecha")
    public ResponseEntity<Map<String, Object>> actualizarFechaInscripcion(
        @PathVariable Integer id,
        @RequestBody Map<String, String> body) {
            LocalDate nuevaFecha = LocalDate.parse(body.get("fechaInscripcion"));
            InscripcionDTO actualizada = inscripcionService.actualizarFechaInscripcion(id, nuevaFecha);
            
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Fecha de inscripción actualizada correctamente");
        respuesta.put("inscripcion", actualizada);
            
        return ResponseEntity.ok(respuesta);
    }

    //Ruta para filtros
    @GetMapping("/filtrar")
    public ResponseEntity<Map<String, Object>> filtrarInscripciones(
        @RequestParam(required = false) String nombreEstudiante,
        @RequestParam(required = false) String apellidoEstudiante,
        @RequestParam(required = false) String nombreCurso,
        @RequestParam(required = false) Integer anoInscripcion) {
            
            List<InscripcionDTO> resultado = inscripcionService.filtrarInscripciones(nombreEstudiante, apellidoEstudiante, nombreCurso, anoInscripcion);
            Map<String, Object> respuesta = new HashMap<>();
            if (resultado.isEmpty()) {
                respuesta.put("mensaje", "No se encontraron inscripciones con los criterios especificados");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            respuesta.put("mensaje", "Filtro aplicado correctamente");
            respuesta.put("inscripciones", resultado);
            
            return ResponseEntity.ok(respuesta);
        
        }



}
