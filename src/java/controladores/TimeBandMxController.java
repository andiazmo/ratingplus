/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.TimeBandMx;
import fachadas.TimeBandMxFacade;
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
@ManagedBean(name = "timeBandMxController")
@ViewScoped
public class TimeBandMxController extends AbstractController<TimeBandMx> implements Serializable {

    @EJB
    private TimeBandMxFacade ejbFacade;

    public TimeBandMxController() {
        super(TimeBandMx.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public void guardar(ActionEvent event) {
        List<TimeBandMx> listaTime = new ArrayList<TimeBandMx>();
        Boolean isNew = Boolean.TRUE;
        listaTime = ejbFacade.findAll();
        if (!listaTime.isEmpty()) {
            for (TimeBandMx bandMx : listaTime) {
                if (bandMx.getNodo().equals(getSelected().getNodo())) {
                    isNew = Boolean.FALSE;
                }
            }
        }
        if (isNew) {
            saveNew(event);
        } else {
            JsfUtil.addErrorMessage("El nodo de la TimeBand ya esta agregado.");
        }

    }

}
