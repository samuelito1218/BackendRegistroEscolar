package com.example.registroEscolar.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "administrativo")
public class Administrativo {

    @Id
    private Integer idPersona;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "idPersona")
    @MapsId
    private Persona persona;

    @Column(length = 100, nullable = false)
    private String cargo;

    @Column(length = 100, nullable = false)
    private String departamento;
}
