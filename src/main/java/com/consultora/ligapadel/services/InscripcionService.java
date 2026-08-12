package com.consultora.ligapadel.services;

import com.consultora.ligapadel.enums.Rol;
import com.consultora.ligapadel.models.*;
import com.consultora.ligapadel.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Inscripcion inscribirJugador(Long jugadorId, Long equipoId, Long ligaId, Rol rolUsuario) {
        //Valida que el jugador exista en la BBDD
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new RuntimeException("Error: El jugador con ID " + jugadorId + " no existe."));

        //Valida que la liga existe
        Liga liga = ligaRepository.findById(ligaId)
                .orElseThrow(() -> new RuntimeException("Error: La liga con ID " + ligaId + " no existe."));

        // Declara el equipo
        Equipo equipo = null;
        // Solo permite inscripcion en equipo si no tiene rol de ADMIN
        if (rolUsuario != Rol.ADMIN){
            //Valida el equipo
            equipo = equipoRepository.findById(equipoId)
                    .orElseThrow(() -> new RuntimeException("Error: El equipo con ID " + equipoId + " no existe."));
        }

        //Ejecuta la validacion
        validarRolEnLiga(jugador, liga, equipo, rolUsuario);

        //Comprobación de duplicados
        if (inscripcionRepository.existsByJugadorAndLiga(jugador, liga, rolUsuario)) {
            throw new IllegalArgumentException("El jugador ya existe en la liga seleccionada");
        }

        if (equipo != null) {
            //Modifica contador de numero de jugadores y equipos
            equipo.incrementarJugadoresEquipo();
            liga.incrementarJugadoresLiga();
        }

        //El repositorio de equipos guarda el equipo con el nuevo numero
        if (equipo != null) {
            equipoRepository.save(equipo);
        }

        ligaRepository.save(liga);

        //Crea inscripcion del jugador
        //Crea el objeto de la tabla intermedia y asociamos las relaciones
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setJugador(jugador);
        nuevaInscripcion.setEquipo(equipo);
        nuevaInscripcion.setLiga(liga);
        nuevaInscripcion.setRolUsuario(rolUsuario);

        //Guardamos en la BBDD si no esta repetido


            return inscripcionRepository.save(nuevaInscripcion);
    }

    @Transactional
    public Inscripcion cambiarDeEquipo(Long jugadorId, Long ligaId, Long nuevoEquipoId){
        //Busca el jugador y en la liga
        List<Inscripcion> inscripciones = inscripcionRepository.findByJugadorIdJugadorAndLigaIdLiga(jugadorId, ligaId);
        Inscripcion inscripcionActual = inscripciones.stream()
                .filter(i -> i.getEquipo() != null)
                .findFirst()
                .orElse(null);


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

    public Inscripcion buscarInscripcion(Long idInscripcion) {
        return inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("La inscripcion " + idInscripcion + " no existe."));
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

    //Modificar inscripcion
    @Transactional
    public Inscripcion modificarInscripcion(Long idInscripcion, Inscripcion nuevosDatos) {
        //Busca inscripcion en la BBDD
        Inscripcion inscripcionRegistrada = inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("La inscripción con id " + idInscripcion + " no existe."));

        //Actualiza campos permitidos
        inscripcionRegistrada.setEquipo(nuevosDatos.getEquipo());
        inscripcionRegistrada.setLiga(nuevosDatos.getLiga());
        inscripcionRegistrada.setJugador(nuevosDatos.getJugador());

        //Guarda nuevos datos
        return inscripcionRepository.save(inscripcionRegistrada);
    }

    //Método para validar el rol
    private boolean validarRolEnLiga(Jugador jugador, Liga liga,Equipo equipo, Rol nuevoRol) {
        if(nuevoRol == Rol.CAPITAN) {
            if (equipo == null) {
                throw new IllegalArgumentException("Para ser capitan debes indicar un equipo.");
            }
            boolean equipoYaTieneCapitan = inscripcionRepository.existsByEquipoIdEquipoAndRolUsuario(
                    equipo.getIdEquipo(), Rol.CAPITAN);
            if (equipoYaTieneCapitan) {
                throw new IllegalArgumentException("El equipo " + equipo.getNombreEquipo() + " ya tiene capitán.");
            }
        }
        //Obtiene inscripciones previas del jugador en la liga
        List<Inscripcion> inscripcionesEnLiga = inscripcionRepository.findByJugadorIdJugadorAndLigaIdLiga(
                jugador.getIdJugador(), liga.getIdLiga());


        //Regla de incompatibilidad de roles
        for (Inscripcion inscripcionExistentes : inscripcionesEnLiga) {
            Rol rolExistente = inscripcionExistentes.getRolUsuario();

            //Intenta ser ADMIN pero ya hay un ADMIN
            if (nuevoRol == Rol.ADMIN && rolExistente == Rol.ADMIN) {
                    throw new IllegalArgumentException("El usuario ya es Administrador de esta liga.");
            }

            //Intenta participar como jugador/capitan pero ya forma parte de otro equipo.
            if ((nuevoRol == Rol.CAPITAN || nuevoRol == Rol.JUGADOR)
            && (rolExistente == Rol.JUGADOR || rolExistente == Rol.CAPITAN)){
                    throw new IllegalArgumentException("El usuario ya participa en un equipo de esta liga."
                    + " Para cambiar de rol, solicítelo al Administrador de la Liga.");
                }
            }
        return true;
    }
}
