package com.projeto.saara.repositories.interfaces;

import com.projeto.saara.entities.Lembrete;
import com.projeto.saara.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    List<Lembrete> findLembreteByUsuario(Usuario usuario);

    Lembrete findLembreteById(Long id);
}
