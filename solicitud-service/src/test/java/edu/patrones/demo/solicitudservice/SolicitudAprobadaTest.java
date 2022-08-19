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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SolicitudAprobadaTest {

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
        //Login
        Thread.sleep(3000);

        WebElement linkLogin = driver.findElement(By.linkText("Iniciar sesión"));
        linkLogin.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement username = driver.findElement(By.name("username"));
        username.clear();
        username.sendKeys("123456789");

        WebElement password = driver.findElement(By.name("password"));
        password.clear();
        password.sendKeys("OdYlLzXa");

        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='w-100 btn btn-lg btn-primary' and contains(.,'Ingresar')]")));
        element.click();

        //Paso 1
        Select dropTipoDocumento = new Select(driver.findElement(By.name("cliente.tipoDocumento")));
        dropTipoDocumento.selectByValue("CC");

        WebElement numeroDocumento = driver.findElement(By.name("cliente.numeroDocumento"));
        numeroDocumento.clear();
        numeroDocumento.sendKeys("123456789");

        WebElement fechaExpedicion = driver.findElement(By.name("cliente.fechaExpedicion"));
        fechaExpedicion.clear();
        fechaExpedicion.sendKeys("2017/12/27");

        WebElement nombre1 = driver.findElement(By.name("cliente.nombre1"));
        nombre1.clear();
        nombre1.sendKeys("NOMBRE PRUEBA 1");

        WebElement nombre2 = driver.findElement(By.name("cliente.nombre2"));
        nombre2.clear();
        nombre2.sendKeys("NOMBRE PRUEBA 2");

        WebElement apellido1 = driver.findElement(By.name("cliente.apellido1"));
        apellido1.clear();
        apellido1.sendKeys("APELLIDO PRUEBA 1");

        WebElement apellido2 = driver.findElement(By.name("cliente.apellido2"));
        apellido2.clear();
        apellido2.sendKeys("APELLIDO PRUEBA 2");

        Select dropGenero = new Select(driver.findElement(By.name("cliente.genero")));
        dropGenero.selectByValue("M");

        Thread.sleep(3000);

        WebElement btnPaso2 = driver.findElement(By.id("btn-sig1"));
        btnPaso2.click();

        //Paso 2

        WebElement fechaNacimiento = driver.findElement(By.name("cliente.fechaNacimiento"));
        fechaNacimiento.clear();
        fechaNacimiento.sendKeys("1980/12/27");

        WebElement celular = driver.findElement(By.name("cliente.celular"));
        celular.clear();
        celular.sendKeys("31012345656375");

        WebElement correoElectronico = driver.findElement(By.name("cliente.correoElectronico"));
        correoElectronico.clear();
        correoElectronico.sendKeys("titomaturanad@javeriana.edu.co");

        Select dropEstadoCivil = new Select(driver.findElement(By.name("cliente.estadoCivil")));
        dropEstadoCivil.selectByValue("C");

        Select dropTipoInmueble = new Select(driver.findElement(By.name("cliente.tipoInmueble")));
        dropTipoInmueble.selectByValue("A");

        Select dropTipoResidencia = new Select(driver.findElement(By.name("cliente.tipoResidencia")));
        dropTipoResidencia.selectByValue("P");

        WebElement autorizaCentrales = driver.findElement(By.name("cliente.autorizaCentrales"));
        autorizaCentrales.click();

        Thread.sleep(3000);

        WebElement btnPaso3 = driver.findElement(By.id("btn-sig2"));
        btnPaso3.click();

        //Paso 3

        Select dropTipoContrato = new Select(driver.findElement(By.name("cliente.tipoContrato")));
        dropTipoContrato.selectByValue("I");

        WebElement salarioMensual = driver.findElement(By.name("cliente.salarioMensual"));
        salarioMensual.clear();
        salarioMensual.sendKeys("5000000");

        Select dropNivelEstudios = new Select(driver.findElement(By.name("cliente.nivelEstudios")));
        dropNivelEstudios.selectByValue("U");

        Select dropActividadEconomica = new Select(driver.findElement(By.name("cliente.actividadEconomica")));
        dropActividadEconomica.selectByValue("2");

        WebElement totalActivos = driver.findElement(By.name("cliente.totalActivos"));
        totalActivos.clear();
        totalActivos.sendKeys("20000000");

        WebElement totalPasivos = driver.findElement(By.name("cliente.totalPasivos"));
        totalPasivos.clear();
        totalPasivos.sendKeys("20000000");

        WebElement valorGastos = driver.findElement(By.name("cliente.gastos"));
        valorGastos.clear();
        valorGastos.sendKeys("20000000");

        WebElement valorSolicitado = driver.findElement(By.name("valorSolicitado"));
        valorSolicitado.clear();
        valorSolicitado.sendKeys("50000000");

        Select dropPlazoSolicitado = new Select(driver.findElement(By.name("plazo")));
        dropPlazoSolicitado.selectByValue("60");

        Thread.sleep(3000);

        WebElement btnSolicitar = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-solicitar")));
        btnSolicitar.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String titleElem = driver.findElement(By.xpath("//*[@class='container p-5 my-5 bg-dark text-white']/h1")).getText();

        assertEquals("Hemos Generado una Solicitud de Crédito", titleElem, "Se verifica mensaje de radicación exitoso");

        Solicitud solicitud = new Solicitud();

        Thread.sleep(15000);

        try{
            preparedStatement.setLong(1, 123456789);
            // Get the contents of userinfo table from DB
            ResultSet res = preparedStatement.executeQuery();
            // Print the result untill all the records are printed
            // res.next() returns true if there is any next record else returns false
            while (res.next())
            {
                solicitud.setNumeroSolicitud(res.getString("numero_solicitud"));
                solicitud.setAportesLineaStatus(AportesLineaStatus.valueOf(res.getString("aportes_en_linea")));
                solicitud.setCentralesStatus(CentralesStatus.valueOf(res.getString("informacion_centrales")));
                solicitud.setEstudioStatus(EstudioStatus.valueOf(res.getString("estado_estudio")));
                solicitud.setSolicitudStatus(SolicitudStatus.valueOf(res.getString("estado_solicitud")));
                solicitud.setRnecStatus(RNECStatus.valueOf(res.getString("estado_registraduria")));
                solicitud.setEstudioStatus(EstudioStatus.valueOf(res.getString("estado_estudio")));
                solicitud.setValorAprobado(res.getLong("valor_aprobado"));
                solicitud.setPlazo(res.getInt("plazo"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        assertEquals("SOLICITUD_APROBADA", solicitud.getSolicitudStatus().name(), "Se verifica que la solicitud haya sido aprobada");
        assertEquals("RNEC_COMPLETADO", solicitud.getRnecStatus().name(), "Se verifica que la RNEC haya sido aprobada");
        assertEquals("APORTES_LINEA_VALIDADO", solicitud.getAportesLineaStatus().name(), "Se verifica que la Aportes haya sido aprobada");
        assertEquals("CENTRALES_COMPLETADO", solicitud.getCentralesStatus().name(), "Se verifica que la Centrales haya sido aprobada");
        assertEquals("ESTUDIO_APROBADO", solicitud.getEstudioStatus().name(), "Se verifica que la Estudio haya sido aprobada");
        assertEquals(60, solicitud.getPlazo(), "Se verifica el plazo aprobado");
        assertNotEquals(0, solicitud.getValorAprobado(), "Se verifica que la solicitud haya sido aprobada con un valor");
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
