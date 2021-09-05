package com.informatorio.ecommerce.controller;

import com.informatorio.ecommerce.domain.Carrito;
import com.informatorio.ecommerce.domain.LineaDeCarrito;
import com.informatorio.ecommerce.domain.Producto;
import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.dto.CarritoDto;
import com.informatorio.ecommerce.exception.CarritoException;
import com.informatorio.ecommerce.repository.CarritoRepository;
import com.informatorio.ecommerce.repository.ProductoRepository;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import com.informatorio.ecommerce.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
public class CarritoController {


    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    private CarritoService carritoService;

    public CarritoController(UsuarioRepository usuarioRepository, CarritoRepository carritoRepository,
                             ProductoRepository productoRepository) {
        System.out.println("entrar");
        this.usuarioRepository = usuarioRepository;
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }

    @GetMapping("/carrito")
    public ResponseEntity<?> buscarCarrito() throws CarritoException {
        return new ResponseEntity<>(carritoRepository.findAll(), HttpStatus.OK);
    }

    /*crear carrito*/
    @PostMapping("/usuario/{id}/carrito")
    public ResponseEntity<?> createCarrito(@PathVariable("id") Long userId,
                                           @Valid @RequestBody Carrito carrito) {
        Usuario usuario = usuarioRepository.getById(userId);
        carrito.setUsuario(usuario);
        return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
    }

    /*modificar carrito*/
    @PutMapping("/carrito/{idCarrito}")
    public ResponseEntity<?> modificarCarrito(@PathVariable("idCarrito") Long idCarrito,
                                             @Valid @RequestBody CarritoDto carritoDto) {
        return new ResponseEntity<>(carritoService.editCarrito(idCarrito, carritoDto), HttpStatus.OK);
    }

    /*eliminar carrito*/
    @DeleteMapping("/usuario/{id}/carrito/{idCarrito}")
    public ResponseEntity<?> borrarProducto(@PathVariable("id") Long userId,
                                            @PathVariable("idCarrito") Long idCarrito){
        Carrito carrito = carritoRepository.getById(idCarrito);
        carritoRepository.delete(carrito);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
