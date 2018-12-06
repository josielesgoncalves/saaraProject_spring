package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findCursoById(Long id);

}
