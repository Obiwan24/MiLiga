package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Equipo;
import com.consultora.ligapadel.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos") //URL del controlador
public class EquipoController {
    // Referencia al repositorio del equipo
    @Autowired
    private EquipoRepository equipoRepository;

    //Metodo para crear equipos
    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo nuevoEquipo){
        return equipoRepository.save(nuevoEquipo);
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
}
