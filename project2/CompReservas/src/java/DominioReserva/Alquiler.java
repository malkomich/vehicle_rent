/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DominioReserva;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JuanCarlos
 */
@Entity
@Table(name = "ALQUILER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquiler.findAll", query = "SELECT a FROM Alquiler a"),
    @NamedQuery(name = "Alquiler.findByIdalquiler", query = "SELECT a FROM Alquiler a WHERE a.idalquiler = :idalquiler"),
    @NamedQuery(name = "Alquiler.findByFechainicio", query = "SELECT a FROM Alquiler a WHERE a.fechainicio = :fechainicio"),
    @NamedQuery(name = "Alquiler.findByFechafin", query = "SELECT a FROM Alquiler a WHERE a.fechafin = :fechafin"),
    @NamedQuery(name = "Alquiler.findByPrecio", query = "SELECT a FROM Alquiler a WHERE a.precio = :precio"),
    @NamedQuery(name = "Alquiler.findByKilometrajesalida", query = "SELECT a FROM Alquiler a WHERE a.kilometrajesalida = :kilometrajesalida"),
    @NamedQuery(name = "Alquiler.findByMatricula", query = "SELECT a FROM Alquiler a WHERE a.matricula = :matricula"),
    @NamedQuery(name = "Alquiler.findByRealizadopor", query = "SELECT a FROM Alquiler a WHERE a.realizadopor = :realizadopor"),
    @NamedQuery(name = "Alquiler.findByCliente", query = "SELECT a FROM Alquiler a WHERE a.cliente = :cliente")})
public class Alquiler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDALQUILER")
    private Integer idalquiler;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private float precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KILOMETRAJESALIDA")
    private float kilometrajesalida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MATRICULA")
    private String matricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "REALIZADOPOR")
    private String realizadopor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CLIENTE")
    private String cliente;

    public Alquiler() {
    }

    public Alquiler(Integer idalquiler) {
        this.idalquiler = idalquiler;
    }

    public Alquiler(Integer idalquiler, Date fechainicio, Date fechafin, float precio, float kilometrajesalida, String matricula, String realizadopor, String cliente) {
        this.idalquiler = idalquiler;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.precio = precio;
        this.kilometrajesalida = kilometrajesalida;
        this.matricula = matricula;
        this.realizadopor = realizadopor;
        this.cliente = cliente;
    }

    public Integer getIdalquiler() {
        return idalquiler;
    }

    public void setIdalquiler(Integer idalquiler) {
        this.idalquiler = idalquiler;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getKilometrajesalida() {
        return kilometrajesalida;
    }

    public void setKilometrajesalida(float kilometrajesalida) {
        this.kilometrajesalida = kilometrajesalida;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getRealizadopor() {
        return realizadopor;
    }

    public void setRealizadopor(String realizadopor) {
        this.realizadopor = realizadopor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalquiler != null ? idalquiler.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alquiler)) {
            return false;
        }
        Alquiler other = (Alquiler) object;
        if ((this.idalquiler == null && other.idalquiler != null) || (this.idalquiler != null && !this.idalquiler.equals(other.idalquiler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DominioReserva.Alquiler[ idalquiler=" + idalquiler + " ]";
    }
    
}
