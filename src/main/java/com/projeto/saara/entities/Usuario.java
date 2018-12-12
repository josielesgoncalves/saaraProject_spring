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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Curso.class, cascade= CascadeType.ALL)
    private Curso curso;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = UsuarioMateria.class, cascade= CascadeType.ALL)
    private List<UsuarioMateria> materias;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Lembrete.class, cascade= CascadeType.ALL)
    private List<Lembrete> lembretes;

}
