/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "corporativo_cal_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorporativoCalMx.findAll", query = "SELECT c FROM CorporativoCalMx c")})
public class CorporativoCalMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calculo", columnDefinition = "serial")
    private Integer idCalculo;
    @JoinColumn(name = "id_operacion", referencedColumnName = "id_operacion")
    @ManyToOne
    private OperacionMx operacion;
    @Column(name = "timeband_pf")
    private String timebandPf;
    @Column(name = "begin_risk")
    private Double beginRisk;
    @Column(name = "end_risk")
    private Double endRisk;
    @Column(name = "iec")
    private Double iec;
    @Column(name = "addon")
    private Double addon;
    @Column(name = "iee")
    private Double iee;
    @Column(name = "iee_cop")
    private Double ieeCop;
    @Column(name = "addon_cop")
    private Double addonCop;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;    

    public Integer getIdCalculo() {
        return idCalculo;
    }

    public void setIdCalculo(Integer idCalculo) {
        this.idCalculo = idCalculo;
    }

    public OperacionMx getOperacion() {
        return operacion;
    }

    public void setOperacion(OperacionMx operacion) {
        this.operacion = operacion;
    }

    public String getTimebandPf() {
        return timebandPf;
    }

    public void setTimebandPf(String timebandPf) {
        this.timebandPf = timebandPf;
    }

    public Double getBeginRisk() {
        return beginRisk;
    }

    public void setBeginRisk(Double beginRisk) {
        this.beginRisk = beginRisk;
    }

    public Double getEndRisk() {
        return endRisk;
    }

    public void setEndRisk(Double endRisk) {
        this.endRisk = endRisk;
    }

    public Double getIec() {
        return iec;
    }

    public void setIec(Double iec) {
        this.iec = iec;
    }

    public Double getAddon() {
        return addon;
    }

    public void setAddon(Double addon) {
        this.addon = addon;
    }

    public Double getIee() {
        return iee;
    }

    public void setIee(Double iee) {
        this.iee = iee;
    }

    public Double getIeeCop() {
        return ieeCop;
    }

    public void setIeeCop(Double ieeCop) {
        this.ieeCop = ieeCop;
    }

    public Double getAddonCop() {
        return addonCop;
    }

    public void setAddonCop(Double addonCop) {
        this.addonCop = addonCop;
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
        hash += (idCalculo != null ? idCalculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorporativoCalMx)) {
            return false;
        }
        CorporativoCalMx other = (CorporativoCalMx) object;
        if ((this.idCalculo == null && other.idCalculo != null) || (this.idCalculo != null && !this.idCalculo.equals(other.idCalculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.santander.service.entity.CorporativoCalMx[ idCalculo=" + idCalculo + " ]";
    }

}
