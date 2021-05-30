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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x236216
 */
@Entity
@Table(name = "ecuacion_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EcuacionMx.findAll", query = "SELECT e FROM EcuacionMx e"),
    @NamedQuery(name = "EcuacionMx.findByIdEcuacion", query = "SELECT e FROM EcuacionMx e WHERE e.idEcuacion = :idEcuacion"),
    @NamedQuery(name = "EcuacionMx.findByNombre", query = "SELECT e FROM EcuacionMx e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EcuacionMx.findByConstante1", query = "SELECT e FROM EcuacionMx e WHERE e.constante1 = :constante1"),
    @NamedQuery(name = "EcuacionMx.findByConstante2", query = "SELECT e FROM EcuacionMx e WHERE e.constante2 = :constante2")})
public class EcuacionMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ecuacion", columnDefinition = "serial")
    private Integer idEcuacion;    
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "constante1")
    private Double constante1;
    @Column(name = "constante2")
    private Double constante2;

    public EcuacionMx() {
    }

    public EcuacionMx(Integer idEcuacion) {
        this.idEcuacion = idEcuacion;
    }

    public EcuacionMx(Integer idEcuacion, Double constante1, Double constante2) {
        this.idEcuacion = idEcuacion;
        this.constante1 = constante1;
        this.constante2 = constante2;
    }

    public Integer getIdEcuacion() {
        return idEcuacion;
    }

    public void setIdEcuacion(Integer idEcuacion) {
        this.idEcuacion = idEcuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getConstante1() {
        return constante1;
    }

    public void setConstante1(Double constante1) {
        this.constante1 = constante1;
    }

    public Double getConstante2() {
        return constante2;
    }

    public void setConstante2(Double constante2) {
        this.constante2 = constante2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEcuacion != null ? idEcuacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EcuacionMx)) {
            return false;
        }
        EcuacionMx other = (EcuacionMx) object;
        if ((this.idEcuacion == null && other.idEcuacion != null) || (this.idEcuacion != null
                && !this.idEcuacion.equals(other.idEcuacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EcuacionMx[ idEcuacion=" + idEcuacion + " ]";
    }

}
