package com.projeto.saara.controllers;

import lombok.Data;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@Scope(value = "session")
@Component(value = "testeController")
@ELBeanName(value = "testeController")
@Join(path = "/", to = "/paginas/home.jsf")
public class TesteController implements Serializable {

    List<String> listaTeste = new ArrayList<String>();
    HashMap<String, Integer> dadosProgresso = new HashMap<>();
    List<Atividade> atividades = new ArrayList<>();

    String teste = "teste";

    public TesteController() {
        listaTeste.add("teste1");
        listaTeste.add("teste2");
        listaTeste.add("teste3");
        listaTeste.add("teste4");
        listaTeste.add("teste5");

        dadosProgresso.put("teste1", 10);
        dadosProgresso.put("teste2", 20);
        dadosProgresso.put("teste3", 30);
        dadosProgresso.put("teste4", 40);
        dadosProgresso.put("teste5", 50);

        atividades.add(new Atividade(/*new Materia("Materia teste1"),*/ new Date(), "tipo1", "descrição1"));
        atividades.add(new Atividade(/*new Materia("Materia teste2"),*/ new Date(), "tipo2", "descrição2"));
        atividades.add(new Atividade(/*new Materia("Materia teste3"),*/ new Date(), "tipo1", "descrição3"));
        atividades.add(new Atividade(/*new Materia("Materia teste4"),*/ new Date(), "tipo2", "descrição4"));
        atividades.add(new Atividade(/*new Materia("Materia teste5"),*/ new Date(), "tipo1", "descrição5"));
        atividades.add(new Atividade(/*new Materia("Materia teste6"),*/ new Date(), "tipo1", "descrição6"));

    }










    /*@Autowired
    private ProductRepository productRepository;

    private Product product = new Product();

    public String save() {
        productRepository.save(product);
        product = new Product();
        return "/product-list.xhtml?faces-redirect=true";
    }

    public Product getProduct() {
        return product;
    }*/
}
