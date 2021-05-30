/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.santander.service.entity;

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
@Table(name = "info_portafolio_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfoPortafolioMx.findAll", query = "SELECT i FROM InfoPortafolioMx i"),
    @NamedQuery(name = "InfoPortafolioMx.listUnique",
            query = "SELECT DISTINCT i.nit FROM InfoPortafolioMx i")})
public class InfoPortafolioMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info", columnDefinition = "serial")
    private Integer idInfo;
    @Column(name = "num_operacion")
    private String numOperacion;
    @Column(name = "tipo_operacion")
    private String tipoOperacion;
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "nit")
    private String nit;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "moneda_nominal")
    private String monedaNominal;
    @Column(name = "nominal")
    private String nominal;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "par_monedas")
    private String parMonedas;
    @Column(name = "mtm")
    private String mtm;
    @Column(name = "mtm_divisa")
    private String mtmDivisa;

    public InfoPortafolioMx() {
    }

    public InfoPortafolioMx(Integer idInfo) {
        this.idInfo = idInfo;
    }

    public InfoPortafolioMx(Integer idInfo, String numOperacion, String nit) {
        this.idInfo = idInfo;
        this.numOperacion = numOperacion;
        this.nit = nit;
    }

    public Integer getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(Integer idInfo) {
        this.idInfo = idInfo;
    }

    public String getNumOperacion() {
        return numOperacion;
    }

    public void setNumOperacion(String numOperacion) {
        this.numOperacion = numOperacion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMonedaNominal() {
        return monedaNominal;
    }

    public void setMonedaNominal(String monedaNominal) {
        this.monedaNominal = monedaNominal;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getParMonedas() {
        return parMonedas;
    }

    public void setParMonedas(String parMonedas) {
        this.parMonedas = parMonedas;
    }

    public String getMtm() {
        return mtm;
    }

    public void setMtm(String mtm) {
        this.mtm = mtm;
    }

    public String getMtmDivisa() {
        return mtmDivisa;
    }

    public void setMtmDivisa(String mtmDivisa) {
        this.mtmDivisa = mtmDivisa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInfo != null ? idInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfoPortafolioMx)) {
            return false;
        }
        InfoPortafolioMx other = (InfoPortafolioMx) object;
        if ((this.idInfo == null && other.idInfo != null)
                || (this.idInfo != null && !this.idInfo.equals(other.idInfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.santander.service.entity.InfoPortafolioMx[ idInfo=" + idInfo + " ]";
    }

}
