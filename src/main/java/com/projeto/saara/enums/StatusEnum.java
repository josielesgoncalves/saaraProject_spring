package com.projeto.saara.enums;

public enum StatusEnum {
    CURSADA(1L, "Cursada"),
    CURSANDO(2L, "Cursando"),
    NAO_CURSADA(3L, "Não cursada");

    StatusEnum(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    private Long id;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
