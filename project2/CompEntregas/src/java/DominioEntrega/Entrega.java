/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DominioEntrega;

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
@Table(name = "ENTREGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e"),
    @NamedQuery(name = "Entrega.findByIdentrega", query = "SELECT e FROM Entrega e WHERE e.identrega = :identrega"),
    @NamedQuery(name = "Entrega.findByFechaentrega", query = "SELECT e FROM Entrega e WHERE e.fechaentrega = :fechaentrega"),
    @NamedQuery(name = "Entrega.findByRecargo", query = "SELECT e FROM Entrega e WHERE e.recargo = :recargo"),
    @NamedQuery(name = "Entrega.findByIncidencias", query = "SELECT e FROM Entrega e WHERE e.incidencias = :incidencias"),
    @NamedQuery(name = "Entrega.findByInformeincidencias", query = "SELECT e FROM Entrega e WHERE e.informeincidencias = :informeincidencias"),
    @NamedQuery(name = "Entrega.findByRegistradapor", query = "SELECT e FROM Entrega e WHERE e.registradapor = :registradapor"),
    @NamedQuery(name = "Entrega.findByIdalquiler", query = "SELECT e FROM Entrega e WHERE e.idalquiler = :idalquiler")})
public class Entrega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDENTREGA")
    private Integer identrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAENTREGA")
    @Temporal(TemporalType.DATE)
    private Date fechaentrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RECARGO")
    private Float recargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCIDENCIAS")
    private char incidencias;
    @Size(max = 200)
    @Column(name = "INFORMEINCIDENCIAS")
    private String informeincidencias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "REGISTRADAPOR")
    private String registradapor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDALQUILER")
    private int idalquiler;

    public Entrega() {
    }

    public Entrega(Integer identrega) {
        this.identrega = identrega;
    }

    public Entrega(Integer identrega, Date fechaentrega, char incidencias, String registradapor, int idalquiler) {
        this.identrega = identrega;
        this.fechaentrega = fechaentrega;
        this.incidencias = incidencias;
        this.registradapor = registradapor;
        this.idalquiler = idalquiler;
    }

    public Integer getIdentrega() {
        return identrega;
    }

    public void setIdentrega(Integer identrega) {
        this.identrega = identrega;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public Float getRecargo() {
        return recargo;
    }

    public void setRecargo(Float recargo) {
        this.recargo = recargo;
    }

    public char getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(char incidencias) {
        this.incidencias = incidencias;
    }

    public String getInformeincidencias() {
        return informeincidencias;
    }

    public void setInformeincidencias(String informeincidencias) {
        this.informeincidencias = informeincidencias;
    }

    public String getRegistradapor() {
        return registradapor;
    }

    public void setRegistradapor(String registradapor) {
        this.registradapor = registradapor;
    }

    public int getIdalquiler() {
        return idalquiler;
    }

    public void setIdalquiler(int idalquiler) {
        this.idalquiler = idalquiler;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identrega != null ? identrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;
        if ((this.identrega == null && other.identrega != null) || (this.identrega != null && !this.identrega.equals(other.identrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DominioEntrega.Entrega[ identrega=" + identrega + " ]";
    }
    
}
