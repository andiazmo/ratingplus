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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x236216
 */
@Entity
@Table(name = "band_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BandMx.findAll", query = "SELECT b FROM BandMx b"),
    @NamedQuery(name = "BandMx.findByIdBand", query = "SELECT b FROM BandMx b WHERE b.idBand = :idBand"),
    @NamedQuery(name = "BandMx.findByNumeroBanda", query = "SELECT b FROM BandMx b WHERE b.numeroBanda = :numeroBanda")
})
public class BandMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_band", columnDefinition = "serial")
    private Integer idBand;
    @Column(name = "numero_banda")
    private Integer numeroBanda;
    @JoinColumn(name = "id_time_band", referencedColumnName = "id_time_band")
    @ManyToOne
    private TimeBandMx timeBandMx;

    public BandMx() {
    }

    public BandMx(Integer idBand) {
        this.idBand = idBand;
    }

    public BandMx(Integer idBand, Integer numeroBanda) {
        this.idBand = idBand;
        this.numeroBanda = numeroBanda;
    }

    public Integer getIdBand() {
        return idBand;
    }

    public void setIdBand(Integer idBand) {
        this.idBand = idBand;
    }

    public Integer getNumeroBanda() {
        return numeroBanda;
    }

    public void setNumeroBanda(Integer numeroBanda) {
        this.numeroBanda = numeroBanda;
    }

    public TimeBandMx getTimeBandMx() {
        return timeBandMx;
    }

    public void setTimeBandMx(TimeBandMx timeBandMx) {
        this.timeBandMx = timeBandMx;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBand != null ? idBand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BandMx)) {
            return false;
        }
        BandMx other = (BandMx) object;
        if ((this.idBand == null && other.idBand != null) || (this.idBand != null
                && !this.idBand.equals(other.idBand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.BandMx[ idBand=" + idBand + " ]";
    }

}
