package controladores;

import entidades.Reconduccion;
import fachadas.ReconduccionFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "reconduccionController")
@ViewScoped
public class ReconduccionController extends AbstractController<Reconduccion> {

    @EJB
    private ReconduccionFacade ejbFacade;

    /**
     * Initialize the concrete Reconduccion controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
    }

    public ReconduccionController() {
        // Inform the Abstract parent controller of the concrete Reconduccion?cap_first Entity
        super(Reconduccion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of EstadosReconduccion
     * entities that are retrieved from Reconduccion?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for EstadosReconduccion page
     */
    public String navigateEstadosReconduccionCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstadosReconduccion_items", this.getSelected().getEstadosReconduccionCollection());
        }
        return "/estadosReconduccion/index";
    }

    /**
     * Sets the "items" attribute with a collection of Clientes entities that
     * are retrieved from Reconduccion?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Clientes page
     */
    public String navigateClientesCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Clientes_items", this.getSelected().getClientesCollection());
        }
        return "/clientes/index";
    }

}
