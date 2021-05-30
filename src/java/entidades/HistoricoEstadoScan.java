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
 * @author 0000
 */
@Entity
@Table(name = "historico_estadoscan")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoEstadoScan.findAll", query = "SELECT h FROM HistoricoEstadoScan h"),
    @NamedQuery(name = "HistoricoEstadoScan.findById", query = "SELECT h FROM HistoricoEstadoScan h WHERE h.id = :id"),
    @NamedQuery(name = "HistoricoEstadoScan.findByNit", query = "SELECT h FROM HistoricoEstadoScan h WHERE h.nitcliente = :nitcliente"),
    @NamedQuery(name = "HistoricoEstadoScan.findByNombre", query = "SELECT h FROM HistoricoEstadoScan h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "HistoricoEstadoScan.findByNitfecha", query = "SELECT h FROM HistoricoEstadoScan h WHERE h.nitcliente = :nitcliente ORDER BY h.fechaestadoscan DESC"),
    @NamedQuery(name = "HistoricoEstadoScan.findByrantinginicial", query = "SELECT h FROM HistoricoEstadoScan h WHERE h.estadoscan = :estadoscan"),
    @NamedQuery(name = "HistoricoEstadoScan.findByratingmodificado", query = "SELECT h FROM HistoricoEstadoScan h WHERE h.subestadoscan = :subestadoscan"),
    @NamedQuery(name = "HistoricoEstadoScan.findByfecharating", query = "SELECT h FROM HistoricoEstadoScan h WHERE h.fechaestadoscan = :fechaestadoscan")})

public class HistoricoEstadoScan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;

    @Size(min = 1, max = 15)
    @Column(name = "nitcliente")
    private String nitcliente;

    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estadoscan")
    private String estadoscan;

    @Column(name = "subestadoscan")
    private String subestadoscan;

    @Column(name = "fechaestadoscan")
    @Temporal(TemporalType.DATE)
    private Date fechaestadoscan;

    public HistoricoEstadoScan() {
    }

    public HistoricoEstadoScan(String id) {
        this.id = id;
    }

    public HistoricoEstadoScan(String nit, String nombre , String estadoscan, String subestadoscan, Date fechaestadoscan) {
        this.id = UUID.randomUUID().toString();
        this.nitcliente = nit;
        this.nombre=nombre;
        this.estadoscan = estadoscan;
        this.subestadoscan = subestadoscan;
        this.fechaestadoscan = fechaestadoscan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNitcliente() {
        return nitcliente;
    }

    public void setNitcliente(String nitcliente) {
        this.nitcliente = nitcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoscan() {
       return estadoscan;
    }

    public void setEstadoscan(String estadoscan) {
        this.estadoscan = estadoscan;
    }

    public String getSubestadoscan() {
        return subestadoscan;
    }

    public void setSubestadoscan(String subestadoscan) {
        this.subestadoscan = subestadoscan;
    }

    public Date getFechaestadoscan() {
        return fechaestadoscan;
    }

    public void setFechaestadoscan(Date fechaestadoscan) {
        this.fechaestadoscan = fechaestadoscan;
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
        if (!(object instanceof HistoricoEstadoScan)) {
            return false;
        }
        HistoricoEstadoScan other = (HistoricoEstadoScan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HistoricoEstadoScan[ id=" + id + " ]";
    }

}
