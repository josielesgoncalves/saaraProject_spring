package com.projeto.saara.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USUARIO_MATERIA")
@Data
public class UsuarioMateria implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Materia.class, cascade= CascadeType.ALL)
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class, cascade= CascadeType.ALL)
    private Usuario usuario;


    @OneToMany(fetch = FetchType.LAZY, targetEntity = Nota.class, cascade= CascadeType.ALL)
    private List<Nota> notas;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "MEDIA")
    private double media;
}
