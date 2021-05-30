/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.ValoresActCupoMx;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author x217287
 */
@Stateless
public class ValoresActuaCupoMxFacade  extends AbstractFacade<ValoresActCupoMx>{
      @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    public ValoresActuaCupoMxFacade() {
        super(ValoresActCupoMx.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
        public ValoresActCupoMx valoresActuacupocliente(String cliente, Date fecha, Date fechaDiaAnterior) {
        try {
            Query q = em.createNamedQuery("ValoresActCupoMx.findByClienteFecha");
            q.setParameter("cliente", cliente);
            q.setParameter("fechaCreacion", fecha);
            q.setParameter("fechaCreacionA", fechaDiaAnterior);
            return (ValoresActCupoMx) q.getResultList().get(0);
            
        } catch (Exception e) {
            return null;
        }
    }
         
}
