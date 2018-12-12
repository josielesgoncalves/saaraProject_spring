package com.projeto.saara.services;

import com.projeto.saara.dto.output.LembreteDTO;
import com.projeto.saara.dto.output.MateriaDTO;
import com.projeto.saara.entities.Lembrete;
import com.projeto.saara.entities.Usuario;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.repositories.interfaces.LembreteRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository, UsuarioRepository usuarioRepository) {
        this.lembreteRepository = lembreteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<LembreteDTO> getLembretes(long usuarioId) {

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException("Usuario de id \"" + usuarioId + "\" não encontrado"));

        List<LembreteDTO> lembreteDTOS = new ArrayList<>();
        List<Lembrete> lembretes = lembreteRepository.findLembreteByUsuario(usuario).orElseThrow(() ->
                new ObjetoNaoEncontradoException("Os lembretes do usuario \"" + usuario.getNome() + "\" não foram encontrados"));

        for (Lembrete lembrete : lembretes) { //TODO fazer isso aqui com o mapper?
            LembreteDTO lembreteDTO = new LembreteDTO();
            lembreteDTO.setTipo(ConverterHelper.convertIdToLembreteTypeEnum(lembrete
                    .getTipo()).getDescricao());
            lembreteDTO.setAssunto(lembrete.getAssunto());
            if (lembrete.getTexto() != null) {
                lembreteDTO.setTexto(lembrete.getTexto());
            }
            if (lembrete.getData() != null) {
                lembreteDTO.setData(ConverterHelper.convertCalendarToString(lembrete
                        .getData()));
            }
            if (lembrete.getMateria() != null) {
                lembreteDTO.setMateriaDTO(
                        new MateriaDTO(lembrete.getMateria().getId().toString(), lembrete.getMateria().getNome())
                );
            }

            lembreteDTOS.add(lembreteDTO);
        }

        return lembreteDTOS;
    }

    public LembreteDTO getLembrete(long lembreteId) {

        LembreteDTO lembreteDTO;

        Lembrete lembrete = lembreteRepository.findLembreteById(lembreteId).orElseThrow(() ->
                new ObjetoNaoEncontradoException("O lembrete de id \"" + lembreteId + "\" não foi encontrado"));

        lembreteDTO = new LembreteDTO();
        lembreteDTO.setTipo(ConverterHelper.convertIdToLembreteTypeEnum(lembrete
                .getTipo()).getDescricao());
        lembreteDTO.setAssunto(lembrete.getAssunto());
        if (lembrete.getTexto() != null) {
            lembreteDTO.setTexto(lembrete.getTexto());
        }
        if (lembrete.getData() != null) {
            lembreteDTO.setData(ConverterHelper.convertCalendarToString(lembrete
                    .getData()));
        }
        if (lembrete.getMateria() != null) {
            lembreteDTO.setMateriaDTO(
                    new MateriaDTO(lembrete.getMateria().getId().toString(), lembrete.getMateria().getNome())
            );
        }

        return lembreteDTO;
    }
}
