/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import entidades.Clientes;
import entidades.Parametros;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author User
 */
public class ClientesDAO extends GenericDAOJPA<Clientes>{
    
    public ClientesDAO() {
        super(Clientes.class);
        em = factory.createEntityManager();
    }
    
    public List<Clientes> clientesXNit(String nit){
        EntityManager em = getEntityManager();
        List<Clientes> result = em.createNamedQuery("Clientes.findByLikeNit").setParameter("nit",   nit + "%").getResultList();
        return result;
    }
    
    public List<Clientes> clientesNit(String nit){
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Clientes.findByNit").setParameter("nit",   nit ).getResultList();
    }
    
    public Parametros Derivado(){
        EntityManager em = getEntityManager();
        Parametros result=  em.find(Parametros.class, "Derivados");
        return result;
    }
    
    public List<Clientes> clientesTotal(){
        EntityManager em = getEntityManager();
        List<Clientes> clientes = new ArrayList<Clientes>();
        List<Object> obj = em.createNativeQuery("SELECT id FROM Clientes  order by nit").getResultList();
        Iterator it = obj.iterator();
        while(it.hasNext()){
            clientes.add(em.find(Clientes.class, it.next()));
        }
        return  clientes;
    }
    
    public String estadoInactivo(){
        EntityManager em = getEntityManager();
        return em.find(Parametros.class, "ClienteCancelado").getValor();
    }
    
    /**
     *2018-03-07 Migracion de WS Cupos a aplicativo Cupos Web
     *              Implementacion fusuion de persistencia
     *INI-ACT
     * @param nit
     * @return 
     **/
   // public Clientes buscarCliente(String nit){
   //     EntityManager em = getEntityManager();
   //     Clientes result = (Clientes) em.createNamedQuery("Clientes.findByLikeNit").setParameter("nit",   nit + "%").getResultList();
   //     return result;
   // }
    /**
     *2018-03-07 Migracion de WS Cupos a aplicativo Cupos Web
     *              Implementacion fusuion de persistencia
     *FIN-ACT
     **/   
}
