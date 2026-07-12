package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Jugador;
import com.consultora.ligapadel.repositories.JugadorRepository;
import com.consultora.ligapadel.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadoresController {

    @Autowired // Referencia al repositorio del jugador
    private JugadorRepository jugadorRepository;

    @Autowired
    private JugadorService jugadorService;

    //Metodo para crear el jugador
    @PostMapping
    public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
        Jugador jugadorCreado = jugadorService.crearJugador(jugador);
        return new ResponseEntity<>(jugadorCreado, HttpStatus.CREATED);
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

    //Método para borrar jugadores
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarJugador(@PathVariable("id") Long idJugador){
        jugadorService.borrarJugador(idJugador);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Formatear tabla de jugadores
    @DeleteMapping("/limpiar-tabla")
    public ResponseEntity<Void> formatearTabla(){
        jugadorService.formatearJugadores();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Formatear jugadores de una Liga
    @DeleteMapping("/BorrarJugadoresPorLiga")
    public ResponseEntity<Void> formatearJugadoresPorLiga(@RequestParam("LigaId") Long idLiga){
        jugadorService.formatearJugadoresPorLiga(idLiga);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
