package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.models.Jornada;
import com.consultora.ligapadel.models.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {

    //Metodo para obtener jornadas de una liga
    List<Jornada> findByLiga(Liga idLiga);
}
