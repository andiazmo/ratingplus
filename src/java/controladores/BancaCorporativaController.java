/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.BancaCorporativa;
import fachadas.BancaCorporativaFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author x332015
 */
@ManagedBean(name = "bancaCorporativaController")
@ViewScoped
public class BancaCorporativaController extends AbstractController<BancaCorporativa> {

    @EJB
    private BancaCorporativaFacade ejbFacade;

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public BancaCorporativaController() {
        super(BancaCorporativa.class);
    }

}
