package controladores;

import entidades.Archivos;
import fachadas.ArchivosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean(name = "archivosController")
@ViewScoped
public class ArchivosController extends AbstractController<Archivos> {

    @EJB
    private ArchivosFacade ejbFacade;

    /**
     * Initialize the concrete Archivos controller bean. The AbstractController
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

    public ArchivosController() {
        // Inform the Abstract parent controller of the concrete Archivos?cap_first Entity
        super(Archivos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Logarchivos entities that
     * are retrieved from Archivos?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Logarchivos page
     */
    public String navigateLogarchivosList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Logarchivos_items", this.getSelected().getLogarchivosList());
        }
        return "/logarchivos/index";
    }

}
