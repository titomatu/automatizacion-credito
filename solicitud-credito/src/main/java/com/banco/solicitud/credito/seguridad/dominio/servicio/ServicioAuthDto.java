package com.banco.solicitud.credito.seguridad.dominio.servicio;

import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.seguridad.dominio.servicio.Interface.ConsultarTokenDto;

public abstract class ServicioAuthDto {

  private ConsultarTokenDto consultarTokenDto;

  public ServicioAuthDto() {
  }

  public Auth ObtenerAuth(Auth auth){
    return consultarTokenDto.consultarToken(auth);

  }

  public void setConsultarTokenDto(
      ConsultarTokenDto consultarTokenDto) {
    this.consultarTokenDto = consultarTokenDto;
  }
}
