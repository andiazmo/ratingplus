package controladores;

import entidades.Estados;
import fachadas.EstadosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "estadosController")
@ViewScoped
public class EstadosController_1 extends AbstractController<Estados> {

    @EJB
    private EstadosFacade ejbFacade;

    /**
     * Initialize the concrete Estados controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
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

    public EstadosController_1() {
        // Inform the Abstract parent controller of the concrete Estados?cap_first Entity
        super(Estados.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of SubEstado entities that
     * are retrieved from Estados?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for SubEstado page
     */
    public String navigateSubEstadoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("SubEstado_items", this.getSelected().getSubEstadoCollection());
        }
        return "/subEstado/index";
    }

}
