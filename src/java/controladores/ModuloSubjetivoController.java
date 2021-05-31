/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.ClientesRating;
import entidades.GruposClientes;
import entidades.VariablesRating;
import fachadas.ConsultaSubjetivoFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name="moduloSubjetivo")
@ViewScoped
public class ModuloSubjetivoController extends AbstractController{

    @EJB
    private ConsultaSubjetivoFacade ejbFacade;
    private List<GruposClientes> listaGrupos;
    private List<ClientesRating> listaClientes;
    private List<ClientesRating> listaClientesSeleccionados = new ArrayList<>();
    private List<VariablesRating> listaRespuestasVariablesSubjetivo;
    private List<VariablesRating> listaVariablesSubjetivo;
    private List<VariablesRating> listaVariablesFrontSubjetivo;
    private List<List<VariablesRating>> listaVariablesModulo;
    private List<List<VariablesRating>> listaRespuestasVariablesModulo;
    private String tipoConsulta;
    private String nitDiligenciado;
    private String nombreDiligenciado;
    private List<VariablesRating> variablesRating;
    private List<List<VariablesRating>> listVariablesRating;
    private List<VariablesRating> variablesRatingResultado;
    private List<VariablesRating> listaRespuestasVariablesSubjetivoFront;
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

    @PostConstruct
    @Override
    public void init(){
        this.precargaListaVariables();
        this.precargaListaRespuestasVariables();
       
        listaVariablesFrontSubjetivo = this.precargaListaSubjetivo();
        listaRespuestasVariablesSubjetivoFront = this.precargaRespuestaListaSubjetivo();
    }

    public void precargaListaVariables(){
       this.setListaVariablesModulo(getEjbFacade().listarVariablesRating());  
    }
    
    public void precargaListaRespuestasVariables(){
       this.setListaRespuestasVariablesModulo(getEjbFacade().listarRespuestasVariablesRating());
    }
     
    public List<VariablesRating> precargaRespuestaListaSubjetivo(){
       this.setListaRespuestasVariablesSubjetivo(this.getListaRespuestasVariablesModulo().get(0));
       
       return listaRespuestasVariablesSubjetivo;
    }
    
    public List<VariablesRating> precargaListaSubjetivo(){
       this.setListaVariablesSubjetivo(this.getListaVariablesModulo().get(0));
       
       return listaVariablesSubjetivo;
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
        this.listaClientes = ejbFacade.
                consultaClienteGrupoResultadoSinResRating(tipoConsulta, 
                        parametroConsulta);
        
        if(listaClientes.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(":growl", 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                "No se ha encontrado información de clientes "
                                        + "con los criterios de búsqueda", ""));
        }
         else {
            this.setNitDiligenciado(this.listaClientes.get(0).getNit());
        }
    }
    
    public void registrarSubjetivo(ActionEvent event) throws Exception{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
            
        String usuario = seccion.getUsuario().getCodigo();
      
        Boolean result = ejbFacade.registrarSubjetivo(this.nitDiligenciado, 
                this.tipoProductoServicio, this.gerenciaCapacidadProfesionalidad, 
                this.abanicoBancario, this.mecanismosFinanciacion, this.estructuraCostos, 
                this.capacidadAtenderCalendario, this.gradoAutoFinanciacion,
                this.existenciaDeudas, this.perfilPago, this.calidadActivos, 
                this.tipoAccionista, usuario);
        
        if(result){
            addMessage("Registro Exitoso", "Se registro la información en el Módulo "
                    + "Subjetivo al usuaio: "
                    +this.nitDiligenciado);
        }
        else{
            addMessage("Registro Fallido", "Por favor intente mas tarde"
                    +this.nitDiligenciado);
        }
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());        
    }
    
    /**
     * @return the ejbFacade
     */
    public ConsultaSubjetivoFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ConsultaSubjetivoFacade ejbFacade) {
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

    public List<VariablesRating> getListaVariablesSubjetivo() {
        return listaVariablesSubjetivo;
    }

    public void setListaVariablesSubjetivo(List<VariablesRating> listaVariablesSubjetivo) {
        this.listaVariablesSubjetivo = listaVariablesSubjetivo;
    }
    
    public List<List<VariablesRating>> getListaVariablesModulo() {
        return listaVariablesModulo;
    }

    public void setListaVariablesModulo(List<List<VariablesRating>> listaVariablesModulo) {
        this.listaVariablesModulo = listaVariablesModulo;
    }
    
    public List<VariablesRating> getListaVariablesFrontSubjetivo() {
        return listaVariablesFrontSubjetivo;
    }

    public void setListaVariablesFrontSubjetivo(List<VariablesRating> listaVariablesFrontSubjetivo) {
        this.listaVariablesFrontSubjetivo = listaVariablesFrontSubjetivo;
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
    
    public List<VariablesRating> getVariablesRatingResultado() {
        return variablesRatingResultado;
    }

    public void setVariablesRatingResultado(List<VariablesRating> variablesRatingResultado) {
        this.variablesRatingResultado = variablesRatingResultado;
    }
    
     public List<List<VariablesRating>> getListaRespuestasVariablesModulo() {
        return listaRespuestasVariablesModulo;
    }

    public void setListaRespuestasVariablesModulo(List<List<VariablesRating>> listaRespuestasVariablesModulo) {
        this.listaRespuestasVariablesModulo = listaRespuestasVariablesModulo;
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
    
     public List<VariablesRating> getListaRespuestasVariablesSubjetivoFront() {
        return listaRespuestasVariablesSubjetivoFront;
    }

    public void setListaRespuestasVariablesSubjetivoFront(List<VariablesRating> listaRespuestasVariablesSubjetivoFront) {
        this.listaRespuestasVariablesSubjetivoFront = listaRespuestasVariablesSubjetivoFront;
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
    
     public List<VariablesRating> getListaRespuestasVariablesSubjetivo() {
        return listaRespuestasVariablesSubjetivo;
    }

    public void setListaRespuestasVariablesSubjetivo(List<VariablesRating> listaRespuestasVariablesSubjetivo) {
        this.listaRespuestasVariablesSubjetivo = listaRespuestasVariablesSubjetivo;
    }
}
