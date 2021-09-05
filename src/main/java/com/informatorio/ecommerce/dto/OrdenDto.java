package com.informatorio.ecommerce.dto;

import com.informatorio.ecommerce.domain.Carrito;
import com.informatorio.ecommerce.domain.Usuario;

import java.io.Serializable;

public class OrdenDto implements Serializable{

    private Long carritoId;
    private Long usuarioId;
    private String observacion;

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
