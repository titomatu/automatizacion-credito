package com.banco.solicitud.credito.compartidos.excepcion;

import java.util.UUID;

public abstract class ExcepcionBase extends RuntimeException {

  private static final long serialVersionUID = -824690113517284691L ;
  private final CodigoExcepcion codigoExcepcion;
  private final String id = UUID.randomUUID().toString();

  /**
   * Constructs a new runtime exception with {@code null} as its detail message.  The cause is not
   * initialized, and may subsequently be initialized by a call to {@link #initCause}.
   */
  protected ExcepcionBase(CodigoExcepcion codigoExcepcion) {
    super(codigoExcepcion.getTipo());
    this.codigoExcepcion = codigoExcepcion;
  }

  protected ExcepcionBase(CodigoExcepcion codigoExcepcion , String mensaje) {
    super(mensaje);
    this.codigoExcepcion = codigoExcepcion;
  }

  protected ExcepcionBase(CodigoExcepcion codigoExcepcion , Throwable mensaje) {
    super(mensaje);
    this.codigoExcepcion = codigoExcepcion;
  }

  public CodigoExcepcion getCodigoExcepcion() {
    return codigoExcepcion;
  }

  public String getId() {
    return id;
  }


}
