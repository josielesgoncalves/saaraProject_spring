package com.projeto.saara.services;

import com.projeto.saara.dto.UsuarioDTO;
import com.projeto.saara.dto.UsuarioMateriaDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.entities.Curso;
import com.projeto.saara.entities.Materia;
import com.projeto.saara.entities.Usuario;
import com.projeto.saara.entities.UsuarioMateria;
import com.projeto.saara.enums.StatusEnum;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.repositories.interfaces.CursoRepository;
import com.projeto.saara.repositories.interfaces.MateriaRepository;
import com.projeto.saara.repositories.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    public UsuarioDTO buscarUsuario(String login) throws ValidationException {

        UsuarioDTO dto = null;
        final Usuario usuario = usuarioRepository.findUsuarioByEmail(login);

        if (usuario == null) {
            throw new ValidationException();
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

    public void cadastrarUsuario(NewUsuarioDTO dto) throws ValidationException {
        if (!usuarioCadastrado(dto.getEmail())) {
            Usuario usuario = dto.criarNovoUsuario();
            Curso curso = cursoRepository.findCursoById
                    (ConverterHelper.convertStringToLong(dto.getCursoId()));

            usuario.setCurso(curso);

            usuarioRepository.saveAndFlush(usuario);
        }
    }

    /**
     * Salva os dados do usuario com as materias cursadas ou cursando
     * @param usuarioDTO
     * @param dtoList lista de dados da materia selecionada
     * @throws ValidationException
     */
    public void cadastrarMaterias(NewUsuarioDTO usuarioDTO, List<UsuarioMateriaDTO> dtoList)
            throws
            ValidationException {

        Usuario usuario = usuarioRepository.findUsuarioByEmail(usuarioDTO.getEmail());
        if (usuario == null) {
            throw new ValidationException();
        }

        List<UsuarioMateria> usuarioMaterias = new ArrayList<>();

        //Nesse caso o usuario pode não salvar as matérias que não cursou
        for (UsuarioMateriaDTO item : dtoList) {
            Materia materia = materiaRepository.getMateriaById(ConverterHelper
                    .convertStringToLong(item.getMateriaId()));
            StatusEnum status = ConverterHelper.convertStringToStatusEnum(item
                    .getStatusId());
            UsuarioMateria usuarioMateria = new UsuarioMateria();
            usuarioMateria.setMateria(materia);
            usuarioMateria.setStatus(status);

            usuarioMaterias.add(usuarioMateria);
        }
        usuario.setMaterias(usuarioMaterias);

        usuarioRepository.saveAndFlush(usuario);
    }
}
