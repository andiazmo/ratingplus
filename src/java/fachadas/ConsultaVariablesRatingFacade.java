/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.GruposClientes;
import entidades.Modulo;
import entidades.VariablesRating;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author x356167
 */
@Stateless
public class ConsultaVariablesRatingFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;
    
    private List<VariablesRating> listaVariablesFinanciero;
    private List<VariablesRating> listaVariablesComportamiento;
    private List<VariablesRating> listaVariablesObjetivo;
    private List<VariablesRating> listaVariablesSubjetivo;
    private List<VariablesRating> listaVariablesResultado;
    private List<VariablesRating> listaRespuestasVariablesFinanciero;
    private List<VariablesRating> listaRespuestasVariablesComportamiento;
    private List<VariablesRating> listaRespuestasVariablesObjetivo;
    private List<VariablesRating> listaRespuestasVariablesSubjetivo;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaVariablesRatingFacade() {
        super(GruposClientes.class);
    }
   
    public List<List<VariablesRating>> listarVariablesRating(){
        List<List<VariablesRating>> listaVariablesModulo = 
                new ArrayList<>();
        List<VariablesRating> listaVariables = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select id, id_modulo, "
                + "nombre, beta, id_variable from ri.variable_modulo "
                + "order by id_modulo, id_variable asc").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            VariablesRating objetoAgregado = new VariablesRating();
            objetoAgregado.setNombre((String) object[2]);
            objetoAgregado.setIdModulo(((Integer)object[1]));
        
            listaVariables.add(objetoAgregado);
        }
