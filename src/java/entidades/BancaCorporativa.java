package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the banca_corporativa database table.
 *
 */
@Entity
@Table(name = "banca_corporativa")
@NamedQuery(name = "BancaCorporativa.findAll", query = "SELECT b FROM BancaCorporativa b")
public class BancaCorporativa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    private String descripcion;

    private String nombre;

    //bi-directional many-to-one association to Clientes
    @OneToMany(mappedBy = "bancaCorporativaBean")
    private List<Clientes> clientes;

    public BancaCorporativa() {
    }

    public String getId() {
        return id;
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

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Clientes> getClientes() {
        return this.clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public Clientes addCliente(Clientes cliente) {
        getClientes().add(cliente);
        cliente.setBancaCorporativaBean(this);

        return cliente;
    }

    public Clientes removeCliente(Clientes cliente) {
        getClientes().remove(cliente);
        cliente.setBancaCorporativaBean(null);

        return cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BancaCorporativa other = (BancaCorporativa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
