package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Liga;
import com.consultora.ligapadel.repositories.LigaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ligas") //La URL base para este controlador
public class LigaController {

    @Autowired
    private LigaRepository ligaRepository; // Inyecta el repositorio creado

    //Endpoint para obtener todas las ligas
    @GetMapping //GETMapping para obtener un dato de la tabla
    public List<Liga> obtenerTodasLasLigas() {
        return ligaRepository.findAll(); // método heredado de JpaRepository
    }

    //Método para guardar datos
    @PostMapping //POSTMappìng para escribir un dato en la tabla
    public Liga crearLiga(@RequestBody Liga nuevaLiga) {
        return ligaRepository.save(nuevaLiga);
    }

    //Método para buscar liga por ID
    @GetMapping("/{id}")
    public Liga buscarLiga(@PathVariable Long id) {
        return ligaRepository.findById(id).orElse(null);
    }
}
