package com.banco.solicitud.credito.otp.dominio.modelo;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Otp {

  private String username;
  private String password;
  private Date otpRequestedTime;
  private String correo;

  public Otp() {
  }

  public Otp(String username, String password, Date otpRequestedTime, String correo) {
    this.username = username;
    this.password = password;
    this.otpRequestedTime = otpRequestedTime;
    this.correo = correo;
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

  public Date getOtpRequestedTime() {
    return otpRequestedTime;
  }

  public void setOtpRequestedTime(Date otpRequestedTime) {
    this.otpRequestedTime = otpRequestedTime;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }
}
