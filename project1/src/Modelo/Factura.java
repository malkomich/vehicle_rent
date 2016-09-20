/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JuanCarlos
 */
public class Factura implements Serializable{
    
    private int numFactura;
    private Date fechaFactura;
    private float importe;
    private boolean pendientePago;
    private Date fechaCobro;
    private String concepto;
    private Alquiler alquiler;
    
    public Factura(int numFactura, Date fechaFactura, float importe, boolean pendientePago, Date fechaCobro, String concepto, Alquiler alquiler){
        this.numFactura = numFactura;
        this.fechaFactura = fechaFactura;
        this.importe = importe;
        this.pendientePago = pendientePago;
        this.fechaCobro = fechaCobro;
        this.concepto = concepto;
        this.alquiler = alquiler;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public float getImporte() {
        return importe;
    }

    public boolean isPendientePago() {
        return pendientePago;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public String getConcepto() {
        return concepto;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public void setPendientePago(boolean pendientePago) {
        this.pendientePago = pendientePago;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
        return ("Factura de " + (this.concepto == "I" ? "Incidencia - " : "Alquiler - ") + sdf.format(this.fechaFactura) + " (" + moneyFormatter.format(this.importe) + ")");
    }
}
