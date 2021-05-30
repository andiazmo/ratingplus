package controladores;

import entidades.Monedas;
import fachadas.MonedasFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "monedasController")
@ViewScoped
public class MonedasController extends AbstractController<Monedas> {

    @EJB
    private MonedasFacade ejbFacade;

    /**
     * Initialize the concrete Monedas controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public MonedasController() {
        // Inform the Abstract parent controller of the concrete Monedas?cap_first Entity
        super(Monedas.class);
    }

}
