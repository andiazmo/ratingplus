/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author x356167
 */
public class Patrimonio {
    private String cliente;
    private Double cupo;
    private Double diferencia;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getCupo() {
        return cupo;
    }

    public void setCupo(Double cupo) {
        this.cupo = cupo;
    }

    public Double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Double diferencia) {
        this.diferencia = diferencia;
    }

    

    
    
    
    public Patrimonio( String cliente, Double cupo, Double diferencia) {
        this.cliente = cliente;
        this.cupo = cupo;
        this.diferencia = diferencia;
   
    }
    
    
}
