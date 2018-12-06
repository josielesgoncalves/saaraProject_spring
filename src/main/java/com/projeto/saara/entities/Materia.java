package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "MATERIA")
@Data
public class Materia implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Curso> cursos;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Aula> aulas;


}
