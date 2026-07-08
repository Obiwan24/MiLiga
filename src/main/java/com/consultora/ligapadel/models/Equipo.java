package com.consultora.ligapadel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="Equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Equipo", nullable = false)
    private Long idEquipo;


    @Column(name="nombre_equipo")
    private String nombreEquipo;

    @Column(name="num_jugadores")
    private int numJugadores = 0;

    @Column(name="sede")
    private String sede;

    @Column(name="puntos")
    private int puntos = 0;

    @Column(name="penalizacion")
    private int penalizacion = 0;

    @Column(name="j_ganados")
    private int jGanados = 0;

    @Column(name="j_perdidos")
    private int jPerdidos = 0;

    @Column(name="p_ganados")
    private int pGanados = 0;

    @Column(name="p_perdidos")
    private int pPerdidos = 0;

    @ManyToOne
    @JsonIgnore //Evitar bucles en la salida JSON
    @JoinColumn(name="id_liga")
    private Liga liga;

    //Getters y setters
    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        this.penalizacion = penalizacion;
    }

    public int getjGanados() {
        return jGanados;
    }

    public void setjGanados(int jGanados) {
        this.jGanados = jGanados;
    }

    public int getjPerdidos() {
        return jPerdidos;
    }

    public void setjPerdidos(int jPerdidos) {
        this.jPerdidos = jPerdidos;
    }

    public int getpGanados() {
        return pGanados;
    }

    public void setpGanados(int pGanados) {
        this.pGanados = pGanados;
    }

    public int getpPerdidos() {
        return pPerdidos;
    }

    public void setpPerdidos(int pPerdidos) {
        this.pPerdidos = pPerdidos;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
}
