package controladores;

import controladores.util.JsfUtil;
import constantes.*;
import entidades.Auditoria;
import entidades.GruposEconomicos;
import entidades.ParametriaCw;
import entidades.Parametros;
import entidades.RelacionClienteGrupo;
import entidades.RpUsuarios;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.GruposEconomicosFacade;
import fachadas.LimitesautorizadosFacade;
import fachadas.ParametrosFacade;
import fachadas.RelacionClienteGrupoFacade;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import session.UsuarioSeccion;

@ManagedBean(name = "gruposEconomicosController")
@ViewScoped
public class GruposEconomicosController extends AbstractController<GruposEconomicos> {

    @EJB
    private GruposEconomicosFacade ejbFacade;
    @EJB
    private ClientesFacade ejbClientes;
    @EJB
    private RelacionClienteGrupoFacade relacionFacade;
    @EJB
    private LimitesautorizadosFacade limiteFacade;
    @EJB
    private ParametrosFacade parametrosFacade;
    @EJB
    private AuditoriaFacade ejbAuditoria;

    private GruposEconomicos gruposEntity = new GruposEconomicos();

    private List<GruposEconomicos> grupos = new ArrayList<>();
    private List<RelacionClienteGrupo> relacion;

    //Validaciones de Codigo
    List<GruposEconomicos> grupoConsecutivo;
    List<GruposEconomicos> grupoNoConsecutivo;

    private Boolean renderedCodigo = true;

    private Boolean habilitarBoton = true;

    private String mensaje;

    private Boolean habilitarBotonBorrado = true;

    private Boolean deshabilitarBotonNoAsignado = false;

    private String nombreGrupoReservado = Constantes.NOMBRE_RESERVADO_GRUPO;

    private Boolean activarGarantia;

