/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Map;

/**
 *
 * @author Anyelo
 */
public class VariablesRating {
    
    private int id;
    private String nombre;
    private int idModulo;
    private int idVariable;
    private double beta;
    private String value;
    private Map<String, String> mapRespuesta;
    private String respuesta;
    private String woe;
    private String min;
    private String max;
    private String nombreModulo;

    public VariablesRating() {
    }

    public VariablesRating(String nombre, int idModulo, int idVariable, double beta, String value) {
        this.nombre = nombre;
        this.idModulo = idModulo;
        this.idVariable = idVariable;
        this.beta = beta;
        this.value = value;
    }

    
    public VariablesRating(String nombre, int idModulo, String value) {
        this.nombre = nombre;
        this.idModulo = idModulo;
        this.value = value;
    }

    public VariablesRating(String nombre, int idModulo, int idVariable, double beta) {
        this.nombre = nombre;
        this.idModulo = idModulo;
        this.idVariable = idVariable;
        this.beta = beta;
    }

    public VariablesRating(String nombre, int idModulo) {
        this.nombre = nombre;
        this.idModulo = idModulo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(int idVariable) {
        this.idVariable = idVariable;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public Map<String, String> getMapRespuesta() {
        return mapRespuesta;
    }

    public void setMapRespuesta(Map<String, String> mapRespuesta) {
        this.mapRespuesta = mapRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }
    
    public String getWoe() {
        return woe;
    }

    public void setWoe(String woe) {
        this.woe = woe;
    }
    
     public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

}
