/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 24-07-2018
 --------------------------------------------------------------------------------
 */
package session;

import static controladores.util.JsfUtil.fechaFormateada;
import entidades.HistoricoAccesoUsuario;
import entidades.RpModulos;
import entidades.RpSubmodulos;
import entidades.RpUsuarios;
import entidades.VersionesCw;
import fachadas.HistoricoAccesoUsuarioFacade;
import fachadas.RpModulosFacade;
import fachadas.RpSubmodulosFacade;
import fachadas.VersionesCwFacade;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;


/**
 *
 * @author ANGELICA M
 */
@ManagedBean(name = "rescateSeccion")
@RequestScoped
public class RescateSeccion {
    @EJB
    private RpModulosFacade ejbFacade;
    @EJB
    private RpSubmodulosFacade ejbSubmodulos;
    
    @EJB
    private HistoricoAccesoUsuarioFacade historicoAccesoUsuarioFacade;
    
    @EJB
    private VersionesCwFacade ejbVersionesCwFacade;
    
    /**
     * Creates a new instance of RescateSeccion
     */
    public RescateSeccion() {
        
    }
    
      public MenuModel Model (){ 
          FacesContext facesContext = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
          UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
          if(seccion != null){
            MenuModel model  = new DefaultMenuModel();  
            List<RpModulos> rps = (List<RpModulos>) ejbFacade.findAll();
            for(RpModulos rp : rps){  
               List<RpSubmodulos> sms = ejbSubmodulos.submodulosPorPerfilModulo(seccion.getUsuario().getRol().getCodigo(),rp.getCodigo());
              if(sms.size()> 0){
                Iterator it = sms.iterator();
                DefaultSubMenu submenu = new DefaultSubMenu();  
                submenu.setLabel(rp.getNombre());
                while(it.hasNext()){
                            Object[] obj = (Object[]) it.next();
                            DefaultMenuItem item = new DefaultMenuItem();  
                            item.setValue(obj[1]);  
                            item.setUrl(obj[2].toString()+"?v=" + obj[0]);
                            submenu.addElement(item);
                } 
                 model.addElement(submenu);  
                }
               }
            return model;
          }else{
              try {
                  session.setAttribute("seccion", null);
                  FacesContext contex = FacesContext.getCurrentInstance();
                  contex.getExternalContext().redirect( "/cupos/cupos/login.xhtml" );   
              } catch (IOException ex) {
                  Logger.getLogger(RescateSeccion.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
           return null;
    }  
    public String getUsuario(){
          FacesContext facesContext = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
          UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
          // FIXPACK2 - inicio
          return seccion.getUsuario().getNombres();
          // FIXPACK2 - inicio
    }     
        // FIXPACK2 - inicio
    public String getPerfil(){
          FacesContext facesContext = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
          UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
          return seccion.getUsuario().getRol().getNombre();
    }
    
    public String getUltimoAcceso() {
        HistoricoAccesoUsuario historicoAccesoUsuario = null;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");

        List<HistoricoAccesoUsuario> historicoAccesoUsuarios = historicoAccesoUsuarioFacade.buscarUltimoAcceso( (RpUsuarios)seccion.getUsuario() );
        if (historicoAccesoUsuarios.size() > 0) {
            historicoAccesoUsuario = historicoAccesoUsuarios.get(1);
        }
        return fechaFormateada(historicoAccesoUsuario.getFecha(),"dd/MM/yyyy");
    }
    
    public String getVersion() {
        String version = "1.0";
        ResourceBundle myBundle = ResourceBundle.getBundle("/MyBundle"); 
        int versionInt = Integer.valueOf(myBundle.getString("Version"));
        int subVersionInt = Integer.valueOf(myBundle.getString("Subversion"));
        VersionesCw versionesCw = ejbVersionesCwFacade.buscarXVersion(versionInt, subVersionInt);
        if (versionesCw != null) {
            version = versionesCw.getNumVersion() + "." + versionesCw.getNumSubversion();
        }
        return version;
    }
    // FIXPACK2 - fin
      
  public RpUsuarios Usuario(){
          FacesContext facesContext = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
          UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
          return seccion.getUsuario();
    }    
    
    public String getBrouser(){
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");

    if(userAgent.contains("MSIE 8.0")){ 
        return "Este navegador no es del todo compatible con la aplicacion y puede generar inconvenientes";
    }
    if(userAgent.contains("MSIE 9.0")){ 
        return "Este navegador no es del todo compatible con la aplicacion y puede generar inconvenientes";
    }
    /*if(userAgent.contains("Firefox")){ 
        return "Firefox";
    }
    if(userAgent.contains("Chrome")){ 
        return "Chrome";
    }
    if(userAgent.contains("Opera")){ 
        return "Opera";
    }
    if(userAgent.contains("Safari")){ 
        return "Safari";
    }*/
    return "";
   
    }
  
  
}