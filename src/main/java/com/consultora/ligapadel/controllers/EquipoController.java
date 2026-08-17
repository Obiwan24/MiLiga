package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Equipo;
import com.consultora.ligapadel.repositories.EquipoRepository;
import com.consultora.ligapadel.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos") //URL del controlador
public class EquipoController {
    // Referencia al Service del equipo
    @Autowired
    private EquipoService equipoService;

    // Mantenemos repositorio para las consultas simples de lectura
    @Autowired
    private EquipoRepository equipoRepository;

    //Metodo para crear equipos //Actualizado al crear EquipoService
    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo nuevoEquipo){
        return equipoService.crearEquipo(nuevoEquipo);
    }

    //Métodos para buscar equipos
    @GetMapping("/{id}")
    public Equipo buscarEquipo(@PathVariable Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Equipo> listarTodosEquipos(){
        return equipoRepository.findAll();
    }

    //Borrar equipo
    @DeleteMapping
    public ResponseEntity<Void> borrarEquipo(@PathVariable("id") Long idEquipo) {
        equipoService.borrarEquipo(idEquipo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Modificar equipo
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> modificarEquipo(@PathVariable("id") Long idEquipo,
                                                  @RequestBody Equipo datosNuevos) {
        //Llamar metodo para modificar los datos
        Equipo equipoActualizado = equipoService.modificarEquipo(idEquipo, datosNuevos);

        //Devuelve equipo modificado
        return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);

    }
}
