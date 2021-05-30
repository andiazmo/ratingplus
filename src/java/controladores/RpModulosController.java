package controladores;

import entidades.RpModulos;
import entidades.RpPermisos;
import entidades.RpSubmodulos;
import fachadas.RpModulosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;




@ManagedBean(name = "rpModulosController")
@ViewScoped
public class RpModulosController extends AbstractController<RpModulos> implements Serializable {

    @EJB
    private RpModulosFacade ejbFacade;
  
  
    
    public RpModulosController() {
        super(RpModulos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }
    
      public MenuModel Model (){
           MenuModel model  = new DefaultMenuModel();  
           List<RpModulos> rps = (List<RpModulos>) ejbFacade.findAll();
           for(RpModulos rp : rps){
               DefaultSubMenu submenu = new DefaultSubMenu();  
               submenu.setLabel(rp.getNombre());  
               List<RpSubmodulos> sms = (List<RpSubmodulos>) rp.getRpSubmodulosList();
               for(RpSubmodulos sm : sms){
                   List<RpPermisos> permisos = sm.getRpPermisosList();
                   for(RpPermisos permiso:permisos){
                       if(true){                        
                           DefaultMenuItem item = new DefaultMenuItem();  
                           item.setValue(sm.getNombre());  
                           item.setUrl(sm.getUrl());
                           submenu.addElement(item);
                       }
                   }
               
               }  
              model.addElement(submenu);  
           }
           return model;
       
        }
      
    }  


