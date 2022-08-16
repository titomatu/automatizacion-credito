package com.banco.solicitud.credito.solicitud.dominio.servicio.Interface;

import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;
import com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso.SolicitudDto;

@FunctionalInterface
public interface GuardarSolicitud {

  Solicitud guardaSolicitu( SolicitudDto solicitudDto );

}
