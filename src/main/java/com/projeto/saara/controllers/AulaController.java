package com.projeto.saara.controllers;

import com.projeto.saara.dto.output.AulaDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getMaterias/{id}")
public class AulaController {

    @GetMapping("/getAulas")
    public List<AulaDTO> getAulas(String materiaId){
        return null;
    }

    @GetMapping("/getAulas/{id}")
    public AulaDTO getAula(String materiaId, String aulaId){
        return null;
    }
}
