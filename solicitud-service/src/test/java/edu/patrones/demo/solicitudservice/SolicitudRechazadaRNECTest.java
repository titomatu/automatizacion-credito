package edu.patrones.demo.solicitudservice;

import edu.patrones.demo.solicitudservice.model.Solicitud;
import edu.patrones.demo.solicitudservice.pages.LoginPage;
import edu.patrones.demo.solicitudservice.pages.SolicitudPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolicitudRechazadaRNECTest extends SolicitudBaseTest{

    @ParameterizedTest
    @CsvSource({ "123456799,GFuYouc4"})
    public void testSolicitudRechazadaRNEC(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        SolicitudPage solicitudPage = loginPage.loginAs(username, password);
        solicitudPage.realizarSolicitud(
            "CC",
                username,
            "2017/12/27",
            "NOMBRE PRUEBA 11",
            "NOMBRE PRUEBA 22",
            "APELLIDO PRUEBA 11",
            "APELLIDO PRUEBA 22",
            "M",
            "1980/12/27",
            "31012345656375",
            "titomaturanad@javeriana.edu.co",
            "C",
            "A",
            "P",
            "I",
            "5000000",
            "U",
            "2",
            "20000000",
            "20000000",
            "20000000",
            "50000000",
            "60"
        );

        assertTrue(solicitudPage.isSuccessAlertVisible());

        Thread.sleep(15000);

        Solicitud solicitud = getUltimaSolicitud(username);

        assertEquals("SOLICITUD_RECHAZADA", solicitud.getSolicitudStatus().name(), "Se verifica que la solicitud haya sido aprobada");
        assertEquals("RNEC_NO_EXITOSO", solicitud.getRnecStatus().name(), "Se verifica que la RNEC haya sido aprobada");
        assertEquals("APORTES_LINEA_VALIDADO", solicitud.getAportesLineaStatus().name(), "Se verifica que la Aportes haya sido aprobada");
        assertEquals("CENTRALES_COMPLETADO", solicitud.getCentralesStatus().name(), "Se verifica que la Centrales haya sido aprobada");
        assertEquals("ESTUDIO_PENDIENTE", solicitud.getEstudioStatus().name(), "Se verifica que la Estudio haya sido aprobada");
        assertEquals(60, solicitud.getPlazo(), "Se verifica el plazo aprobado");
        assertEquals(0, solicitud.getValorAprobado(), "Se verifica que la solicitud haya sido aprobada con un valor");
    }

}
