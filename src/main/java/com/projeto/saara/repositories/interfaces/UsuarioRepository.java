package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByEmail(String email);

    Usuario findUsuarioById(Long id);



}
