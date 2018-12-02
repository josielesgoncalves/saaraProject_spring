package com.projeto.saara.enums;

public enum NotaTypeEnum {

    NOTA_P1(1L, "Nota P1"),
    NOTA_P2(2L, "Nota P2"),
    NOTA_P3(3L, "Nota P3"),
    NOTA_T1(4L, "Nota T1"),
    NOTA_T2(5L, "Nota T2"),
    NOTA_T3(6L, "Nota T3"),
    OUTROS(7L, "Outros");

    NotaTypeEnum(Long id, String descricao) {
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
