/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Desembolsos;
import entidades.Pagos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author x356167
 */
@Stateless
public class PagosFacade extends AbstractFacade<Pagos> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagosFacade() {
        super(Pagos.class);
    }

    public void guardar(Pagos pago) {
        em.persist(pago);
    }

    public void modificar(Pagos pago) {
        em.merge(pago);
    }

    public void borrar(Pagos pago) {
        em.createNativeQuery("delete from pagos where id = '" + pago.getId() + "'").executeUpdate();
    }

    public List<Pagos> pagosXLimite(Desembolsos limite) {
        return em.createNamedQuery("Pagos.findByDesembolso").setParameter("desembolso", limite).getResultList();

    }

}
