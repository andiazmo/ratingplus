package controladores;

import entidades.Pagos;
import fachadas.PagosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "pagosController")
@ViewScoped
public class PagosController extends AbstractController<Pagos> {

    @EJB
    private PagosFacade ejbFacade;

    /**
     * Initialize the concrete Pagos controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public PagosController() {
        // Inform the Abstract parent controller of the concrete Pagos?cap_first Entity
        super(Pagos.class);
    }

}
