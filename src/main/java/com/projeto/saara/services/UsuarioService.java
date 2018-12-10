package com.projeto.saara.services;

import com.projeto.saara.dto.output.UsuarioDTO;
import com.projeto.saara.dto.output.UsuarioMateriaDTO;
import com.projeto.saara.dto.input.NewLembreteDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.entities.*;
import com.projeto.saara.exceptions.ObjetoNaoEncontradoException;
import com.projeto.saara.exceptions.ParametroInvalidoException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.repositories.interfaces.CursoRepository;
import com.projeto.saara.repositories.interfaces.LembreteRepository;
import com.projeto.saara.repositories.interfaces.MateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final CursoRepository cursoRepository;

    private final MateriaRepository materiaRepository;

    private final LembreteRepository lembreteRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,
                          CursoRepository cursoRepository,
                          MateriaRepository materiaRepository,
                          LembreteRepository lembreteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.materiaRepository = materiaRepository;
        this.lembreteRepository = lembreteRepository;
    }

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
     *
     * @param usuarioDTO usuarioDTO
     * @param dtoList    lista de dados da materia selecionada
     */
    public void cadastrarMaterias(NewUsuarioDTO usuarioDTO, List<UsuarioMateriaDTO> dtoList) {

        Usuario usuario = usuarioRepository.findUsuarioByEmail(usuarioDTO.getEmail()).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "O usuario de email \"" + usuarioDTO.getEmail() + "\" não foi encontrado"));

        List<UsuarioMateria> usuarioMaterias = new ArrayList<>();

        //Nesse caso o usuario pode não salvar as matérias que não cursou
        for (UsuarioMateriaDTO item : dtoList) {
            Materia materia = materiaRepository.getMateriaById(
                    ConverterHelper.convertStringToLong(item.getMateriaId())).orElseThrow(() ->
                        new ObjetoNaoEncontradoException(
                            "A materia de id \"" + item.getMateriaId() + "\" não foi encontrada"));

            Long statusId = ConverterHelper.convertStringToLong(item.getStatusId());
            UsuarioMateria usuarioMateria = new UsuarioMateria();
            usuarioMateria.setMateria(materia);
            usuarioMateria.setStatus(statusId);

            usuarioMaterias.add(usuarioMateria);
        }
        usuario.setMaterias(usuarioMaterias);

        usuarioRepository.saveAndFlush(usuario);
    }

    public void adicionarLembrete(long usuarioId, long materiaId, NewLembreteDTO dto) {
        Usuario usuario = usuarioRepository.findUsuarioById(usuarioId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "O usuario de id \"" + usuarioId + "\" não foi encontrado"));

        Materia materia = materiaRepository.getMateriaById(materiaId).orElseThrow(() ->
                new ObjetoNaoEncontradoException(
                        "A materia de id \"" + materiaId + "\" não foi encontrada"));

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
