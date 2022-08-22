package edu.patrones.demo.solicitudservice;

import edu.patrones.demo.solicitudservice.model.Solicitud;
import edu.patrones.demo.solicitudservice.pages.LoginPage;
import edu.patrones.demo.solicitudservice.pages.SolicitudPage;
import edu.patrones.demo.solicitudservice.screenplay.LLenarPasoTresTask;
import edu.patrones.demo.solicitudservice.screenplay.LlenarPasoDosTask;
import edu.patrones.demo.solicitudservice.screenplay.LlenarPasoUnoTask;
import edu.patrones.demo.solicitudservice.screenplay.LoginTask;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testScreenPlay(String username, String password) {
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

        then(cliente)
                .should(

                );
    }

}