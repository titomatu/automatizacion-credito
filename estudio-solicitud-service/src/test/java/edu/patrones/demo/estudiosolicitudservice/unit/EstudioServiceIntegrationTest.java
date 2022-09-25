package edu.patrones.demo.estudiosolicitudservice.unit;

import edu.patrones.demo.dto.ClienteDto;
import edu.patrones.demo.dto.MotorReglaRequestDto;
import edu.patrones.demo.dto.SolicitudDto;
import edu.patrones.demo.estudiosolicitudservice.service.EstudioService;
import edu.patrones.demo.event.estudio.EstudioEvent;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.solicitud.SolicitudEvent;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstudioServiceIntegrationTest {

    @InjectMocks
    private EstudioService estudioService;

    @Mock
    private ProducerTemplate producerTemplate;

    @Test
    public void estudioAprobadoTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(123456789L);
        clienteDto.setFechaExpedicion(formatter.parse("27-12-2007"));
        clienteDto.setNombre1("NOMBRE 1");
        clienteDto.setNombre2("NOMBRE 2");
        clienteDto.setApellido1("APELLIDO 1");
        clienteDto.setApellido2("APELLIDO 2");
        clienteDto.setCelular(12345789L);
        clienteDto.setCorreoElectronico("titomaturanad@javeriana.edu.co");
        clienteDto.setSalarioMensual(8000000D);
        clienteDto.setFechaNacimiento(formatter.parse("09-09-1988"));
        clienteDto.setGastos(1600000D);
        clienteDto.setTotalActivos(50000000D);
        clienteDto.setTotalPasivos(30000000D);
        clienteDto.setAutorizaCentrales("S");
        clienteDto.setGenero("X");
        clienteDto.setTipoResidencia("F");
        clienteDto.setActividadEconomica(2);
        clienteDto.setTipoContrato("I");
        clienteDto.setTipoContrato("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        solicitudDto.setNumeroSolicitud(UUID.randomUUID().toString());
        solicitudDto.setValorSolicitado(50000000D);
        solicitudDto.setPlazo(60);
        solicitudDto.setReportado("N");
        solicitudDto.setPromedioAportes(9500000D);

        solicitudDto.setClienteDto(clienteDto);
        solicitudEvent.setSolicitudDto(solicitudDto);

        String initialString = "{\"numeroSolicitud\":\"" + solicitudDto.getNumeroSolicitud() + "\"," +
                "\"valorAprobado\":" + 50000000 + "," +
                "\"valorCuota\":" + 1500000 +"," +
                "\"tasaCalculada\":" + 2.7 + "," +
                "\"mensajeS\":\"Transacción Correcta\"}";
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());

        doNothing().when(producerTemplate).start();
        doNothing().when(producerTemplate).stop();

        when(producerTemplate.requestBody(any(String.class), any(MotorReglaRequestDto.class), eq(InputStream.class)))
                .thenReturn(targetStream);

        EstudioEvent estudioEvent = estudioService.nuevaSolicitud(solicitudEvent);

        assertThat(estudioEvent.getEstudioStatus()).isEqualTo(EstudioStatus.ESTUDIO_APROBADO);
        assertThat(estudioEvent.getMotorReglaResponseDto().getValorAprobado()).isEqualTo(50000000L);
        assertThat(estudioEvent.getMotorReglaResponseDto().getValorCuota()).isEqualTo(1500000D);
        assertThat(estudioEvent.getMotorReglaResponseDto().getTasaCalculada()).isEqualTo(2.7D);
    }

    @Test
    public void estudioNoAprobadoTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(123456789L);
        clienteDto.setFechaExpedicion(formatter.parse("27-12-2007"));
        clienteDto.setNombre1("NOMBRE 1");
        clienteDto.setNombre2("NOMBRE 2");
        clienteDto.setApellido1("APELLIDO 1");
        clienteDto.setApellido2("APELLIDO 2");
        clienteDto.setCelular(12345789L);
        clienteDto.setCorreoElectronico("titomaturanad@javeriana.edu.co");
        clienteDto.setSalarioMensual(8000000D);
        clienteDto.setFechaNacimiento(formatter.parse("09-09-1988"));
        clienteDto.setGastos(1600000D);
        clienteDto.setTotalActivos(50000000D);
        clienteDto.setTotalPasivos(30000000D);
        clienteDto.setAutorizaCentrales("S");
        clienteDto.setGenero("X");
        clienteDto.setTipoResidencia("F");
        clienteDto.setActividadEconomica(2);
        clienteDto.setTipoContrato("I");
        clienteDto.setTipoContrato("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        solicitudDto.setNumeroSolicitud(UUID.randomUUID().toString());
        solicitudDto.setValorSolicitado(50000000D);
        solicitudDto.setPlazo(60);
        solicitudDto.setReportado("N");
        solicitudDto.setPromedioAportes(9500000D);

        solicitudDto.setClienteDto(clienteDto);
        solicitudEvent.setSolicitudDto(solicitudDto);

        String initialString = "{\"numeroSolicitud\":\"" + solicitudDto.getNumeroSolicitud() + "\"," +
                "\"codeRespuesta\":" + 1695 + "," +
                "\"valorAprobado\":" + 0 + "," +
                "\"valorCuota\":" + 0 +"," +
                "\"tasaCalculada\":" + 0 + "," +
                "\"mensajeS\":\"Transacción Correcta\"}";
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());

        doNothing().when(producerTemplate).start();
        doNothing().when(producerTemplate).stop();

        when(producerTemplate.requestBody(any(String.class), any(MotorReglaRequestDto.class), eq(InputStream.class)))
                .thenReturn(targetStream);

        EstudioEvent estudioEvent = estudioService.nuevaSolicitud(solicitudEvent);

        assertThat(estudioEvent.getEstudioStatus()).isEqualTo(EstudioStatus.ESTUDIO_NO_APROBADO);
        assertThat(estudioEvent.getMotorReglaResponseDto().getValorAprobado()).isEqualTo(0L);
        assertThat(estudioEvent.getMotorReglaResponseDto().getValorCuota()).isEqualTo(0D);
        assertThat(estudioEvent.getMotorReglaResponseDto().getTasaCalculada()).isEqualTo(0D);
    }


    @Test
    public void estudioErrorFormatoRespuestaMotorReglasTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        SolicitudEvent solicitudEvent = new SolicitudEvent();
        SolicitudDto solicitudDto = new SolicitudDto();
        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(123456789L);
        clienteDto.setFechaExpedicion(formatter.parse("27-12-2007"));
        clienteDto.setNombre1("NOMBRE 1");
        clienteDto.setNombre2("NOMBRE 2");
        clienteDto.setApellido1("APELLIDO 1");
        clienteDto.setApellido2("APELLIDO 2");
        clienteDto.setCelular(12345789L);
        clienteDto.setCorreoElectronico("titomaturanad@javeriana.edu.co");
        clienteDto.setSalarioMensual(8000000D);
        clienteDto.setFechaNacimiento(formatter.parse("09-09-1988"));
        clienteDto.setGastos(1600000D);
        clienteDto.setTotalActivos(50000000D);
        clienteDto.setTotalPasivos(30000000D);
        clienteDto.setAutorizaCentrales("S");
        clienteDto.setGenero("X");
        clienteDto.setTipoResidencia("F");
        clienteDto.setActividadEconomica(2);
        clienteDto.setTipoContrato("I");
        clienteDto.setTipoContrato("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        solicitudDto.setNumeroSolicitud(UUID.randomUUID().toString());
        solicitudDto.setValorSolicitado(50000000D);
        solicitudDto.setPlazo(60);
        solicitudDto.setReportado("N");
        solicitudDto.setPromedioAportes(9500000D);

        solicitudDto.setClienteDto(clienteDto);
        solicitudEvent.setSolicitudDto(solicitudDto);

        String initialString = "{\"numeroSolicitud\":\"" + solicitudDto.getNumeroSolicitud() + "\"," +
                "codeRespuesta:" + 1695 + "," +
                "\"valorAprobado\":" + 0 + "," +
                "\"valorCuota\":" + 0 +"," +
                "\"tasaCalculada\":" + 0 + "," +
                "\"mensajeS\":\"Transacción Correcta\"}";
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());

        doNothing().when(producerTemplate).start();
        doNothing().when(producerTemplate).stop();

        when(producerTemplate.requestBody(any(String.class), any(MotorReglaRequestDto.class), eq(InputStream.class)))
                .thenReturn(targetStream);

        EstudioEvent estudioEvent = estudioService.nuevaSolicitud(solicitudEvent);

        assertThat(estudioEvent.getEstudioStatus()).isEqualTo(EstudioStatus.ESTUDIO_NO_APROBADO);
        assertThat(estudioEvent.getMotorReglaResponseDto().getValorAprobado()).isEqualTo(0L);
        assertThat(estudioEvent.getMotorReglaResponseDto().getValorCuota()).isEqualTo(0D);
        assertThat(estudioEvent.getMotorReglaResponseDto().getTasaCalculada()).isEqualTo(0D);
    }
}
