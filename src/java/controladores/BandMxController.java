/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.BandMx;
import entidades.RpSubmodulos;
import entidades.TimeBandMx;
import fachadas.BandMxFacade;
import fachadas.RpSubmodulosFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author x236216
 */
@ManagedBean(name = "bandMxController")
@ViewScoped
public class BandMxController extends AbstractController<BandMx> implements Serializable {

    @EJB
    private BandMxFacade ejbFacade;

    public BandMxController() {
        super(BandMx.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public void guardar(ActionEvent event) {
        List<BandMx> listaBand = new ArrayList<BandMx>();
        Boolean isNew = Boolean.TRUE;
        listaBand = ejbFacade.findAll();
        if (!listaBand.isEmpty()) {
            for (BandMx bandMx : listaBand) {
                if (bandMx.getNumeroBanda().equals(getSelected().getNumeroBanda())) {
                    isNew = Boolean.FALSE;
                }
                if (bandMx.getTimeBandMx().getNodo().equals(getSelected().getTimeBandMx().getNodo())) {
                    isNew = Boolean.FALSE;
                }
            }
        }
        if (isNew) {
            saveNew(event);
        } else {
            JsfUtil.addErrorMessage("El número de la banda ya esta asociado a un nodo.");
        }

    }
}
