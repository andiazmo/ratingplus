/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author x217287
 */
public class ReporteCuposDerivados {
    private String nit;
    private String cliente;
    private Double usocupo;
    private String nivel;
    private Double cupototal;
    private Double cupoconsumido;
    private Double cupodisponible;
    private String plazo;
    private Date fechavencimiento;
    private String bandamaxima;
    private String diasparavencimiento;
    private String estado;

    public void ReporteCuposDerivados() {
    }
    
     public void ReporteCuposDerivados(String nit,String cliente,Double usocupo,String nivel,
             Double cupototal,Double cupoconsumido,  Double cupodisponible,String plazo,
             Date fechavencimiento,String bandamaxima,String diasparavencimiento, String estado) {
         
         this.nit=nit;
         this.cliente=cliente;
         this.usocupo=usocupo;
         this.nivel=nivel;
         this.cupototal=cupototal;
         this.cupoconsumido=cupoconsumido;
         this.cupodisponible=cupodisponible;
         this.plazo=plazo;
         this.fechavencimiento=fechavencimiento;
         this.bandamaxima=bandamaxima;
         this.diasparavencimiento=diasparavencimiento;
         this.estado=estado;
         
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getUsocupo() {
        return usocupo;
    }

    public void setUsocupo(Double usocupo) {
        this.usocupo = usocupo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Double getCupototal() {
        return cupototal;
    }

    public void setCupototal(Double cupototal) {
        this.cupototal = cupototal;
    }

    public Double getCupoconsumido() {
        return cupoconsumido;
    }

    public void setCupoconsumido(Double cupoconsumido) {
        this.cupoconsumido = cupoconsumido;
    }

    public Double getCupodisponible() {
        return cupodisponible;
    }

    public void setCupodisponible(Double cupodisponible) {
        this.cupodisponible = cupodisponible;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public String getBandamaxima() {
        return bandamaxima;
    }

    public void setBandamaxima(String bandamaxima) {
        this.bandamaxima = bandamaxima;
    }

    public String getDiasparavencimiento() {
        return diasparavencimiento;
    }

    public void setDiasparavencimiento(String diasparavencimiento) {
        this.diasparavencimiento = diasparavencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
     
     
}
