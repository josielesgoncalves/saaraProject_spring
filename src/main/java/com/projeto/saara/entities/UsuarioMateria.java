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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Nota> notas;

    @Column(name = "STATUS")
    private StatusEnum status;

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

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNota(List<Nota> notas) {
        this.notas = notas;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
