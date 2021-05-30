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
@Table(name = "valores_actcupo_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "ValoresActCupoMx.findAll", query = "SELECT n FROM ValoresActCupoMx n"),
   @NamedQuery(name = "ValoresActCupoMx.findByCliente", query = "SELECT DISTINCT n.cliente FROM ValoresActCupoMx n WHERE (n.fechaCreacion = :fechaCreacion OR n.fechaCreacion = :fechaCreacionA)"),
   @NamedQuery(name = "ValoresActCupoMx.findByClienteFecha", query = "SELECT n FROM ValoresActCupoMx n WHERE n.cliente = :cliente and (n.fechaCreacion = :fechaCreacion or n.fechaCreacion = :fechaCreacionA) ORDER BY n.idvalor DESC") })
public class ValoresActCupoMx implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_valor", columnDefinition = "serial")
    private Integer idvalor;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "acumulado_corp")
    private Double acumulado_corp;
    @Column(name = "neteo_corp")
    private Double neteo_corp;
     @Column(name = "acumulado_sfc")
    private Double acumulado_sfc;
    @Column(name = "neteo_sfc")
    private Double neteo_sfc;
        @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    public Integer getIdvalor() {
        return idvalor;
    }

    public void setIdvalor(Integer idvalor) {
        this.idvalor = idvalor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getAcumulado_corp() {
        return acumulado_corp;
    }

    public void setAcumulado_corp(Double acumulado_corp) {
        this.acumulado_corp = acumulado_corp;
    }

    public Double getNeteo_corp() {
        return neteo_corp;
    }

    public void setNeteo_corp(Double neteo_corp) {
        this.neteo_corp = neteo_corp;
    }

    public Double getAcumulado_sfc() {
        return acumulado_sfc;
    }

    public void setAcumulado_sfc(Double acumulado_sfc) {
        this.acumulado_sfc = acumulado_sfc;
    }

    public Double getNeteo_sfc() {
        return neteo_sfc;
    }

    public void setNeteo_sfc(Double neteo_sfc) {
        this.neteo_sfc = neteo_sfc;
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
        hash += (idvalor != null ? idvalor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValoresActCupoMx)) {
            return false;
        }
        ValoresActCupoMx other = (ValoresActCupoMx) object;
        if ((this.idvalor == null && other.idvalor != null) || (this.idvalor != null && !this.idvalor.equals(other.idvalor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.santander.service.entity.ValoresActCupoMx[ id_valor=" + idvalor + " ]";
    }
    
}
