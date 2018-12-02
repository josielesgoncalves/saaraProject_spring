package com.projeto.saara.services;

import com.projeto.saara.dto.output.NotaDTO;
import com.projeto.saara.dto.output.UsuarioMateriaDTO;
import com.projeto.saara.entities.Nota;
import com.projeto.saara.entities.Usuario;
import com.projeto.saara.entities.UsuarioMateria;
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
public class UsuarioMateriaService {

    @Autowired
    private UsuarioMateriaRepository usuarioMateriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotaRepository notaRepository;

    public List<UsuarioMateriaDTO> getUsuarioMaterias(String usuarioId) throws ValidationException {

        List<UsuarioMateriaDTO> usuarioMateriaDTOS = new ArrayList<>();

        if (usuarioId == null) {
            throw new ValidationException();
        }

        Usuario usuario = usuarioRepository.findUsuarioById(ConverterHelper
                .convertStringToLong(usuarioId));
        if (usuario == null) {
            throw new ValidationException();
        }

        List<UsuarioMateria> usuarioMaterias = usuarioMateriaRepository
                .findUsuarioMateriaByUsuario(usuario);

        if (usuarioMaterias != null) {
            for (UsuarioMateria uMateria : usuarioMaterias) {

                UsuarioMateriaDTO usuarioMateriaDTO = new UsuarioMateriaDTO();
                usuarioMateriaDTO.setMateriaId(ConverterHelper.convertLongToString
                        (uMateria.getMateria().getId()));
                usuarioMateriaDTO.setUsuarioId(usuarioId);
                usuarioMateriaDTO.setStatusId(uMateria.getStatus().getDescricao());
                usuarioMateriaDTO.setMedia(ConverterHelper.convertDoubleToString
                        (uMateria.getMedia()));

                List<Nota> notas = notaRepository.findNotasByUsuarioMateria(uMateria);

                List<NotaDTO> notaDTOS = setNotaDTO(notas, uMateria);
                if (notaDTOS != null) {
                    usuarioMateriaDTO.setNotaDTOList(notaDTOS);
                }
            }
        }
        return usuarioMateriaDTOS;
    }

    public UsuarioMateriaDTO getUsuarioMateria(String usuarioMateriaId) throws ValidationException {

        UsuarioMateriaDTO usuarioMateriaDTO = new UsuarioMateriaDTO();

        if (usuarioMateriaId == null) {
            throw new ValidationException();
        }

        UsuarioMateria usuarioMateria = usuarioMateriaRepository.findUsuarioMateriaById
                (ConverterHelper.convertStringToLong(usuarioMateriaId));

        if (usuarioMateria != null) {

            usuarioMateriaDTO.setMateriaId(ConverterHelper.convertLongToString
                    (usuarioMateria.getMateria().getId()));
            usuarioMateriaDTO.setUsuarioId(ConverterHelper.convertLongToString(usuarioMateria.getUsuario().getId()));
            usuarioMateriaDTO.setStatusId(usuarioMateria.getStatus().getDescricao());
            usuarioMateriaDTO.setMedia(ConverterHelper.convertDoubleToString
                    (usuarioMateria.getMedia()));

            List<Nota> notas = notaRepository.findNotasByUsuarioMateria(usuarioMateria);

            List<NotaDTO> notaDTOS = setNotaDTO(notas, usuarioMateria);
            if (notaDTOS != null) {
                usuarioMateriaDTO.setNotaDTOList(notaDTOS);
            }
        }
        return usuarioMateriaDTO;
    }

    private List<NotaDTO> setNotaDTO(List<Nota> notas, UsuarioMateria usuarioMateria) throws ValidationException {
        List<NotaDTO> notaDTOS = null;

        if (notas != null) {
            notaDTOS = new ArrayList<>();
            for (Nota nota : notas) {
                NotaDTO notaDTO = new NotaDTO();
                notaDTO.setUsuarioMateriaId(ConverterHelper.convertLongToString(usuarioMateria.getId()));
                notaDTO.setPesoNota(ConverterHelper.convertDoubleToString(nota.getPesoNota()));
                notaDTO.setValor(ConverterHelper.convertDoubleToString(nota
                        .getValor()));
                notaDTO.setTipo(nota.getTipo().getDescricao());
                notaDTOS.add(notaDTO);
            }
        }
        return notaDTOS;
    }

    public void atualizaStatusUsuarioMateria(String usuarioMateriaId, UsuarioMateriaDTO
            dto)
            throws ValidationException {

        UsuarioMateria usuarioMateria = usuarioMateriaRepository.findUsuarioMateriaById
                (ConverterHelper.convertStringToLong(usuarioMateriaId));

        Long statusId = ConverterHelper.convertStringToLong(dto.getStatusId());
        usuarioMateria.setStatus(ConverterHelper.convertIdToStatusEnum(statusId));


    }
}
