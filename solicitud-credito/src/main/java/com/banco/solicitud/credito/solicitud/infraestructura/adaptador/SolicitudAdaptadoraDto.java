package com.banco.solicitud.credito.solicitud.infraestructura.adaptador;

import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.dominio.servicio.Interface.GuardarSolicitudDto;
import com.banco.solicitud.credito.solicitud.dominio.servicio.ServicioSolicitudDto;
import com.banco.solicitud.credito.solicitud.infraestructura.ensamblador.SolicitudEnsamblador;
import com.banco.solicitud.credito.solicitud.infraestructura.ensamblador.SolicitudEnsambladorDto;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.cliente.SolicitudRestCliente;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitudAdaptadoraDto extends ServicioSolicitudDto {

  @Autowired
  private SolicitudDto solicitudDto;
  @Autowired
  private Solicitud solicitud;

  @Autowired
  private Auth auth;


  private final SolicitudEnsamblador solicitudEnsamblador;
  private final SolicitudEnsambladorDto solicitudEnsambladorDto;
  private final SolicitudRestCliente solicitudRestCliente;


  public SolicitudAdaptadoraDto(
      SolicitudDto solicitudDto,
      Solicitud solicitud,
      SolicitudEnsamblador solicitudEnsamblador,
      SolicitudEnsambladorDto solicitudEnsambladorDto,
      SolicitudRestCliente solicitudRestCliente,
      Auth auth
  ) {
    this.solicitudDto = solicitudDto;
    this.solicitud = solicitud;
    this.solicitudEnsamblador = solicitudEnsamblador;
    this.solicitudEnsambladorDto = solicitudEnsambladorDto;
    this.solicitudRestCliente = solicitudRestCliente;
    this.auth = auth;
    super.setGuardarSolicitudDto(guardarSolicitudDto(solicitud));
  }

  public GuardarSolicitudDto guardarSolicitudDto(Solicitud solicitud){


    return (Solicitud solicitud1) -> {
      try{
        return solicitudEnsamblador.convierteDTOaModelo(solicitudRestCliente.radicarSolicitud(solicitud.getMensaje(),solicitudEnsambladorDto.convierteModeloaDTO(solicitud)));
      }catch (Exception e){
        return null;

      }



    } ;
  }




}
