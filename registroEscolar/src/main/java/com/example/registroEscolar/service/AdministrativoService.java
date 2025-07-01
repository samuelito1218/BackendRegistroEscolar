package com.example.registroEscolar.service;

import java.util.List;
import com.example.registroEscolar.dto.AdministrativoDTO;

public interface AdministrativoService {
    AdministrativoDTO registrar(AdministrativoDTO dto);
    List<AdministrativoDTO> listar();
    AdministrativoDTO obtenerPorId(Integer idPersona);
    void eliminar(Integer idPersona);
    AdministrativoDTO actualizarInformacionAdministrativo(Integer idPersona, AdministrativoDTO administrativoDTO);

    //Filtros
    List<AdministrativoDTO> filtrar(String cargo, String departamento, String nombre, String apellido);
}
