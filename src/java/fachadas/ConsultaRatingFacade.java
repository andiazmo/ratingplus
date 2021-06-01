/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import dao.ConnectionFactory;
import entidades.GruposClientes;
import entidades.Modulo;
import entidades.RatingInfo;
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
public class ConsultaRatingFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaRatingFacade() {
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
     
    public List<List<VariablesRating>> calcularRating(String[] listaNits, 
            String periodo, String usuario) throws SQLException{
        List<List<VariablesRating>> listVariablesRatingTotal;
        try (Connection conn = new ConnectionFactory().getConnection()) {
            List<RatingInfo> listaRating = new ArrayList<>();
            List<List<VariablesRating>> listVariablesRating = new ArrayList<>();
            List<VariablesRating> listaVariables = new ArrayList<>();
            listVariablesRatingTotal = new ArrayList<>();
            List<VariablesRating> listaVariablesComportamientoFinal = new ArrayList<>();
            List<VariablesRating> listaVariablesObjetivoFinal = new ArrayList<>();
            List<VariablesRating> listaVariablesSubjetivoFinal = new ArrayList<>();
            List<VariablesRating> listaVariablesResultadoFinal = new ArrayList<>();
            listVariablesRating = listarVariablesRating();
            List listaProvisional;
            if (periodo == null) {
                Query query = em.createNativeQuery("SELECT "
                        + "ri.calcular_rating_sinfin_fn(?,?)");
                listaProvisional = query.setParameter(1,
                        conn.createArrayOf("text", listaNits)).
                        setParameter(2, usuario).getResultList();
            }
            else {
                Query q = em.createNativeQuery("SELECT "
                        + "ri.calcular_rating_fn(?,?,TO_DATE(?,'YYYY-MM-DD'))");
                listaProvisional = q.setParameter(1,
                        conn.createArrayOf("text", listaNits)).
                        setParameter(2, usuario).setParameter(3, periodo).
                        getResultList();    
            }   
            Query q1 = em.createNativeQuery("SELECT * "
                    + "FROM ri.Informacion_Rating_Plus_VW "
                    + "WHERE usuario = ? AND nit = ANY(?) "
                    + "AND id IN (SELECT MAX(id) "
                    + "FROM ri.Informacion_Rating_Plus_VW WHERE nit = ANY(?) "
                    + "GROUP BY nit)");
            List listaTemporal = q1.setParameter(1, usuario).
                    setParameter(2, conn.createArrayOf("text", listaNits)).
                    setParameter(3, conn.createArrayOf("text", listaNits)).
                    getResultList();
            List listaTemporalResp =
                    em.createNativeQuery("select * "
                            + "from ri.resultados_rating_plus "
                            + "order by id desc limit 1").getResultList();
            List<VariablesRating> listaVariablesFinanciero = listVariablesRating.get(0);
            List<VariablesRating> listaVariablesComportamiento = listVariablesRating.get(1);
            List<VariablesRating> listaVariablesObjetivo = listVariablesRating.get(2);
            List<VariablesRating> listaVariablesSubjetivo = listVariablesRating.get(3);
            List<VariablesRating> listaVariablesResultado = listVariablesRating.get(4);
            Iterator j = listaTemporalResp.iterator();
            while(j.hasNext()){
                Object[] object = (Object[]) j.next();
                
                VariablesRating variableNit =
                        new VariablesRating(listaVariablesFinanciero.get(0).
                                getNombre(), 1, this.retrieveData(object[1]));
                VariablesRating variableApalancamiento =
                        new VariablesRating(listaVariablesFinanciero.get(1).
                                getNombre(), 1, this.retrieveData(object[69]));
                VariablesRating variableEbitda =
                        new VariablesRating(listaVariablesFinanciero.get(2).
                                getNombre(), 1, this.retrieveData(object[70]));
                VariablesRating variableCfl =
                        new VariablesRating(listaVariablesFinanciero.get(3).
                                getNombre(), 1, this.retrieveData(object[71]));
                VariablesRating variableLiquidez =
                        new VariablesRating(listaVariablesFinanciero.get(4).
                                getNombre(), 1, this.retrieveData(object[72]));
                VariablesRating variableCfo =
                        new VariablesRating(listaVariablesFinanciero.get(5).
                                getNombre(), 1, this.retrieveData(object[73]));
                VariablesRating variableMargenEbitda =
                        new VariablesRating(listaVariablesFinanciero.get(6).
                                getNombre(), 1, this.retrieveData(object[74]));
                VariablesRating variableRotacionClientesDias =
                        new VariablesRating(listaVariablesFinanciero.get(7).
                                getNombre(), 1, this.retrieveData(object[75]));
                VariablesRating variableRotacionInventariosDias =
                        new VariablesRating(listaVariablesFinanciero.get(8).
                                getNombre(), 1, this.retrieveData(object[76]));
                VariablesRating variableRotacionProveedoresDias =
                        new VariablesRating(listaVariablesFinanciero.get(9).
                                getNombre(), 1, this.retrieveData(object[77]));
                VariablesRating variableRating =
                        new VariablesRating(listaVariablesResultado.get(0).
                                getNombre(), 1, this.retrieveData(object[2]));
                VariablesRating variableId =
                        new VariablesRating(listaVariablesResultado.get(0).
                                getNombre(), 1, this.retrieveData(object[0]));
                VariablesRating variableEvolucion =
                        new VariablesRating(listaVariablesObjetivo.get(1).
                                getNombre(), 3, this.retrieveData(object[50]));
                VariablesRating variablePosicion =
                        new VariablesRating(listaVariablesObjetivo.get(2).
                                getNombre(), 3, this.retrieveData(object[51]));
                VariablesRating variableDependencia =
                        new VariablesRating(listaVariablesObjetivo.get(3).
                                getNombre(), 3, this.retrieveData(object[52]));
                VariablesRating variableProveedor =
                        new VariablesRating(listaVariablesObjetivo.get(4).
                                getNombre(), 3, this.retrieveData(object[53]));
                VariablesRating variableCapacidad =
                        new VariablesRating(listaVariablesObjetivo.get(5).
                                getNombre(), 3, this.retrieveData(object[54]));
                VariablesRating variableGarantias =
                        new VariablesRating(listaVariablesObjetivo.get(6).
                                getNombre(), 3, this.retrieveData(object[55]));
                VariablesRating variableCalidad =
                        new VariablesRating(listaVariablesObjetivo.get(7).
                                getNombre(), 3, this.retrieveData(object[56]));
                VariablesRating variableInforme =
                        new VariablesRating(listaVariablesObjetivo.get(8)
                                .getNombre(), 3, this.retrieveData(object[57]));
                
                VariablesRating variableTipoProductoServicio =
                        new VariablesRating(listaVariablesSubjetivo.get(1).
                                getNombre(), 1, this.retrieveData(object[58]));
                VariablesRating variableGerenciaCapcidadProfesionalidadExperiencia =
                        new VariablesRating(listaVariablesSubjetivo.get(2).
                                getNombre(), 1, this.retrieveData(object[59]));
                VariablesRating varaibleAbanicoBancario =
                        new VariablesRating(listaVariablesSubjetivo.get(3).
                                getNombre(), 1, this.retrieveData(object[60]));
                VariablesRating variableMecanismosFinanciacion =
                        new VariablesRating(listaVariablesSubjetivo.get(4).
                                getNombre(), 1, this.retrieveData(object[61]));
                VariablesRating variableEstrcuturaCostos =
                        new VariablesRating(listaVariablesSubjetivo.get(5).
                                getNombre(), 1, this.retrieveData(object[62]));
                VariablesRating variableCapacidadAtenderCalendario =
                        new VariablesRating(listaVariablesSubjetivo.get(6).
                                getNombre(), 1, this.retrieveData(object[63]));
                VariablesRating variableGradoAutofinanciacion =
                        new VariablesRating(listaVariablesSubjetivo.get(7).
                                getNombre(), 1, this.retrieveData(object[64]));
                VariablesRating variableExistenciaDeudasCompromisos =
                        new VariablesRating(listaVariablesSubjetivo.get(8).
                                getNombre(), 1, this.retrieveData(object[65]));
                VariablesRating variablePerfilPagoDeuda =
                        new VariablesRating(listaVariablesSubjetivo.get(9).
                                getNombre(), 1, this.retrieveData(object[66]));
                VariablesRating variableCalidadActivos =
                        new VariablesRating(listaVariablesSubjetivo.get(10).
                                getNombre(), 1, this.retrieveData(object[67]));
                VariablesRating variableTipoAccionista =
                        new VariablesRating(listaVariablesSubjetivo.get(11).
                                getNombre(), 1, this.retrieveData(object[68]));
                
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
                
                listaVariables.add(variableNit);
                listaVariables.add(variableApalancamiento);
                listaVariables.add(variableEbitda);
                listaVariables.add(variableCfl);
                listaVariables.add(variableLiquidez);
                listaVariables.add(variableCfo);
                listaVariables.add(variableMargenEbitda);
                listaVariables.add(variableRotacionClientesDias);
                listaVariables.add(variableRotacionInventariosDias);
                listaVariables.add(variableRotacionProveedoresDias);
                
                VariablesRating variableCalificacion =
                        new VariablesRating(listaVariablesComportamiento.get(0).
                                getNombre(), 2, this.retrieveData(object[45]));
                VariablesRating variableGarantia =
                        new VariablesRating(listaVariablesComportamiento.get(1).
                                getNombre(), 2, this.retrieveData(object[46]));
                VariablesRating variableIndicadorMora =
                        new VariablesRating(listaVariablesComportamiento.get(2).
                                getNombre(), 2, this.retrieveData(object[47]));
                VariablesRating variableNumeroBancos =
                        new VariablesRating(listaVariablesComportamiento.get(3).
                                getNombre(), 2, this.retrieveData(object[48]));
                VariablesRating variableMarcacionReestructuracion =
                        new VariablesRating(listaVariablesComportamiento.get(4).
                                getNombre(), 2, this.retrieveData(object[49]));
                
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
            }    Iterator i = listaTemporal.iterator();
            while(i.hasNext()){
                
                Object[] object = (Object[]) i.next();
                RatingInfo  objetoAgregado = new RatingInfo();
                
                objetoAgregado.setId(Integer.parseInt(((BigDecimal) object[0]).toString()));
                objetoAgregado.setRazonSocial(this.retrieveData(object[1]));
                objetoAgregado.setGrupoEconomico(this.retrieveData(object[2]));
                objetoAgregado.setNit(this.retrieveData(object[3]));
                objetoAgregado.setValorRating(this.retrieveDataNumeric(object[4]));
                objetoAgregado.setValorRatingFinal(this.retrieveDataNumeric(object[5]));
                objetoAgregado.setEstado(this.retrieveData(object[7]));
                objetoAgregado.setApalancamiento(this.retrieveDataNumeric(object[8]));
                objetoAgregado.setEbitda(this.retrieveDataNumeric(object[9]));
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
                objetoAgregado.setFechaInsercion(
                        new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").
                                format(
                                        object[57] == null ? new Date() :
                                                ((Timestamp) object[57])));
                objetoAgregado.setComentariosUsuario(this.retrieveData(object[58]));
                
                listaRating.add(objetoAgregado);
            }
        }
        
        return listVariablesRatingTotal;
    }
     
    public boolean confirmarRating(BigDecimal valorRatingAnterior, 
            BigDecimal valorRatingDefinitivo, BigDecimal id, String comentario){
        Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET "
                + "estado = 'Definitivo', valor_rating_final = ?, "
                + "comentarios_usuario = ? WHERE id = ?");
        return  q.setParameter(1, valorRatingDefinitivo).
                setParameter(2, comentario).
                setParameter(3, id).executeUpdate() != 0;
    }
    
    public BigDecimal retrieveDataNumeric(Object validate){
        return validate == null ? new BigDecimal("0") : (BigDecimal) validate;
    }
   
    public String retrieveData(Object validate){
        return validate == null ? "" : validate.toString();
    }
}