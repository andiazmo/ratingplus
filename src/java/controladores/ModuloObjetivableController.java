/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.ClientesRating;
import entidades.GruposClientes;
import entidades.Modulo;
import entidades.RatingInfo;
import entidades.VariablesRating;
import fachadas.ConsultaClientesRatingFacade;
import fachadas.ConsultaObjetivableFacade;
import fachadas.ConsultaVariablesRatingFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.UsuarioSeccion;

/**
 *
 * @author santiago
 */
@ManagedBean(name="moduloObjetivable")
@ViewScoped
public class ModuloObjetivableController extends AbstractController{

    @EJB
    private ConsultaObjetivableFacade ejbFacade;
    @EJB
    private ConsultaClientesRatingFacade ejbFacadeCliente;
    @EJB
    private ConsultaVariablesRatingFacade ejbFacadeVariables;
    private List<GruposClientes> listaGrupos;
    private List<ClientesRating> listaClientes;
    private List<ClientesRating> listaClientesSeleccionados = 
            new ArrayList<>();
    private List<RatingInfo> informacionRating;
    private List<VariablesRating> listaVariables;
    private List<VariablesRating> listaVariablesFinanciero;
    private List<VariablesRating> listaVariablesComportamiento;
    private List<VariablesRating> listaRespuestasVariablesComportamiento;
    private List<VariablesRating> listaRespuestasVariablesObjetivable;
    private List<VariablesRating> listaVariablesObjetivo;
    private List<VariablesRating> listaVariablesSubjetivo;
    private List<VariablesRating> listaVariablesFront;
    private List<VariablesRating> listaVariablesFrontComportamiento;
    private List<VariablesRating> listaRespuestasVariablesFrontComportamiento;
    private List<VariablesRating> listaVariablesFrontObjetivo;
    private List<VariablesRating> listaVariablesFrontResultado;
    private List<List<VariablesRating>> listaVariablesModulo;
    private List<List<VariablesRating>> listaRespuestasVariablesModulo;
    private String tipoConsulta;
    private String nitDiligenciado;
    private String nombreDiligenciado;
    private Integer grupoSeleccionado;
    private RatingInfo objetoRatingInfo;
    private List<Modulo> listaModulos;
    private List<VariablesRating> variablesRating;
    private List<List<VariablesRating>> listVariablesRating;
    private List<VariablesRating> variablesRatingComportamiento;
    private List<VariablesRating> variablesRatingResultado;
    private List<VariablesRating> listaRespuestasVariablesObjetivableFront;
    private String respuestaCalificacion;
    private String respuestaGarantias;
    private String respuestaIndMora;
    private String respuestaNumeroBancos;
    private String respuestaMarcacionReestructuracion;
    private String evolucionEsperada;
    private String posicionMercado;
    private String dependenciaClientes;
    private String concentracionProveedor;
    private String voluntadCapacidadApoyo;
    private String garantiasAdicionales;
    private String calidadRevisorFiscal;
    private String informeRevisorFiscal;
    private String tipoProductoServicio;
    private String gerenciaCapacidadProfesionalidad;
    private String abanicoBancario;
    private String mecanismosFinanciacion;
    private String estructuraCostos;
    private String capacidadAtenderCalendario;
    private String gradoAutoFinanciacion;
    private String existenciaDeudas;
    private String perfilPago;
    private String calidadActivos;
    private String tipoAccionista;
    private Map<String, String> mapRespuestasEvolucion = new HashMap<>();
    private Map<String, String> mapRespuestasPosicion = new HashMap<>();
    private Map<String, String> mapRespuestasDependencia = new HashMap<>();
    private Map<String, String> mapRespuestasConcentracion = new HashMap<>();
    private Map<String, String> mapRespuestasVoluntad = new HashMap<>();
    private Map<String, String> mapRespuestasGarantias= new HashMap<>();
    private Map<String, String> mapRespuestasCalidad = new HashMap<>();
    private Map<String, String> mapRespuestasInforme = new HashMap<>();

    @PostConstruct
    @Override
    public void init(){
        this.precargaInformacion();
        this.precargaListaVariables();
        this.precargaListaRespuestasVariables();
        
        listaVariablesFrontObjetivo = this.precargaListaObjetivo();
        listaRespuestasVariablesObjetivableFront = 
                this.precargaRespuestaListaObjetivable();
    }

