package com.projeto.saara.controllers;

import com.projeto.saara.dto.NotaDTO;
import com.projeto.saara.dto.UsuarioDTO;
import com.projeto.saara.dto.UsuarioMateriaDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.dto.output.LembreteDTO;
import com.projeto.saara.dto.output.MateriaDTO;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /**
     * @param dto dados necessários para cadastrar o usuario
     * @return void
     * @throws ValidationException
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<Void> adicionarUsuario(@Valid @RequestBody NewUsuarioDTO dto)
            throws ValidationException {
        usuarioService.cadastrarUsuario(dto);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cadastrar/materias")
    public ResponseEntity<Void> cadastrarMaterias(NewUsuarioDTO dto,
                                                  List<UsuarioMateriaDTO> idList)
            throws ValidationException {
        usuarioService.cadastrarMaterias(dto, idList);

        return ResponseEntity.noContent().build();
    }

    /**
     * @param email email do usuario
     * @return dados do usuário
     * @throws ValidationException
     */
    @GetMapping("/email")
    public ResponseEntity<UsuarioDTO> getUsuario(@RequestParam(value = "value")
                                                         String email)
            throws ValidationException {

        UsuarioDTO dto = usuarioService.buscarUsuario(email);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * @param usuarioId id do usuario
     * @return lista de matérias do usuário
     */
    @GetMapping("/{id}/getMaterias")
    public ResponseEntity<List<MateriaDTO>> getMaterias(String usuarioId) {
        return null;
    }

    /**
     * @param usuarioId
     * @param materiaId
     * @return detalhes da matéria relacionadas ao usuario
     */
    @GetMapping("/{id}/getMaterias/{id}")
    public ResponseEntity<MateriaDTO> getMateria(String usuarioId, String materiaId) {
        return null;
    }

    /**
     * @param usuarioId
     * @param materiaId
     * @param notaId
     * @return nota do usuario relacionada a materia dele
     */
    @GetMapping("/{id}/getMaterias/{id}/getNota/{id}")
    public ResponseEntity<NotaDTO> getNota(String usuarioId, String materiaId, String
            notaId) {
        return null;
    }

    @GetMapping("/{id}/getLembretes")
    public ResponseEntity<List<LembreteDTO>> getLembretes(String usuarioId) {
        return null;
    }

    @GetMapping("/{id}/getLembretes/{id}")
    public LembreteDTO getLembrete(String usuarioId, String lembreteId) {
        return null;
    }
}
