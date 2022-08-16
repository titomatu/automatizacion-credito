package com.banco.solicitud.credito.compartidos.excepcion;

public enum CodigoExcepcion {
  INVALIDO_REQUEST_STATUS("EXC-01", "INVALID_REQUEST_STATUS"),
  CLIENTE_REQUEST_EXCEPTION("EXC-02","CLIENTE_REQUEST_EXCEPCION");

  private String codigo;
  private String tipo;

  CodigoExcepcion(String codigo, String tipo) {
    this.codigo = codigo;
    this.tipo = tipo;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getTipo() {
    return tipo;
  }
}
