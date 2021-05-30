/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 03-08-2018
 --------------------------------------------------------------------------------
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "procesos")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
     // FIXPACK2 - inicio
    @NamedQuery(name = "Procesos.findAll", query = "SELECT p FROM Procesos p ORDER BY p.identificador ASC"),
    // FIXPACK2 - fin
    @NamedQuery(name = "Procesos.findByIdentificador", query = "SELECT p FROM Procesos p WHERE p.identificador = :identificador"),
    @NamedQuery(name = "Procesos.findByNombre", query = "SELECT p FROM Procesos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Procesos.findByCron", query = "SELECT p FROM Procesos p WHERE p.cron = :cron"),
    @NamedQuery(name = "Procesos.findByEstado", query = "SELECT p FROM Procesos p WHERE p.estado = :estado")})
public class Procesos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "identificador")
    private Integer identificador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cron")
    private String cron;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "clase")
    private String clase;
    // FIXPACK2 - inicio
    @Column(name = "automatico")
    private boolean automatico;
    // FIXPACK2 - inicio

    public Procesos() {
        this.estado = true;
    }

    public Procesos(Integer identificador) {
        this.identificador = identificador;
    }

    public Procesos(Integer identificador, String nombre, String cron, boolean estado) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.cron = cron;
        this.estado = estado;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificador != null ? identificador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesos)) {
            return false;
        }
        Procesos other = (Procesos) object;
        if ((this.identificador == null && other.identificador != null) || (this.identificador != null && !this.identificador.equals(other.identificador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Procesos[ identificador=" + identificador + " ]";
    }
    
      /**
     * @return the automatico
     */
    public boolean isAutomatico() {
        return automatico;
    }

    /**
     * @param automatico the automatico to set
     */
    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }
    
}
