/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : 17_0170
 *Programados: Julio Beltran
 *Tag de cambio: CWS_SIAE_BOT
 *Fecha del cambio : 21-05-2018
 --------------------------------------------------------------------------------
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.Clientes;
import entidades.EstadoScan;
import entidades.Estados;
import entidades.EstadosCliente;
import entidades.HistoricoEstadoScan;
import entidades.HistoricoEstadosclientes;
import entidades.Limitesautorizados;
import entidades.SubEstado;
import entidades.SubEstadoScan;
import fachadas.ClientesFacade;
import fachadas.EstadoScanFacade;
import fachadas.EstadosClienteFacade;
import fachadas.EstadosFacade;
import fachadas.HistoricoEstadoScanFacade;
import fachadas.HistoricoEstadosClienteFacade;
import fachadas.LimitesautorizadosFacade;
import fachadas.SubEstadoFacade;
import fachadas.SubEstadoScanFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ws_cliente_siae.ResultadoWS;
import ws_cliente_siae.WS_Cliente_SIAE;

/**
 *
 * @author User
 */
@ManagedBean(name = "estadosClienteController")
@ViewScoped
public class EstadosClienteController extends AbstractController<EstadosCliente> {

    @EJB
    private EstadosClienteFacade ejbFacade;

    @EJB
    private ClientesFacade ejbClienteFacade;

    @EJB
    private EstadosFacade ejbEstadosFacade;

    @EJB
    private HistoricoEstadosClienteFacade ejbHistoricoEstadosClienteFacade;

    @EJB
    private EstadoScanFacade ejbEstadoScanFacade;

    @EJB
    private SubEstadoFacade ejbSubEstadoFacade;

    @EJB
    private HistoricoEstadoScanFacade ejbHistoricoEstadoScanFacade;

    @EJB
    private SubEstadoScanFacade ejbSubEstadoScanFacade;

    @EJB
    private LimitesautorizadosFacade ejbLimite;

    private List<SubEstadoScan> listsubestadoscan;
    private boolean subestadoscanFlag = true;
    private EstadoScan scan;
    private List<EstadosCliente> list;

    private Date fecha = new Date();
    private List<Estados> estadosList;
    private String descripcion = "";
    private Estados estado = new Estados();
    private SubEstado subestado = new SubEstado();

    private SubEstado subestadoSI = new SubEstado();

    private List<SubEstado> subestadosList;
    private final String variableScan = "SCAN";
    private SubEstadoScan subEstadoScan = new SubEstadoScan();
    private List<SubEstadoScan> subEstadosScanList;
    private Clientes clientes;
    private List<EstadoScan> estadosScanList;
    private EstadoScan estadoScan = new EstadoScan();

    private EstadosCliente selectedRow;

    private boolean subestadoFlag = false;
    private boolean subEstadoScanFlag = false;

