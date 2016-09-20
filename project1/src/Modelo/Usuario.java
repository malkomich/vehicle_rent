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
public class Usuario implements Serializable{
    
    private String nif;
    private String nombre;
    private String password;
    private String direccionPostal;
    private int codigoPostal;
    private String email;    
    
    public Usuario(String nif, String nombre, String passwd, String direccionPostal, int codigoPostal, String email){
        this.nif = nif;
        this.nombre = nombre;
        this.password = passwd;
        this.direccionPostal = direccionPostal;
        this.codigoPostal = codigoPostal;
        this.email = email;
    }
    
    public boolean passwordCorrecto(String passwd){
        return passwd.equals(this.getPassword());
    }

    /**
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * @param nif the nif to set
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the direccionPostal
     */
    public String getDireccionPostal() {
        return direccionPostal;
    }

    /**
     * @param direccionPostal the direccionPostal to set
     */
    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    /**
     * @return the codigoPostal
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
