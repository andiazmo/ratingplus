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
@Table(name = "estados_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosCliente.findAll", query = "SELECT e FROM EstadosCliente e"),
    @NamedQuery(name = "EstadosCliente.findById", query = "SELECT e FROM EstadosCliente e WHERE e.id = :id"),
    @NamedQuery(name = "EstadosCliente.findByIdCliente", query = "SELECT e FROM EstadosCliente e WHERE e.idCliente =:idCliente ORDER BY e.fecha DESC"),
    @NamedQuery(name = "EstadosCliente.findByDescripcion", query = "SELECT e FROM EstadosCliente e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EstadosCliente.findByFecha", query = "SELECT e FROM EstadosCliente e WHERE e.fecha = :fecha")})
public class EstadosCliente implements Serializable {
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
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados idEstado;
    @JoinColumn(name = "subestado", referencedColumnName = "id")
    @ManyToOne
    private SubEstado subestado;

    public EstadosCliente() {
    }

    public EstadosCliente(String id) {
        this.id = id;
    }

    public EstadosCliente(String id, String descripcion, Date fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public EstadosCliente(String id, String descripcion, Date fecha, Clientes idCliente, Estados idEstado, SubEstado subestado) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.subestado = subestado;
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

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public SubEstado getSubestado() {
        return subestado;
    }

    public void setSubestado(SubEstado subestado) {
        this.subestado = subestado;
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
        if (!(object instanceof EstadosCliente)) {
            return false;
        }
        EstadosCliente other = (EstadosCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idEstado.getNombre();
    }
    
}
