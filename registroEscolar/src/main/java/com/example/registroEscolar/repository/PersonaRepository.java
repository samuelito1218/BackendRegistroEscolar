package com.example.registroEscolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.registroEscolar.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {


    //Filtro combinado para buscar por nombre y apellido
    List<Persona> findByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCase(String nombre, String apellido);


    //Filtro para buscar por nombre
    List<Persona> findByNombreContainingIgnoreCase(String nombre);


    //Filtro para buscar por apellido
    List<Persona> findByApellidoContainingIgnoreCase(String apellido);

    //Filtro para buscar por email
    List<Persona> findByEmailContainingIgnoreCase(String email);

    
}
