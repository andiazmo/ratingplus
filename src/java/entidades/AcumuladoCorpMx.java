/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x236216
 */
@Entity
@Table(name = "acumulado_corp_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcumuladoCorpMx.findAll", query = "SELECT a FROM AcumuladoCorpMx a"),
     @NamedQuery(name = "AcumuladoCorpMx.findByCliente", query = "SELECT a FROM AcumuladoCorpMx a WHERE a.cliente = :cliente"
            + " AND (a.fechaCreacion = :fechaCreacion OR a.fechaCreacion = :fechaCreacionA)")})
public class AcumuladoCorpMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acumulado", columnDefinition = "serial")
    private Integer idAcumulado;   
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "banda_0D1W")
    private Double banda_0D1W;
    @Column(name = "banda_1W1M")
    private Double banda_1W1M;
    @Column(name = "banda_1M3M")
    private Double banda_1M3M;
    @Column(name = "banda_3M6M")
    private Double banda_3M6M;
    @Column(name = "banda_6M1Y")
    private Double banda_6M1Y;
    @Column(name = "banda_1Y2Y")
    private Double banda_1Y2Y;
    @Column(name = "banda_2Y3Y")
    private Double banda_2Y3Y;
    @Column(name = "banda_3Y5Y")
    private Double banda_3Y5Y;
    @Column(name = "banda_5Y7Y")
    private Double banda_5Y7Y;
    @Column(name = "banda_7Y10Y")
    private Double banda_7Y10Y;
    @Column(name = "banda_RF10Y15Y")
    private Double banda_RF10Y15Y;
    @Column(name = "banda_RF15Y20Y")
    private Double banda_RF15Y20Y;
    @Column(name = "banda_RF20Y30Y")
    private Double banda_RF20Y30Y;
    @Column(name = "banda_RF30Y40Y")
    private Double banda_RF30Y40Y;
    @Column(name = "banda_RF40Y999Y")
    private Double banda_RF40Y999Y;
    @Column(name = "ieecop_acumulado")
    private Double ieecop_acumulado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;    

    public Integer getIdAcumulado() {
        return idAcumulado;
    }

    public void setIdAcumulado(Integer idAcumulado) {
        this.idAcumulado = idAcumulado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getBanda_0D1W() {
        return banda_0D1W;
    }

    public void setBanda_0D1W(Double banda_0D1W) {
        this.banda_0D1W = banda_0D1W;
    }

    public Double getBanda_1W1M() {
        return banda_1W1M;
    }

    public void setBanda_1W1M(Double banda_1W1M) {
        this.banda_1W1M = banda_1W1M;
    }

    public Double getBanda_1M3M() {
        return banda_1M3M;
    }

    public void setBanda_1M3M(Double banda_1M3M) {
        this.banda_1M3M = banda_1M3M;
    }

    public Double getBanda_3M6M() {
        return banda_3M6M;
    }

    public void setBanda_3M6M(Double banda_3M6M) {
        this.banda_3M6M = banda_3M6M;
    }

    public Double getBanda_6M1Y() {
        return banda_6M1Y;
    }

    public void setBanda_6M1Y(Double banda_6M1Y) {
        this.banda_6M1Y = banda_6M1Y;
    }

    public Double getBanda_1Y2Y() {
        return banda_1Y2Y;
    }

    public void setBanda_1Y2Y(Double banda_1Y2Y) {
        this.banda_1Y2Y = banda_1Y2Y;
    }

    public Double getBanda_2Y3Y() {
        return banda_2Y3Y;
    }

    public void setBanda_2Y3Y(Double banda_2Y3Y) {
        this.banda_2Y3Y = banda_2Y3Y;
    }

    public Double getBanda_3Y5Y() {
        return banda_3Y5Y;
    }

    public void setBanda_3Y5Y(Double banda_3Y5Y) {
        this.banda_3Y5Y = banda_3Y5Y;
    }

    public Double getBanda_5Y7Y() {
        return banda_5Y7Y;
    }

    public void setBanda_5Y7Y(Double banda_5Y7Y) {
        this.banda_5Y7Y = banda_5Y7Y;
    }

    public Double getBanda_7Y10Y() {
        return banda_7Y10Y;
    }

    public void setBanda_7Y10Y(Double banda_7Y10Y) {
        this.banda_7Y10Y = banda_7Y10Y;
    }

    public Double getBanda_RF10Y15Y() {
        return banda_RF10Y15Y;
    }

    public void setBanda_RF10Y15Y(Double banda_RF10Y15Y) {
        this.banda_RF10Y15Y = banda_RF10Y15Y;
    }

    public Double getBanda_RF15Y20Y() {
        return banda_RF15Y20Y;
    }

    public void setBanda_RF15Y20Y(Double banda_RF15Y20Y) {
        this.banda_RF15Y20Y = banda_RF15Y20Y;
    }

    public Double getBanda_RF20Y30Y() {
        return banda_RF20Y30Y;
    }

    public void setBanda_RF20Y30Y(Double banda_RF20Y30Y) {
        this.banda_RF20Y30Y = banda_RF20Y30Y;
    }

    public Double getBanda_RF30Y40Y() {
        return banda_RF30Y40Y;
    }

    public void setBanda_RF30Y40Y(Double banda_RF30Y40Y) {
        this.banda_RF30Y40Y = banda_RF30Y40Y;
    }

    public Double getBanda_RF40Y999Y() {
        return banda_RF40Y999Y;
    }

    public void setBanda_RF40Y999Y(Double banda_RF40Y999Y) {
        this.banda_RF40Y999Y = banda_RF40Y999Y;
    }

    public Double getIeecop_acumulado() {
        return ieecop_acumulado;
    }

    public void setIeecop_acumulado(Double ieecop_acumulado) {
        this.ieecop_acumulado = ieecop_acumulado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcumulado != null ? idAcumulado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcumuladoCorpMx)) {
            return false;
        }
        AcumuladoCorpMx other = (AcumuladoCorpMx) object;
        if ((this.idAcumulado == null && other.idAcumulado != null) || 
                (this.idAcumulado != null && !this.idAcumulado.equals(other.idAcumulado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.santander.service.entity.AcumuladoCorpMx[ idAcumulado=" + idAcumulado + " ]";
    }

}
