/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author JuanCarlos
 */
public class Reserva implements Serializable{
    
    private int id;
    private Date createDate;
    private Date initDate;
    private Date endDate;
    private boolean executed;
    private Cliente cliente;
    private Vehiculo vehiculo;
    
    public Reserva(int id, Date createDate, Date initDate, Date endDate, boolean executed, Cliente cliente, Vehiculo vehiculo){
        this.id = id;
        this.createDate = createDate;
        this.initDate = initDate;
        this.endDate = endDate;
        this.executed = executed;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @return the initDate
     */
    public Date getInitDate() {
        return initDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the executed
     */
    public boolean isExecuted() {
        return executed;
    }

    /**
     * @return the nif
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return the matricula
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @param initDate the initDate to set
     */
    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @param executed the executed to set
     */
    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    /**
     * @param nif the nif to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        return sdf.format(this.createDate);
    }
}
