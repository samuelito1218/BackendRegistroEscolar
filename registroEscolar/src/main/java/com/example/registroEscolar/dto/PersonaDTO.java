package com.example.registroEscolar.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonaDTO {

    private Integer idPersona;

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(
    regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$",
    message = "El nombre solo debe contener letras y espacios"
    )
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Pattern(
    regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$",
    message = "El apellido solo puede contener letras y espacios"
    )
    private String apellido;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 10, message = "El número de teléfono debe tener entre 7 y 10 dígitos")
    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números")
    private String telefono;

    
}
