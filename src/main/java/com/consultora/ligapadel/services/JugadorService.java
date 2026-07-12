package com.consultora.ligapadel.services;

import com.consultora.ligapadel.models.Jugador;
import com.consultora.ligapadel.repositories.JugadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Transactional
    public Jugador crearJugador (Jugador nuevoJugador) {

        //Guarda el jugador nuevo
        return jugadorRepository.save(nuevoJugador);
    }
}
