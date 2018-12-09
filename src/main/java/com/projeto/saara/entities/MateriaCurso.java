package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MATERIA_CURSO")
@Data
public class MateriaCurso {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Materia.class)
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Curso.class)
    private Curso curso;

    @Column(name = "SEMESTRE")
    private int semestre;
}
