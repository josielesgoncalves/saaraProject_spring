package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findCursoById(Long id);

}
