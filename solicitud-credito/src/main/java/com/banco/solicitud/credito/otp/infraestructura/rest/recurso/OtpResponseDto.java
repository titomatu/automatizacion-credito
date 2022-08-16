package com.banco.solicitud.credito.otp.infraestructura.rest.recurso;


import org.springframework.stereotype.Component;

@Component
public class OtpResponseDto {
  private String username;
  private String mensaje;

  public OtpResponseDto() {
  }

  public OtpResponseDto(String username, String mensaje) {
    this.username = username;
    this.mensaje = mensaje;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
}
