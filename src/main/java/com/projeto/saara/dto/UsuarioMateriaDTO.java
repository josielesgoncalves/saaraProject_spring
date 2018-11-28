package com.projeto.saara.dto;

public class UsuarioMateriaDTO {

    private String usuarioMateriaId;

    private String materiaId;

    private String statusId;

    public  UsuarioMateriaDTO(){}

    public String getId() {
        return usuarioMateriaId;
    }

    public void setId(String usuarioMateriaId) {
        this.usuarioMateriaId= usuarioMateriaId;
    }

    public String getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(String materiaId) {
        this.materiaId = materiaId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}
