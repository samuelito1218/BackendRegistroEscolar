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

import com.example.registroEscolar.dto.AdministrativoDTO;
import com.example.registroEscolar.service.AdministrativoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/administrativos")
public class AdministrativoController {

    private final AdministrativoService administrativoService;

    public AdministrativoController(AdministrativoService administrativoService) {
        this.administrativoService = administrativoService;
    }

    // Ruta para registrar un administrativo
    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@Valid @RequestBody AdministrativoDTO dto) {
        AdministrativoDTO creado = administrativoService.registrar(dto);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Administrativo registrado con éxito");
        respuesta.put("administrativo", creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // Ruta para listar todos los administrativos
    @GetMapping
    public List<AdministrativoDTO> listar() {
        return administrativoService.listar();
    }

    // Rutas para buscar administrativo por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable Integer id) {
        AdministrativoDTO dto = administrativoService.obtenerPorId(id);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Administrativo encontrado");
        respuesta.put("administrativo", dto);
        return ResponseEntity.ok(respuesta);
    }

    // Rutas para eliminar administrativo por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        administrativoService.eliminar(id);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Administrativo eliminado con éxito");
        return ResponseEntity.ok(respuesta);
    }

    // Ruta para actualizar el departamento de un administrativo    
    @PutMapping("/{id}/actualizar")
    public ResponseEntity<Map<String, Object>> actualizarinfo(
        @PathVariable Integer id, 
        @Valid @RequestBody AdministrativoDTO administrativoDTO) {

        AdministrativoDTO actualizado = administrativoService.actualizarInformacionAdministrativo(id, administrativoDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Información del administrativo actualizada con éxito");
        respuesta.put("administrativo", actualizado);
        
        return ResponseEntity.ok(respuesta);
    
    }
    // Filtros
    @GetMapping("/filtrar")
    public ResponseEntity<Map<String, Object>> filtrar(
        @RequestParam(required = false)String cargo, 
        @RequestParam(required = false)String departamento, 
        @RequestParam(required = false)String nombre, 
        @RequestParam(required = false)String apellido){
            
            List<AdministrativoDTO> administrativosFiltrados = administrativoService.filtrar(cargo, departamento, nombre, apellido);
            Map<String, Object> respuesta = new HashMap<>();
            if (administrativosFiltrados.isEmpty()) {
                respuesta.put("mensaje", "No se encontraron administrativos con los criterios especificados");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            }
            respuesta.put("mensaje", "El filtro se aplicó correctamente");
            respuesta.put("administrativos", administrativosFiltrados);
            return ResponseEntity.ok(respuesta);
    }

}
