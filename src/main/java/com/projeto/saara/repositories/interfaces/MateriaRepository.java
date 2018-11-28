package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Curso;
import com.projeto.saara.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    List<Materia> findAllByCursos(Curso curso);

    Materia getMateriaById(Long id);
}
