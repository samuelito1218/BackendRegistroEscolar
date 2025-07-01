package com.example.registroEscolar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.registroEscolar.dto.CursoDTO;
import com.example.registroEscolar.exception.CursoConProfesorAsignadoException;
import com.example.registroEscolar.exception.ResourceNotFoundException;
import com.example.registroEscolar.mapper.CursoMapper;
import com.example.registroEscolar.model.Curso;
import com.example.registroEscolar.model.Profesor;
import com.example.registroEscolar.repository.CursoRepository;
import com.example.registroEscolar.repository.ProfesorRepository;
import com.example.registroEscolar.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final ProfesorRepository profesorRepository;

    public CursoServiceImpl(CursoRepository cursoRepository, CursoMapper cursoMapper, ProfesorRepository profesorRepository) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
        this.profesorRepository = profesorRepository;
    }

    @Override //Lógica del método para registrar un curso
    public CursoDTO registrarCurso(CursoDTO cursoDTO) {
        Profesor profesor = profesorRepository.findById(cursoDTO.getIdProfesor())
            .orElseThrow(() -> new ResourceNotFoundException("Profesor con ID " + cursoDTO.getIdProfesor() + " no encontrado"));
            
            boolean yaExisteCursoConNombre = cursoRepository.existsByNombreIgnoreCaseAndProfesor_IdPersona(cursoDTO.getNombre(), cursoDTO.getIdProfesor());
            if (yaExisteCursoConNombre) {
                
                throw new CursoConProfesorAsignadoException("Este profesor ya tiene un curso con el nombre: " + cursoDTO.getNombre());
    }
    
        Curso curso = cursoMapper.toEntity(cursoDTO, profesor);
        Curso registrado = cursoRepository.save(curso);
        return cursoMapper.toDTO(registrado);
    }


    @Override //Lógica del método para listar cursos
    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll()
        .stream()
        .map(cursoMapper::toDTO)
        .toList();
    }

    @Override //lógica del método para buscar curso por id
    public CursoDTO buscarCursoPorId(Integer idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + idCurso));
        return cursoMapper.toDTO(curso);
    }

    @Override// lógica del método para actualizar curso por ID
    public CursoDTO actualizarCurso(CursoDTO cursoDTO, Integer idCurso) {
        
        Curso cursoExistente = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + idCurso));


        Profesor nuevoProfesor = profesorRepository.findById(cursoDTO.getIdProfesor())
            .orElseThrow(() -> new ResourceNotFoundException("Profesor con ID " + cursoDTO.getIdProfesor() + " no encontrado"));
            
            /*if (cursoExistente.getProfesor().getIdPersona().equals(cursoDTO.getIdProfesor())) {
                throw new CursoConProfesorAsignadoException("Este profesor ya está asignado a este curso.");
            }*/
        cursoExistente.setNombre(cursoDTO.getNombre());
        cursoExistente.setDescripcion(cursoDTO.getDescripcion());
        cursoExistente.setCreditos(cursoDTO.getCreditos());
        cursoExistente.setProfesor(nuevoProfesor);
        Curso cursoActualizado = cursoRepository.save(cursoExistente);
        return cursoMapper.toDTO(cursoActualizado);
    }

    @Override //Lógica del método para eliminar curso por ID
    public void eliminarCurso(Integer idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + idCurso));
        cursoRepository.delete(curso);
    }

    //Lógica de Filtros
    @Override
    public List<CursoDTO> filtrarCursos(String nombre, Integer creditos, Integer idProfesor) {
        return cursoRepository.filtrarCursos(nombre, creditos, idProfesor)
            .stream()
            .map(cursoMapper::toDTO)
            .toList();
        }


    
    
}
