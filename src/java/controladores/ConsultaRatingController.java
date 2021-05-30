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
import fachadas.ConsultaRatingFacade;
import static java.lang.Boolean.TRUE;
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
@ManagedBean(name="consultaRating")
@ViewScoped
public class ConsultaRatingController extends AbstractController{

    @EJB
    private ConsultaRatingFacade ejbFacade;
    
    private List<GruposClientes> listaGrupos;
    private List<ClientesRating> listaClientes;
    private List<ClientesRating> listaClientesSeleccionados = new ArrayList<ClientesRating>();
    private List<RatingInfo> informacionRating;
    private List<VariablesRating> listaVariables;
    private List<VariablesRating> listaVariablesFinanciero;
    private List<VariablesRating> listaVariablesComportamiento;
    private List<VariablesRating> listaVariablesObjetivo;
    private List<VariablesRating> listaVariablesSubjetivo;
    private List<VariablesRating> listaVariablesFront;
    private List<VariablesRating> listaVariablesFrontComportamiento;
    private List<VariablesRating> listaVariablesFrontObjetivo;
    private List<VariablesRating> listaVariablesFrontSubjetivo;
    private List<VariablesRating> listaVariablesFrontResultado;
    private List<List<VariablesRating>> listaVariablesModulo;
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
    private String periodo;

    @PostConstruct
    public void init(){
        this.precargaInformacion();
        this.precargaListaVariables();
        this.periodo = null;
       
       listaVariablesFront = this.precargaListaFinanciera();
       listaVariablesFrontComportamiento = this.precargaListaComportamiento();
       listaVariablesFrontObjetivo = this.precargaListaObjetivo();
       listaVariablesFrontSubjetivo = this.precargaListaSubjetivo();
       
    }

    public void precargaInformacion(){
        this.setListaGrupos(getEjbFacade().listarGruposCliente());
       // this.setListaVariables(getEjbFacade().listarVariablesRating());
        
    }
    
    public void precargaListaVariables(){
        
       this.setListaVariablesModulo(getEjbFacade().listarVariablesRating());
       
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
    public ConsultaRatingFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ConsultaRatingFacade ejbFacade) {
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
        //this.listaClientes = ejbFacade.consultaClientes(tipoConsulta, parametroConsulta, periodoSeleccionado);
        this.listaClientes = ejbFacade.consultaClienteGrupoResultadoRating(tipoConsulta, parametroConsulta, periodoSeleccionado);
        
        
        if(listaClientes.size() == 0){
            this.listaClientes = ejbFacade.consultaClienteGrupo(tipoConsulta, parametroConsulta);
            if(listaClientes.size() == 0){
                FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha encontrado información de clientes con los criterios de búsqueda", ""));
            }
        }
        
    }

