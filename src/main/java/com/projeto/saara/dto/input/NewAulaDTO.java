package com.projeto.saara.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class NewAulaDTO implements Serializable {

     @NotBlank(message = "Insira o nome do professor")
     private String professor;

     @NotBlank(message = "O dia da aula deve ser inserido")
     private String diaSemanaId;

     private String horario;

     private String local;

     private String materiaId;

     private String usuarioId;
}
