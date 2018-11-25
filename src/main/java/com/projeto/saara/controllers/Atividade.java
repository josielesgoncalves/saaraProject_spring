package com.projeto.saara.controllers;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Atividade implements Serializable {
	/*private Materia materia;*/
	private Date data;
	private String tipo;
	private String descricao;
	
	public Atividade(/*Materia materia, */Date data, String tipo, String descricao) {
		/*this.materia = materia;*/
		this.data = data;
		this.tipo = tipo;
		this.descricao = descricao;
	}
}
