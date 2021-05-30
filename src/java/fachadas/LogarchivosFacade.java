/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;


import entidades.Logarchivos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author GCOCOL0281
 */
@Stateless
public class LogarchivosFacade extends AbstractFacade<Logarchivos> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogarchivosFacade() {
        super(Logarchivos.class);
    }
    
}
