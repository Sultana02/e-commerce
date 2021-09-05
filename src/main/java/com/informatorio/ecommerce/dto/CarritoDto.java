package com.informatorio.ecommerce.dto;

import com.informatorio.ecommerce.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class CarritoDto implements Serializable {

    @Autowired
    private Long Id;
    private String desdeDispositivo;
    private boolean activo;
    private Long usuarioId;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getDesdeDispositivo() {
        return desdeDispositivo;
    }

    public void setDesdeDispositivo(String desdeDispositivo) {
        this.desdeDispositivo = desdeDispositivo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
