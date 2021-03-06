/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.ParamRespuestaVariable;
import entidades.Modulo;
import entidades.VariablesRating;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class ParamRespuestaVariableFacade extends AbstractFacade<ParamRespuestaVariable> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParamRespuestaVariableFacade() {
        super(ParamRespuestaVariable.class);
    }
    
    public List<String> listarTipoRespuesta(){
        
        Query q = em.createNativeQuery("select distinct upper(nombre) "
                + "from ri.variable_modulo "
                + "where nombre <> 'id cliente' or nombre <> 'id_cliente'");
        List listaProvisional = q.getResultList();
        Iterator i = listaProvisional.iterator();
        
        List<String> listaTipos = new ArrayList<>();
        
        while(i.hasNext()){
            
            Object [] objetoAgregado = (Object[]) i.next();
            listaTipos.add(objetoAgregado[0].toString());
            
        }
    
        return listaTipos;
        
    }
    
    
    public List<ParamRespuestaVariable> listarRespuestasVariable(){
        List<ParamRespuestaVariable> listaRespuestas = new ArrayList<>();
        List listaProvisional = 
                em.createNativeQuery("select r.id, v.nombre, r.respuesta, "
                        + "r.woe, r.min, r.max from ri.respuesta r "
                        + "inner join ri.variable_modulo v "
                        + "on r.id_variable = v.id_variable").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            ParamRespuestaVariable objetoAgregado = new ParamRespuestaVariable();
            objetoAgregado.setId((Integer) object[0]);
            objetoAgregado.setNombre(object[1].toString());
            objetoAgregado.setRespuesta(object[2].toString());
            objetoAgregado.setWoe((BigDecimal) object[3]);
            objetoAgregado.setMin((BigDecimal) object[4]);
            objetoAgregado.setMax((BigDecimal) object[5]);
            listaRespuestas.add(objetoAgregado);
        }
        
        return listaRespuestas;
        
    }

    public boolean guardarParametro(ParamRespuestaVariable parametro){
        Query q1 = em.createNativeQuery("select max(id) from ri.respuesta");
        Integer codigo;
        
        try{
            codigo = (Integer) q1.getSingleResult();
        } catch(NoResultException e){
            codigo = 1;
        }
        
        Query q = em.createNativeQuery("insert into "
                + "ri.respuesta values(?, ?, ?, ?, ?, ?, ?)");
        q.setParameter(1, codigo);
        q.setParameter(2, 1);
        q.setParameter(3, 1);
        q.setParameter(4, parametro.getNombre());
        q.setParameter(5, parametro.getWoe());
        q.setParameter(6, parametro.getMin());
        q.setParameter(7, parametro.getMax());
    
        int result = q.executeUpdate();
        
        return result != 0;
        
    }
    
    public boolean actualizarParametro(ParamRespuestaVariable parametro){
    
        Query q = em.createNativeQuery("UPDATE ri.respuesta "
                + "SET woe = ?, min = ?, max = ? WHERE id = ?");
        q.setParameter(1, parametro.getWoe());
        q.setParameter(2, parametro.getMin());
        q.setParameter(3, parametro.getMax());
        q.setParameter(4, parametro.getId());
             
        int result = q.executeUpdate();
        
        return result != 0;
        
    }
    
    public boolean eliminarParametro(ParamRespuestaVariable parametro){

        Query q = em.createNativeQuery("DELETE FROM ri.respuesta WHERE id = ?");
        q.setParameter(1, parametro.getId());
             
        int result = q.executeUpdate();
        
        return result != 0;        
        
    }
    
    public List<List<VariablesRating>> listarRespuestasVariablesRating(){
        
        List<List<VariablesRating>> listaRespuestasVariablesModulo = 
                new ArrayList<>();
        List<VariablesRating> listaRespuestasVariables = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select id_modulo, "
                + "nombre_modulo, id_variable, respuesta, woe, min, max, "
                + "nombre_variable "
                + "from ri.informacion_respuestas_variables_rating_woe_vw").
                getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            VariablesRating objetoAgregado = new VariablesRating();
            objetoAgregado.setIdModulo(((Integer)object[0]));
            objetoAgregado.setNombreModulo(((String)object[1]));
            objetoAgregado.setIdVariable((Integer) object[2]);
            objetoAgregado.setRespuesta((String) object[3]);
       
            listaRespuestasVariables.add(objetoAgregado);
        }
        listaRespuestasVariablesModulo.
                add(listarRespuestasVariablesComportamiento
        (listaRespuestasVariables));
       
        return listaRespuestasVariablesModulo;
    }
    
    public List<VariablesRating> listarVariablesFinanciero(List<VariablesRating> listaVariables){
        
        List<VariablesRating> listaVariablesFinanciero = new ArrayList<>();
       
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 1) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                listaVariablesFinanciero.add(objetoAgregado);
            }
        }
        return listaVariablesFinanciero;
    }
    
    public List<VariablesRating> listarRespuestasVariablesFinanciero
        (List<VariablesRating> listaRespuestaVariables){
        
        List<VariablesRating> listaRespuestaVariablesFinanciero = 
                new ArrayList<>();
       
        for (int i = 0; i < listaRespuestaVariables.size(); i++) {
            if (listaRespuestaVariables.get(i).getIdModulo() == 1) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaRespuestaVariablesFinanciero.add(objetoAgregado);
            }
        }
        return listaRespuestaVariablesFinanciero;
    }
    
    public List<VariablesRating> listarVariablesComportamiento
        (List<VariablesRating> listaVariables){
        
        List<VariablesRating> listaVariablesComportamiento = new ArrayList<>();
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 2) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaVariables.get(i).getRespuesta());
                listaVariablesComportamiento.add(objetoAgregado);
            }
            
        }
        return listaVariablesComportamiento;
    }
    
    public List<VariablesRating> listarRespuestasVariablesComportamiento
        (List<VariablesRating> listaRespuestasVariables){
        
        List<VariablesRating> listaRespuestasVariablesComportamiento = 
                new ArrayList<>();
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 2) {
                VariablesRating objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesComportamiento.add(objetoAgregado);
            }
            
        }
        return listaRespuestasVariablesComportamiento;
    }
    
    public List<VariablesRating> listarVariablesObjetivo(List<VariablesRating> listaVariables){
        List<VariablesRating> listaVariablesObjetivo = new ArrayList<>();
        VariablesRating objetoAgregado = null;
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 3) {
                objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                
                listaVariablesObjetivo.add(objetoAgregado);
            }    
        }
        return listaVariablesObjetivo;
    }
    
    public List<VariablesRating> listarVariablesSubjetivo(List<VariablesRating> listaVariables){
        
        List<VariablesRating> listaVariablesSubjetivo = new ArrayList<>();
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 4) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                        
                listaVariablesSubjetivo.add(objetoAgregado);
            }
        }
        return listaVariablesSubjetivo;
    }
    
    public List<VariablesRating> listarVariablesResultado(List<VariablesRating> listaVariables){   
        List<VariablesRating> listaVariablesResultado = new ArrayList<>();
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 5) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                        
                listaVariablesResultado.add(objetoAgregado);
            }
            
        }
        return listaVariablesResultado;
    }
    
    public List<Modulo> listarModulo(){    
        List<Modulo> listaModulos = new ArrayList<>();
        Modulo objetoAgregado = null;
       
        List listaProvisional = em.createNativeQuery("select id, nombre "
                + "from ri.modulo order by id asc").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new Modulo();
            objetoAgregado.setNombre((String) object[1]);
        
            listaModulos.add(objetoAgregado);
        }
        return listaModulos;
    }
    
    public List<List<VariablesRating>> listarVariablesRating(){    
        List<List<VariablesRating>> listaVariablesModulo = new ArrayList<>();
        List<VariablesRating> listaVariables = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select id_modulo, "
                + "nombre_modulo, id_variable, respuesta, woe, min, max, "
                + "nombre_variable "
                + "from ri.informacion_respuestas_variables_rating_woe_vw").getResultList();
        Iterator i = listaProvisional.iterator();
       
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            VariablesRating objetoAgregado = new VariablesRating();
            objetoAgregado.setIdModulo(((Integer)object[0]));
            objetoAgregado.setNombreModulo((String) object[1]);
            objetoAgregado.setIdVariable((Integer)object[2]);
            objetoAgregado.setRespuesta((String)object[3]);
       
            listaVariables.add(objetoAgregado);
        }
        
        listaVariablesModulo.add(listarVariablesFinanciero(listaVariables));
        listaVariablesModulo.add(listarVariablesComportamiento(listaVariables));
        listaVariablesModulo.add(listarVariablesObjetivo(listaVariables));
        listaVariablesModulo.add(listarVariablesSubjetivo(listaVariables));
        listaVariablesModulo.add(listarVariablesResultado(listaVariables));
        
        return listaVariablesModulo;
    }  
}