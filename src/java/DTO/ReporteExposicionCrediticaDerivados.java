/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author x217287
 */
public class ReporteExposicionCrediticaDerivados {
    
    private String nit;
    private String cliente;
    private String tiposfc;
    private Double maxcontrolrisk;
    private Double consumosfc;
    private Double sfcacumulado;
    private Double sfcneteo;
    private Double consumocorp;
    private Double corpacumulado;
    private Double corpneteo;
    private String maxaddon;
    
    
    public void ReporteExposicionCrediticaDerivados(){
    }
    
    
    public void ReporteExposicionCrediticaDerivados(String nit,String cliente,String tiposfc,Double maxcontrolrisk,Double consumosfc,Double sfcacumulado,Double sfcneteo,
            Double consumocorp,Double corpacumulado,Double corpneteo,String maxaddon){
        
        this.nit=nit;
        this.cliente=cliente;
        this.tiposfc=tiposfc;
        this.maxcontrolrisk=maxcontrolrisk;
        this.consumosfc=consumosfc;
        this.sfcacumulado=sfcacumulado;
        this.sfcneteo=sfcneteo;
        this.consumocorp=consumocorp;
        this.corpacumulado=corpacumulado;
        this.corpneteo=corpneteo;
        this.maxaddon=maxaddon;
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

    public String getTiposfc() {
        return tiposfc;
    }

    public void setTiposfc(String tiposfc) {
        this.tiposfc = tiposfc;
    }

    public Double getMaxcontrolrisk() {
        return maxcontrolrisk;
    }

    public void setMaxcontrolrisk(Double maxcontrolrisk) {
        this.maxcontrolrisk = maxcontrolrisk;
    }

    public Double getConsumosfc() {
        return consumosfc;
    }

    public void setConsumosfc(Double consumosfc) {
        this.consumosfc = consumosfc;
    }

    public Double getSfcacumulado() {
        return sfcacumulado;
    }

    public void setSfcacumulado(Double sfcacumulado) {
        this.sfcacumulado = sfcacumulado;
    }

    public Double getSfcneteo() {
        return sfcneteo;
    }

    public void setSfcneteo(Double sfcneteo) {
        this.sfcneteo = sfcneteo;
    }

    public Double getConsumocorp() {
        return consumocorp;
    }

    public void setConsumocorp(Double consumocorp) {
        this.consumocorp = consumocorp;
    }

    public Double getCorpacumulado() {
        return corpacumulado;
    }

    public void setCorpacumulado(Double corpacumulado) {
        this.corpacumulado = corpacumulado;
    }

    public Double getCorpneteo() {
        return corpneteo;
    }

    public void setCorpneteo(Double corpneteo) {
        this.corpneteo = corpneteo;
    }

    public String getMaxaddon() {
        return maxaddon;
    }

    public void setMaxaddon(String maxaddon) {
        this.maxaddon = maxaddon;
    }
             
}
