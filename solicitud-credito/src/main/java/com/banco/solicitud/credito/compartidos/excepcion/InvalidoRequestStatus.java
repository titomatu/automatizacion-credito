package com.banco.solicitud.credito.compartidos.excepcion;

public class InvalidoRequestStatus extends ExceptionTecnica {
  private static final long serialVersionUID = 1L;

  public InvalidoRequestStatus(Throwable mensaje) {
    super(CodigoExcepcion.INVALIDO_REQUEST_STATUS , mensaje);
  }

}
