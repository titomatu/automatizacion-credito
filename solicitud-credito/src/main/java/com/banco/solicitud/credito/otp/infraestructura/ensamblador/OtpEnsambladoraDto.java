package com.banco.solicitud.credito.otp.infraestructura.ensamblador;

import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface OtpEnsambladoraDto {

@Mappings({
@Mapping( target = "username", source = "otp.username" ),
    @Mapping( target = "correo", source = "otp.correo" ),
})
  OtpRequestDto convierteModeloaDTO(Otp otp);



  }

