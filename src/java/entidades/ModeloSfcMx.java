/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x236216
 */
@Entity
@Table(name = "modelo_sfc_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModeloSfcMx.findAll", query = "SELECT m FROM ModeloSfcMx m"),
    @NamedQuery(name = "ModeloSfcMx.findByCliente", query = "SELECT m FROM ModeloSfcMx m WHERE m.cliente = :cliente"
            + " AND (m.fechaCreacion = :fechaCreacion OR m.fechaCreacion = :fechaCreacionA)")})
public class ModeloSfcMx implements Serializable {

    private static final long serialVersionUID = 1L;
  @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sfc", columnDefinition = "serial")
    private Integer idSfc;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "total_acumulado_sfc")
    private Double totalAcumuladoSfc;
    @Column(name = "total_neteo_sfc")
    private Double totalNeteoSfc;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    public Integer getIdSfc() {
        return idSfc;
    }

    public void setIdSfc(Integer idSfc) {
        this.idSfc = idSfc;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getTotalAcumuladoSfc() {
        return totalAcumuladoSfc;
    }

    public void setTotalAcumuladoSfc(Double totalAcumuladoSfc) {
        this.totalAcumuladoSfc = totalAcumuladoSfc;
    }

    public Double getTotalNeteoSfc() {
        return totalNeteoSfc;
    }

    public void setTotalNeteoSfc(Double totalNeteoSfc) {
        this.totalNeteoSfc = totalNeteoSfc;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSfc != null ? idSfc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModeloSfcMx)) {
            return false;
        }
        ModeloSfcMx other = (ModeloSfcMx) object;
        if ((this.idSfc == null && other.idSfc != null) || (this.idSfc != null && !this.idSfc.equals(other.idSfc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.santander.service.entity.ModeloSfcMx[ idSfc=" + idSfc + " ]";
    }
    
}
