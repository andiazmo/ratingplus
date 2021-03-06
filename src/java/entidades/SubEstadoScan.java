/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 0000
 */
@Entity
@Table(name = "subestadoscan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubEstadoScan.findAll", query = "SELECT s FROM SubEstadoScan s"),
    @NamedQuery(name = "SubEstadoScan.findById", query = "SELECT s FROM SubEstadoScan s WHERE s.id = :id"),
    @NamedQuery(name = "SubEstadoScan.findByEstado", query = "SELECT s FROM SubEstadoScan s WHERE s.estado = :estado"),
    @NamedQuery(name = "SubEstadoScan.findByNombre", query = "SELECT s FROM SubEstadoScan s WHERE s.nombre = :nombre")})
public class SubEstadoScan implements Serializable {
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
    @JoinColumn(name = "estado", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoScan estado;
    
    public SubEstadoScan() {
    }

    public SubEstadoScan(String id) {
        this.id = id;
    }

    public SubEstadoScan(String id, String nombre) {
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

    public EstadoScan getEstado() {
        return estado;
    }

    public void setEstadoScan(EstadoScan estado) {
        this.estado = estado;
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
        if (!(object instanceof SubEstadoScan)) {
            return false;
        }
        SubEstadoScan other = (SubEstadoScan) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
