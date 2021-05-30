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
@Table(name = "limitesautorizados")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Limitesautorizados.findAll", query = "SELECT l FROM Limitesautorizados l"),
    @NamedQuery(name = "Limitesautorizados.findById", query = "SELECT l FROM Limitesautorizados l WHERE l.id = :id"),
    @NamedQuery(name = "Limitesautorizados.findBySublimiteautorizado", query = "SELECT l FROM Limitesautorizados l WHERE l.sublimiteautorizado = :sublimiteautorizado"),
    @NamedQuery(name = "Limitesautorizados.findByComentarios", query = "SELECT l FROM Limitesautorizados l WHERE l.comentarios = :comentarios"),
    @NamedQuery(name = "Limitesautorizados.findByConsumido", query = "SELECT l FROM Limitesautorizados l WHERE l.consumido = :consumido"),
    @NamedQuery(name = "Limitesautorizados.findByDisponible", query = "SELECT l FROM Limitesautorizados l WHERE l.disponible = :disponible"),
    @NamedQuery(name = "Limitesautorizados.findByCupo", query = "SELECT l FROM Limitesautorizados l WHERE l.cupo = :cupo"),
    @NamedQuery(name = "Limitesautorizados.findByModalidad&Cupo", query = "SELECT l FROM Limitesautorizados l WHERE l.modalidad = :modalidad and l.cupo = :cupo and l.disponible > 0 order by l.modalidad.nombre asc"),
    @NamedQuery(name = "Limitesautorizados.findByModalidad", query = "SELECT l FROM Limitesautorizados l WHERE l.modalidad = :modalidad and l.sublimiteautorizado > 0 ")
})

public class Limitesautorizados implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "sublimiteautorizado")
    private double sublimiteautorizado;
    @Column(name = "comentarios")
    private String comentarios;
    @Basic(optional = false)
    @Column(name = "consumido")
    private double consumido;
    @Basic(optional = false)
    @Column(name = "disponible")
    private double disponible;
    @Column(name = "plazo_swaps")
    private String plazo_swaps;
    @Column(name = "plazo_forwards")
    private String plazo_forwards;
    @Column(name = "tiposfc")
    private String tiposfc;
    //BSNC-19-0119 Operaciones Especiales - ACT - INI
    @Basic(optional = true)
    @Column(name = "fecha_vencimiento_op_esp")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    //BSNC-19-0119 Operaciones Especiales - ACT - INI
    @JoinColumn(name = "modalidad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modalidades modalidad;
    @JoinColumn(name = "cupo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cupos cupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "limite")
    private List<Desembolsos> desembolsosList;

    public Limitesautorizados() {
        this.id = UUID.randomUUID().toString();
    }

    public Limitesautorizados(String id) {
        this.id = id;
    }

    public Limitesautorizados(String id, double sublimiteautorizado, double consumido, double disponible) {
        this.id = id;
        this.sublimiteautorizado = sublimiteautorizado;
        this.consumido = consumido;
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSublimiteautorizado() {
        return sublimiteautorizado;
    }

    public void setSublimiteautorizado(double sublimiteautorizado) {
        this.sublimiteautorizado = sublimiteautorizado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getConsumido() {
        return consumido;
    }

    public void setConsumido(double consumido) {
        this.consumido = consumido;
    }

    public double getDisponible() {
        return disponible;
    }

    public void setDisponible(double disponible) {
        this.disponible = disponible;
    }

    public Modalidades getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidades modalidad) {
        this.modalidad = modalidad;
    }

    public Cupos getCupo() {
        return cupo;
    }

    public void setCupo(Cupos cupo) {
        this.cupo = cupo;
    }

    @XmlTransient
    public List<Desembolsos> getDesembolsosList() {
        return desembolsosList;
    }

    public void setDesembolsosList(List<Desembolsos> desembolsosList) {
        this.desembolsosList = desembolsosList;
    }

    public String getPlazo_swaps() {
        return plazo_swaps;
    }

    public void setPlazo_swaps(String plazo_swaps) {
        this.plazo_swaps = plazo_swaps;
    }

    public String getPlazo_forwards() {
        return plazo_forwards;
    }

    public void setPlazo_forwards(String plazo_forwards) {
        this.plazo_forwards = plazo_forwards;
    }

    public String getTiposfc() {
        return tiposfc;
    }

    public void setTiposfc(String tiposfc) {
        this.tiposfc = tiposfc;
    }

    //BSNC-19-0119 Operaciones Especiales - ACT - INI
    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }
    //BSNC-19-0119 Operaciones Especiales - ACT - FIN

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Limitesautorizados)) {
            return false;
        }
        Limitesautorizados other = (Limitesautorizados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Limitesautorizados id=" + id + " ]";
    }

}
