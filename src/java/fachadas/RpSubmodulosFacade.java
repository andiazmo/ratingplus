/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.RpSubmodulos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author siervo
 */
@Stateless
public class RpSubmodulosFacade extends AbstractFacade<RpSubmodulos> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RpSubmodulosFacade() {
        super(RpSubmodulos.class);
    }
    
    public List<RpSubmodulos> submodulosPorPerfilModulo(String rol , String modulo){
        String query = "select  m.* from rp_submodulos m join rp_permisos p on p.modulo = m.codigo where  p.rol = '" + rol +"' and m.modulo = '" +  modulo +"'" ;
        System.out.println(query);
        return (List<RpSubmodulos>)em.createNativeQuery(query).getResultList();
    }

    
}
