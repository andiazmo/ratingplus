/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.HistoricoEstadosclientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author x217287
 */

@Stateless
public class HistoricoEstadosClienteFacade extends AbstractFacade<HistoricoEstadosclientes> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

     @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     public HistoricoEstadosClienteFacade() {
        super(HistoricoEstadosclientes.class);
    }

    
    public void guardar(HistoricoEstadosclientes historicoestadosclientes) {
        em.persist(historicoestadosclientes);
    }

}
