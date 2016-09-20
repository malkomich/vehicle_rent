/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Exceptions.InitializationException;
import Exceptions.LoginException;
import Exceptions.UpdateException;
import Modelo.CtrCUAlquiler;
import Modelo.Reserva;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanCarlos
 */
class CtrlVAlquiler{

    private final VistaAlquiler vista;
    private CtrCUAlquiler controller;
    Reserva[] reservas;
    
    /**
    * Constructor genérico
    * @param vista Clase vista correspondiente a la interfaz gráfica(GUI).
    */
    public CtrlVAlquiler(VistaAlquiler vista) {
        this.vista = vista;
        try{
            this.controller = new CtrCUAlquiler();
        } catch (InitializationException ex) {
            vista.showError(ex, "Error de Inicio");
        }
    }

    /**
    * Muestra la lista de reservas a partir de un cliente dado.
    */
    void procesaMostrarReservas(){
        String dni = vista.getDni();
        try{
            reservas = controller.recuperarReservas(dni);
        } catch(SQLException ex){
            vista.showError("Error en la base de datos: " + ex, "SQL Error");
            return;
        } catch(ClassNotFoundException ex){
            vista.showError("Error irrecuperable en las clases del dominio: " + ex, "Class Not Found");
            return;
        } catch (LoginException ex) {
            vista.showError(ex, "Client Not Found");
            return;
        }
        
        if(reservas != null){
            vista.setReservas(reservas);
        } else{
            JOptionPane.showMessageDialog(vista,"No se han encontrado reservas con inicio a fecha de hoy del cliente solicitado. Proceda a realizar la reserva.", "No hay reservas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
    * Crea el registro de alquiler y actualiza los datos.
    * @param r Reserva a ejecutar.
    */
    void procesaCrearAlquiler(Reserva r) {
        float km = -1;
        boolean kmValido = false;
        
        if(r == null)
            return;
        controller.crearAlquiler(r);
        while(km > -1 && !kmValido){
            try {
                km = vista.getKilometraje();
                controller.setKilometraje(km);
                kmValido = true;
            } catch(NumberFormatException ex) {
                vista.showError(km + " no es un número de kilómetros válido", "Formato inválido");
            } catch (UpdateException ex) {
                vista.showError(ex, "Kilometraje Inválido");
            }
        }
        try{
            controller.guardarDatos();
        } catch (SQLException ex) {
            vista.showError("Error en la base de datos: " + ex, "SQL Error");
            return;
        } catch (ClassNotFoundException ex) {
            vista.showError("Error irrecuperable en las clases del dominio: " + ex, "Class Not Found");
            return;
        }
        vista.updateGUI(r);
    }
    
}
