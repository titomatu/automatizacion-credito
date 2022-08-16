package com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso;

import org.springframework.stereotype.Component;

@Component
public class AuthResponseDto {
  private String access_token;

  public AuthResponseDto() {
  }

  public AuthResponseDto(String access_token) {
    this.access_token = access_token;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }
}
