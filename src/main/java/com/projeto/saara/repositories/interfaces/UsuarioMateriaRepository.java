package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Usuario;
import com.projeto.saara.entities.UsuarioMateria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioMateriaRepository extends JpaRepository<UsuarioMateria, Long> {

    Optional<List<UsuarioMateria>> findUsuarioMateriaByUsuario(Usuario usuario);

    Optional<UsuarioMateria> findUsuarioMateriaById(Long id);
}
