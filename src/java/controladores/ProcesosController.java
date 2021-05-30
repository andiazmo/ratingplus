/*
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 24-07-2018
 --------------------------------------------------------------------------------
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.Procesos;
import fachadas.ArchivosFacade;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.CuposFacade;
import fachadas.DesembolsosFacade;
import fachadas.HistoricoAccesoUsuarioFacade;
import fachadas.HistoricoClaveFacade;
import fachadas.LimitesautorizadosFacade;
import fachadas.LogarchivosFacade;
import fachadas.ParametrosFacade;
import fachadas.ProcesosFacade;
import fachadas.RpUsuariosFacade;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import org.quartz.JobDataMap;
import quartzutil.QuartzUtil;
import static utilidades.FileHelper.verificarRuta;

@ManagedBean(name = "procesosController")
@ViewScoped
public class ProcesosController extends AbstractController<Procesos> {
    
    @EJB
    private ProcesosFacade ejbFacade;
    @EJB
    private AuditoriaFacade ejbAuditoria;
    @EJB
    private CuposFacade ejbCupos;
    @EJB
    private LimitesautorizadosFacade ejbLimites;
    @EJB
    private DesembolsosFacade ejbDesembolsos;
    @EJB
    private ClientesFacade ejbClientes;
    @EJB
    private ArchivosFacade ejbArchivos;
    @EJB
    private LogarchivosFacade ejbLogArchivos;
    @EJB
    private ParametrosFacade ejbParametro;
    @EJB
    private HistoricoAccesoUsuarioFacade ejbHistoricoAcesso;
    @EJB
    private RpUsuariosFacade ejbRpUsuarios;
    @EJB
    private HistoricoClaveFacade ejbHistoricoClaveFacade;
    
    private List<Procesos> procesos;
    
    /**
     * Initialize the concrete Procesos controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        // FIXPACK2 - inicio
        this.procesos = this.ejbFacade.buscarTodosOrdenados();
        // FIXPACK2 - fin
        validarEstadosJobs();
    }
    
    private void validarEstadosJobs(){
        QuartzUtil.listarJobs();
        for(Procesos proceso: this.procesos){
            proceso.setEstado(QuartzUtil.getEstadoJob(proceso.getIdentificador().toString()));
            ejbFacade.modificar(proceso);
        }
    }
    
    public ProcesosController() {
        // Inform the Abstract parent controller of the concrete Procesos?cap_first Entity
        super(Procesos.class);
    }
    
    public List<Procesos> getProcesos() {
        return procesos;
    }
    
    public void setProcesos(List<Procesos> procesos) {
        this.procesos = procesos;
    }
    
    public void guardar(){
        try {
            this.ejbFacade.guardar(this.getSelected());
            QuartzUtil q = new QuartzUtil();
            JobDataMap map = new JobDataMap();
            map.put("ejbAuditoria", this.ejbAuditoria);
            map.put("ejbCupos",this.ejbCupos);
            map.put("ejbLimites", this.ejbLimites);
            map.put("ejbDesembolsos",this.ejbDesembolsos);
            map.put("ejbClientes",this.ejbClientes);
            map.put("ejbProcesos",this.ejbFacade);
            map.put("ejbArchivos",this.ejbArchivos);
            map.put("ejbLogArchivo", this.ejbLogArchivos);
            map.put("ejbParametro", this.ejbParametro);
            map.put("ejbRpUsuarios", this.ejbRpUsuarios);
            map.put("ejbHistoricoAcesso", this.ejbHistoricoAcesso);
            map.put("ejbHistoricoClaveFacade",this.ejbHistoricoClaveFacade);
            
            Class clase = Class.forName(this.getSelected().getClase());
            map.put("identificador",this.getSelected().getIdentificador().toString());
            q.lanzarTarea(q.creaJob(clase, this.getSelected().getIdentificador().toString(), map), this.getSelected().getCron());
            JsfUtil.addSuccessMessage("Proceso Guardado con Exito");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcesosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarNuevo(){
        this.ejbFacade.guardar(this.getSelected());
        JsfUtil.addSuccessMessage("Proceso Guardado con Exito");
    }
    
    public void modificar(){
        if(this.getSelected().getEstado()){
            JsfUtil.addErrorMessage("Detenga primero el proceso");
            return;
        }
        try {
            this.getSelected().setEstado(true);
            this.ejbFacade.modificar(this.getSelected());
            QuartzUtil q = new QuartzUtil();
            JobDataMap map = new JobDataMap();
            map.put("ejbAuditoria", this.ejbAuditoria);
            map.put("ejbCupos",this.ejbCupos);
            map.put("ejbLimites", this.ejbLimites);
            map.put("ejbDesembolsos",this.ejbDesembolsos);
            map.put("ejbClientes",this.ejbClientes);
            map.put("ejbProcesos",this.ejbFacade);
            map.put("ejbArchivos",this.ejbArchivos);
            map.put("ejbLogArchivo", this.ejbLogArchivos);
            map.put("identificador",this.getSelected().getIdentificador().toString());
            map.put("ejbParametro", this.ejbParametro);
            map.put("ejbRpUsuarios", this.ejbRpUsuarios);
            map.put("ejbHistoricoAcesso", this.ejbHistoricoAcesso);
            map.put("ejbHistoricoClaveFacade",this.ejbHistoricoClaveFacade);
            
            Class clase = Class.forName(this.getSelected().getClase());
            q.reLanzarTarea(q.creaJob(clase, this.getSelected().getIdentificador().toString(), map), this.getSelected().getCron(),this.getSelected().getIdentificador().toString());
            JsfUtil.addSuccessMessage("Proceso Modificado con Exito");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcesosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrar(){
        QuartzUtil q = new QuartzUtil();
        this.ejbFacade.borrar(this.getSelected());
        q.borrar(this.getSelected().getIdentificador().toString());
        JsfUtil.addSuccessMessage("Proceso Borrado Con Exito");
    }
    
    public void cambiarEstado(){
        // FIXPACK2 - inicio
        String resultado = "";
        // FIXPACK2 - fin
        if(this.getSelected().getEstado()){
            detener();
        }
        else{
             // FIXPACK2 - inicio
            /*this.getSelected().setEstado(true);
            this.ejbFacade.modificar(this.getSelected());
            lanzarJob(this.getSelected());*/
            resultado = lanzarJob(this.getSelected());
            if ("".equals(resultado)) {
                this.getSelected().setEstado(true);
                this.ejbFacade.modificar(this.getSelected());
            } else {
                JsfUtil.addErrorMessage(resultado);
            }
            // FIXPACK2 - fin
        }
    }
    
    private void detener(){
        QuartzUtil q = new QuartzUtil();
        q.borrar(this.getSelected().getIdentificador().toString());
        this.getSelected().setEstado(false);
        this.ejbFacade.modificar(this.getSelected());
        JsfUtil.addSuccessMessage("Proceso Detenido Con Exito");
    }
    
    public void modificarNuevo() {
        if(this.getSelected().getEstado()){
            JsfUtil.addErrorMessage("Detenga primero el proceso");
            return;
        }
        this.ejbFacade.modificar(this.getSelected());
        JsfUtil.addSuccessMessage("Proceso Modificado con Exito");
        
    }
    
   // FIXPACK2 - inicio
    /*private boolean lanzarJob(Procesos proceso){*/
    public static String lanzarJob(Procesos proceso){        
    // FIXPACK2 - fin
          String resultado = "";
        try{
            QuartzUtil q = new QuartzUtil();
            JobDataMap map = new JobDataMap();
            // FIXPACK2 - inicio
            /*map.put("fullPath", this.getSelected().getNombre());            
            Class clase = Class.forName(this.getSelected().getClase());*/
            if(!verificarRuta(proceso.getNombre()))
                throw new FileNotFoundException(proceso.getNombre());
            map.put("fullPath", proceso.getNombre());            
            Class clase = Class.forName(proceso.getClase());                        
            //q.reLanzarTarea(q.creaJob(clase, this.getSelected().getIdentificador().toString(), map), this.getSelected().getCron(),this.getSelected().getIdentificador().toString());
            q.reLanzarTarea(q.creaJob(clase, proceso.getIdentificador().toString(), map), proceso.getCron(),proceso.getIdentificador().toString());
            return resultado;
            // FIXPACK2 - fin
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcesosController.class.getName()).log(Level.SEVERE, null, ex);
            // FIXPACK2 - inicio
            resultado = "Error! No se encontro la clase job: " + ex.getMessage();
            return resultado;
        }  
        catch (FileNotFoundException ex){
            Logger.getLogger(ProcesosController.class.getName()).log(Level.SEVERE, null, ex);
            resultado ="Error! No se encontro el archivo: " + ex.getMessage();
            return resultado;
            // FIXPACK2 - fin            
        }
        }
        
}
