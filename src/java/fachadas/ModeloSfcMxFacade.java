/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;


import entidades.ModeloSfcMx;
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
public class ModeloSfcMxFacade extends AbstractFacade<ModeloSfcMx>{
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public  ModeloSfcMxFacade(){
         super(ModeloSfcMx.class);
    }
    
      
      public ModeloSfcMx neteoacumuladoModeloSfcByCliente(String cliente, Date fecha, Date fechaDiaAnterior) {
        try {
            Query q = em.createNamedQuery("ModeloSfcMx.findByCliente");
            q.setParameter("cliente", cliente);
            q.setParameter("fechaCreacion", fecha);
            q.setParameter("fechaCreacionA", fechaDiaAnterior);
            ModeloSfcMx entities = (ModeloSfcMx) q.getSingleResult();
            return entities;
        } catch (Exception e) {
            return null;
        }
    }
      
      
      }
