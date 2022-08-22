package edu.patrones.demo.solicitudservice;

import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.centrales.CentralesStatus;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudStatus;
import edu.patrones.demo.solicitudservice.model.Solicitud;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;

public abstract class SolicitudBaseSBDDTest {

    @Managed
    protected WebDriver driver;

    @BeforeAll
    public static void setUpBaseTest(){

    }

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        driver.quit();
    }
}
