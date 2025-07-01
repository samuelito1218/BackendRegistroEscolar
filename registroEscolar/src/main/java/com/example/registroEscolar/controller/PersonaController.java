package com.example.registroEscolar.controller;

import com.example.registroEscolar.dto.PersonaDTO;
import com.example.registroEscolar.service.PersonaService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    //Ruta para listar todas las personas
    @GetMapping
    public List<PersonaDTO> listar() {
        return personaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    //Ruta para crear una nueva persona
    @PostMapping
    public ResponseEntity<Map<String, Object>> guardar(@Valid @RequestBody PersonaDTO personaDTO) {
        PersonaDTO creada = personaService.guardar(personaDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Persona creada con éxito");
        respuesta.put("persona", creada);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //Ruta para actualizar una persona existente
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarPersona(
            @PathVariable Integer id,
            @Valid @RequestBody PersonaDTO personaDTO) {

        PersonaDTO actualizada = personaService.actualizar(id, personaDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Persona actualizada con éxito");
        respuesta.put("persona", actualizada);

        return ResponseEntity.ok(respuesta);
    }

    //Ruta para eliminar una persona por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        personaService.eliminar(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Persona eliminada con éxito");

        return ResponseEntity.ok(respuesta);
    }

    //Ruta para los filtros de búsqueda
    @GetMapping("/filtro")
public ResponseEntity<Map<String, Object>> filtrar(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String apellido,
        @RequestParam(required = false) String email) {

    List<PersonaDTO> resultado = personaService.filtrar(nombre, apellido, email);

    

    Map<String, Object> respuesta = new HashMap<>();

    if (resultado.isEmpty()) {
        respuesta.put("mensaje", "No se encontraron personas con los criterios proporcionados");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    respuesta.put("mensaje", "El filtro se aplicó correctamente");
    respuesta.put("resultado", resultado);

    return ResponseEntity.ok(respuesta);
}

}
