package controladores;

import entidades.Auditoria;
import fachadas.AuditoriaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

@ManagedBean(name = "auditoriaController")
@ViewScoped
public class AuditoriaController extends AbstractController<Auditoria> {

    @EJB
    private AuditoriaFacade ejbFacade;

    /**
     * Initialize the concrete Auditoria controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public AuditoriaController() {
        // Inform the Abstract parent controller of the concrete Auditoria?cap_first Entity
        super(Auditoria.class);
    }

}
