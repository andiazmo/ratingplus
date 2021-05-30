package entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the parametria_cw database table.
 *
 */
@Entity
@Table(name = "parametria_cw")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametriaCw.findAll", query = "SELECT p FROM ParametriaCw p")
    ,
    @NamedQuery(name = "ParametriaCw.findByClave", query = "SELECT p FROM ParametriaCw p WHERE p.id.clave =:clave AND p.id.tabla =:tabla")
})
public class ParametriaCw implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ParametriaCwPK id;

    private String datos;

    @Basic(optional = false)
    @Column(name = "timest_alt")
    private Timestamp timestAlt;

    @Basic(optional = false)
    @Column(name = "timest_mod")
    private Timestamp timestMod;

    @Basic(optional = false)
    @Column(name = "usuario_alt")
    private String usuarioAlt;

    @Basic(optional = false)
    @Column(name = "usuario_mod")
    private String usuarioMod;

    public ParametriaCw() {
    }

    public ParametriaCwPK getId() {
        return this.id;
    }

    public void setId(ParametriaCwPK id) {
        this.id = id;
    }

    public String getDatos() {
        return this.datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public Timestamp getTimestAlt() {
        return this.timestAlt;
    }

    public void setTimestAlt(Timestamp timestAlt) {
        this.timestAlt = timestAlt;
    }

    public Timestamp getTimestMod() {
        return this.timestMod;
    }

    public void setTimestMod(Timestamp timestMod) {
        this.timestMod = timestMod;
    }

    public String getUsuarioAlt() {
        return this.usuarioAlt;
    }

    public void setUsuarioAlt(String usuarioAlt) {
        this.usuarioAlt = usuarioAlt;
    }

    public String getUsuarioMod() {
        return this.usuarioMod;
    }

    public void setUsuarioMod(String usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

}
