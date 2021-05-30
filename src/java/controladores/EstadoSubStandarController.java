/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.EstadoSubstandar;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.EstadoSubstandarFacade;
import fachadas.EstadosSubstandarFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author x332015
 */
@ManagedBean(name = "estadoSubStandarController")
@ViewScoped
public class EstadoSubStandarController extends AbstractController<EstadoSubstandar> {

    @EJB
    private EstadoSubstandarFacade ejbFacade;

    @EJB
    private EstadosSubstandarFacade ejbEstadosSubStandarFacade;

    @EJB
    private ClientesFacade ejbClienteFacade;
    
    @EJB
    private AuditoriaFacade ejbAuditoriaFacade;

    private List<EstadoSubstandar> listEstados;
    private EstadoSubstandar selectedRow;

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        listEstados = ejbFacade.findAll();
    }

    // METODOS
    public List<EstadoSubstandar> getEstadosList() {
        if (listEstados == null || listEstados.isEmpty()) {
            listEstados = ejbFacade.findAll();
        }
        return listEstados;
    }

    public void limpiarFormCreate() {
        this.getSelected().setNombre("");
        this.getSelected().setId("");
    }

    public void crearEstadoSubStandar() {
        List<EstadoSubstandar> estado = this.ejbFacade.estadoList(this.getSelected().getId());
        if (!estado.isEmpty()) {
            listEstados = ejbFacade.findAll();
            JsfUtil.addErrorMessage("El ID ya existe, prueba con otro.");
        } else {
            this.ejbFacade.create(this.getSelected());
            this.ejbAuditoriaFacade.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Nuevo estado SubStandar Creado ID: " + this.getSelected().getId() + " Nombre: " + this.getSelected().getNombre()));
            listEstados = ejbFacade.findAll();
            JsfUtil.addSuccessMessage("Estado SubStandar Creado Correctamente.");
        }
    }

    public void editarEstadoSubStandar() {
        if (this.getSelected().getNombre().trim().isEmpty()) {
            JsfUtil.addSuccessMessage("Se debe Colocar un Nombre.");
        } else {
            String nombreAnterior = ejbFacade.estadoList(this.getSelected().getId()).get(0).getNombre();
            ejbFacade.edit(this.getSelected());
            this.ejbAuditoriaFacade.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se modifico el nombre del estado SubStandar de " + nombreAnterior + " a " + this.getSelected().getNombre()));
            JsfUtil.addSuccessMessage("Se modifico el estado correctamente.");
        }
    }

    public void preparar() {
        this.setSelected(new EstadoSubstandar());
    }
    //GETTERS AND SETTERS

    public List<EstadoSubstandar> getListEstados() {
        return listEstados;
    }

    public void setListEstados(List<EstadoSubstandar> listEstados) {
        this.listEstados = listEstados;
    }

    public EstadoSubstandar getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(EstadoSubstandar selectedRow) {
        this.selectedRow = selectedRow;
    }

}
