package com.banco.solicitud.credito.seguridad.dominio.servicio.Interface;

import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;

@FunctionalInterface
public interface ConsultarTokenDto {

  Auth consultarToken(Auth auth);



}

