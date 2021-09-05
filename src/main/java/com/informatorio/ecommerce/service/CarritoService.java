package com.informatorio.ecommerce.service;

import com.informatorio.ecommerce.converter.CarritoDtoToCarritoEntityConverter;
import com.informatorio.ecommerce.domain.Carrito;
import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.dto.CarritoDto;
import com.informatorio.ecommerce.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarritoService {
    @Autowired
    private Converter<CarritoDto, Carrito> carritoDtoCarritoConverter;
    @Autowired
    private CarritoRepository carritoRepository;

    public CarritoService(Converter<CarritoDto, Carrito> carritoDtoCarritoConverter,
                        CarritoRepository CarritoRepository) {
        this.carritoDtoCarritoConverter = carritoDtoCarritoConverter;
        this.carritoRepository = carritoRepository;
    }

    public boolean editCarrito(Long carritoId, CarritoDto carritoDto) {
        Optional<Carrito> oCarrito = carritoRepository.findById(carritoId);
        //Carrito carrito = carritoDtoCarritoConverter.convert(carritoDto);
        if (!oCarrito.isPresent())  return false;
        Carrito carrito = oCarrito.get();

        if (Objects.nonNull(carritoDto.getDesdeDispositivo())){
            carrito.setDesdeDispositivo(carritoDto.getDesdeDispositivo());
        }
        if (Objects.nonNull(carritoDto.getActivo())) {
            carrito.setActivo(carritoDto.getActivo());
        }
        carritoRepository.save(carrito);
        return true;
    }
}