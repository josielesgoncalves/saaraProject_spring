package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Curso;
import com.projeto.saara.entities.MateriaCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MateriaCursoRepository extends JpaRepository<MateriaCurso, Long> {

    Optional<List<MateriaCurso>> findAllByCurso(Curso curso);
}
