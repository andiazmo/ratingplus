/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.RpRoles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author siervo
 */
@Stateless
public class RpRolesFacade extends AbstractFacade<RpRoles> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RpRolesFacade() {
        super(RpRoles.class);
    }
    
   
    
    
}
