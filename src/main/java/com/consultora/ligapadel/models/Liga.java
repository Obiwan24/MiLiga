package com.consultora.ligapadel.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="ligas") // Como se llamará la tabla en DBeaver
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    @Column(name = "id_Liga", nullable = false)
    private Long idLiga;

    @Column(name = "nombre_liga", nullable = false, length = 100) // Debe tener nombre
    private String nombreLiga;

    @Column(name = "ambito", nullable = false)
    private String ambito;

    @Column(name = "numero_equipos")
    private int numEquipos = 0;

    @Column(name = "numero_jugadores")

    private int numJugadores = 0;

    @Column(name = "fecha_inicio_inscripcion")
    private LocalDate fechaInicioInscr;

    @Column(name = "fecha_fin_inscripcion")
    private LocalDate fechaFinInscr;

    @Column(name = "fecha_inicio_liga")
    private LocalDate fechaInicioLiga;

    @Column(name = "fecha_fin_liga")
    private LocalDate fechaFinLiga;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL)
    @JsonManagedReference // (parte "padre")Serializa la lista con normalidad, evita bucle infinito
    public List<Partido> partidos;

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
        return numEquipos;
    }

    public void setNumeroEquipos(int numeroEquipos) {
        this.numEquipos = numeroEquipos;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
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

    //Logica de negocio
    public void incrementarJugadoresLiga() {
        this.numJugadores++;
    }
}
