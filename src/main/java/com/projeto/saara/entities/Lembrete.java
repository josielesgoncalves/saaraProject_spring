package com.projeto.saara.entities;

import com.projeto.saara.enums.LembreteTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "LEMBRETE")
@Data
public class Lembrete implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO")
    private Long tipo;

    @Column(name = "ASSUNTO")
    private String assunto;

    @Column(name = "TEXTO")
    private String texto;

    @Column(name = "DATA")
    private Calendar data;

    @ManyToOne(targetEntity = Materia.class)
    private Materia materia;

    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;

}