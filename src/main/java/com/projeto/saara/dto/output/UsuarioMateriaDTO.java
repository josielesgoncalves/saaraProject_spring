package com.projeto.saara.dto.output;

import com.projeto.saara.dto.output.NotaDTO;

import java.util.List;

public class UsuarioMateriaDTO {

    private String usuarioMateriaId;

    private String materiaId;

    private String usuarioId;

    private List<NotaDTO> notaDTOList;

    private String statusId;

    private String media;

    public String getUsuarioMateriaId() {
        return usuarioMateriaId;
    }

    public void setUsuarioMateriaId(String usuarioMateriaId) {
        this.usuarioMateriaId = usuarioMateriaId;
    }

    public String getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(String materiaId) {
        this.materiaId = materiaId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<NotaDTO> getNotaDTOList() {
        return notaDTOList;
    }

    public void setNotaDTOList(List<NotaDTO> notaDTOList) {
        this.notaDTOList = notaDTOList;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
