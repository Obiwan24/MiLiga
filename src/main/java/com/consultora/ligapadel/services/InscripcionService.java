package com.consultora.ligapadel.services;

import com.consultora.ligapadel.models.*;
import com.consultora.ligapadel.repositories.*;
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
    private InscripcionRepository inscripcionRepository;

    @Transactional
    public Inscripcion inscribirJugador(Long jugadorId, Long equipoId, Long ligaId) {
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
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setJugador(jugador);
        nuevaInscripcion.setEquipo(equipo);
        nuevaInscripcion.setLiga(liga);

        //Guardamos en la BBDD si no esta repetido
        if (inscripcionRepository.existsByJugadorAndLiga(jugador, liga)) {
            throw new IllegalArgumentException("El jugador ya existe en la liga seleccionada");
        }

            return inscripcionRepository.save(nuevaInscripcion);
    }

    @Transactional
    public Inscripcion cambiarDeEquipo(Long jugadorId, Long ligaId, Long nuevoEquipoId){
        //Busca el jugador y en la liga
        Inscripcion inscripcionActual = inscripcionRepository.findByJugadorIdJugadorAndLigaIdLiga(jugadorId, ligaId).orElse(null);

        //Busca el nuevo equipo al que se quiere apuntar
        Equipo nuevoEquipo = equipoRepository.findById(nuevoEquipoId).orElse(null);

        if (inscripcionActual != null && nuevoEquipo != null) {
            //Conseguimos equipo antiguo
            Equipo equipoAntiguo = inscripcionActual.getEquipo();
            //Resta 1 al equipo antiguo
            equipoAntiguo.setNumJugadores(equipoAntiguo.getNumJugadores() - 1);
            equipoRepository.save(equipoAntiguo);

            //Suma 1 al equipo nuevo
            nuevoEquipo.setNumJugadores(nuevoEquipo.getNumJugadores() + 1);
            equipoRepository.save(nuevoEquipo);

            //Actualiza el equipo en la inscripcion existente
            inscripcionActual.setEquipo(nuevoEquipo);

            return inscripcionRepository.save(inscripcionActual);
        }
        return null;
    }

    public List<Inscripcion> buscarTodos() {
        return inscripcionRepository.findAll();
    }

    //Eliminar inscripcion
    @Transactional
    public void eliminarInscripcion(Long idInscripcion){
        //Valida si existe la inscripcion
        if (!inscripcionRepository.existsById(idInscripcion)) {
            throw new RuntimeException("La inscripción con id " + idInscripcion + " no existe.");
        }
        inscripcionRepository.deleteById(idInscripcion);
    }
}
