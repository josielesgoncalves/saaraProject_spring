package com.projeto.saara.dto.output;

import lombok.Data;

import java.io.Serializable;

@Data
public class LembreteDTO implements Serializable {

    private String lembreteId;

    private String tipo;

    private String assunto;

    private String texto;

    private String data;

    private String materiaId;

    private String usuarioId;
}
