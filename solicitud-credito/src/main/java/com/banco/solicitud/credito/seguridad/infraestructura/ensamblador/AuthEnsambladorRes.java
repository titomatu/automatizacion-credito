package com.banco.solicitud.credito.seguridad.infraestructura.ensamblador;

import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthEnsambladorRes {

  @Mapping( target = "access_token", source = "authResponseDto.access_token" )
  Auth  convierteDTOoModel(AuthResponseDto authResponseDto);

}
