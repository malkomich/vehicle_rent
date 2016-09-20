/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;

/**
 *
 * @author JuanCarlos
 */
public class Cliente extends Usuario implements Serializable{
    
    private boolean bloqueado;

    public Cliente(String nif, String nombre, String passwd, String direccionPostal, int codigoPostal, String email, boolean bloqueado) {
        super(nif, nombre, passwd, direccionPostal, codigoPostal, email);
        this.bloqueado = bloqueado;
    }
    
    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
    
    @Override
    public String toString(){
        return ("Cliente " + super.getNif() + " - " + super.getNombre());
    }
    
}
