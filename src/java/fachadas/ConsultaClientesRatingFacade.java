/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.ClientesRating;
import entidades.GruposClientes;
import entidades.Modulo;
import entidades.VariablesRating;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
public class ConsultaClientesRatingFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaClientesRatingFacade() {
        super(GruposClientes.class);
    }
    
    public List<GruposClientes> listarGruposCliente(){
        List<GruposClientes> listaGrupos = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select codigo_grupo, "
                + "nombre from grupos_economicos "
                + "order by codigo_grupo asc").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            GruposClientes objetoAgregado = new GruposClientes();
            objetoAgregado.
                    setCodigoGrupo
        (Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setNombreGrupo((String) object[1]);
            listaGrupos.add(objetoAgregado);
        }
        return listaGrupos;    
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
        listaVariablesModulo.add(listarVariablesFinanciero(listaVariables));
        listaVariablesModulo.add(listarVariablesComportamiento(listaVariables));
        listaVariablesModulo.add(listarVariablesObjetivo(listaVariables));
        listaVariablesModulo.add(listarVariablesSubjetivo(listaVariables));
        listaVariablesModulo.add(listarVariablesResultado(listaVariables));
        
        return listaVariablesModulo;
    }
    
    
    public List<List<VariablesRating>> listarRespuestasVariablesRating(){
        List<List<VariablesRating>> listaRespuestasVariablesModulo = 
                new ArrayList<>();
        List<VariablesRating> listaRespuestasVariables = new ArrayList<>();
        
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
        listaRespuestasVariablesModulo.
                add(listarRespuestasVariablesComportamiento
        (listaRespuestasVariables));
        listaRespuestasVariablesModulo.
                add(listarRespuestasVariablesSubjetivo
        (listaRespuestasVariables));
        listaRespuestasVariablesModulo.
                add(listarRespuestasVariablesObjetivo
        (listaRespuestasVariables));
        
        return listaRespuestasVariablesModulo;
    }
    
    public List<VariablesRating> listarVariablesFinanciero
        (List<VariablesRating> listaVariables){
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
        List<VariablesRating> listaVariablesComportamiento = 
                new ArrayList<>();
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 2) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).
                        getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).
                        getNombre());
                objetoAgregado.setRespuesta(listaVariables.get(i).
                        getRespuesta());
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
    
    public List<VariablesRating> listarVariablesObjetivo(
            List<VariablesRating> listaVariables){
        List<VariablesRating> listaVariablesObjetivo = new ArrayList<>();
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 3) {
                VariablesRating objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                
                listaVariablesObjetivo.add(objetoAgregado);
            }
        }
        return listaVariablesObjetivo;
    }
    
    public List<VariablesRating> listarVariablesSubjetivo(
            List<VariablesRating> listaVariables){
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
   
    public List<VariablesRating> listarVariablesResultado(
            List<VariablesRating> listaVariables){
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
    
    public List<ClientesRating> consultaClienteGrupoResultadoRating
        (String tipoConsulta, String parametro, String periodoFiscal){
        String query = "SELECT id, nit, valor_rating, valor_rating_final, "
                + "razon_social, nombre_grupo, fecha_calculo "
                + "FROM ri.informacion_cliente_grupo_resultado_rating_vw WHERE ";
        List listaProvisional;
        List<ClientesRating> listaClientes = new ArrayList<>();
        Iterator i;
        Iterator j;
        
        switch(Integer.parseInt(tipoConsulta)){    
            case 0:
                query += " nit = ?";
                break;
            case 1:
                query += " razon_social = ?";
                break;
        }
        query += " ORDER BY fecha_calculo desc LIMIT 1";
        listaProvisional = em.createNativeQuery(query).setParameter(1, 
                tipoConsulta.equals("2") ? Integer.parseInt(parametro) : 
                        parametro).getResultList();
        
        i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            BigDecimal valorProvisional = object[3] == null ? new BigDecimal("0"): 
                    ((BigDecimal) object[3]);
            Timestamp fechaProvisional = object[6] == null ? null : 
                    ((Timestamp) object[6]);
            
        ClientesRating objetoAgregado = new ClientesRating();
            objetoAgregado.setId(String.valueOf(object[0]));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setValorRating(valorProvisional.toString());
            objetoAgregado.setNombre((String) object[4]);
            objetoAgregado.setGrupo((String) object[5]);
            if(fechaProvisional != null){
                objetoAgregado.
                        setFechaInsercion(
                                new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa")
                                        .format(fechaProvisional));
            } else {
                objetoAgregado.setFechaInsercion("No disponible");
            }             
            listaClientes.add(objetoAgregado);
        }
        return listaClientes;
    }
    
    public List<ClientesRating> consultaClienteGrupoResultadoSinResRating
        (String tipoConsulta, String parametro){
        String query = "SELECT id, nit, nombre, nombre_grupo, valor_rating, "
                + "valor_rating_final, fecha_insercion "
                + "FROM ri.informacion_cliente_grupo_resultado_sinref_rating_vw "
                + "WHERE ";
        List listaProvisional;
        List<ClientesRating> listaClientes = new ArrayList<>();
        Iterator i;
      
        switch(Integer.parseInt(tipoConsulta)){
            case 0:
                query += " nit = ?";
                break;
            case 1:
                query += " nombre = ?";
                break;
        }
        query += " ORDER BY fecha_insercion desc LIMIT 1";
        listaProvisional = em.createNativeQuery(query).
                setParameter(1, tipoConsulta.equals("2") ? 
                        Integer.parseInt(parametro) : parametro).getResultList();
        
        i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            BigDecimal valorProvisional = 
                    object[5] == null ? 
                    new BigDecimal("0"): ((BigDecimal) object[5]);
            Timestamp fechaProvisional = 
                    object[6] == null ? null : ((Timestamp) object[6]);
            
            ClientesRating objetoAgregado = new ClientesRating();
            objetoAgregado.setId(String.valueOf(object[0]));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setNombre((String) object[2]);
            objetoAgregado.setGrupo((String) object[3]);
            objetoAgregado.setValorRating(valorProvisional.toString());
            
            if(fechaProvisional != null){
                objetoAgregado.setFechaInsercion(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").format(fechaProvisional));
            } else {
                objetoAgregado.setFechaInsercion("No disponible");
            } 
            listaClientes.add(objetoAgregado);
        }
        return listaClientes;
    }
     
    public List<String> obtenerPeriodos(String tipoConsulta, String parametro){
        String query = "SELECT aniobalance FROM ri.resumeneeff ";
        
        switch(Integer.parseInt(tipoConsulta)){
            case 0:
                query += "WHERE nit = ?";
                break;
                
            case 1:
                System.out.println("case 1");
                query += "WHERE nombreempresa = ?";
                break;
        }
      
        Query q = em.createNativeQuery(query);
        List listaProvisional = q.setParameter(1, parametro).getResultList();
        
        List<String> listaPeriodos = new ArrayList<String>();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
        
            java.sql.Date fechaPeriodo = (java.sql.Date) i.next();
            String dateAgregado = new SimpleDateFormat("yyyy-MM-dd").
                    format(fechaPeriodo);
            
            listaPeriodos.add(dateAgregado);   
        }
        return listaPeriodos;
    }
}