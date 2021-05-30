/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeferson Camargo
 */
@Entity
@Table(name = "ri.historicoreeff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicos_eeff.findAll", query = "SELECT h FROM Historicos_eeff h")
    , @NamedQuery(name = "Historicos_eeff.findByNit", query = "SELECT h FROM Historicos_eeff h WHERE h.nit = :nit")
    , @NamedQuery(name = "Historicos_eeff.findByNombreempresa", query = "SELECT h FROM Historicos_eeff h WHERE h.nombreempresa = :nombreempresa")
    , @NamedQuery(name = "Historicos_eeff.findBySector", query = "SELECT h FROM Historicos_eeff h WHERE h.sector = :sector")
    , @NamedQuery(name = "Historicos_eeff.findByRating", query = "SELECT h FROM Historicos_eeff h WHERE h.rating = :rating")
    , @NamedQuery(name = "Historicos_eeff.findByFechaacta", query = "SELECT h FROM Historicos_eeff h WHERE h.fechaacta = :fechaacta")
    , @NamedQuery(name = "Historicos_eeff.findByEstadoaprobacion", query = "SELECT h FROM Historicos_eeff h WHERE h.estadoaprobacion = :estadoaprobacion")
    , @NamedQuery(name = "Historicos_eeff.findByAniobalance", query = "SELECT h FROM Historicos_eeff h WHERE h.aniobalance = :aniobalance")
    , @NamedQuery(name = "Historicos_eeff.findByIngresosnetos", query = "SELECT h FROM Historicos_eeff h WHERE h.ingresosnetos = :ingresosnetos")
    , @NamedQuery(name = "Historicos_eeff.findByCostedeventas", query = "SELECT h FROM Historicos_eeff h WHERE h.costedeventas = :costedeventas")
    , @NamedQuery(name = "Historicos_eeff.findByResultadobruto", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadobruto = :resultadobruto")
    , @NamedQuery(name = "Historicos_eeff.findByGastosgenerales", query = "SELECT h FROM Historicos_eeff h WHERE h.gastosgenerales = :gastosgenerales")
    , @NamedQuery(name = "Historicos_eeff.findByOtrosingresosGastosOp", query = "SELECT h FROM Historicos_eeff h WHERE h.otrosingresosGastosOp = :otrosingresosGastosOp")
    , @NamedQuery(name = "Historicos_eeff.findByProvisiones", query = "SELECT h FROM Historicos_eeff h WHERE h.provisiones = :provisiones")
    , @NamedQuery(name = "Historicos_eeff.findByResultadoexplotacionEbitda", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadoexplotacionEbitda = :resultadoexplotacionEbitda")
    , @NamedQuery(name = "Historicos_eeff.findByMargenebitda", query = "SELECT h FROM Historicos_eeff h WHERE h.margenebitda = :margenebitda")
    , @NamedQuery(name = "Historicos_eeff.findByAmortizaciones", query = "SELECT h FROM Historicos_eeff h WHERE h.amortizaciones = :amortizaciones")
    , @NamedQuery(name = "Historicos_eeff.findByResultadooperativoEbit", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadooperativoEbit = :resultadooperativoEbit")
    , @NamedQuery(name = "Historicos_eeff.findByMargenebit", query = "SELECT h FROM Historicos_eeff h WHERE h.margenebit = :margenebit")
    , @NamedQuery(name = "Historicos_eeff.findByIngresossociedadesgrupo", query = "SELECT h FROM Historicos_eeff h WHERE h.ingresossociedadesgrupo = :ingresossociedadesgrupo")
    , @NamedQuery(name = "Historicos_eeff.findByOtrosingresosfinancieros", query = "SELECT h FROM Historicos_eeff h WHERE h.otrosingresosfinancieros = :otrosingresosfinancieros")
    , @NamedQuery(name = "Historicos_eeff.findByGastosfinancieros", query = "SELECT h FROM Historicos_eeff h WHERE h.gastosfinancieros = :gastosfinancieros")
    , @NamedQuery(name = "Historicos_eeff.findByResultadofinanciero", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadofinanciero = :resultadofinanciero")
    , @NamedQuery(name = "Historicos_eeff.findByAjustescambiariosInflacion", query = "SELECT h FROM Historicos_eeff h WHERE h.ajustescambiariosInflacion = :ajustescambiariosInflacion")
    , @NamedQuery(name = "Historicos_eeff.findByAmortFondocomercioconsolidacion", query = "SELECT h FROM Historicos_eeff h WHERE h.amortFondocomercioconsolidacion = :amortFondocomercioconsolidacion")
    , @NamedQuery(name = "Historicos_eeff.findByResultadospuestaenequivalencia", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadospuestaenequivalencia = :resultadospuestaenequivalencia")
    , @NamedQuery(name = "Historicos_eeff.findByResultadoordinario", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadoordinario = :resultadoordinario")
    , @NamedQuery(name = "Historicos_eeff.findByResultadoextraordinario", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadoextraordinario = :resultadoextraordinario")
    , @NamedQuery(name = "Historicos_eeff.findByResultadoantesdeimpuestos", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadoantesdeimpuestos = :resultadoantesdeimpuestos")
    , @NamedQuery(name = "Historicos_eeff.findByImpuestos", query = "SELECT h FROM Historicos_eeff h WHERE h.impuestos = :impuestos")
    , @NamedQuery(name = "Historicos_eeff.findByResultadoneto", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadoneto = :resultadoneto")
    , @NamedQuery(name = "Historicos_eeff.findByMargenneto", query = "SELECT h FROM Historicos_eeff h WHERE h.margenneto = :margenneto")
    , @NamedQuery(name = "Historicos_eeff.findByInteresesminoritarios", query = "SELECT h FROM Historicos_eeff h WHERE h.interesesminoritarios = :interesesminoritarios")
    , @NamedQuery(name = "Historicos_eeff.findByResultadoatribuible", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadoatribuible = :resultadoatribuible")
    , @NamedQuery(name = "Historicos_eeff.findByDividendosaprobados", query = "SELECT h FROM Historicos_eeff h WHERE h.dividendosaprobados = :dividendosaprobados")
    , @NamedQuery(name = "Historicos_eeff.findByBeneficioretenido", query = "SELECT h FROM Historicos_eeff h WHERE h.beneficioretenido = :beneficioretenido")
    , @NamedQuery(name = "Historicos_eeff.findByNoDeacciones", query = "SELECT h FROM Historicos_eeff h WHERE h.noDeacciones = :noDeacciones")
    , @NamedQuery(name = "Historicos_eeff.findByBeneficioporaccion", query = "SELECT h FROM Historicos_eeff h WHERE h.beneficioporaccion = :beneficioporaccion")
    , @NamedQuery(name = "Historicos_eeff.findByDividendoporaccion", query = "SELECT h FROM Historicos_eeff h WHERE h.dividendoporaccion = :dividendoporaccion")
    , @NamedQuery(name = "Historicos_eeff.findByPayOut", query = "SELECT h FROM Historicos_eeff h WHERE h.payOut = :payOut")
    , @NamedQuery(name = "Historicos_eeff.findByAniobalanceRo", query = "SELECT h FROM Historicos_eeff h WHERE h.aniobalanceRo = :aniobalanceRo")
    , @NamedQuery(name = "Historicos_eeff.findByResultadoperativoEbit", query = "SELECT h FROM Historicos_eeff h WHERE h.resultadoperativoEbit = :resultadoperativoEbit")
    , @NamedQuery(name = "Historicos_eeff.findByAmortizacionesProvisiones", query = "SELECT h FROM Historicos_eeff h WHERE h.amortizacionesProvisiones = :amortizacionesProvisiones")
    , @NamedQuery(name = "Historicos_eeff.findByVariacioncapitalcirculanteoperativo", query = "SELECT h FROM Historicos_eeff h WHERE h.variacioncapitalcirculanteoperativo = :variacioncapitalcirculanteoperativo")
    , @NamedQuery(name = "Historicos_eeff.findByOtrosajustesRo", query = "SELECT h FROM Historicos_eeff h WHERE h.otrosajustesRo = :otrosajustesRo")
    , @NamedQuery(name = "Historicos_eeff.findByImpuestosRo", query = "SELECT h FROM Historicos_eeff h WHERE h.impuestosRo = :impuestosRo")
    , @NamedQuery(name = "Historicos_eeff.findByCfoperativoCfo", query = "SELECT h FROM Historicos_eeff h WHERE h.cfoperativoCfo = :cfoperativoCfo")
    , @NamedQuery(name = "Historicos_eeff.findByCapex", query = "SELECT h FROM Historicos_eeff h WHERE h.capex = :capex")
    , @NamedQuery(name = "Historicos_eeff.findByDesinversionesporventadeactivos", query = "SELECT h FROM Historicos_eeff h WHERE h.desinversionesporventadeactivos = :desinversionesporventadeactivos")
    , @NamedQuery(name = "Historicos_eeff.findByInversionesfinancieras", query = "SELECT h FROM Historicos_eeff h WHERE h.inversionesfinancieras = :inversionesfinancieras")
    , @NamedQuery(name = "Historicos_eeff.findByDesinversionesfinancieras", query = "SELECT h FROM Historicos_eeff h WHERE h.desinversionesfinancieras = :desinversionesfinancieras")
    , @NamedQuery(name = "Historicos_eeff.findByCobrosfinancieros", query = "SELECT h FROM Historicos_eeff h WHERE h.cobrosfinancieros = :cobrosfinancieros")
    , @NamedQuery(name = "Historicos_eeff.findByPrestamosalgrupo", query = "SELECT h FROM Historicos_eeff h WHERE h.prestamosalgrupo = :prestamosalgrupo")
    , @NamedQuery(name = "Historicos_eeff.findByOtrasinversionesDesinversiones", query = "SELECT h FROM Historicos_eeff h WHERE h.otrasinversionesDesinversiones = :otrasinversionesDesinversiones")
    , @NamedQuery(name = "Historicos_eeff.findByCfaplicadoainversionCfi", query = "SELECT h FROM Historicos_eeff h WHERE h.cfaplicadoainversionCfi = :cfaplicadoainversionCfi")
    , @NamedQuery(name = "Historicos_eeff.findByCfantesdefinanciacionCfafCfoCfi", query = "SELECT h FROM Historicos_eeff h WHERE h.cfantesdefinanciacionCfafCfoCfi = :cfantesdefinanciacionCfafCfoCfi")
    , @NamedQuery(name = "Historicos_eeff.findByDividendospagados", query = "SELECT h FROM Historicos_eeff h WHERE h.dividendospagados = :dividendospagados")
    , @NamedQuery(name = "Historicos_eeff.findByVariacionnetadeudaalP", query = "SELECT h FROM Historicos_eeff h WHERE h.variacionnetadeudaalP = :variacionnetadeudaalP")
    , @NamedQuery(name = "Historicos_eeff.findByVariacionnetadeudaacP", query = "SELECT h FROM Historicos_eeff h WHERE h.variacionnetadeudaacP = :variacionnetadeudaacP")
    , @NamedQuery(name = "Historicos_eeff.findByPagosfinancierosIntereses", query = "SELECT h FROM Historicos_eeff h WHERE h.pagosfinancierosIntereses = :pagosfinancierosIntereses")
    , @NamedQuery(name = "Historicos_eeff.findByVariacionderecursospropios", query = "SELECT h FROM Historicos_eeff h WHERE h.variacionderecursospropios = :variacionderecursospropios")
    , @NamedQuery(name = "Historicos_eeff.findByOtrosajustescf", query = "SELECT h FROM Historicos_eeff h WHERE h.otrosajustescf = :otrosajustescf")
    , @NamedQuery(name = "Historicos_eeff.findByCfderivadodefinanciacionCff", query = "SELECT h FROM Historicos_eeff h WHERE h.cfderivadodefinanciacionCff = :cfderivadodefinanciacionCff")
    , @NamedQuery(name = "Historicos_eeff.findByCfdespuesdefinanciacionCfdfCfafCff", query = "SELECT h FROM Historicos_eeff h WHERE h.cfdespuesdefinanciacionCfdfCfafCff = :cfdespuesdefinanciacionCfdfCfafCff")
    , @NamedQuery(name = "Historicos_eeff.findByEfectodelasdiferenciasdecambioentesoreria", query = "SELECT h FROM Historicos_eeff h WHERE h.efectodelasdiferenciasdecambioentesoreria = :efectodelasdiferenciasdecambioentesoreria")
    , @NamedQuery(name = "Historicos_eeff.findByAumentoDisminucionnetadetesoreria", query = "SELECT h FROM Historicos_eeff h WHERE h.aumentoDisminucionnetadetesoreria = :aumentoDisminucionnetadetesoreria")
    , @NamedQuery(name = "Historicos_eeff.findByCajaaliniciodelejercicio", query = "SELECT h FROM Historicos_eeff h WHERE h.cajaaliniciodelejercicio = :cajaaliniciodelejercicio")
    , @NamedQuery(name = "Historicos_eeff.findByCajaalfinaldelperiodo", query = "SELECT h FROM Historicos_eeff h WHERE h.cajaalfinaldelperiodo = :cajaalfinaldelperiodo")
    , @NamedQuery(name = "Historicos_eeff.findByNetworkinginvestment", query = "SELECT h FROM Historicos_eeff h WHERE h.networkinginvestment = :networkinginvestment")
    , @NamedQuery(name = "Historicos_eeff.findByAniobalanceTaf", query = "SELECT h FROM Historicos_eeff h WHERE h.aniobalanceTaf = :aniobalanceTaf")
    , @NamedQuery(name = "Historicos_eeff.findByTesoreriaActivosfacilmenteliquidables", query = "SELECT h FROM Historicos_eeff h WHERE h.tesoreriaActivosfacilmenteliquidables = :tesoreriaActivosfacilmenteliquidables")
    , @NamedQuery(name = "Historicos_eeff.findByCuentasacobrarAdt", query = "SELECT h FROM Historicos_eeff h WHERE h.cuentasacobrarAdt = :cuentasacobrarAdt")
    , @NamedQuery(name = "Historicos_eeff.findByExistencias", query = "SELECT h FROM Historicos_eeff h WHERE h.existencias = :existencias")
    , @NamedQuery(name = "Historicos_eeff.findByAdministracionespublicas", query = "SELECT h FROM Historicos_eeff h WHERE h.administracionespublicas = :administracionespublicas")
    , @NamedQuery(name = "Historicos_eeff.findByOtrosactivoscirculantes", query = "SELECT h FROM Historicos_eeff h WHERE h.otrosactivoscirculantes = :otrosactivoscirculantes")
    , @NamedQuery(name = "Historicos_eeff.findByTotalactivocirculante", query = "SELECT h FROM Historicos_eeff h WHERE h.totalactivocirculante = :totalactivocirculante")
    , @NamedQuery(name = "Historicos_eeff.findByInmovilizadomaterialneto", query = "SELECT h FROM Historicos_eeff h WHERE h.inmovilizadomaterialneto = :inmovilizadomaterialneto")
    , @NamedQuery(name = "Historicos_eeff.findByActivosintangibles", query = "SELECT h FROM Historicos_eeff h WHERE h.activosintangibles = :activosintangibles")
    , @NamedQuery(name = "Historicos_eeff.findByInmovilizadofinanciero", query = "SELECT h FROM Historicos_eeff h WHERE h.inmovilizadofinanciero = :inmovilizadofinanciero")
    , @NamedQuery(name = "Historicos_eeff.findByOtrosactivoslP", query = "SELECT h FROM Historicos_eeff h WHERE h.otrosactivoslP = :otrosactivoslP")
    , @NamedQuery(name = "Historicos_eeff.findByTotalactivofijo", query = "SELECT h FROM Historicos_eeff h WHERE h.totalactivofijo = :totalactivofijo")
    , @NamedQuery(name = "Historicos_eeff.findByTotalactivo", query = "SELECT h FROM Historicos_eeff h WHERE h.totalactivo = :totalactivo")
    , @NamedQuery(name = "Historicos_eeff.findByActivoscontingentes", query = "SELECT h FROM Historicos_eeff h WHERE h.activoscontingentes = :activoscontingentes")
    , @NamedQuery(name = "Historicos_eeff.findByDeudafinancieraacP", query = "SELECT h FROM Historicos_eeff h WHERE h.deudafinancieraacP = :deudafinancieraacP")
    , @NamedQuery(name = "Historicos_eeff.findByDeudagrupocP", query = "SELECT h FROM Historicos_eeff h WHERE h.deudagrupocP = :deudagrupocP")
    , @NamedQuery(name = "Historicos_eeff.findByCuentasapagar", query = "SELECT h FROM Historicos_eeff h WHERE h.cuentasapagar = :cuentasapagar")
    , @NamedQuery(name = "Historicos_eeff.findByCuentasapagaralaadministracion", query = "SELECT h FROM Historicos_eeff h WHERE h.cuentasapagaralaadministracion = :cuentasapagaralaadministracion")
    , @NamedQuery(name = "Historicos_eeff.findByOtrospasivoscP", query = "SELECT h FROM Historicos_eeff h WHERE h.otrospasivoscP = :otrospasivoscP")
    , @NamedQuery(name = "Historicos_eeff.findByTotalexigibleacorto", query = "SELECT h FROM Historicos_eeff h WHERE h.totalexigibleacorto = :totalexigibleacorto")
    , @NamedQuery(name = "Historicos_eeff.findByDeudafinancieraalP", query = "SELECT h FROM Historicos_eeff h WHERE h.deudafinancieraalP = :deudafinancieraalP")
    , @NamedQuery(name = "Historicos_eeff.findByDeudagrupoalP", query = "SELECT h FROM Historicos_eeff h WHERE h.deudagrupoalP = :deudagrupoalP")
    , @NamedQuery(name = "Historicos_eeff.findByOtrospasivoslP", query = "SELECT h FROM Historicos_eeff h WHERE h.otrospasivoslP = :otrospasivoslP")
    , @NamedQuery(name = "Historicos_eeff.findByProvisionesTec", query = "SELECT h FROM Historicos_eeff h WHERE h.provisionesTec = :provisionesTec")
    , @NamedQuery(name = "Historicos_eeff.findByTotalexigiblealargo", query = "SELECT h FROM Historicos_eeff h WHERE h.totalexigiblealargo = :totalexigiblealargo")
    , @NamedQuery(name = "Historicos_eeff.findByCapitalsocial", query = "SELECT h FROM Historicos_eeff h WHERE h.capitalsocial = :capitalsocial")
    , @NamedQuery(name = "Historicos_eeff.findByReservasacumuladas", query = "SELECT h FROM Historicos_eeff h WHERE h.reservasacumuladas = :reservasacumuladas")
    , @NamedQuery(name = "Historicos_eeff.findBySociosexternos", query = "SELECT h FROM Historicos_eeff h WHERE h.sociosexternos = :sociosexternos")
    , @NamedQuery(name = "Historicos_eeff.findByRecursospropios", query = "SELECT h FROM Historicos_eeff h WHERE h.recursospropios = :recursospropios")
    , @NamedQuery(name = "Historicos_eeff.findByTotalexigible", query = "SELECT h FROM Historicos_eeff h WHERE h.totalexigible = :totalexigible")
    , @NamedQuery(name = "Historicos_eeff.findByTotalpasivo", query = "SELECT h FROM Historicos_eeff h WHERE h.totalpasivo = :totalpasivo")
    , @NamedQuery(name = "Historicos_eeff.findByPasivoscontingentes", query = "SELECT h FROM Historicos_eeff h WHERE h.pasivoscontingentes = :pasivoscontingentes")
    , @NamedQuery(name = "Historicos_eeff.findByIndicadores", query = "SELECT h FROM Historicos_eeff h WHERE h.indicadores = :indicadores")
    , @NamedQuery(name = "Historicos_eeff.findByDeudafinancierabruta", query = "SELECT h FROM Historicos_eeff h WHERE h.deudafinancierabruta = :deudafinancierabruta")
    , @NamedQuery(name = "Historicos_eeff.findByDeudafinancieranetaDfn", query = "SELECT h FROM Historicos_eeff h WHERE h.deudafinancieranetaDfn = :deudafinancieranetaDfn")
    , @NamedQuery(name = "Historicos_eeff.findByCapitalizacionFfppAt", query = "SELECT h FROM Historicos_eeff h WHERE h.capitalizacionFfppAt = :capitalizacionFfppAt")
    , @NamedQuery(name = "Historicos_eeff.findByApalancamiento", query = "SELECT h FROM Historicos_eeff h WHERE h.apalancamiento = :apalancamiento")
    , @NamedQuery(name = "Historicos_eeff.findByDfnFfpp", query = "SELECT h FROM Historicos_eeff h WHERE h.dfnFfpp = :dfnFfpp")
    , @NamedQuery(name = "Historicos_eeff.findByDfbEbitda", query = "SELECT h FROM Historicos_eeff h WHERE h.dfbEbitda = :dfbEbitda")
    , @NamedQuery(name = "Historicos_eeff.findByDfnEbitda", query = "SELECT h FROM Historicos_eeff h WHERE h.dfnEbitda = :dfnEbitda")
    , @NamedQuery(name = "Historicos_eeff.findByDfnCfl", query = "SELECT h FROM Historicos_eeff h WHERE h.dfnCfl = :dfnCfl")
    , @NamedQuery(name = "Historicos_eeff.findByLiquidez", query = "SELECT h FROM Historicos_eeff h WHERE h.liquidez = :liquidez")
    , @NamedQuery(name = "Historicos_eeff.findByLiquidezacida", query = "SELECT h FROM Historicos_eeff h WHERE h.liquidezacida = :liquidezacida")
    , @NamedQuery(name = "Historicos_eeff.findByRoeBnetoFfpp", query = "SELECT h FROM Historicos_eeff h WHERE h.roeBnetoFfpp = :roeBnetoFfpp")
    , @NamedQuery(name = "Historicos_eeff.findByRoaBnetoAt", query = "SELECT h FROM Historicos_eeff h WHERE h.roaBnetoAt = :roaBnetoAt")
    , @NamedQuery(name = "Historicos_eeff.findByEbitdaGastosfinancieros", query = "SELECT h FROM Historicos_eeff h WHERE h.ebitdaGastosfinancieros = :ebitdaGastosfinancieros")
    , @NamedQuery(name = "Historicos_eeff.findByCoberturadeinteresCfoperatGastosfinancieros", query = "SELECT h FROM Historicos_eeff h WHERE h.coberturadeinteresCfoperatGastosfinancieros = :coberturadeinteresCfoperatGastosfinancieros")
    , @NamedQuery(name = "Historicos_eeff.findByCapitaldetrabajoActivocirculantePasivocirculante", query = "SELECT h FROM Historicos_eeff h WHERE h.capitaldetrabajoActivocirculantePasivocirculante = :capitaldetrabajoActivocirculantePasivocirculante")
    , @NamedQuery(name = "Historicos_eeff.findByCapitaldetrabajoVentas", query = "SELECT h FROM Historicos_eeff h WHERE h.capitaldetrabajoVentas = :capitaldetrabajoVentas")
    , @NamedQuery(name = "Historicos_eeff.findByCapitaldetrabajooperativo", query = "SELECT h FROM Historicos_eeff h WHERE h.capitaldetrabajooperativo = :capitaldetrabajooperativo")
    , @NamedQuery(name = "Historicos_eeff.findByCapitaldetrabajooperativoVentas", query = "SELECT h FROM Historicos_eeff h WHERE h.capitaldetrabajooperativoVentas = :capitaldetrabajooperativoVentas")
    , @NamedQuery(name = "Historicos_eeff.findByRotaciondeclientesDias", query = "SELECT h FROM Historicos_eeff h WHERE h.rotaciondeclientesDias = :rotaciondeclientesDias")
    , @NamedQuery(name = "Historicos_eeff.findByRotaciondeinventariosDias", query = "SELECT h FROM Historicos_eeff h WHERE h.rotaciondeinventariosDias = :rotaciondeinventariosDias")
    , @NamedQuery(name = "Historicos_eeff.findByRotaciondeproveedoresDias", query = "SELECT h FROM Historicos_eeff h WHERE h.rotaciondeproveedoresDias = :rotaciondeproveedoresDias")
    , @NamedQuery(name = "Historicos_eeff.findByUsuario", query = "SELECT h FROM Historicos_eeff h WHERE h.usuario = :usuario")
    , @NamedQuery(name = "Historicos_eeff.findByVentas", query = "SELECT h FROM Historicos_eeff h WHERE h.ventas = :ventas")
    , @NamedQuery(name = "Historicos_eeff.findByUtilidad", query = "SELECT h FROM Historicos_eeff h WHERE h.utilidad = :utilidad")
    , @NamedQuery(name = "Historicos_eeff.findByTotalActivos", query = "SELECT h FROM Historicos_eeff h WHERE h.totalActivos = :totalActivos")
    , @NamedQuery(name = "Historicos_eeff.findByTotalPasivos", query = "SELECT h FROM Historicos_eeff h WHERE h.totalPasivos = :totalPasivos")
    , @NamedQuery(name = "Historicos_eeff.findByPatrimonio", query = "SELECT h FROM Historicos_eeff h WHERE h.patrimonio = :patrimonio")
    , @NamedQuery(name = "Historicos_eeff.findByTotalIngresos", query = "SELECT h FROM Historicos_eeff h WHERE h.totalIngresos = :totalIngresos")
    , @NamedQuery(name = "Historicos_eeff.findByTotalGastos", query = "SELECT h FROM Historicos_eeff h WHERE h.totalGastos = :totalGastos")
    , @NamedQuery(name = "Historicos_eeff.findByIdResumeneeff", query = "SELECT h FROM Historicos_eeff h WHERE h.idResumeneeff = :idResumeneeff")
    , @NamedQuery(name = "Historicos_eeff.findByFechainsercion", query = "SELECT h FROM Historicos_eeff h WHERE h.fechainsercion = :fechainsercion")
})
public class Historicos_eeff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "nit")
    private String nit;
    @Size(max = 50)
    @Column(name = "nombreempresa")
    private String nombreempresa;
    @Size(max = 50)
    @Column(name = "sector")
    private String sector;
    @Size(max = 12)
    @Column(name = "rating")
    private String rating;
    @Column(name = "fechaacta")
    @Temporal(TemporalType.DATE)
    private Date fechaacta;
    @Size(max = 30)
    @Column(name = "estadoaprobacion")
    private String estadoaprobacion;
    @Column(name = "aniobalance")
    @Temporal(TemporalType.DATE)
    private Date aniobalance;
    @Size(max = 30)
    @Column(name = "ingresosnetos")
    private String ingresosnetos;
    @Size(max = 30)
    @Column(name = "costedeventas")
    private String costedeventas;
    @Size(max = 30)
    @Column(name = "resultadobruto")
    private String resultadobruto;
    @Size(max = 30)
    @Column(name = "gastosgenerales")
    private String gastosgenerales;
    @Size(max = 30)
    @Column(name = "otrosingresos_gastos_op")
    private String otrosingresosGastosOp;
    @Size(max = 30)
    @Column(name = "provisiones")
    private String provisiones;
    @Size(max = 30)
    @Column(name = "resultadoexplotacion_ebitda")
    private String resultadoexplotacionEbitda;
    @Size(max = 30)
    @Column(name = "margenebitda")
    private String margenebitda;
    @Size(max = 30)
    @Column(name = "amortizaciones")
    private String amortizaciones;
    @Size(max = 30)
    @Column(name = "resultadooperativo_ebit")
    private String resultadooperativoEbit;
    @Size(max = 30)
    @Column(name = "margenebit")
    private String margenebit;
    @Size(max = 30)
    @Column(name = "ingresossociedadesgrupo")
    private String ingresossociedadesgrupo;
    @Size(max = 30)
    @Column(name = "otrosingresosfinancieros")
    private String otrosingresosfinancieros;
    @Size(max = 30)
    @Column(name = "gastosfinancieros")
    private String gastosfinancieros;
    @Size(max = 30)
    @Column(name = "resultadofinanciero")
    private String resultadofinanciero;
    @Size(max = 30)
    @Column(name = "ajustescambiarios_inflacion")
    private String ajustescambiariosInflacion;
    @Size(max = 30)
    @Column(name = "amort_fondocomercioconsolidacion")
    private String amortFondocomercioconsolidacion;
    @Size(max = 30)
    @Column(name = "resultadospuestaenequivalencia")
    private String resultadospuestaenequivalencia;
    @Size(max = 30)
    @Column(name = "resultadoordinario")
    private String resultadoordinario;
    @Size(max = 30)
    @Column(name = "resultadoextraordinario")
    private String resultadoextraordinario;
    @Size(max = 30)
    @Column(name = "resultadoantesdeimpuestos")
    private String resultadoantesdeimpuestos;
    @Size(max = 30)
    @Column(name = "impuestos")
    private String impuestos;
    @Size(max = 30)
    @Column(name = "resultadoneto")
    private String resultadoneto;
    @Size(max = 30)
    @Column(name = "margenneto")
    private String margenneto;
    @Size(max = 30)
    @Column(name = "interesesminoritarios")
    private String interesesminoritarios;
    @Size(max = 30)
    @Column(name = "resultadoatribuible")
    private String resultadoatribuible;
    @Size(max = 30)
    @Column(name = "dividendosaprobados")
    private String dividendosaprobados;
    @Size(max = 30)
    @Column(name = "beneficioretenido")
    private String beneficioretenido;
    @Size(max = 30)
    @Column(name = "no_deacciones")
    private String noDeacciones;
    @Size(max = 30)
    @Column(name = "beneficioporaccion")
    private String beneficioporaccion;
    @Size(max = 30)
    @Column(name = "dividendoporaccion")
    private String dividendoporaccion;
    @Size(max = 30)
    @Column(name = "pay_out")
    private String payOut;
    @Column(name = "aniobalance_ro")
    @Temporal(TemporalType.DATE)
    private Date aniobalanceRo;
    @Size(max = 30)
    @Column(name = "resultadoperativo_ebit")
    private String resultadoperativoEbit;
    @Size(max = 30)
    @Column(name = "amortizaciones_provisiones")
    private String amortizacionesProvisiones;
    @Size(max = 30)
    @Column(name = "variacioncapitalcirculanteoperativo")
    private String variacioncapitalcirculanteoperativo;
    @Size(max = 30)
    @Column(name = "otrosajustes_ro")
    private String otrosajustesRo;
    @Size(max = 30)
    @Column(name = "impuestos_ro")
    private String impuestosRo;
    @Size(max = 30)
    @Column(name = "cfoperativo_cfo")
    private String cfoperativoCfo;
    @Size(max = 30)
    @Column(name = "capex")
    private String capex;
    @Size(max = 30)
    @Column(name = "desinversionesporventadeactivos")
    private String desinversionesporventadeactivos;
    @Size(max = 30)
    @Column(name = "inversionesfinancieras")
    private String inversionesfinancieras;
    @Size(max = 30)
    @Column(name = "desinversionesfinancieras")
    private String desinversionesfinancieras;
    @Size(max = 30)
    @Column(name = "cobrosfinancieros")
    private String cobrosfinancieros;
    @Size(max = 30)
    @Column(name = "prestamosalgrupo")
    private String prestamosalgrupo;
    @Size(max = 30)
    @Column(name = "otrasinversiones_desinversiones")
    private String otrasinversionesDesinversiones;
    @Size(max = 30)
    @Column(name = "cfaplicadoainversion_cfi")
    private String cfaplicadoainversionCfi;
    @Size(max = 30)
    @Column(name = "cfantesdefinanciacion_cfaf_cfo_cfi")
    private String cfantesdefinanciacionCfafCfoCfi;
    @Size(max = 30)
    @Column(name = "dividendospagados")
    private String dividendospagados;
    @Size(max = 30)
    @Column(name = "variacionnetadeudaal_p")
    private String variacionnetadeudaalP;
    @Size(max = 30)
    @Column(name = "variacionnetadeudaac_p")
    private String variacionnetadeudaacP;
    @Size(max = 30)
    @Column(name = "pagosfinancieros_intereses")
    private String pagosfinancierosIntereses;
    @Size(max = 30)
    @Column(name = "variacionderecursospropios")
    private String variacionderecursospropios;
    @Size(max = 30)
    @Column(name = "otrosajustescf")
    private String otrosajustescf;
    @Size(max = 30)
    @Column(name = "cfderivadodefinanciacion_cff")
    private String cfderivadodefinanciacionCff;
    @Size(max = 30)
    @Column(name = "cfdespuesdefinanciacion_cfdf_cfaf_cff")
    private String cfdespuesdefinanciacionCfdfCfafCff;
    @Size(max = 30)
    @Column(name = "efectodelasdiferenciasdecambioentesoreria")
    private String efectodelasdiferenciasdecambioentesoreria;
    @Size(max = 30)
    @Column(name = "aumento_disminucionnetadetesoreria")
    private String aumentoDisminucionnetadetesoreria;
    @Size(max = 30)
    @Column(name = "cajaaliniciodelejercicio")
    private String cajaaliniciodelejercicio;
    @Size(max = 30)
    @Column(name = "cajaalfinaldelperiodo")
    private String cajaalfinaldelperiodo;
    @Size(max = 30)
    @Column(name = "networkinginvestment")
    private String networkinginvestment;
    @Column(name = "aniobalance_taf")
    @Temporal(TemporalType.DATE)
    private Date aniobalanceTaf;
    @Size(max = 30)
    @Column(name = "tesoreria_activosfacilmenteliquidables")
    private String tesoreriaActivosfacilmenteliquidables;
    @Size(max = 30)
    @Column(name = "cuentasacobrar_adt")
    private String cuentasacobrarAdt;
    @Size(max = 30)
    @Column(name = "existencias")
    private String existencias;
    @Size(max = 30)
    @Column(name = "administracionespublicas")
    private String administracionespublicas;
    @Size(max = 30)
    @Column(name = "otrosactivoscirculantes")
    private String otrosactivoscirculantes;
    @Size(max = 30)
    @Column(name = "totalactivocirculante")
    private String totalactivocirculante;
    @Size(max = 30)
    @Column(name = "inmovilizadomaterialneto")
    private String inmovilizadomaterialneto;
    @Size(max = 30)
    @Column(name = "activosintangibles")
    private String activosintangibles;
    @Size(max = 30)
    @Column(name = "inmovilizadofinanciero")
    private String inmovilizadofinanciero;
    @Size(max = 30)
    @Column(name = "otrosactivosl_p")
    private String otrosactivoslP;
    @Size(max = 30)
    @Column(name = "totalactivofijo")
    private String totalactivofijo;
    @Size(max = 30)
    @Column(name = "totalactivo")
    private String totalactivo;
    @Size(max = 30)
    @Column(name = "activoscontingentes")
    private String activoscontingentes;
    @Size(max = 30)
    @Column(name = "deudafinancieraac_p")
    private String deudafinancieraacP;
    @Size(max = 30)
    @Column(name = "deudagrupoc_p")
    private String deudagrupocP;
    @Size(max = 30)
    @Column(name = "cuentasapagar")
    private String cuentasapagar;
    @Size(max = 30)
    @Column(name = "cuentasapagaralaadministracion")
    private String cuentasapagaralaadministracion;
    @Size(max = 30)
    @Column(name = "otrospasivosc_p")
    private String otrospasivoscP;
    @Size(max = 30)
    @Column(name = "totalexigibleacorto")
    private String totalexigibleacorto;
    @Size(max = 30)
    @Column(name = "deudafinancieraal_p")
    private String deudafinancieraalP;
    @Size(max = 30)
    @Column(name = "deudagrupoal_p")
    private String deudagrupoalP;
    @Size(max = 30)
    @Column(name = "otrospasivosl_p")
    private String otrospasivoslP;
    @Size(max = 30)
    @Column(name = "provisiones_tec")
    private String provisionesTec;
    @Size(max = 30)
    @Column(name = "totalexigiblealargo")
    private String totalexigiblealargo;
    @Size(max = 30)
    @Column(name = "capitalsocial")
    private String capitalsocial;
    @Size(max = 30)
    @Column(name = "reservasacumuladas")
    private String reservasacumuladas;
    @Size(max = 30)
    @Column(name = "sociosexternos")
    private String sociosexternos;
    @Size(max = 30)
    @Column(name = "recursospropios")
    private String recursospropios;
    @Size(max = 30)
    @Column(name = "totalexigible")
    private String totalexigible;
    @Size(max = 30)
    @Column(name = "totalpasivo")
    private String totalpasivo;
    @Size(max = 30)
    @Column(name = "pasivoscontingentes")
    private String pasivoscontingentes;
    @Size(max = 30)
    @Column(name = "indicadores")
    private String indicadores;
    @Size(max = 30)
    @Column(name = "deudafinancierabruta")
    private String deudafinancierabruta;
    @Size(max = 30)
    @Column(name = "deudafinancieraneta_dfn")
    private String deudafinancieranetaDfn;
    @Size(max = 30)
    @Column(name = "capitalizacion_ffpp_at")
    private String capitalizacionFfppAt;
    @Size(max = 30)
    @Column(name = "apalancamiento")
    private String apalancamiento;
    @Size(max = 30)
    @Column(name = "dfn_ffpp")
    private String dfnFfpp;
    @Size(max = 30)
    @Column(name = "dfb_ebitda")
    private String dfbEbitda;
    @Size(max = 30)
    @Column(name = "dfn_ebitda")
    private String dfnEbitda;
    @Size(max = 30)
    @Column(name = "dfn_cfl")
    private String dfnCfl;
    @Size(max = 30)
    @Column(name = "liquidez")
    private String liquidez;
    @Size(max = 30)
    @Column(name = "liquidezacida")
    private String liquidezacida;
    @Size(max = 30)
    @Column(name = "roe_bneto_ffpp")
    private String roeBnetoFfpp;
    @Size(max = 30)
    @Column(name = "roa_bneto_at")
    private String roaBnetoAt;
    @Size(max = 30)
    @Column(name = "ebitda_gastosfinancieros")
    private String ebitdaGastosfinancieros;
    @Size(max = 30)
    @Column(name = "coberturadeinteres_cfoperat_gastosfinancieros")
    private String coberturadeinteresCfoperatGastosfinancieros;
    @Size(max = 30)
    @Column(name = "capitaldetrabajo_activocirculante_pasivocirculante")
    private String capitaldetrabajoActivocirculantePasivocirculante;
    @Size(max = 30)
    @Column(name = "capitaldetrabajo_ventas")
    private String capitaldetrabajoVentas;
    @Size(max = 30)
    @Column(name = "capitaldetrabajooperativo")
    private String capitaldetrabajooperativo;
    @Size(max = 30)
    @Column(name = "capitaldetrabajooperativo_ventas")
    private String capitaldetrabajooperativoVentas;
    @Size(max = 30)
    @Column(name = "rotaciondeclientes_dias")
    private String rotaciondeclientesDias;
    @Size(max = 30)
    @Column(name = "rotaciondeinventarios_dias")
    private String rotaciondeinventariosDias;
    @Size(max = 30)
    @Column(name = "rotaciondeproveedores_dias")
    private String rotaciondeproveedoresDias;
    @Size(max = 30)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 30)
    @Column(name = "ventas")
    private String ventas;
    @Size(max = 30)
    @Column(name = "utilidad")
    private String utilidad;
    @Size(max = 30)
    @Column(name = "total_activos")
    private String totalActivos;
    @Size(max = 30)
    @Column(name = "total_pasivos")
    private String totalPasivos;
    @Size(max = 30)
    @Column(name = "patrimonio")
    private String patrimonio;
    @Size(max = 30)
    @Column(name = "total_ingresos")
    private String totalIngresos;
    @Size(max = 30)
    @Column(name = "total_gastos")
    private String totalGastos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resumeneeff")
    private Integer idResumeneeff;
    @Column(name = "fechainsercion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainsercion;

    public Historicos_eeff() {
    }

    public Historicos_eeff(Integer idResumeneeff) {
        this.idResumeneeff = idResumeneeff;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getFechaacta() {
        return fechaacta;
    }

    public void setFechaacta(Date fechaacta) {
        this.fechaacta = fechaacta;
    }

    public String getEstadoaprobacion() {
        return estadoaprobacion;
    }

    public void setEstadoaprobacion(String estadoaprobacion) {
        this.estadoaprobacion = estadoaprobacion;
    }

    public Date getAniobalance() {
        return aniobalance;
    }

    public void setAniobalance(Date aniobalance) {
        this.aniobalance = aniobalance;
    }

    public String getIngresosnetos() {
        return ingresosnetos;
    }

    public void setIngresosnetos(String ingresosnetos) {
        this.ingresosnetos = ingresosnetos;
    }

    public String getCostedeventas() {
        return costedeventas;
    }

    public void setCostedeventas(String costedeventas) {
        this.costedeventas = costedeventas;
    }

    public String getResultadobruto() {
        return resultadobruto;
    }

    public void setResultadobruto(String resultadobruto) {
        this.resultadobruto = resultadobruto;
    }

    public String getGastosgenerales() {
        return gastosgenerales;
    }

    public void setGastosgenerales(String gastosgenerales) {
        this.gastosgenerales = gastosgenerales;
    }

    public String getOtrosingresosGastosOp() {
        return otrosingresosGastosOp;
    }

    public void setOtrosingresosGastosOp(String otrosingresosGastosOp) {
        this.otrosingresosGastosOp = otrosingresosGastosOp;
    }

    public String getProvisiones() {
        return provisiones;
    }

    public void setProvisiones(String provisiones) {
        this.provisiones = provisiones;
    }

    public String getResultadoexplotacionEbitda() {
        return resultadoexplotacionEbitda;
    }

    public void setResultadoexplotacionEbitda(String resultadoexplotacionEbitda) {
        this.resultadoexplotacionEbitda = resultadoexplotacionEbitda;
    }

    public String getMargenebitda() {
        return margenebitda;
    }

    public void setMargenebitda(String margenebitda) {
        this.margenebitda = margenebitda;
    }

    public String getAmortizaciones() {
        return amortizaciones;
    }

    public void setAmortizaciones(String amortizaciones) {
        this.amortizaciones = amortizaciones;
    }

    public String getResultadooperativoEbit() {
        return resultadooperativoEbit;
    }

    public void setResultadooperativoEbit(String resultadooperativoEbit) {
        this.resultadooperativoEbit = resultadooperativoEbit;
    }

    public String getMargenebit() {
        return margenebit;
    }

    public void setMargenebit(String margenebit) {
        this.margenebit = margenebit;
    }

    public String getIngresossociedadesgrupo() {
        return ingresossociedadesgrupo;
    }

    public void setIngresossociedadesgrupo(String ingresossociedadesgrupo) {
        this.ingresossociedadesgrupo = ingresossociedadesgrupo;
    }

    public String getOtrosingresosfinancieros() {
        return otrosingresosfinancieros;
    }

    public void setOtrosingresosfinancieros(String otrosingresosfinancieros) {
        this.otrosingresosfinancieros = otrosingresosfinancieros;
    }

    public String getGastosfinancieros() {
        return gastosfinancieros;
    }

    public void setGastosfinancieros(String gastosfinancieros) {
        this.gastosfinancieros = gastosfinancieros;
    }

    public String getResultadofinanciero() {
        return resultadofinanciero;
    }

    public void setResultadofinanciero(String resultadofinanciero) {
        this.resultadofinanciero = resultadofinanciero;
    }

    public String getAjustescambiariosInflacion() {
        return ajustescambiariosInflacion;
    }

    public void setAjustescambiariosInflacion(String ajustescambiariosInflacion) {
        this.ajustescambiariosInflacion = ajustescambiariosInflacion;
    }

    public String getAmortFondocomercioconsolidacion() {
        return amortFondocomercioconsolidacion;
    }

    public void setAmortFondocomercioconsolidacion(String amortFondocomercioconsolidacion) {
        this.amortFondocomercioconsolidacion = amortFondocomercioconsolidacion;
    }

    public String getResultadospuestaenequivalencia() {
        return resultadospuestaenequivalencia;
    }

    public void setResultadospuestaenequivalencia(String resultadospuestaenequivalencia) {
        this.resultadospuestaenequivalencia = resultadospuestaenequivalencia;
    }

    public String getResultadoordinario() {
        return resultadoordinario;
    }

    public void setResultadoordinario(String resultadoordinario) {
        this.resultadoordinario = resultadoordinario;
    }

    public String getResultadoextraordinario() {
        return resultadoextraordinario;
    }

    public void setResultadoextraordinario(String resultadoextraordinario) {
        this.resultadoextraordinario = resultadoextraordinario;
    }

    public String getResultadoantesdeimpuestos() {
        return resultadoantesdeimpuestos;
    }

    public void setResultadoantesdeimpuestos(String resultadoantesdeimpuestos) {
        this.resultadoantesdeimpuestos = resultadoantesdeimpuestos;
    }

    public String getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(String impuestos) {
        this.impuestos = impuestos;
    }

    public String getResultadoneto() {
        return resultadoneto;
    }

    public void setResultadoneto(String resultadoneto) {
        this.resultadoneto = resultadoneto;
    }

    public String getMargenneto() {
        return margenneto;
    }

    public void setMargenneto(String margenneto) {
        this.margenneto = margenneto;
    }

    public String getInteresesminoritarios() {
        return interesesminoritarios;
    }

    public void setInteresesminoritarios(String interesesminoritarios) {
        this.interesesminoritarios = interesesminoritarios;
    }

    public String getResultadoatribuible() {
        return resultadoatribuible;
    }

    public void setResultadoatribuible(String resultadoatribuible) {
        this.resultadoatribuible = resultadoatribuible;
    }

    public String getDividendosaprobados() {
        return dividendosaprobados;
    }

    public void setDividendosaprobados(String dividendosaprobados) {
        this.dividendosaprobados = dividendosaprobados;
    }

    public String getBeneficioretenido() {
        return beneficioretenido;
    }

    public void setBeneficioretenido(String beneficioretenido) {
        this.beneficioretenido = beneficioretenido;
    }

    public String getNoDeacciones() {
        return noDeacciones;
    }

    public void setNoDeacciones(String noDeacciones) {
        this.noDeacciones = noDeacciones;
    }

    public String getBeneficioporaccion() {
        return beneficioporaccion;
    }

    public void setBeneficioporaccion(String beneficioporaccion) {
        this.beneficioporaccion = beneficioporaccion;
    }

    public String getDividendoporaccion() {
        return dividendoporaccion;
    }

    public void setDividendoporaccion(String dividendoporaccion) {
        this.dividendoporaccion = dividendoporaccion;
    }

    public String getPayOut() {
        return payOut;
    }

    public void setPayOut(String payOut) {
        this.payOut = payOut;
    }

    public Date getAniobalanceRo() {
        return aniobalanceRo;
    }

    public void setAniobalanceRo(Date aniobalanceRo) {
        this.aniobalanceRo = aniobalanceRo;
    }

    public String getResultadoperativoEbit() {
        return resultadoperativoEbit;
    }

    public void setResultadoperativoEbit(String resultadoperativoEbit) {
        this.resultadoperativoEbit = resultadoperativoEbit;
    }

    public String getAmortizacionesProvisiones() {
        return amortizacionesProvisiones;
    }

    public void setAmortizacionesProvisiones(String amortizacionesProvisiones) {
        this.amortizacionesProvisiones = amortizacionesProvisiones;
    }

    public String getVariacioncapitalcirculanteoperativo() {
        return variacioncapitalcirculanteoperativo;
    }

    public void setVariacioncapitalcirculanteoperativo(String variacioncapitalcirculanteoperativo) {
        this.variacioncapitalcirculanteoperativo = variacioncapitalcirculanteoperativo;
    }

    public String getOtrosajustesRo() {
        return otrosajustesRo;
    }

    public void setOtrosajustesRo(String otrosajustesRo) {
        this.otrosajustesRo = otrosajustesRo;
    }

    public String getImpuestosRo() {
        return impuestosRo;
    }

    public void setImpuestosRo(String impuestosRo) {
        this.impuestosRo = impuestosRo;
    }

    public String getCfoperativoCfo() {
        return cfoperativoCfo;
    }

    public void setCfoperativoCfo(String cfoperativoCfo) {
        this.cfoperativoCfo = cfoperativoCfo;
    }

    public String getCapex() {
        return capex;
    }

    public void setCapex(String capex) {
        this.capex = capex;
    }

    public String getDesinversionesporventadeactivos() {
        return desinversionesporventadeactivos;
    }

    public void setDesinversionesporventadeactivos(String desinversionesporventadeactivos) {
        this.desinversionesporventadeactivos = desinversionesporventadeactivos;
    }

    public String getInversionesfinancieras() {
        return inversionesfinancieras;
    }

    public void setInversionesfinancieras(String inversionesfinancieras) {
        this.inversionesfinancieras = inversionesfinancieras;
    }

    public String getDesinversionesfinancieras() {
        return desinversionesfinancieras;
    }

    public void setDesinversionesfinancieras(String desinversionesfinancieras) {
        this.desinversionesfinancieras = desinversionesfinancieras;
    }

    public String getCobrosfinancieros() {
        return cobrosfinancieros;
    }

    public void setCobrosfinancieros(String cobrosfinancieros) {
        this.cobrosfinancieros = cobrosfinancieros;
    }

    public String getPrestamosalgrupo() {
        return prestamosalgrupo;
    }

    public void setPrestamosalgrupo(String prestamosalgrupo) {
        this.prestamosalgrupo = prestamosalgrupo;
    }

    public String getOtrasinversionesDesinversiones() {
        return otrasinversionesDesinversiones;
    }

    public void setOtrasinversionesDesinversiones(String otrasinversionesDesinversiones) {
        this.otrasinversionesDesinversiones = otrasinversionesDesinversiones;
    }

    public String getCfaplicadoainversionCfi() {
        return cfaplicadoainversionCfi;
    }

    public void setCfaplicadoainversionCfi(String cfaplicadoainversionCfi) {
        this.cfaplicadoainversionCfi = cfaplicadoainversionCfi;
    }

    public String getCfantesdefinanciacionCfafCfoCfi() {
        return cfantesdefinanciacionCfafCfoCfi;
    }

    public void setCfantesdefinanciacionCfafCfoCfi(String cfantesdefinanciacionCfafCfoCfi) {
        this.cfantesdefinanciacionCfafCfoCfi = cfantesdefinanciacionCfafCfoCfi;
    }

    public String getDividendospagados() {
        return dividendospagados;
    }

    public void setDividendospagados(String dividendospagados) {
        this.dividendospagados = dividendospagados;
    }

    public String getVariacionnetadeudaalP() {
        return variacionnetadeudaalP;
    }

    public void setVariacionnetadeudaalP(String variacionnetadeudaalP) {
        this.variacionnetadeudaalP = variacionnetadeudaalP;
    }

    public String getVariacionnetadeudaacP() {
        return variacionnetadeudaacP;
    }

    public void setVariacionnetadeudaacP(String variacionnetadeudaacP) {
        this.variacionnetadeudaacP = variacionnetadeudaacP;
    }

    public String getPagosfinancierosIntereses() {
        return pagosfinancierosIntereses;
    }

    public void setPagosfinancierosIntereses(String pagosfinancierosIntereses) {
        this.pagosfinancierosIntereses = pagosfinancierosIntereses;
    }

    public String getVariacionderecursospropios() {
        return variacionderecursospropios;
    }

    public void setVariacionderecursospropios(String variacionderecursospropios) {
        this.variacionderecursospropios = variacionderecursospropios;
    }

    public String getOtrosajustescf() {
        return otrosajustescf;
    }

    public void setOtrosajustescf(String otrosajustescf) {
        this.otrosajustescf = otrosajustescf;
    }

    public String getCfderivadodefinanciacionCff() {
        return cfderivadodefinanciacionCff;
    }

    public void setCfderivadodefinanciacionCff(String cfderivadodefinanciacionCff) {
        this.cfderivadodefinanciacionCff = cfderivadodefinanciacionCff;
    }

    public String getCfdespuesdefinanciacionCfdfCfafCff() {
        return cfdespuesdefinanciacionCfdfCfafCff;
    }

    public void setCfdespuesdefinanciacionCfdfCfafCff(String cfdespuesdefinanciacionCfdfCfafCff) {
        this.cfdespuesdefinanciacionCfdfCfafCff = cfdespuesdefinanciacionCfdfCfafCff;
    }

    public String getEfectodelasdiferenciasdecambioentesoreria() {
        return efectodelasdiferenciasdecambioentesoreria;
    }

    public void setEfectodelasdiferenciasdecambioentesoreria(String efectodelasdiferenciasdecambioentesoreria) {
        this.efectodelasdiferenciasdecambioentesoreria = efectodelasdiferenciasdecambioentesoreria;
    }

    public String getAumentoDisminucionnetadetesoreria() {
        return aumentoDisminucionnetadetesoreria;
    }

    public void setAumentoDisminucionnetadetesoreria(String aumentoDisminucionnetadetesoreria) {
        this.aumentoDisminucionnetadetesoreria = aumentoDisminucionnetadetesoreria;
    }

    public String getCajaaliniciodelejercicio() {
        return cajaaliniciodelejercicio;
    }

    public void setCajaaliniciodelejercicio(String cajaaliniciodelejercicio) {
        this.cajaaliniciodelejercicio = cajaaliniciodelejercicio;
    }

    public String getCajaalfinaldelperiodo() {
        return cajaalfinaldelperiodo;
    }

    public void setCajaalfinaldelperiodo(String cajaalfinaldelperiodo) {
        this.cajaalfinaldelperiodo = cajaalfinaldelperiodo;
    }

    public String getNetworkinginvestment() {
        return networkinginvestment;
    }

    public void setNetworkinginvestment(String networkinginvestment) {
        this.networkinginvestment = networkinginvestment;
    }

    public Date getAniobalanceTaf() {
        return aniobalanceTaf;
    }

    public void setAniobalanceTaf(Date aniobalanceTaf) {
        this.aniobalanceTaf = aniobalanceTaf;
    }

    public String getTesoreriaActivosfacilmenteliquidables() {
        return tesoreriaActivosfacilmenteliquidables;
    }

    public void setTesoreriaActivosfacilmenteliquidables(String tesoreriaActivosfacilmenteliquidables) {
        this.tesoreriaActivosfacilmenteliquidables = tesoreriaActivosfacilmenteliquidables;
    }

    public String getCuentasacobrarAdt() {
        return cuentasacobrarAdt;
    }

    public void setCuentasacobrarAdt(String cuentasacobrarAdt) {
        this.cuentasacobrarAdt = cuentasacobrarAdt;
    }

    public String getExistencias() {
        return existencias;
    }

    public void setExistencias(String existencias) {
        this.existencias = existencias;
    }

    public String getAdministracionespublicas() {
        return administracionespublicas;
    }

    public void setAdministracionespublicas(String administracionespublicas) {
        this.administracionespublicas = administracionespublicas;
    }

    public String getOtrosactivoscirculantes() {
        return otrosactivoscirculantes;
    }

    public void setOtrosactivoscirculantes(String otrosactivoscirculantes) {
        this.otrosactivoscirculantes = otrosactivoscirculantes;
    }

    public String getTotalactivocirculante() {
        return totalactivocirculante;
    }

    public void setTotalactivocirculante(String totalactivocirculante) {
        this.totalactivocirculante = totalactivocirculante;
    }

    public String getInmovilizadomaterialneto() {
        return inmovilizadomaterialneto;
    }

    public void setInmovilizadomaterialneto(String inmovilizadomaterialneto) {
        this.inmovilizadomaterialneto = inmovilizadomaterialneto;
    }

    public String getActivosintangibles() {
        return activosintangibles;
    }

    public void setActivosintangibles(String activosintangibles) {
        this.activosintangibles = activosintangibles;
    }

    public String getInmovilizadofinanciero() {
        return inmovilizadofinanciero;
    }

    public void setInmovilizadofinanciero(String inmovilizadofinanciero) {
        this.inmovilizadofinanciero = inmovilizadofinanciero;
    }

    public String getOtrosactivoslP() {
        return otrosactivoslP;
    }

    public void setOtrosactivoslP(String otrosactivoslP) {
        this.otrosactivoslP = otrosactivoslP;
    }

    public String getTotalactivofijo() {
        return totalactivofijo;
    }

    public void setTotalactivofijo(String totalactivofijo) {
        this.totalactivofijo = totalactivofijo;
    }

    public String getTotalactivo() {
        return totalactivo;
    }

    public void setTotalactivo(String totalactivo) {
        this.totalactivo = totalactivo;
    }

    public String getActivoscontingentes() {
        return activoscontingentes;
    }

    public void setActivoscontingentes(String activoscontingentes) {
        this.activoscontingentes = activoscontingentes;
    }

    public String getDeudafinancieraacP() {
        return deudafinancieraacP;
    }

    public void setDeudafinancieraacP(String deudafinancieraacP) {
        this.deudafinancieraacP = deudafinancieraacP;
    }

    public String getDeudagrupocP() {
        return deudagrupocP;
    }

    public void setDeudagrupocP(String deudagrupocP) {
        this.deudagrupocP = deudagrupocP;
    }

    public String getCuentasapagar() {
        return cuentasapagar;
    }

    public void setCuentasapagar(String cuentasapagar) {
        this.cuentasapagar = cuentasapagar;
    }

    public String getCuentasapagaralaadministracion() {
        return cuentasapagaralaadministracion;
    }

    public void setCuentasapagaralaadministracion(String cuentasapagaralaadministracion) {
        this.cuentasapagaralaadministracion = cuentasapagaralaadministracion;
    }

    public String getOtrospasivoscP() {
        return otrospasivoscP;
    }

    public void setOtrospasivoscP(String otrospasivoscP) {
        this.otrospasivoscP = otrospasivoscP;
    }

    public String getTotalexigibleacorto() {
        return totalexigibleacorto;
    }

    public void setTotalexigibleacorto(String totalexigibleacorto) {
        this.totalexigibleacorto = totalexigibleacorto;
    }

    public String getDeudafinancieraalP() {
        return deudafinancieraalP;
    }

    public void setDeudafinancieraalP(String deudafinancieraalP) {
        this.deudafinancieraalP = deudafinancieraalP;
    }

    public String getDeudagrupoalP() {
        return deudagrupoalP;
    }

    public void setDeudagrupoalP(String deudagrupoalP) {
        this.deudagrupoalP = deudagrupoalP;
    }

    public String getOtrospasivoslP() {
        return otrospasivoslP;
    }

    public void setOtrospasivoslP(String otrospasivoslP) {
        this.otrospasivoslP = otrospasivoslP;
    }

    public String getProvisionesTec() {
        return provisionesTec;
    }

    public void setProvisionesTec(String provisionesTec) {
        this.provisionesTec = provisionesTec;
    }

    public String getTotalexigiblealargo() {
        return totalexigiblealargo;
    }

    public void setTotalexigiblealargo(String totalexigiblealargo) {
        this.totalexigiblealargo = totalexigiblealargo;
    }

    public String getCapitalsocial() {
        return capitalsocial;
    }

    public void setCapitalsocial(String capitalsocial) {
        this.capitalsocial = capitalsocial;
    }

    public String getReservasacumuladas() {
        return reservasacumuladas;
    }

    public void setReservasacumuladas(String reservasacumuladas) {
        this.reservasacumuladas = reservasacumuladas;
    }

    public String getSociosexternos() {
        return sociosexternos;
    }

    public void setSociosexternos(String sociosexternos) {
        this.sociosexternos = sociosexternos;
    }

    public String getRecursospropios() {
        return recursospropios;
    }

    public void setRecursospropios(String recursospropios) {
        this.recursospropios = recursospropios;
    }

    public String getTotalexigible() {
        return totalexigible;
    }

    public void setTotalexigible(String totalexigible) {
        this.totalexigible = totalexigible;
    }

    public String getTotalpasivo() {
        return totalpasivo;
    }

    public void setTotalpasivo(String totalpasivo) {
        this.totalpasivo = totalpasivo;
    }

    public String getPasivoscontingentes() {
        return pasivoscontingentes;
    }

    public void setPasivoscontingentes(String pasivoscontingentes) {
        this.pasivoscontingentes = pasivoscontingentes;
    }

    public String getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(String indicadores) {
        this.indicadores = indicadores;
    }

    public String getDeudafinancierabruta() {
        return deudafinancierabruta;
    }

    public void setDeudafinancierabruta(String deudafinancierabruta) {
        this.deudafinancierabruta = deudafinancierabruta;
    }

    public String getDeudafinancieranetaDfn() {
        return deudafinancieranetaDfn;
    }

    public void setDeudafinancieranetaDfn(String deudafinancieranetaDfn) {
        this.deudafinancieranetaDfn = deudafinancieranetaDfn;
    }

    public String getCapitalizacionFfppAt() {
        return capitalizacionFfppAt;
    }

    public void setCapitalizacionFfppAt(String capitalizacionFfppAt) {
        this.capitalizacionFfppAt = capitalizacionFfppAt;
    }

    public String getApalancamiento() {
        return apalancamiento;
    }

    public void setApalancamiento(String apalancamiento) {
        this.apalancamiento = apalancamiento;
    }

    public String getDfnFfpp() {
        return dfnFfpp;
    }

    public void setDfnFfpp(String dfnFfpp) {
        this.dfnFfpp = dfnFfpp;
    }

    public String getDfbEbitda() {
        return dfbEbitda;
    }

    public void setDfbEbitda(String dfbEbitda) {
        this.dfbEbitda = dfbEbitda;
    }

    public String getDfnEbitda() {
        return dfnEbitda;
    }

    public void setDfnEbitda(String dfnEbitda) {
        this.dfnEbitda = dfnEbitda;
    }

    public String getDfnCfl() {
        return dfnCfl;
    }

    public void setDfnCfl(String dfnCfl) {
        this.dfnCfl = dfnCfl;
    }

    public String getLiquidez() {
        return liquidez;
    }

    public void setLiquidez(String liquidez) {
        this.liquidez = liquidez;
    }

    public String getLiquidezacida() {
        return liquidezacida;
    }

    public void setLiquidezacida(String liquidezacida) {
        this.liquidezacida = liquidezacida;
    }

    public String getRoeBnetoFfpp() {
        return roeBnetoFfpp;
    }

    public void setRoeBnetoFfpp(String roeBnetoFfpp) {
        this.roeBnetoFfpp = roeBnetoFfpp;
    }

    public String getRoaBnetoAt() {
        return roaBnetoAt;
    }

    public void setRoaBnetoAt(String roaBnetoAt) {
        this.roaBnetoAt = roaBnetoAt;
    }

    public String getEbitdaGastosfinancieros() {
        return ebitdaGastosfinancieros;
    }

    public void setEbitdaGastosfinancieros(String ebitdaGastosfinancieros) {
        this.ebitdaGastosfinancieros = ebitdaGastosfinancieros;
    }

    public String getCoberturadeinteresCfoperatGastosfinancieros() {
        return coberturadeinteresCfoperatGastosfinancieros;
    }

    public void setCoberturadeinteresCfoperatGastosfinancieros(String coberturadeinteresCfoperatGastosfinancieros) {
        this.coberturadeinteresCfoperatGastosfinancieros = coberturadeinteresCfoperatGastosfinancieros;
    }

    public String getCapitaldetrabajoActivocirculantePasivocirculante() {
        return capitaldetrabajoActivocirculantePasivocirculante;
    }

    public void setCapitaldetrabajoActivocirculantePasivocirculante(String capitaldetrabajoActivocirculantePasivocirculante) {
        this.capitaldetrabajoActivocirculantePasivocirculante = capitaldetrabajoActivocirculantePasivocirculante;
    }

    public String getCapitaldetrabajoVentas() {
        return capitaldetrabajoVentas;
    }

    public void setCapitaldetrabajoVentas(String capitaldetrabajoVentas) {
        this.capitaldetrabajoVentas = capitaldetrabajoVentas;
    }

    public String getCapitaldetrabajooperativo() {
        return capitaldetrabajooperativo;
    }

    public void setCapitaldetrabajooperativo(String capitaldetrabajooperativo) {
        this.capitaldetrabajooperativo = capitaldetrabajooperativo;
    }

    public String getCapitaldetrabajooperativoVentas() {
        return capitaldetrabajooperativoVentas;
    }

    public void setCapitaldetrabajooperativoVentas(String capitaldetrabajooperativoVentas) {
        this.capitaldetrabajooperativoVentas = capitaldetrabajooperativoVentas;
    }

    public String getRotaciondeclientesDias() {
        return rotaciondeclientesDias;
    }

    public void setRotaciondeclientesDias(String rotaciondeclientesDias) {
        this.rotaciondeclientesDias = rotaciondeclientesDias;
    }

    public String getRotaciondeinventariosDias() {
        return rotaciondeinventariosDias;
    }

    public void setRotaciondeinventariosDias(String rotaciondeinventariosDias) {
        this.rotaciondeinventariosDias = rotaciondeinventariosDias;
    }

    public String getRotaciondeproveedoresDias() {
        return rotaciondeproveedoresDias;
    }

    public void setRotaciondeproveedoresDias(String rotaciondeproveedoresDias) {
        this.rotaciondeproveedoresDias = rotaciondeproveedoresDias;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getVentas() {
        return ventas;
    }

    public void setVentas(String ventas) {
        this.ventas = ventas;
    }

    public String getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(String utilidad) {
        this.utilidad = utilidad;
    }

    public String getTotalActivos() {
        return totalActivos;
    }

    public void setTotalActivos(String totalActivos) {
        this.totalActivos = totalActivos;
    }

    public String getTotalPasivos() {
        return totalPasivos;
    }

    public void setTotalPasivos(String totalPasivos) {
        this.totalPasivos = totalPasivos;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(String totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public String getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(String totalGastos) {
        this.totalGastos = totalGastos;
    }

    public Integer getIdResumeneeff() {
        return idResumeneeff;
    }

    public void setIdResumeneeff(Integer idResumeneeff) {
        this.idResumeneeff = idResumeneeff;
    }

    public Date getFechainsercion() {
        return fechainsercion;
    }

    public void setFechainsercion(Date fechainsercion) {
        this.fechainsercion = fechainsercion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResumeneeff != null ? idResumeneeff.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicos_eeff)) {
            return false;
        }
        Historicos_eeff other = (Historicos_eeff) object;
        if ((this.idResumeneeff == null && other.idResumeneeff != null) || (this.idResumeneeff != null && !this.idResumeneeff.equals(other.idResumeneeff))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Historicos_eeff[ idResumeneeff=" + idResumeneeff + " ]";
    }

}
