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

import com.example.registroEscolar.dto.CursoDTO;
import com.example.registroEscolar.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    
    //Ruta para registrar un curso
    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarCurso(@Valid @RequestBody CursoDTO cursoDTO){
        CursoDTO cursoRegistrado = cursoService.registrarCurso(cursoDTO);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Curso registrado exitosamente");
        respuesta.put("curso", cursoRegistrado);
        return ResponseEntity.ok(respuesta);
    }

    //Ruta para listar todos los cursos
    @GetMapping
    public List<CursoDTO> listarCursos() {
        return cursoService.listarCursos();
    }

    //Ruta para buscar un curso por ID
    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> buscarCursoPorId(@PathVariable Integer idCurso) {
        CursoDTO curso = cursoService.buscarCursoPorId(idCurso);
        return ResponseEntity.ok(curso);
    }


    //Ruta para actualizar un curso
    @PutMapping("/{idCurso}")
    public ResponseEntity<Map<String, Object>> actualizarCurso(@PathVariable Integer idCurso, @Valid @RequestBody CursoDTO cursoDTO) {
        CursoDTO cursoActualizado = cursoService.actualizarCurso(cursoDTO, idCurso);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Curso actualizado exitosamente");
        respuesta.put("curso", cursoActualizado);
        return ResponseEntity.ok(respuesta);
    }

    //Ruta para eliminar un curso
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Map<String, String>> eliminarCurso(@PathVariable Integer idCurso) {
        cursoService.eliminarCurso(idCurso);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Curso eliminado exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    //Ruta para filtros
    @GetMapping("/filtrar")
    public ResponseEntity<Map<String, Object>> filtrarCursos(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) Integer creditos,
        @RequestParam(required = false) Integer idProfesor) {

        List<CursoDTO> cursos = cursoService.filtrarCursos(nombre, creditos, idProfesor);
    
        Map<String, Object> respuesta = new HashMap<>();
    
            if (cursos.isEmpty()) {
                respuesta.put("mensaje", "No se encontraron cursos con los filtros especificados.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
         respuesta.put("mensaje", "Filtro aplicado con éxito");
         respuesta.put("cursos", cursos);
         return ResponseEntity.ok(respuesta);
        
    }

    
    
}
