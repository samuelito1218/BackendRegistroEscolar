package com.example.registroEscolar.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EstudianteDTO {

    @NotNull(message = "El ID de la persona es obligatorio")
    private Integer idPersona;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private String email;

    private String telefono;

    private String numeroMatricula; 

    @NotNull(message = "El grado es obligatorio")
    @Min(value = 1, message = "El grado debe ser al menos 1")
    @Max(value = 11, message = "El grado no puede ser mayor a 11")
    private Integer grado;
}
