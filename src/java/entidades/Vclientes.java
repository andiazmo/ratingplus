
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author x356167
 */
@Entity
@Table(name = "vclientes")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vclientes.findAll", query = "SELECT v FROM Vclientes v"),
    @NamedQuery(name = "Vclientes.findByNn", query = "SELECT v FROM Vclientes v WHERE v.nn = :nn"),
    @NamedQuery(name = "Vclientes.findByNit", query = "SELECT v FROM Vclientes v WHERE v.nit = :nit"),
    @NamedQuery(name = "Vclientes.findByDigitochequeo", query = "SELECT v FROM Vclientes v WHERE v.digitochequeo = :digitochequeo"),
    @NamedQuery(name = "Vclientes.findByNombre", query = "SELECT v FROM Vclientes v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Vclientes.findByCiiu", query = "SELECT v FROM Vclientes v WHERE v.ciiu = :ciiu"),
    @NamedQuery(name = "Vclientes.findByRating", query = "SELECT v FROM Vclientes v WHERE v.rating = :rating"),
    @NamedQuery(name = "Vclientes.findByBanca", query = "SELECT v FROM Vclientes v WHERE v.banca = :banca"),
    @NamedQuery(name = "Vclientes.findByEstadocliente", query = "SELECT v FROM Vclientes v WHERE v.estadocliente = :estadocliente"),
    @NamedQuery(name = "Vclientes.findByDesde", query = "SELECT v FROM Vclientes v WHERE v.desde = :desde"),
    @NamedQuery(name = "Vclientes.findByGestor", query = "SELECT v FROM Vclientes v WHERE v.gestor = :gestor"),
    @NamedQuery(name = "Vclientes.findByFecharating", query = "SELECT v FROM Vclientes v WHERE v.fecharating = :fecharating"),
    @NamedQuery(name = "Vclientes.findByEstadofeve", query = "SELECT v FROM Vclientes v WHERE v.estadofeve = :estadofeve"),
    @NamedQuery(name = "Vclientes.findByValoractivo", query = "SELECT v FROM Vclientes v WHERE v.valoractivo = :valoractivo"),
    @NamedQuery(name = "Vclientes.findByGrupo", query = "SELECT v FROM Vclientes v WHERE v.grupo = :grupo"),
    @NamedQuery(name = "Vclientes.findByFechabalance", query = "SELECT v FROM Vclientes v WHERE v.fechabalance = :fechabalance"),
    @NamedQuery(name = "Vclientes.findByFechaalta", query = "SELECT v FROM Vclientes v WHERE v.fechaalta = :fechaalta"),
    @NamedQuery(name = "Vclientes.findByFechavencimiento", query = "SELECT v FROM Vclientes v WHERE v.fechavencimiento = :fechavencimiento"),
    @NamedQuery(name = "Vclientes.findByMoneda", query = "SELECT v FROM Vclientes v WHERE v.moneda = :moneda"),
    @NamedQuery(name = "Vclientes.findByLimitetotal", query = "SELECT v FROM Vclientes v WHERE v.limitetotal = :limitetotal"),
    @NamedQuery(name = "Vclientes.findByLimiteconsumido", query = "SELECT v FROM Vclientes v WHERE v.limiteconsumido = :limiteconsumido")})
public class Vclientes implements Serializable {
    @Size(max = 2147483647)
    @Column(name = "nn")
    @Id
    private String nn;
    @Size(max = 15)
    @Column(name = "nit")
    private String nit;
    @Size(max = 1)
    @Column(name = "digitochequeo")
    private String digitochequeo;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 40)
    @Column(name = "ciiu")
    private String ciiu;
    @Column(name = "rating")
    private BigInteger rating;
    @Size(max = 40)
    @Column(name = "banca")
    private String banca;
    @Size(max = 100)
    @Column(name = "estadocliente")
    private String estadocliente;
    
    @Size(max = 100)
    @Column(name = "reconduccion")
    private String reconduccion;
    
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Size(max = 255)
    @Column(name = "gestor")
    private String gestor;
    @Column(name = "fecharating")
    @Temporal(TemporalType.DATE)
    private Date fecharating;
    @Size(max = 100)
    @Column(name = "estadofeve")
    private String estadofeve;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valoractivo")
    private Double valoractivo;
    @Size(max = 100)
    @Column(name = "grupo")
    private String grupo;
    @Column(name = "fechabalance")
    @Temporal(TemporalType.DATE)
    private Date fechabalance;
    @Column(name = "fechaalta")
    @Temporal(TemporalType.DATE)
    private Date fechaalta;
    @Column(name = "fechavencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechavencimiento;
    @Size(max = 100)
    @Column(name = "moneda")
    private String moneda;
    @Column(name = "limitetotal")
    private Double limitetotal;
    @Column(name = "limiteconsumido")
    private Double limiteconsumido;

    public Vclientes() {
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDigitochequeo() {
        return digitochequeo;
    }

    public void setDigitochequeo(String digitochequeo) {
        this.digitochequeo = digitochequeo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiiu() {
        return ciiu;
    }

    public void setCiiu(String ciiu) {
        this.ciiu = ciiu;
    }

    public BigInteger getRating() {
        return rating;
    }

    public void setRating(BigInteger rating) {
        this.rating = rating;
    }

    public String getBanca() {
        return banca;
    }

    public void setBanca(String banca) {
        this.banca = banca;
    }

    public String getEstadocliente() {
        return estadocliente;
    }

    public void setEstadocliente(String estadocliente) {
        this.estadocliente = estadocliente;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public Date getFecharating() {
        return fecharating;
    }

    public void setFecharating(Date fecharating) {
        this.fecharating = fecharating;
    }

    public String getEstadofeve() {
        return estadofeve;
    }

    public void setEstadofeve(String estadofeve) {
        this.estadofeve = estadofeve;
    }

    public Double getValoractivo() {
        return valoractivo;
    }

    public void setValoractivo(Double valoractivo) {
        this.valoractivo = valoractivo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Date getFechabalance() {
        return fechabalance;
    }

    public void setFechabalance(Date fechabalance) {
        this.fechabalance = fechabalance;
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getLimitetotal() {
        return limitetotal;
    }

    public void setLimitetotal(Double limitetotal) {
        this.limitetotal = limitetotal;
    }

    public Double getLimiteconsumido() {
        return limiteconsumido;
    }

    public void setLimiteconsumido(Double limiteconsumido) {
        this.limiteconsumido = limiteconsumido;
    }

    public String getReconduccion() {
        return reconduccion;
    }

    public void setReconduccion(String reconduccion) {
        this.reconduccion = reconduccion;
    }
    
}
