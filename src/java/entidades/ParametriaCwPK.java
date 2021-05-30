package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the parametria_cw database table.
 *
 */
@Embeddable
public class ParametriaCwPK implements Serializable {
    //default serial version id, required for serializable classes.

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    private String tabla;

    @Basic(optional = false)
    private String entidad;

    @Basic(optional = false)
    private String clave;

    public ParametriaCwPK() {
    }

    public String getTabla() {
        return this.tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getEntidad() {
        return this.entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParametriaCwPK)) {
            return false;
        }
        ParametriaCwPK castOther = (ParametriaCwPK) other;
        return this.tabla.equals(castOther.tabla)
                && this.entidad.equals(castOther.entidad)
                && this.clave.equals(castOther.clave);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.tabla.hashCode();
        hash = hash * prime + this.entidad.hashCode();
        hash = hash * prime + this.clave.hashCode();

        return hash;
    }
}
