package com.projeto.saara.enums;

public enum LembreteTypeEnum {
    PROVA(1L, "Prova"),
    TRABALHO(2L, "Trabalho"),
    OUTROS(3L, "Outros");

    LembreteTypeEnum(Long id, String descricao) {
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
