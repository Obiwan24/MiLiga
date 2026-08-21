package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.enums.Rol;
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
    public ResponseEntity<?> crearInscripcion(@RequestParam String dni,
                                              @RequestParam(required = false) Long equipoId,//(required = false) para que tenga en cuenta que no es necesario equipo con rol Admin.
                                              @RequestParam Long ligaId,
                                              @RequestParam Rol rolUsuario){
        //Llamada al servicio y guarda el resultado en una variable
        Inscripcion resultado = inscripcionService.inscribirUsuario(dni, equipoId, ligaId, rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas(){
        //Llama al metodo y guarda el resultado en una variable local
        List<Inscripcion> lista = inscripcionService.buscarTodos();
        //Responder con un 200 OK y se adjunta en la lista
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> buscarInscripcion(@PathVariable("id") Long idInscripcion) {
        Inscripcion resultado = inscripcionService.buscarInscripcion(idInscripcion);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable("id") Long idInscripcion) {
        inscripcionService.eliminarInscripcion(idInscripcion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Modificar equipo
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> modificarEquipo(
            @PathVariable("id") Long idInscripcion,
            @RequestBody Inscripcion nuevosDatos) {
        //Llama al método
        Inscripcion inscripcionActualizada = inscripcionService.modificarInscripcion(idInscripcion, nuevosDatos);

        //Devuelve el equipo modificado
        return new ResponseEntity<>(inscripcionActualizada, HttpStatus.OK);
    }
}
