/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Exceptions.InitializationException;
import Exceptions.LoginException;
import Exceptions.UpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author JuanCarlos
 */
public class CtrCUAlquiler {
    Reserva currentReserva;
    Alquiler alquiler;
    Factura factura;
    Vehiculo vehiculo;
    int numAlquileres, numFacturas;
    Empleado empleado;

    public CtrCUAlquiler() throws InitializationException{
        try {
            numAlquileres = GestorReservas.countAlquiler();
            empleado = GestorUsuarios.getDefaultEmpleado();
            numFacturas = GestorFacturas.countFactura();
        } catch (SQLException ex) {
            throw new InitializationException("Error en la base de datos: " + ex);
        } catch (ClassNotFoundException ex) {
            throw new InitializationException("Error irrecuperable en las clases del dominio: " + ex);
        }
    }
    
    /**
     * Comprueba el dni introducido y devuelve un array de reservas. 
     * @param dni DNI del Cliente.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws LoginException 
     */
    public Reserva[] recuperarReservas(String dni) throws SQLException, ClassNotFoundException, LoginException {
        
        Cliente c = GestorUsuarios.recuperaClientePorId(dni);
        if(c == null){
            throw new LoginException("El DNI " + dni + " no está registrado en nuestra base de datos.");
        }
        ArrayList reservasList = GestorReservas.recuperarReservas(c);
        if(reservasList == null)
            return null;
        Reserva[] reservas = new Reserva[reservasList.size()];
        Iterator it = reservasList.iterator();
        
        int i = 0;
        while(it.hasNext()){
            reservas[i++] = (Reserva) it.next();
        }
        
        return reservas;
    }

    /**
     * Crea el registro de alquiler y la factura correspondientes.
     * @param r Reserva a ejecutar. 
     */
    public void crearAlquiler(Reserva r) {
        
        //Pasar estado de la Reserva a ejecutada
        this.currentReserva = r;
        currentReserva.setExecuted(true);
        
        //Crear el objeto Alquiler
        numAlquileres++;
        int diffInDays = (int)( (r.getEndDate().getTime() - r.getInitDate().getTime()) / (1000 * 60 * 60 * 24) );
        float precio = r.getVehiculo().getModelo().getCosteAlquiler() * diffInDays;
        alquiler = new Alquiler(numAlquileres, r.getInitDate(), r.getEndDate(), precio, r.getVehiculo(), r.getCliente(), empleado);
        
        //Crear factura pendiente de pago
        numFacturas++;
        factura = new Factura(numFacturas, new Date(), precio, true, null, "A", alquiler);
    }
    
    /**
     * Comprueba el kilometraje, y lo actualiza en el vehiculo.
     * @param km
     * @throws UpdateException 
     */
    public void setKilometraje(float km) throws UpdateException{
        vehiculo = currentReserva.getVehiculo();
        if(km <= vehiculo.getKilometraje())
            throw new UpdateException("El kilometraje debe ser mayor que el actual.");
        vehiculo.setKilometraje(km);
    }

    /**
     * Aplica persistencia de los datos en la base de datos.
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void guardarDatos() throws SQLException, ClassNotFoundException {
        //Actualizar base de datos
        GestorReservas.updateVehiculo(vehiculo);
        GestorReservas.updateReserva(currentReserva);
        GestorReservas.appendAlquiler(alquiler);
        GestorFacturas.appendFactura(factura);
    }
}
