package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NOTA")
@Data
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
