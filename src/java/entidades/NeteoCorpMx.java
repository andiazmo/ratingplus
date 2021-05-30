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
@Table(name = "neteo_corp_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NeteoCorpMx.findAll", query = "SELECT a FROM NeteoCorpMx a"),
     @NamedQuery(name = "NeteoCorpMx.findByCliente", query = "SELECT a FROM NeteoCorpMx a WHERE a.cliente = :cliente"
            + " AND (a.fechaCreacion = :fechaCreacion OR a.fechaCreacion = :fechaCreacionA)")})
public class NeteoCorpMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_neteo", columnDefinition = "serial")
    private Integer idNeteo;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "mtm_bruto_0D1W")
    private Double mtm_bruto_0D1W;
    @Column(name = "mtm_bruto_1W1M")
    private Double mtm_bruto_1W1M;
    @Column(name = "mtm_bruto_1M3M")
    private Double mtm_bruto_1M3M;
    @Column(name = "mtm_bruto_3M6M")
    private Double mtm_bruto_3M6M;
    @Column(name = "mtm_bruto_6M1Y")
    private Double mtm_bruto_6M1Y;
    @Column(name = "mtm_bruto_1Y2Y")
    private Double mtm_bruto_1Y2Y;
    @Column(name = "mtm_bruto_2Y3Y")
    private Double mtm_bruto_2Y3Y;
    @Column(name = "mtm_bruto_3Y5Y")
    private Double mtm_bruto_3Y5Y;
    @Column(name = "mtm_bruto_5Y7Y")
    private Double mtm_bruto_5Y7Y;
    @Column(name = "mtm_bruto_7Y10Y")
    private Double mtm_bruto_7Y10Y;
    @Column(name = "mtm_bruto_RF10Y15Y")
    private Double mtm_bruto_RF10Y15Y;
    @Column(name = "mtm_bruto_RF15Y20Y")
    private Double mtm_bruto_RF15Y20Y;
    @Column(name = "mtm_bruto_RF20Y30Y")
    private Double mtm_bruto_RF20Y30Y;
    @Column(name = "mtm_bruto_RF30Y40Y")
    private Double mtm_bruto_RF30Y40Y;
    @Column(name = "mtm_bruto_RF40Y999Y")
    private Double mtm_bruto_RF40Y999Y;
    @Column(name = "addon_0D1W")
    private Double addon_0D1W;
    @Column(name = "addon_1W1M")
    private Double addon_1W1M;
    @Column(name = "addon_1M3M")
    private Double addon_1M3M;
    @Column(name = "addon_3M6M")
    private Double addon_3M6M;
    @Column(name = "addon_6M1Y")
    private Double addon_6M1Y;
    @Column(name = "addon_1Y2Y")
    private Double addon_1Y2Y;
    @Column(name = "addon_2Y3Y")
    private Double addon_2Y3Y;
    @Column(name = "addon_3Y5Y")
    private Double addon_3Y5Y;
    @Column(name = "addon_5Y7Y")
    private Double addon_5Y7Y;
    @Column(name = "addon_7Y10Y")
    private Double addon_7Y10Y;
    @Column(name = "addon_RF10Y15Y")
    private Double addon_RF10Y15Y;
    @Column(name = "addon_RF15Y20Y")
    private Double addon_RF15Y20Y;
    @Column(name = "addon_RF20Y30Y")
    private Double addon_RF20Y30Y;
    @Column(name = "addon_RF30Y40Y")
    private Double addon_RF30Y40Y;
    @Column(name = "addon_RF40Y999Y")
    private Double addon_RF40Y999Y;
    @Column(name = "mtm_positivo_0D1W")
    private Double mtm_positivo_0D1W;
    @Column(name = "mtm_positivo_1W1M")
    private Double mtm_positivo_1W1M;
    @Column(name = "mtm_positivo_1M3M")
    private Double mtm_positivo_1M3M;
    @Column(name = "mtm_positivo_3M6M")
    private Double mtm_positivo_3M6M;
    @Column(name = "mtm_positivo_6M1Y")
    private Double mtm_positivo_6M1Y;
    @Column(name = "mtm_positivo_1Y2Y")
    private Double mtm_positivo_1Y2Y;
    @Column(name = "mtm_positivo_2Y3Y")
    private Double mtm_positivo_2Y3Y;
    @Column(name = "mtm_positivo_3Y5Y")
    private Double mtm_positivo_3Y5Y;
    @Column(name = "mtm_positivo_5Y7Y")
    private Double mtm_positivo_5Y7Y;
    @Column(name = "mtm_positivo_7Y10Y")
    private Double mtm_positivo_7Y10Y;
    @Column(name = "mtm_positivo_RF10Y15Y")
    private Double mtm_positivo_RF10Y15Y;
    @Column(name = "mtm_positivo_RF15Y20Y")
    private Double mtm_positivo_RF15Y20Y;
    @Column(name = "mtm_positivo_RF20Y30Y")
    private Double mtm_positivo_RF20Y30Y;
    @Column(name = "mtm_positivo_RF30Y40Y")
    private Double mtm_positivo_RF30Y40Y;
    @Column(name = "mtm_positivo_RF40Y999Y")
    private Double mtm_positivo_RF40Y999Y;
    @Column(name = "nrg_0D1W")
    private Double nrg_0D1W;
    @Column(name = "nrg_1W1M")
    private Double nrg_1W1M;
    @Column(name = "nrg_1M3M")
    private Double nrg_1M3M;
    @Column(name = "nrg_3M6M")
    private Double nrg_3M6M;
    @Column(name = "nrg_6M1Y")
    private Double nrg_6M1Y;
    @Column(name = "nrg_1Y2Y")
    private Double nrg_1Y2Y;
    @Column(name = "nrg_2Y3Y")
    private Double nrg_2Y3Y;
    @Column(name = "nrg_3Y5Y")
    private Double nrg_3Y5Y;
    @Column(name = "nrg_5Y7Y")
    private Double nrg_5Y7Y;
    @Column(name = "nrg_7Y10Y")
    private Double nrg_7Y10Y;
    @Column(name = "nrg_RF10Y15Y")
    private Double nrg_RF10Y15Y;
    @Column(name = "nrg_RF15Y20Y")
    private Double nrg_RF15Y20Y;
    @Column(name = "nrg_RF20Y30Y")
    private Double nrg_RF20Y30Y;
    @Column(name = "nrg_RF30Y40Y")
    private Double nrg_RF30Y40Y;
    @Column(name = "nrg_RF40Y999Y")
    private Double nrg_RF40Y999Y;
    @Column(name = "pee_0D1W")
    private Double pee_0D1W;
    @Column(name = "pee_1W1M")
    private Double pee_1W1M;
    @Column(name = "pee_1M3M")
    private Double pee_1M3M;
    @Column(name = "pee_3M6M")
    private Double pee_3M6M;
    @Column(name = "pee_6M1Y")
    private Double pee_6M1Y;
    @Column(name = "pee_1Y2Y")
    private Double pee_1Y2Y;
    @Column(name = "pee_2Y3Y")
    private Double pee_2Y3Y;
    @Column(name = "pee_3Y5Y")
    private Double pee_3Y5Y;
    @Column(name = "pee_5Y7Y")
    private Double pee_5Y7Y;
    @Column(name = "pee_7Y10Y")
    private Double pee_7Y10Y;
    @Column(name = "pee_RF10Y15Y")
    private Double pee_RF10Y15Y;
    @Column(name = "pee_RF15Y20Y")
    private Double pee_RF15Y20Y;
    @Column(name = "pee_RF20Y30Y")
    private Double pee_RF20Y30Y;
    @Column(name = "pee_RF30Y40Y")
    private Double pee_RF30Y40Y;
    @Column(name = "pee_RF40Y999Y")
    private Double pee_RF40Y999Y;
    @Column(name = "neteo_maximo")
    private Double neteoMaximo;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    public Integer getIdNeteo() {
        return idNeteo;
    }

    public void setIdNeteo(Integer idNeteo) {
        this.idNeteo = idNeteo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getMtm_bruto_0D1W() {
        return mtm_bruto_0D1W;
    }

    public void setMtm_bruto_0D1W(Double mtm_bruto_0D1W) {
        this.mtm_bruto_0D1W = mtm_bruto_0D1W;
    }

    public Double getMtm_bruto_1W1M() {
        return mtm_bruto_1W1M;
    }

    public void setMtm_bruto_1W1M(Double mtm_bruto_1W1M) {
        this.mtm_bruto_1W1M = mtm_bruto_1W1M;
    }

    public Double getMtm_bruto_1M3M() {
        return mtm_bruto_1M3M;
    }

    public void setMtm_bruto_1M3M(Double mtm_bruto_1M3M) {
        this.mtm_bruto_1M3M = mtm_bruto_1M3M;
    }

    public Double getMtm_bruto_3M6M() {
        return mtm_bruto_3M6M;
    }

    public void setMtm_bruto_3M6M(Double mtm_bruto_3M6M) {
        this.mtm_bruto_3M6M = mtm_bruto_3M6M;
    }

    public Double getMtm_bruto_6M1Y() {
        return mtm_bruto_6M1Y;
    }

    public void setMtm_bruto_6M1Y(Double mtm_bruto_6M1Y) {
        this.mtm_bruto_6M1Y = mtm_bruto_6M1Y;
    }

    public Double getMtm_bruto_1Y2Y() {
        return mtm_bruto_1Y2Y;
    }

    public void setMtm_bruto_1Y2Y(Double mtm_bruto_1Y2Y) {
        this.mtm_bruto_1Y2Y = mtm_bruto_1Y2Y;
    }

    public Double getMtm_bruto_2Y3Y() {
        return mtm_bruto_2Y3Y;
    }

    public void setMtm_bruto_2Y3Y(Double mtm_bruto_2Y3Y) {
        this.mtm_bruto_2Y3Y = mtm_bruto_2Y3Y;
    }

    public Double getMtm_bruto_3Y5Y() {
        return mtm_bruto_3Y5Y;
    }

    public void setMtm_bruto_3Y5Y(Double mtm_bruto_3Y5Y) {
        this.mtm_bruto_3Y5Y = mtm_bruto_3Y5Y;
    }

    public Double getMtm_bruto_5Y7Y() {
        return mtm_bruto_5Y7Y;
    }

    public void setMtm_bruto_5Y7Y(Double mtm_bruto_5Y7Y) {
        this.mtm_bruto_5Y7Y = mtm_bruto_5Y7Y;
    }

    public Double getMtm_bruto_7Y10Y() {
        return mtm_bruto_7Y10Y;
    }

    public void setMtm_bruto_7Y10Y(Double mtm_bruto_7Y10Y) {
        this.mtm_bruto_7Y10Y = mtm_bruto_7Y10Y;
    }

    public Double getMtm_bruto_RF10Y15Y() {
        return mtm_bruto_RF10Y15Y;
    }

    public void setMtm_bruto_RF10Y15Y(Double mtm_bruto_RF10Y15Y) {
        this.mtm_bruto_RF10Y15Y = mtm_bruto_RF10Y15Y;
    }

    public Double getMtm_bruto_RF15Y20Y() {
        return mtm_bruto_RF15Y20Y;
    }

    public void setMtm_bruto_RF15Y20Y(Double mtm_bruto_RF15Y20Y) {
        this.mtm_bruto_RF15Y20Y = mtm_bruto_RF15Y20Y;
    }

    public Double getMtm_bruto_RF20Y30Y() {
        return mtm_bruto_RF20Y30Y;
    }

    public void setMtm_bruto_RF20Y30Y(Double mtm_bruto_RF20Y30Y) {
        this.mtm_bruto_RF20Y30Y = mtm_bruto_RF20Y30Y;
    }

    public Double getMtm_bruto_RF30Y40Y() {
        return mtm_bruto_RF30Y40Y;
    }

    public void setMtm_bruto_RF30Y40Y(Double mtm_bruto_RF30Y40Y) {
        this.mtm_bruto_RF30Y40Y = mtm_bruto_RF30Y40Y;
    }

    public Double getMtm_bruto_RF40Y999Y() {
        return mtm_bruto_RF40Y999Y;
    }

    public void setMtm_bruto_RF40Y999Y(Double mtm_bruto_RF40Y999Y) {
        this.mtm_bruto_RF40Y999Y = mtm_bruto_RF40Y999Y;
    }
    
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getAddon_0D1W() {
        return addon_0D1W;
    }

    public void setAddon_0D1W(Double addon_0D1W) {
        this.addon_0D1W = addon_0D1W;
    }

    public Double getAddon_1W1M() {
        return addon_1W1M;
    }

    public void setAddon_1W1M(Double addon_1W1M) {
        this.addon_1W1M = addon_1W1M;
    }

    public Double getAddon_1M3M() {
        return addon_1M3M;
    }

    public void setAddon_1M3M(Double addon_1M3M) {
        this.addon_1M3M = addon_1M3M;
    }

    public Double getAddon_3M6M() {
        return addon_3M6M;
    }

    public void setAddon_3M6M(Double addon_3M6M) {
        this.addon_3M6M = addon_3M6M;
    }

    public Double getAddon_6M1Y() {
        return addon_6M1Y;
    }

    public void setAddon_6M1Y(Double addon_6M1Y) {
        this.addon_6M1Y = addon_6M1Y;
    }

    public Double getAddon_1Y2Y() {
        return addon_1Y2Y;
    }

    public void setAddon_1Y2Y(Double addon_1Y2Y) {
        this.addon_1Y2Y = addon_1Y2Y;
    }

    public Double getAddon_2Y3Y() {
        return addon_2Y3Y;
    }

    public void setAddon_2Y3Y(Double addon_2Y3Y) {
        this.addon_2Y3Y = addon_2Y3Y;
    }

    public Double getAddon_3Y5Y() {
        return addon_3Y5Y;
    }

    public void setAddon_3Y5Y(Double addon_3Y5Y) {
        this.addon_3Y5Y = addon_3Y5Y;
    }

    public Double getAddon_5Y7Y() {
        return addon_5Y7Y;
    }

    public void setAddon_5Y7Y(Double addon_5Y7Y) {
        this.addon_5Y7Y = addon_5Y7Y;
    }

    public Double getAddon_7Y10Y() {
        return addon_7Y10Y;
    }

    public void setAddon_7Y10Y(Double addon_7Y10Y) {
        this.addon_7Y10Y = addon_7Y10Y;
    }

    public Double getAddon_RF10Y15Y() {
        return addon_RF10Y15Y;
    }

    public void setAddon_RF10Y15Y(Double addon_RF10Y15Y) {
        this.addon_RF10Y15Y = addon_RF10Y15Y;
    }

    public Double getAddon_RF15Y20Y() {
        return addon_RF15Y20Y;
    }

    public void setAddon_RF15Y20Y(Double addon_RF15Y20Y) {
        this.addon_RF15Y20Y = addon_RF15Y20Y;
    }

    public Double getAddon_RF20Y30Y() {
        return addon_RF20Y30Y;
    }

    public void setAddon_RF20Y30Y(Double addon_RF20Y30Y) {
        this.addon_RF20Y30Y = addon_RF20Y30Y;
    }

    public Double getAddon_RF30Y40Y() {
        return addon_RF30Y40Y;
    }

    public void setAddon_RF30Y40Y(Double addon_RF30Y40Y) {
        this.addon_RF30Y40Y = addon_RF30Y40Y;
    }

    public Double getAddon_RF40Y999Y() {
        return addon_RF40Y999Y;
    }

    public void setAddon_RF40Y999Y(Double addon_RF40Y999Y) {
        this.addon_RF40Y999Y = addon_RF40Y999Y;
    }

    public Double getMtm_positivo_0D1W() {
        return mtm_positivo_0D1W;
    }

    public void setMtm_positivo_0D1W(Double mtm_positivo_0D1W) {
        this.mtm_positivo_0D1W = mtm_positivo_0D1W;
    }

    public Double getMtm_positivo_1W1M() {
        return mtm_positivo_1W1M;
    }

    public void setMtm_positivo_1W1M(Double mtm_positivo_1W1M) {
        this.mtm_positivo_1W1M = mtm_positivo_1W1M;
    }

    public Double getMtm_positivo_1M3M() {
        return mtm_positivo_1M3M;
    }

    public void setMtm_positivo_1M3M(Double mtm_positivo_1M3M) {
        this.mtm_positivo_1M3M = mtm_positivo_1M3M;
    }

    public Double getMtm_positivo_3M6M() {
        return mtm_positivo_3M6M;
    }

    public void setMtm_positivo_3M6M(Double mtm_positivo_3M6M) {
        this.mtm_positivo_3M6M = mtm_positivo_3M6M;
    }

    public Double getMtm_positivo_6M1Y() {
        return mtm_positivo_6M1Y;
    }

    public void setMtm_positivo_6M1Y(Double mtm_positivo_6M1Y) {
        this.mtm_positivo_6M1Y = mtm_positivo_6M1Y;
    }

    public Double getMtm_positivo_1Y2Y() {
        return mtm_positivo_1Y2Y;
    }

    public void setMtm_positivo_1Y2Y(Double mtm_positivo_1Y2Y) {
        this.mtm_positivo_1Y2Y = mtm_positivo_1Y2Y;
    }

    public Double getMtm_positivo_2Y3Y() {
        return mtm_positivo_2Y3Y;
    }

    public void setMtm_positivo_2Y3Y(Double mtm_positivo_2Y3Y) {
        this.mtm_positivo_2Y3Y = mtm_positivo_2Y3Y;
    }

    public Double getMtm_positivo_3Y5Y() {
        return mtm_positivo_3Y5Y;
    }

    public void setMtm_positivo_3Y5Y(Double mtm_positivo_3Y5Y) {
        this.mtm_positivo_3Y5Y = mtm_positivo_3Y5Y;
    }

    public Double getMtm_positivo_5Y7Y() {
        return mtm_positivo_5Y7Y;
    }

    public void setMtm_positivo_5Y7Y(Double mtm_positivo_5Y7Y) {
        this.mtm_positivo_5Y7Y = mtm_positivo_5Y7Y;
    }

    public Double getMtm_positivo_7Y10Y() {
        return mtm_positivo_7Y10Y;
    }

    public void setMtm_positivo_7Y10Y(Double mtm_positivo_7Y10Y) {
        this.mtm_positivo_7Y10Y = mtm_positivo_7Y10Y;
    }

    public Double getMtm_positivo_RF10Y15Y() {
        return mtm_positivo_RF10Y15Y;
    }

    public void setMtm_positivo_RF10Y15Y(Double mtm_positivo_RF10Y15Y) {
        this.mtm_positivo_RF10Y15Y = mtm_positivo_RF10Y15Y;
    }

    public Double getMtm_positivo_RF15Y20Y() {
        return mtm_positivo_RF15Y20Y;
    }

    public void setMtm_positivo_RF15Y20Y(Double mtm_positivo_RF15Y20Y) {
        this.mtm_positivo_RF15Y20Y = mtm_positivo_RF15Y20Y;
    }

    public Double getMtm_positivo_RF20Y30Y() {
        return mtm_positivo_RF20Y30Y;
    }

    public void setMtm_positivo_RF20Y30Y(Double mtm_positivo_RF20Y30Y) {
        this.mtm_positivo_RF20Y30Y = mtm_positivo_RF20Y30Y;
    }

    public Double getMtm_positivo_RF30Y40Y() {
        return mtm_positivo_RF30Y40Y;
    }

    public void setMtm_positivo_RF30Y40Y(Double mtm_positivo_RF30Y40Y) {
        this.mtm_positivo_RF30Y40Y = mtm_positivo_RF30Y40Y;
    }

    public Double getMtm_positivo_RF40Y999Y() {
        return mtm_positivo_RF40Y999Y;
    }

    public void setMtm_positivo_RF40Y999Y(Double mtm_positivo_RF40Y999Y) {
        this.mtm_positivo_RF40Y999Y = mtm_positivo_RF40Y999Y;
    }

    public Double getNrg_0D1W() {
        return nrg_0D1W;
    }

    public void setNrg_0D1W(Double nrg_0D1W) {
        this.nrg_0D1W = nrg_0D1W;
    }

    public Double getNrg_1W1M() {
        return nrg_1W1M;
    }

    public void setNrg_1W1M(Double nrg_1W1M) {
        this.nrg_1W1M = nrg_1W1M;
    }

    public Double getNrg_1M3M() {
        return nrg_1M3M;
    }

    public void setNrg_1M3M(Double nrg_1M3M) {
        this.nrg_1M3M = nrg_1M3M;
    }

    public Double getNrg_3M6M() {
        return nrg_3M6M;
    }

    public void setNrg_3M6M(Double nrg_3M6M) {
        this.nrg_3M6M = nrg_3M6M;
    }

    public Double getNrg_6M1Y() {
        return nrg_6M1Y;
    }

    public void setNrg_6M1Y(Double nrg_6M1Y) {
        this.nrg_6M1Y = nrg_6M1Y;
    }

    public Double getNrg_1Y2Y() {
        return nrg_1Y2Y;
    }

    public void setNrg_1Y2Y(Double nrg_1Y2Y) {
        this.nrg_1Y2Y = nrg_1Y2Y;
    }

    public Double getNrg_2Y3Y() {
        return nrg_2Y3Y;
    }

    public void setNrg_2Y3Y(Double nrg_2Y3Y) {
        this.nrg_2Y3Y = nrg_2Y3Y;
    }

    public Double getNrg_3Y5Y() {
        return nrg_3Y5Y;
    }

    public void setNrg_3Y5Y(Double nrg_3Y5Y) {
        this.nrg_3Y5Y = nrg_3Y5Y;
    }

    public Double getNrg_5Y7Y() {
        return nrg_5Y7Y;
    }

    public void setNrg_5Y7Y(Double nrg_5Y7Y) {
        this.nrg_5Y7Y = nrg_5Y7Y;
    }

    public Double getNrg_7Y10Y() {
        return nrg_7Y10Y;
    }

    public void setNrg_7Y10Y(Double nrg_7Y10Y) {
        this.nrg_7Y10Y = nrg_7Y10Y;
    }

    public Double getNrg_RF10Y15Y() {
        return nrg_RF10Y15Y;
    }

    public void setNrg_RF10Y15Y(Double nrg_RF10Y15Y) {
        this.nrg_RF10Y15Y = nrg_RF10Y15Y;
    }

    public Double getNrg_RF15Y20Y() {
        return nrg_RF15Y20Y;
    }

    public void setNrg_RF15Y20Y(Double nrg_RF15Y20Y) {
        this.nrg_RF15Y20Y = nrg_RF15Y20Y;
    }

    public Double getNrg_RF20Y30Y() {
        return nrg_RF20Y30Y;
    }

    public void setNrg_RF20Y30Y(Double nrg_RF20Y30Y) {
        this.nrg_RF20Y30Y = nrg_RF20Y30Y;
    }

    public Double getNrg_RF30Y40Y() {
        return nrg_RF30Y40Y;
    }

    public void setNrg_RF30Y40Y(Double nrg_RF30Y40Y) {
        this.nrg_RF30Y40Y = nrg_RF30Y40Y;
    }

    public Double getNrg_RF40Y999Y() {
        return nrg_RF40Y999Y;
    }

    public void setNrg_RF40Y999Y(Double nrg_RF40Y999Y) {
        this.nrg_RF40Y999Y = nrg_RF40Y999Y;
    }

    public Double getPee_0D1W() {
        return pee_0D1W;
    }

    public void setPee_0D1W(Double pee_0D1W) {
        this.pee_0D1W = pee_0D1W;
    }

    public Double getPee_1W1M() {
        return pee_1W1M;
    }

    public void setPee_1W1M(Double pee_1W1M) {
        this.pee_1W1M = pee_1W1M;
    }

    public Double getPee_1M3M() {
        return pee_1M3M;
    }

    public void setPee_1M3M(Double pee_1M3M) {
        this.pee_1M3M = pee_1M3M;
    }

    public Double getPee_3M6M() {
        return pee_3M6M;
    }

    public void setPee_3M6M(Double pee_3M6M) {
        this.pee_3M6M = pee_3M6M;
    }

    public Double getPee_6M1Y() {
        return pee_6M1Y;
    }

    public void setPee_6M1Y(Double pee_6M1Y) {
        this.pee_6M1Y = pee_6M1Y;
    }

    public Double getPee_1Y2Y() {
        return pee_1Y2Y;
    }

    public void setPee_1Y2Y(Double pee_1Y2Y) {
        this.pee_1Y2Y = pee_1Y2Y;
    }

    public Double getPee_2Y3Y() {
        return pee_2Y3Y;
    }

    public void setPee_2Y3Y(Double pee_2Y3Y) {
        this.pee_2Y3Y = pee_2Y3Y;
    }

    public Double getPee_3Y5Y() {
        return pee_3Y5Y;
    }

    public void setPee_3Y5Y(Double pee_3Y5Y) {
        this.pee_3Y5Y = pee_3Y5Y;
    }

    public Double getPee_5Y7Y() {
        return pee_5Y7Y;
    }

    public void setPee_5Y7Y(Double pee_5Y7Y) {
        this.pee_5Y7Y = pee_5Y7Y;
    }

    public Double getPee_7Y10Y() {
        return pee_7Y10Y;
    }

    public void setPee_7Y10Y(Double pee_7Y10Y) {
        this.pee_7Y10Y = pee_7Y10Y;
    }

    public Double getPee_RF10Y15Y() {
        return pee_RF10Y15Y;
    }

    public void setPee_RF10Y15Y(Double pee_RF10Y15Y) {
        this.pee_RF10Y15Y = pee_RF10Y15Y;
    }

    public Double getPee_RF15Y20Y() {
        return pee_RF15Y20Y;
    }

    public void setPee_RF15Y20Y(Double pee_RF15Y20Y) {
        this.pee_RF15Y20Y = pee_RF15Y20Y;
    }

    public Double getPee_RF20Y30Y() {
        return pee_RF20Y30Y;
    }

    public void setPee_RF20Y30Y(Double pee_RF20Y30Y) {
        this.pee_RF20Y30Y = pee_RF20Y30Y;
    }

    public Double getPee_RF30Y40Y() {
        return pee_RF30Y40Y;
    }

    public void setPee_RF30Y40Y(Double pee_RF30Y40Y) {
        this.pee_RF30Y40Y = pee_RF30Y40Y;
    }

    public Double getPee_RF40Y999Y() {
        return pee_RF40Y999Y;
    }

    public void setPee_RF40Y999Y(Double pee_RF40Y999Y) {
        this.pee_RF40Y999Y = pee_RF40Y999Y;
    }

    public Double getNeteoMaximo() {
        return neteoMaximo;
    }

    public void setNeteoMaximo(Double neteoMaximo) {
        this.neteoMaximo = neteoMaximo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNeteo != null ? idNeteo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NeteoCorpMx)) {
            return false;
        }
        NeteoCorpMx other = (NeteoCorpMx) object;
        if ((this.idNeteo == null && other.idNeteo != null)
                || (this.idNeteo != null && !this.idNeteo.equals(other.idNeteo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.santander.service.entity.NeteoCorpMx[ idNeteo=" + idNeteo + " ]";
    }

}
