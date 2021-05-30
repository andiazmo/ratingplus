/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.BetaModuloCasoUso;
import entidades.BetaModuloCategoria;
import entidades.VariablesRating;
import fachadas.ParamBetaModuloCasoFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author santiago
 */
@ManagedBean(name="betaModuloCaso")
@ViewScoped
public class ParamBetaModuloController extends AbstractController<BetaModuloCasoUso>{
    
    @EJB
    private ParamBetaModuloCasoFacade ejbFacade;
    
    private List<BetaModuloCasoUso> listaBetas;
    private List<BetaModuloCasoUso> listaBetasFiltrado;
    private List<BetaModuloCasoUso> listaBetasFiltradoFront;
    private List<BetaModuloCategoria> listaCategorias;
    private List<BetaModuloCasoUso> listaBetasEditados;
    private String codigoBeta;
    private int categoriaSeleccionada = 0;
    private String valor;
    private BetaModuloCasoUso registroSeleccionado = null;
    private List<BetaModuloCasoUso> listaCasos;
    private Map<String, String> casos = new HashMap<>();
    private Map<String, String> variables = new HashMap<>();
    private String variableSeleccionada;
    private String casoSeleccionado;
    private Boolean updateVariableResult;

    @PostConstruct
    public void init(){
        this.precargaInformacion();
    }

    public void precargaInformacion(){
        setListaCategorias(ejbFacade.listarCategorias());
        setListaBetas(ejbFacade.listarBetasModulo());
        iniciarListaCasos();
        iniciarListaVariables();
        this.listaBetasFiltrado = new ArrayList<BetaModuloCasoUso>();
        this.listaBetasEditados = new ArrayList<BetaModuloCasoUso>();
        this.variableSeleccionada = null;
    }
    
    public void inicializarVariables(){
        this.codigoBeta = "";
        this.categoriaSeleccionada = 0;
        this.valor = "";
        this.setRegistroSeleccionado(null);
        
    }
    
    public void iniciarListaCasos(){
        this.listaCasos = new ArrayList<BetaModuloCasoUso>();
        this.listaCasos.add(this.listaBetas.get(0));
        casos.put(String.valueOf(this.listaBetas.get(0).getNumCaso()), 
                String.valueOf(this.listaBetas.get(0).getNumCaso()));
        
        variables.put(this.listaBetas.get(0).getCategoria().getCategoria(), 
                this.listaBetas.get(0).getCategoria().getCategoria());
        int caso = this.listaCasos.get(0).getNumCaso();
        String variable = this.listaCasos.get(0).getCategoria().getCategoria();
        for (int i = 1; i < this.listaBetas.size(); i++) {
            
            if(this.listaBetas.get(i).getNumCaso() != caso) {
                this.listaCasos.add(this.listaBetas.get(i));
                caso = this.listaBetas.get(i).getNumCaso();
                casos.put(String.valueOf(this.listaBetas.get(i).getNumCaso()), 
                        String.valueOf(this.listaBetas.get(i).getNumCaso()));
            }
        }
    }
    
    public void iniciarListaVariables(){
        variables.put(this.listaBetas.get(0).getCategoria().getCategoria(), 
                this.listaBetas.get(0).getCategoria().getCategoria());
        String variable = this.listaCasos.get(0).getCategoria().getCategoria();
        for (int i = 1; i < this.listaBetas.size(); i++) {
            String variableLista = this.listaBetas.get(i).getCategoria().getCategoria();
            System.out.println("variableLista:::"+variableLista);
            System.out.println("variableMap:::"+variables.get(variableLista));
            if(variables.get(variableLista) == null) {
                System.out.println("variable a insertar en el Map:::"+this.listaBetas.get(i).getCategoria().getCategoria());
                variables.put(this.listaBetas.get(i).getCategoria().getCategoria(),
                        this.listaBetas.get(i).getCategoria().getCategoria());
            }
            
        }
    }
    
