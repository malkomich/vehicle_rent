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
public class Vehiculo implements Serializable{
    
    private String matricula;
    private String color;
    private float kilometraje;
    private Date fechaAdquisicion;
    private float costeAdquisicion;
    private String averiado;
    private Modelo modelo;
    
    public Vehiculo(String matricula, String color, float kilometraje, Date fechaAdquisicion, float costeAdquisicion, String averiado, Modelo modelo){
        this.matricula = matricula;
        this.color = color;
        this.kilometraje = kilometraje;
        this.fechaAdquisicion = fechaAdquisicion;
        this.costeAdquisicion = costeAdquisicion;
        this.averiado = averiado;
        this.modelo = modelo;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the kilometraje
     */
    public float getKilometraje() {
        return kilometraje;
    }

    /**
     * @return the fechaAdquisicion
     */
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    /**
     * @return the costeAdquisicion
     */
    public float getCosteAdquisicion() {
        return costeAdquisicion;
    }

    /**
     * @return the averiado
     */
    public String getAveriado() {
        return averiado;
    }

    /**
     * @return the idModelo
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param kilometraje the kilometraje to set
     */
    public void setKilometraje(float kilometraje) {
        this.kilometraje = kilometraje;
    }

    /**
     * @param fechaAdquisicion the fechaAdquisicion to set
     */
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    /**
     * @param costeAdquisicion the costeAdquisicion to set
     */
    public void setCosteAdquisicion(float costeAdquisicion) {
        this.costeAdquisicion = costeAdquisicion;
    }

    /**
     * @param averiado the averiado to set
     */
    public void setAveriado(String averiado) {
        this.averiado = averiado;
    }

    /**
     * @param idModelo the idModelo to set
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
