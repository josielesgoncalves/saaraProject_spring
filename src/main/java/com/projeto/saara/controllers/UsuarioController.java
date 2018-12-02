package com.projeto.saara.controllers;

import com.projeto.saara.dto.input.NewNotaDTO;
import com.projeto.saara.dto.output.*;
import com.projeto.saara.dto.input.NewLembreteDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LembreteService lembreteService;

    @Autowired
    private UsuarioMateriaService usuarioMateriaService;

    @Autowired
    private NotaService notaService;

    @Autowired
    private CursoService cursoService;

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
     * @param usuarioId propriedade de UsuarioDTO
     * @return
     * @throws ValidationException
     */
    @PostMapping("/adicionarLembrete")
    public ResponseEntity<Void> adicionarLembrete(String usuarioId, String materiaId,
                                                  @Valid @RequestBody NewLembreteDTO dto)
            throws ValidationException, ParseException {

        usuarioService.adicionarLembrete(usuarioId, materiaId, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getLembretes")
    public ResponseEntity<List<LembreteDTO>> getLembretes(String usuarioId)
            throws ValidationException {

        List<LembreteDTO> lembretesDto = lembreteService.getLembretes(usuarioId);
        return ResponseEntity.ok().body(lembretesDto);
    }

    @GetMapping("/getLembretes/{id}")
    public ResponseEntity<LembreteDTO> getLembrete(String lembreteId)
            throws ValidationException {

        LembreteDTO dto = lembreteService.getLembrete(lembreteId);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * @param usuarioId id do usuario
     * @return lista de matérias do usuário
     */
    @GetMapping("/getMaterias")
    public ResponseEntity<List<UsuarioMateriaDTO>> getMaterias(String usuarioId)
            throws ValidationException {

        List<UsuarioMateriaDTO> dto = usuarioMateriaService.getUsuarioMaterias(usuarioId);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * @param usuarioMateriaId
     * @return detalhes da matéria relacionadas ao usuario
     */
    @GetMapping("/getMaterias/{id}")
    public ResponseEntity<UsuarioMateriaDTO> getMateria(String usuarioMateriaId)
            throws ValidationException {

        UsuarioMateriaDTO dto = usuarioMateriaService.getUsuarioMateria(usuarioMateriaId);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * @param usuarioMateriaId
     * @param notaId
     * @return nota do usuario relacionada a materia dele
     */
    @GetMapping("/getMaterias/{id}/getNota/{id}")
    public ResponseEntity<NotaDTO> getNota(String usuarioMateriaId, String notaId)
            throws ValidationException {

        NotaDTO dto = notaService.getNota(usuarioMateriaId, notaId);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * Adiciona nota e calcula média do usuario
     *
     * @param usuarioMateriaId
     * @param dto
     * @return
     * @throws ValidationException
     */
    @PostMapping("/adicionarNota")
    public ResponseEntity<Void> adicionarNota(String usuarioMateriaId, @Valid
    @RequestBody NewNotaDTO dto) throws ValidationException {

        notaService.adicionarNota(usuarioMateriaId, dto);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param usuarioId
     * @return Percentual de conclusão de curso baseado nas matérias que já foram cursadas
     * @throws ValidationException
     */
    @GetMapping("/getPercentualCurso")
    public ResponseEntity<String> getPercentualCurso(String usuarioId)
            throws ValidationException {

        String percentualCurso = cursoService.getCursoConcluido(usuarioId);
        return ResponseEntity.ok().body(percentualCurso);
    }

    @PutMapping("/atualizaStatusMateria/{id}")
    public ResponseEntity<Void> atualizaMateria(String usuarioMateriaId,
                                                UsuarioMateriaDTO dto)
            throws ValidationException {

        usuarioMateriaService.atualizaStatusUsuarioMateria(usuarioMateriaId, dto);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param usuarioId
     * @param diaId
     * @return lista de aulas do dia selecionado
     * @throws ValidationException
     */
    @GetMapping("/getAulasDia")
    public ResponseEntity<List<AulaDTO>> getAulasDia(String usuarioId, String diaId)
            throws ValidationException {

        List<AulaDTO> aulaDTOS = usuarioMateriaService.getAulasDia(usuarioId, diaId);
        return ResponseEntity.ok().body(aulaDTOS);
    }

    /**
     * @param usuarioMateriaId
     * @return lista de aulas da matéria selecionada
     * @throws ValidationException
     */
    @GetMapping("/getAulasMateria")
    public ResponseEntity<List<AulaDTO>> getAulasMateria(String usuarioMateriaId)
            throws ValidationException {

        List<AulaDTO> aulaDTOS = usuarioMateriaService.getAulasMateria(usuarioMateriaId);
        return ResponseEntity.ok().body(aulaDTOS);
    }
}
