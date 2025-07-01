package com.example.registroEscolar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.registroEscolar.dto.ProfesorDTO;
import com.example.registroEscolar.mapper.ProfesorMapper;
import com.example.registroEscolar.model.Persona;
import com.example.registroEscolar.model.Profesor;
import com.example.registroEscolar.repository.AdministrativoRepository;
import com.example.registroEscolar.repository.EstudianteRepository;
import com.example.registroEscolar.repository.PersonaRepository;
import com.example.registroEscolar.repository.ProfesorRepository;
import com.example.registroEscolar.service.ProfesorService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;
    private final PersonaRepository personaRepository;
    private final EstudianteRepository estudianteRepository; 
    private final AdministrativoRepository administrativoRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper, PersonaRepository personaRepository, EstudianteRepository estudianteRepository, AdministrativoRepository administrativoRepository) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
        this.personaRepository = personaRepository;
        this.estudianteRepository = estudianteRepository;
        this.administrativoRepository = administrativoRepository;
    }


    @Override //Lógica del método para registrar a un profesor
    public ProfesorDTO registrarProfesor(ProfesorDTO profesorDTO) {

        Integer id = profesorDTO.getIdPersona();
        if (estudianteRepository.existsById(id) || administrativoRepository.existsById(id)) {
            throw new RuntimeException("La persona con ID " + id + " ya está registrada como estudiante o administrativo.");
        }

        if(profesorRepository.existsById(id)) {
            throw new RuntimeException("Profesor ya registrado con ID: " + id);
        }
        
        Persona persona = personaRepository.findById(profesorDTO.getIdPersona())                
                .orElseThrow(() -> new RuntimeException ("Persona no encontrada con ID: " + profesorDTO.getIdPersona()));

            Profesor profesor = profesorMapper.toEntity(profesorDTO, persona);
            Profesor guardado = profesorRepository.save(profesor);

        return profesorMapper.toDTO(guardado);
    }

    @Override //Lógica para obtener a un profesor mediante ID
    public ProfesorDTO obtenerProfesorPorId(Integer id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
        return profesorMapper.toDTO(profesor);
    }

    @Override //Lógica del método para eliminar un profesor mediante ID
    public void eliminarProfesor(Integer id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
        profesorRepository.delete(profesor);
    }
    
    @Override //Lógica del método para listar a todos los profesores
    public List<ProfesorDTO> listarProfesores() {
        return profesorRepository.findAll()
                .stream()
                .map(profesorMapper::toDTO)
                .toList();
    }

    @Override //Lógica del método para actualizar únicamente la especialidad del profesor
    public ProfesorDTO actualizarEspecialidadProfesor(Integer idPersona, String nuevaEspecialidad) {
        Profesor profesor = profesorRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + idPersona));

        profesor.setEspecialidad(nuevaEspecialidad.replace("\"", "").trim());

        Profesor actualizado = profesorRepository.save(profesor);

        return profesorMapper.toDTO(actualizado);
    }

    //Lógica de filtros

    @Override
    public List<ProfesorDTO> filtrar(String especialidad, String nombre, String apellido, String email, Integer anoContratacion) {
        return profesorRepository.filtrarProfesores(especialidad, nombre, apellido, email, anoContratacion)
                .stream()
                .map(profesorMapper::toDTO)
                .toList();        
    }
    
}
