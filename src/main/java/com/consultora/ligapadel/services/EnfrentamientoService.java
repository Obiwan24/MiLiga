package com.consultora.ligapadel.services;

import com.consultora.ligapadel.repositories.EnfrentamientoRepository;
import com.consultora.ligapadel.repositories.JornadaRepository;
import com.consultora.ligapadel.repositories.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnfrentamientoService {

    @Autowired
    private EnfrentamientoRepository enfrentamientoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private JornadaRepository jornadaRepository;
}
