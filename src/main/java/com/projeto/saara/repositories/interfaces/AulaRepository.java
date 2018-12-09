package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    Optional<Aula> findAulaById(Long id);

}
