package com.example.registroEscolar.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InscripcionDTO {

    private Integer idInscripcion;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer idEstudiante;

    @NotNull(message = "El ID del curso es obligatorio")
    private Integer idCurso;


    @NotNull(message = "La fecha de inscripción es obligatoria")
    @PastOrPresent(message = "La fecha de inscripción debe ser hoy o en el pasado")
    private LocalDate fechaInscripcion;
}
