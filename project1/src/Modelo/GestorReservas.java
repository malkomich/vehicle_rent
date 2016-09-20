/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Datos.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author JuanCarlos
 */
public class GestorReservas {

    public static ArrayList recuperarReservas(Cliente c) throws SQLException, ClassNotFoundException {
        ArrayList reservas = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        Date dte = new Date();
        java.sql.Date dbDate = new java.sql.Date(dte.getTime());
        String query = "select * from JUANGON.RESERVA where NIF = '" + c.getNif() + "' AND "
                + "EJECUTADA = 'F' AND "
                + "FECHAINICIOALQUILER = '" + dbDate + "'";
        //Para pruebas hemos utilizado la sentencia siguiente:
//        String query = "select * from JUANGON.RESERVA where NIF = '" + c.getNif() + "' AND "
//                + "EJECUTADA = 'F'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            reservas = new ArrayList();
            do{
                int id = result.getInt("IDRESERVA");
                Date createDate = result.getDate("FECHARESERVA");
                Date initDate = result.getDate("FECHAINICIOALQUILER");
                Date endDate = result.getDate("FECHAFINALQUILER");
                boolean executed = result.getBoolean("EJECUTADA");
                String matricula = result.getString("MATRICULA");
                Vehiculo vehiculo = getVehiculo(matricula);
                Reserva reserva = new Reserva(id, createDate, initDate, endDate, executed, c, vehiculo);
                reservas.add(reserva);
            } while(result.next());
        }
        return reservas;
    }
    
    public static Vehiculo getVehiculo(String matricula) throws SQLException, ClassNotFoundException {
        Vehiculo vehiculo = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.VEHICULO where MATRICULA = '" + matricula + "'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            String color = result.getString("COLOR");
            float kilometraje = result.getFloat("KILOMETRAJE");
            Date fechaAdquisicion = result.getDate("FECHAADQUISICION");
            float costeAdquisicion = result.getFloat("COSTEADQUISICION");
            String averiado = result.getString("AVERIADO");
            String idModelo = result.getString("IDMODELO");
            Modelo modelo = getModelo(idModelo);
            vehiculo = new Vehiculo(matricula, color, kilometraje, fechaAdquisicion, costeAdquisicion, averiado, modelo);
        }
        return vehiculo;
    }
    
    public static Modelo getModelo(String idModelo) throws SQLException, ClassNotFoundException {
        Modelo modelo = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.MODELO where IDMODELO = '" + idModelo + "'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            String nombre = result.getString("NOMBRE");
            String fabricante = result.getString("FABRICANTE");
            float costeAlquiler = result.getFloat("COSTEALQUILER");
            float largo = result.getFloat("LARGO");
            float ancho = result.getFloat("ANCHO");
            float peso = result.getFloat("PESO");
            int puertas = result.getInt("PUERTAS");
            int año = result.getInt("AÑO");
            String gps = result.getString("GPS");
            modelo = new Modelo(idModelo, nombre, fabricante, costeAlquiler, largo, ancho, peso, puertas, año, gps);
        }
        return modelo;
    }

    public static void appendAlquiler(Alquiler alquiler) throws SQLException, ClassNotFoundException {
        ConexionBD conexionBD = ConexionBD.getInstancia();
        java.sql.Date dateIni = new java.sql.Date(alquiler.getFechaInicio().getTime());
        java.sql.Date dateFin = new java.sql.Date(alquiler.getFechaFin().getTime());
        String query = "insert into JUANGON.ALQUILER values(" 
                + alquiler.getIdAlquiler() + ",'"
                + dateIni + "','"
                + dateFin + "',"
                + alquiler.getPrecio() + ","
                + alquiler.getKilometrajeSalida() + ",'"
                + alquiler.getVehiculo().getMatricula() + "','"
                + alquiler.getCliente().getNif() + "','"
                + alquiler.getRealizadoPor().getNumEmpleado()+ "')";
        conexionBD.ejecutaInserta(query);
    }
    
    /**
     * Cuenta el número de registros Alquiler en la base de datos
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int countAlquiler() throws SQLException, ClassNotFoundException{
        int contador = 0;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select count(*) as NumberOfAlquileres from JUANGON.ALQUILER";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next())
            contador = result.getInt("NumberOfAlquileres");
        return contador;
    }

    public static void updateVehiculo(Vehiculo vehiculo) throws SQLException, ClassNotFoundException {
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "UPDATE JUANGON.VEHICULO SET KILOMETRAJE=" + vehiculo.getKilometraje() + ", AVERIADO='" + vehiculo.getAveriado()
                + "' WHERE MATRICULA='" + vehiculo.getMatricula() + "'";
        conexionBD.ejecutaInserta(query);
    }

    public static void updateReserva(Reserva r) throws SQLException, ClassNotFoundException {
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "UPDATE JUANGON.RESERVA SET EJECUTADA=" + (r.isExecuted() ? "'T'" : "'F'")
                + " WHERE IDRESERVA=" + r.getId();
        conexionBD.ejecutaInserta(query);
    }

    public static Alquiler getAlquilerByID(int idAlquiler) throws SQLException, ClassNotFoundException {
        Alquiler alquiler = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.ALQUILER where IDALQUILER = " + idAlquiler;
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            Date fechaInicio = result.getDate("FECHAINICIO");
            Date fechaFin = result.getDate("FECHAFIN");
            float precio = result.getFloat("PRECIO");
            float kilometraje = result.getFloat("KILOMETRAJESALIDA");
            String matricula = result.getString("MATRICULA");
            String nifCliente = result.getString("CLIENTE");
            String numEmpleado = result.getString("REALIZADOPOR");
            Vehiculo vehiculo = getVehiculo(matricula);
            Cliente cliente = GestorUsuarios.getCliente(nifCliente);
            Empleado empleado = GestorUsuarios.getEmpleado(numEmpleado);
            alquiler = new Alquiler(idAlquiler, fechaInicio, fechaFin, precio, kilometraje, vehiculo, cliente, empleado);
        }
        return alquiler;
    }

    public static Alquiler getAlquilerActual(Vehiculo vehiculo) throws SQLException, ClassNotFoundException {
        Alquiler alquiler = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.ALQUILER where MATRICULA = '" + vehiculo.getMatricula() + "'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            int idAlquiler = result.getInt("IDALQUILER");
            Date fechaInicio = result.getDate("FECHAINICIO");
            Date fechaFin = result.getDate("FECHAFIN");
            float precio = result.getFloat("PRECIO");
            float kilometraje = result.getFloat("KILOMETRAJESALIDA");
            String nifCliente = result.getString("CLIENTE");
            String numEmpleado = result.getString("REALIZADOPOR");
            Cliente cliente = GestorUsuarios.getCliente(nifCliente);
            Empleado empleado = GestorUsuarios.getEmpleado(numEmpleado);
            alquiler = new Alquiler(idAlquiler, fechaInicio, fechaFin, precio, kilometraje, vehiculo, cliente, empleado);
        }
        return alquiler;
    } 
}
