package com.kfabianp.springcloud.msvc.usuarios.repositories;

import com.kfabianp.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
