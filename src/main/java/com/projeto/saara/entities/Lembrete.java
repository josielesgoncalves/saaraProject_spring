package com.projeto.saara.entities;

import com.projeto.saara.enums.LembreteTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "LEMBRETE")
public class Lembrete implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO")
    private LembreteTypeEnum tipo;

    @Column(name = "ASSUNTO")
    private String assunto;

    @Column(name = "TEXTO")
    private String texto;

    @Column(name = "DATA")
    private Calendar data;

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

    public LembreteTypeEnum getTipo() {
        return tipo;
    }

    public void setTipo(LembreteTypeEnum tipo) {
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

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
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