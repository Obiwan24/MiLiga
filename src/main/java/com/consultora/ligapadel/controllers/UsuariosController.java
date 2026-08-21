package com.consultora.ligapadel.controllers;

import com.consultora.ligapadel.models.Usuario;
import com.consultora.ligapadel.repositories.UsuarioRepository;
import com.consultora.ligapadel.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired // Referencia al repositorio del jugador
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    //Metodo para crear el usuario
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }
    //Método para buscar usuarios
    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable String dni){
        return usuarioRepository.findById(dni).orElse(null);
    }

    @GetMapping
    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    //Método para borrar usuarios
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable("id") String dni){
        usuarioService.borrarUsuarioPorDni(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Formatear tabla de jugadores
    @DeleteMapping("/limpiar-tabla")
    public ResponseEntity<Void> formatearTabla(){
        usuarioService.formatearUsuarios();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Formatear jugadores de una Liga
    @DeleteMapping("/BorrarJugadoresPorLiga")
    public ResponseEntity<Void> formatearUsuarioPorLiga(@RequestParam("LigaId") Long idLiga){
        usuarioService.formatearUsuarioPorLiga(idLiga);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Modificar jugador
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuario(
            @PathVariable("id") String dni,
            @RequestBody Usuario datosNuevos) {
        //Llama al método del servicio para modificar datos
        Usuario usuarioActualizado = usuarioService.modificarUsuario(dni, datosNuevos);

        //Devuelve el usuario ya modificado
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }
}
