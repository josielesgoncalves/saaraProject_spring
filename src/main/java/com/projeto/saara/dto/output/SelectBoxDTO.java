package com.projeto.saara.dto.output;

import java.io.Serializable;

public class SelectBoxDTO implements Serializable {

    private String id;

    private String descricao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SelectBoxDTO(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public SelectBoxDTO() {
    }
}
