package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Inscripcion;
import com.consultora.ligapadel.services.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Inscripcion resultado = inscripcionService.inscribirJugador(jugadorId, equipoId, ligaId);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas(){
        //Llama al metodo y guarda el resultado en una variable local
        List<Inscripcion> lista = inscripcionService.buscarTodos();
        //Responder con un 200 OK y se adjunta en la lista
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public Inscripcion buscarInscripcion(@PathVariable Long idInscripcion) {
        return inscripcionService.buscarInscripcion(idInscripcion);
    }

    @DeleteMapping("/Borrar_inscripcion")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable("id") Long idInscripcion) {
        inscripcionService.eliminarInscripcion(idInscripcion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Modificar equipo
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> modificarEquipo(
            @PathVariable("id") Long idEquipo,
            @RequestBody Inscripcion nuevosDatos) {
        //Llama al método
        Inscripcion inscripcionActualizada = inscripcionService.modificarInscripcion(idEquipo, nuevosDatos);

        //Devuelve el equipo modificado
        return new ResponseEntity<>(inscripcionActualizada, HttpStatus.OK);
    }
}
