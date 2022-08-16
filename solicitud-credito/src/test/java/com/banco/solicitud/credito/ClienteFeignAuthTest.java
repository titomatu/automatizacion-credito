package com.banco.solicitud.credito;


import com.banco.solicitud.credito.seguridad.infraestructura.rest.cliente.TokenRestClien;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthRequestDto;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthResponseDto;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteFeignAuthTest {

  @Autowired
  private TokenRestClien tokenRestClien;
  @Autowired
  private AuthRequestDto otpRequestDto;

  @Autowired
  private AuthResponseDto authResponseDto;

  @Test
  @Disabled("Not Implement Test yet ")
  public void shouldLoadAllPosts() {
    otpRequestDto.setUsername("9145209");
    otpRequestDto.setPassword("TkxXs1MQ");
    final AuthResponseDto posts = tokenRestClien.authToken(otpRequestDto.getUsername(),otpRequestDto.getPassword());
    Assert.assertNotNull(posts);
      System.out.println("PRUEBA UNITARIA: " + posts.getAccess_token()+" -- ");

  }

}
