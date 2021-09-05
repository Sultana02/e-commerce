package com.informatorio.ecommerce.controller;

import com.informatorio.ecommerce.domain.Carrito;
import com.informatorio.ecommerce.domain.Producto;
import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.exception.CarritoException;
import com.informatorio.ecommerce.repository.ProductoRepository;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /*Crear producto*/
    @PostMapping("/createProducto")
    public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto) throws CarritoException {
        return new ResponseEntity<>(productoRepository.save(producto), HttpStatus.CREATED);
    }
    /*Eliminar producto*/
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable("id") Long productoId) {
        Producto producto = productoRepository.getById(productoId);
        productoRepository.delete(producto);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> buscarProducto(@RequestParam(value = "comienzaCon", required = false) String comienzaCon) throws CarritoException {
        if (comienzaCon != null) {
            return new ResponseEntity<>(productoRepository.buscarPorNombreQueComienza(comienzaCon), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
    }
}
