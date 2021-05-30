package controladores;

import entidades.Tasas;
import fachadas.TasasFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "tasasController")
@ViewScoped
public class TasasController extends AbstractController<Tasas> {

    @EJB
    private TasasFacade ejbFacade;

    /**
     * Initialize the concrete Tasas controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public TasasController() {
        // Inform the Abstract parent controller of the concrete Tasas?cap_first Entity
        super(Tasas.class);
    }

}
