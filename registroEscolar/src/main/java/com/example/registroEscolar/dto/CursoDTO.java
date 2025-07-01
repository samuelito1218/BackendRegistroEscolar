package com.example.registroEscolar.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoDTO {

    private Integer idCurso;

    @NotBlank(message = "El nombre del curso es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción del curso es obligatoria")
    private String descripcion;

    @NotNull(message = "Los créditos del curso son obligatorios")
    @Min(value = 1, message = "Una materia debe valer al menos 1 crédito")
    @Max(value = 4, message = "Una materia no puede valer más de 4 créditos")
    private Integer creditos;

    @NotNull(message = "El ID del profesor es obligatorio")
    private Integer idProfesor;
    
}
