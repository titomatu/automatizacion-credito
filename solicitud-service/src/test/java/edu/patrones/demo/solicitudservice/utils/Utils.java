package edu.patrones.demo.solicitudservice.utils;

import edu.patrones.demo.event.aportes.AportesLineaStatus;
import edu.patrones.demo.event.centrales.CentralesStatus;
import edu.patrones.demo.event.estudio.EstudioStatus;
import edu.patrones.demo.event.rnec.RNECStatus;
import edu.patrones.demo.event.solicitud.SolicitudStatus;
import edu.patrones.demo.solicitudservice.model.Solicitud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Utils {

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

    public static Solicitud obtenerSolicitud(String clienteID){
        Solicitud solicitud = new Solicitud();

        try{
            // Database connection
            String dbClass = "com.mysql.cj.jdbc.Driver";
            Class.forName(dbClass).newInstance();
            // Get connection to DB
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Statement object to send the SQL statement to the Database
            String query = "SELECT * FROM db_solicitudes.solicitud_prestamo WHERE cliente_numero_documento = ? ORDER BY creation_date DESC LIMIT 1;";
            preparedStatement = con.prepareStatement(query);

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
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return solicitud;
    }
}
