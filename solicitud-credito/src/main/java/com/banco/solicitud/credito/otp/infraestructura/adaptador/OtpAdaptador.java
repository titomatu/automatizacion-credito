package com.banco.solicitud.credito.otp.infraestructura.adaptador;

import com.banco.solicitud.credito.otp.dominio.servicio.Interface.ConsultarOtp;
import com.banco.solicitud.credito.otp.dominio.servicio.ServicioOtp;
import com.banco.solicitud.credito.otp.infraestructura.ensamblador.OtpEnsamblador;
import com.banco.solicitud.credito.otp.infraestructura.rest.cliente.OtpRestClien;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtpAdaptador extends ServicioOtp {


  @Autowired
  OtpRequestDto otpRequestDto;
  private final OtpEnsamblador otpEnsamblador;
  private final OtpRestClien otpRestClien;




  public OtpAdaptador(OtpEnsamblador otpEnsamblador, OtpRestClien otpRestClien, OtpRequestDto otpRequestDto) {
    this.otpEnsamblador = otpEnsamblador;
    this.otpRestClien = otpRestClien;
    this.otpRequestDto = otpRequestDto;
    super.setConsultarOtp(consultarOtp(otpRequestDto));
  }

  public ConsultarOtp consultarOtp(OtpRequestDto otpRequestDtoP){

    return (OtpRequestDto otpRequestDto) -> {
      try{
        return otpEnsamblador.convierteDTOaModelo(otpRestClien.respuetaOTP(otpRequestDtoP));

      }catch (FeignException.NotFound e){
        return null;
      }

    };
  }

}
