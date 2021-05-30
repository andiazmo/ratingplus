package controladores;

import entidades.EstadoScan;
import fachadas.EstadoScanFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

@ManagedBean(name = "estadoScanController")
@ViewScoped
public class EstadoScanController extends AbstractController<EstadoScan> {

    @EJB
    private EstadoScanFacade ejbFacade;

    /**
     * Initialize the concrete EstadoFeve controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
    }

    public EstadoScanController() {
        // Inform the Abstract parent controller of the concrete EstadoFeve?cap_first Entity
        super(EstadoScan.class);
    }

}
