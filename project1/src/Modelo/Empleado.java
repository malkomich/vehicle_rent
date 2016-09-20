/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author JuanCarlos
 */
public class Empleado extends Usuario implements Serializable{
    
    private String numEmpleado;
    private Date fechaContratacion;
    private String tipoEmpleado;

    public Empleado(String nif, String nombre, String passwd, String direccionPostal, int codigoPostal, String email, String numEmpleado, Date fechaContratacion, String tipoEmpleado) {
        super(nif, nombre, passwd, direccionPostal, codigoPostal, email);
        this.numEmpleado = numEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
    
}
