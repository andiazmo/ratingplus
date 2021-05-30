/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.BetaModuloCasoUso;
import entidades.BetaModuloCategoria;
import entidades.VariablesRating;
import entidades.Modulo;
import fachadas.ParamRespuestaVariableFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author santiago
 */
@ManagedBean(name="respuestasVariable")
@ViewScoped
public class ParamRespuestasVariableController extends AbstractController<Object>{
    
    @EJB
    private ParamRespuestaVariableFacade ejbFacade;
    
    private List<BetaModuloCasoUso> listaBetas;
    private List<BetaModuloCategoria> listaCategorias;
    private String codigoBeta;
    private int categoriaSeleccionada = 0;
    private String valor;
    private BetaModuloCasoUso registroSeleccionado = null;
    
    private List<List<VariablesRating>> listaVariablesModulo;
    private List<Modulo> listaModulos;
    private List<Modulo> listaModulosFront;
    private int moduloSeleccionado = 0;
    private List<VariablesRating> listaVariablesFinanciero;
    private List<VariablesRating> listaVariablesFront;

  
    @PostConstruct
    public void init(){
        this.precargaInformacion();
        this.precargaListaVariables();
        this.listaModulosFront = this.getListaModulos();
        listaVariablesFront = this.precargaListaFinanciera();
    }
    
    public void precargaInformacion(){
        //setListaCategorias(ejbFacade.listarCategorias());
       // setListaBetas(ejbFacade.listarBetasModulo());
        setListaVariablesModulo(ejbFacade.listarRespuestasVariablesRating());
        setListaModulos(ejbFacade.listarModulo());
        
        System.out.println("Tamaño lista modulos:::"+this.getListaModulos());
        
    }
    
    public void precargaListaVariables(){
        
       this.setListaVariablesModulo(getEjbFacade().listarVariablesRating());
       
    }
    
    public List<VariablesRating> precargaListaFinanciera(){
       
       this.setListaVariablesFinanciero(this.getListaVariablesModulo().get(0));
       
       return listaVariablesFinanciero;
    }
 
    public List<List<VariablesRating>> getListaVariablesModulo() {
        return listaVariablesModulo;
    }

    public void setListaVariablesModulo(List<List<VariablesRating>> listaVariablesModulo) {
        this.listaVariablesModulo = listaVariablesModulo;
    }
    
    public List<Modulo> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }
    
    public int getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(int moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }
    
    public List<Modulo> getListaModulosFront() {
        return listaModulosFront;
    }

    public void setListaModulosFront(List<Modulo> listaModulosFront) {
        this.listaModulosFront = listaModulosFront;
    }
    
    public ParamRespuestaVariableFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ParamRespuestaVariableFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    public List<VariablesRating> getListaVariablesFinanciero() {
        return listaVariablesFinanciero;
    }

    public void setListaVariablesFinanciero(List<VariablesRating> listaVariablesFinanciero) {
        this.listaVariablesFinanciero = listaVariablesFinanciero;
    }

    public List<VariablesRating> getListaVariablesFront() {
        return listaVariablesFront;
    }

    public void setListaVariablesFront(List<VariablesRating> listaVariablesFront) {
        this.listaVariablesFront = listaVariablesFront;
    }
    
}
