package com.kfabianp.springcloud.msvc.usuarios.controllers;

import com.kfabianp.springcloud.msvc.usuarios.models.entity.Usuario;
import com.kfabianp.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable(name="id") Long idUsuario){
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(usuario.isPresent()){
            return ResponseEntity.ok().body( usuario.get() );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body( usuarioService.registrarUsuario(usuario) );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable(name="id") Long idUsuario) {
        Optional<Usuario> usuarioBuscadoEnDB = usuarioService.obtenerUsuarioPorId(idUsuario);
        if ( usuarioBuscadoEnDB.isPresent() ){
            Usuario usuarioActualizado = usuarioBuscadoEnDB.get();
            usuarioActualizado.setNombre(usuario.getNombre());
            usuarioActualizado.setEmail(usuario.getEmail());
            usuarioActualizado.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body( usuarioService.registrarUsuario(usuarioActualizado) );
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable(name="id") Long idUsuario){
        Optional<Usuario> usuarioBuscadoEnDB = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(usuarioBuscadoEnDB.isPresent()){
            usuarioService.eliminarUsuario(idUsuario);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
