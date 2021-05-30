/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author santiago
 */
public class BetaModuloCasoUso {
    
    private int codigo;
    private BetaModuloCategoria categoria;
    private int numCaso;
    private double valor;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the categoria
     */
    public BetaModuloCategoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(BetaModuloCategoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the numCaso
     */
    public int getNumCaso() {
        return numCaso;
    }

    /**
     * @param numCaso the numCaso to set
     */
    public void setNumCaso(int numCaso) {
        this.numCaso = numCaso;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
