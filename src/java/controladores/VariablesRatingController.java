/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.ClientesRating;
import entidades.GruposClientes;
import entidades.RatingInfo;
import fachadas.VariablesRatingFacade;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
@ManagedBean(name="variablesRating")
@ViewScoped
public class VariablesRatingController extends AbstractController{

    @EJB
    private VariablesRatingFacade ejbFacade;
    
    private List<GruposClientes> listaGrupos;
    private List<ClientesRating> listaClientes;
    private List<ClientesRating> listaClientesSeleccionados = new ArrayList<ClientesRating>();
    private List<RatingInfo> informacionRating;
    private String tipoConsulta;
    private String nitDiligenciado;
    private String nombreDiligenciado;
    private Integer grupoSeleccionado;
    private BigDecimal valorRatingModificado;
    private String comentariosUsuario;
    private RatingInfo objetoRatingInfo;
    private BigDecimal valorRatingAnterior;
    private List<String> listaPeriodos;
    private String periodoSeleccionado;
    private String fileName = "ResultadosRating_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-" + new SimpleDateFormat("HHmmss").format(new Date()) + ".xls";
    
    @PostConstruct
    public void init(){
        this.precargaInformacion();
    }

    public void precargaInformacion(){
        this.setListaGrupos(getEjbFacade().listarGruposCliente());
    }
    
    public void inicializarVariables(){

    }

    /**
     * @return the ejbFacade
     */
    public VariablesRatingFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(VariablesRatingFacade ejbFacade) {
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
        this.listaClientes = ejbFacade.consultaClientes(tipoConsulta, parametroConsulta, periodoSeleccionado);
        
        if(listaClientes.size() == 0){
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha encontrado información de clientes con los criterios de búsqueda", ""));
        }
        
    }

    public void calcularRating(ActionEvent event) throws Exception{
        
        if(listaClientesSeleccionados.size() == 0){
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar por lo menos un cliente para calcular el Rating", ""));
        } else {
            String [] listaNits = new String[listaClientesSeleccionados.size()];
            
            for(int i = 0; i < listaNits.length; i++){
                listaNits[i] = listaClientesSeleccionados.get(i).getNit();
            }
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");                         
            
            String usuario = seccion.getUsuario().getCodigo();
            String periodo = this.getPeriodoSeleccionado();
            
            this.informacionRating = ejbFacade.calcularRating(listaNits, periodo, usuario);
            System.out.println("tamano arreglo::: "+this.informacionRating.size());
            System.out.println("informacionRating:::"+this.informacionRating.get(0));
            System.out.println("Fecha Seleccionada:::"+this.getPeriodoSeleccionado());
           
                        
            RequestContext.getCurrentInstance().execute("PF('resultadosRating').show();");
            
        }
        
    }
    
    
    public void obtenerVariables(ActionEvent event) throws Exception{
        String version = FacesContext.class.getPackage().getImplementationVersion();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
        
        
        String usuario = seccion.getUsuario().getCodigo();
        
        System.out.println("Version:::"+version);
        
        FacesContext contex = FacesContext.getCurrentInstance();
        System.out.println("******************************************");
        System.out.println("******************************************");
        System.out.println("******************************************");
        System.out.println("******************************************");
        contex.getExternalContext().redirect("/cupos/cupos/ratingPlus/variablesRating.xhtml?v=qfdtjg8d-9271-46b7-b383-cb24340b7f13");
        
    }
    
    public void onRowEdit(RowEditEvent event){
                
        setValorRatingAnterior(informacionRating.get(0).getValorRatingFinal());
        RequestContext.getCurrentInstance().execute("PF('detalleCambio').show();");
                
    }

    public void confirmarCambioRating(ActionEvent event){
            
        informacionRating.get(0).setValorRating(getValorRatingAnterior());
        informacionRating.get(0).setValorRatingFinal(getValorRatingModificado());
        informacionRating.get(0).setComentariosUsuario(getComentariosUsuario());
        setComentariosUsuario("");
        RequestContext.getCurrentInstance().execute("PF('detalleCambio').hide();");
        
    }
    
    public void obtenerPeriodos(ActionEvent event){
    
        listaPeriodos = ejbFacade.obtenerPeriodos(tipoConsulta, tipoConsulta.equals("0") ? nitDiligenciado : nombreDiligenciado);
        
        if(listaPeriodos.size() == 0){
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "No se han encontrado períodos fiscales con los criterios de búsqueda", ""));
        }
        
    }
    
    public void definirRating(ActionEvent event){
    
        if(ejbFacade.confirmarRating(informacionRating.get(0).getValorRating(), informacionRating.get(0).getValorRatingFinal(), new BigDecimal(informacionRating.get(0).getId()))){
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado satisfactoriamente.", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error al actualizar la información del rating, por favor intente más tarde.", ""));
        }
    
    }

    public void redimensionarArchivo(Object document) {
        
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.RED.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        sheet.autoSizeColumn(16);
        
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            header.getCell(i).setCellStyle(cellStyle);
        }
        
    }
    
}
