package com.projeto.saara.dto.output;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UsuarioMateriaDTO implements Serializable {

    private String usuarioMateriaId;

    private String materiaId;

    private String usuarioId;

    private List<NotaDTO> notaDTOList;

    private String statusId;

    private String media;

}
