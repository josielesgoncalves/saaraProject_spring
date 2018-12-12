package com.projeto.saara.controllers;

import com.projeto.saara.dto.input.NewAulaDTO;
import com.projeto.saara.dto.input.NewNotaDTO;
import com.projeto.saara.dto.output.*;
import com.projeto.saara.dto.input.NewLembreteDTO;
import com.projeto.saara.dto.input.NewUsuarioDTO;
import com.projeto.saara.entities.UsuarioMateria;
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

    @PostMapping("/cadastrar/materia")
    public ResponseEntity<Object> cadastrarMaterias(@RequestBody UsuarioMateriaDTO dto) {
        usuarioService.cadastrarMateria(dto);

        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    /**
     * @param email email do usuario
     * @return dados do usuário
     */
    @PostMapping("/login")
    public ResponseEntity<Object> getUsuario(@RequestParam(value = "email") String email,
                                             @RequestParam(value = "senha") String senha) {

        UsuarioDTO dto = usuarioService.logar(email, senha);
        return ResponseEntity.ok().body(new Resposta(0, "", dto));
    }

    /**
     * @param dto UsuarioDTO
     * @return vazio
     */
    @PostMapping("/adicionarLembrete")
    public ResponseEntity<Object> adicionarLembrete(@Valid @RequestBody NewLembreteDTO dto) {

        usuarioService.adicionarLembrete(dto);

        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    @PostMapping("/getLembretes")
    public ResponseEntity<Object> getLembretes(@RequestParam(value = "usuarioId") String usuarioId) {

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
    @PostMapping("/getMaterias")
    public ResponseEntity<Object> getMaterias(@RequestParam(value = "usuarioId") String usuarioId) {

        List<UsuarioMateriaDTO> dto = usuarioMateriaService.getUsuarioMaterias(ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(new Resposta(0, "", dto));
    }

    /**
     * @param usuarioMateriaId usuariomateriaId
     * @return detalhes da matéria relacionadas ao usuario
     */
    @GetMapping("/getMateria")
    public ResponseEntity<Object> getMateria(@RequestParam(value = "usuarioMateriaId") String usuarioMateriaId) {

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
    @GetMapping("/getAulasMateria/{usuarioMateriaId}")
    public ResponseEntity<List<AulaDTO>> getAulasMateria(String usuarioMateriaId) {

        List<AulaDTO> aulaDTOS = usuarioMateriaService.getAulasMateria(ConverterHelper.convertStringToLong(usuarioMateriaId));
        return ResponseEntity.ok().body(aulaDTOS);
    }

    @PostMapping("/adicionarAula")
    public ResponseEntity<Object> adicionarAula(@Valid @RequestBody NewAulaDTO dto) {
        usuarioMateriaService.adicionarAula(dto);
        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    @PutMapping("/atualizarAula/{aulaId}")
    public ResponseEntity<Object> adicionarAula(String aulaId, AulaDTO dto) {
        usuarioMateriaService.atualizarAula(ConverterHelper.convertStringToLong(aulaId), dto);
        return ResponseEntity.ok().body(new Resposta(0, "", ""));
    }

    @GetMapping("/getAulas/{usuarioId}")
    public ResponseEntity<List<AulaDTO>> getAulas(String usuarioId) {

        List<AulaDTO> aulaDTOS = usuarioMateriaService.getAulas(ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(aulaDTOS);
    }

    @GetMapping("/getMateriasCursadas/{usuarioId}")
    public ResponseEntity<List<UsuarioMateriaDTO>> getMateriasCursadas(String usuarioId) {

        List<UsuarioMateriaDTO> materiasDTOS = usuarioMateriaService.getMateriasCursadas
                (ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(materiasDTOS);
    }

    @GetMapping("/getMateriasCursando/{usuarioId}")
    public ResponseEntity<List<UsuarioMateriaDTO>> getMateriasCursando(String usuarioId) {

        List<UsuarioMateriaDTO> materiasDTOS = usuarioMateriaService.getMateriasCursando
                (ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(materiasDTOS);
    }

    @GetMapping("/getMateriasNaoCursadas/{usuarioId}")
    public ResponseEntity<List<UsuarioMateriaDTO>> getMateriasNaoCursadas(String usuarioId) {

        List<UsuarioMateriaDTO> materiasDTOS = usuarioMateriaService.getMateriasNaoCursadas
                (ConverterHelper.convertStringToLong(usuarioId));
        return ResponseEntity.ok().body(materiasDTOS);
    }
}
