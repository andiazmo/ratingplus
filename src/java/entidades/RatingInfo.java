/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.math.BigDecimal;

/**
 *
 * @author santiago
 */
public class RatingInfo {
    
    private int id;
    private String razonSocial;
    private String grupoEconomico;
    private String nit;
    private BigDecimal valorRating;
    private BigDecimal valorRatingFinal;
    private int numeroCaso;
    private String estado;
    private BigDecimal apalancamiento;
    private BigDecimal ebitda;
    private BigDecimal dfnCfl;
    private BigDecimal dfnEbitda;
    private String cfl;
    private BigDecimal liquidez;
    private String coberturaInteres;
    private BigDecimal margenBitda;
    private BigDecimal rotacionClientes;
    private BigDecimal rotacionInventarios;
    private BigDecimal rotacionProveedores;
    private String calificacion;
    private String garantia;
    private String indicadorMora;
    private String numeroBancos;
    private String reestructuracion;
    private String evolucionEsperada;
    private String posicionMercado;
    private String dependenciaClientes;
    private String concentracionProveedor;
    private String voluntadCapacidad;
    private String garantiasAdicionales;
    private String calidadRevisor;
    private String informeRevisor;
    private String tipoProducto;
    private String gerenciaCapacidad;
    private String abanicoBancario;
    private String mecanismosFinanciacion;
    private String estructuraCostos;
    private String capacidadAtencion;
    private String gradoAutofinanciacion;
    private String existenciaDeudas;
    private String perfilPagoDeuda;
    private String calidadActivos;
    private String tipoAccionista;
    private BigDecimal interceptoSumatoria;
    private BigDecimal woeObjetivable;
    private BigDecimal woeFinanciero;
    private BigDecimal woeComportamiento;
    private BigDecimal woeSubjetivo;
    private BigDecimal betaFinanciero;
    private BigDecimal betaComportamiento;
    private BigDecimal betaObjetivable;
    private BigDecimal interceptoObjSubj;
    private BigDecimal sumatoriaWoe;
    private BigDecimal sumatoriaBeta;
    private BigDecimal betaSubjetivo;
    private BigDecimal valorAlpha;
    private BigDecimal valorBeta;
    private BigDecimal valorLogOdds;
    private String usuario;
    private String fechaInsercion;
    private String comentariosUsuario;
    private BigDecimal deudaFinanciera;
    private BigDecimal resultadoExplotacionEbitda;
    private BigDecimal cfo;
    private BigDecimal gastosFinancieros;
    private BigDecimal margenesUtilidadOperativa;
    private BigDecimal margenEbitda;
    private BigDecimal rotacionClientesDias;
    private BigDecimal rotacionInventariosDias;
    private BigDecimal rotacionProveedoresDias;
    private String comentarioUsuario;
    private String usuarioBanco;
    private String rangoApalancamiento;
    private String rangoDFNEBITDA;
    private String rangoDFNCFL;
    private String rangoLiquidez;
    private String rangoCoberturaIntereses;
    private String rangoMargenEBITDA;
    private String rangoRotacionClientesDias;
    private String rangoRotacionInventariosDias;
    private String rangoRotacionProveedoresDias;

    public RatingInfo() {
        super();
    }
    
