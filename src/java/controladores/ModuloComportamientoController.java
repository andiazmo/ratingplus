/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.ClientesRating;
import entidades.GruposClientes;
import entidades.RatingInfo;
import entidades.VariablesRating;
import fachadas.ConsultaClientesRatingFacade;
import fachadas.ConsultaComportamientoFacade;
import fachadas.ConsultaVariablesRatingFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
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
@ManagedBean(name="moduloComportamiento")
@ViewScoped
public class ModuloComportamientoController extends AbstractController{

    @EJB
    private ConsultaComportamientoFacade ejbFacade;
    
    @EJB
    private ConsultaClientesRatingFacade ejbFacadeCliente;
    
    @EJB
    private ConsultaVariablesRatingFacade ejbFacadeVariables;

    private List<GruposClientes> listaGrupos;
    private List<ClientesRating> listaClientes;
    private List<ClientesRating> listaClientesSeleccionados = new ArrayList<>();
    private List<RatingInfo> informacionRating;
    private List<VariablesRating> listaVariables;
    private List<VariablesRating> listaVariablesComportamiento;
    private List<VariablesRating> listaRespuestasVariablesComportamiento;
    private List<VariablesRating> listaVariablesFront;
    private List<VariablesRating> listaVariablesFrontComportamiento;
    private List<VariablesRating> listaRespuestasVariablesFrontComportamiento;
    private List<VariablesRating> listaVariablesFrontResultado;
    private List<List<VariablesRating>> listaVariablesModulo;
    private List<List<VariablesRating>> listaRespuestasVariablesModulo;
    private String tipoConsulta;
    private String nitDiligenciado;
    private String nombreDiligenciado;
    private Integer grupoSeleccionado;
    private List<VariablesRating> variablesRating;
    private List<List<VariablesRating>> listVariablesRating;
    private List<VariablesRating> variablesRatingComportamiento;
    private List<VariablesRating> variablesRatingResultado;
    private Map<String, String> respComp = new HashMap<>();
    private Map<String, String> respFrontComp = new HashMap<>();
    private List<VariablesRating> listaRespuestasVariablesComportamientoFront;
    private List<VariablesRating> listaRespuestasVariablesComportamientoBack;
    private String respuestaCalificacion;
    private String respuestaGarantias;
    private String respuestaIndMora;
    private String respuestaNumeroBancos;
    private String respuestaMarcacionReestructuracion;
    private String respuestasCalificacion;
    private Map<String, String> mapRespuestasCalificacion = new HashMap<>();
    private Map<String, String> mapRespuestasGarantias = new HashMap<>();
    private Map<String, String> mapRespuestasMora = new HashMap<>();
    private Map<String, String> mapRespuestasNumeroBancos = new HashMap<>();
    private Map<String, String> mapRespuestasMarcacion = new HashMap<>();

    @PostConstruct
    @Override
    public void init(){
        this.precargaInformacion();
        this.precargaListaVariables();
        this.precargaListaRespuestasVariables();
       
        listaVariablesFrontComportamiento = this.precargaListaComportamiento();
        listaRespuestasVariablesComportamientoFront = 
                this.precargaRespuestaListaComportamiento();
    }

    public void precargaInformacion(){
        this.setListaGrupos(getEjbFacadeCliente().listarGruposCliente()); 
    }
    
    public void precargaListaVariables(){
        this.setListaVariablesModulo(getEjbFacadeVariables().listarVariablesRating());
    }
    
    public void precargaListaRespuestasVariables(){
        this.setListaRespuestasVariablesModulo
        (getEjbFacadeVariables().listarRespuestasVariablesRating());
    }
     
    public List<VariablesRating> precargaRespuestaListaComportamiento(){
       this.setListaRespuestasVariablesComportamiento
        (this.getListaRespuestasVariablesModulo().get(1));
      
       return this.respuestasPorVariable();
    }
    
