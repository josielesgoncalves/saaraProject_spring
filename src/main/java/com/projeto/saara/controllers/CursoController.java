package com.projeto.saara.controllers;

import com.projeto.saara.dto.CursoDTO;
import com.projeto.saara.dto.output.MateriaDTO;
import com.projeto.saara.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping("/getCursos")
    public ResponseEntity<List<CursoDTO>> getCursos(){
        return null;
    }

    @GetMapping("/getCursos/{id}")
    public CursoDTO getCurso(String cursoId){
        return null;
    }

    @GetMapping("/getCursos/{id}/getMaterias")
    public List<MateriaDTO> getMaterias(String cursoId){
        return null;
    }

    @GetMapping("/getCursos/{id}/getMaterias/{id}")
    public MateriaDTO getMateria(String cursoId, String materiaId){
        return null;
    }
}
