package com.projeto.saara.dto.output;

import lombok.Data;

import java.io.Serializable;

@Data
public class AulaDTO implements Serializable {

    private String professor;

    private String local;

    private String dia;

    private String horario;

    private String nomeMateria;

}
