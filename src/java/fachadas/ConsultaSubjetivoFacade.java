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
public class ConsultaSubjetivoFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaSubjetivoFacade() {
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
        
        
        listaRespuestasVariablesModulo.add(listarRespuestasVariablesSubjetivo(listaRespuestasVariables));
        System.out.println("Tamaño lista de respuestas:::"+listaRespuestasVariablesModulo.size());
        
        
        return listaRespuestasVariablesModulo;
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
    
    public List<VariablesRating> listarRespuestasVariablesObjetivo(List<VariablesRating> listaRespuestasVariables){
        
        List<VariablesRating> listaRespuestasVariablesObjetivo = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 3) {
                objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).getRespuesta());
                System.out.println("Respuesta Agregada:::"+listaRespuestasVariables.get(i).getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesObjetivo.add(objetoAgregado);
            }
            
        }
        return listaRespuestasVariablesObjetivo;
        
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
    
     public List<VariablesRating> listarRespuestasVariablesSubjetivo(List<VariablesRating> listaRespuestasVariables){
        
        List<VariablesRating> listaRespuestasVariablesSubjetivo = new ArrayList<VariablesRating>();
        VariablesRating objetoAgregado = null;
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 4) {
                objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).getRespuesta());
                System.out.println("Respuesta Agregada:::"+listaRespuestasVariables.get(i).getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesSubjetivo.add(objetoAgregado);
            }
            
        }
        return listaRespuestasVariablesSubjetivo;
        
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
        System.out.println("Metodo listarVariablesRating:::"+listaProvisional.size());
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new Modulo();
            objetoAgregado.setNombre((String) object[1]);
        
            listaModulos.add(objetoAgregado);
        }
       
        
        return listaModulos;
        
    }
    
    public List<ClientesRating> consultaClientes(String tipoConsulta, String parametro, String periodoFiscal){
        
        System.out.println("Metodo consultaClientes en el facade:::");
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
//        int parametroConvertido = Integer.parseInt(parametro);
//        System.out.println("parametroConvertido:::"+parametroConvertido);
      //  listaProvisional = em.createNativeQuery(query).setParameter(1, periodoFiscal).setParameter(2, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
      //  listaProvisional = em.createNativeQuery(query).setParameter(1, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        listaProvisional = em.createNativeQuery(query).setParameter(1, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        
        System.out.println("Tamaño lista Inicial:::"+listaProvisional.size());
        //Significa que no se le ha hecho calculo del rating aun
        
        if (listaProvisional.isEmpty()) {
            //No tiene registros en la tabla resultados
            
            listaClientes = consultaClienteGrupo(tipoConsulta,parametro);
            System.out.println("Tamaño lista final:::"+listaClientes.size());
            
            
            
            
            
            return listaClientes;
            
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
    
    
    public List<ClientesRating> consultaClienteGrupoResultadoRating(String tipoConsulta, String parametro, String periodoFiscal){
        
        System.out.println("Metodo consultaClienteGrupoResultadoRating en el facade:::");
        System.out.println("tipoConsulta:::"+tipoConsulta);
        System.out.println("parametro:::"+parametro);
        System.out.println("periodoFiscal:::"+periodoFiscal);
        String query = "SELECT id, nit, valor_rating, valor_rating_final, razon_social, nombre_grupo, fecha_calculo FROM ri.informacion_cliente_grupo_resultado_rating_vw WHERE ";
        List listaProvisional;
        List<ClientesRating> listaClientes = new ArrayList<ClientesRating>();
        ClientesRating objetoAgregado = null;
        Iterator i;
        Iterator j;
        BigDecimal valorProvisional = new BigDecimal("0");
        BigDecimal valorProvisionalId = new BigDecimal("0");
        Timestamp fechaProvisional = null;
        String nombreProvisional = null;
        String grupoProvisional = null;
        
        switch(Integer.parseInt(tipoConsulta)){
            
            case 0:
                query += " nit = ?";
                break;
            
            case 1:
                query += " razon_social = ?";
                break;
            
        }
        
        query += " ORDER BY fecha_calculo desc LIMIT 1";
        System.out.println("query:::"+query);
        listaProvisional = em.createNativeQuery(query).setParameter(1, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        
        System.out.println("Tamaño lista Inicial:::"+listaProvisional.size());
        //Significa que no se le ha hecho calculo del rating aun
        
//        if (listaProvisional.isEmpty()) {
//            //No tiene registros en la tabla resultados
//            System.out.println("Tamaño lista final:::"+listaClientes.size());
//        
//            return listaClientes;
//            
//        }
        
        i = listaProvisional.iterator();
        
        while(i.hasNext()){
            
            Object[] object = (Object[]) i.next();
            valorProvisional = object[3] == null ? new BigDecimal("0"): ((BigDecimal) object[3]);
            fechaProvisional = object[6] == null ? null : ((Timestamp) object[6]);
            
            objetoAgregado = new ClientesRating();
            objetoAgregado.setId(String.valueOf(object[0]));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setValorRating(valorProvisional.toString());
            objetoAgregado.setNombre((String) object[4]);
            objetoAgregado.setGrupo((String) object[5]);
            if(fechaProvisional != null){
                objetoAgregado.setFechaInsercion(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").format(fechaProvisional));
            } else {
                objetoAgregado.setFechaInsercion("No disponible");
            } 
            
            listaClientes.add(objetoAgregado);
        }
        
        System.out.println("Lista a retornar:::"+listaClientes.size());
        return listaClientes;

    }
    
    public List<ClientesRating> consultaClienteGrupoResultadoSinResRating(String tipoConsulta, String parametro){
        
        System.out.println("Metodo consultaClienteGrupoResultadoRating en el facade:::");
        System.out.println("tipoConsulta:::"+tipoConsulta);
        System.out.println("parametro:::"+parametro);
     //   String query = "SELECT id, nit, nombre, nombre_grupo, valor_rating, valor_rating_final, fecha_insercion FROM ri.informacion_cliente_grupo_resultado_sinref_rating_vw WHERE ";
        String query = "SELECT id, nit, nombre, nombre_grupo, valor_rating, valor_rating_final, num_caso, estado, fecha_insercion FROM ri.informacion_cliente_grupo_rating_vw WHERE ";
        List listaProvisional;
        List<ClientesRating> listaClientes = new ArrayList<ClientesRating>();
        ClientesRating objetoAgregado = null;
        Iterator i;
        Iterator j;
        BigDecimal valorProvisional = new BigDecimal("0");
        BigDecimal valorProvisionalId = new BigDecimal("0");
        Timestamp fechaProvisional = null;
        String nombreProvisional = null;
        String grupoProvisional = null;
        
        switch(Integer.parseInt(tipoConsulta)){
            
            case 0:
                query += " nit = ?";
                break;
            
            case 1:
                query += " nombre = ?";
                break;
            
        }
        
        query += " ORDER BY fecha_insercion desc LIMIT 1";
        System.out.println("query:::"+query);
        listaProvisional = em.createNativeQuery(query).setParameter(1, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        
        System.out.println("Tamaño lista Inicial:::"+listaProvisional.size());
        
        i = listaProvisional.iterator();
        
        while(i.hasNext()){
            
            Object[] object = (Object[]) i.next();
            valorProvisional = object[5] == null ? new BigDecimal("0"): ((BigDecimal) object[5]);
            fechaProvisional = object[8] == null ? null : ((Timestamp) object[8]);
            
            objetoAgregado = new ClientesRating();
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
        
        System.out.println("Lista a retornar:::"+listaClientes.size());
        return listaClientes;

    }
    
    
    public List<ClientesRating> consultaClientesGrupo(String tipoConsulta, String parametro){
        
        System.out.println("Metodo consultaClientesGrupo 11:::");
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
            listaClientes = consultaClienteResumen(tipoConsulta,parametro);
            System.out.println("Tamaño lista final:::"+listaClientes.size());
            
            
            return listaClientes;
            
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
    
    
    public List<ClientesRating> consultaClienteGrupoResumen(String tipoConsulta, String parametro){
        System.out.println("Metodo consultaClienteGrupo");
        System.out.println("tipoConsulta:::"+tipoConsulta);
        System.out.println("parametro:::"+parametro);
        //ri.informacion_clientes_rating_vw
        
        
        
        
        
        
        return null;
    }
        
    public List<ClientesRating> consultaClienteGrupo(String tipoConsulta, String parametro){
        
        System.out.println("Metodo consultaClienteGrupo");
        System.out.println("tipoConsulta:::"+tipoConsulta);
        System.out.println("parametro:::"+parametro);
        String param = parametro.trim();
        
        String fecha = "2020-12-31";
        
     //   String query = "select r.nit, r.aniobalance , r.rating , ge.nombre from clientes c, ri.resumeneeff r , grupos_economicos ge where c.nit = r.nit and c.grupo = ge.codigo_grupo and r.nit = ?  and r.aniobalance = TO_DATE(?,'YYYY-MM-DD')";
       
        String query = "select r.nit, r.aniobalance , r.rating , ge.nombre from clientes c, ri.resumeneeff r , grupos_economicos ge where c.nit = r.nit and c.grupo = ge.codigo_grupo and r.nit = ?  and r.aniobalance = TO_DATE(?,'YYYY-MM-DD')";
        List listaProvisional;
        listaProvisional = em.createNativeQuery(query).setParameter(1, parametro).setParameter(2, tipoConsulta.equals("2") ? Long.parseLong(parametro) : parametro).setParameter(3, fecha).getResultList();
      //  listaProvisional = em.createNativeQuery(query).setParameter(1, parametro).setParameter(2, Long.parseLong(param)).setParameter(3, fecha).getResultList();
        System.out.println("Tamaño lista Grupo:::"+listaProvisional.size());
        
        if (listaProvisional.isEmpty()) {
            //No tiene registros en la tabla resultados
            listaProvisional = consultaClienteResumen(tipoConsulta,parametro);
        }
        return listaProvisional;
    }
    
    
    public List<ClientesRating> consultaClienteGrupo1(String tipoConsulta, String parametro){
        
        System.out.println("");
        
        String query = "select r.nit, r.aniobalance , r.rating , ge.nombre from clientes c, ri.resumeneeff r , grupos_economicos ge where c.nit = r.nit and c.grupo = ge.codigo_grupo and r.nit = ?  and r.aniobalance = TO_DATE(?,'YYYY-MM-DD')";
       
        List listaProvisional;
        listaProvisional = em.createNativeQuery(query).setParameter(1, parametro).setParameter(2, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        
        System.out.println("Tamaño lista Grupo:::"+listaProvisional.size());
        
        if (listaProvisional.isEmpty()) {
            //No tiene registros en la tabla resultados
            listaProvisional = consultaClienteResumen(tipoConsulta,parametro);
        }
        return listaProvisional;
    }
    
    
    public List<ClientesRating> consultaClienteResumen(String tipoConsulta, String parametro){
      //  String query = "SELECT id_resumeneeff, nit, nombreempresa, rating  FROM ri.resumeneeff WHERE nit = ? AND aniobalance = TO_DATE(?,'YYYY-MM-DD')";
        String query = "select c.id, c.nit, c.nombre, c.grupo, c.rating, c.fecharating, ge.nombre from clientes c, grupos_economicos ge where c.grupo = ge.codigo_grupo and";
        List listaProvisional;
//        listaProvisional = em.createNativeQuery(query).setParameter(1, parametro).setParameter(2, periodoFiscal).getResultList();
        List<ClientesRating> listaClientes = new ArrayList<ClientesRating>();
        ClientesRating objetoAgregado = null;
        BigDecimal valorProvisional = new BigDecimal("0");
        Integer grupo = 0;
        Iterator i;
        
        switch(Integer.parseInt(query)){
            case 0:
                query += "c.nit =";
                break;
            case 1:
                query += "c.nomnre =";
                break;
        }
        
        query += "ORDER BY c.id desc LIMIT 1";
        listaProvisional = em.createNativeQuery(query).setParameter(1, parametro).getResultList();
        System.out.println("Tamaño lista resumen:::"+listaProvisional.size());
        
        i = listaProvisional.iterator();
        
         while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new ClientesRating();
            valorProvisional = object[4] == null ? new BigDecimal("0"): ((BigDecimal) object[4]);
            grupo = (Integer)object[3]; 
            
            objetoAgregado.setId("No tiene");
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setNombre((String) object[2]);
           // objetoAgregado.setIdGrupo(0);
            objetoAgregado.setIdGrupo(grupo);
            objetoAgregado.setGrupo((String)object[4]);
            objetoAgregado.setValorRating(valorProvisional.toString());
            objetoAgregado.setFechaInsercion(object[5].toString());
            
            listaClientes.add(objetoAgregado);
         }
        
        return listaClientes;
    }     
    
    public List<List<VariablesRating>> calcularRating(String[] listaNits, String periodo, String usuario) throws SQLException{
        
        Connection conn = new ConnectionFactory().getConnection();
        System.out.println("Inputs::: "+ listaNits[0] + " " + usuario + " " + periodo);
        List<RatingInfo> listaRating = new ArrayList<RatingInfo>();
        List<List<VariablesRating>> listVariablesRating = new ArrayList<List<VariablesRating>>();
        List<VariablesRating> listaVariablesFinanciero = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesComportamiento = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesObjetivo = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesSubjetivo = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariables = new ArrayList<VariablesRating>();
        List<List<VariablesRating>> listVariablesRatingTotal = new ArrayList<List<VariablesRating>>();
        List<VariablesRating> listaVariablesComportamientoFinal = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesObjetivoFinal = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesSubjetivoFinal = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesResultado = new ArrayList<VariablesRating>();
        List<VariablesRating> listaVariablesResultadoFinal = new ArrayList<VariablesRating>();

        listVariablesRating = listarVariablesRating();
        
        System.out.println("Tamaño lista variables rating:::"+listVariablesRating.size());
        System.out.println("Usuario en el facade:::"+usuario);
        System.out.println("Nit:::"+listaNits[0]);
        System.out.println("Periodo:::"+periodo);
        List listaProvisional;
        if (periodo == null) {
            System.out.println("Invocando la funcion para calcular el rating sin periodo:::");
            Query query = em.createNativeQuery("SELECT ri.calcular_rating_sinfin_fn(?,?)");
            //Query query = em.createNativeQuery("SELECT ri.calcular_rating_fn(?,?,?)");
            listaProvisional = query.setParameter(1, conn.createArrayOf("text", listaNits)).setParameter(2, usuario).getResultList();
            
            System.out.println("Lista Provisional Sin fin:::"+listaProvisional.size());
        }
        
        else {
            System.out.println("Va a calcular el rating con fecha");
            Query q = em.createNativeQuery("SELECT ri.calcular_rating_fn(?,?,TO_DATE(?,'YYYY-MM-DD'))");
            listaProvisional = q.setParameter(1, conn.createArrayOf("text", listaNits)).setParameter(2, usuario).setParameter(3, periodo).getResultList();
        }
        System.out.println("listaProvisional Rating:::"+listaProvisional.get(0).toString());
        RatingInfo objetoAgregado = null;
        Variable objetoVariable = null;
        VariablesRating variableNit = null;
        VariablesRating variableApalancamiento = null;
        VariablesRating variableEbitda = null;
        VariablesRating variableCfl = null;
        VariablesRating variableLiquidez = null;
        VariablesRating variableCfo = null;
        VariablesRating variableMargenEbitda = null;
        VariablesRating variableRotacionClientesDias = null;
        VariablesRating variableRotacionInventariosDias = null;
        VariablesRating variableRotacionProveedoresDias = null;
        VariablesRating variableCalificacion = null;
        VariablesRating variableGarantia = null;
        VariablesRating variableIndicadorMora = null;
        VariablesRating variableNumeroBancos = null;
        VariablesRating variableMarcacionReestructuracion = null;
        VariablesRating variableEvolucion = null;
        VariablesRating variablePosicion = null;
        VariablesRating variableDependencia = null;
        VariablesRating variableProveedor = null;
        VariablesRating variableCapacidad = null;
        VariablesRating variableGarantias = null;
        VariablesRating variableCalidad = null;
        VariablesRating variableInforme = null;
        VariablesRating variableTipoProductoServicio = null;
        VariablesRating variableGerenciaCapcidadProfesionalidadExperiencia = null;
        VariablesRating varaibleAbanicoBancario = null;
        VariablesRating variableMecanismosFinanciacion = null;
        VariablesRating variableEstrcuturaCostos = null;
        VariablesRating variableCapacidadAtenderCalendario = null;
        VariablesRating variableGradoAutofinanciacion = null;
        VariablesRating variableExistenciaDeudasCompromisos = null;
        VariablesRating variablePerfilPagoDeuda = null;
        VariablesRating variableCalidadActivos = null;
        VariablesRating variableTipoAccionista = null;
        VariablesRating variableRating = null;
        VariablesRating variableId = null;
        
        System.out.println("Usuario:::"+usuario);
        
        Query q1 = em.createNativeQuery("SELECT * FROM ri.Informacion_Rating_Plus_VW WHERE usuario = ? AND nit = ANY(?) AND id IN (SELECT MAX(id) FROM ri.Informacion_Rating_Plus_VW WHERE nit = ANY(?) GROUP BY nit)");
        
        List listaTemporal = q1.setParameter(1, usuario).setParameter(2, conn.createArrayOf("text", listaNits)).setParameter(3, conn.createArrayOf("text", listaNits)).getResultList();
        List listaTemporalResp = em.createNativeQuery("select * from ri.resultados_rating_plus order by id desc limit 1").getResultList();
        System.out.println("Tamaño lista de respuestas:::"+listaTemporalResp.size());
       
        listaVariablesFinanciero = listVariablesRating.get(0);
        listaVariablesComportamiento = listVariablesRating.get(1);
        listaVariablesObjetivo = listVariablesRating.get(2);
        listaVariablesSubjetivo = listVariablesRating.get(3);
        listaVariablesResultado = listVariablesRating.get(4);
       
        System.out.println("Tamaño lista variables resultado:::"+listaVariablesResultado.size());
     
        Iterator j = listaTemporalResp.iterator();
       
        while(j.hasNext()){
            Object[] object = (Object[]) j.next();
            objetoAgregado = new RatingInfo();
           
            String nit = this.retrieveData(object[1]);
            System.out.println("Nit::"+nit);
            String nombre = listaVariablesFinanciero.get(0).getNombre();
            System.out.println("Nombre::"+nombre);
           
            variableNit = new VariablesRating(listaVariablesFinanciero.get(0).getNombre(), 1, this.retrieveData(object[1]));
            variableApalancamiento = new VariablesRating(listaVariablesFinanciero.get(1).getNombre(), 1, this.retrieveData(object[69]));
            variableEbitda = new VariablesRating(listaVariablesFinanciero.get(2).getNombre(), 1, this.retrieveData(object[70]));
            System.out.println("variableEbitda:::"+variableEbitda.toString());
            variableCfl = new VariablesRating(listaVariablesFinanciero.get(3).getNombre(), 1, this.retrieveData(object[71]));
            variableLiquidez = new VariablesRating(listaVariablesFinanciero.get(4).getNombre(), 1, this.retrieveData(object[72]));
            variableCfo = new VariablesRating(listaVariablesFinanciero.get(5).getNombre(), 1, this.retrieveData(object[73]));
            variableMargenEbitda = new VariablesRating(listaVariablesFinanciero.get(6).getNombre(), 1, this.retrieveData(object[74]));
            variableRotacionClientesDias = new VariablesRating(listaVariablesFinanciero.get(7).getNombre(), 1, this.retrieveData(object[75]));
            variableRotacionInventariosDias = new VariablesRating(listaVariablesFinanciero.get(8).getNombre(), 1, this.retrieveData(object[76]));
            variableRotacionProveedoresDias = new VariablesRating(listaVariablesFinanciero.get(9).getNombre(), 1, this.retrieveData(object[77]));
            variableRating = new VariablesRating(listaVariablesResultado.get(0).getNombre(), 1, this.retrieveData(object[2]));
            System.out.println("Variable rating:::"+variableRating.getNombre() + " " +variableRating.getValue());
            variableId = new VariablesRating(listaVariablesResultado.get(0).getNombre(), 1, this.retrieveData(object[0]));
            System.out.println("Variable Id:::"+variableRating.getNombre() + " " +variableId.getValue());
           
            variableEvolucion = new VariablesRating(listaVariablesObjetivo.get(1).getNombre(), 3, this.retrieveData(object[50]));
            variablePosicion = new VariablesRating(listaVariablesObjetivo.get(2).getNombre(), 3, this.retrieveData(object[51]));
            variableDependencia = new VariablesRating(listaVariablesObjetivo.get(3).getNombre(), 3, this.retrieveData(object[52]));
            variableProveedor = new VariablesRating(listaVariablesObjetivo.get(4).getNombre(), 3, this.retrieveData(object[53]));
            variableCapacidad = new VariablesRating(listaVariablesObjetivo.get(5).getNombre(), 3, this.retrieveData(object[54]));
            variableGarantias = new VariablesRating(listaVariablesObjetivo.get(6).getNombre(), 3, this.retrieveData(object[55]));
            variableCalidad = new VariablesRating(listaVariablesObjetivo.get(7).getNombre(), 3, this.retrieveData(object[56]));
            variableInforme = new VariablesRating(listaVariablesObjetivo.get(8).getNombre(), 3, this.retrieveData(object[57]));
           
            variableTipoProductoServicio = new VariablesRating(listaVariablesSubjetivo.get(1).getNombre(), 1, this.retrieveData(object[58]));
            variableGerenciaCapcidadProfesionalidadExperiencia = new VariablesRating(listaVariablesSubjetivo.get(2).getNombre(), 1, this.retrieveData(object[59]));
            varaibleAbanicoBancario = new VariablesRating(listaVariablesSubjetivo.get(3).getNombre(), 1, this.retrieveData(object[60]));
            variableMecanismosFinanciacion = new VariablesRating(listaVariablesSubjetivo.get(4).getNombre(), 1, this.retrieveData(object[61]));
            variableEstrcuturaCostos = new VariablesRating(listaVariablesSubjetivo.get(5).getNombre(), 1, this.retrieveData(object[62]));
            variableCapacidadAtenderCalendario = new VariablesRating(listaVariablesSubjetivo.get(6).getNombre(), 1, this.retrieveData(object[63]));
            variableGradoAutofinanciacion = new VariablesRating(listaVariablesSubjetivo.get(7).getNombre(), 1, this.retrieveData(object[64]));
            variableExistenciaDeudasCompromisos = new VariablesRating(listaVariablesSubjetivo.get(8).getNombre(), 1, this.retrieveData(object[65]));
            variablePerfilPagoDeuda = new VariablesRating(listaVariablesSubjetivo.get(9).getNombre(), 1, this.retrieveData(object[66]));
            variableCalidadActivos = new VariablesRating(listaVariablesSubjetivo.get(10).getNombre(), 1, this.retrieveData(object[67]));
            variableTipoAccionista = new VariablesRating(listaVariablesSubjetivo.get(11).getNombre(), 1, this.retrieveData(object[68]));
          
            listaVariablesSubjetivoFinal.add(variableTipoProductoServicio);
            listaVariablesSubjetivoFinal.add(variableGerenciaCapcidadProfesionalidadExperiencia);
            listaVariablesSubjetivoFinal.add(varaibleAbanicoBancario);
            listaVariablesSubjetivoFinal.add(variableMecanismosFinanciacion);
            listaVariablesSubjetivoFinal.add(variableEstrcuturaCostos);
            listaVariablesSubjetivoFinal.add(variableCapacidadAtenderCalendario);
            listaVariablesSubjetivoFinal.add(variableGradoAutofinanciacion);
            listaVariablesSubjetivoFinal.add(variableExistenciaDeudasCompromisos);
            listaVariablesSubjetivoFinal.add(variablePerfilPagoDeuda);
            listaVariablesSubjetivoFinal.add(variableCalidadActivos);
            listaVariablesSubjetivoFinal.add(variableTipoAccionista);
           
            System.out.println("variableNit:::"+variableNit);
            System.out.println("variableApalancamienro:::"+variableNit);
            System.out.println("variableEbitda:::"+variableEbitda);
            System.out.println("variableCfl:::"+variableCfl);
           
            listaVariables.add(variableNit);
            listaVariables.add(variableApalancamiento);
            listaVariables.add(variableEbitda);
            listaVariables.add(variableLiquidez);
            listaVariables.add(variableCfl);
            listaVariables.add(variableCfo);
            listaVariables.add(variableMargenEbitda);
            listaVariables.add(variableRotacionClientesDias);
            listaVariables.add(variableRotacionInventariosDias);
            listaVariables.add(variableRotacionProveedoresDias);
           
            variableCalificacion = new VariablesRating(listaVariablesComportamiento.get(0).getNombre(), 2, this.retrieveData(object[45]));
            variableGarantia = new VariablesRating(listaVariablesComportamiento.get(1).getNombre(), 2, this.retrieveData(object[46]));
            variableIndicadorMora = new VariablesRating(listaVariablesComportamiento.get(2).getNombre(), 2, this.retrieveData(object[47]));
            variableNumeroBancos = new VariablesRating(listaVariablesComportamiento.get(3).getNombre(), 2, this.retrieveData(object[48]));
            variableMarcacionReestructuracion = new VariablesRating(listaVariablesComportamiento.get(4).getNombre(), 2, this.retrieveData(object[49]));
           
            System.out.println("variableCalificacion:::"+variableCalificacion);
            System.out.println("variableGarantia:::"+variableGarantia);
            System.out.println("variableIndicadorMora:::"+variableIndicadorMora);
            listaVariablesComportamientoFinal.add(variableCalificacion);
            listaVariablesComportamientoFinal.add(variableGarantia);
            listaVariablesComportamientoFinal.add(variableIndicadorMora);
            listaVariablesComportamientoFinal.add(variableNumeroBancos);
            listaVariablesComportamientoFinal.add(variableMarcacionReestructuracion);
          
            listaVariablesObjetivoFinal.add(variableEvolucion);
            listaVariablesObjetivoFinal.add(variablePosicion);
            listaVariablesObjetivoFinal.add(variableDependencia);
            listaVariablesObjetivoFinal.add(variableProveedor);
            listaVariablesObjetivoFinal.add(variableCapacidad);
            listaVariablesObjetivoFinal.add(variableGarantias);
            listaVariablesObjetivoFinal.add(variableCalidad);
            listaVariablesObjetivoFinal.add(variableInforme);
          
            listaVariablesResultadoFinal.add(variableRating);
            listaVariablesResultadoFinal.add(variableId);
           
            listVariablesRatingTotal.add(listaVariables);
            listVariablesRatingTotal.add(listaVariablesComportamientoFinal);
            listVariablesRatingTotal.add(listaVariablesObjetivoFinal);
            listVariablesRatingTotal.add(listaVariablesSubjetivoFinal);
            listVariablesRatingTotal.add(listaVariablesResultadoFinal);
       }
           
        Iterator i = listaTemporal.iterator();
        while(i.hasNext()){
        
            Object[] object = (Object[]) i.next();
            objetoAgregado = new RatingInfo();
            System.out.println("0"+Integer.parseInt(((BigDecimal) object[0]).toString()));
            
            objetoAgregado.setId(Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setRazonSocial(this.retrieveData(object[1]));
            System.out.println("Razon Social:::"+ this.retrieveData(object[1]));
            objetoAgregado.setGrupoEconomico(this.retrieveData(object[2]));
            System.out.println("Grupo Economico:::"+ this.retrieveData(object[2]));
            objetoAgregado.setNit(this.retrieveData(object[3]));
            System.out.println("Nit:::"+ this.retrieveData(object[3]));
            objetoAgregado.setValorRating(this.retrieveDataNumeric(object[4]));
            System.out.println("valor rating:::"+ this.retrieveData(object[4]));
            objetoAgregado.setValorRatingFinal(this.retrieveDataNumeric(object[5]));
            System.out.println("valor rating final:::"+ this.retrieveData(object[5]));
     //       objetoAgregado.setNumeroCaso(Integer.parseInt(((BigDecimal) object[6]).toString()));
            System.out.println("Numero de caso:::"+ this.retrieveData(object[6]));
            objetoAgregado.setEstado(this.retrieveData(object[7]));
            System.out.println("Estado:::"+ this.retrieveData(object[7]));
            objetoAgregado.setApalancamiento(this.retrieveDataNumeric(object[8]));
            System.out.println("Apalancamiento:::"+ this.retrieveData(object[8]));
            objetoAgregado.setEbitda(this.retrieveDataNumeric(object[9]));
            System.out.println("Ebit:::"+ this.retrieveData(object[9]));
            objetoAgregado.setCfl(this.retrieveData(object[10]));
            objetoAgregado.setLiquidez(this.retrieveDataNumeric(object[11]));
            objetoAgregado.setCoberturaInteres(this.retrieveData(object[12]));
            objetoAgregado.setMargenBitda(this.retrieveDataNumeric(object[13]));
            objetoAgregado.setRotacionClientes(this.retrieveDataNumeric(object[14]));
            objetoAgregado.setRotacionInventarios(this.retrieveDataNumeric(object[15]));
            objetoAgregado.setRotacionProveedores(this.retrieveDataNumeric(object[16]));
            objetoAgregado.setCalificacion(this.retrieveData(object[17]));
            objetoAgregado.setGarantia(this.retrieveData(object[18]));
            objetoAgregado.setIndicadorMora(this.retrieveData(object[19]));
            objetoAgregado.setNumeroBancos(this.retrieveData(object[20]));
            objetoAgregado.setReestructuracion(this.retrieveData(object[21]));
            objetoAgregado.setEvolucionEsperada(this.retrieveData(object[22]));
            objetoAgregado.setPosicionMercado(this.retrieveData(object[23]));
            objetoAgregado.setDependenciaClientes(this.retrieveData(object[24]));
            objetoAgregado.setConcentracionProveedor(this.retrieveData(object[25]));
            objetoAgregado.setVoluntadCapacidad(this.retrieveData(object[26]));
            objetoAgregado.setGarantiasAdicionales(this.retrieveData(object[27]));
            objetoAgregado.setCalidadRevisor(this.retrieveData(object[28]));
            objetoAgregado.setInformeRevisor(this.retrieveData(object[29]));
            objetoAgregado.setTipoProducto(this.retrieveData(object[30]));
            objetoAgregado.setGerenciaCapacidad(this.retrieveData(object[31]));
            objetoAgregado.setAbanicoBancario(this.retrieveData(object[32]));
            objetoAgregado.setMecanismosFinanciacion(this.retrieveData(object[33]));
            objetoAgregado.setEstructuraCostos(this.retrieveData(object[34]));
            objetoAgregado.setCapacidadAtencion(this.retrieveData(object[35]));
            objetoAgregado.setGradoAutofinanciacion(this.retrieveData(object[36]));
            objetoAgregado.setExistenciaDeudas(this.retrieveData(object[37]));
            objetoAgregado.setPerfilPagoDeuda(this.retrieveData(object[38]));
            objetoAgregado.setCalidadActivos(this.retrieveData(object[39]));
            objetoAgregado.setTipoAccionista(this.retrieveData(object[40]));
            objetoAgregado.setInterceptoSumatoria(this.retrieveDataNumeric(object[41]));
            objetoAgregado.setWoeObjetivable(this.retrieveDataNumeric(object[42]));
            objetoAgregado.setWoeFinanciero(this.retrieveDataNumeric(object[43]));
            objetoAgregado.setWoeComportamiento(this.retrieveDataNumeric(object[44]));
            objetoAgregado.setWoeSubjetivo(this.retrieveDataNumeric(object[45]));
            objetoAgregado.setBetaFinanciero(this.retrieveDataNumeric(object[46]));
            objetoAgregado.setBetaComportamiento(this.retrieveDataNumeric(object[47]));
            objetoAgregado.setBetaObjetivable(this.retrieveDataNumeric(object[48]));
            objetoAgregado.setInterceptoObjSubj(this.retrieveDataNumeric(object[49]));
            objetoAgregado.setSumatoriaWoe(this.retrieveDataNumeric(object[50]));
            objetoAgregado.setSumatoriaBeta(this.retrieveDataNumeric(object[51]));
            objetoAgregado.setBetaSubjetivo(this.retrieveDataNumeric(object[52]));
            objetoAgregado.setValorAlpha(this.retrieveDataNumeric(object[53]));
            objetoAgregado.setValorBeta(this.retrieveDataNumeric(object[54]));
            objetoAgregado.setValorLogOdds(this.retrieveDataNumeric(object[55]));
            objetoAgregado.setUsuario(this.retrieveData(object[56]));
            objetoAgregado.setFechaInsercion(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").format(object[57] == null ? new Date() : ((Timestamp) object[57])));
            objetoAgregado.setComentariosUsuario(this.retrieveData(object[58]));

            listaRating.add(objetoAgregado);      
        }
        
        conn.close();
        
        return listVariablesRatingTotal;
    }
     
    public BigDecimal retrieveDataNumeric(Object validate){
    
        return validate == null ? new BigDecimal("0") : (BigDecimal) validate;
        
    }
    
   
    public String retrieveData(Object validate){
    
        return validate == null ? "" : validate.toString();
        
    }
    
    public List<String> obtenerPeriodos(String tipoConsulta, String parametro){
    
        String query = "SELECT aniobalance FROM ri.resumeneeff ";
        
        System.out.println("Metodo obtenerPeriodos, parametros::: "+tipoConsulta + " " +parametro );
        switch(Integer.parseInt(tipoConsulta)){
            
            case 0:
                query += "WHERE nit = ?";
                break;
                
            case 1:
                System.out.println("case 1");
            //    query += "WHERE TRIM(nombreempresa) = ?";
                query += "WHERE nombreempresa = ?";
                break;
        
        }
        

        System.out.println("query::: "+query);
    
        Query q = em.createNativeQuery(query);
        List listaProvisional = q.setParameter(1, parametro).getResultList();
        
       
        List<String> listaPeriodos = new ArrayList<String>();
        Iterator i = listaProvisional.iterator();
        String dateAgregado = null;
        
        while(i.hasNext()){
        
            java.sql.Date fechaPeriodo = (java.sql.Date) i.next();
            dateAgregado = new SimpleDateFormat("yyyy-MM-dd").format(fechaPeriodo);
            
            listaPeriodos.add(dateAgregado);
            
        }
         
        return listaPeriodos;
        
    }
    
    public boolean confirmarRating(BigDecimal valorRatingAnterior, BigDecimal valorRatingDefinitivo, BigDecimal id, String comentario){
    
      //  Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating = ?, valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        return  q.setParameter(1, valorRatingDefinitivo).setParameter(2, comentario).setParameter(3, id).executeUpdate() != 0;
        
    }
    
    
    public boolean registrarComportamiento(String respCalificacion, String respGarantia, String indMora, String numeroBancos, String marcacionReestructuracion, String id_cliente, String usuario){
    
    
      //  Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        //return  q.setParameter(1, valorRatingDefinitivo).setParameter(2, comentario).setParameter(3, id).executeUpdate() != 0;
        Query q1 = em.createNativeQuery("INSERT INTO ri.modulo_comportamiento(id, "
                + "calificacion, garantia, indicador_mora, numero_bancos, "
                + "marcacion_reestructuracion, id_cliente, usuario, fecha_insercion)"
                + " VALUES ((SELECT MAX(id) from ri.modulo_comportamiento)+ 1, ?, "
                + "?, ?, ?, ?, ?, ?, (SELECT now()::timestamp))");
   
        return q1.setParameter(1, respCalificacion).setParameter(2, respGarantia).setParameter(3, indMora).setParameter(4, numeroBancos).setParameter(5, marcacionReestructuracion).setParameter(6,id_cliente).setParameter(7, usuario).executeUpdate() != 0;
        
    }
    
    public boolean registrarObjetivable(String cliente, String evolucionEsperada, 
            String posicionMercado, String dependenciaCliente, String concentracionProveedor, 
            String voluntadCapacidadApoyo, String garantiasAdicionales, String calidadRevisorFiscal, 
            String informeRevisorFiscal, String usuario){
    
   
        Query q = em.createNativeQuery(" INSERT INTO ri.modulo_objetivable(\n" +
                "id, id_cliente, evolucion_esperada_actividad, posicion_mercado, dependencia_clientes, "
                + "concentracion_proveedor, voluntad_capacidad_apoyo_financiero_accionistas, "
                + "garantias_adicionales, calidad_revisor_fiscal, informe_revisor_fiscal, usuario, fecha_insercion)"
                + " VALUES ((SELECT MAX(id) from ri.modulo_objetivable)+ 1, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT now()::timestamp))");
   
        return q.setParameter(1, cliente).setParameter(2, evolucionEsperada).
                setParameter(3, posicionMercado).setParameter(4, dependenciaCliente).
                setParameter(5, concentracionProveedor).setParameter(6, voluntadCapacidadApoyo).
                setParameter(7, garantiasAdicionales).setParameter(8, calidadRevisorFiscal).
                setParameter(9, informeRevisorFiscal).setParameter(10, usuario).executeUpdate() != 0;
        
    }
    
    public boolean registrarSubjetivo(String cliente, String tipoProducto, 
            String gerenciaCapacidad, String abanicoBancario, String mecanismoFinanciacion, 
            String estructuraCostos, String capacidadAtenderCalendario, String gradoAutoFinanciacion, 
            String existenciaDeuda, String perfilPago, String calidadActivos, String tipoAccionista,
            String usuario){
    
   
        Query q = em.createNativeQuery(" INSERT INTO ri.modulo_subjetivo(\n" +
                "id, id_cliente, tipo_producto_servicio, gerencia_capacidad_profesionalidad_experiencia, "
                + "abanico_bancario_facilidad_sustitucion, mecanismos_financiacion, "
                + "estructura_costos, capacidad_atender_calendario_pago_deuda, "
                + "grado_autofinanciacion_inversiones, existencia_deudas_compromisos_fuera_alcance, "
                + "perfil_pago_deuda, calidad_activos_circulantes, tipo_accionista,"
                + " usuario, fecha_insercion)"
                + " VALUES ((SELECT MAX(id) from ri.modulo_subjetivo)+ 1, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT now()::timestamp))");
   
        return q.setParameter(1, cliente).setParameter(2, tipoProducto).
                setParameter(3, gerenciaCapacidad).setParameter(4, abanicoBancario).
                setParameter(5, mecanismoFinanciacion).setParameter(6, estructuraCostos).
                setParameter(7, capacidadAtenderCalendario).setParameter(8, gradoAutoFinanciacion).
                setParameter(9, existenciaDeuda).setParameter(10, perfilPago).setParameter(11, calidadActivos).
                setParameter(12, tipoAccionista).setParameter(13, usuario).executeUpdate() != 0;
        
    }
    
}