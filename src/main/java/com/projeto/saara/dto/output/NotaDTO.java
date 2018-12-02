package com.projeto.saara.dto.output;

public class NotaDTO {

    private String notaId;

    private String valor;

    private String pesoNota;

    private String tipo;

    private String usuarioMateriaId;

    public String getNotaId() {
        return notaId;
    }

    public void setNotaId(String notaId) {
        this.notaId = notaId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPesoNota() {
        return pesoNota;
    }

    public void setPesoNota(String pesoNota) {
        this.pesoNota = pesoNota;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuarioMateriaId() {
        return usuarioMateriaId;
    }

    public void setUsuarioMateriaId(String usuarioMateriaId) {
        this.usuarioMateriaId = usuarioMateriaId;
    }
}
