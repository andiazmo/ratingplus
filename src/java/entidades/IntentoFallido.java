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
@Table(name = "intento_fallido")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IntentoFallido.findAll", query = "SELECT i FROM IntentoFallido i"),
    @NamedQuery(name = "IntentoFallido.findById", query = "SELECT i FROM IntentoFallido i WHERE i.id = :id"),
    @NamedQuery(name = "IntentoFallido.findByFechaByUsuario", query = "SELECT i FROM IntentoFallido i WHERE i.fecha = :fecha AND i.usuario = :usuario2"),
    @NamedQuery(name = "IntentoFallido.findByFecha", query = "SELECT i FROM IntentoFallido i WHERE i.fecha = :fecha")})
public class IntentoFallido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private RpUsuarios usuario;
    
    

    public IntentoFallido() {
    }

    public IntentoFallido(String id) {
        this.id = id;
    }

    public IntentoFallido(String id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof IntentoFallido)) {
            return false;
        }
        IntentoFallido other = (IntentoFallido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public RpUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(RpUsuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "entidades.IntentoFallido[ id=" + id + " ]";
    }

    public IntentoFallido(Date fecha, RpUsuarios usuario) {
        this.id = UUID.randomUUID().toString();
        this.fecha = fecha;
        this.usuario = usuario;
    }
    
    
    
}
