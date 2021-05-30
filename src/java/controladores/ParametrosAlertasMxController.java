/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.ParametrosAlertasMx;
import fachadas.ParametrosAlertasMxFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author x217287
 */
@ManagedBean(name = "parametrosAlertasMxController")
@ViewScoped
public class ParametrosAlertasMxController extends AbstractController<ParametrosAlertasMx> implements Serializable {

    @EJB
    private ParametrosAlertasMxFacade ejbFacade;

    public ParametrosAlertasMxController() {
        super(ParametrosAlertasMx.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }


}
