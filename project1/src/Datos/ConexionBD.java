/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 *
 * @author JuanCarlos
 */

public class ConexionBD {

    // Este es el singleton
    private static ConexionBD laConexion;

    private final String urlBD = "jdbc:derby://localhost:1527/DS_Database";
    private final String userName = "juangon";
    private final String password = "12345";
    private final String driverName = "org.apache.derby.jdbc.ClientDriver";

    private Connection conexion;

    // Garantiza que el cliente no cree objetos
    private ConexionBD() throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        conexion = DriverManager.getConnection(urlBD, userName, password);
    }

    // devuelve el singleton
    public static ConexionBD getInstancia() throws SQLException, ClassNotFoundException {
        if (laConexion==null) laConexion = new ConexionBD();
        return laConexion;
    }

    public void cerrar() throws SQLException {
        conexion.close();
    }

    public void ejecutaInserta(String sql) throws SQLException {

        Statement st = conexion.createStatement();

        st.executeUpdate(sql);
    }

    public ResultSet ejecutaRecuperacion(String sql) throws SQLException {

        Statement st = conexion.createStatement();

        ResultSet resultado = st.executeQuery(sql);

        return resultado;
    }

    public static String getCurrentTimeStamp() {
        Timestamp ahora = new Timestamp(System.currentTimeMillis());
        return ahora.toString();
    }

    public static String getTimeStamp(GregorianCalendar momento) {
        Timestamp ahora = new Timestamp(momento.getTimeInMillis());
        return ahora.toString();
    }

}
