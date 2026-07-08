package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Equipo;
import com.consultora.ligapadel.repositories.EquipoRepository;
import com.consultora.ligapadel.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
