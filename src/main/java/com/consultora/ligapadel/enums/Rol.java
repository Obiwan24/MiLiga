package com.consultora.ligapadel.enums;

import java.util.Set;

public enum Rol {
    ADMIN(Set.of(
            Permiso.ACEPTAR_INSCRIPCION_EQUIPO,
            Permiso.ACEPTAR_INSCRIPCION_JUGADOR,
            Permiso.CAMBIAR_NOMBRE_LIGA,
            Permiso.BORRAR_EQUIPO,
            Permiso.BORRAR_LIGA,
            Permiso.BORRAR_INSCRIPCION,
            Permiso.MODIFICAR_PUNTUACION,
            Permiso.MODIFICAR_FECHA,
            Permiso.MODIFICAR_PENALIZACION,
            Permiso.MODIFICAR_MARCADOR,
            Permiso.RESOLVER_RECLAMACION,
            Permiso.APROBAR_ROL,
            Permiso.CAMBIAR_CAPITAN
    )),

    CAPITAN(Set.of(
            Permiso.CREAR_EQUIPO,
            Permiso.BORRAR_EQUIPO_PROPIO,
            Permiso.ACEPTAR_INSCRIPCION_JUG_EQUIPO,
            Permiso.BORRAR_INSCRIPCION_JUG_EQUIPO,
            Permiso.MODIFICAR_FECHA,
            Permiso.MODIFICAR_LUGAR,
            Permiso.INDICAR_MARCADOR_LOCAL,
            Permiso.APROBAR_MARCADOR_VISITANTE,
            Permiso.RECLAMAR_MARCADOR,
            Permiso.ANUNCIAR_CONVOCATORIA,
            Permiso.MODIFICAR_CONVOCATORIA,
            Permiso.MODIFICAR_MARCADOR_LOCAL,
            Permiso.SOLICITAR_CAMBIO_CAPITAN
    )),

    JUGADOR(Set.of(
            Permiso.MODIFICAR_INFO_JUGADOR,
            Permiso.INSCRIBIERSE_EQUIPO,
            Permiso.APUNTARSE_PARTIDO,
            Permiso.DESAPUNTARSE_PARTIDO,
            Permiso.BORRAR_USUARIO_PROPIO,
            Permiso.BORRAR_INSCRIPCION_PROPIO
    ));

    private final Set<Permiso> permisos;

    Rol(Set<Permiso> permisos) { this.permisos = permisos; }

    public boolean tienePermiso(Permiso permiso) { return permisos.contains(permiso);}

    public Set<Permiso> getPermisos() { return permisos; }
}
