package com.consultora.ligapadel.services;

import com.consultora.ligapadel.repositories.EquipoRepository;
import com.consultora.ligapadel.repositories.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {
    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private PartidoRepository partidoRepository;


}
