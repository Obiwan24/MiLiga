package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    //Busca en inscripciones y filtra por liga
    List<Jugador> findByInscripcionesLigaIdLiga(Long idLiga);
}
