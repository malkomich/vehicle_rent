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
public class Alquiler implements Serializable{
    
    private int idAlquiler;
    private Date fechaInicio;
    private Date fechaFin;
    private float precio;
    private float kilometrajeSalida;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private Empleado realizadoPor;
    
    public Alquiler(int idAlquiler, Date fechaInicio, Date fechaFin, float precio, float kilometrajeSalida, Vehiculo vehiculo, Cliente cliente, Empleado realizadoPor){
        this.idAlquiler = idAlquiler;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.kilometrajeSalida = kilometrajeSalida;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.realizadoPor = realizadoPor;
    }

    Alquiler(int idAlquiler, Date initDate, Date endDate, float precio, Vehiculo vehiculo, Cliente cliente, Empleado realizadoPor) {
        this(idAlquiler, initDate, endDate, precio, 0, vehiculo, cliente, realizadoPor);
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public float getPrecio() {
        return precio;
    }

    public float getKilometrajeSalida() {
        return kilometrajeSalida;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getRealizadoPor() {
        return realizadoPor;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setKilometrajeSalida(float kilometrajeSalida) {
        this.kilometrajeSalida = kilometrajeSalida;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setRealizadoPor(Empleado realizadoPor) {
        this.realizadoPor = realizadoPor;
    }
}
