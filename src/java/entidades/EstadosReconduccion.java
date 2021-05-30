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
@Table(name = "estados_reconduccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosReconduccion.findAll", query = "SELECT e FROM EstadosReconduccion e"),
    @NamedQuery(name = "EstadosReconduccion.findByIdCliente", query = "SELECT e FROM EstadosReconduccion e WHERE e.idCliente =:idCliente ORDER BY e.fecha DESC"),
    @NamedQuery(name = "EstadosReconduccion.findById", query = "SELECT e FROM EstadosReconduccion e WHERE e.id = :id"),
    @NamedQuery(name = "EstadosReconduccion.findByDescripcion", query = "SELECT e FROM EstadosReconduccion e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EstadosReconduccion.findByFecha", query = "SELECT e FROM EstadosReconduccion e WHERE e.fecha = :fecha")})
public class EstadosReconduccion implements Serializable {
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
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clientes idCliente;
    @JoinColumn(name = "id_reconduccion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Reconduccion idReconduccion;

    public EstadosReconduccion() {
    }

    public EstadosReconduccion(String id) {
        this.id = id;
    }

    public EstadosReconduccion(String id, String descripcion, Date fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public EstadosReconduccion(String id, String descripcion, Date fecha, Clientes idCliente, Reconduccion idReconduccion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idReconduccion = idReconduccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Reconduccion getIdReconduccion() {
        return idReconduccion;
    }

    public void setIdReconduccion(Reconduccion idReconduccion) {
        this.idReconduccion = idReconduccion;
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
        if (!(object instanceof EstadosReconduccion)) {
            return false;
        }
        EstadosReconduccion other = (EstadosReconduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadosReconduccion[ id=" + id + " ]";
    }
    
}
