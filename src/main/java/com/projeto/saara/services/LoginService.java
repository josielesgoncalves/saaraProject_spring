package com.projeto.saara.services;

import com.projeto.saara.dto.UsuarioDTO;
import com.projeto.saara.entities.Curso;
import com.projeto.saara.entities.Usuario;
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

    public UsuarioDTO buscarUsuario(String login, String senha) throws Exception {

        UsuarioDTO dto = null;
        final Usuario usuario = usuarioRepository.findUsuarioByEmail(login);

        if (usuario == null) {
            throw new Exception();
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

    public void cadastrarUsuario(UsuarioDTO dto) {
        if (!usuarioCadastrado(dto.getEmail())) {
            Usuario usuario = new Usuario();
            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());
            usuario.setSenha(dto.getSenha());

            //TODO: criar um convertStringToLong
//            Curso curso =
//
//            usuario.setCurso();

        }

    }
}