    /**
     * Initialize the concrete GruposEconomicos controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        this.grupos = ejbFacade.findAll();
        this.relacion = relacionFacade.findAll();

        getPermisos();
    }

    public GruposEconomicosController() {
        // Inform the Abstract parent controller of the concrete GruposEconomicos?cap_first Entity
        super(GruposEconomicos.class);
    }

    public List<GruposEconomicos> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GruposEconomicos> grupos) {
        this.grupos = grupos;
    }

    public Boolean getRenderedCodigo() {
        return renderedCodigo;
    }

    public void setRenderedCodigo(Boolean renderedCodigo) {
        this.renderedCodigo = renderedCodigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getHabilitarBoton() {
        return habilitarBoton;
    }

    public void setHabilitarBoton(Boolean habilitarBoton) {
        this.habilitarBoton = habilitarBoton;
    }

    public Boolean getHabilitarBotonBorrado() {
        return habilitarBotonBorrado;
    }

    public void setHabilitarBotonBorrado(Boolean habilitarBotonBorrado) {
        this.habilitarBotonBorrado = habilitarBotonBorrado;
    }

    public Boolean getDeshabilitarBotonNoAsignado() {
        return deshabilitarBotonNoAsignado;
    }

    public void setDeshabilitarBotonNoAsignado(Boolean deshabilitarBotonNoAsignado) {
        this.deshabilitarBotonNoAsignado = deshabilitarBotonNoAsignado;
    }

    public String getNombreGrupoReservado() {
        return nombreGrupoReservado;
    }

    public void setNombreGrupoReservado(String nombreGrupoReservado) {
        this.nombreGrupoReservado = nombreGrupoReservado;
    }

    public List<GruposEconomicos> getGrupoConsecutivo() {
        for (GruposEconomicos grupo : grupos) {
            if (grupo.getConsecutivo().equals(true)) {
                grupoConsecutivo.add(grupo);
            }
        }
        return grupoConsecutivo;
    }

    public void setGrupoConsecutivo(List<GruposEconomicos> grupoConsecutivo) {
        this.grupoConsecutivo = grupoConsecutivo;
    }

    public List<GruposEconomicos> getGrupoNoConsecutivo() {
        return grupoNoConsecutivo;
    }

    public void setGrupoNoConsecutivo(List<GruposEconomicos> grupoNoConsecutivo) {
        this.grupoNoConsecutivo = grupoNoConsecutivo;
    }

    public Boolean getActivarGarantia() {
        return activarGarantia;
    }

    public void setActivarGarantia(Boolean activarGarantia) {
        this.activarGarantia = activarGarantia;
    }

    public void guardar() {
        this.ejbFacade.guardar(this.getSelected());
        this.grupos = this.ejbFacade.findAll();
    }

    public void modificar() {
        this.ejbFacade.modificar(this.getSelected());
        this.grupos = this.ejbFacade.findAll();

    }

    public void borrar() {
        this.ejbFacade.borrar(this.getSelected());
        this.grupos = this.ejbFacade.findAll();
    }

    public RpUsuarios getUsuario() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        UsuarioSeccion seccion = (UsuarioSeccion) session.getAttribute("seccion");
        // FIXPACK2 - inicio
        return seccion.getUsuario();
        // FIXPACK2 - inicio
    }

    public void preparar() {
        this.setSelected(new GruposEconomicos());
        this.getSelected().setCupo(Double.parseDouble(this.ejbClientes.limiteGlobal().getValor()));
        this.getSelected().setDisponibleGrupo(Double.parseDouble(this.ejbClientes.limiteGlobal().getValor()));
        Timestamp time = new Timestamp(System.currentTimeMillis());
        this.getSelected().setFechaCreacion(time);
        this.getSelected().setConsumido(0.0);
        this.getSelected().setUsuarioCreador(getUsuario().getNombre());
    }

    void modificarPatrimonio(String valorAnterior, String valorActual) {
        for (GruposEconomicos grupo : grupos) {
            if (grupo.getCupo().toString().equals(valorAnterior)) {
                grupo.setCupo(Double.valueOf(valorActual));
                ejbFacade.modificar(grupo);
            }
        }
    }

    /*
    
    Grupos Economicos
    
     */
    public void opcionConsecutivo() {
        List<GruposEconomicos> consecutivos = ejbFacade.findCodigosConsecutivos();
        List<GruposEconomicos> noConsecutivos = ejbFacade.findCodigosNoConsecutivos();

        //Banderas
        Long consecutivo;
        Long noConsecutivo;

        if (consecutivos.isEmpty()) {
            consecutivo = 1L;
            if (noConsecutivos.isEmpty()) {
                getSelected().setCodigoGrupo(consecutivo);
            } else {
                Long consecutivoAcumulado = 0L;
                Long consecutivoFor = 0L;
                for (GruposEconomicos noConsecutivoUno : noConsecutivos) {
                    consecutivoFor = consecutivo + consecutivoAcumulado;
                    if (consecutivoFor.equals(noConsecutivoUno.getCodigoGrupo())) {
                        consecutivoAcumulado++;
                    }
                }
                consecutivoFor = consecutivo + consecutivoAcumulado;
                getSelected().setCodigoGrupo(consecutivoFor);

            }
        }

        if (!consecutivos.isEmpty()) {
            consecutivo = consecutivos.get(0).getCodigoGrupo() + 1;
            if (!noConsecutivos.isEmpty()) {
                Long consecutivoAcumulado = 1L;
                Long consecutivoFor = 0L;
                for (GruposEconomicos noConsecutivoUno : noConsecutivos) {
                    consecutivoFor = consecutivos.get(0).getCodigoGrupo() + consecutivoAcumulado;
                    if (consecutivoFor.equals(noConsecutivoUno.getCodigoGrupo())) {
                        consecutivoAcumulado++;
                    }
                }
                consecutivoFor = consecutivos.get(0).getCodigoGrupo() + consecutivoAcumulado;
                getSelected().setCodigoGrupo(consecutivoFor);

            } else {
                getSelected().setCodigoGrupo(consecutivo);
            }
        }

        if (this.getSelected().getConsecutivo().equals(true)) {
            renderedCodigo = true;

        } else {

            renderedCodigo = false;
        }

    }

    public void validatedNoConsecutivo() {
        GruposEconomicos grupo = ejbFacade.findGrupoByCodigo(getSelected().getCodigoGrupo());
        if (grupo == null) {

        } else {
            JsfUtil.addErrorMessage("El codigo de grupo ya se encuentra registrado");
            getSelected().setCodigoGrupo(null);
        }
    }

    public void validarCampos() {

        String nombre = getSelected().getNombre();
        Long totalEmpleados = getSelected().getEmpleadosGrupo();
        Double facturacion = getSelected().getFacturacionGrupo();
        Double activo = getSelected().getTotalActivo();
        Long codigoG = getSelected().getCodigoGrupo();

        nombre = nombre.trim();

        if (!nombre.equals("") || !nombre.isEmpty() && totalEmpleados != null && facturacion != null && activo != null && codigoG != null) {
            habilitarBoton = false;
        } else {
            habilitarBoton = true;
        }
    }

    public void habilitarBotonBorrado() {
        List<GruposEconomicos> grupos = ejbFacade.grupoByRelacion(getSelected().getCodigoGrupo());
        if (grupos.isEmpty()) {
            habilitarBotonBorrado = false;
        } else {
            habilitarBotonBorrado = true;
        }
    }

    public void deshabilitarBoton() {
        if (this.getSelected().getNombre().equalsIgnoreCase(nombreGrupoReservado)) {
            deshabilitarBotonNoAsignado = true;
        } else {
            deshabilitarBotonNoAsignado = false;
        }
    }

