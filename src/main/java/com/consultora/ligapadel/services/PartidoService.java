package com.consultora.ligapadel.services;

import com.consultora.ligapadel.repositories.EnfrentamientoRepository;
import com.consultora.ligapadel.repositories.EquipoRepository;
import com.consultora.ligapadel.repositories.InscripcionRepository;
import com.consultora.ligapadel.repositories.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private EnfrentamientoRepository enfrentamientoRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

}
