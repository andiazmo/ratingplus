/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.GruposClientes;
import entidades.RatingInfo;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    public List<RatingInfo> getResultadosRating() {
        List<RatingInfo> listaResultadosRating;
        listaResultadosRating = new ArrayList<>();
        RatingInfo objetoAgregado;
        
        String query = "SELECT rrrc.*, ru.nombres FROM ri.reporte_resultados_rating_cliente_vw rrrc, "
                + "rp_usuarios ru WHERE rrrc.usuario = ru.codigo order by id desc";
         
         List listaProvisional = em.createNativeQuery(query).getResultList();
         Iterator i = listaProvisional.iterator();
 
         while(i.hasNext()){
            Object[] object = (Object[]) i.next();
            objetoAgregado = new RatingInfo();
            BigDecimal valorRatingProvisional = object[2] == null ? new BigDecimal("0"): ((BigDecimal) object[2]);
            BigDecimal valorRatingFinalProvisional = object[3] == null ? new BigDecimal("0"): ((BigDecimal) object[3]);
            BigDecimal valorInterceptoSumatoria = object[10] == null ? new BigDecimal("0"): ((BigDecimal) object[10]);
            BigDecimal valorSumatoriaWOE = object[19] == null ? new BigDecimal("0"): ((BigDecimal) object[19]);
            BigDecimal valorSumatoriaBetas = object[20] == null ? new BigDecimal("0"): ((BigDecimal) object[20]);
            BigDecimal valorBetaSubjetivo = object[21] == null ? new BigDecimal("0"): ((BigDecimal) object[21]);
            BigDecimal alpha = object[22] == null ? new BigDecimal("0"): ((BigDecimal) object[22]);
            BigDecimal beta = object[23] == null ? new BigDecimal("0"): ((BigDecimal) object[23]);
            BigDecimal logOdds = object[24] == null ? new BigDecimal("0"): ((BigDecimal) object[24]);
            
            objetoAgregado.setId(Integer.parseInt(((BigDecimal) object[0]).toString()));
            objetoAgregado.setNit((String) object[1]);
            objetoAgregado.setValorRating(valorRatingProvisional);
            objetoAgregado.setValorRatingFinal(valorRatingFinalProvisional);
            objetoAgregado.setNumeroCaso(Integer.parseInt((object[4]).toString()));
            objetoAgregado.setEstado((String) object[5]);
            objetoAgregado.setComentarioUsuario((String) object[27]);
            objetoAgregado.setRazonSocial((String) object[78]);
            objetoAgregado.setGrupoEconomico((String) object[80]);
            objetoAgregado.setUsuarioBanco((String) object[81]);
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
            
            objetoAgregado.setFechaInsercion(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss aa").format(object[26] == null ? new Date() : ((Timestamp) object[26])));
            
            listaResultadosRating.add(objetoAgregado);
        }
        return listaResultadosRating;
    }
    
}