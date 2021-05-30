/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.HistoricoClave;
import entidades.RpUsuarios;
import java.util.Calendar;
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
public class HistoricoClaveFacade extends AbstractFacade<HistoricoClave> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoClaveFacade() {
        super(HistoricoClave.class);
    }

    @Override
    public void remove(HistoricoClave entity) {
    }
    
    public void modificar(HistoricoClave entity){
        em.merge(entity);
    }

    public List<HistoricoClave> buscarUltimos5(RpUsuarios rpUsuarios) {
        Query query = em.createNamedQuery("HistoricoClave.findByRpUsuario").setMaxResults(5);
        query.setParameter("usuario2", rpUsuarios);
        List<HistoricoClave> result = query.getResultList();
        return result;
    }

    public List<HistoricoClave> buscarActiva(RpUsuarios usuario) {
        Query query = em.createNamedQuery("HistoricoClave.findByActivaFindByUsuario");
        query.setParameter("activa", true);
        query.setParameter("usuario2", usuario);
        List<HistoricoClave> historicoClaves = query.getResultList();
        return historicoClaves;
    }

    private void DesactivarHistoricoClave(RpUsuarios usuario) {
        List<HistoricoClave> historicoClaves = buscarActiva(usuario);
        for (HistoricoClave historicoClave : historicoClaves) {
            historicoClave.setActiva(false);
        }
    }

    public void crear(RpUsuarios usuario) {
        DesactivarHistoricoClave(usuario); // Se desactiva la clave vigente actual.

        Calendar calendar = Calendar.getInstance();
        Date fechaCreada = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date fechaCaducidad = calendar.getTime();

        HistoricoClave historicoClave = new HistoricoClave(usuario.getClave(),
                fechaCreada, fechaCaducidad, true, usuario);

        em.persist(historicoClave);
    }
    
    public void creaClaveReseto(RpUsuarios usuario) {
        DesactivarHistoricoClave(usuario); // Se desactiva la clave vigente actual.
        
        Calendar calendar = Calendar.getInstance();
        Date fechaCreada = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date fechaCaducidad = calendar.getTime();
        
        HistoricoClave historicoClave = new HistoricoClave(usuario.getClave(),
                fechaCreada, fechaCaducidad, false, usuario);
        
        em.persist(historicoClave);
    }

}
