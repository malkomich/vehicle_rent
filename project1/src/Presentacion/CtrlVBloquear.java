/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Modelo.CtrlCUBloquear;
import Modelo.Factura;
import Modelo.Cliente;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author JuanCarlos
 */
public class CtrlVBloquear {

    private final VistaBloquear vista;
    private final CtrlCUBloquear controller;
    
    private Cliente[] clientes;
    
    /**
    * Constructor genérico
    * @param vista Clase vista correspondiente a la interfaz gráfica(GUI).
    */
    public CtrlVBloquear(VistaBloquear vista) {
        this.vista = vista;
        this.controller = new CtrlCUBloquear();
    }
    
    /**
     * Cambia el estado del cliente a bloqueado y actualiza el array de clientes a mostrar en la vista.
     * @param cliente
     */
    public void procesaEventoBloquear(Cliente cliente) {
        if(cliente == null)
            return;
        try {
            controller.bloquearCliente(cliente);
        } catch (SQLException ex) {
            vista.showError("Error en la base de datos: " + ex, "SQL Error");
            return;
        } catch (ClassNotFoundException ex) {
            vista.showError("Error irrecuperable en las clases del dominio: " + ex, "Class Not Found");
            return;
        }
        List clientes_temp = new LinkedList();
        for(Cliente c : clientes)
            if(!c.equals(cliente))
                clientes_temp.add(c);
        clientes = (Cliente[]) clientes_temp.toArray();
        vista.setClientes(clientes);
    }
    
    /**
     * Obtiene y muestra los detalles del cliente y su lista de facturas impagadas en la vista.
     * @param cliente
     */
    public void mostrarDetalles(Cliente cliente){
        Factura[] facts;
        try {
            facts = controller.buscarFacturas(cliente);
        } catch (SQLException ex) {
            vista.showError("Error en la base de datos: " + ex, "SQL Error");
            return;
        } catch (ClassNotFoundException ex) {
            vista.showError("Error irrecuperable en las clases del dominio: " + ex, "Class Not Found");
            return;
        }
        
        if(facts != null){
            vista.setFacturas(facts);
        }else{
            vista.showInfo("No se han encontrado facturas impagadas de más de 45 días de antigüedad de este cliente.", "No hay facturas");
        }
    }

    /**
     * Obtiene y muestra la lista de clientes en la vista.
     */
    void mostrarClientes() {
        try{
             clientes = controller.buscarClientes();
        } catch(SQLException ex){
            vista.showError("Error en la base de datos: " + ex, "SQL Error");
            return;
        } catch(ClassNotFoundException ex){
            vista.showError("Error irrecuperable en las clases del dominio: " + ex, "Class Not Found");
            return;
        }
        if(clientes != null){
            vista.setClientes(clientes);
        } else{
            vista.showInfo("No se han encontrado clientes con impagos de más de 45 días de antigüedad.", "No hay clientes");
        }
    }
    
}
