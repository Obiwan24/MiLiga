package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    //Busca en inscripciones y filtra por liga
    List<Usuario> findByInscripcionesLigaIdLiga(Long idLiga);
}
