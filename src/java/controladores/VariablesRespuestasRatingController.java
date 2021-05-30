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
import fachadas.VariablesRespuestasRatingFacade;
import static java.lang.Boolean.TRUE;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
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
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author santiago
 */
@ManagedBean(name="respuestaRating")
@ViewScoped
public class VariablesRespuestasRatingController extends AbstractController {

    @EJB
    private VariablesRespuestasRatingFacade ejbFacade;
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
    private List<VariablesRating> respuestasRating;
    private List<VariablesRating> respuestasRatingFinancieras;
    private VariablesRating respuestaRatingSeleccionada;
    private List<VariablesRating> respuestasRatingFiltradas;
    private List<Modulo> respuestasModuloRating;
    private String moduloSeleccionado;
    private Map<String, String> modulos;
    private LazyDataModel<VariablesRating> variablesRespuestas;
    private Map<String,String> variablesFinancieras;
    private Map<String,String> variablesComportamiento;
    private Map<String,String> variables;
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String variableSeleccionada;
    private List<VariablesRating> listaRespuestasRatingFinancieras;
    private List<VariablesRating> listaRespuestasEditadas;
    private Boolean updateVariableResult;


    @PostConstruct
    public void init(){
        this.precargaInformacion();
    }

    public void precargaInformacion(){
        this.setRespuestasRating(getEjbFacade().listarRespuestasRating());
        this.setRespuestasModuloRating(getEjbFacade().listarModulo());
        
        modulos = new HashMap<>();
        for (int i = 0; i < this.getRespuestasModuloRating().size() - 1; i++) {
            modulos.put(this.getRespuestasModuloRating().get(i).getNombre(), this.getRespuestasModuloRating().get(i).getNombre());
        }
        
        this.getVariablesModuloFinanciero(this.getRespuestasRating());
      //  this.getVariablesModuloFinancieroFront(this.getRespuestasRating());
      //  this.moduloSeleccionado = "";
      //  this.variableSeleccionada = "";
        
        variablesRespuestas = new LazyDataModel<VariablesRating>() {
            @Override
            public void forEach(Consumer<? super VariablesRating> action) {
                super.forEach(action); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Spliterator<VariablesRating> spliterator() {
                return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
    }
    
    public void getVariablesModuloFinanciero(List<VariablesRating> variables) {
        this.variables = new HashMap<>();
        
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getIdModulo() == 1) {
                System.out.println("Variable seteada:::"+variables.get(i).getNombre());
                this.variables.put(variables.get(i).getNombre(), variables.get(i).getNombre());
            }
        }
        
        System.out.println("Cantidad variables modulo financiero:::"+variables.size());
        
    }
    
    public void getVariablesModuloFinancieroFront(List<VariablesRating> variables) {
        
        System.out.println("Metodo getVariablesModuloFinancieroFront:::"+moduloSeleccionado + " " + variableSeleccionada);
        System.out.println("Lista que viene como parametro:::"+variables.size());
        this.listaRespuestasRatingFinancieras = new ArrayList<>();
        this.listaRespuestasEditadas = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++) {
            System.out.println("Lista con los modulos:::"+variables.get(i).getNombreModulo());
            if (variables.get(i).getNombreModulo().equals(moduloSeleccionado) && variables.get(i).getNombre().equals(variableSeleccionada)) {
                System.out.println("No se revento");
                System.out.println("Variable:::"+variables.get(i).getNombre());
                this.listaRespuestasRatingFinancieras.add(variables.get(i));
            }
            
            if (variables.get(i).getNombreModulo().equals(moduloSeleccionado) && variableSeleccionada.equals("")) {
                System.out.println("No se revento");
                System.out.println("Variable:::"+variables.get(i).getNombre());
                this.listaRespuestasRatingFinancieras.add(variables.get(i));
            }
        }
    }
    
