/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "desembolsos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desembolsos.findAll", query = "SELECT d FROM Desembolsos d")
    ,
    @NamedQuery(name = "Desembolsos.findById", query = "SELECT d FROM Desembolsos d WHERE d.id = :id")
    ,
    @NamedQuery(name = "Desembolsos.findByFecha", query = "SELECT d FROM Desembolsos d WHERE d.fecha = :fecha")
    ,
    @NamedQuery(name = "Desembolsos.findByValor", query = "SELECT d FROM Desembolsos d WHERE d.valor = :valor")
    ,
    @NamedQuery(name = "Desembolsos.findByComentarios", query = "SELECT d FROM Desembolsos d WHERE d.comentarios = :comentarios")
    ,
    @NamedQuery(name = "Desembolsos.findByLimite", query = "SELECT d FROM Desembolsos d WHERE d.limite = :limite")
})
public class Desembolsos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "contrato")
    private String contrato;

    @JoinColumn(name = "limite", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Limitesautorizados limite;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "desembolso")
    private List<Pagos> pagosList;

    public Desembolsos() {
        this.id = UUID.randomUUID().toString();
        this.fecha = new Date();
    }

    public Desembolsos(String id) {
        this.id = id;
    }

    public Desembolsos(String id, Date fecha, double valor) {
        this.id = id;
        this.fecha = fecha;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Limitesautorizados getLimite() {
        return limite;
    }

    public void setLimite(Limitesautorizados limite) {
        this.limite = limite;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
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
        if (!(object instanceof Desembolsos)) {
            return false;
        }
        Desembolsos other = (Desembolsos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Desembolsos[ id=" + id + " ]";
    }

}
