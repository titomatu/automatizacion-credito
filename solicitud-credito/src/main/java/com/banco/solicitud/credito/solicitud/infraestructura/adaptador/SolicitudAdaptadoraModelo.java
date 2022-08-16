package com.banco.solicitud.credito.solicitud.infraestructura.adaptador;

import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.dominio.servicio.Interface.GuardarSolicitud;
import com.banco.solicitud.credito.solicitud.dominio.servicio.ServicioSolicitud;
import com.banco.solicitud.credito.solicitud.infraestructura.ensamblador.SolicitudEnsamblador;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.cliente.SolicitudRestCliente;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;

public class SolicitudAdaptadoraModelo extends ServicioSolicitud {

  SolicitudDto solicitudDto;
  Solicitud solicitud;
  private final SolicitudEnsamblador solicitudEnsamblador;
  private final SolicitudRestCliente solicitudRestCliente;
  @Autowired
  private Auth auth;

  public SolicitudAdaptadoraModelo(
      SolicitudDto solicitudDto,
      SolicitudEnsamblador solicitudEnsamblador,
      SolicitudRestCliente solicitudRestCliente,
      Auth auth) {
    this.solicitudDto = solicitudDto;
    this.solicitudEnsamblador = solicitudEnsamblador;
    this.solicitudRestCliente = solicitudRestCliente;
    this.auth =  auth;
    super.setGuardarSolicitud(consultarSolicitud(solicitudDto));
  }

public GuardarSolicitud consultarSolicitud (SolicitudDto solicitudDtoP){

    return (SolicitudDto solicitudDto) -> {
      try{

        return  solicitudEnsamblador.convierteDTOaModelo(solicitudRestCliente.radicarSolicitud(solicitudDtoP.getMensaje(),solicitudDtoP));
      }catch (FeignException.NotFound e){
        return  null;

      }
    };
}


}
