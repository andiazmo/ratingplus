/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.Desembolsos;
import entidades.Limitesautorizados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author x356167
 */
@Stateless
public class DesembolsosFacade extends AbstractFacade<Desembolsos> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DesembolsosFacade() {
        super(Desembolsos.class);
    }
    public List<Desembolsos> desembolsosXLimite(Limitesautorizados limite){
          return em.createNamedQuery("Desembolsos.findByLimite").setParameter("limite",limite).getResultList();
          
   }
   
    public void guardar(Desembolsos desembolso){
        em.persist(desembolso);
    }
    
    public void modificar(Desembolsos desembolso){
        em.merge(desembolso);
    }

    public void borrar(Desembolsos desembolso){
        em.createNativeQuery("delete from desembolsos where id ='" + desembolso.getId() + "'").executeUpdate();
    }
    
    public Desembolsos buscaDesembolso(String desembolso){
        return em.find(Desembolsos.class, desembolso);
    }   
    
}
