package com.projeto.saara.controllers;

import com.projeto.saara.dto.output.SelectBoxDTO;
import com.projeto.saara.helpers.ValidationException;
import com.projeto.saara.services.SelectBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/selectBox")
public class SelectBoxController {

    @Autowired
    private SelectBoxService selectBoxService;

    /**
     * @return lista de cursos existentes
     * @throws ValidationException
     */
    @GetMapping("/getCursos")
    public ResponseEntity<List<SelectBoxDTO>> getCursos() throws ValidationException {

        List<SelectBoxDTO> selectBoxDTOList = selectBoxService.getCursos();

        return ResponseEntity.ok().body(selectBoxDTOList);
    }

    /**
     * @param cursoId
     * @return lista de materias relacionadas ao curso
     * @throws ValidationException
     */
    @GetMapping("/getMaterias")
    public ResponseEntity<List<SelectBoxDTO>> getMaterias(String cursoId) throws
            ValidationException {

        List<SelectBoxDTO> selectBoxDTOList = selectBoxService.getMaterias(cursoId);

        return ResponseEntity.ok().body(selectBoxDTOList);
    }

    /**
     * @return lista de staus que podem ser selecionados para uma materia relacionada
     * ao usuario
     */
    @GetMapping("/getStatusMateria")
    public ResponseEntity<List<SelectBoxDTO>> getStatusMateria() throws ValidationException {

        List<SelectBoxDTO> selectBoxDTOList = selectBoxService.getStatusMateria();

        return ResponseEntity.ok().body(selectBoxDTOList);
    }

    /**
     * @param usuarioId
     * @return lista de lembretes do usuario
     * @throws ValidationException
     */
    @GetMapping("/getLembretes")
    public ResponseEntity<List<SelectBoxDTO>> getLembretes(String usuarioId) throws
            ValidationException {

        List<SelectBoxDTO> selectBoxDTOList = selectBoxService.getLembretes(usuarioId);

        return ResponseEntity.ok().body(selectBoxDTOList);
    }

    /**
     * @return lista de tipos de lembretes
     * @throws ValidationException
     */
    @GetMapping("/getLembreteType")
    public ResponseEntity<List<SelectBoxDTO>> getLembreteType() throws
            ValidationException {

        List<SelectBoxDTO> selectBoxDTOList = selectBoxService.getLembreteType();

        return ResponseEntity.ok().body(selectBoxDTOList);
    }

    /**
     * @param usuarioId
     * @return lista de materias que o usuario está cursando
     * @throws ValidationException
     */
    @GetMapping("/getMaterias/{id}")
    public ResponseEntity<List<SelectBoxDTO>> getMateriasUsuario(String usuarioId) throws
            ValidationException {

        List<SelectBoxDTO> selectBoxDTOList = selectBoxService.getMateriasUsuario(usuarioId);

        return ResponseEntity.ok().body(selectBoxDTOList);
    }

}
