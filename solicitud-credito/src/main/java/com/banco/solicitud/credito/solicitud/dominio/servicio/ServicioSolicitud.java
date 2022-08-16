package com.banco.solicitud.credito.solicitud.dominio.servicio;


import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.dominio.servicio.Interface.GuardarSolicitud;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;



public abstract class ServicioSolicitud {

  private GuardarSolicitud guardarSolicitud;

  public ServicioSolicitud() {
  }

  public Solicitud guardarSolicitudDto(SolicitudDto solicitudDto) {

    return guardarSolicitud.guardaSolicitu(solicitudDto);
  }

  public void setGuardarSolicitud(
      GuardarSolicitud guardarSolicitud) {
    this.guardarSolicitud = guardarSolicitud;
  }
}
