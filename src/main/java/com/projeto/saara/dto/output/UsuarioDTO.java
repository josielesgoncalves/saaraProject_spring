package com.projeto.saara.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    private String usuarioId;

    private String nome;

    private String email;

    private String cursoId;//TODO curso

    private String senha;

    private List<LembreteDTO> lembretes;
}
