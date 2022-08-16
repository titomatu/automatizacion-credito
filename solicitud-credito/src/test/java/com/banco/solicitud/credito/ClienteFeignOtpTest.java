package com.banco.solicitud.credito;


import com.banco.solicitud.credito.otp.infraestructura.rest.cliente.OtpRestClien;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpRequestDto;
import com.banco.solicitud.credito.otp.infraestructura.rest.recurso.OtpResponseDto;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClienteFeignOtpTest {

  @Autowired
  private OtpRestClien otpRestClien;
  @Autowired
  private OtpRequestDto otpRequestDto;

  @Test
  @Disabled("Not Implement Test yet ")
  public void shouldLoadAllPosts() {
    otpRequestDto.setUsername("123456789");
    final OtpResponseDto posts = otpRestClien.respuetaOTP(otpRequestDto);
    Assert.assertNotNull(posts);
      System.out.println("PRUEBA UNITARIA: " + posts.getMensaje() +" -- "+posts.getUsername());

  }

}
