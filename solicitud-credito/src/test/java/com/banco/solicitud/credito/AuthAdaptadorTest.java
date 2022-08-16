package com.banco.solicitud.credito;



import com.banco.solicitud.credito.seguridad.dominio.modelo.Auth;
import com.banco.solicitud.credito.seguridad.infraestructura.adaptador.AuthAdaptadorDto;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthRequestDto;
import com.banco.solicitud.credito.seguridad.infraestructura.rest.recurso.AuthResponseDto;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthAdaptadorTest {

  @Autowired
  private AuthAdaptadorDto authAdaptadorDto;

  @Autowired
  private AuthResponseDto otpRequestDto;

  @Autowired
  private AuthRequestDto authRequestDto;

  @Autowired
  Auth auth;



  @Test
  @Disabled("Not Implement Test yet ")
  public void pruebaAdactador(){
    auth.setUsername("123456789");
    auth.setPassword("TkxXs1MQ");
    Auth posts = null;
        authAdaptadorDto.consultarTokenDto(auth);
    Assert.assertNotNull(posts);
    System.out.println("PRUEBA UNITARIA: " + posts.getPassword()+" -- "+posts.getUsername());
  }


}
