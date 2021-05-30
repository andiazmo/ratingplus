/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import dao.ConnectionFactory;
import entidades.ClientesRating;
import entidades.GruposClientes;
import entidades.Modulo;
import entidades.RatingInfo;
import entidades.Variable;
import entidades.VariablesRating;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public List<GruposClientes> listarGruposCliente(){
        
        List<GruposClientes> listaGrupos = new ArrayList<GruposClientes>();
        GruposClientes objetoAgregado = null;
        
        List listaProvisional = em.createNativeQuery("select codigo_grupo, nombre from grupos_economicos order by codigo_grupo asc").getResultList();
        Iterator i = listaProvisional.iterator();
        System.out.println("Metodo listarGruposCliente:::"+listaProvisional.size());
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new GruposClientes();
            objetoAgregado.setCodigoGrupo(Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setNombreGrupo((String) object[1]);
            listaGrupos.add(objetoAgregado);
        }
        
        return listaGrupos;
        
    }
    
    
    public List<List<VariablesRating>> listarVariablesRating(){
        
        List<List<VariablesRating>> listaVariablesModulo = new ArrayList<List<VariablesRating>>();
        List<VariablesRating> listaVariables = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesFinanciero = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesResultado = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        VariablesRating objetoLista = null;
        BigDecimal valorProvisional = new BigDecimal("0");
        
        //List listaProvisional = em.createNativeQuery("select codigo_grupo, nombre from grupos_economicos order by codigo_grupo asc").getResultList();
        List listaProvisional = em.createNativeQuery("select id, id_modulo, nombre, beta, id_variable from ri.variable_modulo order by id_modulo, id_variable asc").getResultList();
        Iterator i = listaProvisional.iterator();
        System.out.println("Metodo listarVariablesRating:::"+listaProvisional.size());
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new VariablesRating();
            objetoAgregado.setNombre((String) object[2]);
            objetoAgregado.setIdModulo(((Integer)object[1]));
        
            listaVariables.add(objetoAgregado);
        }
        
        System.out.println("Tamaño lista creada:::"+listaVariables.size());
        System.out.println("Nombre:::"+listaVariables.get(0).getNombre());
        System.out.println("IdModulo:::"+listaVariables.get(0).getIdModulo());
        listaVariablesModulo.add(listarVariablesFinanciero(listaVariables));
        System.out.println("Tamaño lista financiera creada:::"+listaVariablesModulo.size());
        listaVariablesModulo.add(listarVariablesComportamiento(listaVariables));
        listaVariablesModulo.add(listarVariablesObjetivo(listaVariables));
        listaVariablesModulo.add(listarVariablesSubjetivo(listaVariables));
        listaVariablesModulo.add(listarVariablesResultado(listaVariables));
        
        return listaVariablesModulo;
    }
    
    
    public List<List<VariablesRating>> listarRespuestasVariablesRating(){
        
        List<List<VariablesRating>> listaRespuestasVariablesModulo = new ArrayList<List<VariablesRating>>();
        List<VariablesRating> listaRespuestasVariables = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        
        List listaProvisional = em.createNativeQuery("select id_modulo, id_variable, respuesta, nombre from ri.informacion_respuestas_variables_rating_vm order by id_modulo, id_variable asc").getResultList();
        Iterator i = listaProvisional.iterator();
        System.out.println("Metodo listarRespuestasVariablesRating:::"+listaProvisional.size());
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new VariablesRating();
            objetoAgregado.setIdModulo(((Integer)object[0]));
        //    objetoAgregado.setIdVariable((int) object[2]);
            objetoAgregado.setRespuesta((String) object[2]);
            objetoAgregado.setNombre((String) object[3]);
            
        
            listaRespuestasVariables.add(objetoAgregado);
        }
        
        
        listaRespuestasVariablesModulo.add(listarRespuestasVariablesComportamiento(listaRespuestasVariables));
        System.out.println("Tamaño lista de respuestas:::"+listaRespuestasVariablesModulo.size());
        
        
        return listaRespuestasVariablesModulo;
    }
    
    
    public List<VariablesRating> listarRespuestasRating(){
        
        List<List<VariablesRating>> listaRespuestasVariablesModulo = new ArrayList<List<VariablesRating>>();
        List<VariablesRating> listaRespuestasVariables = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        
        //List listaProvisional = em.createNativeQuery("select id_modulo, id_variable, respuesta, nombre from ri.informacion_respuestas_variables_rating_vm order by id_modulo, id_variable asc").getResultList();
        List listaProvisional = em.createNativeQuery("select id, id_modulo, nombre_modulo, id_variable, nombre_variable, respuesta, woe, min, max  from ri.informacion_respuestas_variables_rating_woe_vw").getResultList();
        Iterator i = listaProvisional.iterator();
        System.out.println("Metodo listarRespuestasVariablesRating:::"+listaProvisional.size());
        
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            BigDecimal valorProvisional = new BigDecimal("0");
            BigDecimal minProvisional = new BigDecimal("0");
            BigDecimal maxProvisional = new BigDecimal("0");
            valorProvisional = object[6] == null ? new BigDecimal("0"): ((BigDecimal) object[6]);
            minProvisional = object[7] == null ? new BigDecimal("0"): ((BigDecimal) object[7]);
            maxProvisional = object[8] == null ? new BigDecimal("0"): ((BigDecimal) object[8]);
            
            objetoAgregado = new VariablesRating();
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
        
        
        listaRespuestasVariablesModulo.add(listarRespuestasVariablesComportamiento(listaRespuestasVariables));
        System.out.println("Tamaño lista de respuestas:::"+listaRespuestasVariables.size());
        
        
        return listaRespuestasVariables;
    }
    
    public boolean updateVariableValues(BigDecimal valorRatingAnterior, BigDecimal valorRatingDefinitivo, BigDecimal id, String comentario){
    
      //  Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating = ?, valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        return  q.setParameter(1, valorRatingDefinitivo).setParameter(2, comentario).setParameter(3, id).executeUpdate() != 0;
        
    }
    
    public List<VariablesRating> listarVariablesFinanciero(List<VariablesRating> listaVariables){
        
        List<VariablesRating> listaVariablesFinanciero = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        System.out.println("Tamaño lista financiero:::"+listaVariables.size());
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 1) {
                objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                System.out.println("IdModulo:::"+listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                System.out.println("Nombre:::"+listaVariables.get(i).getNombre());
                listaVariablesFinanciero.add(objetoAgregado);
            }
            
        }
        
        System.out.println("Tamaño lista financiero:::"+listaVariablesFinanciero.size());
        return listaVariablesFinanciero;
        
    }
    
    
    public List<VariablesRating> listarRespuestasVariablesFinanciero(List<VariablesRating> listaRespuestaVariables){
        
        List<VariablesRating> listaRespuestaVariablesFinanciero = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        System.out.println("Tamaño lista financiero:::"+listaRespuestaVariables.size());
        for (int i = 0; i < listaRespuestaVariables.size(); i++) {
            if (listaRespuestaVariables.get(i).getIdModulo() == 1) {
                objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaRespuestaVariables.get(i).getIdModulo());
                System.out.println("IdModulo:::"+listaRespuestaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaRespuestaVariables.get(i).getNombre());
                System.out.println("Nombre:::"+listaRespuestaVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaRespuestaVariables.get(i).getRespuesta());
                listaRespuestaVariablesFinanciero.add(objetoAgregado);
            }
            
        }
        
        System.out.println("Tamaño lista financiero:::"+listaRespuestaVariablesFinanciero.size());
        return listaRespuestaVariablesFinanciero;
        
    }
    
    public List<VariablesRating> listarVariablesComportamiento(List<VariablesRating> listaVariables){
        
        List<VariablesRating> listaVariablesComportamiento = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 2) {
                objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaVariables.get(i).getRespuesta());
                listaVariablesComportamiento.add(objetoAgregado);
            }
            
        }
        return listaVariablesComportamiento;
        
    }
    
    public List<VariablesRating> listarRespuestasVariablesComportamiento(List<VariablesRating> listaRespuestasVariables){
        
        List<VariablesRating> listaRespuestasVariablesComportamiento = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 2) {
                objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).getRespuesta());
                System.out.println("Respuesta Agregada:::"+listaRespuestasVariables.get(i).getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesComportamiento.add(objetoAgregado);
            }
            
        }
        return listaRespuestasVariablesComportamiento;
        
    }
    
    
    
    
    public List<VariablesRating> listarVariablesObjetivo(List<VariablesRating> listaVariables){
        
        List<VariablesRating> listaVariablesObjetivo = new ArrayList<VariablesRating>();
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
        
        List<VariablesRating> listaVariablesSubjetivo = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 4) {
                objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                
                
                listaVariablesSubjetivo.add(objetoAgregado);
            }
            
        }
        return listaVariablesSubjetivo;
        
    }
    
    
    public List<VariablesRating> listarVariablesResultado(List<VariablesRating> listaVariables){
        
        List<VariablesRating> listaVariablesResultado = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        for (int i = 0; i < listaVariables.size(); i++) {
            if (listaVariables.get(i).getIdModulo() == 5) {
                objetoAgregado = new VariablesRating();
                objetoAgregado.setIdModulo(listaVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaVariables.get(i).getNombre());
                
                
                listaVariablesResultado.add(objetoAgregado);
            }
            
        }
        System.out.println("Tamaño lista variables resultado:::"+listaVariablesResultado.size());
        listaVariablesResultado.get(0).getNombre();
        return listaVariablesResultado;
        
    }
    
    
    
    public List<Modulo> listarModulo(){
        
        List<Modulo> listaModulos = new ArrayList<Modulo>();
        Modulo objetoAgregado = null;
        
        //List listaProvisional = em.createNativeQuery("select codigo_grupo, nombre from grupos_economicos order by codigo_grupo asc").getResultList();
        List listaProvisional = em.createNativeQuery("select id, nombre from ri.modulo order by id asc").getResultList();
        Iterator i = listaProvisional.iterator();
        System.out.println("Metodo listarModulo:::"+listaProvisional.size());
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new Modulo();
            objetoAgregado.setNombre((String) object[1]);
        
            listaModulos.add(objetoAgregado);
        }
       
        
        return listaModulos;
        
    }
    
    public List<ClientesRating> consultaClientes(String tipoConsulta, String parametro, String periodoFiscal){
        
        System.out.println("Metodo consultaClientes:::");
        System.out.println("tipoConsulta:::"+tipoConsulta);
        System.out.println("parametro:::"+parametro);
        System.out.println("periodoFiscal:::"+periodoFiscal);
      //  String query = "SELECT id, nit, razon_social, codigo_grupo, nombre_grupo, valor_rating, fecha_reporte FROM ri.Informacion_Clientes_Rating_VW WHERE periodo_fiscal = TO_DATE(?,'YYYY-MM-DD') AND";
       // String query = "SELECT id_resumeneeff, nit, nombreempresa, rating, fechainsercion FROM ri.resumeneeff WHERE aniobalance = TO_DATE(?,'YYYY-MM-DD') AND";
        String query = "SELECT id, nit, razon_social, rating, fecha_calculo, grupo FROM ri.informacion_clientes_rating_front_vw WHERE";
        List listaProvisional;
        List<ClientesRating> listaClientes = new ArrayList<ClientesRating>();
        ClientesRating objetoAgregado = null;
        Iterator i;
        Iterator j;
        BigDecimal valorProvisional = new BigDecimal("0");
        Timestamp fechaProvisional = null;
        
        switch(Integer.parseInt(tipoConsulta)){
            
            case 0:
                query += " nit = ?";
                break;
            
            case 1:
                query += " razon_social = ?";
               // query += " nombreempresa = ?";
                break;
            
        }
        
      //  query += " ORDER BY fecha_reporte desc LIMIT 1";
      //  query += " ORDER BY fechainsercion desc LIMIT 1";
        query += " ORDER BY fecha_calculo desc LIMIT 1";
        System.out.println("query:::"+query);
      //  listaProvisional = em.createNativeQuery(query).setParameter(1, periodoFiscal).setParameter(2, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        listaProvisional = em.createNativeQuery(query).setParameter(1, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        
        System.out.println("Tamaño lista Inicial:::"+listaProvisional.size());
        
        if (listaProvisional.isEmpty()) {
            //No tiene registros en la tabla resultados
           // listaClientes = consultaClienteGrupo(tipoConsulta,parametro);
          //  System.out.println("Tamaño lista final:::"+listaClientes.size());
            
            
            return null;
            
        }
        
        i = listaProvisional.iterator();
        
        while(i.hasNext()){
            
            Object[] object = (Object[]) i.next();
            
        //    valorProvisional = object[5] == null ? new BigDecimal("0"): ((BigDecimal) object[5]);
        //    fechaProvisional = object[6] == null ? null : ((Timestamp) object[6]);
            
            valorProvisional = object[3] == null ? new BigDecimal("0"): ((BigDecimal) object[3]);
            fechaProvisional = object[4] == null ? null : ((Timestamp) object[4]);
            
            objetoAgregado = new ClientesRating();
//            String test = (String) object[0];
 //           System.out.println("test object:::"+test);
      //      objetoAgregado.setId((String) object[0]);
            objetoAgregado.setId(String.valueOf(object[0]));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setNombre((String) object[2]);
        //    objetoAgregado.setIdGrupo(Integer.parseInt(((BigDecimal) object[3]).toString()));
            objetoAgregado.setGrupo((String) object[5]);
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
    
    
    public List<ClientesRating> consultaClientesGrupo(String tipoConsulta, String parametro){
        
        System.out.println("Metodo consultaClientes:::");
        System.out.println("tipoConsulta:::"+tipoConsulta);
        System.out.println("parametro:::"+parametro);
      //  String query = "SELECT id, nit, razon_social, codigo_grupo, nombre_grupo, valor_rating, fecha_reporte FROM ri.Informacion_Clientes_Rating_VW WHERE periodo_fiscal = TO_DATE(?,'YYYY-MM-DD') AND";
       // String query = "SELECT id_resumeneeff, nit, nombreempresa, rating, fechainsercion FROM ri.resumeneeff WHERE aniobalance = TO_DATE(?,'YYYY-MM-DD') AND";
        String query = "SELECT id, nit, razon_social, rating, fecha_calculo, grupo FROM ri.informacion_clientes_rating_front_vw WHERE";
        List listaProvisional;
        List<ClientesRating> listaClientes = new ArrayList<ClientesRating>();
        ClientesRating objetoAgregado = null;
        Iterator i;
        Iterator j;
        BigDecimal valorProvisional = new BigDecimal("0");
        Timestamp fechaProvisional = null;
        
        switch(Integer.parseInt(tipoConsulta)){
            
            case 0:
                query += " nit = ?";
                break;
            
            case 1:
                query += " razon_social = ?";
               // query += " nombreempresa = ?";
                break;
            
        }
        
      //  query += " ORDER BY fecha_reporte desc LIMIT 1";
      //  query += " ORDER BY fechainsercion desc LIMIT 1";
        query += " ORDER BY fecha_calculo desc LIMIT 1";
        System.out.println("query:::"+query);
      //  listaProvisional = em.createNativeQuery(query).setParameter(1, periodoFiscal).setParameter(2, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        listaProvisional = em.createNativeQuery(query).setParameter(1, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        
        System.out.println("Tamaño lista Inicial:::"+listaProvisional.size());
        
        if (listaProvisional.isEmpty()) {
            //No tiene registros en la tabla resultados
//            listaClientes = consultaClienteResumen(tipoConsulta,parametro);
 //           System.out.println("Tamaño lista final:::"+listaClientes.size());
            
            
            return null;
            
        }
        
        i = listaProvisional.iterator();
        
        while(i.hasNext()){
            
            Object[] object = (Object[]) i.next();
            
        //    valorProvisional = object[5] == null ? new BigDecimal("0"): ((BigDecimal) object[5]);
        //    fechaProvisional = object[6] == null ? null : ((Timestamp) object[6]);
            
            valorProvisional = object[3] == null ? new BigDecimal("0"): ((BigDecimal) object[3]);
            fechaProvisional = object[4] == null ? null : ((Timestamp) object[4]);
            
            objetoAgregado = new ClientesRating();
//            String test = (String) object[0];
 //           System.out.println("test object:::"+test);
      //      objetoAgregado.setId((String) object[0]);
            objetoAgregado.setId(String.valueOf(object[0]));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setNombre((String) object[2]);
        //    objetoAgregado.setIdGrupo(Integer.parseInt(((BigDecimal) object[3]).toString()));
            objetoAgregado.setGrupo((String) object[5]);
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
    
        
    public BigDecimal retrieveDataNumeric(Object validate){
    
        return validate == null ? new BigDecimal("0") : (BigDecimal) validate;
        
    }
    
   
    public String retrieveData(Object validate){
    
        return validate == null ? "" : validate.toString();
        
    }
    
    
    
    public boolean updateVarablesRespuesta(List<VariablesRating> listaRespuestas){
    
 //       Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        Query q = em.createNativeQuery("UPDATE ri.respuesta SET woe = ?, min = ?, max = ? WHERE id = ?");
        
        for (int i = 0; i < listaRespuestas.size(); i++) {
            System.out.println("Id en el facade:::"+listaRespuestas.get(i).getId());
            BigDecimal woe = new BigDecimal(listaRespuestas.get(i).getWoe());
            BigDecimal min = new BigDecimal(listaRespuestas.get(i).getMin());
            BigDecimal max = new BigDecimal(listaRespuestas.get(i).getMax());
            System.out.println("Update en el facade:::"+q.setParameter(1, woe).setParameter(2, min).setParameter(3, max).setParameter(4, (Integer)listaRespuestas.get(i).getId()).executeUpdate());
        }
        
        
  //      return  q.setParameter(1, valorRatingDefinitivo).setParameter(2, comentario).setParameter(3, id).executeUpdate() != 0;
//        
          return true;
    }
    
    public boolean confirmarRating(BigDecimal valorRatingAnterior, BigDecimal valorRatingDefinitivo, BigDecimal id, String comentario){
    
      //  Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating = ?, valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        return  q.setParameter(1, valorRatingDefinitivo).setParameter(2, comentario).setParameter(3, id).executeUpdate() != 0;
        
    }
    
    
    public boolean registrarComportamiento(String respCalificacion, String respGarantia, String indMora, String numeroBancos, String marcacionReestructuracion, String id_cliente, String usuario){
    
    
        Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        //return  q.setParameter(1, valorRatingDefinitivo).setParameter(2, comentario).setParameter(3, id).executeUpdate() != 0;
        Query q1 = em.createNativeQuery("INSERT INTO ri.modulo_comportamiento(id, calificacion, garantia, indicador_mora, numero_bancos, marcacion_reestructuracion, id_cliente, usuario, fecha_insercion) VALUES ((SELECT MAX(id) from ri.modulo_comportamiento)+ 1, ?, ?, ?, ?, ?, ?, ?, (SELECT now()::timestamp))");
   
        return q1.setParameter(1, respCalificacion).setParameter(2, respGarantia).setParameter(3, indMora).setParameter(4, numeroBancos).setParameter(5, marcacionReestructuracion).setParameter(6,id_cliente).setParameter(7, usuario).executeUpdate() != 0;
        
    }
    
}