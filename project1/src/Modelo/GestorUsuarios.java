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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author JuanCarlos
 */
public class GestorUsuarios {
    public static Cliente recuperaClientePorId(String id) throws SQLException, ClassNotFoundException{
        Cliente cliente = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.CLIENTE, JUANGON.USUARIO where JUANGON.CLIENTE.NIF = '" + id + "' AND "
                + "JUANGON.CLIENTE.NIF = JUANGON.USUARIO.NIF";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            String nif = result.getString("NIF");
            String nombre = result.getString("NOMBRE");
            String passwd = result.getString("PASSWORD");
            String direccion = result.getString("DIRECCIONPOSTAL");
            int codPostal = result.getInt("CODIGOPOSTAL");
            String email = result.getString("DIRECCIONELECTRONICA");
            boolean bloqueado = (result.getString("BLOQUEADO") == "T");
            cliente = new Cliente(nif, nombre, passwd, direccion, codPostal, email, bloqueado);
        }
        return cliente;
    }
    
    public static ArrayList<Cliente> buscarClientesBloqueables() throws SQLException, ClassNotFoundException {
        ArrayList<Cliente> clientes = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, -45);
        java.sql.Date fechaBloqueo = new java.sql.Date(cal.getTimeInMillis());
        String query = "select DISTINCT JUANGON.USUARIO.*,JUANGON.CLIENTE.BLOQUEADO from JUANGON.CLIENTE,JUANGON.ALQUILER,JUANGON.FACTURA,JUANGON.USUARIO where "
                + "JUANGON.USUARIO.NIF = JUANGON.CLIENTE.NIF AND "
                + "JUANGON.CLIENTE.NIF = JUANGON.ALQUILER.CLIENTE AND "
                + "JUANGON.ALQUILER.IDALQUILER = JUANGON.FACTURA.IDALQUILER AND "
                + "JUANGON.FACTURA.PENDIENTEDEPAGO = TRUE AND "
                + "JUANGON.FACTURA.FECHAFACTURA <= '" + fechaBloqueo + "'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            clientes = new ArrayList<Cliente>();
            do{
                String nif = result.getString("NIF");
                String nombre = result.getString("NOMBRE");
                String passwd = result.getString("PASSWORD");
                String direccion = result.getString("DIRECCIONPOSTAL");
                int codPostal = result.getInt("CODIGOPOSTAL");
                String email = result.getString("DIRECCIONELECTRONICA");
                boolean bloqueado = (result.getString("BLOQUEADO") == "T");
                Cliente cliente = new Cliente(nif, nombre, passwd, direccion, codPostal, email, bloqueado);
                clientes.add(cliente);
            } while(result.next());
        }
        return clientes;
    }

    public static Cliente getCliente(String nif) throws SQLException, ClassNotFoundException {
        Cliente cliente = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.CLIENTE, JUANGON.USUARIO where "
                + "JUANGON.USUARIO.NIF = JUANGON.CLIENTE.NIF AND "
                + "JUANGON.CLIENTE.NIF = '" + nif + "'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            String nombre = result.getString("NOMBRE");
            String passwd = result.getString("PASSWORD");
            String direccion = result.getString("DIRECCIONPOSTAL");
            int codPostal = result.getInt("CODIGOPOSTAL");
            String email = result.getString("DIRECCIONELECTRONICA");
            boolean bloqueado = (result.getString("BLOQUEADO") == "T");
            cliente = new Cliente(nif, nombre, passwd, direccion, codPostal, email, bloqueado);
        }
        return cliente;
    }
    
    public static Empleado getDefaultEmpleado() throws SQLException, ClassNotFoundException{
        Empleado empleado = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.EMPLEADO, JUANGON.USUARIO where "
                + "JUANGON.USUARIO.NIF = JUANGON.EMPLEADO.NIF";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            String nif = result.getString("NIF");
            String nombre = result.getString("NOMBRE");
            String passwd = result.getString("PASSWORD");
            String direccion = result.getString("DIRECCIONPOSTAL");
            int codPostal = result.getInt("CODIGOPOSTAL");
            String email = result.getString("DIRECCIONELECTRONICA");
            String numEmpleado = result.getString("NUMEROEMPLEADO");
            Date fechaContrato = result.getDate("FECHACONTRATACION");
            String tipo = result.getString("TIPOEMPLEADO");
            empleado = new Empleado(nif, nombre, passwd, direccion, codPostal, email, numEmpleado, fechaContrato, tipo);
        }
        return empleado;
    }

    public static Empleado getEmpleado(String num) throws SQLException, ClassNotFoundException {
        Empleado empleado = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select * from JUANGON.EMPLEADO, JUANGON.USUARIO where "
                + "JUANGON.USUARIO.NIF = JUANGON.EMPLEADO.NIF AND "
                + "JUANGON.EMPLEADO.NUMEROEMPLEADO = '" + num + "'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            String nif = result.getString("NIF");
            String nombre = result.getString("NOMBRE");
            String passwd = result.getString("PASSWORD");
            String direccion = result.getString("DIRECCIONPOSTAL");
            int codPostal = result.getInt("CODIGOPOSTAL");
            String email = result.getString("DIRECCIONELECTRONICA");
            Date fechaContratacion = result.getDate("FECHACONTRATACION");
            String tipo = result.getString("TIPOEMPLEADO");
            empleado = new Empleado(nif, nombre, passwd, direccion, codPostal, email, num, fechaContratacion, tipo);
        }
        return empleado;
    }

    public static void actualizarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "UPDATE JUANGON.CLIENTE SET BLOQUEADO=" + (cliente.isBloqueado() ? "'T'" : "'F'")
                + " WHERE NIF=" + cliente.getNif();
        conexionBD.ejecutaInserta(query);
    }
}
