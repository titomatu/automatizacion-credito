package edu.patrones.demo.solicitudservice;

import edu.patrones.demo.solicitudservice.model.Solicitud;
import edu.patrones.demo.solicitudservice.screenplay.LLenarPasoTresTask;
import edu.patrones.demo.solicitudservice.screenplay.LlenarPasoDosTask;
import edu.patrones.demo.solicitudservice.screenplay.LlenarPasoUnoTask;
import edu.patrones.demo.solicitudservice.screenplay.LoginTask;
import edu.patrones.demo.solicitudservice.utils.Utils;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Title;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SerenityJUnit5Extension.class)
public class SolicitudRechazadaRNECScreenplayTest{

    @Managed(driver = "chrome")
    private WebDriver browser;

    @CastMember(name = "Cliente")
    Actor cliente;

    @BeforeEach
    public void setup(){
        browser.manage().window().maximize();
        cliente.can(BrowseTheWeb.with(browser));
    }

    @ParameterizedTest
    @CsvSource({ "123456799,GFuYouc4"})
    @Title("Solicitud rechazada por información RNEC inválida")
    public void testSolicitudRechazadaRNEC(String username, String password) throws InterruptedException {

        givenThat(cliente)
                .attemptsTo(LoginTask.of(username, password));

        when(cliente)
                .attemptsTo(
                        LlenarPasoUnoTask.of(
                                "CC",
                                username,
                                "2017/12/27",
                                "NOMBRE PRUEBA 11",
                                "NOMBRE PRUEBA 22",
                                "APELLIDO PRUEBA 11",
                                "APELLIDO PRUEBA 22",
                                "M"
                        ),
                        LlenarPasoDosTask.of(
                                "1980/12/27",
                                "31012345656375",
                                "titomaturanad@javeriana.edu.co",
                                "C",
                                "A",
                                "P",
                                "I"
                        ),
                        LLenarPasoTresTask.of(
                                "5000000",
                                "U",
                                "2",
                                "20000000",
                                "20000000",
                                "20000000",
                                "50000000",
                                "60"
                        )
                );

        Thread.sleep(15000);

        //Then

        Solicitud solicitud = Utils.obtenerSolicitud(username);

        assertEquals("SOLICITUD_RECHAZADA", solicitud.getSolicitudStatus().name(), "Se verifica que la solicitud haya sido aprobada");
        assertEquals("RNEC_NO_EXITOSO", solicitud.getRnecStatus().name(), "Se verifica que la RNEC haya sido aprobada");
        assertEquals("APORTES_LINEA_VALIDADO", solicitud.getAportesLineaStatus().name(), "Se verifica que la Aportes haya sido aprobada");
        assertEquals("CENTRALES_COMPLETADO", solicitud.getCentralesStatus().name(), "Se verifica que la Centrales haya sido aprobada");
        assertEquals("ESTUDIO_PENDIENTE", solicitud.getEstudioStatus().name(), "Se verifica que la Estudio haya sido aprobada");
        assertEquals(60, solicitud.getPlazo(), "Se verifica el plazo aprobado");
        assertEquals(0, solicitud.getValorAprobado(), "Se verifica que la solicitud haya sido aprobada con un valor");
    }

}
