package com.banco.solicitud.credito.solicitud.infraestructura.rest.recurso;

import com.banco.solicitud.credito.cliente.infraestructura.rest.recurso.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class SolicitudDto {

  ClienteDTO clienteDto;
  private String numeroSolicitud;
  private Double valorSolicitado;
  private Double valorAprobado;
  private String mensaje;

  public SolicitudDto() {
  }

  public SolicitudDto(
      ClienteDTO clienteDto, String numeroSolicitud, Double valorSolicitado,
      Double valorAprobado, String mensaje) {
    this.clienteDto = clienteDto;
    this.numeroSolicitud = numeroSolicitud;
    this.valorSolicitado = valorSolicitado;
    this.valorAprobado = valorAprobado;
    this.mensaje = mensaje;
  }

  public ClienteDTO getClienteDto() {
    return clienteDto;
  }

  public void setClienteDto(
      ClienteDTO clienteDto) {
    this.clienteDto = clienteDto;
  }

  public String getNumeroSolicitud() {
    return numeroSolicitud;
  }

  public void setNumeroSolicitud(String numeroSolicitud) {
    this.numeroSolicitud = numeroSolicitud;
  }

  public Double getValorSolicitado() {
    return valorSolicitado;
  }

  public void setValorSolicitado(Double valorSolicitado) {
    this.valorSolicitado = valorSolicitado;
  }

  public Double getValorAprobado() {
    return valorAprobado;
  }

  public void setValorAprobado(Double valorAprobado) {
    this.valorAprobado = valorAprobado;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
}
