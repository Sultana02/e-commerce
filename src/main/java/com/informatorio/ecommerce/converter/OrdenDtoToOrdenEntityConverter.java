package com.informatorio.ecommerce.converter;

import com.informatorio.ecommerce.domain.Carrito;
import com.informatorio.ecommerce.domain.Orden;
import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.dto.OrdenDto;
import com.informatorio.ecommerce.repository.ProductoRepository;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrdenDtoToOrdenEntityConverter implements Converter<OrdenDto, Orden> {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public OrdenDtoToOrdenEntityConverter(ProductoRepository productoRepository,
                                          UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Orden convert(OrdenDto ordenDto) {
        Carrito carrito = new Carrito();
        Orden orden = new Orden();
        Usuario usuario = new Usuario();
        carrito.setId(ordenDto.getCarritoId());
        usuario.setId(ordenDto.getUsuarioId());
        orden.setCarrito(carrito);
        orden.setUsuario(usuario);
        return orden;
    }
}
