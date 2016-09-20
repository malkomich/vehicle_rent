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
public class GestorFacturas {

    public static ArrayList<Factura> getFacturasImpagadas(Cliente c) throws SQLException, ClassNotFoundException {
        ArrayList<Factura> facturas = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, -45);
        java.sql.Date fechaBloqueo = new java.sql.Date(cal.getTimeInMillis());
        String query = "select JUANGON.FACTURA.* from JUANGON.ALQUILER,JUANGON.FACTURA where "
                + "JUANGON.ALQUILER.CLIENTE = '" + c.getNif() + "' AND "
                + "JUANGON.ALQUILER.IDALQUILER = JUANGON.FACTURA.IDALQUILER AND "
                + "JUANGON.FACTURA.PENDIENTEDEPAGO = TRUE AND "
                + "JUANGON.FACTURA.FECHAFACTURA <= '" + fechaBloqueo + "'";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            facturas = new ArrayList<Factura>();
            do{
                int numFactura = result.getInt("NUMEROFACTURA");
                Date fechaFactura = result.getDate("FECHAFACTURA");
                float importe = result.getFloat("IMPORTE");
                boolean pendienteDePago = result.getString("PENDIENTEDEPAGO").equals("T");
                Date fechaCobro = result.getDate("FECHACOBRO");
                String concepto = result.getString("CONCEPTO");
                int idAlquiler = result.getInt("IDALQUILER");
                Alquiler alquiler = GestorReservas.getAlquilerByID(idAlquiler);
                Factura factura = new Factura(numFactura, fechaFactura, importe, pendienteDePago, fechaCobro, concepto, alquiler);
                facturas.add(factura);
            } while(result.next());
        }
        return facturas;
    }

    /**
     * Cuenta el número de registros Factura en la base de datos
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int countFactura() throws SQLException, ClassNotFoundException {
        int contador = 0;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select count(*) as NumberOfFacturas from JUANGON.FACTURA";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next())
            contador = result.getInt("NumberOfFacturas");
        return contador;
    }

    public static void appendFactura(Factura factura) throws SQLException, ClassNotFoundException {
        ConexionBD conexionBD = ConexionBD.getInstancia();
        java.sql.Date dateFactura = new java.sql.Date(factura.getFechaFactura().getTime());
        java.sql.Date dateCobro = (factura.getFechaCobro() != null ? new java.sql.Date(factura.getFechaCobro().getTime()) : null);
        String query = "insert into JUANGON.FACTURA values(" 
                + factura.getNumFactura() + ",'"
                + dateFactura + "',"
                + factura.getImporte() + ","
                + (factura.isPendientePago() ? "'T'" : "'F'") + ","
                + (dateCobro != null ? ("'" + dateCobro + "'") : null) + ",'"
                + factura.getConcepto() + "',"
                + factura.getAlquiler().getIdAlquiler() + ")";
        conexionBD.ejecutaInserta(query);
    }

    /**
     * Cuenta el número de registros Entrega en la base de datos
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int countEntrega() throws SQLException, ClassNotFoundException {
        int contador = 0;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select count(*) as NumberOfEntregas from JUANGON.ENTREGA";
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next())
            contador = result.getInt("NumberOfEntregas");
        return contador;
    }

    public static void appendEntrega(Entrega entrega) throws SQLException, ClassNotFoundException {
        ConexionBD conexionBD = ConexionBD.getInstancia();
        java.sql.Date dateEntrega = new java.sql.Date(entrega.getFechaEntrega().getTime());
        String query = "insert into JUANGON.ENTREGA values(" 
                + entrega.getIdEntrega()+ ",'"
                + dateEntrega + "',"
                + entrega.getRecargo()+ ","
                + (entrega.isIncidencias() ? "'T'" : "'F'") + ","
                + (entrega.isIncidencias() ? ("'" + entrega.getInformeIncidencias() + "'") : null) + ","
                + entrega.getAlquiler().getIdAlquiler() + ",'"
                + entrega.getRegistradaPor().getNumEmpleado()+ "')";
        conexionBD.ejecutaInserta(query);
    }

    static ArrayList<Factura> getFacturasByAlquiler(Alquiler alquiler) throws SQLException, ClassNotFoundException {
        ArrayList<Factura> facturas = null;
        ConexionBD conexionBD = ConexionBD.getInstancia();
        String query = "select JUANGON.FACTURA.* from JUANGON.ALQUILER,JUANGON.FACTURA where "
                + "JUANGON.FACTURA.IDALQUILER = JUANGON.ALQUILER.IDALQUILER AND "
                + "JUANGON.ALQUILER.IDALQUILER <= " + alquiler.getIdAlquiler();
        ResultSet result = conexionBD.ejecutaRecuperacion(query);
        if(result.next()){
            facturas = new ArrayList<Factura>();
            do{
                int numFactura = result.getInt("NUMEROFACTURA");
                Date fechaFactura = result.getDate("FECHAFACTURA");
                float importe = result.getFloat("IMPORTE");
                boolean pendienteDePago = result.getString("PENDIENTEDEPAGO").equals("T");
                Date fechaCobro = result.getDate("FECHACOBRO");
                String concepto = result.getString("CONCEPTO");
                Factura factura = new Factura(numFactura, fechaFactura, importe, pendienteDePago, fechaCobro, concepto, alquiler);
                facturas.add(factura);
            } while(result.next());
        }
        return facturas;
    }
    
}
