/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;


import entidades.EstadoScan;
import entidades.SubEstadoScan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class SubEstadoScanFacade extends AbstractFacade<SubEstadoScan> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubEstadoScanFacade() {
        super(SubEstadoScan.class);
    }

    public List<SubEstadoScan> buscar(EstadoScan estadoscan) {
        Query query = em.createNamedQuery("SubEstadoScan.findByEstado");
        query.setParameter("estado", estadoscan);
        return query.getResultList();
    }
    
}
