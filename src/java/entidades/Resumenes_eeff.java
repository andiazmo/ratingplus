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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "ri.resumeneeff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resumenes_eeff.findAll", query = "SELECT r FROM Resumenes_eeff r")
    , @NamedQuery(name = "Resumenes_eeff.findByNit", query = "SELECT r FROM Resumenes_eeff r WHERE r.resumenes_eeffPK.nit = :nit")
    , @NamedQuery(name = "Resumenes_eeff.findByNombreempresa", query = "SELECT r FROM Resumenes_eeff r WHERE r.nombreempresa = :nombreempresa")
    , @NamedQuery(name = "Resumenes_eeff.findBySector", query = "SELECT r FROM Resumenes_eeff r WHERE r.sector = :sector")
    , @NamedQuery(name = "Resumenes_eeff.findByRating", query = "SELECT r FROM Resumenes_eeff r WHERE r.rating = :rating")
    , @NamedQuery(name = "Resumenes_eeff.findByFechaacta", query = "SELECT r FROM Resumenes_eeff r WHERE r.fechaacta = :fechaacta")
    , @NamedQuery(name = "Resumenes_eeff.findByEstadoaprobacion", query = "SELECT r FROM Resumenes_eeff r WHERE r.estadoaprobacion = :estadoaprobacion")
    , @NamedQuery(name = "Resumenes_eeff.findByAniobalance", query = "SELECT r FROM Resumenes_eeff r WHERE r.resumenes_eeffPK.aniobalance = :aniobalance")
    , @NamedQuery(name = "Resumenes_eeff.findByIngresosnetos", query = "SELECT r FROM Resumenes_eeff r WHERE r.ingresosnetos = :ingresosnetos")
    , @NamedQuery(name = "Resumenes_eeff.findByCostedeventas", query = "SELECT r FROM Resumenes_eeff r WHERE r.costedeventas = :costedeventas")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadobruto", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadobruto = :resultadobruto")
    , @NamedQuery(name = "Resumenes_eeff.findByGastosgenerales", query = "SELECT r FROM Resumenes_eeff r WHERE r.gastosgenerales = :gastosgenerales")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrosingresosGastosOp", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrosingresosGastosOp = :otrosingresosGastosOp")
    , @NamedQuery(name = "Resumenes_eeff.findByProvisiones", query = "SELECT r FROM Resumenes_eeff r WHERE r.provisiones = :provisiones")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadoexplotacionEbitda", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadoexplotacionEbitda = :resultadoexplotacionEbitda")
    , @NamedQuery(name = "Resumenes_eeff.findByMargenebitda", query = "SELECT r FROM Resumenes_eeff r WHERE r.margenebitda = :margenebitda")
    , @NamedQuery(name = "Resumenes_eeff.findByAmortizaciones", query = "SELECT r FROM Resumenes_eeff r WHERE r.amortizaciones = :amortizaciones")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadooperativoEbit", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadooperativoEbit = :resultadooperativoEbit")
    , @NamedQuery(name = "Resumenes_eeff.findByMargenebit", query = "SELECT r FROM Resumenes_eeff r WHERE r.margenebit = :margenebit")
    , @NamedQuery(name = "Resumenes_eeff.findByIngresossociedadesgrupo", query = "SELECT r FROM Resumenes_eeff r WHERE r.ingresossociedadesgrupo = :ingresossociedadesgrupo")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrosingresosfinancieros", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrosingresosfinancieros = :otrosingresosfinancieros")
    , @NamedQuery(name = "Resumenes_eeff.findByGastosfinancieros", query = "SELECT r FROM Resumenes_eeff r WHERE r.gastosfinancieros = :gastosfinancieros")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadofinanciero", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadofinanciero = :resultadofinanciero")
    , @NamedQuery(name = "Resumenes_eeff.findByAjustescambiariosInflacion", query = "SELECT r FROM Resumenes_eeff r WHERE r.ajustescambiariosInflacion = :ajustescambiariosInflacion")
    , @NamedQuery(name = "Resumenes_eeff.findByAmortFondocomercioconsolidacion", query = "SELECT r FROM Resumenes_eeff r WHERE r.amortFondocomercioconsolidacion = :amortFondocomercioconsolidacion")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadospuestaenequivalencia", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadospuestaenequivalencia = :resultadospuestaenequivalencia")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadoordinario", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadoordinario = :resultadoordinario")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadoextraordinario", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadoextraordinario = :resultadoextraordinario")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadoantesdeimpuestos", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadoantesdeimpuestos = :resultadoantesdeimpuestos")
    , @NamedQuery(name = "Resumenes_eeff.findByImpuestos", query = "SELECT r FROM Resumenes_eeff r WHERE r.impuestos = :impuestos")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadoneto", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadoneto = :resultadoneto")
    , @NamedQuery(name = "Resumenes_eeff.findByMargenneto", query = "SELECT r FROM Resumenes_eeff r WHERE r.margenneto = :margenneto")
    , @NamedQuery(name = "Resumenes_eeff.findByInteresesminoritarios", query = "SELECT r FROM Resumenes_eeff r WHERE r.interesesminoritarios = :interesesminoritarios")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadoatribuible", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadoatribuible = :resultadoatribuible")
    , @NamedQuery(name = "Resumenes_eeff.findByDividendosaprobados", query = "SELECT r FROM Resumenes_eeff r WHERE r.dividendosaprobados = :dividendosaprobados")
    , @NamedQuery(name = "Resumenes_eeff.findByBeneficioretenido", query = "SELECT r FROM Resumenes_eeff r WHERE r.beneficioretenido = :beneficioretenido")
    , @NamedQuery(name = "Resumenes_eeff.findByNoDeacciones", query = "SELECT r FROM Resumenes_eeff r WHERE r.noDeacciones = :noDeacciones")
    , @NamedQuery(name = "Resumenes_eeff.findByBeneficioporaccion", query = "SELECT r FROM Resumenes_eeff r WHERE r.beneficioporaccion = :beneficioporaccion")
    , @NamedQuery(name = "Resumenes_eeff.findByDividendoporaccion", query = "SELECT r FROM Resumenes_eeff r WHERE r.dividendoporaccion = :dividendoporaccion")
    , @NamedQuery(name = "Resumenes_eeff.findByPayOut", query = "SELECT r FROM Resumenes_eeff r WHERE r.payOut = :payOut")
    , @NamedQuery(name = "Resumenes_eeff.findByAniobalanceRo", query = "SELECT r FROM Resumenes_eeff r WHERE r.aniobalanceRo = :aniobalanceRo")
    , @NamedQuery(name = "Resumenes_eeff.findByResultadoperativoEbit", query = "SELECT r FROM Resumenes_eeff r WHERE r.resultadoperativoEbit = :resultadoperativoEbit")
    , @NamedQuery(name = "Resumenes_eeff.findByAmortizacionesProvisiones", query = "SELECT r FROM Resumenes_eeff r WHERE r.amortizacionesProvisiones = :amortizacionesProvisiones")
    , @NamedQuery(name = "Resumenes_eeff.findByVariacioncapitalcirculanteoperativo", query = "SELECT r FROM Resumenes_eeff r WHERE r.variacioncapitalcirculanteoperativo = :variacioncapitalcirculanteoperativo")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrosajustesRo", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrosajustesRo = :otrosajustesRo")
    , @NamedQuery(name = "Resumenes_eeff.findByImpuestosRo", query = "SELECT r FROM Resumenes_eeff r WHERE r.impuestosRo = :impuestosRo")
    , @NamedQuery(name = "Resumenes_eeff.findByCfoperativoCfo", query = "SELECT r FROM Resumenes_eeff r WHERE r.cfoperativoCfo = :cfoperativoCfo")
    , @NamedQuery(name = "Resumenes_eeff.findByCapex", query = "SELECT r FROM Resumenes_eeff r WHERE r.capex = :capex")
    , @NamedQuery(name = "Resumenes_eeff.findByDesinversionesporventadeactivos", query = "SELECT r FROM Resumenes_eeff r WHERE r.desinversionesporventadeactivos = :desinversionesporventadeactivos")
    , @NamedQuery(name = "Resumenes_eeff.findByInversionesfinancieras", query = "SELECT r FROM Resumenes_eeff r WHERE r.inversionesfinancieras = :inversionesfinancieras")
    , @NamedQuery(name = "Resumenes_eeff.findByDesinversionesfinancieras", query = "SELECT r FROM Resumenes_eeff r WHERE r.desinversionesfinancieras = :desinversionesfinancieras")
    , @NamedQuery(name = "Resumenes_eeff.findByCobrosfinancieros", query = "SELECT r FROM Resumenes_eeff r WHERE r.cobrosfinancieros = :cobrosfinancieros")
    , @NamedQuery(name = "Resumenes_eeff.findByPrestamosalgrupo", query = "SELECT r FROM Resumenes_eeff r WHERE r.prestamosalgrupo = :prestamosalgrupo")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrasinversionesDesinversiones", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrasinversionesDesinversiones = :otrasinversionesDesinversiones")
    , @NamedQuery(name = "Resumenes_eeff.findByCfaplicadoainversionCfi", query = "SELECT r FROM Resumenes_eeff r WHERE r.cfaplicadoainversionCfi = :cfaplicadoainversionCfi")
    , @NamedQuery(name = "Resumenes_eeff.findByCfantesdefinanciacionCfafCfoCfi", query = "SELECT r FROM Resumenes_eeff r WHERE r.cfantesdefinanciacionCfafCfoCfi = :cfantesdefinanciacionCfafCfoCfi")
    , @NamedQuery(name = "Resumenes_eeff.findByDividendospagados", query = "SELECT r FROM Resumenes_eeff r WHERE r.dividendospagados = :dividendospagados")
    , @NamedQuery(name = "Resumenes_eeff.findByVariacionnetadeudaalP", query = "SELECT r FROM Resumenes_eeff r WHERE r.variacionnetadeudaalP = :variacionnetadeudaalP")
    , @NamedQuery(name = "Resumenes_eeff.findByVariacionnetadeudaacP", query = "SELECT r FROM Resumenes_eeff r WHERE r.variacionnetadeudaacP = :variacionnetadeudaacP")
    , @NamedQuery(name = "Resumenes_eeff.findByPagosfinancierosIntereses", query = "SELECT r FROM Resumenes_eeff r WHERE r.pagosfinancierosIntereses = :pagosfinancierosIntereses")
    , @NamedQuery(name = "Resumenes_eeff.findByVariacionderecursospropios", query = "SELECT r FROM Resumenes_eeff r WHERE r.variacionderecursospropios = :variacionderecursospropios")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrosajustescf", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrosajustescf = :otrosajustescf")
    , @NamedQuery(name = "Resumenes_eeff.findByCfderivadodefinanciacionCff", query = "SELECT r FROM Resumenes_eeff r WHERE r.cfderivadodefinanciacionCff = :cfderivadodefinanciacionCff")
    , @NamedQuery(name = "Resumenes_eeff.findByCfdespuesdefinanciacionCfdfCfafCff", query = "SELECT r FROM Resumenes_eeff r WHERE r.cfdespuesdefinanciacionCfdfCfafCff = :cfdespuesdefinanciacionCfdfCfafCff")
    , @NamedQuery(name = "Resumenes_eeff.findByEfectodelasdiferenciasdecambioentesoreria", query = "SELECT r FROM Resumenes_eeff r WHERE r.efectodelasdiferenciasdecambioentesoreria = :efectodelasdiferenciasdecambioentesoreria")
    , @NamedQuery(name = "Resumenes_eeff.findByAumentoDisminucionnetadetesoreria", query = "SELECT r FROM Resumenes_eeff r WHERE r.aumentoDisminucionnetadetesoreria = :aumentoDisminucionnetadetesoreria")
    , @NamedQuery(name = "Resumenes_eeff.findByCajaaliniciodelejercicio", query = "SELECT r FROM Resumenes_eeff r WHERE r.cajaaliniciodelejercicio = :cajaaliniciodelejercicio")
    , @NamedQuery(name = "Resumenes_eeff.findByCajaalfinaldelperiodo", query = "SELECT r FROM Resumenes_eeff r WHERE r.cajaalfinaldelperiodo = :cajaalfinaldelperiodo")
    , @NamedQuery(name = "Resumenes_eeff.findByNetworkinginvestment", query = "SELECT r FROM Resumenes_eeff r WHERE r.networkinginvestment = :networkinginvestment")
    , @NamedQuery(name = "Resumenes_eeff.findByAniobalanceTaf", query = "SELECT r FROM Resumenes_eeff r WHERE r.aniobalanceTaf = :aniobalanceTaf")
    , @NamedQuery(name = "Resumenes_eeff.findByTesoreriaActivosfacilmenteliquidables", query = "SELECT r FROM Resumenes_eeff r WHERE r.tesoreriaActivosfacilmenteliquidables = :tesoreriaActivosfacilmenteliquidables")
    , @NamedQuery(name = "Resumenes_eeff.findByCuentasacobrarAdt", query = "SELECT r FROM Resumenes_eeff r WHERE r.cuentasacobrarAdt = :cuentasacobrarAdt")
    , @NamedQuery(name = "Resumenes_eeff.findByExistencias", query = "SELECT r FROM Resumenes_eeff r WHERE r.existencias = :existencias")
    , @NamedQuery(name = "Resumenes_eeff.findByAdministracionespublicas", query = "SELECT r FROM Resumenes_eeff r WHERE r.administracionespublicas = :administracionespublicas")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrosactivoscirculantes", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrosactivoscirculantes = :otrosactivoscirculantes")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalactivocirculante", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalactivocirculante = :totalactivocirculante")
    , @NamedQuery(name = "Resumenes_eeff.findByInmovilizadomaterialneto", query = "SELECT r FROM Resumenes_eeff r WHERE r.inmovilizadomaterialneto = :inmovilizadomaterialneto")
    , @NamedQuery(name = "Resumenes_eeff.findByActivosintangibles", query = "SELECT r FROM Resumenes_eeff r WHERE r.activosintangibles = :activosintangibles")
    , @NamedQuery(name = "Resumenes_eeff.findByInmovilizadofinanciero", query = "SELECT r FROM Resumenes_eeff r WHERE r.inmovilizadofinanciero = :inmovilizadofinanciero")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrosactivoslP", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrosactivoslP = :otrosactivoslP")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalactivofijo", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalactivofijo = :totalactivofijo")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalactivo", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalactivo = :totalactivo")
    , @NamedQuery(name = "Resumenes_eeff.findByActivoscontingentes", query = "SELECT r FROM Resumenes_eeff r WHERE r.activoscontingentes = :activoscontingentes")
    , @NamedQuery(name = "Resumenes_eeff.findByDeudafinancieraacP", query = "SELECT r FROM Resumenes_eeff r WHERE r.deudafinancieraacP = :deudafinancieraacP")
    , @NamedQuery(name = "Resumenes_eeff.findByDeudagrupocP", query = "SELECT r FROM Resumenes_eeff r WHERE r.deudagrupocP = :deudagrupocP")
    , @NamedQuery(name = "Resumenes_eeff.findByCuentasapagar", query = "SELECT r FROM Resumenes_eeff r WHERE r.cuentasapagar = :cuentasapagar")
    , @NamedQuery(name = "Resumenes_eeff.findByCuentasapagaralaadministracion", query = "SELECT r FROM Resumenes_eeff r WHERE r.cuentasapagaralaadministracion = :cuentasapagaralaadministracion")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrospasivoscP", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrospasivoscP = :otrospasivoscP")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalexigibleacorto", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalexigibleacorto = :totalexigibleacorto")
    , @NamedQuery(name = "Resumenes_eeff.findByDeudafinancieraalP", query = "SELECT r FROM Resumenes_eeff r WHERE r.deudafinancieraalP = :deudafinancieraalP")
    , @NamedQuery(name = "Resumenes_eeff.findByDeudagrupoalP", query = "SELECT r FROM Resumenes_eeff r WHERE r.deudagrupoalP = :deudagrupoalP")
    , @NamedQuery(name = "Resumenes_eeff.findByOtrospasivoslP", query = "SELECT r FROM Resumenes_eeff r WHERE r.otrospasivoslP = :otrospasivoslP")
    , @NamedQuery(name = "Resumenes_eeff.findByProvisionesTec", query = "SELECT r FROM Resumenes_eeff r WHERE r.provisionesTec = :provisionesTec")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalexigiblealargo", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalexigiblealargo = :totalexigiblealargo")
    , @NamedQuery(name = "Resumenes_eeff.findByCapitalsocial", query = "SELECT r FROM Resumenes_eeff r WHERE r.capitalsocial = :capitalsocial")
    , @NamedQuery(name = "Resumenes_eeff.findByReservasacumuladas", query = "SELECT r FROM Resumenes_eeff r WHERE r.reservasacumuladas = :reservasacumuladas")
    , @NamedQuery(name = "Resumenes_eeff.findBySociosexternos", query = "SELECT r FROM Resumenes_eeff r WHERE r.sociosexternos = :sociosexternos")
    , @NamedQuery(name = "Resumenes_eeff.findByRecursospropios", query = "SELECT r FROM Resumenes_eeff r WHERE r.recursospropios = :recursospropios")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalexigible", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalexigible = :totalexigible")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalpasivo", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalpasivo = :totalpasivo")
    , @NamedQuery(name = "Resumenes_eeff.findByPasivoscontingentes", query = "SELECT r FROM Resumenes_eeff r WHERE r.pasivoscontingentes = :pasivoscontingentes")
    , @NamedQuery(name = "Resumenes_eeff.findByIndicadores", query = "SELECT r FROM Resumenes_eeff r WHERE r.indicadores = :indicadores")
    , @NamedQuery(name = "Resumenes_eeff.findByDeudafinancierabruta", query = "SELECT r FROM Resumenes_eeff r WHERE r.deudafinancierabruta = :deudafinancierabruta")
    , @NamedQuery(name = "Resumenes_eeff.findByDeudafinancieranetaDfn", query = "SELECT r FROM Resumenes_eeff r WHERE r.deudafinancieranetaDfn = :deudafinancieranetaDfn")
    , @NamedQuery(name = "Resumenes_eeff.findByCapitalizacionFfppAt", query = "SELECT r FROM Resumenes_eeff r WHERE r.capitalizacionFfppAt = :capitalizacionFfppAt")
    , @NamedQuery(name = "Resumenes_eeff.findByApalancamiento", query = "SELECT r FROM Resumenes_eeff r WHERE r.apalancamiento = :apalancamiento")
    , @NamedQuery(name = "Resumenes_eeff.findByDfnFfpp", query = "SELECT r FROM Resumenes_eeff r WHERE r.dfnFfpp = :dfnFfpp")
    , @NamedQuery(name = "Resumenes_eeff.findByDfbEbitda", query = "SELECT r FROM Resumenes_eeff r WHERE r.dfbEbitda = :dfbEbitda")
    , @NamedQuery(name = "Resumenes_eeff.findByDfnEbitda", query = "SELECT r FROM Resumenes_eeff r WHERE r.dfnEbitda = :dfnEbitda")
    , @NamedQuery(name = "Resumenes_eeff.findByDfnCfl", query = "SELECT r FROM Resumenes_eeff r WHERE r.dfnCfl = :dfnCfl")
    , @NamedQuery(name = "Resumenes_eeff.findByLiquidez", query = "SELECT r FROM Resumenes_eeff r WHERE r.liquidez = :liquidez")
    , @NamedQuery(name = "Resumenes_eeff.findByLiquidezacida", query = "SELECT r FROM Resumenes_eeff r WHERE r.liquidezacida = :liquidezacida")
    , @NamedQuery(name = "Resumenes_eeff.findByRoeBnetoFfpp", query = "SELECT r FROM Resumenes_eeff r WHERE r.roeBnetoFfpp = :roeBnetoFfpp")
    , @NamedQuery(name = "Resumenes_eeff.findByRoaBnetoAt", query = "SELECT r FROM Resumenes_eeff r WHERE r.roaBnetoAt = :roaBnetoAt")
    , @NamedQuery(name = "Resumenes_eeff.findByEbitdaGastosfinancieros", query = "SELECT r FROM Resumenes_eeff r WHERE r.ebitdaGastosfinancieros = :ebitdaGastosfinancieros")
    , @NamedQuery(name = "Resumenes_eeff.findByCoberturadeinteresCfoperatGastosfinancieros", query = "SELECT r FROM Resumenes_eeff r WHERE r.coberturadeinteresCfoperatGastosfinancieros = :coberturadeinteresCfoperatGastosfinancieros")
    , @NamedQuery(name = "Resumenes_eeff.findByCapitaldetrabajoActivocirculantePasivocirculante", query = "SELECT r FROM Resumenes_eeff r WHERE r.capitaldetrabajoActivocirculantePasivocirculante = :capitaldetrabajoActivocirculantePasivocirculante")
    , @NamedQuery(name = "Resumenes_eeff.findByCapitaldetrabajoVentas", query = "SELECT r FROM Resumenes_eeff r WHERE r.capitaldetrabajoVentas = :capitaldetrabajoVentas")
    , @NamedQuery(name = "Resumenes_eeff.findByCapitaldetrabajooperativo", query = "SELECT r FROM Resumenes_eeff r WHERE r.capitaldetrabajooperativo = :capitaldetrabajooperativo")
    , @NamedQuery(name = "Resumenes_eeff.findByCapitaldetrabajooperativoVentas", query = "SELECT r FROM Resumenes_eeff r WHERE r.capitaldetrabajooperativoVentas = :capitaldetrabajooperativoVentas")
    , @NamedQuery(name = "Resumenes_eeff.findByRotaciondeclientesDias", query = "SELECT r FROM Resumenes_eeff r WHERE r.rotaciondeclientesDias = :rotaciondeclientesDias")
    , @NamedQuery(name = "Resumenes_eeff.findByRotaciondeinventariosDias", query = "SELECT r FROM Resumenes_eeff r WHERE r.rotaciondeinventariosDias = :rotaciondeinventariosDias")
    , @NamedQuery(name = "Resumenes_eeff.findByRotaciondeproveedoresDias", query = "SELECT r FROM Resumenes_eeff r WHERE r.rotaciondeproveedoresDias = :rotaciondeproveedoresDias")
    , @NamedQuery(name = "Resumenes_eeff.findByUsuario", query = "SELECT r FROM Resumenes_eeff r WHERE r.usuario = :usuario")
    , @NamedQuery(name = "Resumenes_eeff.findByVentas", query = "SELECT r FROM Resumenes_eeff r WHERE r.ventas = :ventas")
    , @NamedQuery(name = "Resumenes_eeff.findByUtilidad", query = "SELECT r FROM Resumenes_eeff r WHERE r.utilidad = :utilidad")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalActivos", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalActivos = :totalActivos")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalPasivos", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalPasivos = :totalPasivos")
    , @NamedQuery(name = "Resumenes_eeff.findByPatrimonio", query = "SELECT r FROM Resumenes_eeff r WHERE r.patrimonio = :patrimonio")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalIngresos", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalIngresos = :totalIngresos")
    , @NamedQuery(name = "Resumenes_eeff.findByTotalGastos", query = "SELECT r FROM Resumenes_eeff r WHERE r.totalGastos = :totalGastos")
    , @NamedQuery(name = "Resumenes_eeff.findByIdResumeneeff", query = "SELECT r FROM Resumenes_eeff r WHERE r.idResumeneeff = :idResumeneeff")
    , @NamedQuery(name = "Resumenes_eeff.findByFechainsercion", query = "SELECT r FROM Resumenes_eeff r WHERE r.fechainsercion = :fechainsercion")
    , @NamedQuery(name = "Resumenes_eeff.findByFilter", query = "SELECT DISTINCT r.resumenes_eeffPK.nit, r.nombreempresa, r.sector, r.fechaacta, r.estadoaprobacion FROM Resumenes_eeff r WHERE ((:nit is null ) or trim(r.resumenes_eeffPK.nit) = :nit) AND ((:fechaacta is null ) or func('TO_CHAR', r.fechaacta, 'dd/mm/yyyy') = :fechaacta)  AND ((:estadoaprobacion is null ) or trim(r.estadoaprobacion) = :estadoaprobacion) AND ((:nombreempresa is null ) or trim(r.nombreempresa) = :nombreempresa) AND ((:fechainsercion is null ) or func('TO_CHAR', r.fechainsercion, 'dd/mm/yyyy') = :fechainsercion) ")
    , @NamedQuery(name = "Resumenes_eeff.cargadosMes", query = "SELECT count(distinct(r.resumenes_eeffPK.nit)) FROM Resumenes_eeff r WHERE r.fechainsercion BETWEEN :fechaInicio AND :fechaFin ")
})
public class Resumenes_eeff implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Resumenes_eeffPK resumenes_eeffPK;
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
    @Basic(optional = false)
    @Column(name = "id_resumeneeff")
    private int idResumeneeff;
    @Column(name = "fechainsercion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainsercion;

    public Resumenes_eeff() {
    }

    public Resumenes_eeff(Resumenes_eeffPK resumenes_eeffPK) {
        this.resumenes_eeffPK = resumenes_eeffPK;
    }

    public Resumenes_eeff(Resumenes_eeffPK resumenes_eeffPK, int idResumeneeff) {
        this.resumenes_eeffPK = resumenes_eeffPK;
        this.idResumeneeff = idResumeneeff;
    }

    public Resumenes_eeff(String nit, Date aniobalance) {
        this.resumenes_eeffPK = new Resumenes_eeffPK(nit, aniobalance);
    }

    public Resumenes_eeffPK getResumenes_eeffPK() {
        return resumenes_eeffPK;
    }

    public void setResumenes_eeffPK(Resumenes_eeffPK resumenes_eeffPK) {
        this.resumenes_eeffPK = resumenes_eeffPK;
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

    public int getIdResumeneeff() {
        return idResumeneeff;
    }

    public void setIdResumeneeff(int idResumeneeff) {
        this.idResumeneeff = idResumeneeff;
    }

    public Date getFechainsercion() {
        return fechainsercion;
    }

    public void setFechainsercion(Date fechainsercion) {
        this.fechainsercion = fechainsercion;
    }

    public Date getAniobalance() {
        return aniobalance;
    }

    public void setAniobalance(Date aniobalance) {
        this.aniobalance = aniobalance;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resumenes_eeffPK != null ? resumenes_eeffPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resumenes_eeff)) {
            return false;
        }
        Resumenes_eeff other = (Resumenes_eeff) object;
        if ((this.resumenes_eeffPK == null && other.resumenes_eeffPK != null) || (this.resumenes_eeffPK != null && !this.resumenes_eeffPK.equals(other.resumenes_eeffPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Resumenes_eeff[ resumenes_eeffPK=" + resumenes_eeffPK + " ]";
    }

}
