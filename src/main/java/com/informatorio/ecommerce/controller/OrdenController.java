package com.informatorio.ecommerce.controller;

import com.informatorio.ecommerce.domain.Orden;
import com.informatorio.ecommerce.dto.OrdenDto;
import com.informatorio.ecommerce.repository.OrdenRepository;
import com.informatorio.ecommerce.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController

public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private OrdenRepository ordenRepository;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping(value = "/orden")
    public ResponseEntity<?> getOrden(){
        return new ResponseEntity<>(ordenRepository.findAll(), HttpStatus.OK);
    }

    /*Alta,Baja y Modificacion Orden*/
    @PostMapping(value = "/orden")
    public ResponseEntity<?> createOrden(@Valid @RequestBody OrdenDto ordenDto) {
        return new ResponseEntity<>(ordenService.crearOrden(ordenDto), HttpStatus.CREATED);
    }

}