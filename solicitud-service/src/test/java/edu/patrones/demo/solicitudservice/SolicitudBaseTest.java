package edu.patrones.demo.solicitudservice;

import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.centrales.CentralesStatus;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudStatus;
import edu.patrones.demo.solicitudservice.model.Solicitud;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;

public abstract class SolicitudBaseTest {

    protected WebDriver driver;

    private String url = "http://127.0.0.1:8088";

    // Connection object
    static Connection con = null;
    // Statement object
    protected static PreparedStatement preparedStatement;
    // Constant for Database URL
    public static String DB_URL = "jdbc:mysql://localhost:3306/db_solicitudes";
    //Database Username
    public static String DB_USER = "root";
    // Database Password
    public static String DB_PASSWORD = "root12345";

    @BeforeAll
    public static void setUpBaseTest(){

    }

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);

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

    public Solicitud getUltimaSolicitud(String clienteID){

        Solicitud solicitud = new Solicitud();

        try{
            preparedStatement.setString(1, clienteID);
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

        return solicitud;
    }

    @AfterEach
    public void tearDown() throws SQLException {
        driver.quit();
        // Close DB connection
        if (con != null) {
            con.close();
        }
    }
}
