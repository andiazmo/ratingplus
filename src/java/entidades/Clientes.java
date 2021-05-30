/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *Proyecto : Cupos Auditoria 2018
 *Programados: Juan Herrera
 *Tag de cambio: CupoAut2018
 *Fecha del cambio : 22-05-2018
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
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

/**
 *
 * @author User
 */
@Entity
@Table(name = "clientes")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    ,
    @NamedQuery(name = "Clientes.findById", query = "SELECT c FROM Clientes c WHERE c.id = :id")
    ,
    @NamedQuery(name = "Clientes.findByNit", query = "SELECT c FROM Clientes c WHERE c.nit = :nit")
    ,
    @NamedQuery(name = "Clientes.findByDigitochequeo", query = "SELECT c FROM Clientes c WHERE c.digitochequeo = :digitochequeo")
    ,
    @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre")
    ,
    @NamedQuery(name = "Clientes.findByLikeNombre", query = "SELECT c FROM Clientes c WHERE c.nombre LIKE :nombre")
    ,
    @NamedQuery(name = "Clientes.findByLikeNit", query = "SELECT c FROM Clientes c WHERE c.nit LIKE :nit")
    ,
    @NamedQuery(name = "Clientes.findByRating", query = "SELECT c FROM Clientes c WHERE c.rating = :rating")
    ,
    @NamedQuery(name = "Clientes.findByDesde", query = "SELECT c FROM Clientes c WHERE c.desde = :desde")
    ,
    @NamedQuery(name = "Clientes.findByGestorcomercial", query = "SELECT c FROM Clientes c WHERE c.gestorcomercial = :gestorcomercial")
    ,
    @NamedQuery(name = "Clientes.findByFecharating", query = "SELECT c FROM Clientes c WHERE c.fecharating = :fecharating")
    ,
    @NamedQuery(name = "Clientes.findByValoractivo", query = "SELECT c FROM Clientes c WHERE c.valoractivo = :valoractivo")
    ,
    @NamedQuery(name = "Clientes.findByFechabalance", query = "SELECT c FROM Clientes c WHERE c.fechabalance = :fechabalance")
    ,
    @NamedQuery(name = "Clientes.findByAlta", query = "SELECT c FROM Clientes c WHERE c.alta = :alta")
    ,
    @NamedQuery(name = "Clientes.findByFechascan", query = "SELECT c FROM Clientes c WHERE c.fechascan = :fechascan")
    ,
    @NamedQuery(name = "Clientes.findByFechaestado", query = "SELECT c FROM Clientes c WHERE c.fechaestado = :fechaestado")
    ,
    @NamedQuery(name = "Clientes.findByRating", query = "SELECT c FROM Clientes c WHERE c.rating = :rating")
    ,
    /*  INI-ACT  Migracion de WS Cupos Bizagi a aplicativo Cupos Web
     *                   Implementacion fusuion de persistencia
     * 2018-03-07 */
    @NamedQuery(name = "Clientes.findByTipDoc_Nit_DV", query = "SELECT c FROM Clientes c WHERE c.nit LIKE :nit")
    ,
    /*  FIN-ACT  Migracion de WS Cupos Bizagi a aplicativo Cupos Web
     *                   Implementacion fusuion de persistencia
     * 2018-03-07 */
    @NamedQuery(name = "Clientes.findRatingByFechasAbierto", query = "select c from Clientes c where c.fecharating > :fechaIni and c.fecharating <=:fechaFin")
    ,
    @NamedQuery(name = "Clientes.findRatingByFechas", query = "select c from Clientes c where c.fecharating between :fechaIni and :fechaFin")
    ,
    @NamedQuery(name = "Clientes.findByGrupo", query = "SELECT c FROM Clientes c INNER JOIN c.relacionClienteGrupos r INNER JOIN r.gruposEconomico g WHERE g.codigoGrupo=:codigo")
    ,
    @NamedQuery(name = "Clientes.updateById", query = "UPDATE Clientes c SET c.grupo=:grupo WHERE c.id=:id")
/*select rs.* from clientes cl 
		 inner join relacion_cliente_grupo rs on rs.codigo_client = cl.id
		 inner join grupos_economicos g on g.codigo_grupo = rs.codigo_grup where g.codigo_grupo = 1; */
})

