/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.NeteoCorpMx;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author x217287
 */
public class NeteoCorpMxDAO extends GenericDAOJPA<NeteoCorpMx>{
    
    public NeteoCorpMxDAO(){
        super(NeteoCorpMxDAO.class);
        em=factory.createEntityManager();
    }
    
    
       public NeteoCorpMx neteocorpByCliente(String cliente, Date fecha, Date fechaDiaAnterior) {
        try {
            Query q = em.createNamedQuery("NeteoCorpMx.findByCliente");
            q.setParameter("cliente", cliente);
            q.setParameter("fechaCreacion", fecha);
            q.setParameter("fechaCreacionA", fechaDiaAnterior);
            NeteoCorpMx entities = (NeteoCorpMx) q.getSingleResult();
            return entities;
        } catch (Exception e) {
            return null;
        }
    }
}
