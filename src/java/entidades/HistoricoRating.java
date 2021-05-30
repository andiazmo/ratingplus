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
@Table(name = "historico_rating")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoRating.findAll", query = "SELECT r FROM HistoricoRating r"),
    @NamedQuery(name = "HistoricoRating.findById", query = "SELECT r FROM HistoricoRating r WHERE r.id = :id"),
    @NamedQuery(name = "HistoricoRating.findByNit", query = "SELECT r.nitcliente, r.nombre, r.ratinginicial ,r.ratingmodificado, r.ratingactual, r.fecharating FROM HistoricoRating r WHERE r.nitcliente = :nitcliente ORDER BY r.fecharating DESC"),
    @NamedQuery(name = "HistoricoRating.findByNitfecha", query = "SELECT r FROM HistoricoRating r WHERE r.nitcliente = :nitcliente ORDER BY r.fecharating DESC"),
    @NamedQuery(name = "HistoricoRating.findByNombre", query = "SELECT r FROM HistoricoRating r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "HistoricoRating.findByrantinginicial", query = "SELECT r FROM HistoricoRating r WHERE r.ratinginicial = :ratinginicial"),
    @NamedQuery(name = "HistoricoRating.findByratingmodificado", query = "SELECT r FROM HistoricoRating r WHERE r.ratingmodificado = :ratingmodificado"),
    @NamedQuery(name = "HistoricoRating.findByratingactual", query = "SELECT r FROM HistoricoRating r WHERE r.ratingactual = :ratingactual"),
    @NamedQuery(name = "HistoricoRating.findByfecharating", query = "SELECT r FROM HistoricoRating r WHERE r.fecharating = :fecharating")})

public class HistoricoRating implements Serializable {
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
     
    @Column(name = "ratinginicial")
    private Double ratinginicial;
    
    @Column(name = "ratingmodificado")
    private Double ratingmodificado;
    
    
    @Column(name = "ratingactual")
    private Double ratingactual;
    
    @Column(name = "fecharating")
    @Temporal(TemporalType.DATE)
    private Date fecharating;

    
     public HistoricoRating() {
    }

    public HistoricoRating(String id) {
        this.id = id;
    }
    
    public HistoricoRating(String nit, String nombre, Double ratinginicial, Double ratingmodificado,Double ratingactual, Date fecharating) {
        this.id= UUID.randomUUID().toString();
        this.nitcliente= nit;
        this.nombre=nombre;
        this.ratinginicial=ratinginicial;
        this.ratingmodificado=ratingmodificado;
        this.ratingactual=ratingactual;
        this.fecharating=fecharating;
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

    public Double getRatinginicial() {
        return ratinginicial;
    }

    public void setRatinginicial(Double ratinginicial) {
        this.ratinginicial = ratinginicial;
    }

    public Double getRatingmodificado() {
        return ratingmodificado;
    }

    public void setRatingmodificado(Double ratingmodificado) {
        this.ratingmodificado = ratingmodificado;
    }

    public Double getRatingactual() {
        return ratingactual;
    }

    public void setRatingactual(Double ratingactual) {
        this.ratingactual = ratingactual;
    }

    public Date getFecharating() {
        return fecharating;
    }

    public void setFecharating(Date fecharating) {
        this.fecharating = fecharating;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof HistoricoRating)) {
            return false;
        }
        HistoricoRating other = (HistoricoRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.HistoricoRating[ id=" + id + " ]";
    }
    
}
