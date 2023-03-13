package com.kfabianp.springcloud.msvc.usuarios.services;

import com.kfabianp.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(Long idUsuario);
    Usuario registrarUsuario(Usuario usuario);
    void eliminarUsuario(Long idUsuario);
}
