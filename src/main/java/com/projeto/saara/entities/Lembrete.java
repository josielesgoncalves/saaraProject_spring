package com.projeto.saara.entities;

import com.projeto.saara.enums.TipoLembrete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "LEMBRETE")
public class Lembrete implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO")
    private TipoLembrete tipo;

    @Column(name = "ASSUNTO")
    private String assunto;

    @Column(name = "TEXTO")
    private String texto;

    @Column(name = "DATA")
    private Date data;

    @ManyToOne
    private Materia materia;

    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLembrete getTipo() {
        return tipo;
    }

    public void setTipo(TipoLembrete tipo) {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}