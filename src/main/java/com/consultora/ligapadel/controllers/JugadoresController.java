package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Jugador;
import com.consultora.ligapadel.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadoresController {

    @Autowired // Referencia al repositorio del jugador
    private JugadorRepository jugadorRepository;

    //Metodo para crear el jugador
    @PostMapping
    public Jugador crearJugador(@RequestBody Jugador nuevoJugador) {
         return jugadorRepository.save(nuevoJugador);
    }

    //Método para buscar jugadores
    @GetMapping("/{id}")
    public Jugador buscarJugador(@PathVariable Long id){
        return jugadorRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Jugador> listarJugadores(){
        return jugadorRepository.findAll();
    }
}
