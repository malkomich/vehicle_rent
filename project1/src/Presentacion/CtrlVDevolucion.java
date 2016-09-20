/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Exceptions.InitializationException;
import Exceptions.InputError;
import Exceptions.UpdateException;
import Modelo.Alquiler;
import Modelo.CtrlCUDevolucion;
import Modelo.Factura;
import java.sql.SQLException;

/**
 *
 * @author JuanCarlos
 */
public class CtrlVDevolucion {

    private final VistaDevolucion vista;
    private CtrlCUDevolucion controller;
    
    public CtrlVDevolucion(VistaDevolucion vista) {
        this.vista = vista;
        try {
            this.controller = new CtrlCUDevolucion();
        } catch (InitializationException ex) {
            vista.showError(ex, "Error de Inicio");
        }
    }
    
    /**
     * Procesa la devolución del vehículo.
     * @param alquiler 
     */
    public void procesaEventoDevolucion(Alquiler alquiler) {
        Factura[] facts = null;
        float km = -1;
        String incidencia;
        boolean kmValido = false;
        if(alquiler == null)
            return;
        try {
            controller.crearEntrega(alquiler);
            
            while(km > -1 && !kmValido){
                try {
                    km = vista.getKilometraje();
                    kmValido = controller.comprobarKm(km);
                } catch(NumberFormatException ex) {
                    vista.showError(km + " no es un número de kilómetros válido", "Formato inválido");
                } catch (UpdateException ex) {
                    vista.showError(ex, "Kilometraje Inválido");
                }
            }
            
            incidencia = vista.getIncidencia();
            float precio = -1;
            if(incidencia != null )
                while (precio > 0)
                    precio = vista.getRecargo();
                controller.crearIncidencia(incidencia, precio);
            controller.actualizarEntrega(alquiler);
            facts = controller.recuperarFacturas(alquiler);
        } catch(SQLException ex){
            vista.showError("Error en la base de datos: " + ex, "SQL Error");
        } catch(ClassNotFoundException ex){
            vista.showError("Error irrecuperable en las clases del dominio: " + ex, "Class Not Found");
        } catch(NumberFormatException ex) {
            vista.showError(km + " no es un número de kilómetros válido", "Formato inválido");
        }
        if(facts != null)
            vista.setFacturas(facts);
    }

    /**
     * Muestra los datos de alquiler a partir de la matrícula introducida.
     */
    public void mostrarAlquiler() {
        String matricula = vista.getMatricula();
        if(matricula.isEmpty())
            return;
        Alquiler alquiler;
        try {
            alquiler = controller.recuperarAlquiler(matricula);
        } catch (SQLException ex) {
            vista.showError("Error en la base de datos: " + ex, "SQL Error");
            return;
        } catch (ClassNotFoundException ex) {
            vista.showError("Error irrecuperable en las clases del dominio: " + ex, "Class Not Found");
            return;
        } catch (InputError ex) {
            vista.showError(ex, "Vehículo no encontrado");
            return;
        }
        
        if(alquiler != null){
            vista.setAlquiler(alquiler);
        }else{
            vista.showInfo("El vehículo solicitado no se encuentra en alquiler.", "No alquilado!");
        }
    }
    
}
