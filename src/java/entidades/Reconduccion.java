/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "reconduccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reconduccion.findAll", query = "SELECT r FROM Reconduccion r"),
    @NamedQuery(name = "Reconduccion.findById", query = "SELECT r FROM Reconduccion r WHERE r.id = :id"),
    @NamedQuery(name = "Reconduccion.findByNombre", query = "SELECT r FROM Reconduccion r WHERE r.nombre = :nombre")})
public class Reconduccion implements Serializable {
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
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReconduccion")
    private Collection<EstadosReconduccion> estadosReconduccionCollection;
    @OneToMany(mappedBy = "reconduccion")
    private Collection<Clientes> clientesCollection;

    public Reconduccion() {
        this.id = UUID.randomUUID().toString();
    }

    public Reconduccion(String id) {
        this.id = id;
    }

    public Reconduccion(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<EstadosReconduccion> getEstadosReconduccionCollection() {
        return estadosReconduccionCollection;
    }

    public void setEstadosReconduccionCollection(Collection<EstadosReconduccion> estadosReconduccionCollection) {
        this.estadosReconduccionCollection = estadosReconduccionCollection;
    }

    @XmlTransient
    public Collection<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
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
        if (!(object instanceof Reconduccion)) {
            return false;
        }
        Reconduccion other = (Reconduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
