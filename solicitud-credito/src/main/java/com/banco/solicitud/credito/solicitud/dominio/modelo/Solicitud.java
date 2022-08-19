package com.banco.solicitud.credito.solicitud.dominio.modelo;

import com.banco.solicitud.credito.cliente.dominio.modelo.Cliente;
import org.springframework.stereotype.Component;


@Component
public class Solicitud {

  private Cliente cliente;
  private Integer numeroSolictud;
  private String valorSolicitado;
  private String valorAprobado;
  private String mensaje;

  private Integer plazo;

  public Solicitud() {
  }

  public Solicitud(Cliente cliente, Integer numeroSolictud, String valorSolicitado,
      String valorAprobado, String mensaje, String valorGastos, Integer plazo) {
    this.cliente = cliente;
    this.numeroSolictud = numeroSolictud;
    this.valorSolicitado = valorSolicitado;
    this.valorAprobado = valorAprobado;
    this.mensaje = mensaje;
    this.plazo = plazo;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Integer getNumeroSolictud() {
    return numeroSolictud;
  }

  public void setNumeroSolictud(Integer numeroSolictud) {
    this.numeroSolictud = numeroSolictud;
  }

  public String getValorSolicitado() {
    return valorSolicitado;
  }

  public void setValorSolicitado(String valorSolicitado) {
    this.valorSolicitado = valorSolicitado;
  }

  public String getValorAprobado() {
    return valorAprobado;
  }

  public void setValorAprobado(String valorAprobado) {
    this.valorAprobado = valorAprobado;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Integer getPlazo() {
    return plazo;
  }

  public void setPlazo(Integer plazo) {
    this.plazo = plazo;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Solicitud{");
    sb.append("cliente=").append(cliente);
    sb.append(", numeroSolictud=").append(numeroSolictud);
    sb.append(", valorSolicitado='").append(valorSolicitado).append('\'');
    sb.append(", valorAprobado='").append(valorAprobado).append('\'');
    sb.append(", mensaje='").append(mensaje).append('\'');
    sb.append(", plazo'=").append(plazo).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
