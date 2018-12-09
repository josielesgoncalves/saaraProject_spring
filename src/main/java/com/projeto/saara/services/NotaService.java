package com.projeto.saara.services;

import com.projeto.saara.dto.input.NewNotaDTO;
import com.projeto.saara.dto.output.NotaDTO;
import com.projeto.saara.entities.Nota;
import com.projeto.saara.entities.UsuarioMateria;
import com.projeto.saara.enums.NotaTypeEnum;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.exceptions.ParametroInvalidoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.repositories.interfaces.NotaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioMateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    private final UsuarioMateriaRepository usuarioMateriaRepository;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public NotaService(NotaRepository notaRepository, UsuarioMateriaRepository usuarioMateriaRepository, UsuarioRepository usuarioRepository) {
        this.notaRepository = notaRepository;
        this.usuarioMateriaRepository = usuarioMateriaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public NotaDTO getNota(long usuarioMateriaId, long notaId) {

        UsuarioMateria usuarioMateria =
                usuarioMateriaRepository.findUsuarioMateriaById(usuarioMateriaId).orElseThrow(() ->
                    new ObjetoNaoEncontradoException(
                            "A materia do usuario de id \"" + usuarioMateriaId + "\" não foi encontrada"));

        Nota nota = notaRepository.findNotaByUsuarioMateriaAndId(usuarioMateria, notaId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "A nota do usuarioMateria de id \"" + usuarioMateria + "\" " +
                                "e da nota de id \"" + notaId + "\" não foi encontrada"));

        NotaDTO notaDTO = new NotaDTO();

        notaDTO.setUsuarioMateriaId(ConverterHelper.convertLongToString
                (nota.getUsuarioMateria().getId()));
        notaDTO.setNotaId(ConverterHelper.convertLongToString(nota.getId()));
        notaDTO.setPesoNota(ConverterHelper.convertDoubleToString(nota.getPesoNota()));
        notaDTO.setValor(ConverterHelper.convertDoubleToString(nota.getValor()));
        notaDTO.setTipo(ConverterHelper.convertIdToNotaTypeEnum(nota.getTipo())
                .getDescricao());

        return notaDTO;
    }

    public void adicionarNota(long usuarioMateriaId, NewNotaDTO dto) {

        if (dto == null)
            throw new ParametroInvalidoException("NotaDTO nulo");

        UsuarioMateria usuarioMateria =
                usuarioMateriaRepository.findUsuarioMateriaById(usuarioMateriaId).orElseThrow(() ->
                    new ObjetoNaoEncontradoException(
                        "A materia do usuario de id \"" + usuarioMateriaId + "\" não foi encontrada"));

        Nota nota = new Nota();
        nota.setUsuarioMateria(usuarioMateria);
        nota.setPesoNota(ConverterHelper.convertStringToDouble(dto.getPesoNota()));
        nota.setValor(ConverterHelper.convertStringToDouble(dto.getValor()));

        if (dto.getTipo() == null) {
            nota.setTipo(NotaTypeEnum.OUTROS.getId());
        }

        Long tipoNotaId = ConverterHelper.convertStringToLong(dto.getTipo());
        nota.setTipo(tipoNotaId);
        notaRepository.saveAndFlush(nota);

        List<Nota> notas = new ArrayList<>();
        notas.add(nota);

        double media = calculaMediaMateria(notas);

        usuarioMateria.setNotas(notas);
        usuarioMateria.setMedia(media);
        usuarioMateriaRepository.saveAndFlush(usuarioMateria);
    }

    private double calculaMediaMateria(List<Nota> notas) {
        double media = 0;

        for (Nota nota : notas) {
            media += nota.getPesoNota() * nota.getValor();
        }
        return media;
    }
}
