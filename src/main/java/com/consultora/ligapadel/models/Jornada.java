package com.consultora.ligapadel.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="jornadas", uniqueConstraints = {@UniqueConstraint(name="uk_jornada_por_liga", columnNames = {
        "id_liga", "jornada"}) })
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_jornada", nullable = false)
    private Long idJornada;

    @ManyToOne
    @JoinColumn(name="id_liga")
    private Liga liga;

    @OneToMany(mappedBy = "jornada")
    private List<Enfrentamiento> enfrentamiento;
}
