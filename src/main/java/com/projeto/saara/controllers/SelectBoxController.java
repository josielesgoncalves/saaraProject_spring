package com.projeto.saara.controllers;

import com.projeto.saara.dto.output.SelectBoxDTO;
import com.projeto.saara.exceptions.ValidationException;
import com.projeto.saara.helpers.ConverterHelper;
import com.projeto.saara.helpers.Resposta;
import com.projeto.saara.services.SelectBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/selectBox")
public class SelectBoxController {

    private final SelectBoxService selectBoxService;

    @Autowired
    public SelectBoxController(SelectBoxService selectBoxService) {
        this.selectBoxService = selectBoxService;
    }

    /**
     * @return lista de cursos existentes
     */
    @GetMapping("/getCursos")
    public ResponseEntity<List<SelectBoxDTO>> getCursos() {

        List<SelectBoxDTO> selectBoxDTOList = selectBoxService.getCursos();

        return ResponseEntity.ok().body(selectBoxDTOList);
    }

    /**
     * @param cursoId id do curso
     * @return lista de materias relacionadas ao curso
     */
    @GetMapping("/getMaterias")
    public ResponseEntity<Object> getMaterias(String cursoId)
    {
        return ResponseEntity.ok().body(new Resposta(0, "", selectBoxService.getMaterias(ConverterHelper.convertStringToLong(cursoId))));
    }

    /**
     * @return lista de staus que podem ser selecionados para uma materia relacionada
     * ao usuario
     */
    @GetMapping("/getStatusMateria")
    public ResponseEntity<Object> getStatusMateria()
    {
        return ResponseEntity.ok().body(new Resposta(0, "", selectBoxService.getStatusMateria()));
    }

    /**
     * @param usuarioId id do usuario
     * @return lista de lembretes do usuario
     */
    @GetMapping("/getLembretes")
    public ResponseEntity<Object> getLembretes(String usuarioId)
    {
        return ResponseEntity.ok().body(new Resposta(0, "", selectBoxService.getLembretes(ConverterHelper.convertStringToLong(usuarioId))));
    }

    /**
     * @return lista de tipos de lembretes
     */
    @GetMapping("/getLembreteType")
    public ResponseEntity<Object> getLembreteType()
    {
        return ResponseEntity.ok().body(new Resposta(0, "", selectBoxService.getLembreteType()));
    }

    /**
     * @param usuarioId id do usuario
     * @return lista de materias que o usuario está cursando
     */
    @GetMapping("/getMaterias/{usuarioId}")
    public ResponseEntity<Object> getMateriasUsuario(@PathVariable String usuarioId)
    {
        return ResponseEntity.ok().body(new Resposta(0, "", selectBoxService.getMateriasUsuario(ConverterHelper.convertStringToLong(usuarioId))));
    }

    //TODO: criar selectBox para selecionar dia, mes, ano, hora, min e seg
    //TODO: pode ser uma caixa com os valores e os valores serão recebbidos como string e
    //TODO: convertidos em Calendar
}
