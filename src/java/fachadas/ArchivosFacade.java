/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Archivos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author GCOCOL0281
 */
@Stateless
public class ArchivosFacade extends AbstractFacade<Archivos> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArchivosFacade() {
        super(Archivos.class);
    }
    
    public void guardar(Archivos archivo){
        em.persist(archivo);
    }
    
    public void modificar(Archivos archivo){
        em.merge(archivo);
    }
    
    public List<Archivos> noProcesados(){
           return em.createNamedQuery("Archivos.findByProcesado").setParameter("procesado", false).getResultList();
    }
    
    public boolean existeResgistro(String nombre){
        Query query = em.createNamedQuery("Archivos.findByNombre");
        query.setParameter("nombre", nombre);
        return ( query.getResultList().size() > 0 );
    }
    
}
