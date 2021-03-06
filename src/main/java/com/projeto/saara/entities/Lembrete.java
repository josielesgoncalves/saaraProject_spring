package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "LEMBRETE")
@Data
public class Lembrete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO")
    private Long tipo;

    @Column(name = "ASSUNTO")
    private String assunto;

    @Column(name = "TEXTO")
    private String texto;

    @Column(name = "DATA")
    private String data;

    @ManyToOne(targetEntity = Materia.class, cascade= CascadeType.ALL)
    private Materia materia;

    @ManyToOne(targetEntity = Usuario.class, cascade= CascadeType.ALL)
    private Usuario usuario;

}