/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.HistoricoRating;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 0000
 */
@Stateless
public class HistoricoRatingFacade extends AbstractFacade<HistoricoRating> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoRatingFacade() {
        super(HistoricoRating.class);
    }

    public void guardar(HistoricoRating historicorating) {
        em.persist(historicorating);
    }

    public HistoricoRating Ultimamodificacionrating(String nit) {
        HistoricoRating ultimamodificacion = null;
        try {
            Query query = em.createNamedQuery("HistoricoRating.findByNitfecha").setMaxResults(1);
            query.setParameter("nitcliente", nit);
            List<HistoricoRating> historicoultimamodificacion = query.getResultList();
            if (historicoultimamodificacion.size() > 0) {
                ultimamodificacion = historicoultimamodificacion.get(0);
            }
            
        } catch (Exception e) {
         ultimamodificacion=null;   
        }
        
     return ultimamodificacion;
    }
    
    
    public List<HistoricoRating> HistoricoRatingreporte(String nit) {
       List<HistoricoRating> historicorating=null;
        try {
            Query query = em.createNamedQuery("HistoricoRating.findByNit");
            query.setParameter("nitcliente", nit);
            historicorating = query.getResultList();
                       
        } catch (Exception e) {
         historicorating=null;   
        }
        
     return historicorating;
    }
    
    
    
}
