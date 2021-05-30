package controladores;

import controladores.util.JsfUtil;
import entidades.Estados;
import fachadas.EstadosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "estadosController")
@ViewScoped
public class EstadosController extends AbstractController<Estados> {

    @EJB
    private EstadosFacade ejbFacade;

    /**
     * Initialize the concrete Estados controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public EstadosController() {
        // Inform the Abstract parent controller of the concrete Estados?cap_first Entity
        super(Estados.class);
    }

    @Override
    public void delete(ActionEvent event) {
        JsfUtil.addSuccessMessage("Imposible borrar este registros");
    }

}
