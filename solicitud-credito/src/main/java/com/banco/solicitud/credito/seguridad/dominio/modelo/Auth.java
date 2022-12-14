package com.banco.solicitud.credito.seguridad.dominio.modelo;

import org.springframework.stereotype.Component;

@Component
public class Auth {

  private String username;
  private String password;
  private String access_token;

  public Auth() {
  }

  public Auth(String username, String password, String access_token) {
    this.username = username;
    this.password = password;
    this.access_token = access_token;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }
}
