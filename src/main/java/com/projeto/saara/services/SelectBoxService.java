package com.projeto.saara.services;

import com.projeto.saara.dto.output.SelectBoxDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.enums.LembreteTypeEnum;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.repositories.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectBoxService {

    private final CursoRepository cursoRepository;

    private final MateriaCursoRepository materiaCursoRepository;

    private final UsuarioRepository usuarioRepository;

    private final LembreteRepository lembreteRepository;

    @Autowired
    public SelectBoxService(CursoRepository cursoRepository,
                            UsuarioRepository usuarioRepository,
                            LembreteRepository lembreteRepository,
                            MateriaCursoRepository materiaCursoRepository) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
        this.lembreteRepository = lembreteRepository;
        this.materiaCursoRepository = materiaCursoRepository;
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

        List<MateriaCurso> materias = materiaCursoRepository.findAllByCurso(curso)
                .orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "As materias do curso \"" + curso.getNome() + "\" não foram encontradas"));

        for (MateriaCurso materiaCurso : materias)
            selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                    (materiaCurso.getMateria().getId()), materiaCurso.getMateria().getNome()));

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
