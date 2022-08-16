package com.banco.solicitud.credito.compartidos.excepcion;

public abstract class ExceptionTecnica extends ExcepcionBase {

  private static  final long serialversionUID = -8946415577270136466L;

  public ExceptionTecnica(CodigoExcepcion codigoExcepcion , Throwable mensaje) {
    super(codigoExcepcion, mensaje);
  }
}
