package com.informatorio.ecommerce.service;

import com.informatorio.ecommerce.domain.Orden;
import com.informatorio.ecommerce.dto.OrdenDto;
import com.informatorio.ecommerce.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class OrdenService {
    @Autowired
    private Converter<OrdenDto, Orden> ordenDtoToOrdenConverter;
    @Autowired
    private OrdenRepository ordenRepository;

    public OrdenService(Converter<OrdenDto, Orden> ordenDtoToOrdenConverter,
                        OrdenRepository OrdenRepository) {
        this.ordenDtoToOrdenConverter = ordenDtoToOrdenConverter;
        this.ordenRepository = ordenRepository;
    }

    public boolean crearOrden(OrdenDto ordenDto) {
        Orden orden = ordenDtoToOrdenConverter.convert(ordenDto);
        orden.setFecha_creacion(LocalDateTime.now());
        ordenRepository.save(orden);
        return true;
    }
}