    public void precargaInformacion(){
        this.setListaGrupos(getEjbFacadeCliente().listarGruposCliente()); 
    }
    
    public void precargaListaVariables(){
       this.setListaVariablesModulo(getEjbFacadeVariables().listarVariablesRating());
  
    }
    
    public void precargaListaRespuestasVariables(){
       this.setListaRespuestasVariablesModulo(getEjbFacadeVariables().
               listarRespuestasVariablesRating());
    }
     
    public List<VariablesRating> precargaRespuestaListaObjetivable(){ 
       this.setListaRespuestasVariablesObjetivable(this.
               getListaRespuestasVariablesModulo().get(2));
      
        return this.getListaRespuestasVariablesObjetivable();
    }
    

    
    public List<VariablesRating> respuestasPorVariableObjetivo(){
        
        mapRespuestasEvolucion.
                        put(listaRespuestasVariablesObjetivable.get(0).
                                getRespuesta(),
                                listaRespuestasVariablesObjetivable.
                                        get(0).getRespuesta());
        
        for (int i = 1; i < listaRespuestasVariablesObjetivable.size(); i++) {
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    contains("Evolucion")){
                mapRespuestasEvolucion.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                
                mapRespuestasEvolucion.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    contains("Posicion")){
                mapRespuestasPosicion.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                mapRespuestasPosicion.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
            
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().contains("Dependencia")){
                mapRespuestasDependencia.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                mapRespuestasDependencia.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
            
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().contains("Concentracion")){
                mapRespuestasConcentracion.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                mapRespuestasConcentracion.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
            
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().contains("Voluntad")){
                mapRespuestasVoluntad.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                mapRespuestasVoluntad.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().contains("Garantias")){
                mapRespuestasGarantias.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                mapRespuestasGarantias.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().contains("Calidad")){
                mapRespuestasGarantias.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                mapRespuestasGarantias.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
            if(listaRespuestasVariablesObjetivable.get(i).getNombre().contains("Informe")){
                mapRespuestasCalidad.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                
                if(listaRespuestasVariablesObjetivable.get(i).getNombre().
                    equals(listaRespuestasVariablesObjetivable.get(i-1).getNombre())) {
                mapRespuestasCalidad.
                        put(listaRespuestasVariablesObjetivable.get(i).getRespuesta(),
                                listaRespuestasVariablesObjetivable.get(i).getRespuesta());
                }
            }
        }
       
       return listaRespuestasVariablesObjetivable;
    }
    
    public List<VariablesRating> precargaListaObjetivo(){
       this.setListaVariablesObjetivo(this.getListaVariablesModulo().get(2));
       
       return listaVariablesObjetivo;
    }
    
   public void reasignarControles(){
        switch(Integer.parseInt(tipoConsulta)){     
            case 0:
                this.nombreDiligenciado = "";
                break;
                
            case 1:
                this.nitDiligenciado = "";
                break;
        }
    }

    public void limpiarResultados(ActionEvent event){
        this.listaClientes = new ArrayList<>();
        this.tipoConsulta = null;
        this.nitDiligenciado = "";
        this.nombreDiligenciado = "";
    }
    
