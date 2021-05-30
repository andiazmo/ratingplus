package controladores;

import entidades.Logarchivos;
import fachadas.LogarchivosFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

@ManagedBean(name = "logarchivosController")
@ViewScoped
public class LogarchivosController extends AbstractController<Logarchivos> {

    @EJB
    private LogarchivosFacade ejbFacade;

    /**
     * Initialize the concrete Logarchivos controller bean. The
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
    }

    public LogarchivosController() {
        // Inform the Abstract parent controller of the concrete Logarchivos?cap_first Entity
        super(Logarchivos.class);
    }

   
   
}
