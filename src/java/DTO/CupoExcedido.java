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
public class CupoExcedido {
    private String cliente;
    private Double cupo;
    private Double diferencia;
    private String nit;
    private Double consumido;
    private String linea;
    private Double limite;
    private Double utilizado;
    private Double diferencialimite;

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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Double getConsumido() {
        return consumido;
    }

    public void setConsumido(Double consumido) {
        this.consumido = consumido;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Double utilizado) {
        this.utilizado = utilizado;
    }

    public Double getDiferencialimite() {
        return diferencialimite;
    }

    public void setDiferencialimite(Double diferencialimite) {
        this.diferencialimite = diferencialimite;
    }

    public CupoExcedido(String cliente, Double cupo, Double diferencia, String nit, Double consumido, String linea, Double limite, Double utilizado, Double diferencialimite) {
        this.cliente = cliente;
        this.cupo = cupo;
        this.diferencia = diferencia;
        this.nit = nit;
        this.consumido = consumido;
        this.linea = linea;
        this.limite = limite;
        this.utilizado = utilizado;
        this.diferencialimite = diferencialimite;
    }

  

}
