/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Clientes;
import entidades.EstadosReconduccion;
import entidades.Reconduccion;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class EstadosReconduccionFacade extends AbstractFacade<EstadosReconduccion> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosReconduccionFacade() {
        super(EstadosReconduccion.class);
    }
    
    public void guardar(EstadosReconduccion estadosCliente){
        em.persist(estadosCliente);
    }
    
    public void modificar(EstadosReconduccion estadosCliente){
        em.merge(estadosCliente);
    }
    
    public List<EstadosReconduccion> loadEstado(Clientes clientes) {
        return em.createNamedQuery("EstadosReconduccion.findByIdCliente")
                .setParameter("idCliente",clientes).getResultList();
    }

    // String id, String descripcion, Date fecha, Clientes idCliente, Reconduccion idReconduccion/|
    public void guardar(Date newDate, Reconduccion reconduccion, String descripcion, Clientes cliente) {
        String id = UUID.randomUUID().toString();
        EstadosReconduccion estadosReconduccion = 
                new EstadosReconduccion(id, descripcion, newDate, cliente, reconduccion);
        guardar(estadosReconduccion);
    }
    
    public Reconduccion ReconduccionXid(String id){
       Reconduccion reconduccion = null;
        Query query = em.createNamedQuery("Reconduccion.findById");
        query.setParameter("id", id);
        if(!query.getResultList().isEmpty()){
            reconduccion = (Reconduccion) query.getResultList().get(0);
        }
        return reconduccion;       
   }
}
