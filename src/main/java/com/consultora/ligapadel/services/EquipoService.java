package com.consultora.ligapadel.services;

import com.consultora.ligapadel.models.Equipo;
import com.consultora.ligapadel.models.Liga;
import com.consultora.ligapadel.repositories.EquipoRepository;
import com.consultora.ligapadel.repositories.JugadorRepository;
import com.consultora.ligapadel.repositories.LigaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private LigaRepository ligaRepository;

    @Transactional
    public Equipo crearEquipo(Equipo nuevoEquipo) {
        //Busca la liga en la que se va a registrar el equipo
        Liga liga = ligaRepository.findById(nuevoEquipo.getLiga().getIdLiga()).orElse(null);

        if (liga != null){
            //Suma 1 a los equipos que tiene la liga
            int equiposActuales = liga.getNumeroEquipos(); //Pregunta cuantos equipos hay en la liga
            liga.setNumeroEquipos(equiposActuales + 1); //Le suma 1 a los equipos que hay

            //Guarda la liga actualizada
            ligaRepository.save(liga);
        }
        //Guarda el equipo en su tabla
        return equipoRepository.save(nuevoEquipo);
    }
}
