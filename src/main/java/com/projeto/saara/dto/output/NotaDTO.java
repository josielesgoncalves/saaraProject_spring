package com.projeto.saara.dto.output;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotaDTO implements Serializable {

    private String notaId;

    private String valor;

    private String pesoNota;

    private String tipo;

    private String usuarioMateriaId;
}
