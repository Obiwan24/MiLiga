package com.consultora.ligapadel.models;

import com.consultora.ligapadel.enums.EstadoEnfrentamiento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="enfrentamientos")
public class Enfrentamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_enfrentamiento", nullable = false)
    private Long idEnfrentamiento;

    @ManyToOne
    @JoinColumn(name="liga")
    private Liga liga;

    @ManyToOne
    @JoinColumn(name="equipo_local")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name="equipo_visitante")
    private Equipo equipoVisitante;

    @Column(name="fecha_inicio_enfrentamiento", nullable = false)
    private LocalDate fechaInicioEnfrentamiento;

    @Column(name="fecha_fin_enfrentamiento")
    private LocalDate fechaFinEnfrentamiento;

    @Column(name="jornada", nullable = false)
    private int jornada;

    @OneToMany(mappedBy = "enfrentamiento", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Partido> partidos = new ArrayList<>();

    @Column(name="observaciones")
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(name="estado_enfrentamiento", nullable = false, length = 30)
    private EstadoEnfrentamiento estado;

    //Métodos añadir y eliminar partidos

    public void addPartido(Partido partido) {
        this.partidos.add(partido);
        partido.setEnfrentamiento(this);
    }

    public void removePartido(Partido partido) {
        this.partidos.remove(partido);
        partido.setEnfrentamiento(null);
    }



    //Getters y setters
    public Long getIdEnfrentamiento() {return idEnfrentamiento;}
    public void setIdEnfrentamiento(Long idEnfrentamiento) {this.idEnfrentamiento = idEnfrentamiento;}

    public Liga getLiga() {return liga;}
    public void setLiga(Liga liga) {this.liga = liga;}

    public Equipo getEquipoLocal() {return equipoLocal;}
    public void setEquipoLocal(Equipo equipoLocal) {this.equipoLocal = equipoLocal;}

    public Equipo getEquipoVisitante() {return equipoVisitante;}
    public void setEquipoVisitante(Equipo equipoVisitante) {this.equipoVisitante = equipoVisitante;}

    public LocalDate getFechaInicioEnfrentamiento() {return fechaInicioEnfrentamiento;}
    public void setFechaInicioEnfrentamiento(LocalDate fechaInicioEnfrentamiento) {this.fechaInicioEnfrentamiento = fechaInicioEnfrentamiento;}

    public LocalDate getFechaFinEnfrentamiento() {return fechaFinEnfrentamiento;}
    public void setFechaFinEnfrentamiento(LocalDate fechaFinEnfrentamiento) {this.fechaFinEnfrentamiento = fechaFinEnfrentamiento;}

    public int getJornada() {return jornada;}
    public void setJornada(int jornada) {this.jornada = jornada;}

    public String getObservaciones() {return observaciones;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}

    public List<Partido> getPartidos() {return partidos;}
    public void setPartidos(List<Partido> partidos) {this.partidos = partidos;}

    public EstadoEnfrentamiento getEstado() {return estado;}

    public void setEstado(EstadoEnfrentamiento estado) {this.estado = estado;}
}
