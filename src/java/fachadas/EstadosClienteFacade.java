/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Clientes;
import entidades.Estados;
import entidades.EstadosCliente;
import entidades.SubEstado;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author User
 */
@Stateless
public class EstadosClienteFacade extends AbstractFacade<EstadosCliente> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosClienteFacade() {
        super(EstadosCliente.class);
    }
    
    public void guardar(EstadosCliente estadosCliente){
        em.persist(estadosCliente);
    }
    
    public void modificar(EstadosCliente estadosCliente){
        em.merge(estadosCliente);
    }
    
    public List<EstadosCliente> loadEstado(Clientes clientes) {
        return em.createNamedQuery("EstadosCliente.findByIdCliente")
                .setParameter("idCliente",clientes).getResultList();
    }

    public void guardar(Date newDate, Estados estado, String descripcion, Clientes cliente, SubEstado subEstado) {
        String id = UUID.randomUUID().toString();
        cliente.setSubestado(subEstado);
        EstadosCliente estadosCliente = new EstadosCliente(id, descripcion, 
                newDate, cliente, estado, subEstado);
        guardar(estadosCliente);
    }
    
}