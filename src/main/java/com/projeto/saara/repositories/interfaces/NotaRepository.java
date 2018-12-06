package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Nota;
import com.projeto.saara.entities.UsuarioMateria;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    Optional<List<Nota>> findNotasByUsuarioMateria(UsuarioMateria usuarioMateria);

    Optional<Nota> findNotaByUsuarioMateriaAndId(UsuarioMateria usuarioMateria, Long id);

}
