package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.JugadoresLiga;
import com.consultora.ligapadel.services.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
