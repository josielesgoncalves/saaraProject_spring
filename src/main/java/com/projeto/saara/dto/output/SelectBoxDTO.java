package com.projeto.saara.dto.output;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectBoxDTO implements Serializable {

    private String id;

    private String descricao;

    public SelectBoxDTO(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
