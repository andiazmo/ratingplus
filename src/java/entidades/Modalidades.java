/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
 * @author x356167
 */
@Entity
@Table(name = "modalidades")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modalidades.findAll", query = "SELECT m FROM Modalidades m"),
    @NamedQuery(name = "Modalidades.findById", query = "SELECT m FROM Modalidades m WHERE m.id = :id"),
    @NamedQuery(name = "Modalidades.findByNombre", query = "SELECT m FROM Modalidades m WHERE m.nombre = :nombre")})
public class Modalidades implements Serializable {
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
    @Column(name = "soloconsulta")
    private boolean consulta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modalidad")
    private List<Limitesautorizados> limitesautorizadosList;

    public Modalidades() {
        this.id = UUID.randomUUID().toString();
        this.consulta = false;
    }

    public Modalidades(String id) {
        this.id = id;
    }

    public Modalidades(String id, String nombre) {
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

    public boolean isConsulta() {
        return consulta;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    
    
    
    @XmlTransient
    public List<Limitesautorizados> getLimitesautorizadosList() {
        return limitesautorizadosList;
    }

    public void setLimitesautorizadosList(List<Limitesautorizados> limitesautorizadosList) {
        this.limitesautorizadosList = limitesautorizadosList;
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
        if (!(object instanceof Modalidades)) {
            return false;
        }
        Modalidades other = (Modalidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Modalidades[ id=" + id + " ]";
    }
    
}