    public void onCaseChange() {
        System.out.println("Metodo onCaseChange");
        System.out.println("Caso seleccionado:::"+this.casoSeleccionado);
        
        
        
        if(this.casoSeleccionado != null && !this.casoSeleccionado.equals("")) {
            
            for(int i=0; i < this.listaBetas.size(); i++) {
                
                if (this.casoSeleccionado.equals("1") && this.listaBetas.get(i).
                        getNumCaso() == 1) {
                    System.out.println("Va a adicionar");
                    this.listaBetasFiltrado.add(this.listaBetas.get(i));
                }
                
                if (this.casoSeleccionado.equals("1") && this.listaBetas.get(i).
                        getNumCaso() == 3) {
                    this.listaBetasFiltrado.add(this.listaBetas.get(i));
                }
                
                if (this.casoSeleccionado.equals("1") && this.listaBetas.get(i).
                        getNumCaso() == 5) {
                    this.listaBetasFiltrado.add(this.listaBetas.get(i));
                }   
            }
        }
    }
    
    
    public void displayDataTable() {
        
        System.out.println("Metodo displayTabla");
        System.out.println("Variable selecconada:::"+this.variableSeleccionada + " " + this.variableSeleccionada.length());
        System.out.println("Caso seleccionado:::"+this.casoSeleccionado);
        
        this.listaBetasFiltradoFront = new ArrayList<BetaModuloCasoUso>();
        
        if(variableSeleccionada != null && !variableSeleccionada.equals("")) {
            System.out.println("Primer filtro:::");
            System.out.println("Tamaño lista caso:::"+this.listaBetasFiltrado);
            for(int i=0; i<this.listaBetasFiltrado.size();i++) {
                System.out.println("Variable en la lista:::"+this.listaBetasFiltrado.get(i).getCategoria().getCategoria());
                String casoStr = String.valueOf(this.listaBetasFiltrado.get(i).getNumCaso());
                if(this.listaBetasFiltrado.get(i).getCategoria().getCategoria().
                        equals(this.variableSeleccionada) && casoStr.equals(this.casoSeleccionado)) {
                    this.listaBetasFiltradoFront.add(this.listaBetasFiltrado.get(i));
                    System.out.println("Tamaño lista:::"+this.listaBetasFiltrado.size());
                }
            }
        }
        
        if((variableSeleccionada.length() == 0) && (casoSeleccionado.length() == 0)) {
            System.out.println("Lista filtrada"+listaBetasFiltrado.size());
         //   this.listaBetasFiltradoFront = this.listaBetasFiltrado;
            this.listaBetasFiltradoFront.addAll(this.listaBetas);
        }
        
    }
    
    
    public void updateVariablesValuesFront() {
        System.out.println("Metodo updateVariablesValuesFront");
        
//        for (int i = 0; i < this.getListaRespuestasEditadas().size(); i++) {
//            System.out.println("Valores editados:::"+this.getListaRespuestasEditadas().get(i).getWoe());
//            System.out.println("Valores editados:::"+this.getListaRespuestasEditadas().get(i).getMax());
//            System.out.println("Valores editados:::"+this.getListaRespuestasEditadas().get(i).getMin());
//        }
//       
//        this.setUpdateVariableResult(this.getEjbFacade().updateVarablesRespuesta(this.getListaRespuestasEditadas()));
         this.setUpdateVariableResult(this.getEjbFacade().actualizarParametro(this.listaBetasEditados));
        
    }
    
    
     public void onRowEdit(RowEditEvent event) {
        BetaModuloCasoUso betaModuloCasoUso = new BetaModuloCasoUso();
        
        BetaModuloCasoUso variable = (BetaModuloCasoUso)event.getObject();
        FacesMessage msg = new FacesMessage("Valores Actualizados", String.valueOf(variable.getCategoria().getCategoria()+ " " + "Caso: " + " " + String.valueOf(variable.getNumCaso())));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        System.out.println("Id:::"+String.valueOf(variable.getCodigo()));
        System.out.println("numcaso:::"+String.valueOf(variable.getNumCaso()));
        System.out.println("categoria:::"+variable.getCategoria().getCategoria());
         System.out.println("valor:::"+variable.getValor());
        
        betaModuloCasoUso.setCodigo(variable.getCodigo());
        betaModuloCasoUso.setNumCaso(variable.getNumCaso());
        betaModuloCasoUso.setCategoria(variable.getCategoria());
        betaModuloCasoUso.setValor(variable.getValor());
//        System.out.println("WOE:::"+String.valueOf(respuesta.getWoe()));
//        System.out.println("Min:::"+String.valueOf(respuesta.getMin()));
//        System.out.println("Max:::"+String.valueOf(respuesta.getMax()));
//        variableRespuesta.setId(respuesta.getId());
//        variableRespuesta.setWoe(String.valueOf(respuesta.getWoe()));
//        variableRespuesta.setMin(String.valueOf(respuesta.getMin()));
//        variableRespuesta.setMax(String.valueOf(respuesta.getMax()));
        
      //  this.listaRespuestasEditadas.add(variableRespuesta);
      this.listaBetasEditados.add(betaModuloCasoUso);
        
    }
     
