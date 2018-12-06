package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CURSO")
@Data
public class Curso implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Materia.class)
    private List<Materia> materias;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Usuario.class)
    private List<Usuario> usuarios;
}
