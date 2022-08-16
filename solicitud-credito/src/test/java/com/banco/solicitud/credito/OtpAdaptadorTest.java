package com.banco.solicitud.credito;


import com.banco.solicitud.credito.otp.dominio.modelo.Otp;
import com.banco.solicitud.credito.otp.infraestructura.adaptador.OtpAdaptador;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OtpAdaptadorTest {

  @Autowired
  private OtpAdaptador otpAdaptador;

  @Autowired
  private OtpRequestDto otpRequestDto;


  @Test
  @Disabled("Not Implement Test yet ")
  public void pruebaAdactador(){
    otpRequestDto.setUsername("123456789");
    Otp posts = otpAdaptador.ObtenerOtpDto(otpRequestDto);
    Assert.assertNotNull(posts);
    System.out.println("PRUEBA UNITARIA: " + posts.getPassword()+" -- "+posts.getUsername());
  }


}
