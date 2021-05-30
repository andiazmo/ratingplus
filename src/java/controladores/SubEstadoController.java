package controladores;

import entidades.SubEstado;
import fachadas.SubEstadoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "subEstadoController")
@ViewScoped
public class SubEstadoController extends AbstractController<SubEstado> {

    @EJB
    private SubEstadoFacade ejbFacade;
    private EstadosController idEstadoController;

    /**
     * Initialize the concrete SubEstado controller bean. The AbstractController
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
        idEstadoController = context.getApplication().evaluateExpressionGet(context, "#{estadosController}", EstadosController.class);
    }

    public SubEstadoController() {
        // Inform the Abstract parent controller of the concrete SubEstado?cap_first Entity
        super(SubEstado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idEstadoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Estados controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEstado(ActionEvent event) {
        if (this.getSelected() != null && idEstadoController.getSelected() == null) {
            idEstadoController.setSelected(this.getSelected().getIdEstado());
        }
    }
}
