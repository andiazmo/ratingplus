/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.Clientes;
import entidades.EstadoSubstandar;
import entidades.EstadosSubstandar;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.EstadoSubstandarFacade;
import fachadas.EstadosSubstandarFacade;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author x332015
 */
@ManagedBean(name = "estadosSubStandarController")
@ViewScoped
public class EstadosSubStandarController extends AbstractController<EstadosSubstandar> {

    @EJB
    private EstadosSubstandarFacade ejbFacade;

    @EJB
    private EstadoSubstandarFacade ejbEstadoSubStandarFacade;

    @EJB
    private ClientesFacade ejbClienteFacade;

    @EJB
    private AuditoriaFacade ejbAuditoriaFacade;

    private Clientes clientes;
    private List<EstadosSubstandar> list;
    private List<EstadoSubstandar> listEstados;
    private EstadoSubstandar estadoSubStandar = new EstadoSubstandar();

    private Date fecha = new Date();
    private String descripcion = "";
    private EstadosSubstandar selectedRow;

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
        getIdParam(context);
        listEstados = ejbEstadoSubStandarFacade.findAll();
        estadoSubStandar = listEstados.get(0);
    }

    // METODOS
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

    public List<EstadoSubstandar> getEstadosList() {
        if (listEstados == null || listEstados.isEmpty()) {
            listEstados = ejbEstadoSubStandarFacade.findAll();
        }
        return listEstados;
    }

    public void guardar() {
        clientes.setEstadoSubstandar(estadoSubStandar);
        ejbFacade.guardar(fecha, estadoSubStandar, descripcion, clientes);
        ejbClienteFacade.guardar(clientes);
        list = ejbFacade.loadEstado(clientes);
        this.ejbAuditoriaFacade.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se Asigno el estado SubStandar: " + estadoSubStandar.getNombre() + " al Cliente con NIT: " + clientes.getNit()));
        clear();
        JsfUtil.addSuccessMessage("Se guardo Correctamente");
    }

    public void borrar() {
        this.ejbFacade.remove(selectedRow);
        this.ejbAuditoriaFacade.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se borro el estado SubStandar: " + selectedRow.getEstadoSubstandar().getNombre() + " al Cliente con NIT: " + selectedRow.getCliente().getNit()));
        clear();
        JsfUtil.addSuccessMessage("Borrado Exitoso");
    }

    private void clear() {
        fecha = new Date();
        estadoSubStandar = listEstados.get(0);
        descripcion = "";
        load(clientes.getId());
    }

    //GETTER - SETTER
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoSubstandar getEstadoSubStandar() {
        return estadoSubStandar;
    }

    public void setEstadoSubStandar(EstadoSubstandar estadoSubStandar) {
        this.estadoSubStandar = estadoSubStandar;
    }

    public List<EstadoSubstandar> getListEstados() {
        return listEstados;
    }

    public void setListEstados(List<EstadoSubstandar> listEstados) {
        this.listEstados = listEstados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public List<EstadosSubstandar> getList() {
        return list;
    }

    public void setList(List<EstadosSubstandar> list) {
        this.list = list;
    }

    public EstadosSubstandar getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(EstadosSubstandar selectedRow) {
        this.selectedRow = selectedRow;
    }

}
