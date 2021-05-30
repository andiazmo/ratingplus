/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.BancaCorporativa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author x332015
 */
@Stateless
public class BancaCorporativaFacade extends AbstractFacade<BancaCorporativa> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BancaCorporativaFacade() {
        super(BancaCorporativa.class);
    }

}
