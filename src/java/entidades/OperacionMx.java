/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "operacion_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperacionMx.findAll", query = "SELECT o FROM OperacionMx o"),
    @NamedQuery(name = "OperacionMx.findByCliente", query = "SELECT o FROM OperacionMx o WHERE o.nit = :cliente"
            + " and (o.fechaCreacion = :fechaCreacion or o.fechaCreacion = :fechaCreacionA)"),
    @NamedQuery(name = "OperacionMx.findByNumOp", query = "SELECT o FROM OperacionMx o WHERE o.numOperacion = :numOperacion"),
    @NamedQuery(name = "OperacionMx.findByClientBand", query = "SELECT o FROM OperacionMx o WHERE o.nit = :cliente"
            + " and (o.fechaCreacion = :fechaCreacion or o.fechaCreacion = :fechaCreacionA) "
            + " and o.timeBand.idTimeBand = :idTimeBand"),
})
public class OperacionMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion", columnDefinition = "serial")
    private Integer idOperacion;
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
    @JoinColumn(name = "time_band", referencedColumnName = "id_time_band")
    @ManyToOne
    private TimeBandMx timeBand;
    @Column(name = "interpolado")
    private Double interpolado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CorporativoCalMx> calculos;    

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
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

    public TimeBandMx getTimeBand() {
        return timeBand;
    }

    public void setTimeBand(TimeBandMx timeBand) {
        this.timeBand = timeBand;
    }

    public Double getInterpolado() {
        return interpolado;
    }

    public void setInterpolado(Double interpolado) {
        this.interpolado = interpolado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<CorporativoCalMx> getCalculos() {
        return calculos;
    }

    public void setCalculos(List<CorporativoCalMx> calculos) {
        this.calculos = calculos;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperacion != null ? idOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperacionMx)) {
            return false;
        }
        OperacionMx other = (OperacionMx) object;
        if ((this.idOperacion == null && other.idOperacion != null)
                || (this.idOperacion != null && !this.idOperacion.equals(other.idOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.santander.service.entity.OperacionMx[ idOperacion=" + idOperacion + " ]";
    }

}
