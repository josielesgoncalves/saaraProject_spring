package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Curso;
import com.projeto.saara.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    Optional<Materia> getMateriaById(Long id);
}
