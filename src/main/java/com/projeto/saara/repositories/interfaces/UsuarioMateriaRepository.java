package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Usuario;
import com.projeto.saara.entities.UsuarioMateria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioMateriaRepository extends JpaRepository<UsuarioMateria, Long> {

    List<UsuarioMateria> findUsuarioMateriaByUsuario(Usuario usuario);

    UsuarioMateria findUsuarioMateriaById(Long id);
}