    public List<VariablesRating> respuestasPorVariable(){
        
        mapRespuestasCalificacion.
                        put(listaRespuestasVariablesComportamiento.get(0).
                                getRespuesta(),
                                listaRespuestasVariablesComportamiento.
                                        get(0).getRespuesta());
        
        for (int i = 1; i < listaRespuestasVariablesComportamiento.size(); i++) {
            if(listaRespuestasVariablesComportamiento.get(i).getNombre().
                    contains("Calificaciones")){
                mapRespuestasCalificacion.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                
                if(listaRespuestasVariablesComportamiento.get(i).getNombre().
                    equals(listaRespuestasVariablesComportamiento.get(i-1).getNombre())) {
                
                mapRespuestasCalificacion.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                }
            }
            if(listaRespuestasVariablesComportamiento.get(i).getNombre().
                    contains("Garantias")){
                mapRespuestasGarantias.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                
                if(listaRespuestasVariablesComportamiento.get(i).getNombre().
                    equals(listaRespuestasVariablesComportamiento.get(i-1).getNombre())) {
                mapRespuestasGarantias.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                }
            }
            
            if(listaRespuestasVariablesComportamiento.get(i).getNombre().contains("Indicador")){
                mapRespuestasMora.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                
                if(listaRespuestasVariablesComportamiento.get(i).getNombre().
                    equals(listaRespuestasVariablesComportamiento.get(i-1).getNombre())) {
                mapRespuestasMora.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                }
            }
            
            if(listaRespuestasVariablesComportamiento.get(i).getNombre().contains("bancos")){
                mapRespuestasNumeroBancos.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                
                if(listaRespuestasVariablesComportamiento.get(i).getNombre().
                    equals(listaRespuestasVariablesComportamiento.get(i-1).getNombre())) {
                mapRespuestasNumeroBancos.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                }
            }
            
            if(listaRespuestasVariablesComportamiento.get(i).getNombre().contains("Marcacion")){
                mapRespuestasMarcacion.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                
                if(listaRespuestasVariablesComportamiento.get(i).getNombre().
                    equals(listaRespuestasVariablesComportamiento.get(i-1).getNombre())) {
                mapRespuestasMarcacion.
                        put(listaRespuestasVariablesComportamiento.get(i).getRespuesta(),
                                listaRespuestasVariablesComportamiento.get(i).getRespuesta());
                }
            }
        }
       
       return listaRespuestasVariablesComportamiento;
    }
    
    
    public List<VariablesRating> precargaListaComportamiento(){
       this.setListaVariablesComportamiento(this.getListaVariablesModulo().get(1));
       
       return listaVariablesComportamiento;
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

    public void consultaClientes(ActionEvent event){
        String parametroConsulta = tipoConsulta.equals("0") ? 
                this.nitDiligenciado : this.nombreDiligenciado;
        this.listaClientes = ejbFacadeCliente.
                consultaClienteGrupoResultadoSinResRating(tipoConsulta, 
                        parametroConsulta);
        
        if(listaClientes.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(":growl", 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, 
                            "No se ha encontrado informaci??n de clientes con "
                                    + "los criterios de b??squeda", ""));
        }
        else {
            this.setNitDiligenciado(this.listaClientes.get(0).getNit());
        }
    }
    
