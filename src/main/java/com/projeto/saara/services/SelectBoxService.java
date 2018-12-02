package com.projeto.saara.services;

import com.projeto.saara.dto.output.SelectBoxDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.enums.LembreteTypeEnum;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.CursoRepository;
import com.projeto.saara.repositories.interfaces.LembreteRepository;
import com.projeto.saara.repositories.interfaces.MateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectBoxService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LembreteRepository lembreteRepository;

    public List<SelectBoxDTO> getCursos() throws ValidationException {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        List<Curso> cursos = cursoRepository.findAll();
        if (cursos == null) {
            throw new ValidationException();
        }

        for (Curso curso : cursos) {
            selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                    (curso.getId()), curso.getNome()));
        }
        return selectBoxList;
    }

    public List<SelectBoxDTO> getMaterias(String cursoId) throws ValidationException {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        Curso curso = cursoRepository.findCursoById(ConverterHelper.convertStringToLong
                (cursoId));

        if (curso == null) {
            throw new ValidationException();
        }

        List<Materia> materias = materiaRepository.findAllByCursos(curso);

        if (materias == null) {
            throw new ValidationException();
        }

        for (Materia materia : materias) {
            selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                    (materia.getId()), materia.getNome()));
        }
        return selectBoxList;
    }

    public List<SelectBoxDTO> getStatusMateria() throws ValidationException {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                (StatusEnum.CURSADA.getId()), StatusEnum.CURSADA.getDescricao()));
        selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                (StatusEnum.CURSANDO.getId()), StatusEnum.CURSANDO.getDescricao()));
        selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                (StatusEnum.NAO_CURSADA.getId()), StatusEnum.NAO_CURSADA.getDescricao()));

        return selectBoxList;
    }

    public List<SelectBoxDTO> getLembretes(String usuarioId) throws ValidationException {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(ConverterHelper
                .convertStringToLong(usuarioId));

        List<Lembrete> lembretes = lembreteRepository.findLembreteByUsuario(usuario);

        if (lembretes == null)
            throw new ValidationException();

        for (Lembrete lembrete : lembretes) {
            selectBoxList.add(new SelectBoxDTO(lembrete.getId().toString(),
                    lembrete.getAssunto()));
        }
        return selectBoxList;
    }

    public List<SelectBoxDTO> getLembreteType() throws ValidationException {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                (LembreteTypeEnum.PROVA.getId()), LembreteTypeEnum.PROVA.getDescricao()));
        selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                (LembreteTypeEnum.TRABALHO.getId()), LembreteTypeEnum.TRABALHO.getDescricao()));
        selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                (LembreteTypeEnum.OUTROS.getId()), LembreteTypeEnum.OUTROS.getDescricao()));

        return selectBoxList;
    }

    public List<SelectBoxDTO> getMateriasUsuario(String usuarioId) throws ValidationException {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(ConverterHelper.convertStringToLong(usuarioId));
        List<UsuarioMateria> usuarioMaterias = usuario.getMaterias();
        List<Materia> materias = new ArrayList<>();

        for (UsuarioMateria uMateria : usuarioMaterias) {
            if (uMateria.getStatus().equals(StatusEnum.CURSANDO)) {
                materias.add(uMateria.getMateria());
            }
        }

        for (Materia materia : materias) {
            selectBoxList.add(new SelectBoxDTO(ConverterHelper.convertLongToString
                    (materia.getId()), materia.getNome()));
        }
        return selectBoxList;
    }
}
