package com.example.registroEscolar.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class ProfesorDTO {

    @NotNull(message = "El ID de la persona es obligatorio")
    private Integer idPersona;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private String email;

    private String telefono;

    @NotNull(message = "La especialidad es obligatoria")
    @Column(length = 100)
    private String especialidad;

    @NotNull(message = "La fecha de contratación es obligatoria")
    @PastOrPresent(message = "La fecha de contratación debe ser hoy o en el pasado")
    private LocalDate fechaContratacion;

   

   

    
}
