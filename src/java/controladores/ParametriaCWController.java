/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.ParametriaCw;
import fachadas.ParametriaCWFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import constantes.*;

/**
 *
 * @author x302266
 */
@ManagedBean(name = "parametriaCWController")
@RequestScoped
public class ParametriaCWController extends AbstractController<ParametriaCw> {

    @EJB
    private ParametriaCWFacade parametriaFacade;

    private String codigoGrupo = Constantes.CODIGO_TABLA_GRUPO;
    private String codigoPais = Constantes.CODIGO_TABLA_PAIS;
    private String codigoActivadad = Constantes.CODIGO_TABLA_ACTIVIDAD;
    private String codigoSucursal = Constantes.CODIGO_TABLA_SUCURSAL;
    private String codigoFormaJuridica = Constantes.CODIGO_TABLA_FORMA_JURIDICA;
    private String codigoSectorEconomico = Constantes.CODIGO_TABLA_SECTOR_ECONOMICO;
    private String codigoOperacion = Constantes.CODIGO_MODO_OPERACION;
    private String codigoEntidadPais = Constantes.CODIGO_ENTIDAD_PAIS;
    private String codigoTipoVinculo = Constantes.CODIGO_TABLA_TIPO_VINCULO;
    private String codigoTipoRelacion = Constantes.CODIGO_TABLA_TIPO_RELACION;
    private String codigoRolJerarquico = Constantes.CODIGO_TABLA_ROL_JERARQUICO;

    @PostConstruct
    @Override
    public void init() {
        //super.setFacade(parametriaFacade);

    }

    public ParametriaCWFacade getParametriaFacade() {
        return parametriaFacade;
    }

    public void setParametriaFacade(ParametriaCWFacade parametriaFacade) {
        this.parametriaFacade = parametriaFacade;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoActivadad() {
        return codigoActivadad;
    }

    public void setCodigoActivadad(String codigoActivadad) {
        this.codigoActivadad = codigoActivadad;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getCodigoFormaJuridica() {
        return codigoFormaJuridica;
    }

    public void setCodigoFormaJuridica(String codigoFormaJuridica) {
        this.codigoFormaJuridica = codigoFormaJuridica;
    }

    public String getCodigoSectorEconomico() {
        return codigoSectorEconomico;
    }

    public void setCodigoSectorEconomico(String codigoSectorEconomico) {
        this.codigoSectorEconomico = codigoSectorEconomico;
    }

    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public String getCodigoEntidadPais() {
        return codigoEntidadPais;
    }

    public void setCodigoEntidadPais(String codigoEntidadPais) {
        this.codigoEntidadPais = codigoEntidadPais;
    }

    public String getCodigoTipoVinculo() {
        return codigoTipoVinculo;
    }

    public void setCodigoTipoVinculo(String codigoTipoVinculo) {
        this.codigoTipoVinculo = codigoTipoVinculo;
    }

    public String getCodigoTipoRelacion() {
        return codigoTipoRelacion;
    }

    public void setCodigoTipoRelacion(String codigoTipoRelacion) {
        this.codigoTipoRelacion = codigoTipoRelacion;
    }

    public String getCodigoRolJerarquico() {
        return codigoRolJerarquico;
    }

    public void setCodigoRolJerarquico(String codigoRolJerarquico) {
        this.codigoRolJerarquico = codigoRolJerarquico;
    }

    public List<ParametriaCw> listTablaP(String tabla) {
        List<ParametriaCw> list = parametriaFacade.findAll();
        List<ParametriaCw> listParametria = new ArrayList<>();

        for (ParametriaCw listP : list) {
            if (listP.getId().getTabla().equals(tabla)) {
                listParametria.add(listP);
            }
        }

        return listParametria;
    }

    public String definirParametria(String clave, String tabla, Boolean codigo) {
        List<ParametriaCw> parametria = parametriaFacade.parametriaPorClave(clave, tabla);
        if (parametria.isEmpty()) {
            return clave;
        }
        String nombreCode = "";
        if (codigo) {
            nombreCode = parametria.get(0).getId().getClave() + " - " + parametria.get(0).getDatos();
        }else{
            nombreCode = parametria.get(0).getDatos();
        }
        
        return nombreCode;
    }

}
