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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x217287
 */
@Entity
@Table(name = "parametros_alertas_mx")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrosAlertasMx.findByDiasFin", query = "SELECT e FROM ParametrosAlertasMx e ")})

public class ParametrosAlertasMx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_parametroalerta")
    private String Idparametroalerta;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "inicio_porcentaje")
    private Double inicioporcentaje;
    @Column(name = "fin_porcentaje")
    private Double finporcentaje;

    public ParametrosAlertasMx() {
    this.Idparametroalerta=UUID.randomUUID().toString();
    }

    public ParametrosAlertasMx(String Idparametroalerta) {
        this.Idparametroalerta = Idparametroalerta;
    }

    public ParametrosAlertasMx(String nombre,Double inicioporcentaje, Double finporcentaje) {
        this.nombre = nombre;
        this.inicioporcentaje=inicioporcentaje;
        this.finporcentaje=finporcentaje;
    }

    public String getIdparametroalerta() {
        return Idparametroalerta;
    }

    public void setIdparametroalerta(String Idparametroalerta) {
        this.Idparametroalerta = Idparametroalerta;
    }

     
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getInicioporcentaje() {
        return inicioporcentaje;
    }

    public void setInicioporcentaje(Double inicioporcentaje) {
        this.inicioporcentaje = inicioporcentaje;
    }

    public Double getFinporcentaje() {
        return finporcentaje;
    }

    public void setFinporcentaje(Double finporcentaje) {
        this.finporcentaje = finporcentaje;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Idparametroalerta != null ? Idparametroalerta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosAlertasMx)) {
            return false;
        }
        ParametrosAlertasMx other = (ParametrosAlertasMx) object;
        if ((this.Idparametroalerta == null && other.Idparametroalerta != null) || (this.Idparametroalerta != null
                && !this.Idparametroalerta.equals(other.Idparametroalerta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ParametrosAlertasMx[ Idparametroalerta=" + Idparametroalerta + " ]";
    }

}
