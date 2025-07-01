package com.example.registroEscolar.service.impl;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.registroEscolar.dto.EstudianteDTO;
import com.example.registroEscolar.mapper.EstudianteMapper;
import com.example.registroEscolar.model.Estudiante;
import com.example.registroEscolar.model.Persona;
import com.example.registroEscolar.repository.AdministrativoRepository;
import com.example.registroEscolar.repository.EstudianteRepository;
import com.example.registroEscolar.repository.PersonaRepository;
import com.example.registroEscolar.repository.ProfesorRepository;
import com.example.registroEscolar.service.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final PersonaRepository personaRepository;
    private final ProfesorRepository profesorRepository; 
    private final AdministrativoRepository administrativoRepository; 

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, EstudianteMapper estudianteMapper, PersonaRepository personaRepository, ProfesorRepository profesorRepository, AdministrativoRepository administrativoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
        this.personaRepository = personaRepository;
        this.profesorRepository = profesorRepository; 
        this.administrativoRepository = administrativoRepository;
    }

    private String generarNumeroMatricula(){
        int año = Year.now().getValue();
        long conteo = estudianteRepository.count() + 1;
        return año + String.format("%04d", conteo);
    }

    @Override //Lógica del método para registrar un estudiante

    public EstudianteDTO registrarEstudiante( EstudianteDTO estudianteDTO){
        Integer id = estudianteDTO.getIdPersona();
        if (profesorRepository.existsById(id) || administrativoRepository.existsById(id)) {
            throw new RuntimeException("La persona con ID " + id + " ya está registrada como profesor o administrativo.");
        }

        if(estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante ya registrado con ID: " + id);
        } 

        Persona persona = personaRepository.findById(estudianteDTO.getIdPersona())
            .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " +estudianteDTO.getIdPersona()));

            int matriculaGenerada = Integer.parseInt(generarNumeroMatricula());
            Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO, persona, matriculaGenerada);
            Estudiante guardado = estudianteRepository.save(estudiante);

            return estudianteMapper.toDTO(guardado);
        }

    @Override //Lógica del método para obtener un estudiante por ID
    public EstudianteDTO obtenerEstudiantePorId(Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        return estudianteMapper.toDTO(estudiante);
    }

    @Override //Lógica del método para eliminar un estudiante por ID
    public void eliminarEstudiante(Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        estudianteRepository.delete(estudiante);
    }

    @Override //Lógica del método para listar a todos los estudiantes
    public List<EstudianteDTO> listarEstudiantes() {
        return estudianteRepository.findAll()
            .stream()
            .map(estudianteMapper::toDTO)
            .toList();
    }

    @Override //Lógica del método para actualizar solamente el grado del estudiante mediante ID
    public EstudianteDTO actualizarGradoEstudiante(Integer idPersona, Integer nuevoGrado) {
        Estudiante estudiante = estudianteRepository.findById(idPersona)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + idPersona));
        
        estudiante.setGrado(nuevoGrado);
        Estudiante actualizado = estudianteRepository.save(estudiante);

        return estudianteMapper.toDTO(actualizado);
    }

    ////Lógica de los filtros
    @Override
    public List<EstudianteDTO> filtrar (Integer grado, String nombre, String apellido, String email, Integer numeroMatricula){
        return estudianteRepository.filtrarEstudiantes(grado, nombre, apellido, email, numeroMatricula)
            .stream()
            .map(estudianteMapper::toDTO)
            .toList();
    }
        


  
}
