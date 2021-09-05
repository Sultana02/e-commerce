package com.informatorio.ecommerce.converter;

import com.informatorio.ecommerce.domain.Carrito;
import com.informatorio.ecommerce.domain.LineaDeCarrito;
import com.informatorio.ecommerce.domain.Producto;
import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.dto.CarritoDto;
import com.informatorio.ecommerce.repository.ProductoRepository;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import javax.persistence.Convert;
import javax.persistence.Id;

@Component
public class CarritoDtoToCarritoEntityConverter implements Converter<CarritoDto, Carrito> {

    private final UsuarioRepository usuarioRepository;

    public CarritoDtoToCarritoEntityConverter(
                                          UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Carrito convert(CarritoDto carritoDto) {
        Carrito carrito = new Carrito();
        LineaDeCarrito lineaDeCarrito = new LineaDeCarrito();
        Usuario usuario = new Usuario();
        carrito.setId(carritoDto.getId());
        usuario.setId(carritoDto.getUsuarioId());
        carrito.setUsuario(usuario);
        carrito.agregarLineaDeCarrito(lineaDeCarrito);
        return carrito;
    }
}
