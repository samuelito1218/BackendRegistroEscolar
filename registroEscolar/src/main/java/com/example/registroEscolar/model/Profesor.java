package com.example.registroEscolar.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    private Integer idPersona;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "idPersona")
    @MapsId
    private Persona persona;


    private String especialidad;

    
    private LocalDate fechaContratacion;
    
}
