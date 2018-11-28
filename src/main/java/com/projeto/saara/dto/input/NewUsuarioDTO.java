package com.projeto.saara.dto.input;

import com.projeto.saara.entities.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/** Classe usada para criar um novo usuario */
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmSenha() {
        return confirmSenha;
    }

    public void setConfirmSenha(String confirmSenha) {
        this.confirmSenha = confirmSenha;
    }

    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }

    public Usuario criarNovoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setSenha(this.senha);
        usuario.setEmail(this.email);

        return usuario;

    }
}
