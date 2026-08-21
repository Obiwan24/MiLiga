package com.consultora.ligapadel.services;

import com.consultora.ligapadel.models.Usuario;
import com.consultora.ligapadel.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Método para crear usuario
    @Transactional
    public Usuario crearUsuario(Usuario nuevoUsuario) {

        //Guarda el usuario nuevo
        return usuarioRepository.save(nuevoUsuario);
    }

    //Método para borra un usuario
    @Transactional
    public void borrarUsuarioPorDni(String dni){
        //Valida si existe el usuario en la BBDD
        if (!usuarioRepository.existsById(dni)){
            throw new RuntimeException("El usuario con id: " + dni + " no existe.");
        }
        //Se borra el usuario de la BBDD
        usuarioRepository.deleteById(dni);
    }

    //Método para formatear la tabla de usuarios
    @Transactional
    public void formatearUsuarios(){
        usuarioRepository.deleteAll();
    }

    //Método para borrar usuarios de una liga en concreto
    @Transactional
    public void formatearUsuarioPorLiga(Long idLiga){
        //Buscamos los usuarios de la liga
        List<Usuario> usuariosLiga = usuarioRepository.findByInscripcionesLigaIdLiga(idLiga);
        //Si hay usuarios los borra
        if (!usuariosLiga.isEmpty()) {
            usuarioRepository.deleteAll(usuariosLiga);
        }
    }

    @Transactional
    public Usuario modificarUsuario(String dni, Usuario datosNuevos) {
        //Buscamos al usuario en la base de datos
        Usuario usuarioRegistrado = usuarioRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("El usuario con id " + dni +" no está registrado."));

        //Actualiza campos permitidos
        usuarioRegistrado.setNombre(datosNuevos.getNombre());
        usuarioRegistrado.setDni(datosNuevos.getDni());
        usuarioRegistrado.setPosicion(datosNuevos.getPosicion());

        return usuarioRepository.save(datosNuevos);
    }
}
