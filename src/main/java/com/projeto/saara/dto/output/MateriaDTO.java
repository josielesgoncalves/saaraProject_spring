package com.projeto.saara.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MateriaDTO implements Serializable {

    private String materiaId;

    private String nome;
}
