/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.ClientesRating;
import entidades.GruposClientes;
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
        
        List<GruposClientes> listaGrupos = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select codigo_grupo, nombre "
                + "from grupos_economicos order by codigo_grupo asc").
                getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            GruposClientes objetoAgregado = new GruposClientes();
            objetoAgregado.setCodigoGrupo(Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setNombreGrupo((String) object[1]);
            listaGrupos.add(objetoAgregado);
        }
        return listaGrupos; 
    } 
    
    public List<List<VariablesRating>> listarVariablesRating(){
        
        List<List<VariablesRating>> listaVariablesModulo = new ArrayList<>();
        List<VariablesRating> listaVariables = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select id, id_modulo, "
                + "nombre, beta, id_variable from ri.variable_modulo order "
                + "by id_modulo, id_variable asc").getResultList();
        Iterator i = listaProvisional.iterator();
       
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            VariablesRating objetoAgregado = new VariablesRating();
            objetoAgregado.setNombre((String) object[2]);
            objetoAgregado.setIdModulo(((Integer)object[1]));
        
            listaVariables.add(objetoAgregado);
        }
        listaVariablesModulo.add(listarVariablesSubjetivo(listaVariables));
        listaVariablesModulo.add(listarVariablesResultado(listaVariables));
        
        return listaVariablesModulo;
    }
    
    public List<List<VariablesRating>> listarRespuestasVariablesRating(){
        
        List<List<VariablesRating>> listaRespuestasVariablesModulo = 
                new ArrayList<>();
        List<VariablesRating> listaRespuestasVariables = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select id_modulo, id_variable, "
                + "respuesta, nombre from ri.informacion_respuestas_variables_rating_vm "
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
        listaRespuestasVariablesModulo.add(listarRespuestasVariablesSubjetivo(listaRespuestasVariables));
        
        return listaRespuestasVariablesModulo;
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
    
     public List<VariablesRating> listarRespuestasVariablesSubjetivo(List<VariablesRating> listaRespuestasVariables){
        
        List<VariablesRating> listaRespuestasVariablesSubjetivo = new ArrayList<>();
        
        for (int i = 0; i < listaRespuestasVariables.size(); i++) {
            if (listaRespuestasVariables.get(i).getIdModulo() == 4) {
                VariablesRating objetoAgregado = new VariablesRating();
                Map<String, String> resp = new HashMap<>();
                objetoAgregado.setIdModulo(listaRespuestasVariables.get(i).getIdModulo());
                objetoAgregado.setNombre(listaRespuestasVariables.get(i).getNombre());
                objetoAgregado.setRespuesta(listaRespuestasVariables.get(i).getRespuesta());
                resp.put(listaRespuestasVariables.get(i).getRespuesta(), listaRespuestasVariables.get(i).getRespuesta());
                objetoAgregado.setMapRespuesta(resp);
                listaRespuestasVariablesSubjetivo.add(objetoAgregado);
            } 
        }
        return listaRespuestasVariablesSubjetivo;
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
        listaVariablesResultado.get(0).getNombre();
        return listaVariablesResultado;
    }
    
    public List<ClientesRating> consultaClienteGrupoResultadoSinResRating(String tipoConsulta, 
            String parametro){
        String query = "SELECT id, nit, nombre, nombre_grupo, valor_rating, "
                + "valor_rating_final, num_caso, estado, fecha_insercion "
                + "FROM ri.informacion_cliente_grupo_rating_vw WHERE ";
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
      
        listaProvisional = em.createNativeQuery(query).setParameter(1, 
                tipoConsulta.equals("2") ? Integer.parseInt(parametro) : 
                        parametro).getResultList();
        
        i = listaProvisional.iterator();
        
        while(i.hasNext()){
            
            Object[] object = (Object[]) i.next();
            BigDecimal valorProvisional = object[5] == null ? new BigDecimal("0"): ((BigDecimal) object[5]);
            Timestamp fechaProvisional = object[8] == null ? null : ((Timestamp) object[8]);
            
            ClientesRating objetoAgregado = new ClientesRating();
            objetoAgregado.setId(String.valueOf(object[0]));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setNombre((String) object[2]);
            objetoAgregado.setGrupo((String) object[3]);
            objetoAgregado.setValorRating(valorProvisional.toString()); 
            if(fechaProvisional != null){
                objetoAgregado.setFechaInsercion(new 
        SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").format(fechaProvisional));
            } 
            else {
                objetoAgregado.setFechaInsercion("No disponible");
            } 
            listaClientes.add(objetoAgregado);
        }
        return listaClientes;
    }
    
    public boolean registrarSubjetivo(String cliente, String tipoProducto, 
            String gerenciaCapacidad, String abanicoBancario, String mecanismoFinanciacion, 
            String estructuraCostos, String capacidadAtenderCalendario, 
            String gradoAutoFinanciacion, String existenciaDeuda, String perfilPago, 
            String calidadActivos, String tipoAccionista,String usuario){
    
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