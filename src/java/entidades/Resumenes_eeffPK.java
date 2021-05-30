/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jeferson Camargo
 */
@Embeddable
public class Resumenes_eeffPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aniobalance")
    @Temporal(TemporalType.DATE)
    private Date aniobalance;

    public Resumenes_eeffPK() {
    }

    public Resumenes_eeffPK(String nit, Date aniobalance) {
        this.nit = nit;
        this.aniobalance = aniobalance;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Date getAniobalance() {
        return aniobalance;
    }

    public void setAniobalance(Date aniobalance) {
        this.aniobalance = aniobalance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nit != null ? nit.hashCode() : 0);
        hash += (aniobalance != null ? aniobalance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resumenes_eeffPK)) {
            return false;
        }
        Resumenes_eeffPK other = (Resumenes_eeffPK) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        if ((this.aniobalance == null && other.aniobalance != null) || (this.aniobalance != null && !this.aniobalance.equals(other.aniobalance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Resumenes_eeffPK[ nit=" + nit + ", aniobalance=" + aniobalance + " ]";
    }
    
}
