package com.projeto.saara.entities;

import com.projeto.saara.enums.NotaTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "NOTA")
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
    private NotaTypeEnum tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPesoNota() {
        return pesoNota;
    }

    public void setPesoNota(double pesoNota) {
        this.pesoNota = pesoNota;
    }

    public NotaTypeEnum getTipo() {
        return tipo;
    }

    public void setTipo(NotaTypeEnum tipo) {
        this.tipo = tipo;
    }

  }
