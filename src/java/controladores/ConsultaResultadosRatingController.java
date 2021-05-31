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
import entidades.ResultadoLazyDataModel;
import entidades.VariablesRating;
import fachadas.ConsultaResultadosRatingFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import java.io.Serializable;
import org.primefaces.model.LazyDataModel;
import java.text.DateFormat;
import java.text.ParseException;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author santiago
 */
@ManagedBean(name="consultaResultadosRating")
@ViewScoped
public class ConsultaResultadosRatingController extends AbstractController implements Serializable {

    @EJB
    private ConsultaResultadosRatingFacade ejbFacade;
    
    private List<RatingInfo> results;
    private LazyDataModel<RatingInfo> lazyModel;
    private LazyDataModel<RatingInfo> lazyModelFiltro;
    private List<GruposClientes> listaGrupos;
    private List<ClientesRating> listaClientes;
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
    private String nombreDiligenciado;
    private List<Modulo> listaModulos;
    private String fileName = "ResultadosRating_" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "-" + new SimpleDateFormat("HHmmss").format(new Date()) + ".xls";
    private List<VariablesRating> variablesRating;
    private List<List<VariablesRating>> listVariablesRating;
    private List<VariablesRating> variablesRatingComportamiento;
    private List<VariablesRating> variablesRatingResultado;
    private List<VariablesRating> ratingUpdate;
    private List<RatingInfo> listaResultadosRating;
    private List<RatingInfo> listaResultadosRatingFiltro;
    private List<RatingInfo> resultsRating;
    private String nitSeleccionado;
    private String grupoDiligenciado;
    private Date daySelecction;
    private Date minDate;
    private Date maxDate;
    private String strDateMin;
    private String strDateMax;
    private String strDateList;
    private Date date;
    private Date finalDate;
    private Boolean validDate;
    private String nitDiligenciado;

    @PostConstruct
    @Override
    public void init(){
        this.nitSeleccionado = "";
        this.nombreDiligenciado = "";
        this.grupoDiligenciado = "";
        this.maxDate = null;
        this.minDate = null;
        this.strDateMin = "";
        this.strDateMax = null;
        this.strDateList = "";
        this.finalDate = null;
        this.precargaInformacion();
        this.lazyModel = new ResultadoLazyDataModel(this.getListaResultadosRating());
        date = new Date();   
        this.validDate = true;
    }

    public void precargaInformacion(){
        this.setListaResultadosRating(getEjbFacade().getResultadosRating());  
    }
    
    public void displayDataTable() throws ParseException {
        this.listaResultadosRatingFiltro = new ArrayList<>();
        this.finalDate = new Date();
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       
        if (!this.nitSeleccionado.isEmpty()) {
            for (int i = 0; i < this.listaResultadosRating.size(); i++) {
                String nit = this.listaResultadosRating.get(i).getNit();
                if (nit != null) {
                    if (nit.equals(this.nitSeleccionado)) {
                        this.listaResultadosRatingFiltro.add(this.listaResultadosRating.get(i));   
                    }
                }
            }
            this.lazyModelFiltro = new ResultadoLazyDataModel(this.getListaResultadosRatingFiltro());  
        }
        if (!this.nombreDiligenciado.isEmpty()) {
            for (int i = 0; i < this.listaResultadosRating.size(); i++) {
                String razonSocial = this.listaResultadosRating.get(i).getRazonSocial();
                if (razonSocial != null) {
                    if (razonSocial.equals(this.nombreDiligenciado)) {
                        this.listaResultadosRatingFiltro.add(this.listaResultadosRating.get(i));   
                    }
                }
            }
            this.lazyModelFiltro = new ResultadoLazyDataModel(this.getListaResultadosRatingFiltro());  
        }
        if (!this.grupoDiligenciado.isEmpty()) {
            for (int i = 0; i < this.listaResultadosRating.size(); i++) {
                String grupo = this.listaResultadosRating.get(i).getGrupoEconomico();
                if (grupo != null) {
                    if (grupo.equals(this.grupoDiligenciado)) {
                        this.listaResultadosRatingFiltro.add(this.listaResultadosRating.get(i));   
                    }
                }
            }
            this.lazyModelFiltro = new ResultadoLazyDataModel(this.getListaResultadosRatingFiltro());  
        }
        
        if (this.minDate != null && this.maxDate != null) {
            strDateMin = dateFormat.format(this.minDate);
            strDateMax = dateFormat.format(this.maxDate);
          
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date initDate = format.parse(strDateMin); 
            Date endDate = format.parse(strDateMax);
            
            for (int i = 0; i < this.listaResultadosRating.size(); i++) {
                Date dateInsert = new SimpleDateFormat("dd/MM/yyyy").parse(this.getListaResultadosRating().get(i).getFechaInsercion());
                strDateList = dateFormat.format(dateInsert);
                Date dateDB = format.parse(strDateList);
                int resultCompareInit = initDate.compareTo(dateDB);
                int resultCompareEnd = endDate.compareTo(dateDB);
                
                if(resultCompareInit == 0 || resultCompareEnd == 0){
                    this.listaResultadosRatingFiltro.add(this.listaResultadosRating.get(i));
                }
                
                if(resultCompareInit < 0 && resultCompareEnd > 0){
                    this.listaResultadosRatingFiltro.add(this.listaResultadosRating.get(i));
                }
            }
            this.lazyModelFiltro = new ResultadoLazyDataModel(this.getListaResultadosRatingFiltro());  
        }
        
        if (this.minDate != null && this.maxDate == null) {
            strDateMin = dateFormat.format(this.minDate);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date initDate = format.parse(strDateMin);
            
            for (int i = 0; i < this.listaResultadosRating.size(); i++) {
                Date dateInsert = new SimpleDateFormat("dd/MM/yyyy").parse(this.getListaResultadosRating().get(i).getFechaInsercion());
                strDateList = dateFormat.format(dateInsert);
                
                if(strDateList != null) {
                    Date dateDB = format.parse(strDateList);
                    int resultCompare = initDate.compareTo(dateDB);
                    
                    if(resultCompare == 0 || resultCompare < 0) {
                        this.listaResultadosRatingFiltro.add(this.listaResultadosRating.get(i));
                    }
                }
            }   
            this.lazyModelFiltro = new ResultadoLazyDataModel(this.getListaResultadosRatingFiltro());  
        }
        
        if ( this.nitSeleccionado.isEmpty() && this.nombreDiligenciado.isEmpty() && this.grupoDiligenciado.isEmpty() && this.minDate == null) {
            this.listaResultadosRatingFiltro = this.getListaResultadosRating();
            this.lazyModelFiltro = new ResultadoLazyDataModel(this.listaResultadosRatingFiltro);
        }
    }
    
