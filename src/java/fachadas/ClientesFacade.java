    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;


import entidades.Clientes;
import entidades.Estados;
import entidades.GruposEconomicos;
import entidades.HistoricoEstadosclientes;
import entidades.Parametros;
import java.util.ArrayList;
import java.util.Date;
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
public class ClientesFacade extends AbstractFacade<Clientes> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesFacade() {
        super(Clientes.class);
    }
    
   public List<Clientes> clientesXNombre(String nombre){
          return em.createNamedQuery("Clientes.findByLikeNombre").setParameter("nombre",   nombre + "%").getResultList();
   } 
   
   public List<Clientes> clientesXNit(String nit){
       List<Clientes> result = em.createNamedQuery("Clientes.findByLikeNit").setParameter("nit",   nit + "%").getResultList();
       return result;
   } 
  
   public List<Clientes> clientesNit(String nit){
       return em.createNamedQuery("Clientes.findByNit").setParameter("nit",   nit ).getResultList();
   } 
   
  public Date clientesNitfecrating(String nit) {
        Clientes cliente = null;
        try {
            Query query = em.createNamedQuery("Clientes.findByNit").setMaxResults(1);
            query.setParameter("nit", nit);
            List<Clientes> clientefecharating = query.getResultList();
            if (clientefecharating.size() > 0) {
                cliente = clientefecharating.get(0);
            }
            
        } catch (Exception e) {
          cliente=null;   
        }
        
     return cliente.getFecharating();
    } 
   
   public void borrar(Clientes cliente){
       cliente.setEstadocliente(em.find(Estados.class,em.find(Parametros.class, "ClienteCancelado").getValor()));
       em.merge(cliente);
       
   }

   
   public void guardar(Clientes cliente,String usuario,int estado,int scan){
           Clientes cli = em.find(Clientes.class, cliente.getId());
               if(cli != null){
                   em.merge(cliente);
                   if(estado == 1){
                     HistoricoEstadosclientes historico = new HistoricoEstadosclientes(cliente.getNit(), cliente.getNombre(),  cliente.getEstadocliente().getNombre(),  cliente.getFechaestado(), usuario,1);
                     em.persist(historico);
                   }
                   if(scan == 1){
                        HistoricoEstadosclientes historico =new HistoricoEstadosclientes(cliente.getNit(), cliente.getNombre(),  cliente.getScan().getNombre(),  cliente.getFechascan(), usuario,2);
                        em.persist(historico);
                   }      
               }else{
                   em.persist(cliente);
                   HistoricoEstadosclientes historico = new HistoricoEstadosclientes(cliente.getNit(), cliente.getNombre(),  cliente.getEstadocliente().getNombre(),  cliente.getFechaestado(), usuario,1);
                   em.persist(historico);
                   historico =new HistoricoEstadosclientes(cliente.getNit(), cliente.getNombre(),  cliente.getScan().getNombre(),  cliente.getFechascan(), usuario,2);
                   em.persist(historico);
               }
   }
   
   public String estadoInactivo(){
       return em.find(Parametros.class, "ClienteCancelado").getValor();
   }
   
   public List<Clientes> clientesTotal(){
       List<Clientes> clientes = new ArrayList<Clientes>();
       List<Object> obj = em.createNativeQuery("SELECT id FROM Clientes order by nit").getResultList();
       Iterator it = obj.iterator();
       while(it.hasNext()){
           clientes.add(em.find(Clientes.class, it.next()));
       }
       return  clientes;
   }
  
   
    public Parametros limiteGlobal(){
           return em.find(Parametros.class, "LimiteLegal");
    }
   
    public Parametros Derivado(){
           return em.find(Parametros.class, "Derivados");
    }
    
    public List<HistoricoEstadosclientes> historicos(String nit,Integer tipo){
        return em.createNamedQuery("HistoricoEstadosclientes.findByNitTipo").setParameter("nit", nit).setParameter("tipo", tipo).getResultList();
    }
    
    
    public Double clientesXGrupos(GruposEconomicos grupo){
        return (Double) em.createNativeQuery("select sum(limitetotal)from cupos where cliente in (select id from clientes where grupo = '"+ grupo.getCodigoGrupo()+"')").getSingleResult();
    }
    
    public void guardar(Clientes cliente) {
        em.merge(cliente);
    }
    
    
        /**
     * Metodo que se encarga de consultar los clientes con fecha de rating que
     * se encuentren dentro del rango de fechas.
     *
     * @param fechaIni fecha de inicio del rango de busqueda
     * @param fechaFin fecha fin del rango de busqueda.
     * @return Lista de Clientes que cumplan con lo requerido.
     */
    public List<Clientes> findRatingByFechas(Date fechaIni, Date fechaFin) {
        Query query = em.createNamedQuery("Clientes.findRatingByFechas");
        query.setParameter("fechaIni", fechaIni);
        query.setParameter("fechaFin", fechaFin);
        List<Clientes> result = (List<Clientes>) query.getResultList();
        return result;
    }

    /**
     * Metodo que se encarga de consultar los clientes con fecha de rating que
     * se encuentren dentro del rango de fechas sin incluir la fecha del inicio
     * del rango.
     *
     * @param fechaIni fecha de inicio del rango de busqueda
     * @param fechaFin fecha fin del rango de busqueda.
     * @return Lista de Clientes que cumplan con lo requerido.
     */
    public List<Clientes> findRatingByFechasAbierto(Date fechaIni, Date fechaFin) {
        Query query = em.createNamedQuery("Clientes.findRatingByFechasAbierto");
        query.setParameter("fechaIni", fechaIni);
        query.setParameter("fechaFin", fechaFin);
        List<Clientes> result = (List<Clientes>) query.getResultList();
        return result;
    }
    
        /* Grupos Economicos */
    
    public List<Clientes> clientesByCodigo(Long codigo){
        Query q = em.createNamedQuery("Clientes.findByGrupo");
        q.setParameter("codigo", codigo);
        List<Clientes> clientes = q.getResultList();
        
        return clientes;
    }
    
    public void updateById(GruposEconomicos grupo, String id){
        Query q = em.createNamedQuery("Clientes.updateById");
        q.setParameter("grupo", grupo);
        q.setParameter("id", id);
        q.executeUpdate();
    }
   
}
