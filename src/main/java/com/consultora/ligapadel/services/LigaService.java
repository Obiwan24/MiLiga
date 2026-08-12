package com.consultora.ligapadel.services;

import com.consultora.ligapadel.models.Liga;
import com.consultora.ligapadel.repositories.LigaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LigaService {

    @Autowired
    private LigaRepository ligaRepository;


    @Transactional
    public Liga crearLiga(Liga nuevaLiga) {
        //Busca la liga en la BBDD
        Liga liga = ligaRepository.findById(nuevaLiga.getIdLiga()).orElse(null);

        //Guarda la liga en bbdd
        return ligaRepository.save(nuevaLiga);
    }

}
