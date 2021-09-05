package com.informatorio.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javassist.bytecode.LineNumberAttribute;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {
/*Esta clase almacena los productos y relaciona con un cliente en particular
    para almacenar los mismos para comprarlos*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Foreign Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;

    @NotBlank
    private String desdeDispositivo;

    @Transient
    private boolean activo;

    @Transient
    private BigDecimal total;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;


    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LineaDeCarrito> lineasDeCarrito = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        LineaDeCarrito linea = new LineaDeCarrito();
        this.total = linea.getSubtotal(); }

    public List<LineaDeCarrito> getLineaDeCarrito() {
        return lineasDeCarrito;
    }

    public void agregarLineaDeCarrito(LineaDeCarrito lineaDeCarrito) {
        lineasDeCarrito.add(lineaDeCarrito);
        lineaDeCarrito.setCarrito(this);
    }

    public void removerLineDeCarrito(LineaDeCarrito lineaDeCarrito) {
        lineasDeCarrito.remove(lineaDeCarrito);
        lineaDeCarrito.setCarrito(null);
    }
}
