package com.projeto.saara.controllers;

import com.projeto.saara.dto.input.NewAulaDTO;
import com.projeto.saara.dto.input.NewNotaDTO;
import com.projeto.saara.dto.output.*;
import com.projeto.saara.dto.input.NewLembreteDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.exceptions.ValidationException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.Resposta;
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

    private final UsuarioService usuarioService;

    private final LembreteService lembreteService;

    private final UsuarioMateriaService usuarioMateriaService;

    private final NotaService notaService;

    private final CursoService cursoService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService,
                             LembreteService lembreteService,
                             UsuarioMateriaService usuarioMateriaService,
                             NotaService notaService, CursoService cursoService) {
        this.usuarioService = usuarioService;
        this.lembreteService = lembreteService;
        this.usuarioMateriaService = usuarioMateriaService;
        this.notaService = notaService;
        this.cursoService = cursoService;
    }

    /**
     * @param dto dados necessários para cadastrar o usuario
     * @return void
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> adicionarUsuario(@Valid @RequestBody NewUsuarioDTO dto) {
        usuarioService.cadastrarUsuario(dto);

        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    @PostMapping("/cadastrar/materias")
    public ResponseEntity<Object> cadastrarMaterias(NewUsuarioDTO dto, List<UsuarioMateriaDTO> idList) {
        usuarioService.cadastrarMaterias(dto, idList);

        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    /**
     * @param email email do usuario
     * @return dados do usuário
     */
    @GetMapping("/email")
    public ResponseEntity<Object> getUsuario(@RequestParam(value = "value") String email) {

        UsuarioDTO dto = usuarioService.buscarUsuario(email);
        return ResponseEntity.ok().body(new Resposta(0, "", dto));
    }

    /**
     * @param usuarioId propriedade de UsuarioDTO
     * @return vazio
     */
    @PostMapping("/adicionarLembrete")
    public ResponseEntity<Object> adicionarLembrete(
            String usuarioId, String materiaId, @Valid @RequestBody NewLembreteDTO dto) {

        usuarioService.adicionarLembrete(ConverterHelper.convertStringToLong(usuarioId), ConverterHelper.convertStringToLong(materiaId), dto);

        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    @GetMapping("/getLembretes")
    public ResponseEntity<Object> getLembretes(String usuarioId) {

        List<LembreteDTO> lembretesDto = lembreteService.getLembretes(ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(new Resposta(0, "", lembretesDto));
    }

    @GetMapping("/getLembretes/{lembreteId}")
    public ResponseEntity<Object> getLembrete(@PathVariable String lembreteId) {

        LembreteDTO dto = lembreteService.getLembrete(ConverterHelper.convertStringToLong(lembreteId));
        return ResponseEntity.ok().body(new Resposta(0, "", dto));
    }

    /**
     * @param usuarioId id do usuario
     * @return lista de matérias do usuário
     */
    @GetMapping("/getMaterias")
    public ResponseEntity<Object> getMaterias(String usuarioId) {

        List<UsuarioMateriaDTO> dto = usuarioMateriaService.getUsuarioMaterias(ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(new Resposta(0, "", dto));
    }

    /**
     * @param usuarioMateriaId usuariomateriaId
     * @return detalhes da matéria relacionadas ao usuario
     */
    @GetMapping("/getMaterias/{usuarioMateriaId}")
    public ResponseEntity<Object> getMateria(@PathVariable String usuarioMateriaId) {

        UsuarioMateriaDTO dto = usuarioMateriaService.getUsuarioMateria(ConverterHelper.convertStringToLong(usuarioMateriaId));
        return ResponseEntity.ok().body(new Resposta(0, "", dto));
    }

    /**
     * @param usuarioMateriaId usuarioMateriaId
     * @param notaId           notaid
     * @return nota do usuario relacionada a materia dele
     */
    @GetMapping("/getMaterias/{usuarioMateriaId}/getNota/{notaId}")
    public ResponseEntity<Object> getNota(@PathVariable String usuarioMateriaId, @PathVariable String notaId) {

        NotaDTO dto = notaService.getNota(ConverterHelper.convertStringToLong(usuarioMateriaId), ConverterHelper.convertStringToLong(notaId));
        return ResponseEntity.ok().body(new Resposta(0, "", dto));
    }

    /**
     * Adiciona nota e calcula média do usuario
     *
     * @param usuarioMateriaId usuariomateriaId
     * @param dto              NotaDTO
     * @return vazio
     */
    @PostMapping("/adicionarNota")
    public ResponseEntity<Object> adicionarNota(String usuarioMateriaId, @Valid
    @RequestBody NewNotaDTO dto) {

        notaService.adicionarNota(ConverterHelper.convertStringToLong(usuarioMateriaId), dto);
        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    /**
     * @param usuarioId usuarioid
     * @return Percentual de conclusão de curso baseado nas matérias que já foram cursadas
     */
    @GetMapping("/getPercentualCurso")
    public ResponseEntity<Object> getPercentualCurso(String usuarioId) {

        String percentualCurso = cursoService.getCursoConcluido(ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(new Resposta(0, "", percentualCurso));
    }

    @PutMapping("/atualizaStatusMateria/{usuarioMateriaId}")
    public ResponseEntity<Object> atualizaMateria(@PathVariable String usuarioMateriaId, UsuarioMateriaDTO dto) {

        usuarioMateriaService.atualizaStatusUsuarioMateria(ConverterHelper.convertStringToLong(usuarioMateriaId), dto);
        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    /**
     * @param usuarioId usuarioid
     * @param diaId     diaid
     * @return lista de aulas do dia selecionado
     */
    @GetMapping("/getAulasDia")
    public ResponseEntity<Object> getAulasDia(String usuarioId, String diaId) {

        List<AulaDTO> aulaDTOS = usuarioMateriaService.getAulasDia(ConverterHelper.convertStringToLong(usuarioId), ConverterHelper.convertStringToLong(diaId));
        return ResponseEntity.ok().body(new Resposta(0, "", aulaDTOS));
    }

    /**
     * @param usuarioMateriaId usuariomateriaID
     * @return lista de aulas da matéria selecionada
     */
    @GetMapping("/getAulasMateria")
    public ResponseEntity<Object> getAulasMateria(String usuarioMateriaId) {

        List<AulaDTO> aulaDTOS = usuarioMateriaService.getAulasMateria(ConverterHelper.convertStringToLong(usuarioMateriaId));
        return ResponseEntity.ok().body(new Resposta(0, "", aulaDTOS));
    }

    @PostMapping("/adicionarAula")
    public ResponseEntity<Object> adicionarAula(String usuarioMateriaId, @Valid
    @RequestBody NewAulaDTO dto) {
        usuarioMateriaService.adicionarAula(ConverterHelper.convertStringToLong(usuarioMateriaId), dto);
        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    @PutMapping("/atualizarAula/{aulaId}")
    public ResponseEntity<Object> adicionarAula(String aulaId, AulaDTO dto) {
        usuarioMateriaService.atualizarAula(ConverterHelper.convertStringToLong(aulaId), dto);
        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }
}
