package com.informatorio.ecommerce.controller;

import com.informatorio.ecommerce.domain.Carrito;
import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.dto.UsuarioDto;
import com.informatorio.ecommerce.repository.CarritoRepository;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import com.informatorio.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /*Alta*/
    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDto), HttpStatus.CREATED);
    }
    /*Baja*/
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.eliminarUsuario(id), HttpStatus.OK);
    }
    /*Modificar*/
    @PutMapping("{id}")
    public ResponseEntity<?> editUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) {
        return new ResponseEntity<>(usuarioService.editUsuario(id, usuarioDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosUsuarios(
            @RequestParam(name = "fechaDeCreacion", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido", required = false) String apellido,
            @RequestParam(name = "ciudad", required = false) String ciudad){


        if (fechaDeCreacion != null) {
            return new ResponseEntity<>(usuarioRepository.findByFechaDeCreacionAfter(fechaDeCreacion.atStartOfDay()), HttpStatus.OK);
        } else if (Objects.nonNull(ciudad)) {
            System.out.println("Hola Mundo");
            return new ResponseEntity<>(usuarioRepository.findByCiudadContaining(ciudad), HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }
}
