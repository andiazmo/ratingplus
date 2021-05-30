/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.EcuacionMx;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author x236216
 */
@Stateless
public class EcuacionMxFacade extends AbstractFacade<EcuacionMx> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    public EcuacionMxFacade() {
        super(EcuacionMx.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void guardar(EcuacionMx ecuacionMx) throws Exception {
        try{
        em.persist(ecuacionMx);
        }catch (Exception ex){
            System.out.println("Se ha producido un error al modificar " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public void modificar(EcuacionMx ecuacionMx) {
        em.merge(ecuacionMx);
    }

    public EcuacionMx findEcuacion(String nombre) {
        try {
            Query q = em.createNamedQuery("EcuacionMx.findByNombre");
            q.setParameter("nombre", nombre);
            return (EcuacionMx) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

}
