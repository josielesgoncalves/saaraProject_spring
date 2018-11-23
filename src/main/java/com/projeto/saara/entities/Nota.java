package com.projeto.saara.entities;

import com.projeto.saara.enums.TipoNota;

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
    private TipoNota tipo;

    @Column(name = "CURSANDO_MATERIA")
    private boolean cursandoMateria;

    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    private Materia materia;

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

    public TipoNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoNota tipo) {
        this.tipo = tipo;
    }

    public boolean isCursandoMateria() {
        return cursandoMateria;
    }

    public void setCursandoMateria(boolean cursandoMateria) {
        this.cursandoMateria = cursandoMateria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
