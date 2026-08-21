package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.models.Enfrentamiento;
import com.consultora.ligapadel.models.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnfrentamientoRepository extends JpaRepository<Enfrentamiento, Long> {

    //Metodo para obtener enfrentamientos de una jornada
    List<Enfrentamiento> findByJornada(Jornada idJornada);
}
