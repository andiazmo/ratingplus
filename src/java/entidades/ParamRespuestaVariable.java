/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.math.BigDecimal;

/**
 *
 * @author santiago
 */
public class ParamRespuestaVariable {
    
    private int id;
    private String nombre;
    private String respuesta;
    private BigDecimal woe;
    private BigDecimal min;
    private BigDecimal max;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the woe
     */
    public BigDecimal getWoe() {
        return woe;
    }

    /**
     * @param woe the woe to set
     */
    public void setWoe(BigDecimal woe) {
        this.woe = woe;
    }

    /**
     * @return the min
     */
    public BigDecimal getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(BigDecimal min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public BigDecimal getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(BigDecimal max) {
        this.max = max;
    }
    
}