     public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(event.getObject().toString()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public List<BetaModuloCasoUso> getListaBetasEditados() {
        return listaBetasEditados;
    }

    public void setListaBetasEditados(List<BetaModuloCasoUso> listaBetasEditados) {
        this.listaBetasEditados = listaBetasEditados;
    }

    /**
     * @return the listaBetas
     */
    public List<BetaModuloCasoUso> getListaBetas() {
        return listaBetas;
    }

    /**
     * @param listaBetas the listaBetas to set
     */
    public void setListaBetas(List<BetaModuloCasoUso> listaBetas) {
        this.listaBetas = listaBetas;
    }

    /**
     * @return the listaCategorias
     */
    public List<BetaModuloCategoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * @param listaCategorias the listaCategorias to set
     */
    public void setListaCategorias(List<BetaModuloCategoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    /**
     * @return the codigo
     */
    public String getCodigoBeta() {
        return codigoBeta;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigoBeta(String codigoBeta) {
        this.codigoBeta = codigoBeta;
    }

    /**
     * @return the categoriaSeleccionada
     */
    public int getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    /**
     * @param categoriaSeleccionada the categoriaSeleccionada to set
     */
    public void setCategoriaSeleccionada(int categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void setRegistroSeleccionado(BetaModuloCasoUso registroSeleccionado){
        this.registroSeleccionado = registroSeleccionado;
    }

    public void guardarParametro(ActionEvent event){
        
        boolean variableRegistro = false;
        for(BetaModuloCasoUso objetoExistente : listaBetas){
        
            if(objetoExistente.getNumCaso() == Integer.parseInt(codigoBeta) &&
               objetoExistente.getCategoria().getCodigo() == categoriaSeleccionada){
                variableRegistro = true;
                break;
            }
            
        }
        
        if(variableRegistro){
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de registro. Información ya existente para la categoría y el caso de uso diligenciados.",""));       
        } else {
            this.registrarInformacion(getRegistroSeleccionado() == null ? "ADD" : "EDIT");
        }
                
    }
        
    public void seleccionarParametro(ActionEvent event){
        this.setRegistroSeleccionado((BetaModuloCasoUso) event.getComponent().getAttributes().get("registroSeleccionado"));
        this.codigoBeta = String.valueOf(getRegistroSeleccionado().getNumCaso());
        this.categoriaSeleccionada = getRegistroSeleccionado().getCategoria().getCodigo();
        this.valor = String.valueOf(getRegistroSeleccionado().getValor());
    }
    
    public void registrarInformacion(String accion){
        
        BetaModuloCategoria categoria = new BetaModuloCategoria();
        for(BetaModuloCategoria objCategoria : listaCategorias){
            if(objCategoria.getCodigo() == categoriaSeleccionada){
                categoria = objCategoria;
                break;
            }
        }

        BetaModuloCasoUso parametro = new BetaModuloCasoUso();
        parametro.setCodigo(accion.equals("ADD") ? 0 : getRegistroSeleccionado().getCodigo());
        parametro.setNumCaso(Integer.parseInt(codigoBeta));
        parametro.setCategoria(categoria);
        parametro.setValor(Double.parseDouble(valor));

        if(ejbFacade.guardarParametro(parametro)){
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_INFO,"Información guardada",""));    
            this.precargaInformacion();
            this.inicializarVariables();
        } else {
            FacesContext.getCurrentInstance().addMessage(":growl", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de registro. Se ha presentado un error técnico al guardar la información.",""));
        }
        
    }
    
    public void preseleccionarRegistro(ActionEvent event){
    
        setRegistroSeleccionado((BetaModuloCasoUso) event.getComponent().getAttributes().get("registroSeleccionado"));
        
    }
    
  
    
    /**
     * @return the registroSeleccionado
     */
    public BetaModuloCasoUso getRegistroSeleccionado() {
        return registroSeleccionado;
    }
    
    public List<BetaModuloCasoUso> getListaCasos() {
        return listaCasos;
    }

    public void setListaCasos(List<BetaModuloCasoUso> listaCasos) {
        this.listaCasos = listaCasos;
    }
    
    public Map<String, String> getCasos() {
        return casos;
    }

    public void setCasos(Map<String, String> casos) {
        this.casos = casos;
    }
    
    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }
    
    public List<BetaModuloCasoUso> getListaBetasFiltradoFront() {
        return listaBetasFiltradoFront;
    }

    public void setListaBetasFiltradoFront(List<BetaModuloCasoUso> listaBetasFiltradoFront) {
        this.listaBetasFiltradoFront = listaBetasFiltradoFront;
    }

    public String getVariableSeleccionada() {
        return variableSeleccionada;
    }

    public void setVariableSeleccionada(String variableSeleccionada) {
        this.variableSeleccionada = variableSeleccionada;
    }

    public String getCasoSeleccionado() {
        return casoSeleccionado;
    }

    public void setCasoSeleccionado(String casoSeleccionado) {
        this.casoSeleccionado = casoSeleccionado;
    }
    
    public List<BetaModuloCasoUso> getListaBetasFiltrado() {
        return listaBetasFiltrado;
    }

    public void setListaBetasFiltrado(List<BetaModuloCasoUso> listaBetasFiltrado) {
        this.listaBetasFiltrado = listaBetasFiltrado;
    }
    
    public Boolean getUpdateVariableResult() {
        return updateVariableResult;
    }

    public void setUpdateVariableResult(Boolean updateVariableResult) {
        this.updateVariableResult = updateVariableResult;
    }
    
    public ParamBetaModuloCasoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ParamBetaModuloCasoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

}
