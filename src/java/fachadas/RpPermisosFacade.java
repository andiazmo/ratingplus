/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.RpPermisos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author siervo
 */
@Stateless
public class RpPermisosFacade extends AbstractFacade<RpPermisos> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RpPermisosFacade() {
        super(RpPermisos.class);
    }
    
     public int  guardar(RpPermisos permiso){
        if(em.createNamedQuery("RpPermisos.findByRolModulo").setParameter("rol", permiso.getRol().getCodigo()).setParameter("modulo", permiso.getModulo().getCodigo()).getResultList().size()==0){    
           em.persist(permiso);
           return 1;
        }   
        return 0;
    }
    
     public int  modificar(RpPermisos permiso){
        if(em.createNamedQuery("RpPermisos.findByRolModulo").setParameter("rol", permiso.getRol().getCodigo()).setParameter("modulo", permiso.getModulo().getCodigo()).getResultList().size()<=1){    
           em.merge(permiso);
           return 1;
        }   
        return 0;
    } 
     
     
    
}
