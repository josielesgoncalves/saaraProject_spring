package com.projeto.saara.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsuarioDTO {

    private String usuarioId;

    private String nome;

    private String email;

    private String cursoId;

    private String senha;

    private String confirmSenha;

    private List<LembreteDTO> lembretes;
}
