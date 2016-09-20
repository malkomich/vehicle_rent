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
public class Entrega implements Serializable{
        
    private int idEntrega;
    private Date fechaEntrega;
    private float recargo;
    private boolean incidencias;
    private String informeIncidencias;
    private Alquiler alquiler;
    private Empleado registradaPor;
    
    public Entrega(int idEntrega, Date fechaEntrega, float recargo, boolean incidencias, String informeIncidencias, Alquiler alquiler, Empleado registradaPor){
        this.idEntrega = idEntrega;
        this.fechaEntrega = fechaEntrega;
        this.recargo = recargo;
        this.incidencias = incidencias;
        this.informeIncidencias = informeIncidencias;
        this.alquiler = alquiler;
        this.registradaPor = registradaPor;
    }

    public Entrega(int idEntrega, Date date, Alquiler alquiler, Empleado registradaPor) {
        this(idEntrega, date, 0, false, null, alquiler, registradaPor);
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public float getRecargo() {
        return recargo;
    }

    public boolean isIncidencias() {
        return incidencias;
    }

    public String getInformeIncidencias() {
        return informeIncidencias;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public Empleado getRegistradaPor() {
        return registradaPor;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setRecargo(float recargo) {
        this.recargo = recargo;
    }

    public void setIncidencias(boolean incidencias) {
        this.incidencias = incidencias;
    }

    public void setInformeIncidencias(String informeIncidencias) {
        this.informeIncidencias = informeIncidencias;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public void setRegistradaPor(Empleado registradaPor) {
        this.registradaPor = registradaPor;
    }
    
    @Override
    public String toString(){
        return ("Entrega " + (this.idEntrega) );
    }
}
