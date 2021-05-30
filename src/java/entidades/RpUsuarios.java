/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : BSNC-18-0119 - Cupos Auditoria Dual 2018
 *Programador: Wittman Gutiérrez
 *Tag de cambio: CupoAutDual2018
 *Fecha del cambio : 26-07-2018
 --------------------------------------------------------------------------------
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rp_usuarios")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RpUsuarios.findAll", query = "SELECT r FROM RpUsuarios r"),
    @NamedQuery(name = "RpUsuarios.findByCodigo", query = "SELECT r FROM RpUsuarios r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RpUsuarios.findByNombre", query = "SELECT r FROM RpUsuarios r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RpUsuarios.findByClave", query = "SELECT r FROM RpUsuarios r WHERE r.clave = :clave"),
    @NamedQuery(name = "RpUsuarios.findByNombreClave", query = "SELECT r FROM RpUsuarios r WHERE r.clave = :clave and r.nombre = :nombre")})
public class RpUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 100)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 200)
    @Column(name = "telefonos")
    private String telefonos;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "clave")
    private String clave;
    @JoinColumn(name = "rol", referencedColumnName = "codigo")
    @ManyToOne
    private RpRoles rol;
    @Column(name = "bloqueado")
    private Boolean bloqueado;
    @Column(name = "activado")
    private Boolean activado;
    // CupoAutDual2018 - inicio
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<AccionesUsuario> accionesUsuarioList;
    // CupoAutDual2018 - fin
    
   
    
    public RpUsuarios() {
        this.codigo = UUID.randomUUID().toString();
        this.clave="123genericasantander";
        this.bloqueado = false;
        this.activado = true;
    }

    public RpUsuarios(String codigo) {
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public RpRoles getRol() {
        return rol;
    }

    public void setRol(RpRoles rol) {
        this.rol = rol;
    }

      public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
        if (!(object instanceof RpUsuarios)) {
            return false;
        }
        RpUsuarios other = (RpUsuarios) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RpUsuarios[ codigo=" + codigo + " ]";
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Boolean getActivado() {
        return activado;
    }

    public void setActivado(Boolean activado) {
        this.activado = activado;
    }
    
    // CupoAutDual2018 - inicio
    @XmlTransient
    public List<AccionesUsuario> getAccionesUsuarioList() {
        return accionesUsuarioList;
    }

    public void setAccionesUsuarioList(List<AccionesUsuario> accionesUsuarioList) {
        this.accionesUsuarioList = accionesUsuarioList;
    }
    // CupoAutDual2018 - fin
    
}
