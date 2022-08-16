package com.banco.solicitud.credito.otp.dominio.servicio;

import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.dominio.servicio.Interface.ConsultarOtp;
import com.banco.solicitud.credito.otp.dominio.servicio.Interface.ConsultarOtpDto;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;


public abstract class ServicioOtpDto {

  private ConsultarOtpDto consultarOtpDto;

  public ServicioOtpDto() {}

  public Otp ObtenerOtp(Otp otp){

   return consultarOtpDto.Consultar(otp);

  }

  public void setConsultarOtpDto(
      ConsultarOtpDto consultarOtpDto) {
    this.consultarOtpDto = consultarOtpDto;
  }
}
