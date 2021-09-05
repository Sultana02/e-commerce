package com.informatorio.ecommerce.converter;

import com.informatorio.ecommerce.domain.Usuario;
import com.informatorio.ecommerce.dto.UsuarioDto;
import com.informatorio.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import javax.persistence.Id;

@Component
    public class UsuarioDtoToUsuarioEntityConverter implements Converter<UsuarioDto, Usuario> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDtoToUsuarioEntityConverter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario convert(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setCiudad(usuarioDto.getCiudad());
        usuario.setProvincia(usuarioDto.getProvincia());
        usuario.setPais(usuarioDto.getPais());

        return usuario;
    }
}
