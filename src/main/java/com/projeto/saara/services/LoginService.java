package com.projeto.saara.services;

import com.projeto.saara.dto.UsuarioDTO;
import com.projeto.saara.entities.Curso;
import com.projeto.saara.entities.Usuario;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.CursoRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    public UsuarioDTO buscarUsuario(String login, String senha) throws ValidationException {

        UsuarioDTO dto = null;
        final Usuario usuario = usuarioRepository.findUsuarioByEmail(login);

        if (usuario == null) {
            throw new ValidationException();
        }

        if (senha.equals(usuario.getSenha())) {
            dto = new UsuarioDTO(usuario);
        }

        return dto;
    }

    public boolean usuarioCadastrado(String email) {
        boolean cadastrado = true;
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email);

        if (usuario == null)
            cadastrado = false;

        return cadastrado;
    }

    public void cadastrarUsuario(UsuarioDTO dto) throws ValidationException {
        if (!usuarioCadastrado(dto.getEmail())) {
            Usuario usuario = new Usuario();
            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());
            usuario.setSenha(dto.getSenha());

            Curso curso = cursoRepository.findCursoById
                    (ConverterHelper.convertStringToLong(dto.getCursoId()));

            usuario.setCurso(curso);

            usuarioRepository.saveAndFlush(usuario);
        }
    }
}
