/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.HistoricoEstadoScan;
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
public class HistoricoEstadoScanFacade extends AbstractFacade<HistoricoEstadoScan> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoEstadoScanFacade() {
        super(HistoricoEstadoScan.class);
    }

    public void guardar(HistoricoEstadoScan historicoestadoscan) {
        em.persist(historicoestadoscan);
    }

    
    public HistoricoEstadoScan Ultimamodificacionestadoscan(String nit) {
        HistoricoEstadoScan ultimamodificacion = null;
        try {
            Query query = em.createNamedQuery("HistoricoEstadoScan.findByNitfecha").setMaxResults(1);
            query.setParameter("nitcliente", nit);
            List<HistoricoEstadoScan> historicoultimamodificacion = query.getResultList();
            if (historicoultimamodificacion.size() > 0) {
                ultimamodificacion = historicoultimamodificacion.get(0);
            }
            
        } catch (Exception e) {
         ultimamodificacion=null;   
        }
        
     return ultimamodificacion;
    }
    
    
    public List<HistoricoEstadoScan> historicoscan(String nit){
        return em.createNamedQuery("HistoricoEstadoScan.findByNit").setParameter("nitcliente", nit).getResultList();
    }
       
   
}
