/*
--------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de creación: FIXPACK2
 *Fecha de creación : 10-08-2018
 --------------------------------------------------------------------------------
 */
package fachadas;

import entidades.Parametros;
import entidades.VersionesCw;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Wittman Gutiérrez
 */
@Stateless
public class VersionesCwFacade  extends AbstractFacade<VersionesCw> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    public VersionesCwFacade() {
        super(VersionesCw.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public VersionesCw buscarXVersion(int numVersion, int numSubversion){
        Query query = em.createNamedQuery("VersionesCw.findByNumVersionSubversion");
        query.setParameter("numVersion", numVersion);
        query.setParameter("numSubversion", numSubversion);
        List<VersionesCw> list = (List<VersionesCw>) query.getResultList();
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }    
}
