package com.projeto.saara.services;

import com.projeto.saara.dto.output.SelectBoxDTO;
import com.projeto.saara.entities.Curso;
import com.projeto.saara.entities.Materia;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.CursoRepository;
import com.projeto.saara.repositories.interfaces.MateriaRepository;
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

    public List<SelectBoxDTO> getStatusMateria() {
        List<SelectBoxDTO> selectBoxList = new ArrayList<>();

        selectBoxList.add(new SelectBoxDTO((StatusEnum.CURSADA.name()), "Cursada"));
        selectBoxList.add(new SelectBoxDTO((StatusEnum.CURSANDO.name()), "Cursando"));
        selectBoxList.add(new SelectBoxDTO((StatusEnum.NAO_CURSADA.name()), "Não " +
                "Cursada"));

        return selectBoxList;
    }
}
