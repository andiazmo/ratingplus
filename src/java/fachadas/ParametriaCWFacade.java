/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;
 
import entidades.ParametriaCw;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author x302266
 */
@Stateless
public class ParametriaCWFacade extends AbstractFacade<ParametriaCw> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametriaCWFacade() {
        super(ParametriaCw.class);
    }
    
    public List<ParametriaCw> parametriaPorClave(String clave, String tabla){
        Query query = em.createNamedQuery("ParametriaCw.findByClave");
          query.setParameter("clave", clave);
          query.setParameter("tabla", tabla);
          
        List<ParametriaCw> parametria = query.getResultList();
        
        return parametria;
    }

}
