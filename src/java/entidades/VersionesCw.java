/*
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de creación: FIXPACK2
 *Fecha de creación : 10-08-2018
 --------------------------------------------------------------------------------
 */
package entidades;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wittman Gutiérrez
 */
@Entity
@Table(name = "versiones_cw")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VersionesCw.findAll", query = "SELECT v FROM VersionesCw v"),
    @NamedQuery(name = "VersionesCw.findById", query = "SELECT v FROM VersionesCw v WHERE v.id = :id"),
    @NamedQuery(name = "VersionesCw.findByNumVersion", query = "SELECT v FROM VersionesCw v WHERE v.numVersion = :numVersion"),
    @NamedQuery(name = "VersionesCw.findByNumSubversion", query = "SELECT v FROM VersionesCw v WHERE v.numSubversion = :numSubversion"),
    @NamedQuery(name = "VersionesCw.findByNumVersionSubversion", query = "SELECT v FROM VersionesCw v WHERE v.numVersion = :numVersion AND v.numSubversion = :numSubversion AND v.activa=true ORDER BY v.fecha DESC"),
    @NamedQuery(name = "VersionesCw.findByDescripcion", query = "SELECT v FROM VersionesCw v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "VersionesCw.findByFecha", query = "SELECT v FROM VersionesCw v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "VersionesCw.findByActiva", query = "SELECT v FROM VersionesCw v WHERE v.activa = :activa")})
public class VersionesCw implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "num_version")
    private Short numVersion;
    @Column(name = "num_subversion")
    private Short numSubversion;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "activa")
    private Boolean activa;

    public VersionesCw() {
    }

    public VersionesCw(String id) {
        this.id = id;
    }

    public VersionesCw(String id, String descripcion, Date fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(Short numVersion) {
        this.numVersion = numVersion;
    }

    public Short getNumSubversion() {
        return numSubversion;
    }

    public void setNumSubversion(Short numSubversion) {
        this.numSubversion = numSubversion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VersionesCw)) {
            return false;
        }
        VersionesCw other = (VersionesCw) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.VersionesCw[ id=" + id + " ]";
    }
    
}
