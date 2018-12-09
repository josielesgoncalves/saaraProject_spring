package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Curso.class)
    private Curso curso;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UsuarioMateria.class)
    private List<UsuarioMateria> materias;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Lembrete.class)
    private List<Lembrete> lembretes;

}
