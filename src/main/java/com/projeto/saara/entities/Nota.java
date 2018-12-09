package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "NOTA")
@Data
public class Nota {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALOR")
    private double valor;

    @Column(name = "PESO")
    private double pesoNota;

    @Column(name = "TIPO")
    private Long tipo;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UsuarioMateria.class)
    private UsuarioMateria usuarioMateria;
}
