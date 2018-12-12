package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MATERIA_CURSO")
@Data
public class MateriaCurso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Materia.class, cascade= CascadeType.ALL)
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Curso.class, cascade= CascadeType.ALL)
    private Curso curso;

    @Column(name = "SEMESTRE")
    private int semestre;
}
