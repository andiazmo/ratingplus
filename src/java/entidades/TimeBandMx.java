/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x236216
 */
@Entity
@Table(name = "time_band_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeBandMx.findAll", query = "SELECT e FROM TimeBandMx e"),
    @NamedQuery(name = "TimeBandMx.findByIdTimeBand", query = "SELECT e FROM TimeBandMx e WHERE e.idTimeBand = :idTimeBand"),
    @NamedQuery(name = "TimeBandMx.findByNodo", query = "SELECT e FROM TimeBandMx e WHERE e.nodo = :nodo"),
    @NamedQuery(name = "TimeBandMx.findByDiasInicio", query = "SELECT e FROM TimeBandMx e WHERE e.diasInicio = :diasInicio"),
    @NamedQuery(name = "TimeBandMx.findByDiasFin", query = "SELECT e FROM TimeBandMx e WHERE e.diasFin = :diasFin")})
public class TimeBandMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_time_band", columnDefinition = "serial")
    private Integer idTimeBand;    
    @Column(name = "nodo")
    private String nodo;
    @Column(name = "dias_inicio")
    private Integer diasInicio;
    @Column(name = "dias_fin")
    private Integer diasFin;

    public TimeBandMx() {
    }

    public TimeBandMx(Integer idTimeBand) {
        this.idTimeBand = idTimeBand;
    }

    public TimeBandMx(Integer idTimeBand, Integer diasInicio, Integer diasFin) {
        this.idTimeBand = idTimeBand;
        this.diasInicio = diasInicio;
        this.diasFin = diasFin;
    }

    public Integer getIdTimeBand() {
        return idTimeBand;
    }

    public void setIdTimeBand(Integer idTimeBand) {
        this.idTimeBand = idTimeBand;
    }

    public String getNodo() {
        return nodo;
    }

    public void setnodo(String nodo) {
        this.nodo = nodo;
    }

    public Integer getDiasInicio() {
        return diasInicio;
    }

    public void setDiasInicio(Integer diasInicio) {
        this.diasInicio = diasInicio;
    }

    public Integer getDiasFin() {
        return diasFin;
    }

    public void setDiasFin(Integer diasFin) {
        this.diasFin = diasFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTimeBand != null ? idTimeBand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeBandMx)) {
            return false;
        }
        TimeBandMx other = (TimeBandMx) object;
        if ((this.idTimeBand == null && other.idTimeBand != null) || (this.idTimeBand != null
                && !this.idTimeBand.equals(other.idTimeBand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TimeBandMx[ idTimeBand=" + idTimeBand + " ]";
    }

}
