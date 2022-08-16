package com.banco.solicitud.credito.solicitud.dominio.servicio.Interface;

import com.banco.solicitud.credito.solicitud.dominio.modelo.Solicitud;

@FunctionalInterface
public interface GuardarSolicitudDto {

  Solicitud guardaSolicituDto( Solicitud solicitud );
}