public class Clientes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Cupos> cuposList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nit")
    private String nit;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "digitochequeo")
    private String digitochequeo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @NotNull
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecharating")
    @Temporal(TemporalType.DATE)
    private Date fecharating;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valoractivo")
    private double valoractivo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fechabalance")
    @Temporal(TemporalType.DATE)
    private Date fechabalance;

    @Basic(optional = false)
    @NotNull
    @Column(name = "alta")
    @Temporal(TemporalType.DATE)
    private Date alta;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fechascan")
    @Temporal(TemporalType.DATE)
    private Date fechascan;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaestado")
    @Temporal(TemporalType.DATE)
    private Date fechaestado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private Double rating;

    @JoinColumn(name = "banca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bancas banca;
    @JoinColumn(name = "ciiu", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ciius ciiu;
    @JoinColumn(name = "scan", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private EstadoScan scan;
    @JoinColumn(name = "estadocliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados estadocliente;
    @JoinColumn(name = "gestorcomercial", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Gestores gestorcomercial;

    @JoinColumn(name = "grupo", referencedColumnName = "codigo_grupo")
    @ManyToOne(optional = false)
    private GruposEconomicos grupo;

    @JoinColumn(name = "reconduccion", referencedColumnName = "id")
    @ManyToOne
    private Reconduccion reconduccion;
    @JoinColumn(name = "subestado", referencedColumnName = "id")
    @ManyToOne
    private SubEstado subestado;
    @JoinColumn(name = "subestado_scan", referencedColumnName = "id")
    @ManyToOne
    private SubEstadoScan subestado_scan;
    @NotNull
    @Column(name = "ventas")
    private double ventas;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "codigo")
    @ManyToOne
    private TipoDocumento tipo_documento;

    @OneToMany(mappedBy = "cliente")
    private List<RelacionClienteGrupo> relacionClienteGrupos;

    @OneToMany(mappedBy = "cliente")
    private List<EstadosSubstandar> estadosSubstandars;

    @ManyToOne
    @JoinColumn(name = "sub_standar")
    private EstadoSubstandar estadoSubstandar;

    @JoinColumn(name = "banca_corporativa", referencedColumnName = "id")
    @ManyToOne
    private BancaCorporativa bancaCorporativaBean;

    public Clientes() {
        this.id = UUID.randomUUID().toString();
        this.alta = new Date();
        this.fechaestado = new Date();
        this.fechascan = new Date();
        this.fecharating = new Date();
    }

    public Clientes(String id) {
        this.id = id;
    }

    public Clientes(String id, String nit, String digitochequeo, String nombre, Date desde, Date fecharating, double valoractivo, Date fechabalance, Date alta, Date fechascan, Date fechaestado) {
        this.id = id;
        this.nit = nit;
        this.digitochequeo = digitochequeo;
        this.nombre = nombre;
        this.desde = desde;
        this.fecharating = fecharating;
        this.valoractivo = valoractivo;
        this.fechabalance = fechabalance;
        this.alta = alta;
        this.fechascan = fechascan;
        this.fechaestado = fechaestado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getFecharating() {
        return fecharating;
    }

    public void setFecharating(Date fecharating) {
        this.fecharating = fecharating;
    }

    public double getValoractivo() {
        return valoractivo;
    }

    public void setValoractivo(double valoractivo) {
        this.valoractivo = valoractivo;
    }

    public Date getFechabalance() {
        return fechabalance;
    }

    public void setFechabalance(Date fechabalance) {
        this.fechabalance = fechabalance;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getFechascan() {
        return fechascan;
    }

    public void setFechascan(Date fechascan) {
        this.fechascan = fechascan;
    }

    public Date getFechaestado() {
        return fechaestado;
    }

    public void setFechaestado(Date fechaestado) {
        this.fechaestado = fechaestado;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Bancas getBanca() {
        return banca;
    }

    public void setBanca(Bancas banca) {
        this.banca = banca;
    }

    public Ciius getCiiu() {
        return ciiu;
    }

    public void setCiiu(Ciius ciiu) {
        this.ciiu = ciiu;
    }

    public EstadoScan getScan() {
        return scan;
    }

    public void setScan(EstadoScan scan) {
        this.scan = scan;
    }

    public Estados getEstadocliente() {
        return estadocliente;
    }

    public void setEstadocliente(Estados estadocliente) {
        this.estadocliente = estadocliente;
    }

    public Gestores getGestorcomercial() {
        return gestorcomercial;
    }

    public void setGestorcomercial(Gestores gestorcomercial) {
        this.gestorcomercial = gestorcomercial;
    }

    public GruposEconomicos getGrupo() {
        return grupo;
    }

    public void setGrupo(GruposEconomicos grupo) {
        this.grupo = grupo;
    }

    public Reconduccion getReconduccion() {
        return reconduccion;
    }

    public void setReconduccion(Reconduccion reconduccion) {
        this.reconduccion = reconduccion;
    }

    public SubEstado getSubestado() {
        return subestado;
    }

    public void setSubestado(SubEstado subestado) {
        this.subestado = subestado;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public SubEstadoScan getSubestado_scan() {
        return subestado_scan;
    }

    public void setSubestado_scan(SubEstadoScan subestado_scan) {
        this.subestado_scan = subestado_scan;
    }

    public TipoDocumento getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(TipoDocumento tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public List<RelacionClienteGrupo> getRelacionClienteGrupos() {
        return relacionClienteGrupos;
    }

    public void setRelacionClienteGrupos(List<RelacionClienteGrupo> relacionClienteGrupos) {
        this.relacionClienteGrupos = relacionClienteGrupos;
    }

    public List<EstadosSubstandar> getEstadosSubstandars() {
        return estadosSubstandars;
    }

    public void setEstadosSubstandars(List<EstadosSubstandar> estadosSubstandars) {
        this.estadosSubstandars = estadosSubstandars;
    }

    public EstadoSubstandar getEstadoSubstandar() {
        return estadoSubstandar;
    }

    public void setEstadoSubstandar(EstadoSubstandar estadoSubstandar) {
        this.estadoSubstandar = estadoSubstandar;
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
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public List<Cupos> getCuposList() {
        return cuposList;
    }

    public void setCuposList(List<Cupos> cuposList) {
        this.cuposList = cuposList;
    }

    public BancaCorporativa getBancaCorporativaBean() {
        return this.bancaCorporativaBean;
    }

    public void setBancaCorporativaBean(BancaCorporativa bancaCorporativaBean) {
        this.bancaCorporativaBean = bancaCorporativaBean;
    }

    @Override
    public String toString() {
        return "entidades.Clientes[ id=" + id + " ]";
    }

    // CupoAut2018 -Inicio
    public String getCupoEstado() {
        String estadoCupo = "";
        List<Cupos> cupo = getCuposList();
        if (cupo.size() >= 1) {
            estadoCupo = cuposList.get(0).getEstadoActualCupo();
        }
        return estadoCupo;
    }
    // CupoAut2018 -Fin

}
