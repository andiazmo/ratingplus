package controladores;

import entidades.Garantias;
import fachadas.GarantiasFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "garantiasController")
@ViewScoped
public class GarantiasController extends AbstractController<Garantias> {

    @EJB
    private GarantiasFacade ejbFacade;

    /**
     * Initialize the concrete Garantias controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public GarantiasController() {
        // Inform the Abstract parent controller of the concrete Garantias?cap_first Entity
        super(Garantias.class);
    }

}
