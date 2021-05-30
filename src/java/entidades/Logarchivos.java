/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GCOCOL0281
 */
@Entity
@Table(name = "logarchivos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logarchivos.findAll", query = "SELECT l FROM Logarchivos l"),
    @NamedQuery(name = "Logarchivos.findById", query = "SELECT l FROM Logarchivos l WHERE l.id = :id"),
    @NamedQuery(name = "Logarchivos.findByLinea", query = "SELECT l FROM Logarchivos l WHERE l.linea = :linea"),
    @NamedQuery(name = "Logarchivos.findByLog", query = "SELECT l FROM Logarchivos l WHERE l.log = :log")})
public class Logarchivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "linea")
    private int linea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "log")
    private String log;
    @JoinColumn(name = "archivo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Archivos archivo;

    public Logarchivos() {
        this.id = UUID.randomUUID().toString();
    }

    public Logarchivos(String id) {
        this.id = id;
    }

    public Logarchivos(String id, int linea, String log) {
        this.id = id;
        this.linea = linea;
        this.log = log;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Archivos getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivos archivo) {
        this.archivo = archivo;
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
        if (!(object instanceof Logarchivos)) {
            return false;
        }
        Logarchivos other = (Logarchivos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Logarchivos[ id=" + id + " ]";
    }
    
}
