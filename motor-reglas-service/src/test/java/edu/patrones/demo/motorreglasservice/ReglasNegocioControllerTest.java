package edu.patrones.demo.motorreglasservice;

import edu.patrones.demo.dto.MotorReglaRequestDto;
import edu.patrones.demo.dto.MotorReglaResponseDto;
import edu.patrones.demo.motorreglasservice.controller.ReglasNegocioController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReglasNegocioControllerTest {

    @Autowired
    ReglasNegocioController reglasNegocioController;


    @Test
    public void evaluarSolicitudPreofertaCreditoRechazada() throws ParseException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        MotorReglaRequestDto motorReglaRequestDto = new MotorReglaRequestDto();

        motorReglaRequestDto.setTipoDocumento("CC");
        motorReglaRequestDto.setNumeroDocumento(12345678);
        motorReglaRequestDto.setFechaExpedicion(new SimpleDateFormat("dd/MM/yyyy").parse("27/12/2017"));
        motorReglaRequestDto.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("27/12/2017"));
        motorReglaRequestDto.setAprobadoCentral("S");
        motorReglaRequestDto.setSalarioMensual(5000000D);
        motorReglaRequestDto.setSalarioAportes(1950000D);
        motorReglaRequestDto.setValorSolicitado(50000000D);
        motorReglaRequestDto.setPlazo(60);
        motorReglaRequestDto.setNumeroSolicitud("a4845166-668b-40fe-8428-3173e429de08");

        MotorReglaResponseDto motorReglaResponseDto = reglasNegocioController.getDiscountPercent(motorReglaRequestDto);

        assertThat(motorReglaResponseDto.getValorAprobado()).isEqualTo(0L);
    }
}
