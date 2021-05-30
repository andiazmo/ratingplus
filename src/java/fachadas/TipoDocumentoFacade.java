/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;
import entidades.TipoDocumento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author 0000
 */
@Stateless
public class TipoDocumentoFacade extends AbstractFacade<TipoDocumento> {

@PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumentoFacade() {
        super(TipoDocumento.class);
    }
  
    
    public TipoDocumento buscar(String Codigo) {
        Query query = em.createNamedQuery("TipoDocumento.findByCodigo");
        query.setParameter("codigo", Codigo);
        return (TipoDocumento) query.getSingleResult();
    }

  
}
