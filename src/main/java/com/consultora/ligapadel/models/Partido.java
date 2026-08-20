package com.consultora.ligapadel.models;

import com.consultora.ligapadel.enums.EstadoPartido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="partidos")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_partido", nullable = false)
    private Long idPartido;

    @ManyToOne
    @JoinColumn(name="id_Enfrentamiento", nullable = false)
    @JsonIgnore
    private Enfrentamiento enfrentamiento;

    private Integer orden;

    @ManyToOne
    @JoinColumn(name="id_jugador1_local")
    private Inscripcion jugador1Local;

    @ManyToOne
    @JoinColumn(name="id_jugador2_local")
    private Inscripcion jugador2Local;

    @ManyToOne
    @JoinColumn(name="id_jugador1_visitante")
    private Inscripcion jugador1Visitante;

    @ManyToOne
    @JoinColumn(name="id_jugador2_visitante")
    private Inscripcion jugador2Visitante;

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
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name="estado_partido", nullable = false, length = 30)
    private EstadoPartido estado;

    //Getters y Setters

    public Long getIdPartido() {
        return idPartido;
    }
    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
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

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }

    public Enfrentamiento getEnfrentamiento() { return enfrentamiento; }
    public void setEnfrentamiento(Enfrentamiento enfrentamiento) { this.enfrentamiento = enfrentamiento; }

    public EstadoPartido getEstado() {return estado;}
    public void setEstado(EstadoPartido estado) {this.estado = estado;}

    //Get&Set jugadores

    public Inscripcion getJugador1Local() {
        return jugador1Local;
    }
    public void setJugador1Local(Inscripcion jugador1Local) {
        this.jugador1Local = jugador1Local;
    }

    public Inscripcion getJugador2Local() {
        return jugador2Local;
    }
    public void setJugador2Local(Inscripcion jugador2Local) {
        this.jugador2Local = jugador2Local;
    }

    public Inscripcion getJugador1Visitante() {
        return jugador1Visitante;
    }
    public void setJugador1Visitante(Inscripcion jugador1Visitante) {
        this.jugador1Visitante = jugador1Visitante;
    }

    public Inscripcion getJugador2Visitante() {
        return jugador2Visitante;
    }
    public void setJugador2Visitante(Inscripcion jugador2Visitante) {
        this.jugador2Visitante = jugador2Visitante;
    }
}