    public void registrarComportamiento(ActionEvent event) throws Exception{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().
                getSession(true);
        UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
        
        String usuario = seccion.getUsuario().getCodigo();
            
        Boolean result = ejbFacade.registrarComportamiento(
                this.respuestaCalificacion, 
                this.respuestaGarantias, this.respuestaIndMora, 
                this.respuestaNumeroBancos, 
                this.respuestaMarcacionReestructuracion, 
                this.nitDiligenciado, usuario);
        
        if(result) {
            addMessage("Registro Exitoso", 
                    "Se registro la informaci??n en el M??dulo Objetivable al "
                            + "usuaio: "
                            +this.nitDiligenciado);
        }
        else {
            addMessage("Registro Fallido", "Por favor intente de nuevo: "
                    +this.nitDiligenciado);
        }
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void limpiarResultados(ActionEvent event) throws IOException{
       FacesContext context = FacesContext.getCurrentInstance();
       this.tipoConsulta = null;
       this.nitDiligenciado = "";
       this.nombreDiligenciado = "";
       this.listaClientes = new ArrayList<>();
       String refreshpage = context.getViewRoot().getViewId();
       ViewHandler handler = context.getApplication().getViewHandler();
       UIViewRoot root = handler.createView(context, refreshpage);
       root.setViewId(refreshpage);
       context.setViewRoot(root);
    }
    
    /**
     * @return the ejbFacade
     */
    public ConsultaComportamientoFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ConsultaComportamientoFacade ejbFacade) {
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

    public List<VariablesRating> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(List<VariablesRating> listaVariables) {
        this.listaVariables = listaVariables;
    }
    
    public List<VariablesRating> getListaVariablesComportamiento() {
        return listaVariablesComportamiento;
    }

    public void setListaVariablesComportamiento(List<VariablesRating> listaVariablesComportamiento) {
        this.listaVariablesComportamiento = listaVariablesComportamiento;
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
    
    public Map<String, String> getRespComp() {
        return respComp;
    }

    public void setRespComp(Map<String, String> respComp) {
        this.respComp = respComp;
    }
    
    public Map<String, String> getRespFrontComp() {
        return respFrontComp;
    }

    public void setRespFrontComp(Map<String, String> respFrontComp) {
        this.respFrontComp = respFrontComp;
    }
    
    public List<VariablesRating> getListaRespuestasVariablesComportamientoFront() {
        return listaRespuestasVariablesComportamientoFront;
    }

    public void setListaRespuestasVariablesComportamientoFront(List<VariablesRating> listaRespuestasVariablesComportamientoFront) {
        this.listaRespuestasVariablesComportamientoFront = listaRespuestasVariablesComportamientoFront;
    }
    
    public List<VariablesRating> getListaRespuestasVariablesComportamientoBack() {
        return listaRespuestasVariablesComportamientoBack;
    }

    public void setListaRespuestasVariablesComportamientoBack(List<VariablesRating> listaRespuestasVariablesComportamientoBack) {
        this.listaRespuestasVariablesComportamientoBack = listaRespuestasVariablesComportamientoBack;
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
    
    public String getRespuestasCalificacion() {
        return respuestasCalificacion;
    }

    public void setRespuestasCalificacion(String respuestasCalificacion) {
        this.respuestasCalificacion = respuestasCalificacion;
    }

    public Map<String, String> getMapRespuestasCalificion() {
        return mapRespuestasCalificacion;
    }

    public void setMapRespuestasCalificion(Map<String, String> mapRespuestasCalificion) {
        this.mapRespuestasCalificacion = mapRespuestasCalificion;
    }
    
    public Map<String, String> getMapRespuestasGarantias() {
        return mapRespuestasGarantias;
    }

    public void setMapRespuestasGarantias(Map<String, String> mapRespuestasGarantias) {
        this.mapRespuestasGarantias = mapRespuestasGarantias;
    }

    public Map<String, String> getMapRespuestasNumeroBancos() {
        return mapRespuestasNumeroBancos;
    }

    public void setMapRespuestasNumeroBancos(Map<String, String> mapRespuestasNumeroBancos) {
        this.mapRespuestasNumeroBancos = mapRespuestasNumeroBancos;
    }

    public Map<String, String> getMapRespuestasMarcacion() {
        return mapRespuestasMarcacion;
    }

    public void setMapRespuestasMarcacion(Map<String, String> mapRespuestasMarcacion) {
        this.mapRespuestasMarcacion = mapRespuestasMarcacion;
    }
    
    public Map<String, String> getMapRespuestasMora() {
        return mapRespuestasMora;
    }

    public void setMapRespuestasMora(Map<String, String> mapRespuestasMora) {
        this.mapRespuestasMora = mapRespuestasMora;
    }
}
