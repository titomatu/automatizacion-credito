package edu.patrones.demo.solicitudservice;

import edu.patrones.demo.solicitudservice.model.Solicitud;
import edu.patrones.demo.solicitudservice.pages.LoginPage;
import edu.patrones.demo.solicitudservice.pages.SolicitudPage;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SerenityJUnit5Extension.class)
public class SolicitudRechazadaCentralesTest extends SolicitudBaseTest{

    @ParameterizedTest
    @CsvSource({ "12345678912,MWuBgSjr"})
    public void testSolicitudRechazadaCentrales(String username, String password) throws InterruptedException {
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
        assertEquals("RNEC_COMPLETADO", solicitud.getRnecStatus().name(), "Se verifica que la RNEC haya sido aprobada");
        assertEquals("APORTES_LINEA_VALIDADO", solicitud.getAportesLineaStatus().name(), "Se verifica que la Aportes haya sido aprobada");
        assertEquals("CENTRALES_COMPLETADO", solicitud.getCentralesStatus().name(), "Se verifica que la Centrales haya sido aprobada");
        assertEquals("ESTUDIO_NO_APROBADO", solicitud.getEstudioStatus().name(), "Se verifica que la Estudio haya sido aprobada");
        assertEquals(60, solicitud.getPlazo(), "Se verifica el plazo aprobado");
        assertEquals(0, solicitud.getValorAprobado(), "Se verifica que la solicitud haya sido aprobada con un valor");
    }

}
