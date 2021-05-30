/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *Proyecto : Cupos Auditoria 2018
 *Programados: Juan Herrera
 *Tag de cambio: CupoAut2018
 *Fecha del cambio : 22-05-2018
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author x356167
 */
@Entity
@Table(name = "cupos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cupos.findAll", query = "SELECT c FROM Cupos c"),
    @NamedQuery(name = "Cupos.findById", query = "SELECT c FROM Cupos c WHERE c.id = :id"),
    @NamedQuery(name = "Cupos.findByFechaalta", query = "SELECT c FROM Cupos c WHERE c.fechaalta = :fechaalta"),
    @NamedQuery(name = "Cupos.findByFechavencimiento", query = "SELECT c FROM Cupos c WHERE c.fechavencimiento = :fechavencimiento"),
    @NamedQuery(name = "Cupos.findByLimitetotal", query = "SELECT c FROM Cupos c WHERE c.limitetotal = :limitetotal"),
    @NamedQuery(name = "Cupos.findByLimiteconsumido", query = "SELECT c FROM Cupos c WHERE c.limiteconsumido = :limiteconsumido"),
    @NamedQuery(name = "Cupos.findByCliente", query = "SELECT c FROM Cupos c WHERE c.cliente = :cliente"),
    @NamedQuery(name = "Cupos.updateByCliente", query = "UPDATE Cupos c SET c.estado=:estado WHERE c.cliente =:cliente")
})
public class Cupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaalta")
    @Temporal(TemporalType.DATE)
    private Date fechaalta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "limitetotal")
    private double limitetotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "limiteconsumido")
    private double limiteconsumido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cupo")
    private List<Limitesautorizados> limitesautorizadosList;
    @JoinColumn(name = "moneda", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Monedas moneda;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clientes cliente;
     
    // CupoAut2018 -Inicio
    // Comentario : Por defecto se deja estado false   
    @Column(name = "estado")
    private boolean estado = false;
    // CupoAut2018 -Fin

   


    

    public Cupos() {
        this.id = UUID.randomUUID().toString();
    }

    public Cupos(String id) {
        this.id = id;
    }

    public Cupos(String id, Date fechaalta, Date fechavencimiento, double limitetotal, double limiteconsumido) {
        this.id = id;
        this.fechaalta = fechaalta;
        this.fechavencimiento = fechavencimiento;
        this.limitetotal = limitetotal;
        this.limiteconsumido = limiteconsumido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public double getLimitetotal() {
        return limitetotal;
    }

    public void setLimitetotal(double limitetotal) {
        this.limitetotal = limitetotal;
    }

    public double getLimiteconsumido() {
        return limiteconsumido;
    }

    public void setLimiteconsumido(double limiteconsumido) {
        this.limiteconsumido = limiteconsumido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
    
    @XmlTransient
    public List<Limitesautorizados> getLimitesautorizadosList() {
        return limitesautorizadosList;
    }

    public void setLimitesautorizadosList(List<Limitesautorizados> limitesautorizadosList) {
        this.limitesautorizadosList = limitesautorizadosList;
    }

    public Monedas getMoneda() {
        return moneda;
    }

    public void setMoneda(Monedas moneda) {
        this.moneda = moneda;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof Cupos)) {
            return false;
        }
        Cupos other = (Cupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cupos[ id=" + id + " ]";
    }
    // CupoAut2018 -Inicio
    public String getEstadoActualCupo() {
        String estadoFlag;
        if (estado) {
            estadoFlag = "Autorizado";
        } else {
            estadoFlag = "Pendiente";
        }
        return estadoFlag;
    }
    // CupoAut2018 -Fin
}
