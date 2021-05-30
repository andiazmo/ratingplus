/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the t

 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 03-08-2018
 --------------------------------------------------------------------------------
 */
package fachadas;

import entidades.Procesos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author GCOCOL0281
 */
@Stateless
public class ProcesosFacade extends AbstractFacade<Procesos> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesosFacade() {
        super(Procesos.class);
    }
    
    public void guardar(Procesos proceso){
        em.persist(proceso);
    }
    
    public void modificar(Procesos proceso){
        em.merge(proceso);
    }
    
    public void borrar(Procesos proceso){
        em.createNativeQuery("delete from procesos where identificador = '" + proceso.getIdentificador() + "'").executeUpdate();
    }
    
    public Procesos buscarId(Integer id){
        return (Procesos) em.createNamedQuery("Procesos.findByIdentificador").setParameter("identificador", id).getResultList().get(0);
    }
    
    // FIXPACK2 - inicio
    public List<Procesos> buscarTodosOrdenados() {
        return em.createNamedQuery("Procesos.findAll").getResultList();
    }
    // FIXPACK2 - fin   
}
