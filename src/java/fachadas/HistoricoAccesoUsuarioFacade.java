/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.HistoricoAccesoUsuario;
import entidades.RpUsuarios;
import java.util.Date;
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
public class HistoricoAccesoUsuarioFacade extends AbstractFacade<HistoricoAccesoUsuario> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoAccesoUsuarioFacade() {
        super(HistoricoAccesoUsuario.class);
    }

    @Override
    public void remove(HistoricoAccesoUsuario entity) {
    }

    public List<HistoricoAccesoUsuario> buscarUltimoAcceso(RpUsuarios rpUsuarios) {
        Query query = em.createNamedQuery("HistoricoAccesoUsuario.findByUsuarioandfecha").setMaxResults(2);
        query.setParameter("usuario", rpUsuarios);
        List<HistoricoAccesoUsuario> result = query.getResultList();
        return result;
    }


    public void crear(RpUsuarios usuario) {
        HistoricoAccesoUsuario historicoAccesoUsuario = new HistoricoAccesoUsuario(new Date(), usuario);
        em.persist(historicoAccesoUsuario);
    }
}
