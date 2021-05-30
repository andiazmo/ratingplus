/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.ModeloSfcMx;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author x217287
 */
public class ModeloSfcMxDAO extends GenericDAOJPA<ModeloSfcMx>{
  
    
    public ModeloSfcMxDAO(){
     super(ModeloSfcMx.class);
        em = factory.createEntityManager();
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
