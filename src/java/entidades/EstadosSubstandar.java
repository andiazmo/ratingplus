package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the estados_substandar database table.
 *
 */
@Entity
@Table(name = "estados_substandar")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "EstadosSubstandar.findAll", query = "SELECT e FROM EstadosSubstandar e")
    ,
    @NamedQuery(name = "EstadosSubstandar.findByIdCliente", query = "SELECT e FROM EstadosSubstandar e WHERE e.cliente =:idCliente ORDER BY e.fecha DESC"),})
public class EstadosSubstandar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    //bi-directional many-to-one association to Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Clientes cliente;

    //bi-directional many-to-one association to EstadoSubstandar
    @ManyToOne
    @JoinColumn(name = "id_estado_substandar")
    private EstadoSubstandar estadoSubstandar;

    public EstadosSubstandar() {
    }

    public EstadosSubstandar(String id, String descripcion, Date fecha, Clientes cliente, EstadoSubstandar estadoSubstandar) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estadoSubstandar = estadoSubstandar;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Clientes getCliente() {
        return this.cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public EstadoSubstandar getEstadoSubstandar() {
        return this.estadoSubstandar;
    }

    public void setEstadoSubstandar(EstadoSubstandar estadoSubstandar) {
        this.estadoSubstandar = estadoSubstandar;
    }

}
