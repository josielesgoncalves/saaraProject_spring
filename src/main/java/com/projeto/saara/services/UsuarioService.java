package com.projeto.saara.services;

import com.projeto.saara.dto.output.UsuarioDTO;
import com.projeto.saara.dto.output.UsuarioMateriaDTO;
import com.projeto.saara.dto.input.NewLembreteDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.enums.NotaTypeEnum;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.exceptions.ParametroInvalidoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.repositories.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final CursoRepository cursoRepository;

    private final MateriaRepository materiaRepository;

    private final LembreteRepository lembreteRepository;

    private final NotaRepository notaRepository;

    private final UsuarioMateriaRepository usuarioMateriaRepository;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          CursoRepository cursoRepository,
                          MateriaRepository materiaRepository,
                          LembreteRepository lembreteRepository,
                          NotaRepository notaRepository,
                          UsuarioMateriaRepository usuarioMateriaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.materiaRepository = materiaRepository;
        this.lembreteRepository = lembreteRepository;
        this.notaRepository = notaRepository;
        this.usuarioMateriaRepository = usuarioMateriaRepository;
    }

    @Transactional
    public UsuarioDTO logar(String email, String senha) {

        UsuarioDTO dto = null;
        final Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "\"" + email + "\" não encontrado"));

        if(senha.equals(usuario.getSenha())){
            dto = new UsuarioDTO();
            dto.setUsuarioId(ConverterHelper.convertLongToString(usuario.getId()));
            dto.setNome(usuario.getNome());
            dto.setCursoId(ConverterHelper.convertLongToString(usuario.getCurso().getId()));
            dto.setEmail(usuario.getEmail());
            dto.setSenha(usuario.getSenha());
        }
        else{
            throw new ParametroInvalidoException("Senha incorreta");
        }

        return dto;
    }

    private boolean usuarioCadastrado(String email)
    {
        return usuarioRepository.findUsuarioByEmail(email).isPresent();
    }

    @Transactional
    public void cadastrarUsuario(NewUsuarioDTO dto) {
        if (!usuarioCadastrado(dto.getEmail())) {
            Usuario usuario = dto.criarNovoUsuario();

            Curso curso =
                cursoRepository.findCursoById(ConverterHelper.convertStringToLong(dto.getCursoId())).orElseThrow(() ->
                    new ObjetoNaoEncontradoException(
                            "O curso de id \"" + dto.getCursoId() + "\" não foi encontrado"));

            usuario.setCurso(curso);
            usuarioRepository.saveAndFlush(usuario);
        }
    }

    /**
     * Salva os dados do usuario com as materias cursadas ou cursando
     * @param dto
     */
    @Transactional
    public void cadastrarMateria(UsuarioMateriaDTO dto) {

        Usuario usuario = usuarioRepository.findUsuarioById(
                ConverterHelper.convertStringToLong(dto.getUsuarioDTO().getUsuarioId())).orElseThrow(() ->
                    new ObjetoNaoEncontradoException("O usuario não foi encontrado"));

        List<UsuarioMateria> usuarioMaterias = new ArrayList<>();
        if(null != usuario.getMaterias()){
            usuarioMaterias = usuario.getMaterias();
        }

        Materia materia = materiaRepository.getMateriaById(
                ConverterHelper.convertStringToLong(dto.getMateriaDTO().getMateriaId())).orElseThrow(() ->
                        new ObjetoNaoEncontradoException("A materia não foi encontrada"));

        Long statusId = ConverterHelper.convertStringToLong(dto.getStatusId());
        UsuarioMateria usuarioMateria = new UsuarioMateria();
        usuarioMateria.setMateria(materia);
        usuarioMateria.setStatus(statusId);
        usuarioMateria.setUsuario(usuario);

        List<Nota> notas = new ArrayList<>();
        Nota nota = null;
        if(!dto.getNota().equals("") || dto.getNota()!= null) {
            nota = new Nota();
            nota.setUsuarioMateria(usuarioMateria);
            notas = new ArrayList<>();
            double valor = ConverterHelper.convertStringToDouble(dto.getNota());
            nota.setValor(valor);
        }
        if(!dto.getPeso().equals("") || dto.getPeso() != null) {
            double peso = ConverterHelper.convertStringToDouble(dto.getPeso());
            nota.setPesoNota(peso);
        }
        if(!dto.getNotaType().equals("") || dto.getNotaType() != null){
            NotaTypeEnum notaTypeEnum = ConverterHelper.convertIdToNotaTypeEnum
                    (ConverterHelper.convertStringToLong(dto.getNotaType()));
            nota.setTipo(notaTypeEnum .getId());
        }
        if(nota != null){
            if(notas != null)
                notas.add(nota);
        }

        usuarioMateria.setNotas(notas);
        usuarioMateriaRepository.saveAndFlush(usuarioMateria);
        notaRepository.saveAndFlush(nota);

        usuarioMaterias.add(usuarioMateria);
        usuario.setMaterias(usuarioMaterias);

        usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    public void adicionarLembrete(NewLembreteDTO dto) {
        Usuario usuario = usuarioRepository.findUsuarioById(ConverterHelper
                .convertStringToLong(dto.getUsuarioId()))
                .orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "Usuario não encontrado"));

        Materia materia = materiaRepository.getMateriaById(ConverterHelper
                .convertStringToLong(dto.getMateriaId()))
                .orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                                "Matéria não encontrada"));

        if (dto == null) {
            throw new ParametroInvalidoException("lembreteDTO nulo");
        }

        Lembrete lembrete = dto.criarLembrete();
        lembrete.setUsuario(usuario);
        lembrete.setMateria(materia);
        lembreteRepository.saveAndFlush(lembrete);

        List<Lembrete> lembretes = new ArrayList<>();
        lembretes.add(lembrete);
        usuario.setLembretes(lembretes);
        usuarioRepository.saveAndFlush(usuario);
    }
}
