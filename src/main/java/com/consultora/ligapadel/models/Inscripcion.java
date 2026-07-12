package com.consultora.ligapadel.models;

import jakarta.persistence.*;

@Entity
@Table(name="inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Inscripcion", nullable = false)
    private Long idInscripcion;

    @ManyToOne
    @JoinColumn(name="id_Jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name="id_Liga")
    private Liga liga;

    @ManyToOne
    @JoinColumn(name="id_Equipo")
    private Equipo equipo;

    //Getters y Setters

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
