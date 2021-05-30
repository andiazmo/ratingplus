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
 * @author GCOCOL0281
 */
@Entity
@Table(name = "archivos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivos.findAll", query = "SELECT a FROM Archivos a"),
    @NamedQuery(name = "Archivos.findById", query = "SELECT a FROM Archivos a WHERE a.id = :id"),
    @NamedQuery(name = "Archivos.findByNombre", query = "SELECT a FROM Archivos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Archivos.findByProcesado", query = "SELECT a FROM Archivos a WHERE a.procesado = :procesado"),
    @NamedQuery(name = "Archivos.findByRprocesados", query = "SELECT a FROM Archivos a WHERE a.rprocesados = :rprocesados"),
    @NamedQuery(name = "Archivos.findByRnprocesados", query = "SELECT a FROM Archivos a WHERE a.rnprocesados = :rnprocesados")})
public class Archivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procesado")
    private boolean procesado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rprocesados")
    private int rprocesados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rnprocesados")
    private int rnprocesados;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "archivo")
    private List<Logarchivos> logarchivosList;

    public Archivos() {
        this.id = UUID.randomUUID().toString();
    }

    public Archivos(String id) {
        this.id = id;
    }

    public Archivos(String id, String nombre, boolean procesado, int rprocesados, int rnprocesados) {
        this.id = id;
        this.nombre = nombre;
        this.procesado = procesado;
        this.rprocesados = rprocesados;
        this.rnprocesados = rnprocesados;
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

    public boolean getProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    public int getRprocesados() {
        return rprocesados;
    }

    public void setRprocesados(int rprocesados) {
        this.rprocesados = rprocesados;
    }

    public int getRnprocesados() {
        return rnprocesados;
    }

    public void setRnprocesados(int rnprocesados) {
        this.rnprocesados = rnprocesados;
    }

    @XmlTransient
    public List<Logarchivos> getLogarchivosList() {
        return logarchivosList;
    }

    public void setLogarchivosList(List<Logarchivos> logarchivosList) {
        this.logarchivosList = logarchivosList;
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
        if (!(object instanceof Archivos)) {
            return false;
        }
        Archivos other = (Archivos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Archivos[ id=" + id + " ]";
    }
    
}
