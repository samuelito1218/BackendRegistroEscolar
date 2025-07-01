package com.example.registroEscolar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.registroEscolar.dto.AdministrativoDTO;
import com.example.registroEscolar.exception.PersonaConRolExistenteException;
import com.example.registroEscolar.mapper.AdministrativoMapper;
import com.example.registroEscolar.model.Administrativo;
import com.example.registroEscolar.model.Persona;
import com.example.registroEscolar.repository.AdministrativoRepository;
import com.example.registroEscolar.repository.EstudianteRepository;
import com.example.registroEscolar.repository.PersonaRepository;
import com.example.registroEscolar.repository.ProfesorRepository;
import com.example.registroEscolar.service.AdministrativoService;

@Service
public class AdministrativoServiceImpl implements AdministrativoService {

    private final AdministrativoRepository administrativoRepository;
    private final AdministrativoMapper administrativoMapper;
    private final PersonaRepository personaRepository;
    private final ProfesorRepository profesorRepository;
    private final EstudianteRepository estudianteRepository;

    public AdministrativoServiceImpl(AdministrativoRepository administrativoRepository, AdministrativoMapper administrativoMapper, PersonaRepository personaRepository, ProfesorRepository profesorRepository, EstudianteRepository estudianteRepository) {
        this.administrativoRepository = administrativoRepository;
        this.administrativoMapper = administrativoMapper;
        this.personaRepository = personaRepository;
        this.profesorRepository = profesorRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Override //Lógica del método para registrar un administrativo
    public AdministrativoDTO registrar(AdministrativoDTO dto) {
        Integer id = dto.getIdPersona();
        if (profesorRepository.existsById(id)|| estudianteRepository.existsById(id)) {
            throw new PersonaConRolExistenteException("La persona con ID " + id + " ya está registrada como profesor o estudiante.");
        }

        if(administrativoRepository.existsById(id)) {
            throw new PersonaConRolExistenteException("Administrativo ya registrado con ID: " + id);
        }
        Persona persona = personaRepository.findById(dto.getIdPersona())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + dto.getIdPersona()));

        Administrativo administrativo = administrativoMapper.toEntity(dto, persona);
        Administrativo guardado = administrativoRepository.save(administrativo);

        return administrativoMapper.toDTO(guardado);
    }

    @Override //Lógica del método para listar administrativos
    public List<AdministrativoDTO> listar() {
        return administrativoRepository.findAll()
                .stream()
                .map(administrativoMapper::toDTO)
                .toList();
    }

    @Override //Lógica del método para obtener un administrativo por ID
    public AdministrativoDTO obtenerPorId(Integer idPersona) {
        Administrativo administrativo = administrativoRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Administrativo no encontrado con ID: " + idPersona));
        return administrativoMapper.toDTO(administrativo);
    }

    @Override //Lógica del método para eliminar un administrativo por ID
    public void eliminar(Integer idPersona) {
        Administrativo administrativo = administrativoRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Administrativo no encontrado con ID: " + idPersona));
        administrativoRepository.delete(administrativo);
    }

    @Override // Lógica del método para actualizar la información de un administrativo
    public AdministrativoDTO actualizarInformacionAdministrativo(Integer idPersona, AdministrativoDTO administrativoDTO) {
        Administrativo administrativo = administrativoRepository.findById(idPersona)
            .orElseThrow(() -> new RuntimeException("Administrativo no encontrado con ID: " + idPersona));
    
        administrativo.setCargo(administrativoDTO.getCargo());
        administrativo.setDepartamento(administrativoDTO.getDepartamento());
        
        Administrativo actualizado = administrativoRepository.save(administrativo);
        
        return administrativoMapper.toDTO(actualizado);
    }

    // Lógica de filtros
    @Override
    public List<AdministrativoDTO> filtrar(String cargo, String departamento, String nombre, String apellido) {
        return administrativoRepository.filtrarAdministrativos(cargo, departamento, nombre, apellido)
                .stream()
                .map(administrativoMapper::toDTO)
                .toList();
    }

        
        
    
}
