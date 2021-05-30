/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siervo
 */
@Entity
@Table(name = "rp_permisos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RpPermisos.findAll", query = "SELECT r FROM RpPermisos r"),
    @NamedQuery(name = "RpPermisos.findByCodigo", query = "SELECT r FROM RpPermisos r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RpPermisos.findByRolModulo", query = "SELECT r FROM RpPermisos r WHERE r.rol.codigo = :rol and r.modulo.codigo = :modulo"),
    @NamedQuery(name = "RpPermisos.findByGuardar", query = "SELECT r FROM RpPermisos r WHERE r.guardar = :guardar"),
    @NamedQuery(name = "RpPermisos.findByRol", query = "SELECT r FROM RpPermisos r WHERE r.modulo = :modulo and r.rol = :rol"),
    @NamedQuery(name = "RpPermisos.findByModificar", query = "SELECT r FROM RpPermisos r WHERE r.modificar = :modificar"),
    @NamedQuery(name = "RpPermisos.findByBorrar", query = "SELECT r FROM RpPermisos r WHERE r.borrar = :borrar")})
public class RpPermisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "guardar")
    private int guardar;
    @Column(name = "modificar")
    private int modificar;
    @Column(name = "borrar")
    private int borrar;
    @JoinColumn(name = "modulo", referencedColumnName = "codigo")
    @ManyToOne
    private RpSubmodulos modulo;
    @JoinColumn(name = "rol", referencedColumnName = "codigo")
    @ManyToOne
    private RpRoles rol;

    public RpPermisos() {
        this.codigo=UUID.randomUUID().toString();
    }

    public RpPermisos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getGuardar() {
        return guardar;
    }

    public void setGuardar(int guardar) {
        this.guardar = guardar;
    }

    public int getModificar() {
        return modificar;
    }

    public void setModificar(int modificar) {
        this.modificar = modificar;
    }

    public int getBorrar() {
        return borrar;
    }

    public void setBorrar(int borrar) {
        this.borrar = borrar;
    }

    public RpSubmodulos getModulo() {
        return modulo;
    }

    public void setModulo(RpSubmodulos modulo) {
        this.modulo = modulo;
    }

    public RpRoles getRol() {
        return rol;
    }

    public void setRol(RpRoles rol) {
        this.rol = rol;
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
        if (!(object instanceof RpPermisos)) {
            return false;
        }
        RpPermisos other = (RpPermisos) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RpPermisos[ codigo=" + codigo + " ]";
    }
    
}
