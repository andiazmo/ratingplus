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
public class ConsultaResultadosRatingFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaResultadosRatingFacade() {
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
        
        
        listaRespuestasVariablesModulo.add(listarRespuestasVariablesComportamiento(listaRespuestasVariables));
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
        System.out.println("Metodo listarVariablesRating:::"+listaProvisional.size());
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new Modulo();
            objetoAgregado.setNombre((String) object[1]);
        
            listaModulos.add(objetoAgregado);
        }
       
        
        return listaModulos;
        
    }
    
  //  public List<RatingInfo> calcularRating(String[] listaNits, String periodo, String usuario) throws SQLException{
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
        
        System.out.println("Tamaña lista variables rating:::"+listVariablesRating.size());
        
      
        System.out.println("Usuario en el facade:::"+usuario);
        Query q = em.createNativeQuery("SELECT ri.calcular_rating_fn(?,?,TO_DATE(?,'YYYY-MM-DD'))");
        List listaProvisional = q.setParameter(1, conn.createArrayOf("text", listaNits)).setParameter(2, usuario).setParameter(3, periodo).getResultList();
        System.out.println("listaProvisional:::"+listaProvisional.get(0).toString());
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
        
    //    Query q1 = em.createNativeQuery("select * from ri.resultados_rating_plus where usuario = ? AND nit = ANY(?)order by id desc limit 1;");
        List listaTemporal = q1.setParameter(1, usuario).setParameter(2, conn.createArrayOf("text", listaNits)).setParameter(3, conn.createArrayOf("text", listaNits)).getResultList();
        
       //---------------------------------
      
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
           
       
       
       
       
       //---------------------------------
   
   
   
        Iterator i = listaTemporal.iterator();
        
        
        //---------------------------------
        
        while(i.hasNext()){
        
            Object[] object = (Object[]) i.next();
            objetoAgregado = new RatingInfo();
            System.out.println("0"+Integer.parseInt(((BigDecimal) object[0]).toString()));
//            System.out.println("1"+Integer.parseInt(((BigDecimal) object[1]).toString()));
//            System.out.println("2"+Integer.parseInt(((BigDecimal) object[2]).toString()));
//            System.out.println("3"+Integer.parseInt(((BigDecimal) object[3]).toString()));
            
            
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
//            System.out.println("59:::"+this.retrieveData(object[59]));
//            System.out.println("60:::"+this.retrieveData(object[60]));
//            System.out.println("61:::"+this.retrieveData(object[61]));
//            System.out.println("62:::"+this.retrieveData(object[62]));
//            System.out.println("63:::"+this.retrieveData(object[63]));            
            
            
            listaRating.add(objetoAgregado);
            
        }
        
        conn.close();
        
      //  return listaRating;
      //return listaVariables;
      return listVariablesRatingTotal;
    
    }
    
    
    public List<RatingInfo> getResultadosRating() {
        List<RatingInfo> listaResultadosRating = new ArrayList<RatingInfo>();
        RatingInfo objetoAgregado = null;
//        String query = "SELECT irrcv.*, ru.nombres FROM ri.informacion_resultados_rating_vw irrcv, "
//                + "rp_usuarios ru WHERE irrcv.usuario = ru.codigo order by id desc";
        
        String query = "SELECT rrrc.*, ru.nombres FROM ri.reporte_resultados_rating_cliente_vw rrrc, "
                + "rp_usuarios ru WHERE rrrc.usuario = ru.codigo order by id desc";
//        String query = "select id,nit,valor_rating,valor_rating_final,num_caso,"
//                + "estado, ru.nombres, valor_variables_objetivable,valor_variables_comportamiento,"
//                + "valor_variables_financiero,valor_variables_subjetivo,\n" +
//"valor_intercepto_sumatoria,woe_objetivable,woe_financiero,woe_comportamiento,woe_subjetivo,"
//                + "valor_beta_financiero,valor_beta_comportamiento,valor_beta_objetivable,\n" +
//"vl_intercepto_obj_sub,vl_sumatoria_woe,valor_beta_sumatoria,valor_beta_subjetivo,valor_alpha,valor_beta,valor_logodds,usuario,fecha_insercion,comentarios_usuario,\n" +
//"id_modulo,apalancamiento,deuda_financiera_dfn,resultado_explotacion_ebitda,resultado_dfn_ebitda,cf_antes_financiacion,resultado_dfn_cfl,liquidez,\n" +
//"cobertura_intereses_cfo,gastos_financieros,resultado_cobertura_intereses,margen_ebitda,margen_ebitda_anterior,resultado_margen_ebitda,rotacion_clientes_dias,rotacion_inventarios_dias,\n" +
//"rotacion_proveedores_dias,calificacion_sector_financiero,garantias_sector_financiero,indicador_mora,numero_bancos,marcacion_reestructuracion,\n" +
//"evolucion_esperada_sector_desarrolla_principal_actividad,posicion_mercado,dependencia_clientes,concentracion_proveedor,voluntad_capacidad_apoyo_financiero_parte_accionista,\n" +
//"garantias_adicionales,calidad_revisor_fiscal,informe_revisor_fiscal,tipo_producto_servicio,gerencia_capacidad_profesionalidad_experiencia,abanico_bancario_facilidad_sustitucion,\n" +
//"mecanismos_financiacion,estructura_costos,capacidad_atender_calendario_pago_deuda,grado_autofinanciacion_inversiones,existencia_deudas_compromisos_fuera_balance,perfil_pago_deuda,\n" +
//"calidad_activos_circulante,tipo_accionista,rango_apalancamiento,rango_resultado_dfn_ebitda,rango_resultado_dfn_cfl,rango_liquidez,rango_resultado_cobertura_intereses,\n" +
//"rango_resultado_margen_ebitda,rango_rotacion_clientes_dias,rango_rotacion_inventarios_dias,rango_rotacion_proveedores_dias, irrcv.nombre, nombre_grupo\n" +
//"from ri.informacion_resultados_rating_vw irrcv, rp_usuarios ru where irrcv.usuario = ru.codigo order by id desc";
         
         List listaProvisional = em.createNativeQuery(query).getResultList();
         Iterator i = listaProvisional.iterator();
         BigDecimal valorRatingProvisional = new BigDecimal("0");
         BigDecimal valorRatingFinalProvisional = new BigDecimal("0");
         BigDecimal valorApalancamientoProvisional = new BigDecimal("0");
         BigDecimal valorDFNEBITDA = new BigDecimal("0");
         BigDecimal valorDFNCFL = new BigDecimal("0");
         BigDecimal valorLiquidez = new BigDecimal("0");
         BigDecimal valorInterceptoSumatoria = new BigDecimal("0");
         BigDecimal valorWOEObjetivable = new BigDecimal("0");
         BigDecimal valorWOEFinanciero = new BigDecimal("0");
         BigDecimal valorWOEComportamiento = new BigDecimal("0");
         BigDecimal valorWOESubjetivo = new BigDecimal("0");
         BigDecimal valorBetaObjetivable = new BigDecimal("0");
         BigDecimal valorBetaFinanciero = new BigDecimal("0");
         BigDecimal valorBetaComportamiento = new BigDecimal("0");
         BigDecimal valorBetaSubjetivo = new BigDecimal("0");
         BigDecimal valorInterceptoCombinacionObjSubj = new BigDecimal("0");
         BigDecimal valorSumatoriaWOE = new BigDecimal("0");
         BigDecimal valorSumatoriaBetas = new BigDecimal("0");
         BigDecimal alpha = new BigDecimal("0");
         BigDecimal beta = new BigDecimal("0");
         BigDecimal logOdds = new BigDecimal("0");
        
//         BigDecimal valorLiquidez = new BigDecimal("0");
         
         while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new RatingInfo();
            valorRatingProvisional = object[2] == null ? new BigDecimal("0"): ((BigDecimal) object[2]);
            valorRatingFinalProvisional = object[3] == null ? new BigDecimal("0"): ((BigDecimal) object[3]);
            valorApalancamientoProvisional = object[29] == null ? new BigDecimal("0"): ((BigDecimal) object[29]);
            valorDFNEBITDA = object[32] == null ? new BigDecimal("0"): ((BigDecimal) object[32]);
            valorDFNCFL = object[34] == null ? new BigDecimal("0"): ((BigDecimal) object[34]);
            valorLiquidez = object[35] == null ? new BigDecimal("0"): ((BigDecimal) object[35]);
            valorInterceptoSumatoria = object[10] == null ? new BigDecimal("0"): ((BigDecimal) object[10]);
            valorWOEObjetivable = object[11] == null ? new BigDecimal("0"): ((BigDecimal) object[11]);        
            valorWOEFinanciero = object[12] == null ? new BigDecimal("0"): ((BigDecimal) object[12]);
            valorWOEComportamiento = object[13] == null ? new BigDecimal("0"): ((BigDecimal) object[13]);
            valorWOESubjetivo = object[14] == null ? new BigDecimal("0"): ((BigDecimal) object[14]);
            valorBetaFinanciero = object[15] == null ? new BigDecimal("0"): ((BigDecimal) object[15]);   
            valorBetaComportamiento = object[16] == null ? new BigDecimal("0"): ((BigDecimal) object[16]);
            valorBetaObjetivable = object[17] == null ? new BigDecimal("0"): ((BigDecimal) object[17]);
            valorInterceptoCombinacionObjSubj  = object[18] == null ? new BigDecimal("0"): ((BigDecimal) object[18]);
            valorSumatoriaWOE = object[19] == null ? new BigDecimal("0"): ((BigDecimal) object[19]);
            valorSumatoriaBetas = object[20] == null ? new BigDecimal("0"): ((BigDecimal) object[20]);
            valorBetaSubjetivo = object[21] == null ? new BigDecimal("0"): ((BigDecimal) object[21]);
            alpha = object[22] == null ? new BigDecimal("0"): ((BigDecimal) object[22]);
            beta = object[23] == null ? new BigDecimal("0"): ((BigDecimal) object[23]);
            logOdds = object[24] == null ? new BigDecimal("0"): ((BigDecimal) object[24]);
            
            
            objetoAgregado.setId(Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setValorRating(valorRatingProvisional);
            objetoAgregado.setValorRatingFinal(valorRatingFinalProvisional);
  //          objetoAgregado.setNumeroCaso(Integer.parseInt(((BigDecimal) object[4]).toString()));
            objetoAgregado.setNumeroCaso(Integer.parseInt((object[4]).toString()));
            objetoAgregado.setEstado((String) object[5]);
            objetoAgregado.setComentarioUsuario((String) object[27]);
            objetoAgregado.setRazonSocial((String) object[78]);
            objetoAgregado.setGrupoEconomico((String) object[80]);
            objetoAgregado.setUsuarioBanco((String) object[81]);
//            objetoAgregado.setApalancamiento(valorApalancamientoProvisional);
//            objetoAgregado.setDfnEbitda(valorDFNEBITDA);
//            objetoAgregado.setDfnCfl(valorDFNCFL);
//            objetoAgregado.setLiquidez(valorLiquidez);
            
            
//            objetoAgregado.setUsuario((String) object[26]);
//            objetoAgregado.setApalancamiento(valorApalancamientoProvisional);
//            objetoAgregado.setEbitda(valorDFNEBITDA);
//            objetoAgregado.setDfnCfl(valorDFNCFL);
//            objetoAgregado.setLiquidez(valorLiquidez);
//            objetoAgregado.setRazonSocial((String) object[78]);
//            objetoAgregado.setGrupoEconomico((String) object[79]);
//            objetoAgregado.setUsuarioBanco((String) object[80]);
            objetoAgregado.setRangoApalancamiento((String) object[69]);
            objetoAgregado.setRangoDFNEBITDA((String) object[70]);
            objetoAgregado.setRangoDFNCFL((String) object[71]);
            objetoAgregado.setRangoLiquidez((String) object[72]);
            objetoAgregado.setRangoCoberturaIntereses((String) object[73]);
            objetoAgregado.setRangoMargenEBITDA((String) object[74]);
            objetoAgregado.setRangoRotacionClientesDias((String) object[75]);
            objetoAgregado.setRangoRotacionInventariosDias((String) object[76]);
            objetoAgregado.setRangoRotacionProveedoresDias((String) object[77]);
            objetoAgregado.setCalificacion((String) object[45]);
            objetoAgregado.setGarantia((String) object[46]);
            objetoAgregado.setIndicadorMora((String) object[47]);
            objetoAgregado.setNumeroBancos((String) object[48]);
            objetoAgregado.setReestructuracion((String) object[49]);
            objetoAgregado.setEvolucionEsperada((String) object[50]);
            objetoAgregado.setPosicionMercado((String) object[51]);
            objetoAgregado.setDependenciaClientes((String) object[52]);
            objetoAgregado.setConcentracionProveedor((String) object[53]);
            objetoAgregado.setVoluntadCapacidad((String) object[54]);
            objetoAgregado.setGarantiasAdicionales((String) object[55]);
            objetoAgregado.setCalidadRevisor((String) object[56]);
            objetoAgregado.setInformeRevisor((String) object[57]);
            objetoAgregado.setTipoProducto((String) object[58]);
            objetoAgregado.setGerenciaCapacidad((String) object[59]);
            objetoAgregado.setAbanicoBancario((String) object[60]);
            objetoAgregado.setMecanismosFinanciacion((String) object[61]);
            objetoAgregado.setEstructuraCostos((String) object[62]);
            objetoAgregado.setCapacidadAtencion((String) object[63]);
            objetoAgregado.setGradoAutofinanciacion((String) object[64]);
            objetoAgregado.setExistenciaDeudas((String) object[65]);
            objetoAgregado.setPerfilPagoDeuda((String) object[66]);
            objetoAgregado.setCalidadActivos((String) object[67]);
            objetoAgregado.setTipoAccionista((String) object[68]);
            objetoAgregado.setInterceptoSumatoria(valorInterceptoSumatoria);
            objetoAgregado.setSumatoriaWoe(valorSumatoriaWOE);
            objetoAgregado.setSumatoriaBeta(valorSumatoriaBetas);
            objetoAgregado.setBetaSubjetivo(valorBetaSubjetivo);
            objetoAgregado.setValorAlpha(alpha);
            objetoAgregado.setValorBeta(beta);
            objetoAgregado.setValorLogOdds(logOdds);
            
//            objetoAgregado.setComentariosUsuario((String)object[28]);
            objetoAgregado.setFechaInsercion(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").format(object[26] == null ? new Date() : ((Timestamp) object[26])));
            listaResultadosRating.add(objetoAgregado);
            
 
        }
       
        
        return listaResultadosRating;
    }
    
    public int getResultsTotalCount() {
        List<RatingInfo> listaResultadosRating = new ArrayList<RatingInfo>();
        RatingInfo objetoAgregado = null;
        String query = "select id,nit,valor_rating,valor_rating_final,num_caso,estado, ru.nombres, valor_variables_objetivable,valor_variables_comportamiento,valor_variables_financiero,valor_variables_subjetivo,\n" +
"valor_intercepto_sumatoria,woe_objetivable,woe_financiero,woe_comportamiento,woe_subjetivo,valor_beta_financiero,valor_beta_comportamiento,valor_beta_objetivable,\n" +
"vl_intercepto_obj_sub,vl_sumatoria_woe,valor_beta_sumatoria,valor_beta_subjetivo,valor_alpha,valor_beta,valor_logodds,usuario,fecha_insercion,comentarios_usuario,\n" +
"id_modulo,apalancamiento,deuda_financiera_dfn,resultado_explotacion_ebitda,resultado_dfn_ebitda,cf_antes_financiacion,resultado_dfn_cfl,liquidez,\n" +
"cobertura_intereses_cfo,gastos_financieros,resultado_cobertura_intereses,margen_ebitda,margen_ebitda_anterior,resultado_margen_ebitda,rotacion_clientes_dias,rotacion_inventarios_dias,\n" +
"rotacion_proveedores_dias,calificacion_sector_financiero,garantias_sector_financiero,indicador_mora,numero_bancos,marcacion_reestructuracion,\n" +
"evolucion_esperada_sector_desarrolla_principal_actividad,posicion_mercado,dependencia_clientes,concentracion_proveedor,voluntad_capacidad_apoyo_financiero_parte_accionista,\n" +
"garantias_adicionales,calidad_revisor_fiscal,informe_revisor_fiscal,tipo_producto_servicio,gerencia_capacidad_profesionalidad_experiencia,abanico_bancario_facilidad_sustitucion,\n" +
"mecanismos_financiacion,estructura_costos,capacidad_atender_calendario_pago_deuda,grado_autofinanciacion_inversiones,existencia_deudas_compromisos_fuera_balance,perfil_pago_deuda,\n" +
"calidad_activos_circulante,tipo_accionista,rango_apalancamiento,rango_resultado_dfn_ebitda,rango_resultado_dfn_cfl,rango_liquidez,rango_resultado_cobertura_intereses,\n" +
"rango_resultado_margen_ebitda,rango_rotacion_clientes_dias,rango_rotacion_inventarios_dias,rango_rotacion_proveedores_dias, irrcv.nombre, nombre_grupo\n" +
"from ri.informacion_resultados_rating_vw irrcv, rp_usuarios ru where irrcv.usuario = ru.codigo and estado = 'Definitivo' order by id desc";
        
        List listaProvisional = em.createNativeQuery(query).getResultList();
        Iterator i = listaProvisional.iterator();
        BigDecimal valorRatingProvisional = new BigDecimal("0");
        BigDecimal valorRatingFinalProvisional = new BigDecimal("0");
        BigDecimal valorApalancamientoProvisional = new BigDecimal("0");
         
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new RatingInfo();
            valorRatingProvisional = object[3] == null ? new BigDecimal("0"): ((BigDecimal) object[3]);
            valorRatingFinalProvisional = object[4] == null ? new BigDecimal("0"): ((BigDecimal) object[4]);
          //  valorApalancamientoProvisional = object[6] == null ? new BigDecimal("0"): ((BigDecimal) object[6]);
            
            
            objetoAgregado.setId(Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setValorRating(valorRatingProvisional);
            objetoAgregado.setValorRatingFinal(valorRatingFinalProvisional);
//            objetoAgregado.setNumeroCaso(Integer.parseInt(((BigDecimal) object[4]).toString()));
       //     objetoAgregado.setEstado((String) object[5]);
        //    objetoAgregado.setApalancamiento(valorApalancamientoProvisional);
            objetoAgregado.setUsuario((String) object[6]);
            objetoAgregado.setRazonSocial((String) object[79]);
            objetoAgregado.setGrupoEconomico((String) object[80]);
            objetoAgregado.setComentariosUsuario((String)object[28]);
            objetoAgregado.setFechaInsercion(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").format(object[27] == null ? new Date() : ((Timestamp) object[27])));
            listaResultadosRating.add(objetoAgregado);
        }
       
        
        return listaResultadosRating.size();
        
        
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
    
    
        Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating_final = ?, comentarios_usuario = ? WHERE id = ?");
        //return  q.setParameter(1, valorRatingDefinitivo).setParameter(2, comentario).setParameter(3, id).executeUpdate() != 0;
        Query q1 = em.createNativeQuery("INSERT INTO ri.modulo_comportamiento(id, calificacion, garantia, indicador_mora, numero_bancos, marcacion_reestructuracion, id_cliente, usuario, fecha_insercion) VALUES ((SELECT MAX(id) from ri.modulo_comportamiento)+ 1, ?, ?, ?, ?, ?, ?, ?, (SELECT now()::timestamp))");
   
        return q1.setParameter(1, respCalificacion).setParameter(2, respGarantia).setParameter(3, indMora).setParameter(4, numeroBancos).setParameter(5, marcacionReestructuracion).setParameter(6,id_cliente).setParameter(7, usuario).executeUpdate() != 0;
        
    }
    
}