package com.banco.solicitud.credito.seguridad.infraestructura.ensamblador;

import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthEnsambladorReq {

  @Mappings({
      @Mapping( target = "username", source = "auth.username" ),
      @Mapping( target = "password" , source = "auth.password")
  })
  AuthRequestDto convierteModeloaDTO(Auth auth);

}
