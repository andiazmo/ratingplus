package controladores;

import entidades.Bancas;
import fachadas.BancasFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "bancasController")
@ViewScoped
public class BancasController extends AbstractController<Bancas> {

    @EJB
    private BancasFacade ejbFacade;

    /**
     * Initialize the concrete Bancas controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public BancasController() {
        // Inform the Abstract parent controller of the concrete Bancas?cap_first Entity
        super(Bancas.class);
    }

}
