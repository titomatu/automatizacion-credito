package com.banco.solicitud.credito.solicitud.infraestructura.ensamblador;

import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SolicitudEnsambladorDto {

  @Mappings({
      @Mapping( target = "clienteDto", source = "solicitud.cliente" ),
      @Mapping( target = "numeroSolicitud", source = "solicitud.numeroSolictud" ),
      @Mapping( target = "valorSolicitado", source = "solicitud.valorSolicitado" ),
      @Mapping( target = "valorAprobado" , source = "solicitud.valorAprobado"),
      @Mapping( target = "mensaje", source = "solicitud.mensaje"),
      @Mapping( target = "plazo", source = "solicitud.plazo")
  })
  public SolicitudDto  convierteModeloaDTO (Solicitud solicitud);

}
