/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.ClientesRating;
import entidades.GruposClientes;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.rpc.ServiceException;
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
    
    public List<ClientesRating> listarClientes(){
        List<ClientesRating> listaClientes = new ArrayList<>();
        
        List listaProvisional = em.createNativeQuery("select grupo, "
                + "nombre, nit from clientes "
                + "order by nit asc").getResultList();
        Iterator i = listaProvisional.iterator();
        
        while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            ClientesRating objetoAgregado = new ClientesRating();
            objetoAgregado.setIdGrupo
        (Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setNombre((String) object[1]);
            objetoAgregado.setNit((String) object[2]);
            listaClientes.add(objetoAgregado);
        }
        return listaClientes;
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
                query += "WHERE nombreempresa = ?";
                break;
        }
      
        Query q = em.createNativeQuery(query);
        List listaProvisional = q.setParameter(1, parametro).getResultList();
        
        List<String> listaPeriodos = new ArrayList<>();
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