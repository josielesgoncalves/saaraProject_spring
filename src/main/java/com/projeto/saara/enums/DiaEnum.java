package com.projeto.saara.enums;

public enum DiaEnum {
    DOMINGO(1L, "Domingo"),
    SEGUNDA(2L, "Segunda"),
    TERCA(3L, "Terça"),
    QUARTA(4L, "Quarta"),
    QUINTA(5L, "Quinta"),
    SEXTA(6L, "Sexta"),
    SABADO(7L, "Sabado");

    DiaEnum(Long id, String descricao) {
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
