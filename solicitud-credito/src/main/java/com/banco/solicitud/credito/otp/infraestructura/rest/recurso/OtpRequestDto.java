package com.banco.solicitud.credito.otp.infraestructura.rest.recurso;

import org.springframework.stereotype.Component;

@Component
public class OtpRequestDto {
  private String username;
  private String correo;

  public OtpRequestDto() {
  }

  public OtpRequestDto(String username, String correo) {
    this.username = username;
    this.correo = correo;
  }

  public OtpRequestDto(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }
}
