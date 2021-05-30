/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author x356167
 */
@Entity
@Table(name = "grupos_economicos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruposEconomicos.findAll", query = "SELECT g FROM GruposEconomicos g")
    ,
    @NamedQuery(name = "GruposEconomicos.findById", query = "SELECT g FROM GruposEconomicos g WHERE g.codigoGrupo = :id")
    ,
    @NamedQuery(name = "GruposEconomicos.findByNombre", query = "SELECT g FROM GruposEconomicos g WHERE g.nombre = :nombre")
    ,
    @NamedQuery(name = "GruposEconomicos.findCodigoConsecutivo", query = "SELECT g FROM GruposEconomicos g WHERE g.consecutivo = TRUE ORDER BY g.codigoGrupo DESC")
    ,
    @NamedQuery(name = "GruposEconomicos.findCodigoNoConsecutivo", query = "SELECT g FROM GruposEconomicos g WHERE g.consecutivo = FALSE ORDER BY g.codigoGrupo ASC")
    ,
    @NamedQuery(name = "GruposEconomicos.findGrupoByCode", query = "SELECT g FROM GruposEconomicos g WHERE g.codigoGrupo=:codigo")
    ,
    @NamedQuery(name = "GruposEconomicos.findGrupoByRelacion", query = "SELECT g FROM GruposEconomicos g INNER JOIN g.relacionClienteGrupos r WHERE g.codigoGrupo=:codigo")
    ,
    @NamedQuery(name = "GruposEconomicos.grupoByNombre", query = "SELECT g FROM GruposEconomicos g WHERE g.nombre=:nombre")
    ,
    @NamedQuery(name = "GruposEconomicos.findGruposAsignables", query = "SELECT g FROM GruposEconomicos g WHERE g.nombre!=:nombre")
    ,
    @NamedQuery(name = "GruposEconomicos.findGruposOrderAsc", query = "SELECT g FROM GruposEconomicos g ORDER BY g.nombre ASC")

    
})