    /**
     * Initialize the concrete EstadosCliente controller bean. The
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

        getIdParam(context);

        estadosList = ejbEstadosFacade.findAll();
        estado = estadosList.get(0);
        subestadosList = ejbSubEstadoFacade.buscar(estado);
        subestado = estado.getSubEstadoCollection().get(0);
        //subEstadosScanList = ejbSubEstadoScanFacade.findAll();
        estadosScanList = ejbEstadoScanFacade.findAll();
    }

    public void getIdParam(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if (params.containsKey("id")) {
            String id = params.get("id");
            load(id);
        }
    }

    public void load(String id) {
        clientes = ejbClienteFacade.find(id);
        list = ejbFacade.loadEstado(clientes);
    }

    public void guardarSI() {

        if (estado.getNombre().equalsIgnoreCase(variableScan)) {
            subestado.setNombre(estadoScan.getNombre());
            clientes.setFechascan(fecha);
        } else {
            subestado.setNombre(subestado.getNombre());
            clientes.setFechaestado(fecha);
            //clientes.setSubestado(subestado);
        }
        clientes.setEstadocliente(estado);
        ejbFacade.guardar(fecha, estado, descripcion, clientes, subestado);

        HistoricoEstadosclientes historico = null;
        if (estado.getNombre().equalsIgnoreCase(variableScan)) {
            ejbHistoricoEstadoScanFacade.guardar(new HistoricoEstadoScan(clientes.getNit(), clientes.getNombre(), estado.getNombre(), subestado.getNombre(), fecha));
            clientes.setScan(estadoScan);
            clientes.setSubestado_scan(subEstadoScan);
            historico = new HistoricoEstadosclientes(clientes.getNit(), clientes.getNombre(), clientes.getScan().getNombre(), clientes.getFechascan(), JsfUtil.Usuario().getNombre(), 2);
            this.ejbHistoricoEstadosClienteFacade.guardar(historico);

        }

        historico = new HistoricoEstadosclientes(clientes.getNit(), clientes.getNombre(), clientes.getEstadocliente().getNombre(), clientes.getFechaestado(), JsfUtil.Usuario().getNombre(), 1);
        this.ejbHistoricoEstadosClienteFacade.guardar(historico);

        ejbClienteFacade.guardar(clientes);

        //CWS_SIAE_BOT -inicio
        for (Limitesautorizados limite : this.clientes.getCuposList().get(0).getLimitesautorizadosList()) {
            if (this.ejbLimite.find(limite.getId()) != null) {
                WS_Cliente_SIAE ws = new WS_Cliente_SIAE();

                List<ResultadoWS> result = ws.generarPeticion("", JsfUtil.Usuario().getNombre(), limite.getId());
                if (result.size() > 0) {
                    for (ResultadoWS resultado : result) {
                        if (resultado.isExito()) {
                            JsfUtil.addSuccessMessage("NOTIFICACION EXITOSA DESDE CUPOS WEB AL SISTEMA BOT PARA EL CLIENTE CON NIT " + resultado.getNitCliente());
                        } else {
                            JsfUtil.addSuccessMessage("NO FUE EXITOSA LA NOTIFICACION DESDE CUPOS WEB AL SISTEMA BOT PARA EL CLIENTE CON NIT " + resultado.getNitCliente() + ". DETALLE ERROR:" + resultado.getTextoRespuesta());
                        }
                    }
                }
            }
        }
        //CWS_SIAE_BOT -fin 
        clear();

    }

    private void clear() {
        if (estado.getNombre().equalsIgnoreCase(variableScan)) {
            subEstadosScanList.clear();
            estadosScanList.clear();
        }
        fecha = new Date();
        estado = estadosList.get(0);
        subestado = estado.getSubEstadoCollection().get(0);
        subestadosList = ejbSubEstadoFacade.buscar(estado);
        subestadoFlag = false;
        subEstadoScanFlag = false;
        descripcion = "";

        load(clientes.getId());
    }

    public EstadosClienteController() {
        // Inform the Abstract parent controller of the concrete EstadosCliente?cap_first Entity
        super(EstadosCliente.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    public List<EstadosCliente> getList() {
        return list;
    }

    public List<Estados> getEstadosList() {
        if (estadosList == null || estadosList.isEmpty()) {
            estadosList = ejbEstadosFacade.findAll();
        }
        return estadosList;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public SubEstado getSubestado() {
        return subestado;
    }

    public void setSubestado(SubEstado subestado) {
        this.subestado = subestado;
    }

    public List<SubEstado> getSubestadosList() {
        if (subestadosList == null || subestadosList.isEmpty()) {
            subestadosList = ejbSubEstadoFacade.buscar(estado);
        }
        return subestadosList;
    }

    public void setSubestadosList(List<SubEstado> subestadosList) {
        this.subestadosList = subestadosList;
    }

    public void serviceChange() {
        subestadosList = ejbSubEstadoFacade.buscar(estado);
        subestadoFlag = true;
        subEstadoScanFlag = estado.getNombre().equalsIgnoreCase(variableScan);

    }

    public void serviceChange2() {
        scan = estadoScan;
        subEstadosScanList = ejbSubEstadoScanFacade.buscar(scan);
    }

    public boolean isSubestadoFlag() {
        return subestadoFlag;
    }

    public boolean isSubEstadoScanFlag() {
        return subEstadoScanFlag;
    }

    public void setSubEstadoScanFlag(boolean subEstadoScanFlag) {
        this.subEstadoScanFlag = subEstadoScanFlag;
    }

    public void borrar() {
        ejbFacade.remove(selectedRow);
        clear();
    }

    public EstadosCliente getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(EstadosCliente selectedRow) {
        this.selectedRow = selectedRow;
    }

    public SubEstadoScan getSubEstadoScan() {
        return subEstadoScan;
    }

    public void setSubEstadoScan(SubEstadoScan subEstadoScan) {
        this.subEstadoScan = subEstadoScan;
    }

    public List<SubEstadoScan> getSubEstadosScanList() {
        return subEstadosScanList;
    }

    public void setSubEstadosScanList(List<SubEstadoScan> subEstadosScanList) {
        this.subEstadosScanList = subEstadosScanList;
    }

    public List<EstadoScan> getEstadosScanList() {
        if (estadosScanList == null || estadosScanList.isEmpty()) {
            estadosScanList = ejbEstadoScanFacade.findAll();
        }
        return estadosScanList;
    }

    public void setEstadosScanList(List<EstadoScan> estadosScanList) {
        this.estadosScanList = estadosScanList;
    }

    public EstadoScan getEstadoScan() {
        return estadoScan;
    }

    public void setEstadoScan(EstadoScan estadoScan) {
        this.estadoScan = estadoScan;
    }

    public List<SubEstadoScan> getListsubestadoscan() {
        return listsubestadoscan;
    }

    public void setListsubestadoscan(List<SubEstadoScan> listsubestadoscan) {
        this.listsubestadoscan = listsubestadoscan;
    }

    public boolean isSubestadoscanFlag() {
        return subestadoscanFlag;
    }

    public void setSubestadoscanFlag(boolean subestadoscanFlag) {
        this.subestadoscanFlag = subestadoscanFlag;
    }

}