    public void onDateSelect(SelectEvent event) {
        Date dateSelected = (Date)event.getObject();
       
        this.setValidDate(false);
        this.setFinalDate(dateSelected);
    }
    
    
    public void postProcessXLS(Object document) {
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
    
    public void limpiarResultados(ActionEvent event){
        this.listaClientes = new ArrayList<>();
        this.nombreDiligenciado = ""; 
    }
    
    
    /**
     * @return the ejbFacade
     */
    public ConsultaResultadosRatingFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(ConsultaResultadosRatingFacade ejbFacade) {
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
    
    public List<RatingInfo> getListaResultadosRating() {
        return listaResultadosRating;
    }

    public void setListaResultadosRating(List<RatingInfo> listaResultadosRating) {
        this.listaResultadosRating = listaResultadosRating;
    }
    
    public List<RatingInfo> getResults() {
        return results;
    }

    public void setResults(List<RatingInfo> results) {
        this.results = results;
    }

    public LazyDataModel<RatingInfo> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<RatingInfo> lazyModel) {
        this.lazyModel = lazyModel;
    }
    
    public List<RatingInfo> getResultsRating() {
        return resultsRating;
    }

    public void setResultsRating(List<RatingInfo> resultsRating) {
        this.resultsRating = resultsRating;
    }
    
    public String getNitSeleccionado() {
        return nitSeleccionado;
    }

    public void setNitSeleccionado(String nitSeleccionado) {
        this.nitSeleccionado = nitSeleccionado;
    }
    
    public List<RatingInfo> getListaResultadosRatingFiltro() {
        return listaResultadosRatingFiltro;
    }

    public void setListaResultadosRatingFiltro(List<RatingInfo> listaResultadosRatingFiltro) {
        this.listaResultadosRatingFiltro = listaResultadosRatingFiltro;
    }
    
    public String getGrupoDiligenciado() {
        return grupoDiligenciado;
    }

    public void setGrupoDiligenciado(String grupoDiligenciado) {
        this.grupoDiligenciado = grupoDiligenciado;
    }
    
   public Date getDaySelecction() {
        return daySelecction;
    }

    public void setDaySelecction(Date daySelecction) {
        this.daySelecction = daySelecction;
    }
    
    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
    
    public LazyDataModel<RatingInfo> getLazyModelFiltro() {
        return lazyModelFiltro;
    }

    public void setLazyModelFiltro(LazyDataModel<RatingInfo> lazyModelFiltro) {
        this.lazyModelFiltro = lazyModelFiltro;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
    
    public Boolean getValidDate() {
        return validDate;
    }

    public void setValidDate(Boolean validDate) {
        this.validDate = validDate;
    }
    
     public String getNombreDiligenciado() {
        return nombreDiligenciado;
    }

    public void setNombreDiligenciado(String nombreDiligenciado) {
        this.nombreDiligenciado = nombreDiligenciado;
    }

    public String getNitDiligenciado() {
        return nitDiligenciado;
    }

    public void setNitDiligenciado(String nitDiligenciado) {
        this.nitDiligenciado = nitDiligenciado;
    }
}
