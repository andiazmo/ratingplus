/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.EcuacionMx;
import entidades.RpPermisos;
import entidades.RpSubmodulos;
import fachadas.EcuacionMxFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author x236216
 */
@ManagedBean(name = "ecuacionMxController")
@ViewScoped
public class EcuacionMxController extends AbstractController<EcuacionMx> {

    @EJB
    private EcuacionMxFacade ejbFacade;
    private EcuacionMx ecuacionMx;
    private Boolean isNew = Boolean.TRUE;
    private EcuacionMxController ecuacionMxController;

    @PostConstruct
    @Override
    public void init() {
        ecuacionMx = new EcuacionMx();
        this.setSelected(ecuacionMx);
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        consultarEcuacion();
        getPermisos();
    }

    public void consultarEcuacion() {
        try {
            this.ecuacionMx = ejbFacade.findEcuacion("Neteo");
            if (ecuacionMx != null) {
                isNew = Boolean.FALSE;
            } else {
                ecuacionMx = new EcuacionMx();
                isNew = Boolean.TRUE;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EcuacionMxController() {
        // Inform the Abstract parent controller of the concrete GruposEconomicos?cap_first Entity
        super(EcuacionMx.class);
    }

    public void guardar() throws Exception {
        try {

            FacesContext context = FacesContext.getCurrentInstance();
            ecuacionMxController = context.getApplication().
                    evaluateExpressionGet(context, "#{ecuacionMxController}",
                            EcuacionMxController.class);
            if (isNew) {
                this.ecuacionMx.setNombre("Neteo");
                this.ejbFacade.guardar(this.ecuacionMx);
            } else {
                modificar();
            }
            consultarEcuacion();
            JsfUtil.addSuccessMessage("Constantes Modificadas Correctamente");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Ocurrio un error inesperado por favor comuniquese con el administrador.");
            e.printStackTrace();
            throw new Exception("Ocurrio un error al intentar guardar la ecuacion por primera vez", e);
        }
    }

    public void modificar() throws Exception {
        try {
            this.ejbFacade.modificar(this.ecuacionMx);
        } catch (Exception e) {
            JsfUtil.addSuccessMessage("Ocurrio un error inesperado por favor comuniquese con el administrador.");
            e.printStackTrace();
            throw new Exception("Ocurrio un error al intentar modificar la ecuacion", e);
        }
    }

    public EcuacionMx getEcuacionMx() {
        return ecuacionMx;
    }

    public void setEcuacionMx(EcuacionMx ecuacionMx) {
        this.ecuacionMx = ecuacionMx;
    }

}
