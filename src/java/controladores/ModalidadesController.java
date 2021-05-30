package controladores;

import entidades.Modalidades;
import fachadas.ModalidadesFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "modalidadesController")
@ViewScoped
public class ModalidadesController extends AbstractController<Modalidades> {

    @EJB
    private ModalidadesFacade ejbFacade;

    /**
     * Initialize the concrete Modalidades controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public ModalidadesController() {
        // Inform the Abstract parent controller of the concrete Modalidades?cap_first Entity
        super(Modalidades.class);
    }

}
