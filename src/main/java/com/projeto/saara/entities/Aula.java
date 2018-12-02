package com.projeto.saara.entities;

import com.projeto.saara.enums.DiaEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Entity
@Table(name="AULA")
public class Aula implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROFESSOR")
    private String professor;

    @Column(name = "DATA_HORARIO")
    private Calendar dataHorario;

    @Column(name = "LOCAL")
    private String local;

    @Column(name = "DIA")
    private DiaEnum dia;

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

    public Calendar getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(Calendar dataHorario) {
        this.dataHorario = dataHorario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public DiaEnum getDia() {
        return dia;
    }

    public void setDia(DiaEnum dia) {
        this.dia = dia;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}
