package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.models.JugadoresLiga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadoresLigaRepository extends JpaRepository<JugadoresLiga, Long> {
}
