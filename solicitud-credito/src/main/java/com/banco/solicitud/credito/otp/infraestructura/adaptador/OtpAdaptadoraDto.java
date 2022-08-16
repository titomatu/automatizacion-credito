package com.banco.solicitud.credito.otp.infraestructura.adaptador;

import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.dominio.servicio.Interface.ConsultarOtp;
import com.banco.solicitud.credito.otp.dominio.servicio.Interface.ConsultarOtpDto;
import com.banco.solicitud.credito.otp.dominio.servicio.ServicioOtp;
import com.banco.solicitud.credito.otp.dominio.servicio.ServicioOtpDto;
import com.banco.solicitud.credito.otp.infraestructura.ensamblador.OtpEnsamblador;
import com.banco.solicitud.credito.otp.infraestructura.ensamblador.OtpEnsambladoraDto;
import com.banco.solicitud.credito.otp.infraestructura.rest.cliente.OtpRestClien;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtpAdaptadoraDto extends ServicioOtpDto {


  @Autowired
  OtpRequestDto otpRequestDto;
  @Autowired
  Otp otp;
  private final OtpEnsamblador otpEnsamblador;
  private final OtpEnsambladoraDto otpEnsambladoraDto;
  private final OtpRestClien otpRestClien;




  public OtpAdaptadoraDto(OtpEnsamblador otpEnsamblador, OtpRestClien otpRestClien, OtpRequestDto otpRequestDto, OtpEnsambladoraDto otpEnsambladoraDto,Otp otp) {
    this.otpEnsamblador = otpEnsamblador;
    this.otpRestClien = otpRestClien;
    this.otpRequestDto = otpRequestDto;
    this.otpEnsambladoraDto = otpEnsambladoraDto;
    this.otp = otp;
    super.setConsultarOtpDto(consultarOtp(otp));
  }

  public ConsultarOtpDto consultarOtp(Otp otp){

    return (Otp otp1) -> {
      try{
        return otpEnsamblador.convierteDTOaModelo(otpRestClien.respuetaOTP(otpEnsambladoraDto.convierteModeloaDTO(otp)));

      }catch (FeignException.NotFound e){
        return null;

      }

    };
  }

}
