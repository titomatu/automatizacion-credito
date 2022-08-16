package com.banco.solicitud.credito.otp.infraestructura.ensamblador;

import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;



@Mapper(componentModel = "spring")
public interface OtpEnsamblador {

  @Mappings ({@Mapping( target = "password", source = "otpResponseDto.mensaje" ),
      @Mapping( target = "otpRequestedTime" , ignore = true)})
  Otp convierteDTOaModelo(OtpResponseDto otpResponseDto);





  }

