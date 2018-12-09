package com.projeto.saara.entities;

import com.projeto.saara.enums.DiaEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Entity
@Table(name="AULA")
@Data
public class Aula implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROFESSOR")
    private String professor;

    @Column(name = "DATA_HORARIO")
    private Calendar dataHorario;

    @Column(name = "LOCAL")
    private String local;

    @Column(name = "DIA")
    private Long dia;

}
