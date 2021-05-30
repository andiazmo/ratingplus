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
import fachadas.ConsultaObjetivableFacade;
import fachadas.ConsultaComportamientoFacade;
import fachadas.ConsultaSubjetivoFacade;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
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
    private List<ClientesRating> listaClientesSeleccionados = new ArrayList<ClientesRating>();
    private List<RatingInfo> informacionRating;
    private List<VariablesRating> listaVariables;
    private List<VariablesRating> listaVariablesFinanciero;
    private List<VariablesRating> listaVariablesComportamiento;
    private List<VariablesRating> listaRespuestasVariablesComportamiento;
    private List<VariablesRating> listaRespuestasVariablesObjetivable;
    private List<VariablesRating> listaRespuestasVariablesSubjetivo;
    private List<VariablesRating> listaVariablesObjetivo;
    private List<VariablesRating> listaVariablesSubjetivo;
    private List<VariablesRating> listaVariablesFront;
    private List<VariablesRating> listaVariablesFrontComportamiento;
    private List<VariablesRating> listaRespuestasVariablesFrontComportamiento;
    private List<VariablesRating> listaVariablesFrontObjetivo;
    private List<VariablesRating> listaVariablesFrontSubjetivo;
    private List<VariablesRating> listaVariablesFrontResultado;
    private List<List<VariablesRating>> listaVariablesModulo;
    private List<List<VariablesRating>> listaRespuestasVariablesModulo;
    private String tipoConsulta;
    private String nitDiligenciado;
    private String nombreDiligenciado;
    private Integer grupoSeleccionado;
    private BigDecimal valorRatingModificado;
    private String comentariosUsuario;
    private RatingInfo objetoRatingInfo;
    private BigDecimal valorRatingAnterior;
    private List<String> listaPeriodos;
    private List<Modulo> listaModulos;
    private String periodoSeleccionado;
    private String fileName = "ResultadosRating_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-" + new SimpleDateFormat("HHmmss").format(new Date()) + ".xls";
    private List<VariablesRating> variablesRating;
    private List<List<VariablesRating>> listVariablesRating;
    private List<VariablesRating> variablesRatingComportamiento;
    private List<VariablesRating> variablesRatingResultado;
    private List<VariablesRating> ratingUpdate;
    private Map<String, String> respComp = new HashMap<>();
    private Map<String, String> respFrontComp = new HashMap<>();
    private List<VariablesRating> listaRespuestasVariablesComportamientoFront;
    private List<VariablesRating> listaRespuestasVariablesObjetivableFront;
    private List<VariablesRating> listaRespuestasVariablesSubjetivoFront;
    private List<VariablesRating> listaRespuestasVariablesComportamientoBack;
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
    public void init(){
        this.precargaInformacion();
        this.precargaListaVariables();
        this.precargaListaRespuestasVariables();
       
        listaVariablesFrontSubjetivo = this.precargaListaSubjetivo();
        listaRespuestasVariablesSubjetivoFront = this.precargaRespuestaListaSubjetivo();
        
    }

    public void precargaInformacion(){
        this.setListaGrupos(getEjbFacade().listarGruposCliente()); 
    }
    
    public void precargaListaVariables(){
        
       this.setListaVariablesModulo(getEjbFacade().listarVariablesRating());
       
    }
    
    public void precargaListaRespuestasVariables(){
        
       this.setListaRespuestasVariablesModulo(getEjbFacade().listarRespuestasVariablesRating());
       System.out.println("Listas en la lista de respuestas:::"+this.getListaRespuestasVariablesModulo().size());
       
    }
     
    public List<VariablesRating> precargaRespuestaListaComportamiento(){
       
       this.setListaRespuestasVariablesComportamiento(this.getListaRespuestasVariablesModulo().get(0));
       
        for (int i = 0; i < this.getListaRespuestasVariablesComportamiento().size(); i++) {
            System.out.println("idModulo:::"+this.getListaRespuestasVariablesComportamiento().get(i).getIdModulo());
            System.out.println("Respuesta:::"+this.getListaRespuestasVariablesComportamiento().get(i).getRespuesta());
            System.out.println("Nombre variable:::"+this.getListaRespuestasVariablesComportamiento().get(i).getNombre());
           // respComp
        }
       
        return listaRespuestasVariablesComportamiento;
    }
    
    public List<VariablesRating> precargaRespuestaListaSubjetivo(){
       
       this.setListaRespuestasVariablesSubjetivo(this.getListaRespuestasVariablesModulo().get(0));
       
        for (int i = 0; i < this.getListaRespuestasVariablesSubjetivo().size(); i++) {
            System.out.println("idModulo:::"+this.getListaRespuestasVariablesSubjetivo().get(i).getIdModulo());
            System.out.println("Respuesta:::"+this.getListaRespuestasVariablesSubjetivo().get(i).getRespuesta());
            System.out.println("Nombre variable:::"+this.getListaRespuestasVariablesSubjetivo().get(i).getNombre());
           // respComp
        }
       
        return listaRespuestasVariablesSubjetivo;
    }
    
   
   
    
    
    public List<VariablesRating> precargaListaFinanciera(){
       
       this.setListaVariablesFinanciero(this.getListaVariablesModulo().get(0));
       
       return listaVariablesFinanciero;
    }
    
    public List<VariablesRating> precargaListaComportamiento(){
        
       this.setListaVariablesComportamiento(this.getListaVariablesModulo().get(1));
       
       return listaVariablesComportamiento;
    
    }
    
    public List<VariablesRating> precargaListaObjetivo(){
        
       this.setListaVariablesObjetivo(this.getListaVariablesModulo().get(2));
       
       return listaVariablesObjetivo;
    
    }
     
    public List<VariablesRating> precargaListaSubjetivo(){
        
       this.setListaVariablesSubjetivo(this.getListaVariablesModulo().get(3));
       
       return listaVariablesSubjetivo;
    
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
     * @return the valorRatingModificado
     */
    public BigDecimal getValorRatingModificado() {
        return valorRatingModificado;
    }

    /**
     * @param valorRatingModificado the valorRatingModificado to set
     */
    public void setValorRatingModificado(BigDecimal valorRatingModificado) {
        this.valorRatingModificado = valorRatingModificado;
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

    /**
     * @return the valorRatingAnterior
     */
    public BigDecimal getValorRatingAnterior() {
        return valorRatingAnterior;
    }

    /**
     * @param valorRatingAnterior the valorRatingAnterior to set
     */
    public void setValorRatingAnterior(BigDecimal valorRatingAnterior) {
        this.valorRatingAnterior = valorRatingAnterior;
    }

    /**
     * @return the listaPeriodos
     */
    public List<String> getListaPeriodos() {
        return listaPeriodos;
    }

    /**
     * @param listaPeriodos the listaPeriodos to set
     */
    public void setListaPeriodos(List<String> listaPeriodos) {
        this.listaPeriodos = listaPeriodos;
    }

    /**
     * @return the periodoSeleccionado
     */
    public String getPeriodoSeleccionado() {
        return periodoSeleccionado;
    }
    
    

    /**
     * @param periodoSeleccionado the periodoSeleccionado to set
     */
    public void setPeriodoSeleccionado(String periodoSeleccionado) {
        this.periodoSeleccionado = periodoSeleccionado;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
    
        this.listaPeriodos = new ArrayList<String>();
        
    }

    public void limpiarResultados(ActionEvent event){
        
        this.listaClientes = new ArrayList<ClientesRating>();
        this.tipoConsulta = null;
        this.nitDiligenciado = "";
        this.nombreDiligenciado = "";
        this.listaPeriodos = new ArrayList<String>();
        
    }
    
    public void consultaClientes(ActionEvent event){
        
        String parametroConsulta = tipoConsulta.equals("0") ? this.nitDiligenciado : this.nombreDiligenciado;
        System.out.println("tipoConsulta:::"+tipoConsulta);
        System.out.println("this.nitDiligenciado:::"+this.nitDiligenciado);
        System.out.println("periodoSeleccionado:::"+periodoSeleccionado);
        System.out.println("parametroConsulta:::"+parametroConsulta);
        this.listaClientes = ejbFacade.consultaClienteGrupoResultadoSinResRating(tipoConsulta, parametroConsulta);
        
        
        if(listaClientes.size() == 0){
            this.listaClientes = ejbFacade.consultaClienteGrupo(tipoConsulta, parametroConsulta);
            if(listaClientes.size() == 0){
                FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha encontrado información de clientes con los criterios de búsqueda", ""));
            }
        }
        
         else {
            this.setNitDiligenciado(this.listaClientes.get(0).getNit());
        }
        
    }
    
  
    public void registrarSubjetivo(ActionEvent event) throws Exception{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
        Boolean result = false;
            
        String usuario = seccion.getUsuario().getCodigo();
            
        System.out.println("usuario:::"+usuario);
      
        result = ejbFacade.registrarSubjetivo(this.nitDiligenciado, this.tipoProductoServicio, 
                this.gerenciaCapacidadProfesionalidad, this.abanicoBancario, this.mecanismosFinanciacion, 
                this.estructuraCostos, this.capacidadAtenderCalendario, this.gradoAutoFinanciacion,
                this.existenciaDeudas, this.perfilPago, this.calidadActivos, this.tipoAccionista,
                usuario);
        
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
    
     public List<VariablesRating> getRatingUpdate() {
        return ratingUpdate;
    }

    public void setRatingUpdate(List<VariablesRating> ratingUpdate) {
        this.ratingUpdate = ratingUpdate;
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
