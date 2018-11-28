package com.projeto.saara.controllers;

import com.projeto.saara.dto.UsuarioDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.services.CursoService;
import com.projeto.saara.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CursoService cursoService;

    @GetMapping("/login")
    public String login(UsuarioDTO usuario) {
        return null;
    }

}
