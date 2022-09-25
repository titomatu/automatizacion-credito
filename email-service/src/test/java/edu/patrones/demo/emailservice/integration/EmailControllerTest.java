package edu.patrones.demo.emailservice.integration;

import edu.patrones.demo.dto.EmailDto;
import edu.patrones.demo.emailservice.controller.EmailController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailControllerTest {

    @Autowired
    EmailController emailController;

    @Test
    void sendEmail(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        EmailDto emailDto = new EmailDto();
        emailDto.setTo("titomaturanad@javeriana.edu.co");
        emailDto.setSubject("Este es un correo de prueba");
        emailDto.setText("TEXT TEXT TEXT");

        EmailDto emailDto1 = emailController.sendMail(emailDto);

        assertThat(emailDto).isEqualTo(emailDto1);
    }
}
