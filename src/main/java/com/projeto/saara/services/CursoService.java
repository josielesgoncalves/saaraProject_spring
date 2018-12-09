package com.projeto.saara.services;

import com.projeto.saara.entities.*;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.exceptions.ParametroInvalidoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.repositories.interfaces.AulaRepository;
import com.projeto.saara.repositories.interfaces.MateriaCursoRepository;
import com.projeto.saara.repositories.interfaces.MateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    private final UsuarioRepository usuarioRepository;

    private final MateriaRepository materiaRepository;

    private final MateriaCursoRepository materiaCursoRepository;

    @Autowired
    public CursoService(UsuarioRepository usuarioRepository,
                        MateriaRepository materiaRepository,
                        MateriaCursoRepository materiaCursoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.materiaRepository = materiaRepository;
        this.materiaCursoRepository = materiaCursoRepository;
    }

    /**
     * @return o quanto do curso o usuario concluiu
     */
    public String getCursoConcluido(long usuarioId) {

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException("Usuario de id \"" + usuarioId + "\" não encontrado"));

        Curso curso = usuario.getCurso();
        List<MateriaCurso> materias = materiaCursoRepository.findAllByCurso(curso).orElseThrow(
                () ->
                new ObjetoNaoEncontradoException("As materias do curso \"" + curso.getNome() + "\" não foram encontradas"));

        List<UsuarioMateria> materiasConcluidas = new ArrayList<>();
        for (UsuarioMateria usuarioMateria : usuario.getMaterias()) {
            if (usuarioMateria.getStatus().equals(StatusEnum.CURSADA)) {
                materiasConcluidas.add(usuarioMateria);
            }
        }
        double percetualConcluido = (double) (materiasConcluidas.size() / materias.size());

        return (percetualConcluido * 100) + "%";
    }
}
