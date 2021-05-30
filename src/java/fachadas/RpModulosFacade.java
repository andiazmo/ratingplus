/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.RpModulos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author siervo
 */
@Stateless
public class RpModulosFacade extends AbstractFacade<RpModulos> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RpModulosFacade() {
        super(RpModulos.class);
    }

    public void refrescar(RpModulos rp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
