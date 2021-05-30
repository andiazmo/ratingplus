/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.Cupos;
import entidades.Limitesautorizados;
import entidades.Modalidades;
import entidades.Parametros;
import entidades.Vclientes;
import java.util.ArrayList;
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
public class LimitesautorizadosFacade extends AbstractFacade<Limitesautorizados> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LimitesautorizadosFacade() {
        super(Limitesautorizados.class);
    }
    public List<Limitesautorizados> LimiteXCupos(Cupos cupo){
          return em.createNamedQuery("Limitesautorizados.findByCupo").setParameter("cupo",cupo).getResultList();
   }
    
   public List<Limitesautorizados> LimiteXCuposXModalidad(Cupos cupo, Modalidades modalidad){
       List<Limitesautorizados>   limites = em.createNamedQuery("Limitesautorizados.findByModalidad&Cupo").setParameter("modalidad", modalidad) .setParameter("cupo",cupo).getResultList();
       if(limites.size() > 0) return limites;
       else return new ArrayList<Limitesautorizados>();
   }
   
    public void modificar (Limitesautorizados limite){
        try{
        em.merge(limite);
        }catch (Exception ex){
            System.out.println("Se ha producido un error al modificar " + ex.getMessage());
        }
           
    }
    public List<Limitesautorizados> Limitesautorizadosmodalida(Modalidades modalidad) {
         try {
            Query q = em.createNamedQuery("Limitesautorizados.findByModalidad");
            q.setParameter("modalidad", modalidad);
            List<Limitesautorizados> entities = q.getResultList();
            return entities;
        } catch (Exception e) {
            return null;
        }
    }
     
     
      public Modalidades BuscarModalida(String modalidad) {
        try {
            Query q = em.createNamedQuery("Modalidades.findByNombre");
            q.setParameter("nombre", modalidad);
            Modalidades entities = (Modalidades) q.getSingleResult();
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      
    
    public void guardar (Limitesautorizados limite){
        try{
        em.persist(limite);
        }catch (Exception ex){
            System.out.println("Se ha producido un error al modificar " + ex.getMessage());
        }
           
    }
    public void borrar(Limitesautorizados limite){
        em.createNativeQuery("delete from limitesautorizados where id = '" + limite.getId() + "'").executeUpdate();
    }
    
    public Double subLimiteanterior(String id){
         Limitesautorizados limite = (Limitesautorizados) em.createNamedQuery("Limitesautorizados.findById").setParameter("id", id).getResultList().get(0);
         return limite.getSublimiteautorizado();
    } 
  
    public List<Vclientes> reporte(String nit){
        return em.createNamedQuery("Vclientes.findByNit").setParameter("nit", nit).getResultList();
    }
    
     public List<Vclientes> reporteTodos(){
        return em.createNamedQuery("Vclientes.findAll").getResultList();
    }
    
    public Parametros limiteGlobal(){
           return em.find(Parametros.class, "LimiteLegal");
    }
    
    public String guardarGlobal(String valor){
        Parametros parametro = limiteGlobal();
        String valorAnterior = parametro.getValor();
//        actualizarPatrimonioTecnicoGrupos(parametro.getValor(),valor);
        parametro.setValor(valor);
        em.merge(parametro);
        return valorAnterior;
    }
    
    private void actualizarPatrimonioTecnicoGrupos(String valorAnterior, 
            String valorActual){
        String strQuery = "UPDATE grupos_economicos SET cupo='"+valorActual+
                "' WHERE cupo='"+valorAnterior+"'";
        em.createNativeQuery(strQuery).executeUpdate();
    }
    
    
}
