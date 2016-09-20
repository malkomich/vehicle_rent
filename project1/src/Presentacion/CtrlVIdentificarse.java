/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Exceptions.LoginException;
import Modelo.CtrCUIdentificarse;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanCarlos
 */
public class CtrlVIdentificarse {
    
    private VistaIdentificarse vista;
    private CtrCUIdentificarse controller;

    public CtrlVIdentificarse(VistaIdentificarse vista) {
        this.vista = vista;
        this.controller = new CtrCUIdentificarse();
        
    }
    
    public void procesaEventoIdentificarse() {
        String uId = vista.getIdUsuario();
        System.out.println("usuario: " + uId);
        String uPass = vista.getPassUsuario();
        
        Usuario u = null;
        try {
            u = controller.identificarUsuario(uId, uPass);
        } catch (LoginException ex) {
            vista.setLabel(ex.toString());
        } catch (SQLException ex) {
            Logger.getLogger(CtrlVIdentificarse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CtrlVIdentificarse.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(u != null){
            vista.setLabel("Bienvenido "+u.getNombre());
            vista.removeIcon();
        } else{
            vista.setLabel("Datos erróneos. Usuario no encontrado.");
            vista.setIcon();
        }
    }
}
