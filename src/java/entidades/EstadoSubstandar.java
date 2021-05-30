package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the estado_substandar database table.
 *
 */
@Entity
@Table(name = "estado_substandar")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "EstadoSubstandar.findAll", query = "SELECT e FROM EstadoSubstandar e")
        ,
    @NamedQuery(name = "EstadoSubstandar.findByName", query = "SELECT e FROM EstadoSubstandar e WHERE e.nombre=:nombre")
        ,
    @NamedQuery(name = "EstadoSubstandar.findById", query = "SELECT e FROM EstadoSubstandar e WHERE e.id=:id")
})
public class EstadoSubstandar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String nombre;

    //bi-directional many-to-one association to EstadosSubstandar
    @OneToMany(mappedBy = "estadoSubstandar")
    private List<EstadosSubstandar> estadosSubstandars;

    @OneToMany(mappedBy = "estadoSubstandar")
    private List<Clientes> clientes;

    public EstadoSubstandar() {
    }

    public EstadoSubstandar(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<EstadosSubstandar> getEstadosSubstandars() {
        return this.estadosSubstandars;
    }

    public void setEstadosSubstandars(List<EstadosSubstandar> estadosSubstandars) {
        this.estadosSubstandars = estadosSubstandars;
    }

    public List<Clientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public EstadosSubstandar addEstadosSubstandar(EstadosSubstandar estadosSubstandar) {
        getEstadosSubstandars().add(estadosSubstandar);
        estadosSubstandar.setEstadoSubstandar(this);

        return estadosSubstandar;
    }

    public EstadosSubstandar removeEstadosSubstandar(EstadosSubstandar estadosSubstandar) {
        getEstadosSubstandars().remove(estadosSubstandar);
        estadosSubstandar.setEstadoSubstandar(null);

        return estadosSubstandar;
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
        if (!(object instanceof EstadoSubstandar)) {
            return false;
        }
        EstadoSubstandar other = (EstadoSubstandar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
