/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Anyelo
 */
public class Variable {
    
    private int id;
    private String nombre;
    private String valorStr;
    private double valorNum;
    private int idModulo;

    public int getId() {
        return id;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
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

    public String getValorStr() {
        return valorStr;
    }

    public void setValorStr(String valorStr) {
        this.valorStr = valorStr;
    }

    public double getValorNum() {
        return valorNum;
    }

    public void setValorNum(double valorNum) {
        this.valorNum = valorNum;
    }
    
}
