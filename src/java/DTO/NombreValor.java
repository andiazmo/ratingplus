/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras a Reportes Cupos Web
 *Programador: Salomé Gené
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 27-08-2018
 --------------------------------------------------------------------------------
 */
package DTO;

/**
 * DTO Generico utilizado para obtener el nombre y el valor de un objeto.
 *
 * @author Salomé Gené
 * @since 27/08/2018
 */
public class NombreValor {

    private String nombre;
    private int valor;

    public NombreValor() {
    }

    public NombreValor(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.valor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NombreValor other = (NombreValor) obj;
        return this.valor == other.valor;
    }

}