    public void consultaClientes(ActionEvent event){
        String parametroConsulta = tipoConsulta.equals("0") ? 
                this.nitDiligenciado : this.nombreDiligenciado;
        
        this.listaClientes = ejbFacadeCliente.
                consultaClienteGrupoResultadoSinResRating(tipoConsulta, 
                        parametroConsulta);
        
        if(listaClientes.isEmpty()){
            this.listaClientes = ejbFacadeCliente.
                    consultaClienteGrupoResultadoSinResRating(
                            tipoConsulta, parametroConsulta);
            if(listaClientes.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(":growl", 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                "No se ha encontrado información de clientes "
                                        + "con los criterios de búsqueda", ""));
            }
        }
        else {
            this.setNitDiligenciado(this.listaClientes.get(0).getNit());
        }    
    }
    
    public void registrarObjetivable(ActionEvent event) throws Exception{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");    
        String usuario = seccion.getUsuario().getCodigo();
            
        Boolean result = ejbFacade.registrarObjetivable(this.nitDiligenciado, 
                this.evolucionEsperada, this.posicionMercado, 
                this.dependenciaClientes, this.concentracionProveedor, 
                this.voluntadCapacidadApoyo, this.garantiasAdicionales, 
                this.calidadRevisorFiscal,this.informeRevisorFiscal, usuario);
        
        if(result){
            addMessage("Registro Exitoso", 
                    "Se registro la información en el Módulo Objetivable al usuaio: "
                    +this.nitDiligenciado);
        }
        else{
            addMessage("Registro Fallido", "Por favor intente mas tarde"
                    +this.nitDiligenciado);
        }
    }
    
    /**
     * @return the ejbFacade
     */
    public ConsultaObjetivableFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ConsultaObjetivableFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the listaGrupos
     */
    public List<GruposClientes> getListaGrupos() {
        return listaGrupos;
    }

    /**
     * @param listaGrupos the listaGrupos to set
     */
    public void setListaGrupos(List<GruposClientes> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    /**
     * @return the listaClientes
     */
    public List<ClientesRating> getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(List<ClientesRating> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * @return the listaClientes
     */
    public List<ClientesRating> getListaClientesSeleccionados() {
        return listaClientesSeleccionados;
    }

    /**
     * @param listaClientesSeleccionados the listaClientesSeleccionados to set
     */
    public void setListaClientesSeleccionados(List<ClientesRating> listaClientesSeleccionados) {
        this.listaClientesSeleccionados = listaClientesSeleccionados;
    }

    /**
     * @return the informacionRating
     */
    public List<RatingInfo> getInformacionRating() {
        return informacionRating;
    }

    /**
     * @param informacionRating the informacionRating to set
     */
    public void setInformacionRating(List<RatingInfo> informacionRating) {
        this.informacionRating = informacionRating;
    }    
    
    /**
     * @return the tipoConsulta
     */
    public String getTipoConsulta() {
        return tipoConsulta;
    }

    /**
     * @param tipoConsulta the tipoConsulta to set
     */
    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    /**
     * @return the nitDiligenciado
     */
    public String getNitDiligenciado() {
        return nitDiligenciado;
    }

    /**
     * @param nitDiligenciado the nitDiligenciado to set
     */
    public void setNitDiligenciado(String nitDiligenciado) {
        this.nitDiligenciado = nitDiligenciado;
    }

    /**
     * @return the nombreDiligenciado
     */
    public String getNombreDiligenciado() {
        return nombreDiligenciado;
    }

    /**
     * @param nombreDiligenciado the nombreDiligenciado to set
     */
    public void setNombreDiligenciado(String nombreDiligenciado) {
        this.nombreDiligenciado = nombreDiligenciado;
    }

    /**
     * @return the grupoSeleccionado
     */
    public Integer getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    /**
     * @param grupoSeleccionado the grupoSeleccionado to set
     */
    public void setGrupoSeleccionado(Integer grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }

        /**
     * @return the objetoRatingInfo
     */
    public RatingInfo getObjetoRatingInfo() {
        return objetoRatingInfo;
    }

    /**
     * @param objetoRatingInfo the objetoRatingInfo to set
     */
    public void setObjetoRatingInfo(RatingInfo objetoRatingInfo) {
        this.objetoRatingInfo = objetoRatingInfo;
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = 
                new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void reload() throws IOException {
        ExternalContext ec = 
                FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());        
    }

    public List<VariablesRating> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(List<VariablesRating> listaVariables) {
        this.listaVariables = listaVariables;
    }
    
    public List<Modulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }
    
    public List<VariablesRating> getListaVariablesFinanciero() {
        return listaVariablesFinanciero;
    }

    public void setListaVariablesFinanciero(List<VariablesRating> listaVariablesFinanciero) {
        this.listaVariablesFinanciero = listaVariablesFinanciero;
    }

    public List<VariablesRating> getListaVariablesComportamiento() {
        return listaVariablesComportamiento;
    }

    public void setListaVariablesComportamiento(List<VariablesRating> listaVariablesComportamiento) {
        this.listaVariablesComportamiento = listaVariablesComportamiento;
    }

    public List<VariablesRating> getListaVariablesObjetivo() {
        return listaVariablesObjetivo;
    }

    public void setListaVariablesObjetivo(List<VariablesRating> listaVariablesObjetivo) {
        this.listaVariablesObjetivo = listaVariablesObjetivo;
    }

    public List<VariablesRating> getListaVariablesSubjetivo() {
        return listaVariablesSubjetivo;
    }

    public void setListaVariablesSubjetivo(List<VariablesRating> listaVariablesSubjetivo) {
        this.listaVariablesSubjetivo = listaVariablesSubjetivo;
    }
    
     public List<VariablesRating> getListaVariablesFront() {
        return listaVariablesFront;
    }

    public void setListaVariablesFront(List<VariablesRating> listaVariablesFront) {
        this.listaVariablesFront = listaVariablesFront;
    }
    
    public List<List<VariablesRating>> getListaVariablesModulo() {
        return listaVariablesModulo;
    }

    public void setListaVariablesModulo(List<List<VariablesRating>> listaVariablesModulo) {
        this.listaVariablesModulo = listaVariablesModulo;
    }
    
    public List<VariablesRating> getListaVariablesFrontComportamiento() {
        return listaVariablesFrontComportamiento;
    }

    public void setListaVariablesFrontComportamiento(List<VariablesRating> listaVariablesFrontComportamiento) {
        this.listaVariablesFrontComportamiento = listaVariablesFrontComportamiento;
    }

    public List<VariablesRating> getListaVariablesFrontObjetivo() {
        return listaVariablesFrontObjetivo;
    }

    public void setListaVariablesFrontObjetivo(List<VariablesRating> listaVariablesFrontObjetivo) {
        this.listaVariablesFrontObjetivo = listaVariablesFrontObjetivo;
    }
    
    public List<VariablesRating> getVariablesRating() {
        return variablesRating;
    }

    public void setVariablesRating(List<VariablesRating> variablesRating) {
        this.variablesRating = variablesRating;
    }
    
    public List<List<VariablesRating>> getListVariablesRating() {
        return listVariablesRating;
    }

    public void setListVariablesRating(List<List<VariablesRating>> listVariablesRating) {
        this.listVariablesRating = listVariablesRating;
    }
    
    public List<VariablesRating> getVariablesRatingComportamiento() {
        return variablesRatingComportamiento;
    }

    public void setVariablesRatingComportamiento(List<VariablesRating> variablesRatingComportamiento) {
        this.variablesRatingComportamiento = variablesRatingComportamiento;
    }
    
      public List<VariablesRating> getVariablesRatingResultado() {
        return variablesRatingResultado;
    }

    public void setVariablesRatingResultado(List<VariablesRating> variablesRatingResultado) {
        this.variablesRatingResultado = variablesRatingResultado;
    }
    
     public List<VariablesRating> getListaVariablesFrontResultado() {
        return listaVariablesFrontResultado;
    }

    public void setListaVariablesFrontResultado(List<VariablesRating> listaVariablesFrontResultado) {
        this.listaVariablesFrontResultado = listaVariablesFrontResultado;
    }
        
     public List<List<VariablesRating>> getListaRespuestasVariablesModulo() {
        return listaRespuestasVariablesModulo;
    }

    public void setListaRespuestasVariablesModulo(List<List<VariablesRating>> listaRespuestasVariablesModulo) {
        this.listaRespuestasVariablesModulo = listaRespuestasVariablesModulo;
    }
    
    public List<VariablesRating> getListaRespuestasVariablesFrontComportamiento() {
        return listaRespuestasVariablesFrontComportamiento;
    }

    public void setListaRespuestasVariablesFrontComportamiento(List<VariablesRating> listaRespuestasVariablesFrontComportamiento) {
        this.listaRespuestasVariablesFrontComportamiento = listaRespuestasVariablesFrontComportamiento;
    }
    
    public List<VariablesRating> getListaRespuestasVariablesComportamiento() {
        return listaRespuestasVariablesComportamiento;
    }

    public void setListaRespuestasVariablesComportamiento(List<VariablesRating> listaRespuestasVariablesComportamiento) {
        this.listaRespuestasVariablesComportamiento = listaRespuestasVariablesComportamiento;
    }
    
    public String getRespuestaCalificacion() {
        return respuestaCalificacion;
    }

    public void setRespuestaCalificacion(String respuestaCalificacion) {
        this.respuestaCalificacion = respuestaCalificacion;
    }

    public String getRespuestaGarantias() {
        return respuestaGarantias;
    }

    public void setRespuestaGarantias(String respuestaGarantias) {
        this.respuestaGarantias = respuestaGarantias;
    }

    public String getRespuestaIndMora() {
        return respuestaIndMora;
    }

    public void setRespuestaIndMora(String respuestaIndMora) {
        this.respuestaIndMora = respuestaIndMora;
    }

    public String getRespuestaNumeroBancos() {
        return respuestaNumeroBancos;
    }

    public void setRespuestaNumeroBancos(String respuestaNumeroBancos) {
        this.respuestaNumeroBancos = respuestaNumeroBancos;
    }

    public String getRespuestaMarcacionReestructuracion() {
        return respuestaMarcacionReestructuracion;
    }

    public void setRespuestaMarcacionReestructuracion(String respuestaMarcacionReestructuracion) {
        this.respuestaMarcacionReestructuracion = respuestaMarcacionReestructuracion;
    }
    
    public List<VariablesRating> getListaRespuestasVariablesObjetivable() {
        return listaRespuestasVariablesObjetivable;
    }

    public void setListaRespuestasVariablesObjetivable(List<VariablesRating> listaRespuestasVariablesObjetivable) {
        this.listaRespuestasVariablesObjetivable = listaRespuestasVariablesObjetivable;
    }
    
    public List<VariablesRating> getListaRespuestasVariablesObjetivableFront() {
        return listaRespuestasVariablesObjetivableFront;
    }

    public void setListaRespuestasVariablesObjetivableFront(List<VariablesRating> listaRespuestasVariablesObjetivableFront) {
        this.listaRespuestasVariablesObjetivableFront = listaRespuestasVariablesObjetivableFront;
    }
    
    public String getEvolucionEsperada() {
        return evolucionEsperada;
    }

    public void setEvolucionEsperada(String evolucionEsperada) {
        this.evolucionEsperada = evolucionEsperada;
    }

    public String getPosicionMercado() {
        return posicionMercado;
    }

    public void setPosicionMercado(String posicionMercado) {
        this.posicionMercado = posicionMercado;
    }

    public String getDependenciaClientes() {
        return dependenciaClientes;
    }

    public void setDependenciaClientes(String dependenciaClientes) {
        this.dependenciaClientes = dependenciaClientes;
    }

    public String getConcentracionProveedor() {
        return concentracionProveedor;
    }

    public void setConcentracionProveedor(String concentracionProveedor) {
        this.concentracionProveedor = concentracionProveedor;
    }

    public String getVoluntadCapacidadApoyo() {
        return voluntadCapacidadApoyo;
    }

    public void setVoluntadCapacidadApoyo(String voluntadCapacidadApoyo) {
        this.voluntadCapacidadApoyo = voluntadCapacidadApoyo;
    }

    
    public String getCalidadRevisorFiscal() {
        return calidadRevisorFiscal;
    }

    public void setCalidadRevisorFiscal(String calidadRevisorFiscal) {
        this.calidadRevisorFiscal = calidadRevisorFiscal;
    }

    public String getInformeRevisorFiscal() {
        return informeRevisorFiscal;
    }

    public void setInformeRevisorFiscal(String informeRevisorFiscal) {
        this.informeRevisorFiscal = informeRevisorFiscal;
    }
    
     public String getGarantiasAdicionales() {
        return garantiasAdicionales;
    }

    public void setGarantiasAdicionales(String garantiasAdicionales) {
        this.garantiasAdicionales = garantiasAdicionales;
    }
    
    public String getTipoProductoServicio() {
        return tipoProductoServicio;
    }

    public void setTipoProductoServicio(String tipoProductoServicio) {
        this.tipoProductoServicio = tipoProductoServicio;
    }

    public String getGerenciaCapacidadProfesionalidad() {
        return gerenciaCapacidadProfesionalidad;
    }

    public void setGerenciaCapacidadProfesionalidad(String gerenciaCapacidadProfesionalidad) {
        this.gerenciaCapacidadProfesionalidad = gerenciaCapacidadProfesionalidad;
    }

    public String getAbanicoBancario() {
        return abanicoBancario;
    }

    public void setAbanicoBancario(String abanicoBancario) {
        this.abanicoBancario = abanicoBancario;
    }

    public String getMecanismosFinanciacion() {
        return mecanismosFinanciacion;
    }

    public void setMecanismosFinanciacion(String mecanismosFinanciacion) {
        this.mecanismosFinanciacion = mecanismosFinanciacion;
    }

    public String getEstructuraCostos() {
        return estructuraCostos;
    }

    public void setEstructuraCostos(String estructuraCostos) {
        this.estructuraCostos = estructuraCostos;
    }

    public String getCapacidadAtenderCalendario() {
        return capacidadAtenderCalendario;
    }

    public void setCapacidadAtenderCalendario(String capacidadAtenderCalendario) {
        this.capacidadAtenderCalendario = capacidadAtenderCalendario;
    }

    public String getGradoAutoFinanciacion() {
        return gradoAutoFinanciacion;
    }

    public void setGradoAutoFinanciacion(String gradoAutoFinanciacion) {
        this.gradoAutoFinanciacion = gradoAutoFinanciacion;
    }

    public String getExistenciaDeudas() {
        return existenciaDeudas;
    }

    public void setExistenciaDeudas(String existenciaDeudas) {
        this.existenciaDeudas = existenciaDeudas;
    }

    public String getPerfilPago() {
        return perfilPago;
    }

    public void setPerfilPago(String perfilPago) {
        this.perfilPago = perfilPago;
    }

    public String getCalidadActivos() {
        return calidadActivos;
    }

    public void setCalidadActivos(String calidadActivos) {
        this.calidadActivos = calidadActivos;
    }

    public String getTipoAccionista() {
        return tipoAccionista;
    }

    public void setTipoAccionista(String tipoAccionista) {
        this.tipoAccionista = tipoAccionista;
    }
    
    public ConsultaClientesRatingFacade getEjbFacadeCliente() {
        return ejbFacadeCliente;
    }

    public void setEjbFacadeCliente(ConsultaClientesRatingFacade ejbFacadeCliente) {
        this.ejbFacadeCliente = ejbFacadeCliente;
    }
    
    public ConsultaVariablesRatingFacade getEjbFacadeVariables() {
        return ejbFacadeVariables;
    }

    public void setEjbFacadeVariables(ConsultaVariablesRatingFacade ejbFacadeVariables) {
        this.ejbFacadeVariables = ejbFacadeVariables;
    }
    
    public Map<String, String> getMapRespuestasEvolucion() {
        return mapRespuestasEvolucion;
    }

    public void setMapRespuestasEvolucion(Map<String, String> mapRespuestasEvolucion) {
        this.mapRespuestasEvolucion = mapRespuestasEvolucion;
    }

    public Map<String, String> getMapRespuestasPosicion() {
        return mapRespuestasPosicion;
    }

    public void setMapRespuestasPosicion(Map<String, String> mapRespuestasPosicion) {
        this.mapRespuestasPosicion = mapRespuestasPosicion;
    }

    public Map<String, String> getMapRespuestasDependencia() {
        return mapRespuestasDependencia;
    }

    public void setMapRespuestasDependencia(Map<String, String> mapRespuestasDependencia) {
        this.mapRespuestasDependencia = mapRespuestasDependencia;
    }

    public Map<String, String> getMapRespuestasConcentracion() {
        return mapRespuestasConcentracion;
    }

    public void setMapRespuestasConcentracion(Map<String, String> mapRespuestasConcentracion) {
        this.mapRespuestasConcentracion = mapRespuestasConcentracion;
    }

    public Map<String, String> getMapRespuestasVoluntad() {
        return mapRespuestasVoluntad;
    }

    public void setMapRespuestasVoluntad(Map<String, String> mapRespuestasVoluntad) {
        this.mapRespuestasVoluntad = mapRespuestasVoluntad;
    }

    public Map<String, String> getMapRespuestasGarantias() {
        return mapRespuestasGarantias;
    }

    public void setMapRespuestasGarantias(Map<String, String> mapRespuestasGarantias) {
        this.mapRespuestasGarantias = mapRespuestasGarantias;
    }

    public Map<String, String> getMapRespuestasCalidad() {
        return mapRespuestasCalidad;
    }

    public void setMapRespuestasCalidad(Map<String, String> mapRespuestasCalidad) {
        this.mapRespuestasCalidad = mapRespuestasCalidad;
    }

    public Map<String, String> getMapRespuestasInforme() {
        return mapRespuestasInforme;
    }

    public void setMapRespuestasInforme(Map<String, String> mapRespuestasInforme) {
        this.mapRespuestasInforme = mapRespuestasInforme;
    }
}
