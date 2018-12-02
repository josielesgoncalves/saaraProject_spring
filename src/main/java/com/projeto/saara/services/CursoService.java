package com.projeto.saara.services;

import com.projeto.saara.dto.output.AulaDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.enums.DiaEnum;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.AulaRepository;
import com.projeto.saara.repositories.interfaces.MateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    /**
     * @return o quanto do curso o usuario concluiu
     */
    public String getCursoConcluido(String usuarioId) throws
            ValidationException {

        Usuario usuario = usuarioRepository.findUsuarioById(ConverterHelper
                .convertStringToLong(usuarioId));

        Curso curso = usuario.getCurso();
        List<Materia> materias = materiaRepository.findAllByCursos(curso);

        List<UsuarioMateria> materiasConcluidas = new ArrayList<>();
        for (UsuarioMateria usuarioMateria : usuario.getMaterias()) {
            if (usuarioMateria.getStatus().equals(StatusEnum.CURSADA)) {
                materiasConcluidas.add(usuarioMateria);
            }
        }

        double percetualConcluido = (double) (materiasConcluidas.size() / materias.size());
        String resultado = (percetualConcluido * 100) + "%";
        return resultado;
    }
}
