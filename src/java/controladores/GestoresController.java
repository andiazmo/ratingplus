package controladores;

import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.Gestores;
import fachadas.AuditoriaFacade;
import fachadas.GestoresFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "gestoresController")
@ViewScoped
public class GestoresController extends AbstractController<Gestores> {

    @EJB
    private GestoresFacade ejbFacade;
    @EJB
    private AuditoriaFacade  ejbAuditoria;
    
    private List<Gestores> gestores;

    /**
     * Initialize the concrete Gestores controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
          this.gestores =this.ejbFacade.findAll();
        getPermisos();
    }

    public GestoresController() {
        // Inform the Abstract parent controller of the concrete Gestores?cap_first Entity
        super(Gestores.class);
    }

    public List<Gestores> getGestores() {
        return gestores;
    }

    public void setGestores(List<Gestores> gestores) {
        this.gestores = gestores;
    }

    
    
    
    public void guardar(){
                this.ejbFacade.guardar(this.getSelected());
                 this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(),"Se Creo el gestor: " + this.getSelected().getCedula()));
                 this.gestores =this.ejbFacade.findAll();
                 JsfUtil.addSuccessMessage("Registro Insertado con exito!");
    }
    
    public void modificar(){
                this.ejbFacade.modificar(this.getSelected());
                 this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(),"Se Modifico el gestor: " + this.getSelected().getCedula()));
                  this.gestores =this.ejbFacade.findAll();
                 JsfUtil.addSuccessMessage("Registro Modificado con exito!");
    }
    
     public void borrar(){
                this.ejbFacade.borrar(this.getSelected());
                 this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(),"Se borro el gestor: " + this.getSelected().getCedula()));
                  this.gestores =this.ejbFacade.findAll();
                 JsfUtil.addSuccessMessage("Registro borrado con exito!");
    }
     
    public String gestorByCedula(String cedula){
        List<Gestores> g = ejbFacade.gestorByCedula(cedula);
        if (g.isEmpty()) {
            return cedula;
        }
        return g.get(0).getNombre();
    }
    
}
