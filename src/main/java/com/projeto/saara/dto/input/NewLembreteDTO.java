package com.projeto.saara.dto.input;

import com.projeto.saara.entities.Lembrete;
import com.projeto.saara.exceptions.ParametroInvalidoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewLembreteDTO implements Serializable {

    @NotBlank(message = "Campo obrigatório")
    private String tipo;

    @NotBlank(message = "Campo obrigatório")
    private String assunto;

    private String texto;

    private String data;

    private String materiaId;

    private String usuarioId;

    public Lembrete criarLembrete() {
        Lembrete lembrete = new Lembrete();

        if (this.getTipo() == null)
            throw new ParametroInvalidoException("Tipo nulo");

        Long tipoId = ConverterHelper.convertStringToLong(this.getTipo());
        lembrete.setTipo(tipoId);

        if (this.assunto == null)
            throw new ParametroInvalidoException("Assunto nulo");

        lembrete.setAssunto(this.assunto);
        lembrete.setData(this.data);
        lembrete.setTexto(this.getTexto());

        return lembrete;
    }
}
