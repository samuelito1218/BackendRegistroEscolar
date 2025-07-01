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

import com.example.registroEscolar.dto.EstudianteDTO;
import com.example.registroEscolar.service.EstudianteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estudiantes")

public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService){
        this.estudianteService = estudianteService;
    }

    //Ruta para listar todos los estudiantes

    @GetMapping
    public List<EstudianteDTO> listarEstudiantes() {
        return estudianteService.listarEstudiantes();
    }

    //Ruta para crear un estudiante

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO creado = estudianteService.registrarEstudiante(estudianteDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Estudiante registrado con éxito");
        respuesta.put("estudiante", creado);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //Obtener estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerEstudiantePorId(@PathVariable Integer id){
        EstudianteDTO dto = estudianteService.obtenerEstudiantePorId(id);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Estudiante encontrado");
        respuesta.put("estudiante", dto);

        return ResponseEntity.ok(respuesta);
    }

    //Eliminar estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarEstudiante(@PathVariable Integer id) {
        estudianteService.eliminarEstudiante(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Estudiante eliminado con éxito");

        return ResponseEntity.ok(respuesta);
    }

    //Actializar estudiante
    @PutMapping("/{id}/grado")
    public ResponseEntity<Map<String, Object>> actualizarGradoEstudiante(@PathVariable Integer id, @RequestBody Integer nuevoGrado) {
        EstudianteDTO actualizado = estudianteService.actualizarGradoEstudiante(id, nuevoGrado);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Grado del estudiante actualizado con éxito");
        respuesta.put("estudiante", actualizado);

        return ResponseEntity.ok(respuesta);
    }

    //Filtros

    @GetMapping("/filtro")
    public ResponseEntity<Map<String, Object>> filtrar (
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String apellido,
        @RequestParam(required = false) Integer grado,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) Integer numeroMatricula){
        List<EstudianteDTO> resultado = estudianteService.filtrar(grado, nombre, apellido, email, numeroMatricula);
        Map<String, Object> respuesta = new HashMap<>();
        if (resultado.isEmpty()){
            respuesta.put("mensaje", "No se encontraron estudiantes con los criterios proporcionados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        respuesta.put("mensaje", "El filtro se aplicó correctamente");
        respuesta.put("resultado", resultado);
        return ResponseEntity.ok(respuesta);
        }
    



    
}
