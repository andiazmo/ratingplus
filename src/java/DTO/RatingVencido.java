/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras a Reportes Cupos Web
 *Programador: Salomé Gené
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 24-08-2018
 --------------------------------------------------------------------------------
 */

package DTO;

import java.util.Date;

/**
 * DTO utilizado para generar el reporte de los clientes con raiting vencidos o
 * por vencer.
 *
 * @author Salomé Gené
 * @since 24/08/2018
 */
public class RatingVencido {

    private String nit;
    private String cliente;
    private Double rating;
    private Date fechaRating;
    private Double activo;
    private String diasRestantes;

    public RatingVencido() {
    }

    public RatingVencido(String nit, String cliente, Double rating,
            Date fechaRating, Double activo, String diasRestantes) {
        this.nit = nit;
        this.cliente = cliente;
        this.rating = rating;
        this.fechaRating = fechaRating;
        this.activo = activo;
        this.diasRestantes = diasRestantes;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getFechaRating() {
        return fechaRating;
    }

    public void setFechaRating(Date fechaRating) {
        this.fechaRating = fechaRating;
    }

    public Double getActivo() {
        return activo;
    }

    public void setActivo(Double activo) {
        this.activo = activo;
    }

    public String getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(String diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

}
