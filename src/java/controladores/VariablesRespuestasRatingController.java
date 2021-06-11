/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Modulo;
import entidades.VariablesRating;
import fachadas.ConsultaClientesRatingFacade;
import fachadas.ConsultaVariablesRatingFacade;
import fachadas.VariablesRespuestasRatingFacade;
import java.util.ArrayList;
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
import org.primefaces.event.RowEditEvent;
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
    
    @EJB
    private ConsultaClientesRatingFacade ejbFacadeCliente;
    
    @EJB
    private ConsultaVariablesRatingFacade ejbFacadeVariables;
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
    private Map<String,Map<String,String>> data = new HashMap<>();
    private String variableSeleccionada;
    private List<VariablesRating> listaRespuestasRatingFinancieras;
    private List<VariablesRating> listaRespuestasEditadas;
    private int updateVariableResult;


    @PostConstruct
    @Override
    public void init(){
        this.precargaInformacion();
    }

    public void precargaInformacion(){
        this.setRespuestasRating(getEjbFacade().listarRespuestasRating());
        this.setRespuestasModuloRating(getEjbFacadeVariables().listarModulo());
        
        
        modulos = new HashMap<>();
        for (int i = 0; i < this.getRespuestasModuloRating().size() - 1; i++) {
            modulos.put(this.getRespuestasModuloRating().get(i).getNombre(), 
                    this.getRespuestasModuloRating().get(i).getNombre());
        }
        this.getVariablesModuloFinanciero(this.getRespuestasRating());
      
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
                this.variables.put(variables.get(i).getNombre(), 
                        variables.get(i).getNombre());
            }
        }    
    }
    
    public void getVariablesModuloFinancieroFront(
            List<VariablesRating> variables) {
        this.listaRespuestasRatingFinancieras = new ArrayList<>();
        this.listaRespuestasEditadas = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getNombreModulo().equals(moduloSeleccionado) 
                    && variables.get(i).getNombre().equals(variableSeleccionada)) {
                this.listaRespuestasRatingFinancieras.add(variables.get(i));
            }
            
            if (variables.get(i).getNombreModulo().equals(moduloSeleccionado) 
                    && variableSeleccionada.equals("")) {
                this.listaRespuestasRatingFinancieras.add(variables.get(i));
            }
        }
    }
    
     public void getVariablesModuloComportamiento(List<VariablesRating> 
             variables) {
        this.variables = new HashMap<>();
        
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getIdModulo() == 2) {
                this.variables.put(variables.get(i).getNombre(), 
                        variables.get(i).getNombre());
            }
        }    
    }
     
     public void getVariablesModuloObjetivo(List<VariablesRating> variables) {
        this.variables = new HashMap<>();
        
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getIdModulo() == 3) {
                this.variables.put(variables.get(i).getNombre(), 
                        variables.get(i).getNombre());
            }
        }    
    }
     
    public void getVariablesModuloSubjetivo(List<VariablesRating> variables) {
        this.variables = new HashMap<>();
        
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getIdModulo() == 4) {
                this.variables.put(variables.get(i).getNombre(), 
                        variables.get(i).getNombre());
            }
        }    
    }
    
    public void onRowEdit(RowEditEvent event) {
        VariablesRating variableRespuesta = new VariablesRating();
        
        VariablesRating respuesta = (VariablesRating)event.getObject();
        FacesMessage msg = new FacesMessage("Por favor confirme el cambio",
                String.valueOf(respuesta.getNombre()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        variableRespuesta.setId(respuesta.getId());
        variableRespuesta.setWoe(String.valueOf(respuesta.getWoe()));
        variableRespuesta.setMin(String.valueOf(respuesta.getMin()));
        variableRespuesta.setMax(String.valueOf(respuesta.getMax()));
        
        this.listaRespuestasEditadas.add(variableRespuesta);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void updateVariablesValuesFront() {
        int result = this.getEjbFacade().
                updateVarablesRespuesta(this.getListaRespuestasEditadas());
        
        if(result != 0) {
            FacesMessage msg = new FacesMessage("Valor actualizado", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
        else {
            FacesMessage msg = new FacesMessage("No fue posible el cambio", 
                    "Intentelo mas tarde");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void precargaListaVariables(){
        this.setListaVariablesModulo(getEjbFacadeVariables().listarVariablesRating());
    }
    
    public void onModuleChange() {
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
        this.getVariablesModuloFinancieroFront(this.getRespuestasRating());
        if(variableSeleccionada == null && variableSeleccionada.equals("")) {
            this.getVariablesModuloFinancieroFront(this.getRespuestasRating());
        }
        if(variableSeleccionada != null && !variableSeleccionada.equals("")) {
            this.getVariablesModuloFinancieroFront(this.getRespuestasRating());
        }
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
    
    public Map<String, String> getVariablesFinancieras() {
        return variablesFinancieras;
    }

    public void setVariablesFinancieras(Map<String, String> variablesFinancieras) {
        this.variablesFinancieras = variablesFinancieras;
    }
     
    public List<VariablesRating> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(List<VariablesRating> listaVariables) {
        this.listaVariables = listaVariables;
    }
    
    public List<VariablesRating> getListaVariablesFinanciero() {
        return listaVariablesFinanciero;
    }

    public void setListaVariablesFinanciero(List<VariablesRating> 
            listaVariablesFinanciero) {
        this.listaVariablesFinanciero = listaVariablesFinanciero;
    }

    public List<VariablesRating> getListaVariablesComportamiento() {
        return listaVariablesComportamiento;
    }

    public void setListaVariablesComportamiento(List<VariablesRating> 
            listaVariablesComportamiento) {
        this.listaVariablesComportamiento = listaVariablesComportamiento;
    }

    public List<VariablesRating> getListaVariablesObjetivo() {
        return listaVariablesObjetivo;
    }

    public void setListaVariablesObjetivo(List<VariablesRating> 
            listaVariablesObjetivo) {
        this.listaVariablesObjetivo = listaVariablesObjetivo;
    }

    public List<VariablesRating> getListaVariablesSubjetivo() {
        return listaVariablesSubjetivo;
    }

    public void setListaVariablesSubjetivo(List<VariablesRating> 
            listaVariablesSubjetivo) {
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

    public void setListaVariablesModulo(List<List<VariablesRating>> 
            listaVariablesModulo) {
        this.listaVariablesModulo = listaVariablesModulo;
    }
    
    public List<VariablesRating> getListaVariablesFrontComportamiento() {
        return listaVariablesFrontComportamiento;
    }

    public void setListaVariablesFrontComportamiento(List<VariablesRating> 
            listaVariablesFrontComportamiento) {
        this.listaVariablesFrontComportamiento = listaVariablesFrontComportamiento;
    }

    public List<VariablesRating> getListaVariablesFrontObjetivo() {
        return listaVariablesFrontObjetivo;
    }

    public void setListaVariablesFrontObjetivo(List<VariablesRating> 
            listaVariablesFrontObjetivo) {
        this.listaVariablesFrontObjetivo = listaVariablesFrontObjetivo;
    }

    public List<VariablesRating> getListaVariablesFrontSubjetivo() {
        return listaVariablesFrontSubjetivo;
    }

    public void setListaVariablesFrontSubjetivo(List<VariablesRating> 
            listaVariablesFrontSubjetivo) {
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

    public void setListVariablesRating(List<List<VariablesRating>> 
            listVariablesRating) {
        this.listVariablesRating = listVariablesRating;
    }
    
    public List<VariablesRating> getVariablesRatingComportamiento() {
        return variablesRatingComportamiento;
    }

    public void setVariablesRatingComportamiento(List<VariablesRating> 
            variablesRatingComportamiento) {
        this.variablesRatingComportamiento = variablesRatingComportamiento;
    }
    
      public List<VariablesRating> getVariablesRatingResultado() {
        return variablesRatingResultado;
    }

    public void setVariablesRatingResultado(List<VariablesRating> 
            variablesRatingResultado) {
        this.variablesRatingResultado = variablesRatingResultado;
    }
    
     public List<VariablesRating> getListaVariablesFrontResultado() {
        return listaVariablesFrontResultado;
    }

    public void setListaVariablesFrontResultado(List<VariablesRating> 
            listaVariablesFrontResultado) {
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

    public void setRespuestaRatingSeleccionada(VariablesRating 
            respuestaRatingSeleccionada) {
        this.respuestaRatingSeleccionada = respuestaRatingSeleccionada;
    }
    
    public List<VariablesRating> getRespuestasRatingFiltradas() {
        return respuestasRatingFiltradas;
    }

    public void setRespuestasRatingFiltradas(List<VariablesRating> 
            respuestasRatingFiltradas) {
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

    public void setVariablesRespuestas(LazyDataModel<VariablesRating> 
            variablesRespuestas) {
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

    public void setRespuestasRatingFinancieras(List<VariablesRating> 
            respuestasRatingFinancieras) {
        this.respuestasRatingFinancieras = respuestasRatingFinancieras;
    }
    
    public List<VariablesRating> getListaRespuestasRatingFinancieras() {
        return listaRespuestasRatingFinancieras;
    }

    public void setListaRespuestasRatingFinancieras(List<VariablesRating> 
            listaRespuestasRatingFinancieras) {
        this.listaRespuestasRatingFinancieras = listaRespuestasRatingFinancieras;
    }
    
    public List<VariablesRating> getListaRespuestasEditadas() {
        return listaRespuestasEditadas;
    }

    public void setListaRespuestasEditadas(List<VariablesRating> 
            listaRespuestasEditadas) {
        this.listaRespuestasEditadas = listaRespuestasEditadas;
    }
    
    public int getUpdateVariableResult() {
        return updateVariableResult;
    }

    public void setUpdateVariableResult(int updateVariableResult) {
        this.updateVariableResult = updateVariableResult;
    }
    
    public Map<String, String> getVariablesComportamiento() {
        return variablesComportamiento;
    }

    public void setVariablesComportamiento(Map<String, String> 
            variablesComportamiento) {
        this.variablesComportamiento = variablesComportamiento;
    }
    
    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
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
    
}
