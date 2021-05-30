package controladores;

import entidades.HistoricoClave;
import entidades.RpUsuarios;
import fachadas.HistoricoClaveFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "historicoClaveController")
@ViewScoped
public class HistoricoClaveController extends AbstractController<HistoricoClave> {

    @EJB
    private HistoricoClaveFacade ejbFacade;
    private RpUsuariosController usuarioController;

    /**
     * Initialize the concrete HistoricoClave controller bean. The
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
        usuarioController = context.getApplication().evaluateExpressionGet(context, "#{rpUsuariosController}", RpUsuariosController.class);
    }

    public HistoricoClaveController() {
        // Inform the Abstract parent controller of the concrete HistoricoClave?cap_first Entity
        super(HistoricoClave.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        usuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the RpUsuarios controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsuario(ActionEvent event) {
        if (this.getSelected() != null && usuarioController.getSelected() == null) {
            usuarioController.setSelected(this.getSelected().getUsuario());
        }
    }

    public List<HistoricoClave> buscarUltimos5(RpUsuarios rpUsuarios) {
        return ejbFacade.buscarUltimos5(rpUsuarios);
    }
}
