package controladores;

import fachadas.AbstractFacade;
import controladores.util.JsfUtil;
import entidades.Auditoria;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import entidades.RpPermisos;
import entidades.RpSubmodulos;
import fachadas.AuditoriaFacade;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import session.UsuarioSeccion;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractController<T> implements Serializable {
      
     @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    
    protected EntityManager getEntityManager() {
        return em;
    }

       
    private AbstractFacade<T> ejbFacade;
    private Class<T> itemClass;
    private T selected;
    private Collection<T> items;
    private String crear = "hidden";
    private String eliminar = "hidden";
    private String modificar = "hidden";
    private String codigo ;
    private List<RpPermisos> permisos;
    private List<RpSubmodulos> submodulos;
    private String submodulo;
    @EJB
    private AuditoriaFacade ejbAuditoria;
    // FIXPACK1 - inicio
    private ResourceBundle myBundle = null;
    // FIXPACK1 - fin    
    
    private enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
        // FIXPACK1 - inicio
        this.myBundle = initBundle();
        // FIXPACK1 - fin
    }  

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }
  public AbstractController(Class<T> itemClass, String codigo) {
        this.itemClass = itemClass;
        this.codigo = codigo;
    }

    
    
    /**
     * Initialize the concrete controller bean. This AbstractController requires
     * the EJB Facade object for most operations, and that task is performed by
     * the concrete controller bean.
     */
    public abstract void init();

    /**
     * Retrieve the current EJB Facade object so that other beans in this
     * package can perform additional data layer tasks (e.g. additional queries)
     *
     * @return the concrete EJB Facade associated with the concrete controller
     * bean.
     */
    protected AbstractFacade<T> getFacade() {
        return ejbFacade;
    }

    /**
     * Sets the concrete EJB Facade object so that data layer actions can be
     * performed. This applies to all basic CRUD actions this controller
     * performs.
     *
     * @param ejbFacade the concrete EJB Facade to perform data layer actions
     * with
     */
    protected void setFacade(AbstractFacade<T> ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        this.selected = selected;
    }

    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public Collection<T> getItems() {
        if (items == null) {
            items = this.ejbFacade.findAll();
        }
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(Collection<T> items) {
        this.items = items;
    }
    
    /*
    Getter y Setter de las variables que brndan permisos de operacion
    */

    public String getCrear() {
        return crear;
    }

    public void setCrear(String crear) {
        this.crear = crear;
    }

    public String getEliminar() {
        return eliminar;
    }

    public void setEliminar(String eliminar) {
        this.eliminar = eliminar;
    }

    public String getModificar() {
        return modificar;
    }

    public void setModificar(String modificar) {
        this.modificar = modificar;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSubmodulo() {
        return submodulo;
    }

    public void setSubmodulo(String submodulo) {
        this.submodulo = submodulo;
    }
    
    
    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
        persist(PersistAction.UPDATE, msg);
    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    public void delete(ActionEvent event) {
//        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
//        persist(PersistAction.DELETE, msg);
//        if (!isValidationFailed()) {
//            selected = null; // Remove selection
//            items = null; // Invalidate list of items to trigger re-query.
//        }
    }
	
	
    public void borrarParam(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                     this.ejbAuditoria.guardaro(new Auditoria(JsfUtil.Usuario().getNombre(),""),selected);
                    this.ejbFacade.edit(selected);
                } else {
                      this.ejbAuditoria.borraro(new Auditoria(JsfUtil.Usuario().getNombre(),"" ), selected);
                    this.ejbFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }
    
    /*
    Manejo de permisos operacionales anivel de usuarios y perfiles
    */
    public void  getPermisos(){
          String rol;               
          //Rescata el rol del usuario que se encuentra loggeado
          FacesContext facesContext = FacesContext.getCurrentInstance();
          HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
          UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");             
          rol = seccion.getUsuario().getRol().getCodigo();
          
          //Asigna a una variable la entidad de permisos recuperada en la consulta
          permisos = ejbFacade.consultapermisos(rol, submodulo);
          if(permisos.size()> 0){
          if (permisos.get(0).getBorrar() == 1)
          {
           eliminar = "visible";
          }
          if (permisos.get(0).getGuardar() == 1)
          {
           crear = "visible";
          }
          if (permisos.get(0).getModificar() == 1)
          {
          modificar = "visible";
          }
          }
    }  
    
    // FIXPACK1 - inicio
    public ResourceBundle getMyBundle(){
        return initBundle();
    }
    
    private ResourceBundle initBundle(){
        if(this.myBundle==null)
            this.myBundle = ResourceBundle.getBundle("/MyBundle");        
        return this.myBundle;        
    }
    // FIXPACK1 - fin    
    
    
}
