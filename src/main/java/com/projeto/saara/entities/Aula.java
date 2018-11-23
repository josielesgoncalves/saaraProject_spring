package com.projeto.saara.entities;

import com.projeto.saara.enums.Dia;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="AULA")
public class Aula implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROFESSOR")
    private String professor;

    @Column(name = "HORARIO")
    private Date horarios;

    @Column(name = "LOCAL")
    private String local;

    @Column(name = "DIA")
    private Dia dias;

    @ManyToOne(fetch = FetchType.LAZY)
    private Materia materia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Date getHorarios() {
        return horarios;
    }

    public void setHorarios(Date horarios) {
        this.horarios = horarios;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Dia getDias() {
        return dias;
    }

    public void setDias(Dia dias) {
        this.dias = dias;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}
