package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.JugadoresLiga;
import com.consultora.ligapadel.services.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<?> crearInscripcion(@RequestParam Long jugadorId, @RequestParam Long equipoId, @RequestParam Long ligaId){
        //Llamada al servicio y guarda el resultado en una variable
        JugadoresLiga resultado = inscripcionService.inscribirJugador(jugadorId, equipoId, ligaId);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas(){
        //Llama al metodo y guarda el resultado en una variable local
        List<JugadoresLiga> lista = inscripcionService.buscarTodos();
        //Responder con un 200 OK y se adjunta en la lista
        return ResponseEntity.ok(lista);
    }
}
