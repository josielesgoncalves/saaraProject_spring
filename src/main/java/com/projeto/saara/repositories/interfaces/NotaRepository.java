package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Materia;
import com.projeto.saara.entities.Nota;
import com.projeto.saara.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findAllByMateriaAndUsuario(Materia materia, Usuario usuario);

    List<Nota> findAllByUsuario(Usuario usuario);
}
