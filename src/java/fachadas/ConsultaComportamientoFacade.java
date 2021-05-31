/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.GruposClientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author x356167
 */
@Stateless
public class ConsultaComportamientoFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaComportamientoFacade() {
        super(GruposClientes.class);
    }
     
    public boolean registrarComportamiento(String respCalificacion,
            String respGarantia, String indMora, String numeroBancos, 
            String marcacionReestructuracion, String id_cliente, String usuario){
    
        Query q = em.createNativeQuery("INSERT INTO ri.modulo_comportamiento(id,"
                + "calificacion, garantia, indicador_mora, numero_bancos, "
                + "marcacion_reestructuracion, id_cliente, usuario, "
                + "fecha_insercion) VALUES ((SELECT MAX(id)"
                + " from ri.modulo_comportamiento)+ 1, ?, ?, ?, ?, ?, ?, ?, "
                + "(SELECT now()::timestamp))");
   
        return q.setParameter(1, respCalificacion).setParameter(2, respGarantia).
                setParameter(3, indMora).setParameter(4, numeroBancos).
                setParameter(5, marcacionReestructuracion).
                setParameter(6,id_cliente).
                setParameter(7, usuario).executeUpdate() != 0;
    } 
}