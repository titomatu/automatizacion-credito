package com.banco.solicitud.credito.seguridad.infraestructura.adaptador;

import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.seguridad.dominio.servicio.Interface.ConsultarTokenDto;
import com.banco.solicitud.credito.seguridad.dominio.servicio.ServicioAuthDto;
import com.banco.solicitud.credito.seguridad.infraestructura.ensamblador.AuthEnsambladorReq;
import com.banco.solicitud.credito.seguridad.infraestructura.ensamblador.AuthEnsambladorRes;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.cliente.TokenRestClien;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthRequestDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthAdaptadorDto extends ServicioAuthDto {

  @Autowired
  Auth auth;
  @Autowired
  AuthRequestDto authRequestDto;

  private final AuthEnsambladorReq authEnsambladorReq;
  private final AuthEnsambladorRes authEnsambladorRes;
  private final TokenRestClien tokenRestClien;

  public AuthAdaptadorDto(Auth auth,
      AuthRequestDto authRequestDto,
      AuthEnsambladorReq authEnsambladorReq,
      AuthEnsambladorRes authEnsambladorRes,
      TokenRestClien tokenRestClien) {
    this.auth = auth;
    this.authRequestDto = authRequestDto;
    this.authEnsambladorReq = authEnsambladorReq;
    this.authEnsambladorRes = authEnsambladorRes;
    this.tokenRestClien = tokenRestClien;
    super.setConsultarTokenDto(consultarTokenDto(auth));
  }

  public ConsultarTokenDto consultarTokenDto(Auth auth){

    return (Auth auth1) -> {
      try{

        return authEnsambladorRes.convierteDTOoModel(tokenRestClien.authToken(auth.getUsername(),auth.getPassword()));
      }catch (FeignException.NotFound e){
          return null;

      }

    };
  }
}
