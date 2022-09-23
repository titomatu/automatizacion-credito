package edu.patrones.demo.solicitudservice.gui;

import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.centrales.CentralesStatus;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudStatus;
import edu.patrones.demo.solicitudservice.gui.screenplay.*;
import edu.patrones.demo.solicitudservice.model.ClienteId;
import edu.patrones.demo.solicitudservice.model.Solicitud;
import edu.patrones.demo.solicitudservice.repository.SolicitudRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SerenityJUnit5Extension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SolicitudRechazadaCentralesScreenplayTest {

    @Autowired
    SolicitudRepository solicitudRepository;

    @Managed(driver = "chrome")
    private WebDriver browser;

    @CastMember(name = "Cliente")
    Actor cliente;

    @CastMember(name = "AnalistaDeCredito")
    Actor analista;

    @BeforeEach
    public void setup(){
        cliente.describedAs("El Prestatario");
        analista.describedAs("Analista de Crédito");

        browser.manage().window().maximize();
        cliente.can(BrowseTheWeb.with(browser));
    }

    @ParameterizedTest
    @CsvSource({ "12345678912,MWuBgSjr"})
    @Title("Solicitud rechazada por información Centrales no satisfactorias")
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
                                "1200000",
                                "50000000",
                                "60"
                        )
                );

        then(cliente)
                .should(
                    seeThat("El mensaje de confirmación de la radicación de la solicitud se ha desplegado", SolicitudRadicadaQuestion.value())
                );

        Thread.sleep(15000);

        List<Solicitud> solicitudList = solicitudRepository.findFirstByCliente_ClienteIdOrderByCreationDateDesc(new ClienteId("CC", Long.valueOf(username)));

        Solicitud solicitud = solicitudList.get(0);
        SolicitudOverviewData solicitudOverviewData = new SolicitudOverviewData(solicitud);

        then(analista)
                .should(
                        seeThat("El estado de la Solicitud es Rechazada", solicitudOverviewData.estadoSolicitud(), equalTo(SolicitudStatus.SOLICITUD_RECHAZADA)),
                        seeThat("El estado de RNEC es Completado", solicitudOverviewData.estadoValidacionRNEC(), equalTo(RNECStatus.RNEC_COMPLETADO)),
                        seeThat("El estado de Aportes es Validado", solicitudOverviewData.estadoAportesLinea(), equalTo(AportesLineaStatus.APORTES_LINEA_VALIDADO)),
                        seeThat("El estado de Centrales es Completado", solicitudOverviewData.estadoVerificacionCentrales(), equalTo(CentralesStatus.CENTRALES_COMPLETADO)),
                        seeThat("El estado del Estudio es No Aprobado", solicitudOverviewData.estadoEstudioSolicitud(), equalTo(EstudioStatus.ESTUDIO_NO_APROBADO)),
                        seeThat("El valor aprobado es 0", solicitudOverviewData.valorAprobado(), equalTo(0L))
                );
    }

}
