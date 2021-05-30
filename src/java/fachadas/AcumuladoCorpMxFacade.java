/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.AcumuladoCorpMx;
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
public class AcumuladoCorpMxFacade extends AbstractFacade<AcumuladoCorpMx> {
     @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public  AcumuladoCorpMxFacade(){
    super(AcumuladoCorpMx.class);
    }

    
     public AcumuladoCorpMx acumuladocorpByCliente(String cliente, Date fecha, Date fechaDiaAnterior) {
        try {
            Query q = em.createNamedQuery("AcumuladoCorpMx.findByCliente");
            q.setParameter("cliente", cliente);
            q.setParameter("fechaCreacion", fecha);
            q.setParameter("fechaCreacionA", fechaDiaAnterior);
            AcumuladoCorpMx entities = (AcumuladoCorpMx) q.getSingleResult();
            return entities;
        } catch (Exception e) {
            return null;
        }
    }
}
