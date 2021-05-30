/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.IntentoFallido;
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
public class IntentoFallidoFacade  extends AbstractFacade<IntentoFallido> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IntentoFallidoFacade() {
        super(IntentoFallido.class);
    }

    @Override
    public void remove(IntentoFallido entity) {}

    public int validarIntentos(RpUsuarios usuario, Date date) {
        List<IntentoFallido> IntentoFallidos = buscarIntentosXUsuarioXdia(usuario, date);
        return IntentoFallidos.size();
    }

    public void crear(RpUsuarios usuario) {
        IntentoFallido intentoFallido = new IntentoFallido(new Date(), usuario);
        em.persist(intentoFallido);
    }

    public void borrarIntentos(RpUsuarios usuario, Date date) {
        List<IntentoFallido> IntentoFallidos = buscarIntentosXUsuarioXdia(usuario, date);

        for(IntentoFallido intentoFallido: IntentoFallidos){
            em.createNativeQuery("delete from intento_fallido where id = '" + intentoFallido.getId() + "'").executeUpdate();
        }
    }
    
    private List<IntentoFallido> buscarIntentosXUsuarioXdia(RpUsuarios usuario, Date date){
        Query query = em.createNamedQuery("IntentoFallido.findByFechaByUsuario").setMaxResults(5);
        query.setParameter("fecha", date);
        query.setParameter("usuario2", usuario);
        List<IntentoFallido> result = query.getResultList();
        return result;
    }
}
