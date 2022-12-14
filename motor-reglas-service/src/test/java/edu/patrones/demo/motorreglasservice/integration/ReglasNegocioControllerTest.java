package edu.patrones.demo.motorreglasservice.integration;

import edu.patrones.demo.dto.MotorReglaRequestDto;
import edu.patrones.demo.dto.MotorReglaResponseDto;
import edu.patrones.demo.motorreglasservice.controller.ReglasNegocioController;
import org.assertj.core.api.Assertions;
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
        motorReglaRequestDto.setSalarioAportes(1450000D);
        motorReglaRequestDto.setValorSolicitado(80000000D);
        motorReglaRequestDto.setPlazo(60);
        motorReglaRequestDto.setNumeroSolicitud("a4845166-668b-40fe-8428-3173e429de08");
        motorReglaRequestDto.setGastos(1400000.0);

        MotorReglaResponseDto motorReglaResponseDto = reglasNegocioController.getDiscountPercent(motorReglaRequestDto);

        assertThat(motorReglaResponseDto.getValorAprobado()).isEqualTo(0L);
    }

    @Test
    public void evaluarSolicitudPreofertaCreditoAprobada() throws ParseException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        MotorReglaRequestDto motorReglaRequestDto = new MotorReglaRequestDto();

        motorReglaRequestDto.setTipoDocumento("CC");
        motorReglaRequestDto.setNumeroDocumento(12345679);
        motorReglaRequestDto.setFechaExpedicion(new SimpleDateFormat("dd/MM/yyyy").parse("27/12/2017"));
        motorReglaRequestDto.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("27/12/2017"));
        motorReglaRequestDto.setAprobadoCentral("S");
        motorReglaRequestDto.setSalarioMensual(5000000D);
        motorReglaRequestDto.setSalarioAportes(5950000D);
        motorReglaRequestDto.setValorSolicitado(50000000D);
        motorReglaRequestDto.setPlazo(60);
        motorReglaRequestDto.setNumeroSolicitud("a4845166-668b-40fe-8428-3173e429de08");
        motorReglaRequestDto.setGastos(1600000.0);

        MotorReglaResponseDto motorReglaResponseDto = reglasNegocioController.getDiscountPercent(motorReglaRequestDto);

        assertThat(motorReglaResponseDto.getValorAprobado()).isEqualTo(0L);
    }
    @Test
    public void evaluarSegundaPreofertaCreditoAprobada() throws ParseException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        MotorReglaRequestDto motorReglaRequestDto = new MotorReglaRequestDto();
        motorReglaRequestDto.setTipoDocumento("CC");
        motorReglaRequestDto.setNumeroDocumento(12345679L);
        motorReglaRequestDto.setFechaExpedicion((new SimpleDateFormat("dd/MM/yyyy")).parse("27/12/2017"));
        motorReglaRequestDto.setFechaNacimiento((new SimpleDateFormat("dd/MM/yyyy")).parse("27/12/2017"));
        motorReglaRequestDto.setAprobadoCentral("S");
        motorReglaRequestDto.setSalarioMensual(8000000.0);
        motorReglaRequestDto.setSalarioAportes(4500000.0);
        motorReglaRequestDto.setValorSolicitado(50000000.0);
        motorReglaRequestDto.setPlazo(60);
        motorReglaRequestDto.setNumeroSolicitud("a4845166-668b-40fe-8428-3173e429de08");
        motorReglaRequestDto.setGastos(600000.0);
        MotorReglaResponseDto motorReglaResponseDto = this.reglasNegocioController.getDiscountPercent(motorReglaRequestDto);
        Assertions.assertThat(motorReglaResponseDto.getValorAprobado()).isEqualTo(0L);
    }
}
