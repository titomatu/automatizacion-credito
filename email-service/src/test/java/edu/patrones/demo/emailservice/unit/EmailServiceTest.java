package edu.patrones.demo.emailservice.unit;

import edu.patrones.demo.dto.EmailDto;
import edu.patrones.demo.emailservice.controller.EmailController;
import edu.patrones.demo.emailservice.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void testEmailService(){
        EmailDto emailDto = new EmailDto();

        emailDto.setText("This a test email");
        emailDto.setTo("to");
        emailDto.setSubject("Test Email");

        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        emailService.sendEmail(emailDto);

        SimpleMailMessage message = mock(SimpleMailMessage.class);

        message.setFrom("tamatu@gmail.com");
        message.setTo(emailDto.getTo());
        message.setSubject(emailDto.getSubject());
        message.setText(emailDto.getText());

        verify(message, times(1)).setFrom("tamatu@gmail.com");
        verify(message, times(1)).setTo(emailDto.getTo());
        verify(message, times(1)).setSubject(emailDto.getSubject());
        verify(message, times(1)).setText(emailDto.getText());
    }
}
