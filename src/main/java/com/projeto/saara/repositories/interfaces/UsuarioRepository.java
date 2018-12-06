package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByEmail(String email);

    Optional<Usuario> findUsuarioById(Long id);
}
