package com.example.registroEscolar.service.impl;

import com.example.registroEscolar.dto.PersonaDTO;
import com.example.registroEscolar.exception.ResourceNotFoundException;
import com.example.registroEscolar.mapper.PersonaMapper;
import com.example.registroEscolar.model.Persona;
import com.example.registroEscolar.repository.PersonaRepository;
import com.example.registroEscolar.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    public PersonaServiceImpl(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    @Override //Lógica del método para listar a todas las personas
    public List<PersonaDTO> listarTodos() {
        return personaRepository.findAll()
                .stream()
                .map(personaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override //Lógica del método para crear Persona
    public PersonaDTO guardar(PersonaDTO personaDTO) {
        Persona persona = personaMapper.toEntity(personaDTO);
        return personaMapper.toDTO(personaRepository.save(persona));
    }

    @Override //Lógica del método para obtener a una persona mediante su ID
    public PersonaDTO obtenerPorId(Integer id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + id));
        return personaMapper.toDTO(persona);
    }

    @Override //Lógica del método para actualizar la información de la persona mediante ID
    public PersonaDTO actualizar(Integer id, PersonaDTO personaDTO) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con ID: " + id));

        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.setEmail(personaDTO.getEmail());
        persona.setTelefono(personaDTO.getTelefono());

        return personaMapper.toDTO(personaRepository.save(persona));
    }

    @Override //Lógica del método para eliminar una persona por ID
    public void eliminar(Integer id) {
        if (!personaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deleteById(id);
    }

    //Lógica para los filtros
    @Override
    public List<PersonaDTO> filtrar (String nombre, String apellido, String email){
        List<Persona> personas;
        
        if (nombre != null && apellido != null) {
            personas = personaRepository.findByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCase(nombre, apellido);
        } else if (nombre != null) {
            personas = personaRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (apellido != null) {
            personas = personaRepository.findByApellidoContainingIgnoreCase(apellido);
        } else if (email != null) {
            personas = personaRepository.findByEmailContainingIgnoreCase(email);
        } else {
            personas = personaRepository.findAll();
        }
        
        return personas.stream().map(personaMapper::toDTO).toList();
    
    }
}
