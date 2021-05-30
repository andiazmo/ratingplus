/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author User
 * @param <T>
 */
public class GenericDAOJPA<T> {
    
    private final Class aClass;
    private final String PERSISTENCE_UNIT_NAME = "cuposPU";
    protected final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    protected EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public GenericDAOJPA(Class aClass) {
        this.aClass = aClass;
    }
    
    public void createEntity(T entity) {
        try{
            em.persist(entity);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public List<T> findAllEntity() {
        String strQuery = "Select t from " + aClass.getName() + " t";
        Query q = em.createQuery(strQuery);
        List<T> entities = q.getResultList();
        return entities;
    }
    
    public T findEntity(String id){
        T entity = (T) em.find(aClass, id);
        return entity;
    }
    
    public void editEntity(T entity){
        try{
            em.merge(entity);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void removeEntity(T entity){
        try{
            EntityManager em = factory.createEntityManager();
            em.remove(entity);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
