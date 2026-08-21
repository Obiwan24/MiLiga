package com.consultora.ligapadel.repositories;

import com.consultora.ligapadel.enums.Rol;
import com.consultora.ligapadel.models.Inscripcion;
import com.consultora.ligapadel.models.Usuario;
import com.consultora.ligapadel.models.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByUsuarioAndLiga(Usuario usuario, Liga liga, Rol rolUsuario);

    //Optional<Inscripcion> cabe la posibilidad de que el usuario en una liga no exista todavia
    //finBy: Quiero hacer un SELECT
    //And: Le dice que incluya AND en el SQL
    Optional<Inscripcion> findByUsuarioDniAndLigaIdLiga(String dni, Long idLiga, Rol rol);

    //Método para comprobar que un equipo ya tiene capitan
    List<Inscripcion> findByUsuarioDniAndLigaIdLiga(String dni,Long idLiga);
        boolean existsByEquipoIdEquipoAndRolUsuario(Long idEquipo, Rol rol);

}
