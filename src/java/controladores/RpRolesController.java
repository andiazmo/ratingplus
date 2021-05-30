package controladores;

import entidades.RpRoles;
import fachadas.RpRolesFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "rpRolesController")
@ViewScoped
public class RpRolesController extends AbstractController<RpRoles> implements Serializable {

    @EJB
    private RpRolesFacade ejbFacade;

    public RpRolesController() {
        super(RpRoles.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }
    

    
}
