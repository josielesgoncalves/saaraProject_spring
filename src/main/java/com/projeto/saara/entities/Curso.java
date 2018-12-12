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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;


    @OneToMany(fetch = FetchType.LAZY, targetEntity = Usuario.class, cascade= CascadeType.ALL)
    private List<Usuario> usuarios;
}
