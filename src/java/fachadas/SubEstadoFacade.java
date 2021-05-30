/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Estados;
import entidades.SubEstado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class SubEstadoFacade extends AbstractFacade<SubEstado> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubEstadoFacade() {
        super(SubEstado.class);
    }

    public List<SubEstado> buscar(Estados estado) {
        Query query = em.createNamedQuery("SubEstado.findByEstado");
        query.setParameter("id_estado", estado);
        return query.getResultList();
    }
    
}
