package com.banco.solicitud.credito.solicitud.infraestructura.ensamblador;


import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SolicitudEnsamblador {

  @Mappings({
      @Mapping( target = "cliente", source = "solicitudDto.clienteDto" ),
      @Mapping( target = "numeroSolictud", source = "solicitudDto.numeroSolicitud" ),
      @Mapping( target = "valorSolicitado", source = "solicitudDto.valorSolicitado" ),
      @Mapping( target = "valorAprobado" , source = "solicitudDto.valorAprobado"),
      @Mapping( target = "mensaje", source = "solicitudDto.mensaje"),
      @Mapping( target = "plazo", source = "solicitudDto.plazo")
  })
  public Solicitud convierteDTOaModelo(SolicitudDto solicitudDto);

}
