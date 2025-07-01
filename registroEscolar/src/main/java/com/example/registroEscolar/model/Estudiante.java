package com.example.registroEscolar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    
    private Integer idPersona;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "idPersona")
    @MapsId
    private Persona persona;

    @Column(unique = true)
    @NotNull(message = "El número de matrícula es obligatorio")
    private Integer numeroMatricula;

    @NotNull(message = "El grado es obligatorio")
    @Min(value = 1, message = "El grado debe ser al menos 1")
    @Max(value = 12, message = "El grado no puede ser mayor a 12")
    private Integer grado;
    
}
