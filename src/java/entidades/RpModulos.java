/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
 * @author siervo
 */
@Entity
@Table(name = "rp_modulos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RpModulos.findAll", query = "SELECT r FROM RpModulos r"),
    @NamedQuery(name = "RpModulos.findByCodigo", query = "SELECT r FROM RpModulos r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RpModulos.findByNombre", query = "SELECT r FROM RpModulos r WHERE r.nombre = :nombre")})
public class RpModulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "modulo")
    private List<RpSubmodulos> rpSubmodulosList;

    public RpModulos() {
        this.codigo = UUID.randomUUID().toString();
    }

    public RpModulos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<RpSubmodulos> getRpSubmodulosList() {
        return rpSubmodulosList;
    }

    public void setRpSubmodulosList(List<RpSubmodulos> rpSubmodulosList) {
        this.rpSubmodulosList = rpSubmodulosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RpModulos)) {
            return false;
        }
        RpModulos other = (RpModulos) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RpModulos[ codigo=" + codigo + " ]";
    }
    
}
