package com.projeto.saara.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioMateriaDTO implements Serializable {

    private String usuarioMateriaId;

    private MateriaDTO materiaDTO;

    private UsuarioDTO usuarioDTO;

    private List<NotaDTO> notaDTOList;

    private String nota;

    private String notaType;

    private String peso;

    private String statusId;

    private String media;

}
