package com.projeto.saara.dto.input;

import com.projeto.saara.entities.Lembrete;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;

public class NewLembreteDTO {

    @NotBlank(message = "Campo obrigatório")
    private String tipo;

    @NotBlank(message = "Campo obrigatório")
    private String assunto;

    private String texto;

    private String data;

    private String materiaId;

    private String usuarioId;

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

    public Lembrete criarLembrete() throws ValidationException, ParseException {
        Lembrete lembrete = new Lembrete();
        if (this.getTipo() == null)
            throw new ValidationException();

        Long tipoId = ConverterHelper.convertStringToLong(this.getTipo());
        lembrete.setTipo(ConverterHelper.convertIdToLembreteTypeEnum(tipoId));

        if (this.assunto == null)
            throw new ValidationException();

        lembrete.setAssunto(this.assunto);
        lembrete.setData(ConverterHelper.convertStringToCalendar(this.data));
        lembrete.setTexto(this.getTexto());

        return lembrete;
    }
}
