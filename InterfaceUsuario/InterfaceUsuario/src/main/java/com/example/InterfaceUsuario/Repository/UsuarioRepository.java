package com.example.InterfaceUsuario.Repository;

import com.example.InterfaceUsuario.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