    public RatingInfo(String nit, String razonSocial) {
        this.nit = nit;
        this.razonSocial = razonSocial;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the grupoEconomico
     */
    public String getGrupoEconomico() {
        return grupoEconomico;
    }

    /**
     * @param grupoEconomico the grupoEconomico to set
     */
    public void setGrupoEconomico(String grupoEconomico) {
        this.grupoEconomico = grupoEconomico;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the valorRating
     */
    public BigDecimal getValorRating() {
        return valorRating;
    }

    /**
     * @param valorRating the valorRating to set
     */
    public void setValorRating(BigDecimal valorRating) {
        this.valorRating = valorRating;
    }

    /**
     * @return the valorRatingFinal
     */
    public BigDecimal getValorRatingFinal() {
        return valorRatingFinal;
    }

    /**
     * @param valorRatingFinal the valorRatingFinal to set
     */
    public void setValorRatingFinal(BigDecimal valorRatingFinal) {
        this.valorRatingFinal = valorRatingFinal;
    }

    /**
     * @return the numeroCaso
     */
    public int getNumeroCaso() {
        return numeroCaso;
    }

    /**
     * @param numeroCaso the numeroCaso to set
     */
    public void setNumeroCaso(int numeroCaso) {
        this.numeroCaso = numeroCaso;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the apalancamiento
     */
    public BigDecimal getApalancamiento() {
        return apalancamiento;
    }

    /**
     * @param apalancamiento the apalancamiento to set
     */
    public void setApalancamiento(BigDecimal apalancamiento) {
        this.apalancamiento = apalancamiento;
    }

    /**
     * @return the ebitda
     */
    public BigDecimal getEbitda() {
        return ebitda;
    }

    /**
     * @param ebitda the ebitda to set
     */
    public void setEbitda(BigDecimal ebitda) {
        this.ebitda = ebitda;
    }

    /**
     * @return the cfl
     */
    public String getCfl() {
        return cfl;
    }

    /**
     * @param cfl the cfl to set
     */
    public void setCfl(String cfl) {
        this.cfl = cfl;
    }

    /**
     * @return the liquidez
     */
    public BigDecimal getLiquidez() {
        return liquidez;
    }

    /**
     * @param liquidez the liquidez to set
     */
    public void setLiquidez(BigDecimal liquidez) {
        this.liquidez = liquidez;
    }

    /**
     * @return the coberturaInteres
     */
    public String getCoberturaInteres() {
        return coberturaInteres;
    }

    /**
     * @param coberturaInteres the coberturaInteres to set
     */
    public void setCoberturaInteres(String coberturaInteres) {
        this.coberturaInteres = coberturaInteres;
    }

    /**
     * @return the margenBitda
     */
    public BigDecimal getMargenBitda() {
        return margenBitda;
    }

    /**
     * @param margenBitda the margenBitda to set
     */
    public void setMargenBitda(BigDecimal margenBitda) {
        this.margenBitda = margenBitda;
    }

    /**
     * @return the rotacionClientes
     */
    public BigDecimal getRotacionClientes() {
        return rotacionClientes;
    }

    /**
     * @param rotacionClientes the rotacionClientes to set
     */
    public void setRotacionClientes(BigDecimal rotacionClientes) {
        this.rotacionClientes = rotacionClientes;
    }

    /**
     * @return the rotacionInventarios
     */
    public BigDecimal getRotacionInventarios() {
        return rotacionInventarios;
    }

    /**
     * @param rotacionInventarios the rotacionInventarios to set
     */
    public void setRotacionInventarios(BigDecimal rotacionInventarios) {
        this.rotacionInventarios = rotacionInventarios;
    }

    /**
     * @return the rotacionProveedores
     */
    public BigDecimal getRotacionProveedores() {
        return rotacionProveedores;
    }

    /**
     * @param rotacionProveedores the rotacionProveedores to set
     */
    public void setRotacionProveedores(BigDecimal rotacionProveedores) {
        this.rotacionProveedores = rotacionProveedores;
    }

    /**
     * @return the calificacion
     */
    public String getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the garantia
     */
    public String getGarantia() {
        return garantia;
    }

    /**
     * @param garantia the garantia to set
     */
    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    /**
     * @return the indicadorMora
     */
    public String getIndicadorMora() {
        return indicadorMora;
    }

    /**
     * @param indicadorMora the indicadorMora to set
     */
    public void setIndicadorMora(String indicadorMora) {
        this.indicadorMora = indicadorMora;
    }

    /**
     * @return the numeroBancos
     */
    public String getNumeroBancos() {
        return numeroBancos;
    }

    /**
     * @param numeroBancos the numeroBancos to set
     */
    public void setNumeroBancos(String numeroBancos) {
        this.numeroBancos = numeroBancos;
    }

    /**
     * @return the reestructuracion
     */
    public String getReestructuracion() {
        return reestructuracion;
    }

    /**
     * @param reestructuracion the reestructuracion to set
     */
    public void setReestructuracion(String reestructuracion) {
        this.reestructuracion = reestructuracion;
    }

    /**
     * @return the evolucionEsperada
     */
    public String getEvolucionEsperada() {
        return evolucionEsperada;
    }

    /**
     * @param evolucionEsperada the evolucionEsperada to set
     */
    public void setEvolucionEsperada(String evolucionEsperada) {
        this.evolucionEsperada = evolucionEsperada;
    }

    /**
     * @return the posicionMercado
     */
    public String getPosicionMercado() {
        return posicionMercado;
    }

    /**
     * @param posicionMercado the posicionMercado to set
     */
    public void setPosicionMercado(String posicionMercado) {
        this.posicionMercado = posicionMercado;
    }

    /**
     * @return the dependenciaClientes
     */
    public String getDependenciaClientes() {
        return dependenciaClientes;
    }

    /**
     * @param dependenciaClientes the dependenciaClientes to set
     */
    public void setDependenciaClientes(String dependenciaClientes) {
        this.dependenciaClientes = dependenciaClientes;
    }

    /**
     * @return the concentracionProveedor
     */
    public String getConcentracionProveedor() {
        return concentracionProveedor;
    }

    /**
     * @param concentracionProveedor the concentracionProveedor to set
     */
    public void setConcentracionProveedor(String concentracionProveedor) {
        this.concentracionProveedor = concentracionProveedor;
    }

    /**
     * @return the voluntadCapacidad
     */
    public String getVoluntadCapacidad() {
        return voluntadCapacidad;
    }

    /**
     * @param voluntadCapacidad the voluntadCapacidad to set
     */
    public void setVoluntadCapacidad(String voluntadCapacidad) {
        this.voluntadCapacidad = voluntadCapacidad;
    }

    /**
     * @return the garantiasAdicionales
     */
    public String getGarantiasAdicionales() {
        return garantiasAdicionales;
    }

    /**
     * @param garantiasAdicionales the garantiasAdicionales to set
     */
    public void setGarantiasAdicionales(String garantiasAdicionales) {
        this.garantiasAdicionales = garantiasAdicionales;
    }

    /**
     * @return the calidadRevisor
     */
    public String getCalidadRevisor() {
        return calidadRevisor;
    }

    /**
     * @param calidadRevisor the calidadRevisor to set
     */
    public void setCalidadRevisor(String calidadRevisor) {
        this.calidadRevisor = calidadRevisor;
    }

    /**
     * @return the informeRevisor
     */
    public String getInformeRevisor() {
        return informeRevisor;
    }

    /**
     * @param informeRevisor the informeRevisor to set
     */
    public void setInformeRevisor(String informeRevisor) {
        this.informeRevisor = informeRevisor;
    }

    /**
     * @return the tipoProducto
     */
    public String getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the gerenciaCapacidad
     */
    public String getGerenciaCapacidad() {
        return gerenciaCapacidad;
    }

    /**
     * @param gerenciaCapacidad the gerenciaCapacidad to set
     */
    public void setGerenciaCapacidad(String gerenciaCapacidad) {
        this.gerenciaCapacidad = gerenciaCapacidad;
    }

    /**
     * @return the abanicoBancario
     */
    public String getAbanicoBancario() {
        return abanicoBancario;
    }

    /**
     * @param abanicoBancario the abanicoBancario to set
     */
    public void setAbanicoBancario(String abanicoBancario) {
        this.abanicoBancario = abanicoBancario;
    }

    /**
     * @return the mecanismosFinanciacion
     */
    public String getMecanismosFinanciacion() {
        return mecanismosFinanciacion;
    }

    /**
     * @param mecanismosFinanciacion the mecanismosFinanciacion to set
     */
    public void setMecanismosFinanciacion(String mecanismosFinanciacion) {
        this.mecanismosFinanciacion = mecanismosFinanciacion;
    }

    /**
     * @return the estructuraCostos
     */
    public String getEstructuraCostos() {
        return estructuraCostos;
    }

    /**
     * @param estructuraCostos the estructuraCostos to set
     */
    public void setEstructuraCostos(String estructuraCostos) {
        this.estructuraCostos = estructuraCostos;
    }

    /**
     * @return the capacidadAtencion
     */
    public String getCapacidadAtencion() {
        return capacidadAtencion;
    }

    /**
     * @param capacidadAtencion the capacidadAtencion to set
     */
    public void setCapacidadAtencion(String capacidadAtencion) {
        this.capacidadAtencion = capacidadAtencion;
    }

    /**
     * @return the gradoAutofinanciacion
     */
    public String getGradoAutofinanciacion() {
        return gradoAutofinanciacion;
    }

    /**
     * @param gradoAutofinanciacion the gradoAutofinanciacion to set
     */
    public void setGradoAutofinanciacion(String gradoAutofinanciacion) {
        this.gradoAutofinanciacion = gradoAutofinanciacion;
    }

    /**
     * @return the existenciaDeudas
     */
    public String getExistenciaDeudas() {
        return existenciaDeudas;
    }

    /**
     * @param existenciaDeudas the existenciaDeudas to set
     */
    public void setExistenciaDeudas(String existenciaDeudas) {
        this.existenciaDeudas = existenciaDeudas;
    }

    /**
     * @return the perfilPagoDeuda
     */
    public String getPerfilPagoDeuda() {
        return perfilPagoDeuda;
    }

    /**
     * @param perfilPagoDeuda the perfilPagoDeuda to set
     */
    public void setPerfilPagoDeuda(String perfilPagoDeuda) {
        this.perfilPagoDeuda = perfilPagoDeuda;
    }

    /**
     * @return the calidadActivos
     */
    public String getCalidadActivos() {
        return calidadActivos;
    }

    /**
     * @param calidadActivos the calidadActivos to set
     */
    public void setCalidadActivos(String calidadActivos) {
        this.calidadActivos = calidadActivos;
    }

    /**
     * @return the tipoAccionista
     */
    public String getTipoAccionista() {
        return tipoAccionista;
    }

    /**
     * @param tipoAccionista the tipoAccionista to set
     */
    public void setTipoAccionista(String tipoAccionista) {
        this.tipoAccionista = tipoAccionista;
    }

    /**
     * @return the interceptoSumatoria
     */
    public BigDecimal getInterceptoSumatoria() {
        return interceptoSumatoria;
    }

    /**
     * @param interceptoSumatoria the interceptoSumatoria to set
     */
    public void setInterceptoSumatoria(BigDecimal interceptoSumatoria) {
        this.interceptoSumatoria = interceptoSumatoria;
    }

    /**
     * @return the woeObjetivable
     */
    public BigDecimal getWoeObjetivable() {
        return woeObjetivable;
    }

    /**
     * @param woeObjetivable the woeObjetivable to set
     */
    public void setWoeObjetivable(BigDecimal woeObjetivable) {
        this.woeObjetivable = woeObjetivable;
    }

    /**
     * @return the woeFinanciero
     */
    public BigDecimal getWoeFinanciero() {
        return woeFinanciero;
    }

    /**
     * @param woeFinanciero the woeFinanciero to set
     */
    public void setWoeFinanciero(BigDecimal woeFinanciero) {
        this.woeFinanciero = woeFinanciero;
    }

    /**
     * @return the woeComportamiento
     */
    public BigDecimal getWoeComportamiento() {
        return woeComportamiento;
    }

    /**
     * @param woeComportamiento the woeComportamiento to set
     */
    public void setWoeComportamiento(BigDecimal woeComportamiento) {
        this.woeComportamiento = woeComportamiento;
    }

    /**
     * @return the woeSubjetivo
     */
    public BigDecimal getWoeSubjetivo() {
        return woeSubjetivo;
    }

    /**
     * @param woeSubjetivo the woeSubjetivo to set
     */
    public void setWoeSubjetivo(BigDecimal woeSubjetivo) {
        this.woeSubjetivo = woeSubjetivo;
    }

    /**
     * @return the betaFinanciero
     */
    public BigDecimal getBetaFinanciero() {
        return betaFinanciero;
    }

    /**
     * @param betaFinanciero the betaFinanciero to set
     */
    public void setBetaFinanciero(BigDecimal betaFinanciero) {
        this.betaFinanciero = betaFinanciero;
    }

    /**
     * @return the betaComportamiento
     */
    public BigDecimal getBetaComportamiento() {
        return betaComportamiento;
    }

    /**
     * @param betaComportamiento the betaComportamiento to set
     */
    public void setBetaComportamiento(BigDecimal betaComportamiento) {
        this.betaComportamiento = betaComportamiento;
    }

    /**
     * @return the betaObjetivable
     */
    public BigDecimal getBetaObjetivable() {
        return betaObjetivable;
    }

    /**
     * @param betaObjetivable the betaObjetivable to set
     */
    public void setBetaObjetivable(BigDecimal betaObjetivable) {
        this.betaObjetivable = betaObjetivable;
    }

    /**
     * @return the interceptoObjSubj
     */
    public BigDecimal getInterceptoObjSubj() {
        return interceptoObjSubj;
    }

    /**
     * @param interceptoObjSubj the interceptoObjSubj to set
     */
    public void setInterceptoObjSubj(BigDecimal interceptoObjSubj) {
        this.interceptoObjSubj = interceptoObjSubj;
    }

    /**
     * @return the sumatoriaWoe
     */
    public BigDecimal getSumatoriaWoe() {
        return sumatoriaWoe;
    }

    /**
     * @param sumatoriaWoe the sumatoriaWoe to set
     */
    public void setSumatoriaWoe(BigDecimal sumatoriaWoe) {
        this.sumatoriaWoe = sumatoriaWoe;
    }

    /**
     * @return the sumatoriaBeta
     */
    public BigDecimal getSumatoriaBeta() {
        return sumatoriaBeta;
    }

    /**
     * @param sumatoriaBeta the sumatoriaBeta to set
     */
    public void setSumatoriaBeta(BigDecimal sumatoriaBeta) {
        this.sumatoriaBeta = sumatoriaBeta;
    }

    /**
     * @return the betaSubjetivo
     */
    public BigDecimal getBetaSubjetivo() {
        return betaSubjetivo;
    }

    /**
     * @param betaSubjetivo the betaSubjetivo to set
     */
    public void setBetaSubjetivo(BigDecimal betaSubjetivo) {
        this.betaSubjetivo = betaSubjetivo;
    }

    /**
     * @return the valorAlpha
     */
    public BigDecimal getValorAlpha() {
        return valorAlpha;
    }

    /**
     * @param valorAlpha the valorAlpha to set
     */
    public void setValorAlpha(BigDecimal valorAlpha) {
        this.valorAlpha = valorAlpha;
    }

    /**
     * @return the valorBeta
     */
    public BigDecimal getValorBeta() {
        return valorBeta;
    }

    /**
     * @param valorBeta the valorBeta to set
     */
    public void setValorBeta(BigDecimal valorBeta) {
        this.valorBeta = valorBeta;
    }

    /**
     * @return the valorLogOdds
     */
    public BigDecimal getValorLogOdds() {
        return valorLogOdds;
    }

    /**
     * @param valorLogOdds the valorLogOdds to set
     */
    public void setValorLogOdds(BigDecimal valorLogOdds) {
        this.valorLogOdds = valorLogOdds;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the fechaInsercion
     */
    public String getFechaInsercion() {
        return fechaInsercion;
    }

    /**
     * @param fechaInsercion the fechaInsercion to set
     */
    public void setFechaInsercion(String fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    /**
     * @return the comentariosUsuario
     */
    public String getComentariosUsuario() {
        return comentariosUsuario;
    }

    /**
     * @param comentariosUsuario the comentariosUsuario to set
     */
    public void setComentariosUsuario(String comentariosUsuario) {
        this.comentariosUsuario = comentariosUsuario;
    }
    
    
    public BigDecimal getDeudaFinanciera() {
        return deudaFinanciera;
    }

    public void setDeudaFinanciera(BigDecimal deudaFinanciera) {
        this.deudaFinanciera = deudaFinanciera;
    }

    public BigDecimal getResultadoExplotacionEbitda() {
        return resultadoExplotacionEbitda;
    }

    public void setResultadoExplotacionEbitda(BigDecimal resultadoExplotacionEbitda) {
        this.resultadoExplotacionEbitda = resultadoExplotacionEbitda;
    }

    public BigDecimal getCfo() {
        return cfo;
    }

    public void setCfo(BigDecimal cfo) {
        this.cfo = cfo;
    }

    public BigDecimal getGastosFinancieros() {
        return gastosFinancieros;
    }

    public void setGastosFinancieros(BigDecimal gastosFinancieros) {
        this.gastosFinancieros = gastosFinancieros;
    }

    public BigDecimal getMargenesUtilidadOperativa() {
        return margenesUtilidadOperativa;
    }

    public void setMargenesUtilidadOperativa(BigDecimal margenesUtilidadOperativa) {
        this.margenesUtilidadOperativa = margenesUtilidadOperativa;
    }

    public BigDecimal getMargenEbitda() {
        return margenEbitda;
    }

    public void setMargenEbitda(BigDecimal margenEbitda) {
        this.margenEbitda = margenEbitda;
    }

    public BigDecimal getRotacionClientesDias() {
        return rotacionClientesDias;
    }

    public void setRotacionClientesDias(BigDecimal rotacionClientesDias) {
        this.rotacionClientesDias = rotacionClientesDias;
    }

    public BigDecimal getRotacionInventariosDias() {
        return rotacionInventariosDias;
    }

    public void setRotacionInventariosDias(BigDecimal rotacionInventariosDias) {
        this.rotacionInventariosDias = rotacionInventariosDias;
    }

    public BigDecimal getRotacionProveedoresDias() {
        return rotacionProveedoresDias;
    }

    public void setRotacionProveedoresDias(BigDecimal rotacionProveedoresDias) {
        this.rotacionProveedoresDias = rotacionProveedoresDias;
    }
    
    public String getComentarioUsuario() {
        return comentarioUsuario;
    }

    public void setComentarioUsuario(String comentarioUsuario) {
        this.comentarioUsuario = comentarioUsuario;
    }
    
    public BigDecimal getDfnCfl() {
        return dfnCfl;
    }

    public void setDfnCfl(BigDecimal dfnCfl) {
        this.dfnCfl = dfnCfl;
    }
    
    public String toString() {
        return getNit();
    }
    
    public String getUsuarioBanco() {
        return usuarioBanco;
    }

    public void setUsuarioBanco(String usuarioBanco) {
        this.usuarioBanco = usuarioBanco;
    }
    
    public String getRangoApalancamiento() {
        return rangoApalancamiento;
    }

    public void setRangoApalancamiento(String rangoApalancamiento) {
        this.rangoApalancamiento = rangoApalancamiento;
    }

    public String getRangoDFNEBITDA() {
        return rangoDFNEBITDA;
    }

    public void setRangoDFNEBITDA(String rangoDFNEBITDA) {
        this.rangoDFNEBITDA = rangoDFNEBITDA;
    }

    public String getRangoDFNCFL() {
        return rangoDFNCFL;
    }

    public void setRangoDFNCFL(String rangoDFNCFL) {
        this.rangoDFNCFL = rangoDFNCFL;
    }

    public String getRangoLiquidez() {
        return rangoLiquidez;
    }

    public void setRangoLiquidez(String rangoLiquidez) {
        this.rangoLiquidez = rangoLiquidez;
    }

    public String getRangoCoberturaIntereses() {
        return rangoCoberturaIntereses;
    }

    public void setRangoCoberturaIntereses(String rangoCoberturaIntereses) {
        this.rangoCoberturaIntereses = rangoCoberturaIntereses;
    }

    public String getRangoMargenEBITDA() {
        return rangoMargenEBITDA;
    }

    public void setRangoMargenEBITDA(String rangoMargenEBITDA) {
        this.rangoMargenEBITDA = rangoMargenEBITDA;
    }

    public String getRangoRotacionClientesDias() {
        return rangoRotacionClientesDias;
    }

    public void setRangoRotacionClientesDias(String rangoRotacionClientesDias) {
        this.rangoRotacionClientesDias = rangoRotacionClientesDias;
    }

    public String getRangoRotacionInventariosDias() {
        return rangoRotacionInventariosDias;
    }

    public void setRangoRotacionInventariosDias(String rangoRotacionInventariosDias) {
        this.rangoRotacionInventariosDias = rangoRotacionInventariosDias;
    }

    public String getRangoRotacionProveedoresDias() {
        return rangoRotacionProveedoresDias;
    }

    public void setRangoRotacionProveedoresDias(String rangoRotacionProveedoresDias) {
        this.rangoRotacionProveedoresDias = rangoRotacionProveedoresDias;
    }
    
    public BigDecimal getDfnEbitda() {
        return dfnEbitda;
    }

    public void setDfnEbitda(BigDecimal dfnEbitda) {
        this.dfnEbitda = dfnEbitda;
    }
    
}