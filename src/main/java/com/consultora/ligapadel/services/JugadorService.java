package com.consultora.ligapadel.services;

import com.consultora.ligapadel.models.Jugador;
import com.consultora.ligapadel.repositories.JugadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    //Método para crear jugador
    @Transactional
    public Jugador crearJugador (Jugador nuevoJugador) {

        //Guarda el jugador nuevo
        return jugadorRepository.save(nuevoJugador);
    }

    //Método para borra un jugador
    @Transactional
    public void borrarJugador(Long idJugador){
        //Valida si existe el jugador en la BBDD
        if (!jugadorRepository.existsById(idJugador)){
            throw new RuntimeException("El jugador con id: " + idJugador + " no existe.");
        }
        //Se borra el jugador de la BBDD
        jugadorRepository.deleteById(idJugador);
    }

    //Método para formatear la tabla de jugadores
    @Transactional
    public void formatearJugadores(){
        jugadorRepository.deleteAll();
    }

    //Método para borrar jugadores de una liga en concreto
    @Transactional
    public void formatearJugadoresPorLiga(Long idLiga){
        //Buscamos los jugadores de la liga
        List<Jugador> jugadoresLiga = jugadorRepository.findbyInscripcionesLigaIdLiga(idLiga);
        //Si hay jugadores los borra
        if (!jugadoresLiga.isEmpty()) {
            jugadorRepository.deleteAll(jugadoresLiga);
        }
    }

    @Transactional
    public Jugador modificarJugador(Long idJugador, Jugador datosNuevos) {
        //Buscamos al jugador en la base de datos
        Jugador jugadorRegistrado = jugadorRepository.findById(idJugador)
                .orElseThrow(() -> new RuntimeException("El jugador con id " + idJugador +" no está registrado."));

        //Actualiza campos permitidos
        jugadorRegistrado.setNombre(datosNuevos.getNombre());
        jugadorRegistrado.setDni(datosNuevos.getDni());
        jugadorRegistrado.setPosicion(datosNuevos.getPosicion());

        return jugadorRepository.save(datosNuevos);
    }
}
