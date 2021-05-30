/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeferson Camargo
 */
@Entity
@Table(name = "ri.auditoria_ri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditorias_Ri.findAll", query = "SELECT a FROM Auditorias_Ri a")
    , @NamedQuery(name = "Auditorias_Ri.findByIdAuditoriaRi", query = "SELECT a FROM Auditorias_Ri a WHERE a.idAuditoriaRi = :idAuditoriaRi")
    , @NamedQuery(name = "Auditorias_Ri.findByAniobalance", query = "SELECT a FROM Auditorias_Ri a WHERE a.aniobalance = :aniobalance")
    , @NamedQuery(name = "Auditorias_Ri.findByNit", query = "SELECT a FROM Auditorias_Ri a WHERE a.nit = :nit")
    , @NamedQuery(name = "Auditorias_Ri.findByFechaacta", query = "SELECT a FROM Auditorias_Ri a WHERE a.fechaacta = :fechaacta")
    , @NamedQuery(name = "Auditorias_Ri.findByUsuario", query = "SELECT a FROM Auditorias_Ri a WHERE a.usuario = :usuario")
    , @NamedQuery(name = "Auditorias_Ri.findByFechaInsercion", query = "SELECT a FROM Auditorias_Ri a WHERE a.fechaInsercion = :fechaInsercion")
    , @NamedQuery(name = "Auditorias_Ri.findByOperacion", query = "SELECT a FROM Auditorias_Ri a WHERE a.operacion = :operacion")
    , @NamedQuery(name = "Auditorias_Ri.findByTablaDestino", query = "SELECT a FROM Auditorias_Ri a WHERE a.tablaDestino = :tablaDestino")
    , @NamedQuery(name = "Auditorias_Ri.findByDetalle", query = "SELECT a FROM Auditorias_Ri a WHERE a.detalle = :detalle")
    , @NamedQuery(name = "Auditorias_Ri.findByOperacionAndTablaDestino", query = "SELECT a FROM Auditorias_Ri a WHERE a.operacion = :operacion and a.tablaDestino = :tablaDestino order by a.idAuditoriaRi desc")
    , @NamedQuery(name = "Auditorias_Ri.findByIdAuditorias_Ri", query = "SELECT a FROM Auditorias_Ri a WHERE a.idAuditoriaRi = :idAuditorias_Ri")
    , @NamedQuery(name = "Auditorias_Ri.findByDate", query = "SELECT a FROM Auditorias_Ri a WHERE func('TO_CHAR', a.fechaInsercion, 'dd/mm/yyyy') BETWEEN  :fechaInicio and :fechaFin ")
})
public class Auditorias_Ri implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_auditoria_ri")
    private Integer idAuditoriaRi;
    @Column(name = "aniobalance")
    @Temporal(TemporalType.DATE)
    private Date aniobalance;
    @Size(max = 20)
    @Column(name = "nit")
    private String nit;
    @Column(name = "fechaacta")
    @Temporal(TemporalType.DATE)
    private Date fechaacta;
    @Size(max = 30)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "fecha_insercion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsercion;
    @Size(max = 30)
    @Column(name = "operacion")
    private String operacion;
    @Size(max = 30)
    @Column(name = "tabla_destino")
    private String tablaDestino;
    @Size(max = 2147483647)
    @Column(name = "detalle")
    private String detalle;

    public Auditorias_Ri() {
    }

    public Auditorias_Ri(Integer idAuditoriaRi) {
        this.idAuditoriaRi = idAuditoriaRi;
    }

    public Integer getIdAuditoriaRi() {
        return idAuditoriaRi;
    }

    public void setIdAuditoriaRi(Integer idAuditoriaRi) {
        this.idAuditoriaRi = idAuditoriaRi;
    }

    public Date getAniobalance() {
        return aniobalance;
    }

    public void setAniobalance(Date aniobalance) {
        this.aniobalance = aniobalance;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Date getFechaacta() {
        return fechaacta;
    }

    public void setFechaacta(Date fechaacta) {
        this.fechaacta = fechaacta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTablaDestino() {
        return tablaDestino;
    }

    public void setTablaDestino(String tablaDestino) {
        this.tablaDestino = tablaDestino;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuditoriaRi != null ? idAuditoriaRi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auditorias_Ri)) {
            return false;
        }
        Auditorias_Ri other = (Auditorias_Ri) object;
        if ((this.idAuditoriaRi == null && other.idAuditoriaRi != null) || (this.idAuditoriaRi != null && !this.idAuditoriaRi.equals(other.idAuditoriaRi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Auditorias_Ri[ idAuditoriaRi=" + idAuditoriaRi + " ]";
    }

}
