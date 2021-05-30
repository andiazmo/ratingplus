package controladores;

import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.RpPermisos;
import fachadas.AuditoriaFacade;
import fachadas.RpPermisosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "rpPermisosController")
@ViewScoped
public class RpPermisosController extends AbstractController<RpPermisos> implements Serializable {

    @EJB
    private RpPermisosFacade ejbFacade;
    @EJB
    private  AuditoriaFacade ejbAuditoria;
    private boolean guardarb;
    private boolean modificarb;
    private boolean borrarb;

    public RpPermisosController() {
        super(RpPermisos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
      
    }
    
     public void guardar(){
        if(this.guardarb) this.getSelected().setGuardar(1);
        else this.getSelected().setGuardar(0);
        if(this.modificarb) this.getSelected().setModificar(1);
        else this.getSelected().setModificar(0);
        if(this.borrarb)this.getSelected().setBorrar(1);
        else this.getSelected().setBorrar(0);
         
        if(this.ejbFacade.guardar(this.getSelected())==1){ JsfUtil.addSuccessMessage("Reguistro Guardado con exito");
         this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(),"Se creo el permiso para el rol: " + this.getSelected().getRol().getNombre() + "Y el modulo: " + this.getSelected().getModulo().getNombre() ));
        }else{ JsfUtil.addErrorMessage("Ya el permiso existe para este rol y modulo modifique");}
    }
   
    public void modificar(){
        if(this.guardarb) this.getSelected().setGuardar(1);
        else this.getSelected().setGuardar(0);
        if(this.modificarb) this.getSelected().setModificar(1);
        else this.getSelected().setModificar(0);
        if(this.borrarb)this.getSelected().setBorrar(1);
        else this.getSelected().setBorrar(0);
        if(this.ejbFacade.modificar(this.getSelected())==1){ JsfUtil.addSuccessMessage("Reguistro Modificado con exito");
         this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(),"Se modifico el permiso para el rol: " + this.getSelected().getRol().getNombre() + "Y el modulo: " + this.getSelected().getModulo().getNombre() ));
        }else{ JsfUtil.addErrorMessage("Ya el permiso existe para este rol y modulo modifique");}
    }

    public boolean isGuardarb() {
        return guardarb;
    }

    public void setGuardarb(boolean guardarb) {
        this.guardarb = guardarb;
    }

    public boolean isModificarb() {
        return modificarb;
    }

    public void setModificarb(boolean modificarb) {
        this.modificarb = modificarb;
    }

    public boolean isBorrarb() {
        return borrarb;
    }

    public void setBorrarb(boolean borrarb) {
        this.borrarb = borrarb;
    }

   public void seleccionar(){
       if(this.getSelected().getGuardar() == 1) this.guardarb=true;
        else this.guardarb=false;
        if(this.getSelected().getModificar() == 1) this.modificarb=true;
        else this.modificarb=false;
        if(this.getSelected().getBorrar() == 1) this.borrarb=true;
        else this.borrarb=false;
   } 
     
}
