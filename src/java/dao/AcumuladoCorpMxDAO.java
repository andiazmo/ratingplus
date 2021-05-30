/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.AcumuladoCorpMx;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author x217287
 */
public class AcumuladoCorpMxDAO extends GenericDAOJPA<AcumuladoCorpMx>{
    
    public AcumuladoCorpMxDAO(){
    super(AcumuladoCorpMx.class);
        em = factory.createEntityManager();
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
