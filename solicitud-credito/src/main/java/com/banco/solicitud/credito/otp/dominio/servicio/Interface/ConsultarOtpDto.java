package com.banco.solicitud.credito.otp.dominio.servicio.Interface;

import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;

@FunctionalInterface
public interface ConsultarOtpDto {

  Otp Consultar(Otp otp);

}
