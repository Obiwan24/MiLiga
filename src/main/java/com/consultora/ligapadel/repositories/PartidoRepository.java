package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.models.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository <Partido, Long>{

}
