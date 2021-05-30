/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.GruposEconomicos;
import java.util.ArrayList;
import java.util.Iterator;
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
public class GruposEconomicosFacade extends AbstractFacade<GruposEconomicos> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GruposEconomicosFacade() {
        super(GruposEconomicos.class);
    }

    public void guardar(GruposEconomicos grupo) {
        em.persist(grupo);
    }

    public void modificar(GruposEconomicos grupo) {
        em.merge(grupo);
    }
    
    public void borrar(GruposEconomicos grupo) {
        em.createNativeQuery("delete from grupos_economicos where codigo_grupo = '" + grupo.getCodigoGrupo() + "'").executeUpdate();
    }

//    public List<GruposEconomicos> reload() {
//        Query query = em.createNamedQuery("GruposEconomicos.findAll");
//        List<GruposEconomicos> result = (List<GruposEconomicos>) query.getResultList();
//        return result;
//    }
    //Codigo Grupo
    
    
    /*
    Grupos Economicos
    */
    
    public List<GruposEconomicos> findCodigosConsecutivos() {
        Query query = em.createNamedQuery("GruposEconomicos.findCodigoConsecutivo");
        List<GruposEconomicos> result = query.getResultList();
        return result;
    }

    public List<GruposEconomicos> findCodigosNoConsecutivos() {
        Query query = em.createNamedQuery("GruposEconomicos.findCodigoNoConsecutivo");
        List<GruposEconomicos> result = query.getResultList();
        return result;
    }
    
    public GruposEconomicos findGrupoByCodigo(Long codigo){
        Query query = em.createNamedQuery("GruposEconomicos.findGrupoByCode")
                .setParameter("codigo", codigo);
        GruposEconomicos grupo = (GruposEconomicos) query.getSingleResult();
        return grupo;
    }
    
    
    public List<GruposEconomicos> grupoByRelacion(Long codigo){
        Query query = em.createNamedQuery("GruposEconomicos.findGrupoByRelacion")
                .setParameter("codigo", codigo);
        List<GruposEconomicos> relacionG = query.getResultList();
        return relacionG;
    }
    
    public GruposEconomicos grupoByNombre(String nombre){
        Query q = em.createNamedQuery("GruposEconomicos.grupoByNombre")
                .setParameter("nombre", nombre);
        GruposEconomicos grupo = (GruposEconomicos) q.getSingleResult();
        return grupo;
    }
    
    public List<GruposEconomicos> gruposAsignables(String nombre){
        Query q = em.createNamedQuery("GruposEconomicos.findGruposAsignables")
                .setParameter("nombre", nombre);
        List<GruposEconomicos> grupos = q.getResultList();
        return grupos;
    }
    
       public List<GruposEconomicos> gruposTotal(){
       List<GruposEconomicos> grupos = new ArrayList<>();
       grupos = em.createNamedQuery("GruposEconomicos.findAll").getResultList();
       return  grupos;
   }
  
    public List<GruposEconomicos> allGroupsAsc(){
        List<GruposEconomicos> grupos = new ArrayList<>();
        grupos = em.createNamedQuery("GruposEconomicos.findGruposOrderAsc").getResultList();
        return grupos;
    }

}
