package com.projeto.saara.dto.input;

import com.projeto.saara.entities.Usuario;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/** Classe usada para criar um novo usuario */
@Data
public class NewUsuarioDTO implements Serializable {

    @NotBlank(message = "Preencimento obrigatorio")
    private String nome;

    @Email(message = "Formato de email invalido")
    @NotBlank(message = "Preencimento obrigatorio")
    private String email;

    @NotBlank(message = "Preencimento obrigatorio")
    private String cursoId;

    @NotBlank(message = "Preencimento obrigatorio")
    private String senha;

    @NotBlank(message = "Preencimento obrigatorio")
    private String confirmSenha;

    private List<String> materias;

    public Usuario criarNovoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setSenha(this.senha);
        usuario.setEmail(this.email);

        return usuario;

    }
}
