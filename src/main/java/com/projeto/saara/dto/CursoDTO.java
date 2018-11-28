package com.projeto.saara.dto;

import com.projeto.saara.entities.Curso;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;

public class CursoDTO {

    private String cursoId;

    private String nome;

    public CursoDTO(){}

    public CursoDTO(Curso curso) throws ValidationException {
        this.cursoId = ConverterHelper.convertLongToString(curso.getId());
        this.nome = curso.getNome();
    }


}
