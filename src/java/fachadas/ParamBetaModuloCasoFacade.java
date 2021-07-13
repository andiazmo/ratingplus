/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.BetaModuloCasoUso;
import entidades.BetaModuloCategoria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author x356167
 */
@Stateless
public class ParamBetaModuloCasoFacade extends AbstractFacade<BetaModuloCasoUso> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParamBetaModuloCasoFacade() {
        super(BetaModuloCasoUso.class);
    }
    
    public List<BetaModuloCategoria> listarCategorias(){
        
        List<BetaModuloCategoria> listaCategorias = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select codigo, info_categoria from rp_categoria_beta_modulo").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            BetaModuloCategoria objetoAgregado = new BetaModuloCategoria();
            objetoAgregado.setCodigo((Integer) object[0]);
            objetoAgregado.setCategoria((String) object[1]);
            listaCategorias.add(objetoAgregado);
        }
        
        return listaCategorias;
        
    }
    
    
    public List<BetaModuloCasoUso> listarBetasModulo(){
    
        List<BetaModuloCategoria> listaCategorias = this.listarCategorias();
        List<BetaModuloCasoUso> listaBetas = new ArrayList<>();
        List listaProvisional = em.createNativeQuery("select codigo, num_caso, "
                + "categoria_beta, valor from ri.rp_beta_modulo_caso asc"
                + "order by num_caso asc").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            BetaModuloCasoUso objetoAgregado = new BetaModuloCasoUso();
            objetoAgregado.setCodigo((Integer) object[0]);
            objetoAgregado.setNumCaso((Integer) object[1]);
            
            for(BetaModuloCategoria categoria : listaCategorias){
                if(categoria.getCodigo() == ((Integer) object[2])){
                    objetoAgregado.setCategoria(categoria);
                }   
            }
            
            objetoAgregado.setValor(((BigDecimal)object[3]).doubleValue());
            listaBetas.add(objetoAgregado);
        }
        
        return listaBetas;
        
    }

    public boolean guardarParametro(BetaModuloCasoUso parametro){
    
        Query q1 = em.createNativeQuery("select max(codigo) from ri.rp_beta_modulo_caso");
        Integer codigo;
        
        try{
            codigo = (Integer) q1.getSingleResult();
        } catch(NoResultException e){
            codigo = 1;
        }
        
        Query q = em.createNativeQuery("insert into ri.rp_beta_modulo_caso values(?, ?, ?, ?)");
        q.setParameter(1, codigo);
        q.setParameter(2, parametro.getNumCaso());
        q.setParameter(3, parametro.getCategoria().getCodigo());
        q.setParameter(4, parametro.getValor());
    
        int result = q.executeUpdate();
        
        return result != 0;
        
    }
    
    public boolean actualizarParametro(List<BetaModuloCasoUso> listParametros){
    
        Query q = em.createNativeQuery("UPDATE ri.rp_beta_modulo_caso SET valor = ? WHERE codigo = ?");
        int result = 0;
        for(int i=0; i < listParametros.size(); i++) {
           
            q.setParameter(1, listParametros.get(i).getValor());
            q.setParameter(2, listParametros.get(i).getCodigo());
             
            result = q.executeUpdate();
            
        }
        
        
        return result != 0;
        
    }
    
    public boolean actualizarParametro1(BetaModuloCasoUso parametro){
    
        Query q = em.createNativeQuery("UPDATE ri.rp_beta_modulo_caso SET valor = ? WHERE codigo = ?");
        q.setParameter(1, parametro.getValor());
        q.setParameter(2, parametro.getCodigo());
             
        int result = q.executeUpdate();
        
        return result != 0;
        
    }
    
    public boolean eliminarParametro(List<BetaModuloCasoUso> listParametro){
        
        Query q = em.createNativeQuery("DELETE FROM ri.rp_beta_modulo_caso WHERE codigo = ?");
        for(int i=0; i<listParametro.size(); i++) {
           // q.setParameter(1, parametro.getCodigo());
           
           
             
        int result = q.executeUpdate();
        }
        
        
        int result = q.executeUpdate();
        
        
        return result != 0;        
        
    }
    
}
