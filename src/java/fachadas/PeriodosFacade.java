/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.Periodos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author x356167
 */
@Stateless
public class PeriodosFacade extends AbstractFacade<Periodos> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodosFacade() {
        super(Periodos.class);
    }
    
}
