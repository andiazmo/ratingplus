package controladores;

import entidades.Clientes;
import entidades.EstadosReconduccion;
import entidades.Reconduccion;
import fachadas.ClientesFacade;
import fachadas.EstadosReconduccionFacade;
import fachadas.ReconduccionFacade;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "estadosReconduccionController")
@ViewScoped
public class EstadosReconduccionController extends AbstractController<EstadosReconduccion> {

    @EJB
    private EstadosReconduccionFacade ejbFacade;
    private ClientesController idClienteController;
    private ReconduccionController idReconduccionController;
    
    @EJB
    private ClientesFacade ejbClienteFacade;
    
    @EJB
    private ReconduccionFacade ejbReconduccionFacade;
    
    private List<EstadosReconduccion> list;
    
    private Date fecha = new Date();
    private List<Reconduccion> reconduccionList;
    private String descripcion = "";
    private Reconduccion reconduccion = new Reconduccion();
    private Clientes clientes;
    
    private EstadosReconduccion selectedRow;

    /**
     * Initialize the concrete EstadosReconduccion controller bean. The
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
        FacesContext context = FacesContext.getCurrentInstance();
        idClienteController = context.getApplication().evaluateExpressionGet(context, "#{clientesController}", ClientesController.class);
        idReconduccionController = context.getApplication().evaluateExpressionGet(context, "#{reconduccionController}", ReconduccionController.class);
        
        getIdParam(context);
        reconduccionList = ejbReconduccionFacade.findAll();
        
        reconduccion = reconduccionList.get(0);
    }
    
    public void getIdParam(FacesContext fc){
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        if(params.containsKey("id")){
            String id = params.get("id");
            load(id);
        }
    }
    
    public void load(String id){
        clientes = ejbClienteFacade.find(id);
        list = ejbFacade.loadEstado(clientes);
        
    }
    
    public void guardar(){
        ejbFacade.guardar(fecha, reconduccion, descripcion, clientes);
        
        clientes.setReconduccion(reconduccion);
        
        ejbClienteFacade.guardar(clientes);
        clear();
    }
    
    private void clear(){
        fecha = new Date();
        reconduccion = reconduccionList.get(0);
        descripcion = "";
        load(clientes.getId());
    }
    

    public EstadosReconduccionController() {
        // Inform the Abstract parent controller of the concrete EstadosReconduccion?cap_first Entity
        super(EstadosReconduccion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idClienteController.setSelected(null);
        idReconduccionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Clientes controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCliente(ActionEvent event) {
        if (this.getSelected() != null && idClienteController.getSelected() == null) {
            idClienteController.setSelected(this.getSelected().getIdCliente());
        }
    }

    /**
     * Sets the "selected" attribute of the Reconduccion controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdReconduccion(ActionEvent event) {
        if (this.getSelected() != null && idReconduccionController.getSelected() == null) {
            idReconduccionController.setSelected(this.getSelected().getIdReconduccion());
        }
    }

    public List<EstadosReconduccion> getList() {
        return list;
    }

    public void setList(List<EstadosReconduccion> list) {
        this.list = list;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Reconduccion> getReconduccionList() {
        return reconduccionList;
    }

    public void setReconduccionList(List<Reconduccion> reconduccionList) {
        this.reconduccionList = reconduccionList;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Reconduccion getReconduccion() {
        return reconduccion;
    }

    public void setReconduccion(Reconduccion reconduccion) {
        this.reconduccion = reconduccion;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    
    public void borrar(){
        ejbFacade.remove(selectedRow);
        clear();
    }

    public EstadosReconduccion getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(EstadosReconduccion selectedRow) {
        this.selectedRow = selectedRow;
    }
    
    
}
