package com.projeto.saara.dto;

import com.projeto.saara.dto.output.LembreteDTO;
import com.projeto.saara.entities.Usuario;

import java.util.List;

public class UsuarioDTO {

    public UsuarioDTO(){}

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cursoId = usuario.getCurso().getId().toString();
    }

    private String usuarioId;

    private String nome;

    private String email;

    private String cursoId;

    private String senha;

    private String confirmSenha;

    private List<LembreteDTO> lembretes;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

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

    public String getConfirmSenha() {
        return confirmSenha;
    }

    public void setConfirmSenha(String confirmSenha) {
        this.confirmSenha = confirmSenha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<LembreteDTO> getLembretes() {
        return lembretes;
    }

    public void setLembretes(List<LembreteDTO> lembretes) {
        this.lembretes = lembretes;
    }
}
