package com.projeto.saara.services;

import com.projeto.saara.dto.output.SelectBoxDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.enums.LembreteTypeEnum;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.exceptions.ValidationException;
import com.projeto.saara.repositories.interfaces.CursoRepository;
import com.projeto.saara.repositories.interfaces.LembreteRepository;
import com.projeto.saara.repositories.interfaces.MateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SelectBoxService {

    private final CursoRepository cursoRepository;

    private final MateriaRepository materiaRepository;

    private final UsuarioRepository usuarioRepository;

    private final LembreteRepository lembreteRepository;

    @Autowired
    public SelectBoxService(CursoRepository cursoRepository, MateriaRepository materiaRepository, UsuarioRepository usuarioRepository, LembreteRepository lembreteRepository) {
        this.cursoRepository = cursoRepository;
        this.materiaRepository = materiaRepository;
        this.usuarioRepository = usuarioRepository;
        this.lembreteRepository = lembreteRepository;
    }

    public List<SelectBoxDTO> getCursos() {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        List<Curso> cursos = cursoRepository.findAll();

        if (cursos == null || cursos.isEmpty())
            throw new ObjetoNaoEncontradoException("nenhum curso foi encontrado");

        for (Curso curso : cursos)
            selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString(curso.getId()), curso.getNome()));

        return selectBoxList;
    }

    public List<SelectBoxDTO> getMaterias(long cursoId) {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        Curso curso = cursoRepository.findCursoById(cursoId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "O curso de id \"" + cursoId + "\" não foi encontrado"));

        List<Materia> materias = materiaRepository.findAllByCursos(curso).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "As materias do curso \"" + curso.getNome() + "\" não foram encontradas"));

        for (Materia materia : materias)
            selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString(materia.getId()), materia.getNome()));

        return selectBoxList;
    }

    public List<SelectBoxDTO> getStatusMateria() {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        selectBoxList.add(new SelectBoxDTO(
                ConverterHelper.convertLongToString(StatusEnum.CURSADA.getId()),
                StatusEnum.CURSADA.getDescricao()
            )
        );
        selectBoxList.add(new SelectBoxDTO(
                ConverterHelper.convertLongToString(StatusEnum.CURSANDO.getId()),
                StatusEnum.CURSANDO.getDescricao()
         )
        );
        selectBoxList.add(new SelectBoxDTO(
                ConverterHelper.convertLongToString(StatusEnum.NAO_CURSADA.getId()),
                StatusEnum.NAO_CURSADA.getDescricao()
            )
        );

        return selectBoxList;
    }

    public List<SelectBoxDTO> getLembretes(long usuarioId) {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "O usuario de id \"" + usuarioId + "\" não foi encontrado"));

        List<Lembrete> lembretes = lembreteRepository.findLembreteByUsuario(usuario).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "Os lembretes do usuario \"" + usuario.getNome() + "\" não foram encontrados"));

        for (Lembrete lembrete : lembretes) {
            selectBoxList.add(new SelectBoxDTO(lembrete.getId().toString(),
                    lembrete.getAssunto()));
        }
        return selectBoxList;
    }

    public List<SelectBoxDTO> getLembreteType() {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        selectBoxList.add(new SelectBoxDTO(
                ConverterHelper.convertLongToString(LembreteTypeEnum.PROVA.getId()),
                LembreteTypeEnum.PROVA.getDescricao()
            )
        );
        selectBoxList.add(new SelectBoxDTO(
                ConverterHelper.convertLongToString(LembreteTypeEnum.TRABALHO.getId()),
                LembreteTypeEnum.TRABALHO.getDescricao()
            )
        );
        selectBoxList.add(new SelectBoxDTO(
                ConverterHelper.convertLongToString(LembreteTypeEnum.OUTROS.getId()),
                LembreteTypeEnum.OUTROS.getDescricao()
            )
        );

        return selectBoxList;
    }

    public List<SelectBoxDTO> getMateriasUsuario(long usuarioId) {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "O usuario de id \"" + usuarioId + "\" não foi encontrado"));

        List<UsuarioMateria> usuarioMaterias = usuario.getMaterias();
        List<Materia> materias = new ArrayList<>();

        for (UsuarioMateria uMateria : usuarioMaterias) {
            if (uMateria.getStatus().equals(StatusEnum.CURSANDO)) {
                materias.add(uMateria.getMateria());
            }
        }

        for (Materia materia : materias) {
            selectBoxList.add(new SelectBoxDTO(
                    ConverterHelper.convertLongToString(materia.getId()), materia.getNome()
                )
            );
        }
        return selectBoxList;
    }
}
