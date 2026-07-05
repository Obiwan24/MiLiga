package com.consultora.ligapadel.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="partidos")
public class Partidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="partidos", nullable = false)
    private Long idPartido;

    @Column(name="eq_local")
    private String eqLocal;

    @Column(name="eq_visitante")
    private String eqVisitante;

    @Column(name="j_local")
    private int jLocal = 0;

    @Column(name="j_visitante")
    private int jVisitante = 0;

    @Column(name="set_local")
    private int setLocal = 0;

    @Column(name="set_visitante")
    private int setVisitante = 0;

    @Column(name="ganador")
    private String ganador;

    @Column(name="fecha")
    private Date fecha;

    @Column(name="jornada")
    private int jornada = 1;

    //Getters y Setters

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public String getEqLocal() {
        return eqLocal;
    }

    public void setEqLocal(String eqLocal) {
        this.eqLocal = eqLocal;
    }

    public String getEqVisitante() {
        return eqVisitante;
    }

    public void setEqVisitante(String eqVisitante) {
        this.eqVisitante = eqVisitante;
    }

    public int getjLocal() {
        return jLocal;
    }

    public void setjLocal(int jLocal) {
        this.jLocal = jLocal;
    }

    public int getjVisitante() {
        return jVisitante;
    }

    public void setjVisitante(int jVisitante) {
        this.jVisitante = jVisitante;
    }

    public int getSetLocal() {
        return setLocal;
    }

    public void setSetLocal(int setLocal) {
        this.setLocal = setLocal;
    }

    public int getSetVisitante() {
        return setVisitante;
    }

    public void setSetVisitante(int setVisitante) {
        this.setVisitante = setVisitante;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }
}
