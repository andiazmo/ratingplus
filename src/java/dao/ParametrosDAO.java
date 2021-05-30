/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Parametros;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class ParametrosDAO extends GenericDAOJPA<Parametros>{

    public ParametrosDAO() {
        super(Parametros.class);
        em = factory.createEntityManager();
    }
    
    public String buscarXClave(String llave){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Parametros.findByClave");
        query.setParameter("clave", llave);
        List<Parametros> list = (List<Parametros>) query.getResultList();
        if(list.size()>0){
            return list.get(0).getValor();
        }
        return "";
    }
    
    
    
}
