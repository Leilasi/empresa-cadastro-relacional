package com.cadastro.relacional.empresa.repository;

import com.cadastro.relacional.empresa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
