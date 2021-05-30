package controladores;

import entidades.RpSubmodulos;
import fachadas.RpSubmodulosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "rpSubmodulosController")
@ViewScoped
public class RpSubmodulosController extends AbstractController<RpSubmodulos> implements Serializable {

    @EJB
    private RpSubmodulosFacade ejbFacade;

    public RpSubmodulosController() {
        super(RpSubmodulos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }
    
    public List<RpSubmodulos> Submodulos(String rol,String modulo){
        return (List<RpSubmodulos>) ejbFacade.submodulosPorPerfilModulo(rol, modulo);
    }
}
