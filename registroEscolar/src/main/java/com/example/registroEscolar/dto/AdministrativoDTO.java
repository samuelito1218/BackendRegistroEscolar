package com.example.registroEscolar.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdministrativoDTO {

    
    private Integer idPersona;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    @NotNull(message = "El cargo es obligatorio")
    private String cargo;

    @NotNull(message = "El departamento es obligatorio")
    private String departamento;
}
