package com.projeto.saara.services;

import com.projeto.saara.dto.CursoDTO;
import com.projeto.saara.entities.Curso;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<CursoDTO> getCursos() throws ValidationException {
        List<CursoDTO> cursosDTO = new ArrayList<>();

        List<Curso> cursos = cursoRepository.findAll();

        for (Curso curso: cursos) {
            CursoDTO cursoDTO = new CursoDTO(curso);
            cursosDTO.add(cursoDTO);
        }

        return cursosDTO;
    }

}
