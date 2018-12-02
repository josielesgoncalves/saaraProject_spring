package com.projeto.saara.dto.output;

public class LembreteDTO {

    private String lembreteId;

    private String tipo;

    private String assunto;

    private String texto;

    private String data;

    private String materiaId;

    private String usuarioId;

    public String getLembreteId() {
        return lembreteId;
    }

    public void setLembreteId(String lembreteId) {
        this.lembreteId = lembreteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(String materiaId) {
        this.materiaId = materiaId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
