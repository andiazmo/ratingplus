package controladores;

import entidades.Periodos;
import fachadas.PeriodosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "periodosController")
@ViewScoped
public class PeriodosController extends AbstractController<Periodos> {

    @EJB
    private PeriodosFacade ejbFacade;

    /**
     * Initialize the concrete Periodos controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public PeriodosController() {
        // Inform the Abstract parent controller of the concrete Periodos?cap_first Entity
        super(Periodos.class);
    }

}
