package com.projeto.saara.dto.input;

import javax.validation.constraints.NotBlank;

public class NewNotaDTO {

    @NotBlank(message = "Campo obrigatório")
    private String valor;

    @NotBlank(message = "Campo obrigatório")
    private String pesoNota;

    @NotBlank(message = "Campo obrigatório")
    private String tipo;

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
}
