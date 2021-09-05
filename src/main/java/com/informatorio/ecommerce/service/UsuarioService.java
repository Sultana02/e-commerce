package com.informatorio.ecommerce.service;

import com.informatorio.ecommerce.domain.Orden;
import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.dto.OrdenDto;
import com.informatorio.ecommerce.dto.UsuarioDto;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private Converter<UsuarioDto, Usuario> usuarioDtoUsuarioConverter;

    @Autowired
    private UsuarioRepository usuarioRepository;
    public boolean crearUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioDtoUsuarioConverter.convert(usuarioDto);
        usuario.setId(null);
        usuario.setFechaDeCreacion(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return true;
    }
    public boolean eliminarUsuario(Long usuarioId) {
        Optional<Usuario> oUsuario = usuarioRepository.findById(usuarioId);
        if (!oUsuario.isPresent())  return false;
        usuarioRepository.delete(oUsuario.get());
        return true;
    }

    public boolean editUsuario(Long usuarioId, UsuarioDto usuarioDto) {
        Optional<Usuario> oUsuario = usuarioRepository.findById(usuarioId);
        if (!oUsuario.isPresent())  return false;
        Usuario usuario = oUsuario.get();
        if (Objects.nonNull(usuarioDto.getNombre())){
            usuario.setNombre(usuarioDto.getNombre());
        }
        if (Objects.nonNull(usuarioDto.getApellido())) {
           usuario.setApellido(usuarioDto.getApellido());
        }
        if (Objects.nonNull(usuarioDto.getEmail())) {
            usuario.setEmail(usuarioDto.getEmail());
        }
        if (Objects.nonNull(usuarioDto.getCiudad())) {
            usuario.setCiudad(usuarioDto.getCiudad());
        }
        if (Objects.nonNull(usuarioDto.getProvincia())) {
            usuario.setProvincia(usuarioDto.getProvincia());
        }
        if (Objects.nonNull(usuarioDto.getPais())) {
            usuario.setPais(usuarioDto.getPais());
        }
        usuarioRepository.save(usuario);
        return true;
    }
}
