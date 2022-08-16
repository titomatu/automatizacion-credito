package com.banco.solicitud.credito.compartidos.excepcion;

public class ClienteRequestExcepcion extends ExceptionTecnica {
  private static final long serialVersionUID = 1L;

 public ClienteRequestExcepcion(Throwable mensaje) {
    super(CodigoExcepcion.CLIENTE_REQUEST_EXCEPTION , mensaje);
  }
}
