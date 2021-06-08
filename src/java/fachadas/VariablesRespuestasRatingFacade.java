/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.GruposClientes;
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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author x356167
 */
@Stateless
public class VariablesRespuestasRatingFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VariablesRespuestasRatingFacade() {
        super(GruposClientes.class);
    }
     
    //
    public List<VariablesRating> listarRespuestasRating(){
        List<VariablesRating> listaRespuestasVariables = new ArrayList<>();
       
        List listaProvisional = em.createNativeQuery("select id, id_modulo, "
                + "nombre_modulo, id_variable, nombre_variable, respuesta, woe, "
                + "min, max  "
                + "from ri.informacion_respuestas_variables_rating_woe_vw").
                getResultList();
        Iterator i = listaProvisional.iterator();
       
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            BigDecimal valorProvisional = object[6] == null ? 
                    new BigDecimal("0"): ((BigDecimal) object[6]);
            BigDecimal minProvisional = object[7] == null ? 
                    new BigDecimal("0"): ((BigDecimal) object[7]);
            BigDecimal maxProvisional = object[8] == null ? 
                    new BigDecimal("0"): ((BigDecimal) object[8]);
            
            VariablesRating objetoAgregado = 
                    new VariablesRating();
            objetoAgregado.setId(((Integer)object[0]));
            objetoAgregado.setIdModulo(((Integer)object[1]));
            objetoAgregado.setNombreModulo(((String)object[2]));
            objetoAgregado.setIdVariable(((Integer)object[3]));
            objetoAgregado.setNombre(((String)object[4]));
            objetoAgregado.setRespuesta(((String)object[5]));
            objetoAgregado.setWoe((valorProvisional.toString()));
            objetoAgregado.setMin((minProvisional.toString()));
            objetoAgregado.setMax((maxProvisional.toString()));
           
            listaRespuestasVariables.add(objetoAgregado);
        }
        return listaRespuestasVariables;
    }
    
    public int updateVarablesRespuesta(List<VariablesRating> listaRespuestas){
        int result = 0;
        Query q = em.createNativeQuery("UPDATE ri.respuesta SET woe = ?, "
                + "min = ?, max = ? WHERE id = ?");
        
        for (int i = 0; i < listaRespuestas.size(); i++) {
            BigDecimal woe = new BigDecimal(listaRespuestas.get(i).getWoe());
            BigDecimal min = new BigDecimal(listaRespuestas.get(i).getMin());
            BigDecimal max = new BigDecimal(listaRespuestas.get(i).getMax());
            result = q.setParameter(1, woe).setParameter(2, min).setParameter(3, max).
                    setParameter(4, (Integer)listaRespuestas.get(i).getId()).
                    executeUpdate();
        }    
          return result;
    }
 }
