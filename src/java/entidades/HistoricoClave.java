/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author User
 */
@Entity
@Table(name = "historico_clave")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoClave.findAll", query = "SELECT h FROM HistoricoClave h"),
    @NamedQuery(name = "HistoricoClave.findById", query = "SELECT h FROM HistoricoClave h WHERE h.id = :id"),
    @NamedQuery(name = "HistoricoClave.findByClave", query = "SELECT h FROM HistoricoClave h WHERE h.clave = :clave"),
    @NamedQuery(name = "HistoricoClave.findByFechaCreada", query = "SELECT h FROM HistoricoClave h WHERE h.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "HistoricoClave.findByFechaCaducidad", query = "SELECT h FROM HistoricoClave h WHERE h.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "HistoricoClave.findByActiva", query = "SELECT h FROM HistoricoClave h WHERE h.activa = :activa"),
    @NamedQuery(name = "HistoricoClave.findByActivaFindByUsuario", query = "SELECT h FROM HistoricoClave h WHERE h.activa = :activa AND h.usuario = :usuario2 ORDER BY h.fechaCreacion DESC"),
    @NamedQuery(name = "HistoricoClave.findByRpUsuario", query = "SELECT h FROM HistoricoClave h WHERE h.usuario = :usuario2 ORDER BY h.fechaCreacion DESC")
})

public class HistoricoClave implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Column(name = "activa")
    private Boolean activa;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private RpUsuarios usuario;

    public HistoricoClave() {
    }

    public HistoricoClave(String id) {
        this.id = id;
    }

    public HistoricoClave(String clave, Date fechaCreacion, Date fechaCaducidad, Boolean activa, RpUsuarios usuario) {
        this.id= UUID.randomUUID().toString();
        this.clave = clave;
        this.fechaCreacion = fechaCreacion;
        this.fechaCaducidad = fechaCaducidad;
        this.activa = activa;
        this.usuario = usuario;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public RpUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(RpUsuarios usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof HistoricoClave)) {
            return false;
        }
        HistoricoClave other = (HistoricoClave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HistoricoClave[ id=" + id + " ]";
    }
    
}
