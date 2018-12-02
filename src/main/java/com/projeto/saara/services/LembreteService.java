package com.projeto.saara.services;

import com.projeto.saara.dto.output.LembreteDTO;
import com.projeto.saara.entities.Lembrete;
import com.projeto.saara.entities.Usuario;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.LembreteRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<LembreteDTO> getLembretes(String usuarioId) throws ValidationException {

        List<LembreteDTO> lembreteDTOS = new ArrayList<>();

        if (usuarioId == null) {
            throw new ValidationException();
        }

        Usuario usuario = usuarioRepository.findUsuarioById(ConverterHelper
                .convertStringToLong(usuarioId));

        List<Lembrete> lembretes = lembreteRepository.findLembreteByUsuario(usuario);

        if (lembretes != null) {
            for (Lembrete lembrete : lembretes) {
                LembreteDTO lembreteDTO = new LembreteDTO();
                lembreteDTO.setTipo(lembrete.getTipo().getDescricao());
                lembreteDTO.setAssunto(lembrete.getAssunto());
                if (lembrete.getTexto() != null) {
                    lembreteDTO.setTexto(lembrete.getTexto());
                }
                if (lembrete.getData() != null) {
                    lembreteDTO.setData(ConverterHelper.convertCalendarToString(lembrete
                            .getData()));
                }
                if (lembrete.getMateria() != null) {
                    lembreteDTO.setMateriaId(ConverterHelper.convertLongToString
                            (lembrete.getMateria().getId()));
                }

                lembreteDTOS.add(lembreteDTO);
            }
        }
        return lembreteDTOS;
    }

    public LembreteDTO getLembrete(String lembreteId) throws ValidationException {

        LembreteDTO lembreteDTO = null;

        if (lembreteId == null) {
            throw new ValidationException();
        }

        Lembrete lembrete = lembreteRepository.findLembreteById(ConverterHelper
                .convertStringToLong(lembreteId));

        if (lembrete != null) {
            lembreteDTO = new LembreteDTO();
            lembreteDTO.setTipo(lembrete.getTipo().getDescricao());
            lembreteDTO.setAssunto(lembrete.getAssunto());
            if (lembrete.getTexto() != null) {
                lembreteDTO.setTexto(lembrete.getTexto());
            }
            if (lembrete.getData() != null) {
                lembreteDTO.setData(ConverterHelper.convertCalendarToString(lembrete
                        .getData()));
            }
            if (lembrete.getMateria() != null) {
                lembreteDTO.setMateriaId(ConverterHelper.convertLongToString
                        (lembrete.getMateria().getId()));
            }
        }
        return lembreteDTO;
    }
}