    public void crearGrupo() {
        try {
            this.getSelected().setId(this.getSelected().getCodigoGrupo().toString());
            ejbFacade.create(this.getSelected());
            JsfUtil.addSuccessMessage("Grupo Economico creado correctamente");
            this.grupos = new ArrayList<>();
            this.grupos = ejbFacade.findAll();
            Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Grupo Economico con el nombre: " + this.getSelected().getNombre() + " Codigo Grupo: " + this.getSelected().getCodigoGrupo() + " creado"));

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al crear grupo economico");
        }
    }

    public void borrarGrupo() {
        try {
            ejbFacade.borrar(this.getSelected());
            this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Grupo Economico con el nombre: " + this.getSelected().getNombre() + " Codigo Grupo: " + this.getSelected().getCodigoGrupo() + " fue eliminado"));
            JsfUtil.addSuccessMessage("Grupo Economico " + this.getSelected().getNombre() + " borrado correctamente");

            this.grupos = new ArrayList<>();
            this.grupos = ejbFacade.findAll();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al eliminar el Grupo Economico " + getSelected().getNombre());
        }
    }

    public void validarNombreReservadoGrupo() {

        String nombre = this.getSelected().getNombre();
        if (nombre.equalsIgnoreCase(nombreGrupoReservado)) {
            this.getSelected().setNombre("");
            JsfUtil.addErrorMessage("El nombre " + nombreGrupoReservado + " es reservado por el sistema, por favor escriba un nombre diferente.");
        }

    }

    public List<GruposEconomicos> gruposAsignables() {
        return ejbFacade.gruposAsignables(nombreGrupoReservado);
    }

    public void limpiarSelected() {
        this.setSelected(null);
    }

    public Boolean enableAndDesabledForSession() {
        if (getUsuario().getRol().getNombre().equalsIgnoreCase(Constantes.NOMBRE_ROL_COMERCIAL)) {
            return true;
        } else {
            return false;
        }
    }

    public void garantiaOpcion() {
        if (this.getSelected().isGarantia()) {
            activarGarantia = false;
        } else {
            activarGarantia = true;
        }

    }

    public void updateGrupo(Boolean editRiesgos) {
        try {
            System.out.println("HOLA");
            if (editRiesgos) {

                GruposEconomicos grupoActual = ejbFacade.find(this.getSelected().getCodigoGrupo());

                if (this.getSelected().getCupo() < grupoActual.getConsumido()) {

                    JsfUtil.addErrorMessage("El valor del cupo patrimonio " + this.getSelected().getCupo() + " es menor al cupo consumido " + grupoActual.getConsumido());

                } else {

                    if ((grupoActual.isGarantia() != this.getSelected().isGarantia())
                            || (!grupoActual.getVgarantia().equals(this.getSelected().getVgarantia()))
                            || (!grupoActual.getCupo().equals(this.getSelected().getCupo()))) {

                        Double disponible = this.getSelected().getCupo() - grupoActual.getConsumido();
                        this.getSelected().setDisponibleGrupo(disponible);

                        this.getSelected().setFechaGarantia(new Timestamp(System.currentTimeMillis()));

                    } else {
                        this.getSelected().setFechaGarantia(grupoActual.getFechaGarantia());
                    }

                    if (this.getSelected().getVgarantia() == null) {
                        this.getSelected().setVgarantia(0.0);
                    }
                    ejbFacade.edit(this.getSelected());
                    this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Grupo Economico con el nombre: " + this.getSelected().getNombre() + " Codigo Grupo: " + this.getSelected().getCodigoGrupo() + " modificado. Datros = Garantia: " + this.getSelected().isGarantia() + " Valor de Garantia: " + this.getSelected().getVgarantia() + " Valor del Cupo Patrimonio: " + this.getSelected().getCupo()));
                    this.setSelected(null);
                    grupoActual = new GruposEconomicos();
                    JsfUtil.addSuccessMessage("Actualizaciòn correcta.");
                }

            }

            if (!editRiesgos) {
                ejbFacade.edit(this.getSelected());
                JsfUtil.addSuccessMessage("Actualizacion Correcta");
                this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Grupo Economico con el nombre: " + this.getSelected().getNombre() + " Codigo Grupo: " + this.getSelected().getCodigoGrupo() + " modificado"));
                this.setSelected(null);
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al registrar la fecha de modiciacion");
        }
    }

    public Double validarMinValueGarantia() {
        Parametros parametro = parametrosFacade.find(Constantes.LIMITE_VALOR_GARANTIA);
        Double valor = Double.parseDouble(parametro.getValor());
        return valor;
    }

    public void reiniciarListGrupos() {
        this.setSelected(null);
        this.grupos = new ArrayList<>();
        this.grupos = ejbFacade.findAll();
    }
    
    public String concatCiiuCode(ParametriaCw parametria){
    String nombreCode = parametria.getId().getClave() + " - " + parametria.getDatos();
    return nombreCode;
    }

}
