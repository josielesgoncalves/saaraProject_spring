package com.projeto.saara.services;

import com.projeto.saara.dto.input.NewAulaDTO;
import com.projeto.saara.dto.output.*;
import com.projeto.saara.dto.output.AulaDTO;
import com.projeto.saara.dto.output.MateriaDTO;
import com.projeto.saara.dto.output.NotaDTO;
import com.projeto.saara.dto.output.UsuarioMateriaDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.enums.DiaEnum;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.exceptions.ValidationException;
import com.projeto.saara.repositories.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioMateriaService {

    private final UsuarioMateriaRepository usuarioMateriaRepository;

    private final UsuarioRepository usuarioRepository;

    private final NotaRepository notaRepository;

    private final AulaRepository aulaRepository;

    private final MateriaRepository materiaRepository;

    private final MateriaCursoRepository materiaCursoRepository;

    @Autowired
    public UsuarioMateriaService(UsuarioMateriaRepository usuarioMateriaRepository,
                                 UsuarioRepository usuarioRepository,
                                 NotaRepository notaRepository,
                                 AulaRepository aulaRepository,
                                 MateriaRepository materiaRepository,
                                 MateriaCursoRepository materiaCursoRepository) {
        this.usuarioMateriaRepository = usuarioMateriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.notaRepository = notaRepository;
        this.aulaRepository = aulaRepository;
        this.materiaRepository = materiaRepository;
        this.materiaCursoRepository = materiaCursoRepository;
    }

    public List<UsuarioMateriaDTO>/*List<UsuarioMateria> */getUsuarioMaterias(long usuarioId) {

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
            usuarioMateriaDTO.setMateriaDTO(
                    new MateriaDTO(uMateria.getMateria().getId().toString(),uMateria.getMateria().getNome())
            );
            usuarioMateriaDTO.setUsuarioDTO(
                    new UsuarioDTO(usuario.getId().toString(),
                            usuario.getNome(),
                            usuario.getEmail(),
                            usuario.getCurso().getId().toString(),
                            null,
                            null)
            );
            usuarioMateriaDTO.setStatusId(ConverterHelper.convertIdToStatusEnum(uMateria.getStatus()).getDescricao());
            usuarioMateriaDTO.setMedia(ConverterHelper.convertDoubleToString(uMateria.getMedia()));

            List<Nota> notas = notaRepository.findNotasByUsuarioMateria(uMateria).orElse(new ArrayList<>());

            List<NotaDTO> notaDTOS = setNotaDTO(notas, uMateria);
            if (notaDTOS != null) {
                usuarioMateriaDTO.setNotaDTOList(notaDTOS);
            }

            usuarioMateriaDTOS.add(usuarioMateriaDTO);
        }
        return usuarioMateriaDTOS;
    }

    @Transactional
    public UsuarioMateriaDTO getUsuarioMateria(long usuarioMateriaId) {

        UsuarioMateriaDTO usuarioMateriaDTO = new UsuarioMateriaDTO();

        UsuarioMateria usuarioMateria =
                usuarioMateriaRepository.findUsuarioMateriaById(usuarioMateriaId).orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "Aa usuariomMateria de id \"" + usuarioMateriaId + "\" não foi encontrada"));

        usuarioMateriaDTO.setMateriaDTO(
                new MateriaDTO(usuarioMateria.getMateria().getId().toString(),usuarioMateria.getMateria().getNome())
        );
        usuarioMateriaDTO.setUsuarioDTO(
                new UsuarioDTO(usuarioMateria.getUsuario().getId().toString(),
                        usuarioMateria.getUsuario().getNome(),
                        usuarioMateria.getUsuario().getEmail(),
                        usuarioMateria.getUsuario().getCurso().getId().toString(),
                        null,
                        null)
        );
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

    @Transactional
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
    @Transactional
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
    @Transactional
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

    @Transactional
    public void adicionarAula(NewAulaDTO dto) {

        Usuario usuario =
                usuarioRepository.findUsuarioById(ConverterHelper
                        .convertStringToLong(dto.getUsuarioId()))
                        .orElseThrow(() ->
                                new ObjetoNaoEncontradoException(
                                        "Usuario nao encontrado"));

        Materia materia = materiaRepository.findById(ConverterHelper
                .convertStringToLong(dto.getMateriaId())).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "Matéria nao encontrada"));

        Aula aula = new Aula();

        aula.setUsuario(usuario);
        aula.setDia(ConverterHelper.convertStringToLong(dto.getDiaSemanaId()));
        aula.setHorario(dto.getHorario());
        aula.setLocal(dto.getLocal());
        aula.setProfessor(dto.getProfessor());
        aulaRepository.saveAndFlush(aula);

        materia.getAulas().add(aula);
        materiaRepository.saveAndFlush(materia);
    }

    @Transactional
    public void atualizarAula(Long aulaId, AulaDTO dto) {

        Aula aula = aulaRepository.findAulaById(aulaId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "Aula de id \"" + aulaId + "\" não foi encontrada"));

        aula.setDia(ConverterHelper.convertStringToLong(dto.getDia()));
        aula.setHorario(dto.getHorario());
        aula.setLocal(dto.getLocal());
        aula.setProfessor(dto.getProfessor());
        aulaRepository.saveAndFlush(aula);
    }

    @Transactional
    public List<AulaDTO> getAulas(Long usuarioId) {
        List<AulaDTO> aulas = new ArrayList<>();
        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "Usuario não encontrado"));

        List<UsuarioMateria> materias = null;
        if (usuario.getMaterias() != null)
            materias = usuario.getMaterias();

        if (materias != null) {
            for (UsuarioMateria usuarioMateria : materias) {
                if (usuarioMateria.getMateria() != null)
                    for (Aula aula : usuarioMateria.getMateria().getAulas()) {
                        if (aula.getUsuario() == usuario) {
                            AulaDTO aulaDTO = new AulaDTO();
                            aulaDTO.setDia(ConverterHelper.convertIdToDiaEnum(aula.getDia()
                            ).getDescricao());
                            aulaDTO.setProfessor(aula.getProfessor());
                            aulaDTO.setLocal(aula.getLocal());
                            aulaDTO.setHorario(aula.getHorario());
                            aulaDTO.setNomeMateria(usuarioMateria.getMateria().getNome());
                            aulas.add(aulaDTO);
                        }
                    }
            }
        }
        return aulas;
    }

    public List<UsuarioMateriaDTO> getMateriasCursadas(Long usuarioId) {
        List<UsuarioMateriaDTO> usuarioMateriaDTOS = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(()
                -> new ObjetoNaoEncontradoException("Usuario nao encontrado"));

        List<UsuarioMateria> usuarioMaterias = usuario.getMaterias();

        if (usuarioMaterias != null) {
            usuarioMateriaDTOS = this.getMaterias(usuarioMaterias, usuarioMateriaDTOS,
                    StatusEnum.CURSADA);
        }
        return usuarioMateriaDTOS;
    }

    public List<UsuarioMateriaDTO> getMateriasCursando(Long usuarioId) {
        List<UsuarioMateriaDTO> usuarioMateriaDTOS = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(()
                -> new ObjetoNaoEncontradoException("Usuario nao encontrado"));

        List<UsuarioMateria> usuarioMaterias = usuario.getMaterias();

        if (usuarioMaterias != null) {
            usuarioMateriaDTOS = this.getMaterias(usuarioMaterias, usuarioMateriaDTOS,
                    StatusEnum.CURSANDO);
        }
        return usuarioMateriaDTOS;
    }

    public List<UsuarioMateriaDTO> getMateriasNaoCursadas(Long usuarioId) {

        List<UsuarioMateriaDTO> usuarioMateriaDTOS = new ArrayList<>();

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(()
                -> new ObjetoNaoEncontradoException("Usuario nao encontrado"));

        List<UsuarioMateria> usuarioMaterias = usuario.getMaterias();

        List<MateriaCurso> materias = materiaCursoRepository.findAllByCurso(usuario.getCurso
                ()).orElseThrow(() -> new ObjetoNaoEncontradoException("Matérias não " +
                "encontradas"));

        for (UsuarioMateria usuarioMateria : usuarioMaterias) {
            for (MateriaCurso materiaCurso : materias) {
                if (usuarioMateria.getStatus().equals(StatusEnum.NAO_CURSADA) ||
                        usuarioMateria.getMateria().getId() != materiaCurso.getMateria().getId()) {
                    UsuarioMateriaDTO usuarioMateriaDTO = new UsuarioMateriaDTO();
                    usuarioMateriaDTO.setNomeMateria(usuarioMateria.getMateria().getNome());
                    usuarioMateriaDTO.setMedia(ConverterHelper.convertDoubleToString(usuarioMateria
                            .getMedia()));
                    usuarioMateriaDTOS.add(usuarioMateriaDTO);
                }
            }
        }
        return usuarioMateriaDTOS;
    }

    private List<UsuarioMateriaDTO> getMaterias(List<UsuarioMateria> usuarioMaterias,
                                                List<UsuarioMateriaDTO> usuarioMateriaDTOS,
                                                StatusEnum status) {

        for (UsuarioMateria usuarioMateria : usuarioMaterias) {
            if (usuarioMateria.getStatus().equals(status)) {
                UsuarioMateriaDTO usuarioMateriaDTO = new UsuarioMateriaDTO();
                usuarioMateriaDTO.setNomeMateria(usuarioMateria.getMateria().getNome());
                usuarioMateriaDTO.setMedia(ConverterHelper.convertDoubleToString(usuarioMateria
                        .getMedia()));
                usuarioMateriaDTOS.add(usuarioMateriaDTO);
            }
        }
        return usuarioMateriaDTOS;
    }


}

