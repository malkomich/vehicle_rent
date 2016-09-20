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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JuanCarlos
 */
@Entity
@Table(name = "FACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByNumerofactura", query = "SELECT f FROM Factura f WHERE f.numerofactura = :numerofactura"),
    @NamedQuery(name = "Factura.findByFechafactura", query = "SELECT f FROM Factura f WHERE f.fechafactura = :fechafactura"),
    @NamedQuery(name = "Factura.findByImporte", query = "SELECT f FROM Factura f WHERE f.importe = :importe"),
    @NamedQuery(name = "Factura.findByPendientedepago", query = "SELECT f FROM Factura f WHERE f.pendientedepago = :pendientedepago"),
    @NamedQuery(name = "Factura.findByFechacobro", query = "SELECT f FROM Factura f WHERE f.fechacobro = :fechacobro"),
    @NamedQuery(name = "Factura.findByConcepto", query = "SELECT f FROM Factura f WHERE f.concepto = :concepto"),
    @NamedQuery(name = "Factura.findByIdalquiler", query = "SELECT f FROM Factura f WHERE f.idalquiler = :idalquiler")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMEROFACTURA")
    private Integer numerofactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAFACTURA")
    @Temporal(TemporalType.DATE)
    private Date fechafactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPORTE")
    private float importe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PENDIENTEDEPAGO")
    private char pendientedepago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHACOBRO")
    @Temporal(TemporalType.DATE)
    private Date fechacobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONCEPTO")
    private char concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDALQUILER")
    private int idalquiler;

    public Factura() {
    }

    public Factura(Integer numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Factura(Integer numerofactura, Date fechafactura, float importe, char pendientedepago, Date fechacobro, char concepto, int idalquiler) {
        this.numerofactura = numerofactura;
        this.fechafactura = fechafactura;
        this.importe = importe;
        this.pendientedepago = pendientedepago;
        this.fechacobro = fechacobro;
        this.concepto = concepto;
        this.idalquiler = idalquiler;
    }

    public Integer getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(Integer numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public char getPendientedepago() {
        return pendientedepago;
    }

    public void setPendientedepago(char pendientedepago) {
        this.pendientedepago = pendientedepago;
    }

    public Date getFechacobro() {
        return fechacobro;
    }

    public void setFechacobro(Date fechacobro) {
        this.fechacobro = fechacobro;
    }

    public char getConcepto() {
        return concepto;
    }

    public void setConcepto(char concepto) {
        this.concepto = concepto;
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
        hash += (numerofactura != null ? numerofactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.numerofactura == null && other.numerofactura != null) || (this.numerofactura != null && !this.numerofactura.equals(other.numerofactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DominioEntrega.Factura[ numerofactura=" + numerofactura + " ]";
    }
    
}
