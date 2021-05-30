/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import dao.ConnectionFactory;
import entidades.ClientesRating;
import entidades.GruposClientes;
import entidades.RatingInfo;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author x356167
 */
@Stateless
public class VariablesRatingFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VariablesRatingFacade() {
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


    public List<ClientesRating> consultaClientes(String tipoConsulta, String parametro, String periodoFiscal){
        
        String query = "SELECT id, nit, razon_social, codigo_grupo, nombre_grupo, valor_rating, fecha_reporte FROM ri.Informacion_Clientes_Rating_VW WHERE periodo_fiscal = TO_DATE(?,'YYYY-MM-DD') AND";
        List listaProvisional;
        List<ClientesRating> listaClientes = new ArrayList<ClientesRating>();
        ClientesRating objetoAgregado = null;
        Iterator i;
        BigDecimal valorProvisional = new BigDecimal("0");
        Timestamp fechaProvisional = null;
        
        switch(Integer.parseInt(tipoConsulta)){
            
            case 0:
                query += " nit = ?";
                break;
            
            case 1:
                query += " razon_social = ?";
                break;
            
        }
        
        query += " ORDER BY fecha_reporte desc LIMIT 1";
        listaProvisional = em.createNativeQuery(query).setParameter(1, periodoFiscal).setParameter(2, tipoConsulta.equals("2") ? Integer.parseInt(parametro) : parametro).getResultList();
        i = listaProvisional.iterator();
        
        
        while(i.hasNext()){
            
            Object[] object = (Object[]) i.next();
            
            valorProvisional = object[5] == null ? new BigDecimal("0"): ((BigDecimal) object[5]);
            fechaProvisional = object[6] == null ? null : ((Timestamp) object[6]);
            
            objetoAgregado = new ClientesRating();
            objetoAgregado.setId((String) object[0]);
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setNombre((String) object[2]);
            objetoAgregado.setIdGrupo(Integer.parseInt(((BigDecimal) object[3]).toString()));
            objetoAgregado.setGrupo((String) object[4]);
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

    public List<RatingInfo> calcularRating(String[] listaNits, String periodo, String usuario) throws SQLException{
        
        
        Connection conn = new ConnectionFactory().getConnection();
        System.out.println("Inputs::: "+ listaNits[0] + " " + usuario);
        List<RatingInfo> listaRating = new ArrayList<RatingInfo>();
        Query q = em.createNativeQuery("SELECT ri.calcular_rating_fn(?,?)");
        
        //-----------
        String jpql = null;
        
       // Query q = em.createNativeQuery("SELECT ri.calcular_rating_fn(?,?,?)");
        List listaProvisional1 = q.setParameter(1, conn.createArrayOf("text", listaNits)).setParameter(2, usuario).getResultList();
        //-----------
        
        //List listaProvisional = q.setParameter(1, conn.createArrayOf("text", listaNits)).setParameter(2, usuario).getResultList();
        
        RatingInfo objetoAgregado = null;
        
        Query q1 = em.createNativeQuery("SELECT * FROM ri.Informacion_Rating_Plus_VW WHERE usuario = ? AND nit = ANY(?) AND id IN (SELECT MAX(id) FROM ri.Informacion_Rating_Plus_VW WHERE nit = ANY(?) GROUP BY nit)");
        
        List listaTemporal = q1.setParameter(1, usuario).setParameter(2, conn.createArrayOf("text", listaNits)).setParameter(3, conn.createArrayOf("text", listaNits)).getResultList();
        Iterator i = listaTemporal.iterator();
        
        while(i.hasNext()){
        
            Object[] object = (Object[]) i.next();
            objetoAgregado = new RatingInfo();
        
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
            objetoAgregado.setNumeroCaso(Integer.parseInt(((BigDecimal) object[6]).toString()));
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
        
        return listaRating;
    
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
    
    public boolean confirmarRating(BigDecimal valorRatingAnterior, BigDecimal valorRatingDefinitivo, BigDecimal id){
    
        Query q = em.createNativeQuery("UPDATE ri.resultados_rating_plus SET estado = 'Definitivo', valor_rating = ?, valor_rating_final = ? WHERE id = ?");
        return  q.setParameter(1, valorRatingAnterior).setParameter(2, valorRatingDefinitivo).setParameter(3, id).executeUpdate() != 0;
        
    }
    
}