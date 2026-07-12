package com.consultora.ligapadel.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Jugador", nullable = false)
    private Long idJugador;

    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name="DNI", nullable = false, length = 100)
    private String dni;

    @Column(name="j_Ganados")
    private int jGanados;

    @Column(name="j_Perdidos")
    private int jPerdidos;

    @Column(name="p_Ganados")
    private int pGanados;

    @Column(name="p_Perdidos")
    private int pPerdidos;

    // "mappedBy" hace que no se cree una nueva tabla sino que busca
    //la variable jugador en la clase jugadoresLiga
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    //Getters y Setters

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getJGanados() {
        return jGanados;
    }

    public void setJGanados(int jGanados) {
        this.jGanados = jGanados;
    }

    public int getJPerdidos() {
        return jPerdidos;
    }

    public void setJPerdidos(int jPerdidos) {
        this.jPerdidos = jPerdidos;
    }

    public int getPGanados() {
        return pGanados;
    }

    public void setPGanados(int pGanados) {
        this.pGanados = pGanados;
    }

    public int getPPerdidos() {
        return pPerdidos;
    }

    public void setPPerdidos(int pPerdidos) {
        this.pPerdidos = pPerdidos;
    }

}
