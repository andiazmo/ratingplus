/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Gestores;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Libardo Rondon
 */
@Stateless
public class GestoresFacade extends AbstractFacade<Gestores> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GestoresFacade() {
        super(Gestores.class);
    }
    
    public void guardar(Gestores gestor){
        em.persist(gestor);
    }
    
     public void modificar(Gestores gestor){
        em.merge(gestor);
    }
    
     public void borrar(Gestores gestor){
         em.createNativeQuery("delete from gestores where cedula = '" + gestor.getCedula()  +"'").executeUpdate();
     }
     
     public List<Gestores> gestorByCedula(String cedula){
         Query q = em.createNamedQuery("Gestores.findByCedula");
         q.setParameter("cedula", cedula);
         
         List<Gestores> g = q.getResultList();
         
         return g;
     }
}
