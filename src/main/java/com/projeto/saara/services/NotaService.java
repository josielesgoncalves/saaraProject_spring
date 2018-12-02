package com.projeto.saara.services;

import com.projeto.saara.dto.input.NewNotaDTO;
import com.projeto.saara.dto.output.NotaDTO;
import com.projeto.saara.entities.Nota;
import com.projeto.saara.entities.Usuario;
import com.projeto.saara.entities.UsuarioMateria;
import com.projeto.saara.enums.NotaTypeEnum;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.NotaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioMateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private UsuarioMateriaRepository usuarioMateriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public NotaDTO getNota(String usuarioMateriaId, String notaId)
            throws ValidationException {
        NotaDTO notaDTO = new NotaDTO();

        if (usuarioMateriaId == null || notaId != null) {
            throw new ValidationException();
        }

        UsuarioMateria usuarioMateria = usuarioMateriaRepository.findUsuarioMateriaById
                (ConverterHelper.convertStringToLong(usuarioMateriaId));

        if (usuarioMateria == null) {
            throw new ValidationException();
        }
        Nota nota = notaRepository.findNotaByUsuarioMateriaAndId(usuarioMateria,
                ConverterHelper.convertStringToLong(notaId));

        if (nota != null) {
            notaDTO.setUsuarioMateriaId(ConverterHelper.convertLongToString
                    (nota.getUsuarioMateria().getId()));
            notaDTO.setNotaId(ConverterHelper.convertLongToString(nota.getId()));
            notaDTO.setPesoNota(ConverterHelper.convertDoubleToString(nota.getPesoNota()));
            notaDTO.setValor(ConverterHelper.convertDoubleToString(nota.getValor()));
            notaDTO.setTipo(nota.getTipo().getDescricao());
        }
        return notaDTO;
    }

    public void adicionarNota(String usuarioMateriaId, NewNotaDTO dto)
            throws ValidationException {

        if (usuarioMateriaId == null || dto == null) {
            throw new ValidationException();
        }

        UsuarioMateria usuarioMateria = usuarioMateriaRepository.findUsuarioMateriaById
                (ConverterHelper.convertStringToLong(usuarioMateriaId));

        if (usuarioMateria == null) {
            throw new ValidationException();
        }

        Nota nota = new Nota();
        nota.setUsuarioMateria(usuarioMateria);
        nota.setPesoNota(ConverterHelper.convertStringToDouble(dto.getPesoNota()));
        nota.setValor(ConverterHelper.convertStringToDouble(dto.getValor()));

        if (dto.getTipo() == null) {
            nota.setTipo(NotaTypeEnum.OUTROS);
        }

        Long tipoNotaId = ConverterHelper.convertStringToLong(dto.getTipo());
        nota.setTipo(ConverterHelper.convertIdToNotaTypeEnum(tipoNotaId));
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
