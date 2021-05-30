/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *Proyecto : Cupos Auditoria 2018
 *Programados: Juan Herrera
 *Tag de cambio: CupoAut2018
 *Fecha del cambio : 22-05-2018
 */
package fachadas;

import entidades.Clientes;
import entidades.Cupos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author x356167
 */
@Stateless
public class CuposFacade extends AbstractFacade<Cupos> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuposFacade() {
        super(Cupos.class);
    }

    public Cupos CuposXCliente(Clientes cliente) {
        List<Cupos> cupos = em.createNamedQuery("Cupos.findByCliente").setParameter("cliente", cliente).getResultList();
        if (cupos.size() > 0) {
            return cupos.get(0);
        } else {
            Cupos cupo = new Cupos();
            cupo.setCliente(cliente);
            // CupoAut2018 -Inicio
            // Comentario : se deja tomar el valor por defecto del Entitie
            //cupo.setEstado(true);
            // CupoAut2018 -Fin
            return cupo;
        }
    }

    public boolean guardar(Cupos cupo) {
        List<Cupos> cupos = em.createNamedQuery("Cupos.findByCliente").setParameter("cliente", cupo.getCliente()).getResultList();
        if (cupos.size() == 0) {
            em.persist(cupo);
            return true;
        }
        return false;
    }

    public void modificar(Cupos cupo) {
        em.merge(cupo);
    }

    public List<Cupos> cuposXCliente(Clientes cliente) {
        return em.createNamedQuery("Cupos.findByCliente").setParameter("cliente", cliente).getResultList();
    }

    public Cupos cupoXId(String id) {
        return em.find(Cupos.class, id);
    }

    //Grupos Economicos
    public void updateCupoByCliente(Clientes cliente, Boolean estado) {
        Query q = em.createNamedQuery("Cupos.updateByCliente");
        q.setParameter("cliente", cliente);
        q.setParameter("estado", estado);
        q.executeUpdate();
    }

}
