package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Lembrete;
import com.projeto.saara.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    Optional<List<Lembrete>> findLembreteByUsuario(Usuario usuario);

    Optional<Lembrete> findLembreteById(Long id);
}
