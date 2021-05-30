/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Clientes;
import entidades.EstadoSubstandar;
import entidades.EstadosSubstandar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author x332015
 */
@Stateless
public class EstadosSubstandarFacade extends AbstractFacade<EstadosSubstandar> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosSubstandarFacade() {
        super(EstadosSubstandar.class);
    }

    public void guardar(EstadosSubstandar estadosSubstandar) {
        em.persist(estadosSubstandar);
    }

    public void modificar(EstadosSubstandar estadosSubstandar) {
        em.merge(estadosSubstandar);
    }

    public List<EstadosSubstandar> loadEstado(Clientes clientes) {
        return em.createNamedQuery("EstadosSubstandar.findByIdCliente")
                .setParameter("idCliente", clientes).getResultList();
    }

    public void guardar(Date newDate, EstadoSubstandar estado, String descripcion, Clientes cliente) {
        String id = UUID.randomUUID().toString();
        EstadosSubstandar estadosSubStandar = new EstadosSubstandar(id, descripcion, newDate, cliente, estado);
        guardar(estadosSubStandar);
    }

}
