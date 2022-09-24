package edu.patrones.demo.unit;

import edu.patrones.demo.dto.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonDtoModelTest {

    @Test
    void testAportesLineaDto(){
        String numeroSol = UUID.randomUUID().toString();
        AportesLineaDto aportesLineaDto = new AportesLineaDto(numeroSol, 15000000D);

        AportesLineaDto aportesLineaDto1 = new AportesLineaDto();
        aportesLineaDto1.setNumeroSolicitud(numeroSol);
        aportesLineaDto1.setPromedioAportes(15000000D);

        assertThat(aportesLineaDto.equals(aportesLineaDto1)).isTrue();
        assertThat(aportesLineaDto.hashCode()).isEqualTo(aportesLineaDto1.hashCode());
        assertThat(aportesLineaDto.toString()).isEqualTo(aportesLineaDto1.toString());
    }

    @Test
    void testAportesLineaDtoNotEqual(){
        String numeroSol = UUID.randomUUID().toString();
        AportesLineaDto aportesLineaDto = new AportesLineaDto(numeroSol, 15000000D);

        AportesLineaDto aportesLineaDto1 = new AportesLineaDto();
        aportesLineaDto1.setNumeroSolicitud(numeroSol);
        aportesLineaDto1.setPromedioAportes(17000000D);

        assertThat(aportesLineaDto.equals(aportesLineaDto1)).isFalse();
        assertThat(aportesLineaDto.hashCode()).isNotEqualTo(aportesLineaDto1.hashCode());
        assertThat(aportesLineaDto.toString()).isNotEqualTo(aportesLineaDto1.toString());
    }

    @Test
    void testCentralesRequestDto(){
        String numeroSol = UUID.randomUUID().toString();

        CentralesRequestDto centralesRequestDto = new CentralesRequestDto(numeroSol, "S");

        CentralesRequestDto centralesRequestDto1 = new CentralesRequestDto();
        centralesRequestDto1.setNumeroSolicitud(numeroSol);
        centralesRequestDto1.setReportado("S");

        assertThat(centralesRequestDto.equals(centralesRequestDto)).isTrue();
        assertThat(centralesRequestDto.hashCode()).isEqualTo(centralesRequestDto1.hashCode());
        assertThat(centralesRequestDto.toString()).isEqualTo(centralesRequestDto1.toString());
    }

    @Test
    void testCentralesRequestDtoNotEqual(){
        String numeroSol = UUID.randomUUID().toString();

        CentralesRequestDto centralesRequestDto = new CentralesRequestDto(numeroSol, "S");

        CentralesRequestDto centralesRequestDto1 = new CentralesRequestDto();
        centralesRequestDto1.setNumeroSolicitud(numeroSol);
        centralesRequestDto1.setReportado("N");

        assertThat(centralesRequestDto.equals(centralesRequestDto1)).isFalse();
        assertThat(centralesRequestDto.hashCode()).isNotEqualTo(centralesRequestDto1.hashCode());
        assertThat(centralesRequestDto.toString()).isNotEqualTo(centralesRequestDto1.toString());
    }

    @Test
    void testClienteDto() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

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
        clienteDto.setNivelEstudios("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        ClienteDto clienteDto1 = new ClienteDto(
                "CC",
                123456789L,
                formatter.parse("27-12-2007"),
                "NOMBRE 1",
                "NOMBRE 2",
                "APELLIDO 1",
                "APELLIDO 2",
                12345789L,
                "titomaturanad@javeriana.edu.co",
                8000000D,
                formatter.parse("09-09-1988"),
                50000000D,
                30000000D,
                1600000D,
                "S",
                "X",
                "F",
                2,
                "I",
                "U",
                "A",
                "C"
        );

        assertThat(clienteDto.equals(clienteDto1)).isTrue();
        assertThat(clienteDto.hashCode()).isEqualTo(clienteDto1.hashCode());
        assertThat(clienteDto.toString()).isEqualTo(clienteDto1.toString());
    }

    @Test
    void testClienteDtoNotEqual() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(12345678999L);
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
        clienteDto.setNivelEstudios("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        ClienteDto clienteDto1 = new ClienteDto(
                "CC",
                123456789L,
                formatter.parse("27-12-2007"),
                "NOMBRE 1",
                "NOMBRE 2",
                "APELLIDO 1",
                "APELLIDO 2",
                12345789L,
                "titomaturanad@javeriana.edu.co",
                8000000D,
                formatter.parse("09-09-1988"),
                50000000D,
                30000000D,
                1600000D,
                "S",
                "X",
                "F",
                2,
                "I",
                "U",
                "A",
                "C"
        );

        assertThat(clienteDto.equals(clienteDto1)).isFalse();
        assertThat(clienteDto.hashCode()).isNotEqualTo(clienteDto1.hashCode());
        assertThat(clienteDto.toString()).isNotEqualTo(clienteDto1.toString());
    }

    @Test
    void testEmailDto(){
        EmailDto emailDto = new EmailDto();

        emailDto.setTo("correo@correo.com");
        emailDto.setSubject("test");
        emailDto.setText("Text");

        EmailDto emailDto1 = new EmailDto();

        emailDto1.setTo("correo@correo.com");
        emailDto1.setSubject("test");
        emailDto1.setText("Text");

        assertThat(emailDto.equals(emailDto1)).isTrue();
        assertThat(emailDto.hashCode()).isEqualTo(emailDto1.hashCode());
        assertThat(emailDto.toString()).isEqualTo(emailDto1.toString());
    }

    @Test
    void testEmailDtoNotEqual(){
        EmailDto emailDto = new EmailDto();

        emailDto.setTo("correo@correo.com");
        emailDto.setSubject("test");
        emailDto.setText("Text");

        EmailDto emailDto1 = new EmailDto();

        emailDto1.setTo("correo1@correo.com");
        emailDto1.setSubject("test");
        emailDto1.setText("Text");

        assertThat(emailDto.equals(emailDto1)).isFalse();
        assertThat(emailDto.hashCode()).isNotEqualTo(emailDto1.hashCode());
        assertThat(emailDto.toString()).isNotEqualTo(emailDto1.toString());
    }

    @Test
    void testSolicitudDto() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(12345678999L);
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
        clienteDto.setNivelEstudios("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        SolicitudDto solicitudDto = new SolicitudDto();

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setPlazo(60);
        solicitudDto.setValorSolicitado(50000000D);
        solicitudDto.setPromedioAportes(9500000D);
        solicitudDto.setReportado("S");
        solicitudDto.setValorAprobado(0D);
        solicitudDto.setMensaje("");
        solicitudDto.setNumeroSolicitud("");

        SolicitudDto solicitudDto1 = new SolicitudDto(
                clienteDto,
                "",
                50000000D,
                0D,
                9500000D,
                60,
                "S",
                ""
        );

        assertThat(solicitudDto.equals(solicitudDto1)).isTrue();
        assertThat(solicitudDto.hashCode()).isEqualTo(solicitudDto1.hashCode());
        assertThat(solicitudDto.toString()).isEqualTo(solicitudDto1.toString());
    }

    @Test
    void testSolicitudDtoNotEqual() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(12345678999L);
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
        clienteDto.setNivelEstudios("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        SolicitudDto solicitudDto = new SolicitudDto();

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setPlazo(60);
        solicitudDto.setValorSolicitado(45000000D);
        solicitudDto.setPromedioAportes(9500000D);
        solicitudDto.setReportado("S");
        solicitudDto.setValorAprobado(0D);
        solicitudDto.setMensaje("");
        solicitudDto.setNumeroSolicitud("");

        SolicitudDto solicitudDto1 = new SolicitudDto(
                clienteDto,
                "",
                50000000D,
                0D,
                9500000D,
                60,
                "S",
                ""
        );

        assertThat(solicitudDto.equals(solicitudDto1)).isFalse();
        assertThat(solicitudDto.hashCode()).isNotEqualTo(solicitudDto1.hashCode());
        assertThat(solicitudDto.toString()).isNotEqualTo(solicitudDto1.toString());
    }

    @Test
    void testEstudioRequestDto() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(12345678999L);
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
        clienteDto.setNivelEstudios("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        SolicitudDto solicitudDto = new SolicitudDto();

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setPlazo(60);
        solicitudDto.setValorSolicitado(50000000D);
        solicitudDto.setPromedioAportes(9500000D);
        solicitudDto.setReportado("S");
        solicitudDto.setValorAprobado(0D);
        solicitudDto.setMensaje("");
        solicitudDto.setNumeroSolicitud("");

        SolicitudDto solicitudDto1 = new SolicitudDto(
                clienteDto,
                "",
                50000000D,
                0D,
                9500000D,
                60,
                "S",
                ""
        );

        EstudioRequestDto estudioRequestDto = new EstudioRequestDto(solicitudDto);
        EstudioRequestDto estudioRequestDto1 = new EstudioRequestDto(solicitudDto1);

        assertThat(estudioRequestDto.equals(estudioRequestDto1)).isTrue();
        assertThat(estudioRequestDto.hashCode()).isEqualTo(estudioRequestDto1.hashCode());
        assertThat(estudioRequestDto.toString()).isEqualTo(estudioRequestDto1.toString());
    }

    @Test
    void testEstudioRequestDtoNotEqual() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setTipoDocumento("CC");
        clienteDto.setNumeroDocumento(12345678999L);
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
        clienteDto.setNivelEstudios("U");
        clienteDto.setTipoInmueble("A");
        clienteDto.setEstadoCivil("C");

        SolicitudDto solicitudDto = new SolicitudDto();

        solicitudDto.setClienteDto(clienteDto);
        solicitudDto.setPlazo(60);
        solicitudDto.setValorSolicitado(45000000D);
        solicitudDto.setPromedioAportes(9500000D);
        solicitudDto.setReportado("S");
        solicitudDto.setValorAprobado(0D);
        solicitudDto.setMensaje("");
        solicitudDto.setNumeroSolicitud("");

        SolicitudDto solicitudDto1 = new SolicitudDto(
                clienteDto,
                "",
                50000000D,
                0D,
                9500000D,
                60,
                "S",
                ""
        );

        EstudioRequestDto estudioRequestDto = new EstudioRequestDto(solicitudDto);
        EstudioRequestDto estudioRequestDto1 = new EstudioRequestDto(solicitudDto1);

        assertThat(estudioRequestDto.equals(estudioRequestDto1)).isFalse();
        assertThat(estudioRequestDto.hashCode()).isNotEqualTo(estudioRequestDto1.hashCode());
        assertThat(estudioRequestDto.toString()).isNotEqualTo(estudioRequestDto1.toString());
    }
}