     public void getVariablesModuloComportamiento(List<VariablesRating> variables) {
        this.variables = new HashMap<>();
        
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getIdModulo() == 2) {
                System.out.println("Variable seteada:::"+variables.get(i).getNombre());
                this.variables.put(variables.get(i).getNombre(), variables.get(i).getNombre());
            }
        }
        
        System.out.println("Cantidad variables modulo comportamiento:::"+variables.size());
        
    }
     
     public void getVariablesModuloObjetivo(List<VariablesRating> variables) {
        this.variables = new HashMap<>();
        
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getIdModulo() == 3) {
                System.out.println("Variable seteada:::"+variables.get(i).getNombre());
                this.variables.put(variables.get(i).getNombre(), variables.get(i).getNombre());
            }
        }
        
        System.out.println("Cantidad variables modulo Objetivo:::"+variables.size());
        
    }
     
    public void getVariablesModuloSubjetivo(List<VariablesRating> variables) {
        this.variables = new HashMap<>();
        
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getIdModulo() == 4) {
                System.out.println("Variable seteada:::"+variables.get(i).getNombre());
                this.variables.put(variables.get(i).getNombre(), variables.get(i).getNombre());
            }
        }
        
        System.out.println("Cantidad variables modulo Subjetivo:::"+variables.size());
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        VariablesRating variableRespuesta = new VariablesRating();
        
        VariablesRating respuesta = (VariablesRating)event.getObject();
        FacesMessage msg = new FacesMessage("Valores Actualizados", String.valueOf(respuesta.getNombre()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Id:::"+String.valueOf(respuesta.getId()));
        System.out.println("WOE:::"+String.valueOf(respuesta.getWoe()));
        System.out.println("Min:::"+String.valueOf(respuesta.getMin()));
        System.out.println("Max:::"+String.valueOf(respuesta.getMax()));
        variableRespuesta.setId(respuesta.getId());
        variableRespuesta.setWoe(String.valueOf(respuesta.getWoe()));
        variableRespuesta.setMin(String.valueOf(respuesta.getMin()));
        variableRespuesta.setMax(String.valueOf(respuesta.getMax()));
        
        this.listaRespuestasEditadas.add(variableRespuesta);
        
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(event.getObject().toString()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void updateVariablesValuesFront() {
        System.out.println("Metodo updateVariablesValuesFront");
        
        for (int i = 0; i < this.getListaRespuestasEditadas().size(); i++) {
            System.out.println("Valores editados:::"+this.getListaRespuestasEditadas().get(i).getWoe());
            System.out.println("Valores editados:::"+this.getListaRespuestasEditadas().get(i).getMax());
            System.out.println("Valores editados:::"+this.getListaRespuestasEditadas().get(i).getMin());
        }
       
        this.setUpdateVariableResult(this.getEjbFacade().updateVarablesRespuesta(this.getListaRespuestasEditadas()));
        
    }
    
    
    
    public void precargaListaVariables(){
        
       this.setListaVariablesModulo(getEjbFacade().listarVariablesRating());
       
    }
    
    public void onModuleChange() {
        System.out.println("Metodo onModuleChange");
        System.out.println("Dato obtenido:::"+this.moduloSeleccionado);
        
        if(moduloSeleccionado != null && !moduloSeleccionado.equals("")) {
            if (this.moduloSeleccionado.equals("Financiero")) {
                this.getVariablesModuloFinanciero(this.getRespuestasRating());
            }
            if (this.moduloSeleccionado.equals("Comportamiento")) {
                this.getVariablesModuloComportamiento(this.getRespuestasRating());
            }
            if (this.moduloSeleccionado.equals("Objetivo")) {
                this.getVariablesModuloObjetivo(this.getRespuestasRating());
            }
            if (this.moduloSeleccionado.equals("Subjetivo")) {
                this.getVariablesModuloSubjetivo(this.getRespuestasRating());
            }
            
        }
    }
    
    public void displayDataTable() {
        
        System.out.println("Metodo displayTabla");
        this.getVariablesModuloFinancieroFront(this.getRespuestasRating());
        if(variableSeleccionada == null && variableSeleccionada.equals("")) {
            this.getVariablesModuloFinancieroFront(this.getRespuestasRating());
        }
        if(variableSeleccionada != null && !variableSeleccionada.equals("")) {
            this.getVariablesModuloFinancieroFront(this.getRespuestasRating());
        }
    }

    public Map<String, String> getVariablesFinancieras() {
        return variablesFinancieras;
    }

    public void setVariablesFinancieras(Map<String, String> variablesFinancieras) {
        this.variablesFinancieras = variablesFinancieras;
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
    
     public List<VariablesRating> getRespuestasRating() {
        return respuestasRating;
    }

    public void setRespuestasRating(List<VariablesRating> respuestasRating) {
        this.respuestasRating = respuestasRating;
    }
    
     public VariablesRespuestasRatingFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(VariablesRespuestasRatingFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    public VariablesRating getRespuestaRatingSeleccionada() {
        return respuestaRatingSeleccionada;
    }

    public void setRespuestaRatingSeleccionada(VariablesRating respuestaRatingSeleccionada) {
        this.respuestaRatingSeleccionada = respuestaRatingSeleccionada;
    }
    
    public List<VariablesRating> getRespuestasRatingFiltradas() {
        return respuestasRatingFiltradas;
    }

    public void setRespuestasRatingFiltradas(List<VariablesRating> respuestasRatingFiltradas) {
        this.respuestasRatingFiltradas = respuestasRatingFiltradas;
    }
    
    public List<Modulo> getRespuestasModuloRating() {
        return respuestasModuloRating;
    }

    public void setRespuestasModuloRating(List<Modulo> respuestasModuloRating) {
        this.respuestasModuloRating = respuestasModuloRating;
    }
    
    public String getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(String moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }
    
    public Map<String, String> getModulos() {
        return modulos;
    }

    public void setModulos(Map<String, String> modulos) {
        this.modulos = modulos;
    }
    
     public LazyDataModel<VariablesRating> getVariablesRespuestas() {
        return variablesRespuestas;
    }

    public void setVariablesRespuestas(LazyDataModel<VariablesRating> variablesRespuestas) {
        this.variablesRespuestas = variablesRespuestas;
    }
    
     public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }
    
    public String getVariableSeleccionada() {
        return variableSeleccionada;
    }

    public void setVariableSeleccionada(String variableSeleccionada) {
        this.variableSeleccionada = variableSeleccionada;
    }
    
    public List<VariablesRating> getRespuestasRatingFinancieras() {
        return respuestasRatingFinancieras;
    }

    public void setRespuestasRatingFinancieras(List<VariablesRating> respuestasRatingFinancieras) {
        this.respuestasRatingFinancieras = respuestasRatingFinancieras;
    }
    
    public List<VariablesRating> getListaRespuestasRatingFinancieras() {
        return listaRespuestasRatingFinancieras;
    }

    public void setListaRespuestasRatingFinancieras(List<VariablesRating> listaRespuestasRatingFinancieras) {
        this.listaRespuestasRatingFinancieras = listaRespuestasRatingFinancieras;
    }
    
    public List<VariablesRating> getListaRespuestasEditadas() {
        return listaRespuestasEditadas;
    }

    public void setListaRespuestasEditadas(List<VariablesRating> listaRespuestasEditadas) {
        this.listaRespuestasEditadas = listaRespuestasEditadas;
    }
    
    public Boolean getUpdateVariableResult() {
        return updateVariableResult;
    }

    public void setUpdateVariableResult(Boolean updateVariableResult) {
        this.updateVariableResult = updateVariableResult;
    }
    
    public Map<String, String> getVariablesComportamiento() {
        return variablesComportamiento;
    }

    public void setVariablesComportamiento(Map<String, String> variablesComportamiento) {
        this.variablesComportamiento = variablesComportamiento;
    }
    
    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }
    
}
