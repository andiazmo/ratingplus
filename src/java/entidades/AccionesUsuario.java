/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : BSNC-18-0119 - Cupos Auditoria Dual 2018
 *Programador: Wittman Gutiérrez
 *Fecha del creacion : 26-07-2018
 --------------------------------------------------------------------------------
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wittman Gutiérrez
 */
@Entity
@Table(name = "acciones_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccionesUsuario.findAll", query = "SELECT a FROM AccionesUsuario a"),
    @NamedQuery(name = "AccionesUsuario.findById", query = "SELECT a FROM AccionesUsuario a WHERE a.id = :id"),
    @NamedQuery(name = "AccionesUsuario.findByNit", query = "SELECT a FROM AccionesUsuario a WHERE a.nit = :nit"),
    @NamedQuery(name = "AccionesUsuario.findByAccion", query = "SELECT a FROM AccionesUsuario a WHERE a.accion = :accion"),
    @NamedQuery(name = "AccionesUsuario.findBySubmodulo", query = "SELECT a FROM AccionesUsuario a WHERE a.submodulo = :submodulo"),
    @NamedQuery(name = "AccionesUsuario.findByFecha", query = "SELECT a FROM AccionesUsuario a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AccionesUsuario.findByNitMaxFecha", query = "SELECT a FROM AccionesUsuario a WHERE a.nit = :nit ORDER BY a.fecha DESC"),})
public class AccionesUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "nit")
    private String nit;
    @Column(name = "accion")
    private String accion;
    @Column(name = "submodulo")
    private String submodulo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "usuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private RpUsuarios usuario;

    public AccionesUsuario(String nit, RpUsuarios usuario, Date fecha) {
        this.id = UUID.randomUUID().toString();
        this.nit = nit;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public AccionesUsuario() {
        this.id = UUID.randomUUID().toString();
    }

    public AccionesUsuario(String id) {
        this.id = id;
    }

    public AccionesUsuario(String id, String nit, Date fecha) {
        this.id = id;
        this.nit = nit;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getSubmodulo() {
        return submodulo;
    }

    public void setSubmodulo(String submodulo) {
        this.submodulo = submodulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public RpUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(RpUsuarios usuario) {
        this.usuario = usuario;
    }

    public void setAccionYSubmodulo(String accion, String submodulo) {
        setAccion(accion);
        setSubmodulo(submodulo);
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
        if (!(object instanceof AccionesUsuario)) {
            return false;
        }
        AccionesUsuario other = (AccionesUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccionesUsuario[ id=" + id + " ]";
    }

}
