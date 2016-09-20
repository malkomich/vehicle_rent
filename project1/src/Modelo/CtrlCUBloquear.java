/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author JuanCarlos
 */
public class CtrlCUBloquear {
    Cliente[] clientes;
    
    /**
     * Devuelve un array de clientes con facturas impagadas de más de 45 días de antigüedad.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Cliente[] buscarClientes() throws SQLException, ClassNotFoundException{
        ArrayList clientesList = GestorUsuarios.buscarClientesBloqueables();
        if(clientesList == null)
            return null;
        clientes = new Cliente[clientesList.size()];
        Iterator it = clientesList.iterator();
        
        int i = 0;
        while(it.hasNext()){
            clientes[i++] = (Cliente) it.next();
        }
        
        return clientes;
    }

    /**
     * Devuelve las facturas de un determinado cliente.
     * @param c
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Factura[] buscarFacturas(Cliente c) throws SQLException, ClassNotFoundException {
        Factura[] facturas;
        ArrayList<Factura> facturasList = GestorFacturas.getFacturasImpagadas(c);
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
     * Actualiza el estado de un cliente a bloqueado.
     * @param cliente
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void bloquearCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        cliente.setBloqueado(true);
        GestorUsuarios.actualizarCliente(cliente);
    }
    
}
