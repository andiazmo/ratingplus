/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK1
 *Fecha del cambio : 26-06-2018
 --------------------------------------------------------------------------------
 */
package fachadas;

import entidades.Reconduccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class ReconduccionFacade extends AbstractFacade<Reconduccion> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReconduccionFacade() {
        super(Reconduccion.class);
    }
    
  
   // FIXPACK1 - inicio
   public Reconduccion ReconduccionXNombre(String nombre){
       Reconduccion reconduccion = null;
        Query query = em.createNamedQuery("Reconduccion.findByNombre");
        query.setParameter("nombre", nombre);
        if(!query.getResultList().isEmpty()){
            reconduccion = (Reconduccion) query.getResultList().get(0);
        }
        return reconduccion;       
   }
   // FIXPACK1 - fin 
   public Reconduccion ReconduccionXid(String id){
       Reconduccion reconduccion = null;
        Query query = em.createNamedQuery("Reconduccion.findById");
        query.setParameter("id", id);
        if(!query.getResultList().isEmpty()){
            reconduccion = (Reconduccion) query.getResultList().get(0);
        }
        return reconduccion;       
   } 
}
