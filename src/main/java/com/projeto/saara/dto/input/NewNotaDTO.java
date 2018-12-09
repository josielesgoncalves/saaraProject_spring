package com.projeto.saara.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class NewNotaDTO implements Serializable {

    @NotBlank(message = "Campo obrigatório")
    private String valor;

    @NotBlank(message = "Campo obrigatório")
    private String pesoNota;

    @NotBlank(message = "Campo obrigatório")
    private String tipo;

}
