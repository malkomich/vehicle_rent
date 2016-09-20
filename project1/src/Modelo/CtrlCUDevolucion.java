/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Exceptions.InitializationException;
import Exceptions.InputError;
import Exceptions.UpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author JuanCarlos
 */
public class CtrlCUDevolucion {

    private final float RECARGOPORDIA = 10;
    private final float MAXKILOMETROSPORDIA = 50;
    private final float RECARGOPORKILOMETRO = 1;
    
    Entrega entrega;
    int numFacturas;
    Date today;
    float recargo;

    public CtrlCUDevolucion() throws InitializationException {
        today = new Date();
        try {
            numFacturas = GestorFacturas.countFactura();
        } catch (SQLException ex) {
            throw new InitializationException("Error en la base de datos: " + ex);
        } catch (ClassNotFoundException ex) {
            throw new InitializationException("Error irrecuperable en las clases del dominio: " + ex);
        }
    }
    
    /**
     * Comprueba la matrícula introducida y devuelve el alquiler actual del vehiculo.
     * @param matricula
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InputError 
     */
    public Alquiler recuperarAlquiler(String matricula) throws SQLException, ClassNotFoundException, InputError {
        Vehiculo vehiculo = GestorReservas.getVehiculo(matricula);
        if(vehiculo == null){
            throw new InputError("No hay ningún vehículo de matrícula " + matricula + " registrado en nuestra base de datos.");
        }
        Alquiler alquiler = GestorReservas.getAlquilerActual(vehiculo);
        if(alquiler == null)
            return null;
        return alquiler;
    }

    /**
     * Aplica persistencia de los datos de la entrega creada.
     * @param alquiler
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void actualizarEntrega(Alquiler alquiler) throws SQLException, ClassNotFoundException {
        //Aplicar la persistencia en la base de datos del objeto Entrega
        GestorFacturas.appendEntrega(entrega);
    }
    
    /**
     * Comprueba la validez del kilometraje introducido, crea una incidencia
     * si se ha sobrepasado el máximo de kilómetros, y se actualiza el kilometraje
     * del vehículo.
     * @param km
     * @return
     * @throws UpdateException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean comprobarKm(float km) throws UpdateException, SQLException, ClassNotFoundException{
        //Actualizar kilometraje en vehículo
        if(km <= entrega.getAlquiler().getKilometrajeSalida())
            throw new UpdateException("El kilometraje debe ser mayor al de salida.");
        int diffInDays = (int)( (today.getTime() - entrega.getAlquiler().getFechaFin().getTime()) / (1000 * 60 * 60 * 24) );
        float kmExcedidos = (km - entrega.getAlquiler().getKilometrajeSalida()) - (MAXKILOMETROSPORDIA * diffInDays);
        if(kmExcedidos > 0){
            if(!entrega.isIncidencias())
                entrega.setIncidencias(true);
            recargo = kmExcedidos * RECARGOPORKILOMETRO;
            entrega.setRecargo(entrega.getRecargo() + recargo);
            numFacturas++;
            Factura facturaIncidencia = new Factura(numFacturas, today, recargo, true, null, "I", entrega.getAlquiler());
            GestorFacturas.appendFactura(facturaIncidencia);
        }
        Vehiculo v = entrega.getAlquiler().getVehiculo();
        v.setKilometraje(km);
        GestorReservas.updateVehiculo(v);
        
        return true;
    }

    /**
     * Se crea un objeto Entrega, y se registra una incidencia en caso de
     * devolución tardía del vehículo.
     * @param alquiler
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void crearEntrega(Alquiler alquiler) throws SQLException, ClassNotFoundException {
        int indexEntrega = GestorFacturas.countEntrega() + 1;
        if(alquiler.getFechaFin().before(today)){
            int diffInDays = (int)( (today.getTime() - alquiler.getFechaFin().getTime()) / (1000 * 60 * 60 * 24) );
            recargo = RECARGOPORDIA * diffInDays;
            entrega = new Entrega(indexEntrega, today, recargo, true, null, alquiler, GestorUsuarios.getDefaultEmpleado());
            numFacturas++;
            Factura facturaIncidencia = new Factura(numFacturas, today, recargo, true, null, "I", alquiler);
            GestorFacturas.appendFactura(facturaIncidencia);
        } else{
            entrega = new Entrega(indexEntrega, today, alquiler, GestorUsuarios.getDefaultEmpleado());
        }
    }

    /**
     * Recupera las facturas asociadas a un alquiler.
     * @param alquiler
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Factura[] recuperarFacturas(Alquiler alquiler) throws SQLException, ClassNotFoundException {
        Factura[] facturas;
        ArrayList<Factura> facturasList = GestorFacturas.getFacturasByAlquiler(alquiler);
        if(facturasList == null)
            return null;
        facturas = new Factura[facturasList.size()];
        Iterator it = facturasList.iterator();
        
        int i = 0;
        while(it.hasNext()){
            facturas[i++] = (Factura) it.next();
        }
        
        return facturas;
    }

    /**
     * Registra la incidencia introducida manualmente.
     * @param incidencia
     * @param recargo
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void crearIncidencia(String incidencia, float recargo) throws SQLException, ClassNotFoundException {
        if(!entrega.isIncidencias())
            entrega.setIncidencias(true);
        entrega.setInformeIncidencias(incidencia);
        entrega.setRecargo(entrega.getRecargo() + recargo);
        numFacturas++;
        Factura facturaIncidencia = new Factura(numFacturas, today, recargo, true, null, "I", entrega.getAlquiler());
        GestorFacturas.appendFactura(facturaIncidencia);
    }
    
}
