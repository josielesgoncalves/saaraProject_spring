package com.projeto.saara.entities;

import com.projeto.saara.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USUARIO_MATERIA")
@Data
public class UsuarioMateria implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    //@Column(name = "MATERIA_ID")
    @ManyToOne(targetEntity = Materia.class)
    private Materia materia;

    //@Column(name = "USUARIO_ID")
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Nota> notas;

    @Column(name = "STATUS")
    private StatusEnum status;

    @Column(name = "MEDIA")
    private double media;
}
