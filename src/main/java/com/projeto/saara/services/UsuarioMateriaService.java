package com.projeto.saara.services;

import com.projeto.saara.dto.input.NewAulaDTO;
import com.projeto.saara.dto.output.AulaDTO;
import com.projeto.saara.dto.output.NotaDTO;
import com.projeto.saara.dto.output.UsuarioMateriaDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.enums.DiaEnum;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.exceptions.ValidationException;
import com.projeto.saara.repositories.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioMateriaService {

    private final UsuarioMateriaRepository usuarioMateriaRepository;

    private final UsuarioRepository usuarioRepository;

    private final NotaRepository notaRepository;

    private final AulaRepository aulaRepository;

    private final MateriaRepository materiaRepository;

    @Autowired
    public UsuarioMateriaService(UsuarioMateriaRepository usuarioMateriaRepository,
                                 UsuarioRepository usuarioRepository,
                                 NotaRepository notaRepository,
                                 AulaRepository aulaRepository,
                                 MateriaRepository materiaRepository) {
        this.usuarioMateriaRepository = usuarioMateriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.notaRepository = notaRepository;
        this.aulaRepository = aulaRepository;
        this.materiaRepository = materiaRepository;
    }

    public List<UsuarioMateriaDTO> getUsuarioMaterias(long usuarioId) {

        List<UsuarioMateriaDTO> usuarioMateriaDTOS = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "O usuario de id \"" + usuarioId + "\" não foi encontrado"));

        List<UsuarioMateria> usuarioMaterias =
                usuarioMateriaRepository.findUsuarioMateriaByUsuario(usuario).orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "As usuariomMateria do usuario \"" + usuarioId + "\" não foram encontradas"));

        for (UsuarioMateria uMateria : usuarioMaterias) {

            UsuarioMateriaDTO usuarioMateriaDTO = new UsuarioMateriaDTO();
            usuarioMateriaDTO.setMateriaId(ConverterHelper.convertLongToString
                    (uMateria.getMateria().getId()));
            usuarioMateriaDTO.setUsuarioId(ConverterHelper.convertLongToString(usuarioId));
            usuarioMateriaDTO.setStatusId(ConverterHelper.convertIdToStatusEnum(uMateria.getStatus())
                    .getDescricao());
            usuarioMateriaDTO.setMedia(ConverterHelper.convertDoubleToString
                    (uMateria.getMedia()));

            List<Nota> notas = notaRepository.findNotasByUsuarioMateria(uMateria).orElseThrow(() ->
                    new ObjetoNaoEncontradoException(
                            "As notas do usuariomMateria \"" + uMateria.getId() + "\" não foram encontradas"));

            List<NotaDTO> notaDTOS = setNotaDTO(notas, uMateria);
            if (notaDTOS != null) {
                usuarioMateriaDTO.setNotaDTOList(notaDTOS);
            }
        }

        return usuarioMateriaDTOS;
    }

    public UsuarioMateriaDTO getUsuarioMateria(long usuarioMateriaId) {

        UsuarioMateriaDTO usuarioMateriaDTO = new UsuarioMateriaDTO();

        UsuarioMateria usuarioMateria =
                usuarioMateriaRepository.findUsuarioMateriaById(usuarioMateriaId).orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "Aa usuariomMateria de id \"" + usuarioMateriaId + "\" não foi encontrada"));

        usuarioMateriaDTO.setMateriaId(ConverterHelper.convertLongToString
                (usuarioMateria.getMateria().getId()));
        usuarioMateriaDTO.setUsuarioId(ConverterHelper.convertLongToString
                (usuarioMateria.getUsuario().getId()));
        usuarioMateriaDTO.setStatusId(ConverterHelper.convertIdToStatusEnum(usuarioMateria.getStatus())
                .getDescricao());
        usuarioMateriaDTO.setMedia(ConverterHelper.convertDoubleToString
                (usuarioMateria.getMedia()));

        List<Nota> notas = notaRepository.findNotasByUsuarioMateria(usuarioMateria).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "As notas da usuariomateria de id \"" + usuarioMateria.getId() + "\" não foram encontradas"));

        List<NotaDTO> notaDTOS = setNotaDTO(notas, usuarioMateria);

        usuarioMateriaDTO.setNotaDTOList(notaDTOS);

        return usuarioMateriaDTO;
    }

    private List<NotaDTO> setNotaDTO(List<Nota> notas, UsuarioMateria usuarioMateria) {
        List<NotaDTO> notaDTOS;

        notaDTOS = new ArrayList<>();
        for (Nota nota : notas) {
            NotaDTO notaDTO = new NotaDTO();
            notaDTO.setUsuarioMateriaId(ConverterHelper.convertLongToString
                    (usuarioMateria.getId()));
            notaDTO.setPesoNota(ConverterHelper.convertDoubleToString
                    (nota.getPesoNota()));
            notaDTO.setValor(ConverterHelper.convertDoubleToString(nota
                    .getValor()));
            notaDTO.setTipo(ConverterHelper.convertIdToNotaTypeEnum(nota.getTipo()).getDescricao());
            notaDTOS.add(notaDTO);
        }

        return notaDTOS;
    }

    public void atualizaStatusUsuarioMateria(long usuarioMateriaId, UsuarioMateriaDTO dto) {

        UsuarioMateria usuarioMateria =
                usuarioMateriaRepository.findUsuarioMateriaById(usuarioMateriaId).orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "A usuariomateria de id \"" + usuarioMateriaId + "\" não foi encontrada"));

        Long statusId = ConverterHelper.convertStringToLong(dto.getStatusId());
        usuarioMateria.setStatus(statusId);

        usuarioMateriaRepository.saveAndFlush(usuarioMateria);
    }

    //getAulas por dia
    public List<AulaDTO> getAulasDia(long usuarioId, long diaId) {

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "O usuario de id \"" + usuarioId + "\" não foi encontrado"));

        List<AulaDTO> aulaDTOS = new ArrayList<>();

        List<UsuarioMateria> usuarioMaterias = usuario.getMaterias();
        DiaEnum dia = ConverterHelper.convertIdToDiaEnum(diaId);

        for (UsuarioMateria usuarioMateria : usuarioMaterias) {
            for (Aula aula : usuarioMateria.getMateria().getAulas()) {
                if (aula != null && aula.getDia().equals(dia)) {
                    AulaDTO aulaDTO = setAulaDTO(aula);
                    aulaDTOS.add(aulaDTO);
                }
            }
        }
        return aulaDTOS;
    }

    //getAulas por materia
    public List<AulaDTO> getAulasMateria(long usuarioMateriaId) {

        UsuarioMateria usuarioMateria =
                usuarioMateriaRepository.findUsuarioMateriaById(usuarioMateriaId).orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "A usuariomateria de id \"" + usuarioMateriaId + "\" não foi encontrada"));

        List<AulaDTO> aulaDTOS = new ArrayList<>();

        for (Aula aula : usuarioMateria.getMateria().getAulas()) {
            AulaDTO aulaDTO = setAulaDTO(aula);
            aulaDTOS.add(aulaDTO);
        }
        return aulaDTOS;
    }

    private AulaDTO setAulaDTO(Aula aula) throws ValidationException {

        AulaDTO aulaDTO = new AulaDTO();
        aulaDTO.setHorario(aula.getHorario());
        aulaDTO.setDia(ConverterHelper.convertIdToDiaEnum(aula.getDia()).getDescricao());
        aulaDTO.setLocal(aula.getLocal());
        aulaDTO.setProfessor(aula.getProfessor());

        return aulaDTO;
    }

    public void adicionarAula(Long usuarioMateriaId, NewAulaDTO dto) {

        UsuarioMateria usuarioMateria =
                usuarioMateriaRepository.findUsuarioMateriaById(usuarioMateriaId).orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "A usuariomateria de id \"" + usuarioMateriaId + "\" não foi encontrada"));

        Materia materia = usuarioMateria.getMateria();
        Aula aula = new Aula();

        aula.setDia(ConverterHelper.convertStringToLong(dto.getDiaSemanaId()));
        aula.setHorario(dto.getHorario());
        aula.setLocal(dto.getLocal());
        aula.setProfessor(dto.getProfessor());
        aulaRepository.saveAndFlush(aula);

        materia.getAulas().add(aula);
        materiaRepository.saveAndFlush(materia);
    }
}