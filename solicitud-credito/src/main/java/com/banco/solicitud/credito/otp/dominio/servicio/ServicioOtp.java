package com.banco.solicitud.credito.otp.dominio.servicio;

import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.dominio.servicio.Interface.ConsultarOtp;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;


public abstract class ServicioOtp {

  private ConsultarOtp consultarOtp;

  public ServicioOtp() {}

  public Otp ObtenerOtpDto(OtpRequestDto otpRequestDto){

   return consultarOtp.Consultar(otpRequestDto);

  }

  public void setConsultarOtp(
      ConsultarOtp consultarOtp) {
    this.consultarOtp = consultarOtp;
  }

}