    public void calcularRating(ActionEvent event) throws Exception{
        
        if(listaClientesSeleccionados.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(":growl", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, 
                            "Debe seleccionar por lo menos un cliente para "
                                    + "calcular el Rating", ""));
        } else {
            String [] listaNits = new String[listaClientesSeleccionados.size()];
            
            for(int i = 0; i < listaNits.length; i++){
                listaNits[i] = listaClientesSeleccionados.get(i).getNit();
            }
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");                         
            
            String usuario = seccion.getUsuario().getCodigo();
            //String periodo = this.getPeriodoSeleccionado();
            this.setPeriodo(this.getPeriodoSeleccionado());
            
            System.out.println("usuario:::"+usuario);
            System.out.println("periodo:::"+this.getPeriodo().length());
            System.out.println("usuario:::"+listaNits[0]);
            
            if (this.getPeriodo().length() == 0) {
                System.out.println("Va a calcular el rating sin periodo");
               // this.listVariablesRating = ejbFacade.calcular_rating_sinfin_fn(listaNits, usuario);
                this.listVariablesRating = ejbFacade.calcularRating(listaNits, null, usuario);
                System.out.println("Tamano lista varaibles rating controlador sin resfin:::"+this.listVariablesRating.size());
                this.setListaVariables(this.listVariablesRating.get(0));
                this.setVariablesRatingComportamiento(this.listVariablesRating.get(1));
                this.setListaVariablesFrontObjetivo(this.listVariablesRating.get(2));
                this.setListaVariablesFrontSubjetivo(this.listVariablesRating.get(3));
                this.setListaVariablesFrontResultado(this.listVariablesRating.get(4));
            }
            
            else {
                this.listVariablesRating = ejbFacade.calcularRating(listaNits, this.getPeriodo(), usuario);
                System.out.println("Tamano lista varaibles rating controlador:::"+this.listVariablesRating.size());
                this.setListaVariables(this.listVariablesRating.get(0));
                this.setVariablesRatingComportamiento(this.listVariablesRating.get(1));
                this.setListaVariablesFrontObjetivo(this.listVariablesRating.get(2));
                this.setListaVariablesFrontSubjetivo(this.listVariablesRating.get(3));
                this.setListaVariablesFrontResultado(this.listVariablesRating.get(4));
            
                this.getListaVariablesFrontResultado().get(0).getValue();
                listaClientesSeleccionados.get(0).setValorRating(this.getListaVariablesFrontResultado().get(0).getValue());
                System.out.println("lista financiera:::"+this.getListaVariables().size());
                System.out.println("lista comportamiento:::"+this.getVariablesRatingComportamiento().size());
                System.out.println("Fecha Seleccionada:::"+this.getPeriodoSeleccionado());
            }


            
            //RequestContext.getCurrentInstance().execute("PF('resultadosRating').show();");
            
        }
        
    }
    
    public void editDataValue(ActionEvent event){
        
        
        this.listaClientesSeleccionados.get(0).getNombre();
        RequestContext.getCurrentInstance().execute("PF('resultadosRating').show();");
    }
    
    public void addDataValue(){
        this.getListaVariablesFinanciero();
        this.getListaVariablesFront();
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
                
        System.out.println("Metodo onRowEdit");
        System.out.println("rating cambiado:::"+this.getListaVariablesFrontResultado().get(0).getValue());
       // this.listaClientesSeleccionados.get(0).setValorRating(fileName);
        this.getListaVariablesFrontResultado().get(0).setValue(this.getListaVariablesFrontResultado().get(0).getValue());
       // setValorRatingAnterior(informacionRating.get(0).getValorRatingFinal());
        RequestContext.getCurrentInstance().execute("PF('detalleCambio').show();");
                
    }
    
    public void onCancel(RowEditEvent event){
                
        System.out.println("Metodo onRowEdit");
       // this.listaClientesSeleccionados.get(0).setValorRating(fileName);
        setValorRatingAnterior(informacionRating.get(0).getValorRatingFinal());
        RequestContext.getCurrentInstance().execute("PF('detalleCambio').show();");
                
    }

    public void confirmarCambioRating(ActionEvent event){
            
        System.out.println("Comentario:::"+getComentariosUsuario());
        RequestContext.getCurrentInstance().execute("PF('detalleCambio').hide();");
        
    }
    
    public void obtenerPeriodos(ActionEvent event){
    
        listaPeriodos = ejbFacade.obtenerPeriodos(tipoConsulta, tipoConsulta.equals("0") ? nitDiligenciado : nombreDiligenciado);
        System.out.println("tipoConsulta:::"+tipoConsulta);
        
        if(listaPeriodos.isEmpty()){
            
            //this.listaClientes = ejbFacade.consultaClienteGrupoResultadoRating(tipoConsulta, parametroConsulta, periodoSeleccionado);
            this.listaClientes = ejbFacade.consultaClienteGrupoResultadoSinResRating(tipoConsulta, tipoConsulta.equals("0") ? nitDiligenciado : nombreDiligenciado);
//            System.out.println("listaClientes:::"+listaClientes.get(0).getNombre());
            
            if(this.listaClientes.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "No se han encontrado clientes con los criterios de búsqueda", ""));
            }
         
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "No se han encontrado períodos fiscales con los criterios de búsqueda", ""));
        }
        
    }
    
    public void definirRating(ActionEvent event){
        
        System.out.println("rating cambiado:::"+this.getListaVariablesFrontResultado().get(0).getValue());
        System.out.println("Lista Resultado"+this.listaVariablesFrontResultado.get(0).getValue());
        System.out.println("Lista Resultado Id:::"+this.listaVariablesFrontResultado.get(1).getValue());
        System.out.println("Ccomentario en definirRating:::"+getComentariosUsuario());
        System.out.println("Id:::"+ this.getListaVariablesFrontResultado().get(1).getNombre() + " "+this.getListaVariablesFrontResultado().get(1).getValue());
        
        this.setRatingUpdate(this.listaVariablesFrontResultado);
       
    }
    
    
     public void confirmarRating(ActionEvent event){
         
          
        System.out.println("rating cambiado confirmar:::"+this.getListaVariablesFrontResultado().get(0).getValue());
        System.out.println("Lista Resultado confirmar"+this.listaVariablesFrontResultado.get(0).getValue());
        System.out.println("Lista Resultado Id confirmar:::"+this.listaVariablesFrontResultado.get(1).getValue());
        System.out.println("Ccomentario en definirRating confirmar:::"+getComentariosUsuario());
        System.out.println("Id:::"+ this.getListaVariablesFrontResultado().get(1).getNombre() + " "+this.getListaVariablesFrontResultado().get(1).getValue());
        
        this.setRatingUpdate(this.listaVariablesFrontResultado);
        
        if(ejbFacade.confirmarRating(new BigDecimal(this.getListaVariablesFrontResultado().get(0).getValue()), new BigDecimal(this.getListaVariablesFrontResultado().get(0).getValue()), new BigDecimal(this.getListaVariablesFrontResultado().get(1).getValue()), getComentariosUsuario())){
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado satisfactoriamente.", ""));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error al actualizar la información del rating, por favor intente más tarde.", ""));
        }
        
     }
    
    
     public void definirRating1(ActionEvent event){
        System.out.println("rating cambiado:::"+this.getListaVariablesFrontResultado().get(0).getValue());    
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
    
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
}
