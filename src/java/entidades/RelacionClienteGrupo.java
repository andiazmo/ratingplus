package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the relacion_cliente_grupo database table.
 * 
 */
@Entity
@Table(name="relacion_cliente_grupo")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="RelacionClienteGrupo.findAll", query="SELECT r FROM RelacionClienteGrupo r")
        ,
    @NamedQuery(name="RelacionClienteGrupo.sumTotalActivo", query = "SELECT SUM(r.totalActivo) FROM RelacionClienteGrupo r INNER JOIN r.gruposEconomico gru WHERE gru.nombre =:nombre")
        ,
    @NamedQuery(name = "RelacionClienteGrupo.deleteRowByClient", query = "UPDATE RelacionClienteGrupo r SET r.gruposEconomico=:grupo, r.tipoVinculo=:vinculo, r.tipoRelacion=:relacion, r.porcParticipacion=:porcentaje, r.cantidadEmpleado=:empleados, r.rolJerarquico=:jerarquia, r.facturacion=:facturacion, r.totalActivo=:activo WHERE r.cliente=:id")
        ,
    @NamedQuery(name = "RelacionClienteGrupo.relacionByGrupo" , query = "SELECT SUM(r.porcParticipacion) FROM RelacionClienteGrupo r INNER JOIN r.gruposEconomico g WHERE g.codigoGrupo=:codigo")
        ,
    @NamedQuery(name = "RelacionClienteGrupo.relacionByGrupoAndClient", query = "SELECT r FROM RelacionClienteGrupo r WHERE r.gruposEconomico=:grupo AND r.cliente=:cliente")
        ,
    @NamedQuery(name = "RelacionClienteGrupo.relacionByClient", query = "SELECT r FROM RelacionClienteGrupo r WHERE r.cliente=:id")
})
public class RelacionClienteGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="cantidad_empleado")
	private Long cantidadEmpleado;

	private Double facturacion;

	@Column(name="porc_participacion")
	private Double porcParticipacion;

	@Column(name="rol_jerarquico")
	private String rolJerarquico;

	@Column(name="tipo_relacion")
	private String tipoRelacion;

	@Column(name="tipo_vinculo")
	private String tipoVinculo;

	@Column(name="total_activo")
	private Double totalActivo;

	//bi-directional many-to-one association to Clientes
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_client")
	private Clientes cliente;

	//bi-directional many-to-one association to GruposEconomicos
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_grup")
	private GruposEconomicos gruposEconomico;

	public RelacionClienteGrupo() {
            this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCantidadEmpleado() {
		return this.cantidadEmpleado;
	}

	public void setCantidadEmpleado(Long cantidadEmpleado) {
		this.cantidadEmpleado = cantidadEmpleado;
	}

	public Double getFacturacion() {
		return this.facturacion;
	}

	public void setFacturacion(Double facturacion) {
		this.facturacion = facturacion;
	}

	public Double getPorcParticipacion() {
		return this.porcParticipacion;
	}

	public void setPorcParticipacion(Double porcParticipacion) {
		this.porcParticipacion = porcParticipacion;
	}

	public String getRolJerarquico() {
		return this.rolJerarquico;
	}

	public void setRolJerarquico(String rolJerarquico) {
		this.rolJerarquico = rolJerarquico;
	}

	public String getTipoRelacion() {
		return this.tipoRelacion;
	}

	public void setTipoRelacion(String tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

	public String getTipoVinculo() {
		return this.tipoVinculo;
	}

	public void setTipoVinculo(String tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	public Double getTotalActivo() {
		return this.totalActivo;
	}

	public void setTotalActivo(Double totalActivo) {
		this.totalActivo = totalActivo;
	}

	public Clientes getCliente() {
		return this.cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public GruposEconomicos getGruposEconomico() {
		return this.gruposEconomico;
	}

	public void setGruposEconomico(GruposEconomicos gruposEconomico) {
		this.gruposEconomico = gruposEconomico;
	}

}