//        listaVariablesModulo.add(listarVariablesFinanciero(listaVariables));
//        listaVariablesModulo.add(listarVariablesComportamiento(listaVariables));
//        listaVariablesModulo.add(listarVariablesObjetivo(listaVariables));
//        listaVariablesModulo.add(listarVariablesSubjetivo(listaVariables));
//        listaVariablesModulo.add(listarVariablesResultado(listaVariables));

        this.listarVariables(listaVariables);
        
        listaVariablesModulo.add(this.getListaVariablesFinanciero());
        listaVariablesModulo.add(this.getListaVariablesComportamiento());
        listaVariablesModulo.add(this.getListaVariablesObjetivo());
        listaVariablesModulo.add(this.getListaVariablesSubjetivo());
        listaVariablesModulo.add(this.getListaVariablesResultado());
        
        return listaVariablesModulo;
    }
    
    public List<List<VariablesRating>> listarRespuestasVariablesRating(){
        List<List<VariablesRating>> listaRespuestasVariablesModulo = 
                new ArrayList<>();
        List<VariablesRating> listaRespuestasVariables = new ArrayList<>();
        this.listaRespuestasVariablesFinanciero = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select id_modulo, "
                + "id_variable, respuesta, nombre "
                + "from ri.informacion_respuestas_variables_rating_vm "
                + "order by id_modulo, id_variable asc").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            VariablesRating objetoAgregado = new VariablesRating();
            objetoAgregado.setIdModulo(((Integer)object[0]));
            objetoAgregado.setRespuesta((String) object[2]);
            objetoAgregado.setNombre((String) object[3]);
            
            
            listaRespuestasVariables.add(objetoAgregado);
        }
        
        this.listarRespuestasVariables(listaRespuestasVariables);
       // listaRespuestasVariablesModulo.
       //         add(listarRespuestasVariablesComportamiento
       // (listaRespuestasVariables));
       // listaRespuestasVariablesModulo.
       //         add(listarRespuestasVariablesSubjetivo
       // (listaRespuestasVariables));
       // listaRespuestasVariablesModulo.
       //         add(listarRespuestasVariablesObjetivo
       // (listaRespuestasVariables));
        listaRespuestasVariablesModulo.add(
                this.getListaRespuestasVariablesFinanciero());
        listaRespuestasVariablesModulo.add(
                this.getListaRespuestasVariablesComportamiento());
        listaRespuestasVariablesModulo.add(listarRespuestasVariablesSubjetivo
        (listaRespuestasVariables));
        listaRespuestasVariablesModulo.
                add(listarRespuestasVariablesObjetivo
        (listaRespuestasVariables));
        
        return listaRespuestasVariablesModulo;
    }
    
    public void listarVariables
        (List<VariablesRating> listaVariables){
        listaVariablesFinanciero = new ArrayList<>();
        listaVariablesComportamiento = new ArrayList<>();
        listaVariablesObjetivo = new ArrayList<>();
        listaVariablesSubjetivo = new ArrayList<>();
        listaVariablesResultado = new ArrayList<>();
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 1) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                listaVariablesFinanciero.add(objetoAgregado);
            }
            if (listaVariables.get(i).getIdModulo() == 2) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                listaVariablesComportamiento.add(objetoAgregado);
            }
            if (listaVariables.get(i).getIdModulo() == 3) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                listaVariablesObjetivo.add(objetoAgregado);
            }
            if (listaVariables.get(i).getIdModulo() == 4) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                listaVariablesSubjetivo.add(objetoAgregado);
            }
             if (listaVariables.get(i).getIdModulo() == 5) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                listaVariablesResultado.add(objetoAgregado);
            }
        }
    }
        
    public void listarRespuestasVariables
        (List<VariablesRating> listaRespuestaVariables){
        listaRespuestasVariablesFinanciero = new ArrayList<>();
        listaRespuestasVariablesComportamiento = new ArrayList<>();
        listaRespuestasVariablesObjetivo = new ArrayList<>();
        listaRespuestasVariablesSubjetivo = new ArrayList<>();
        
        for (int i = 0; i < listaRespuestaVariables.size(); i++) {
            if (listaRespuestaVariables.get(i).getIdModulo() == 1) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaRespuestasVariablesFinanciero.add(objetoAgregado);
            }
            if (listaRespuestaVariables.get(i).getIdModulo() == 2) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaRespuestasVariablesComportamiento.add(objetoAgregado);
            }
            if (listaRespuestaVariables.get(i).getIdModulo() == 3) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaRespuestasVariablesObjetivo.add(objetoAgregado);
            }
            if (listaRespuestaVariables.get(i).getIdModulo() == 4) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaRespuestasVariablesSubjetivo.add(objetoAgregado);
            }
        }
    }
    
    public List<VariablesRating> listarRespuestasVariablesFinanciero
        (List<VariablesRating> listaRespuestaVariables){
        listaRespuestasVariablesFinanciero = new ArrayList<>();
        listaVariablesComportamiento = new ArrayList<>();
        listaVariablesObjetivo = new ArrayList<>();
        listaVariablesSubjetivo = new ArrayList<>();
        
        for (int i = 0; i < listaRespuestaVariables.size(); i++) {
            if (listaRespuestaVariables.get(i).getIdModulo() == 1) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaRespuestasVariablesFinanciero.add(objetoAgregado);
            }
            if (listaRespuestaVariables.get(i).getIdModulo() == 2) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaVariablesComportamiento.add(objetoAgregado);
            }
            if (listaRespuestaVariables.get(i).getIdModulo() == 3) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaVariablesObjetivo.add(objetoAgregado);
            }
            if (listaRespuestaVariables.get(i).getIdModulo() == 4) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).
                        getRespuesta());
                listaVariablesSubjetivo.add(objetoAgregado);
            }
        }
        return listaRespuestasVariablesFinanciero;
    }
    
    public List<VariablesRating> listarRespuestasVariablesComportamiento
        (List<VariablesRating> listaRespuestasVariables){
        List<VariablesRating> listaRespuestasVariablesComportamiento = 
                new ArrayList<>();
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 2) {
                VariablesRating objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).
                        getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), 
                        listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesComportamiento.add(objetoAgregado);
            }
            
        }
        return listaRespuestasVariablesComportamiento;    
    }
        
   public List<VariablesRating> listarRespuestasVariablesObjetivo
        (List<VariablesRating> listaRespuestasVariables){
        List<VariablesRating> listaRespuestasVariablesObjetivo = 
                new ArrayList<>();
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 3) {
                VariablesRating objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).
                        getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), 
                        listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesObjetivo.add(objetoAgregado);
            }
        }
        return listaRespuestasVariablesObjetivo;    
    }
    
    public List<VariablesRating> listarRespuestasVariablesSubjetivo
        (List<VariablesRating> listaRespuestasVariables){
        List<VariablesRating> listaRespuestasVariablesSubjetivo = 
                new ArrayList<>();
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 4) {
                VariablesRating objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).
                        getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), 
                        listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesSubjetivo.add(objetoAgregado);
            }
        }
        return listaRespuestasVariablesSubjetivo;    
    }
   
    public List<Modulo> listarModulo(){
        List<Modulo> listaModulos = new ArrayList<>();
        
        List listaProvisional = 
                em.createNativeQuery("select id, nombre "
                        + "from ri.modulo order by id asc").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            Modulo objetoAgregado = new Modulo();
            objetoAgregado.setNombre((String) object[1]);
        
            listaModulos.add(objetoAgregado);
        }
        return listaModulos;
    }
   
    public List<VariablesRating> getListaVariablesComportamiento() {
        return listaVariablesComportamiento;
    }

    public void setListaVariablesComportamiento(List<VariablesRating> listaVariablesComportamiento) {
        this.listaVariablesComportamiento = listaVariablesComportamiento;
    }

    public List<VariablesRating> getListaVariablesObjetivo() {
        return listaVariablesObjetivo;
    }

    public void setListaVariablesObjetivo(List<VariablesRating> listaVariablesObjetivo) {
        this.listaVariablesObjetivo = listaVariablesObjetivo;
    }

    public List<VariablesRating> getListaVariablesSubjetivo() {
        return listaVariablesSubjetivo;
    }

    public void setListaVariablesSubjetivo(List<VariablesRating> listaVariablesSubjetivo) {
        this.listaVariablesSubjetivo = listaVariablesSubjetivo;
    }
    
    public List<VariablesRating> getListaRespuestasVariablesFinanciero() {
        return listaRespuestasVariablesFinanciero;
    }

    public void setListaRespuestasVariablesFinanciero(List<VariablesRating> listaRespuestasVariablesFinanciero) {
        this.listaRespuestasVariablesFinanciero = listaRespuestasVariablesFinanciero;
    }

    public List<VariablesRating> getListaRespuestasVariablesComportamiento() {
        return listaRespuestasVariablesComportamiento;
    }

    public void setListaRespuestasVariablesComportamiento(List<VariablesRating> listaRespuestasVariablesComportamiento) {
        this.listaRespuestasVariablesComportamiento = listaRespuestasVariablesComportamiento;
    }

    public List<VariablesRating> getListaRespuestasVariablesObjetivo() {
        return listaRespuestasVariablesObjetivo;
    }

    public void setListaRespuestasVariablesObjetivo(List<VariablesRating> listaRespuestasVariablesObjetivo) {
        this.listaRespuestasVariablesObjetivo = listaRespuestasVariablesObjetivo;
    }

    public List<VariablesRating> getListaRespuestasVariablesSubjetivo() {
        return listaRespuestasVariablesSubjetivo;
    }

    public void setListaRespuestasVariablesSubjetivo(List<VariablesRating> listaRespuestasVariablesSubjetivo) {
        this.listaRespuestasVariablesSubjetivo = listaRespuestasVariablesSubjetivo;
    }
    
    public List<VariablesRating> getListaVariablesFinanciero() {
        return listaVariablesFinanciero;
    }

    public void setListaVariablesFinanciero(List<VariablesRating> listaVariablesFinanciero) {
        this.listaVariablesFinanciero = listaVariablesFinanciero;
    }
    
     public List<VariablesRating> getListaVariablesResultado() {
        return listaVariablesResultado;
    }

    public void setListaVariablesResultado(List<VariablesRating> listaVariablesResultado) {
        this.listaVariablesResultado = listaVariablesResultado;
    }
}