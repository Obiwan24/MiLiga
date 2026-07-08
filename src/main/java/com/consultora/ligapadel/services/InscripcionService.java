package com.consultora.ligapadel.services;

import com.consultora.ligapadel.models.Equipo;
import com.consultora.ligapadel.models.Jugador;
import com.consultora.ligapadel.models.JugadoresLiga;
import com.consultora.ligapadel.models.Liga;
import com.consultora.ligapadel.repositories.EquipoRepository;
import com.consultora.ligapadel.repositories.JugadorRepository;
import com.consultora.ligapadel.repositories.JugadoresLigaRepository;
import com.consultora.ligapadel.repositories.LigaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private LigaRepository ligaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadoresLigaRepository jugadoresLigaRepository;

    @Transactional
    public JugadoresLiga inscribirJugador(Long jugadorId, Long equipoId, Long ligaId) {
        //Valida que el jugador exista en la BBDD
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new RuntimeException("Error: El jugador con ID " + jugadorId + " no existe."));

        //Valida que el equipo existe
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Error: El equipo con ID " + equipoId + " no existe"));

        //Valida que la liga existe
        Liga liga = ligaRepository.findById(ligaId)
                .orElseThrow(() -> new RuntimeException("Error: La liga con ID " + ligaId + " no existe."));

        //Modifica contador de numero de jugadores y equipos
        int jugadoresActuales = equipo.getNumJugadores();
        int jugadoresLigaActuales = liga.getNumeroJugadores();

        equipo.setNumJugadores(jugadoresActuales + 1);
        liga.setNumeroJugadores(jugadoresLigaActuales + 1);

        //El repositorio de equipos guarda el equipo con el nuevo numero
        equipoRepository.save(equipo);
        ligaRepository.save(liga);

        //Crea inscripcion del jugador
        //Crea el objeto de la tabla intermedia y asociamos las relaciones
        JugadoresLiga nuevaInscripcion = new JugadoresLiga();
        nuevaInscripcion.setJugador(jugador);
        nuevaInscripcion.setEquipo(equipo);
        nuevaInscripcion.setLiga(liga);

        //Guardamos en la BBDD si no esta repetido
        if (jugadoresLigaRepository.existsByJugadorAndLiga(jugador, liga)) {
            throw new IllegalArgumentException("El jugador ya existe en la liga seleccionada");
        }

            return jugadoresLigaRepository.save(nuevaInscripcion);
    }

    public List<JugadoresLiga> buscarTodos() {
        return jugadoresLigaRepository.findAll();
    }
}
