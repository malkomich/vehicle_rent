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
public class Modelo implements Serializable{
    
    private String idModelo;
    private String nombre;
    private String fabricante;
    private float costeAlquiler;
    private float largo;
    private float ancho;
    private float peso;
    private int puertas;
    private int año;
    private String gps;
    
    public Modelo(String idModelo, String nombre, String fabricante, float costeAlquiler, float largo, float ancho, float peso, int puertas, int año, String gps){
        this.idModelo = idModelo;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.costeAlquiler = costeAlquiler;
        this.largo = largo;
        this.ancho = ancho;
        this.peso = peso;
        this.puertas = puertas;
        this.año = año;
        this.gps = gps;
    }

    /**
     * @return the idModelo
     */
    public String getIdModelo() {
        return idModelo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @return the costeAlquiler
     */
    public float getCosteAlquiler() {
        return costeAlquiler;
    }

    /**
     * @return the largo
     */
    public float getLargo() {
        return largo;
    }

    /**
     * @return the ancho
     */
    public float getAncho() {
        return ancho;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @return the puertas
     */
    public int getPuertas() {
        return puertas;
    }

    /**
     * @return the año
     */
    public int getAño() {
        return año;
    }

    /**
     * @return the gps
     */
    public String getGps() {
        return gps;
    }

    /**
     * @param idModelo the idModelo to set
     */
    public void setIdModelo(String idModelo) {
        this.idModelo = idModelo;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @param costeAlquiler the costeAlquiler to set
     */
    public void setCosteAlquiler(float costeAlquiler) {
        this.costeAlquiler = costeAlquiler;
    }

    /**
     * @param largo the largo to set
     */
    public void setLargo(float largo) {
        this.largo = largo;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * @param puertas the puertas to set
     */
    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    /**
     * @param año the año to set
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * @param gps the gps to set
     */
    public void setGps(String gps) {
        this.gps = gps;
    }
}
