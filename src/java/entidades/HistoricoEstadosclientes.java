/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author x356167
 */
@Entity
@Table(name = "historico_estadosclientes")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoEstadosclientes.findAll", query = "SELECT h FROM HistoricoEstadosclientes h"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByCodigo", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.codigo = :codigo"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByNit", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.nit = :nit"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByNitTipo", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.nit = :nit and h.tipo = :tipo"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByNombre", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByEstado", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.estado = :estado"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByFecha", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByUsuario", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "HistoricoEstadosclientes.findByTipo", query = "SELECT h FROM HistoricoEstadosclientes h WHERE h.tipo = :tipo")})
public class HistoricoEstadosclientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "tipo")
    private Integer tipo;

    public HistoricoEstadosclientes() {
    }

    public HistoricoEstadosclientes(String codigo) {
        this.codigo = codigo;
    }

    public HistoricoEstadosclientes(String nit, String nombre, String estado, Date fecha, String usuario,Integer tipo) {
        this.codigo = UUID.randomUUID().toString();
        this.nit = nit;
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof HistoricoEstadosclientes)) {
            return false;
        }
        HistoricoEstadosclientes other = (HistoricoEstadosclientes) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HistoricoEstadosclientes[ codigo=" + codigo + " ]";
    }
    
}
