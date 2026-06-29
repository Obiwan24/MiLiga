package com.consultora.ligapadel.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="ligas") // Como se llamará la tabla en DBeaver
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    @Column(name = "id_Liga", nullable = false)
    private Long idLiga;

    @Column(name = "nombre_liga", nullable = false, length = 100) // Debe tener nombre
    private String nombreLiga;

    @Column(name = "ambito")
    private String ambito;

    @Column(name = "numero_equipos")
    private int numeroEquipos;

    @Column(name = "numero_jugadores")
    private int numeroJugadores;

    @Column(name = "fecha_inicio_inscripcion")
    private LocalDate fechaInicioInscr;

    @Column(name = "fecha_fin_inscripcion")
    private LocalDate fechaFinInscr;

    @Column(name = "fecha_inicio_liga")
    private LocalDate fechaInicioLiga;

    @Column(name = "fecha_fin_liga")
    private LocalDate fechaFinLiga;

    public Long getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(Long idLiga) {
        this.idLiga = idLiga;
    }

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public int getNumeroEquipos() {
        return numeroEquipos;
    }

    public void setNumeroEquipos(int numeroEquipos) {
        this.numeroEquipos = numeroEquipos;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public LocalDate getFechaInicioInscr() {
        return fechaInicioInscr;
    }

    public void setFechaInicioInscr(LocalDate fechaInicioInscr) {
        this.fechaInicioInscr = fechaInicioInscr;
    }

    public LocalDate getFechaFinInscr() {
        return fechaFinInscr;
    }

    public void setFechaFinInscr(LocalDate fechaFinInscr) {
        this.fechaFinInscr = fechaFinInscr;
    }

    public LocalDate getFechaInicioLiga() {
        return fechaInicioLiga;
    }

    public void setFechaInicioLiga(LocalDate fechaInicioLiga) {
        this.fechaInicioLiga = fechaInicioLiga;
    }

    public LocalDate getFechaFinLiga() {
        return fechaFinLiga;
    }

    public void setFechaFinLiga(LocalDate fechaFinLiga) {
        this.fechaFinLiga = fechaFinLiga;
    }
}
