package com.projeto.saara.entities;

import com.projeto.saara.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USUARIO_MATERIA")
public class UsuarioMateria implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "MATERIA_ID")
    private Materia materia;

    @Column(name = "USUARIO_ID")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Nota> notas;

    @Column(name = "STATUS")
    private StatusEnum status;

    @Column(name = "MEDIA")
    private double media;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
