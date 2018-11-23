package com.projeto.saara.services;

import com.projeto.saara.repositories.interfaces.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaService {

    @Autowired
    NotaRepository notaRepository;




}
