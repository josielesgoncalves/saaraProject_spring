package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="AULA")
@Data
public class Aula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROFESSOR")
    private String professor;

    @Column(name = "HORARIO")
    private String horario;

    @Column(name = "LOCAL")
    private String local;

    @Column(name = "DIA")
    private Long dia;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class)
    private Usuario usuario;
}
