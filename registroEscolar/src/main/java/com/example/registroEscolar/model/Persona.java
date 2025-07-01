package com.example.registroEscolar.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;
    
    @NotBlank(message = "Es obligatorio ingresar el nombre")
    @Pattern(
    regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$",
    message = "El nombre solo puede contener letras y espacios"
    )
    private String nombre;

    @NotBlank(message = "Es obligatorio ingresar el apellido")
    @Pattern(
    regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$",
    message = "El apellido solo puede contener letras y espacios"
    )
    private String apellido;

    @NotNull(message = "Es obligatorio ingresar la fecha de nacimiento")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 10, message = "El número de teléfono debe tener entre 7 y 10 dígitos")
    @Pattern(regexp = "\\d+", message = "El teléfono debe ser numérico")
    private String telefono;
}
