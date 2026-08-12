package com.consultora.ligapadel.models;

import com.consultora.ligapadel.enums.Rol;
import jakarta.persistence.*;

@Entity
@Table(name="inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Inscripcion", nullable = false)
    private Long idInscripcion;

    @ManyToOne
    @JoinColumn(name="dni_jugador", nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name="id_Liga", nullable = false)
    private Liga liga;

    @ManyToOne
    @JoinColumn(name="id_Equipo")
    private Equipo equipo;

    @Enumerated(EnumType.STRING)
    @Column(name="rol", nullable = false)
    private Rol rolUsuario;

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

    public void setJugador(Jugador dni) {
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

    public Rol getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Rol rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
