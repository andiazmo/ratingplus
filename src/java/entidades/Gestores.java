/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Libardo Rondon
 */
@Entity
@Table(name = "gestores")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gestores.findAll", query = "SELECT g FROM Gestores g"),
    @NamedQuery(name = "Gestores.findByCedula", query = "SELECT g FROM Gestores g WHERE g.cedula = :cedula"),
    @NamedQuery(name = "Gestores.findByNombre", query = "SELECT g FROM Gestores g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "Gestores.findByTelefonoFijo", query = "SELECT g FROM Gestores g WHERE g.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "Gestores.findByTelefonoCelular", query = "SELECT g FROM Gestores g WHERE g.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "Gestores.findByDireccion", query = "SELECT g FROM Gestores g WHERE g.direccion = :direccion"),
    @NamedQuery(name = "Gestores.findByEmail", query = "SELECT g FROM Gestores g WHERE g.email = :email")})
public class Gestores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 15)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 15)
    @Column(name = "telefono_celular")
    private String telefonoCelular;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    public Gestores() {
    }

    public Gestores(String cedula) {
        this.cedula = cedula;
    }

    public Gestores(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gestores)) {
            return false;
        }
        Gestores other = (Gestores) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Gestores[ cedula=" + cedula + " ]";
    }
    
}
