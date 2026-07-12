package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.models.Inscripcion;
import com.consultora.ligapadel.models.Jugador;
import com.consultora.ligapadel.models.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByJugadorAndLiga(Jugador jugador, Liga liga);

    //Optional<Inscripcion> cabe la posibilidad de que el jugador en una liga no exista todavia
    //finBy: Quiero hacer un SELECT
    //And: Le dice que incluya AND en el SQL
    Optional<Inscripcion> findByJugadorIdJugadorAndLigaIdLiga(Long idJugador, Long idLiga);
}
