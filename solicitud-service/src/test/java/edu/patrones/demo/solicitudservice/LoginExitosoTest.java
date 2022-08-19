package edu.patrones.demo.solicitudservice;

import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.centrales.CentralesStatus;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudStatus;
import edu.patrones.demo.solicitudservice.model.Solicitud;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginExitosoTest {

    private WebDriver driver;

    // Connection object
    static Connection con = null;
    // Statement object
    private static PreparedStatement preparedStatement;
    // Constant for Database URL
    public static String DB_URL = "jdbc:mysql://localhost:3306/db_solicitudes";
    //Database Username
    public static String DB_USER = "root";
    // Database Password
    public static String DB_PASSWORD = "root12345";

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8088");

        try{
            // Database connection
            String dbClass = "com.mysql.cj.jdbc.Driver";
            Class.forName(dbClass).newInstance();
            // Get connection to DB
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Statement object to send the SQL statement to the Database
            String query = "SELECT * FROM db_solicitudes.solicitud_prestamo WHERE cliente_numero_documento = ? ORDER BY creation_date DESC LIMIT 1;";
            preparedStatement = con.prepareStatement(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testSolicitudAprobada() throws InterruptedException {
        WebElement linkLogin = driver.findElement(By.linkText("Iniciar sesión"));
        linkLogin.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement username = driver.findElement(By.name("username"));
        username.clear();
        username.sendKeys("123456789");

        WebElement password = driver.findElement(By.name("password"));
        password.clear();
        password.sendKeys("OdYlLzXa");

        Thread.sleep(5000);
    }

    @After
    public void tearDown() throws SQLException {
        driver.quit();
        // Close DB connection
        if (con != null) {
            con.close();
        }
    }
}