//select r.* from grupos_economicos g inner join relacion_cliente_grupo r on r.codigo_grup = g.id where g.codigo_grupo = 3;
public class GruposEconomicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_grupo")
    private Long codigoGrupo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cupo")
    private Double cupo;

    @Column(name = "vgarantia")
    private Double vgarantia;

    @Column(name = "garantia")
    private boolean garantia;

    @OneToMany(mappedBy = "grupo")
    private List<Clientes> clientesList;

    @Column(name = "codigo_forma_juridica")
    private String codigoFormaJuridica;

    @Column(name = "codigo_gestor_comercial")
    private String codigoGestorComercial;

    @Column(name = "codigo_modo_operacion")
    private String codigoModoOperacion;

    @Column(name = "codigo_pais_referencia")
    private String codigoPaisReferencia;

    @Column(name = "codigo_sector_economico")
    private String codigoSectorEconomico;

    @Column(name = "codigo_sucursal")
    private String codigoSucursal;

    @Column(name = "codigo_tipo_actividad")
    private String codigoTipoActividad;

    @Column(name = "codigo_tipo_grupo")
    private String codigoTipoGrupo;

    private Double consumido;

    @Column(name = "disponible_grupo")
    private Double disponibleGrupo;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @Column(name = "fecha_garantia")
    private Timestamp fechaGarantia;

    @Column(name = "usuario_creador")
    private String usuarioCreador;

    @Column(name = "facturacion_grupo")
    private Double facturacionGrupo;

    @Column(name = "total_activo")
    private Double totalActivo;

    @Column(name = "empleados_grupo")
    private Long empleadosGrupo;

    @Column(name = "consecutivo")
    private Boolean consecutivo;

    @Column(name = "id")
    private String id;
    
    @Column(name = "comentario")
    private String comentario;
    
    @OneToMany(mappedBy = "gruposEconomico")
    private List<RelacionClienteGrupo> relacionClienteGrupos;

    public GruposEconomicos() {
    }
    
    public GruposEconomicos(String id, String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCupo() {
        return cupo;
    }

    public void setCupo(Double cupo) {
        this.cupo = cupo;
    }

    public Double getVgarantia() {
        return vgarantia;
    }

    public void setVgarantia(Double vgarantia) {
        this.vgarantia = vgarantia;
    }

    public boolean isGarantia() {
        return garantia;
    }

    public void setGarantia(boolean garantia) {
        this.garantia = garantia;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public String getCodigoFormaJuridica() {
        return codigoFormaJuridica;
    }

    public void setCodigoFormaJuridica(String codigoFormaJuridica) {
        this.codigoFormaJuridica = codigoFormaJuridica;
    }

    public String getCodigoGestorComercial() {
        return codigoGestorComercial;
    }

    public void setCodigoGestorComercial(String codigoGestorComercial) {
        this.codigoGestorComercial = codigoGestorComercial;
    }

    public Long getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(Long codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getCodigoModoOperacion() {
        return codigoModoOperacion;
    }

    public void setCodigoModoOperacion(String codigoModoOperacion) {
        this.codigoModoOperacion = codigoModoOperacion;
    }

    public String getCodigoPaisReferencia() {
        return codigoPaisReferencia;
    }

    public void setCodigoPaisReferencia(String codigoPaisReferencia) {
        this.codigoPaisReferencia = codigoPaisReferencia;
    }

    public String getCodigoSectorEconomico() {
        return codigoSectorEconomico;
    }

    public void setCodigoSectorEconomico(String codigoSectorEconomico) {
        this.codigoSectorEconomico = codigoSectorEconomico;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getCodigoTipoActividad() {
        return codigoTipoActividad;
    }

    public void setCodigoTipoActividad(String codigoTipoActividad) {
        this.codigoTipoActividad = codigoTipoActividad;
    }

    public String getCodigoTipoGrupo() {
        return codigoTipoGrupo;
    }

    public void setCodigoTipoGrupo(String codigoTipoGrupo) {
        this.codigoTipoGrupo = codigoTipoGrupo;
    }

    public Double getConsumido() {
        return consumido;
    }

    public void setConsumido(Double consumido) {
        this.consumido = consumido;
    }

    public Double getDisponibleGrupo() {
        return disponibleGrupo;
    }

    public void setDisponibleGrupo(Double disponibleGrupo) {
        this.disponibleGrupo = disponibleGrupo;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaGarantia() {
        return fechaGarantia;
    }

    public void setFechaGarantia(Timestamp fechaGarantia) {
        this.fechaGarantia = fechaGarantia;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Double getFacturacionGrupo() {
        return facturacionGrupo;
    }

    public void setFacturacionGrupo(Double facturacionGrupo) {
        this.facturacionGrupo = facturacionGrupo;
    }

    public Double getTotalActivo() {
        return totalActivo;
    }

    public void setTotalActivo(Double totalActivo) {
        this.totalActivo = totalActivo;
    }

    public Long getEmpleadosGrupo() {
        return empleadosGrupo;
    }

    public void setEmpleadosGrupo(Long empleadosGrupo) {
        this.empleadosGrupo = empleadosGrupo;
    }

    public Boolean getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Boolean consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
    
    public List<RelacionClienteGrupo> getRelacionClienteGrupos() {
        return relacionClienteGrupos;
    }
    public void setRelacionClienteGrupos(List<RelacionClienteGrupo> relacionClienteGrupos) {
        this.relacionClienteGrupos = relacionClienteGrupos;
    }


    public Double getDisponible() {
        Double retorno = new Double(0);
        for (Clientes cliente : this.getClientesList()) {
            if (cliente.getCuposList() != null) {
                if (cliente.getCuposList().size() > 0) {
                    retorno += cliente.getCuposList().get(0).getLimitetotal();
                }
            }
        }
        if (this.getCupo() == null) {
            return retorno;
        } else {
            return this.getCupo() - retorno;
        }
    }
}
