package com.banco.solicitud.credito.solicitud.dominio.servicio;


import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.dominio.servicio.Interface.GuardarSolicitud;
import com.banco.solicitud.credito.solicitud.dominio.servicio.Interface.GuardarSolicitudDto;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;


public abstract class ServicioSolicitudDto {

  private GuardarSolicitudDto guardarSolicitudDto;

  public ServicioSolicitudDto() {
  }

  public Solicitud guardarSolicitud(Solicitud solicitud) {

    return guardarSolicitudDto.guardaSolicituDto(solicitud);

  }

  public void setGuardarSolicitudDto(
      GuardarSolicitudDto guardarSolicitudDto) {
    this.guardarSolicitudDto = guardarSolicitudDto;
  }
}
