/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *Proyecto : Cupos Auditoria 2018
 *Programados: Juan Herrera
 *Tag de cambio: CupoAut2018
 *Fecha del cambio : 22-05-2018
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK1
 *Fecha del cambio : 26-06-2018
 --------------------------------------------------------------------------------
 *Proyecto : BSNC-18-0119 - Cupos Auditoria Dual 2018
 *Programador: Wittman Gutiérrez
 *Tag de cambio: CupoAutDual2018
 *Fecha del cambio : 26-07-2018
 --------------------------------------------------------------------------------
 */
package controladores;

import DTO.CupoExcedido;
import DTO.NombreValor;
import DTO.Patrimonio;
import DTO.RatingVencido;
import DTO.ReporteCuposDerivados;
import DTO.ReporteExposicionCrediticaDerivados;
import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.Clientes;
import entidades.Cupos;
import entidades.Desembolsos;
import entidades.EstadoScan;
import entidades.Estados;
import entidades.EstadosCliente;
import entidades.GruposEconomicos;
import entidades.HistoricoEstadoScan;
import entidades.HistoricoEstadosclientes;
import entidades.HistoricoRating;
import entidades.Limitesautorizados;
import entidades.Modalidades;
import entidades.ParametrosAlertasMx;
import entidades.SubEstadoScan;
import entidades.TipoDocumento;
import entidades.ValoresActCupoMx;
import fachadas.AccionesUsuarioFacade;
import fachadas.AcumuladoCorpMxFacade;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.CuposFacade;
import fachadas.EstadoScanFacade;
import fachadas.EstadosClienteFacade;
import fachadas.GruposEconomicosFacade;
import fachadas.HistoricoEstadoScanFacade;
import fachadas.HistoricoRatingFacade;
import fachadas.LimitesautorizadosFacade;
import fachadas.ModeloSfcMxFacade;
import fachadas.NeteoCorpMxFacade;
import fachadas.ParametrosAlertasMxFacade;
import fachadas.ReconduccionFacade;
import fachadas.RelacionClienteGrupoFacade;
import fachadas.SubEstadoScanFacade;
import fachadas.TipoDocumentoFacade;
import fachadas.ValoresActuaCupoMxFacade;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.joda.time.DateTime;
import utilidades.DV;
import constantes.*;
import entidades.EstadoSubstandar;
import entidades.RelacionClienteGrupo;
import fachadas.EstadoSubstandarFacade;
import fachadas.EstadosSubstandarFacade;
import java.util.Collection;
import java.util.Collections;

@ManagedBean(name = "clientesController")
@ViewScoped
public class ClientesController extends AbstractController<Clientes> {

    @EJB
    private ClientesFacade ejbFacade;
    @EJB
    private CuposFacade ejbFacadec;
    @EJB
    private GruposEconomicosFacade ejbgruposFacade;
    @EJB
    private AuditoriaFacade ejbAuditoria;
    @EJB
    private EstadosClienteFacade ejbEstadosClientesFacade;
    // FIXPACK1 - inicio
    @EJB
    private ReconduccionFacade ejbReconduccionFacade;
    // FIXPACK1 - fin
    // CupoAutDual2018 - inicio
    @EJB
    private HistoricoRatingFacade ejbHistoricoratingFacade;

    @EJB
    private HistoricoEstadoScanFacade ejbHistoricoestadoscanFacade;

    @EJB
    private EstadoScanFacade ejbEstadoScanFacade;

    @EJB
    private AccionesUsuarioFacade ejbAccionesUsuarioFacade;

    @EJB
    private SubEstadoScanFacade ejbSubEstadoScanFacade;

    @EJB
    private TipoDocumentoFacade ejbtipodocumentoFacadec;

    @EJB
    private LimitesautorizadosFacade ejblimitesautorizadosFacade;

    @EJB
    private AcumuladoCorpMxFacade ejbeacumuladoCorpMxFacade;

    @EJB
    private ModeloSfcMxFacade ejbmodeloSfcMxFacade;

    @EJB
    private NeteoCorpMxFacade ejbneteoCorpMxFacade;

    @EJB
    private ValoresActuaCupoMxFacade ejbvaloresActuaCupoMxFacade;

    @EJB
    private ParametrosAlertasMxFacade ejbparametrosAlertasMxFacade;

    @EJB
    private RelacionClienteGrupoFacade ejbClienteGrupoFacade;

    @EJB
    private EstadoSubstandarFacade ejbEstadoSubStandarFacade;

    @EJB
    private EstadosSubstandarFacade ejbEstadosSubStandarFacade;

    // CupoAutDual2018 - fin    
    private List<Clientes> clientes;
    private List<SubEstadoScan> listsubestadoscan;
    private boolean subestadoscanFlag = true;
    // CupoAutDual2018 - inicio
    private List<Clientes> clientesNoPermitidosAutorizar = new ArrayList();

    // CupoAutDual2018 - fin
    private Cupos cupos;

    private Limitesautorizados limite;

    private Desembolsos desembolso;

    private boolean muestra;
    private boolean isRatingVencido;
    private NombreValor paramSelected;
    private List<NombreValor> rangoFechas;

    private Estados estado;
    private EstadoScan scan;
    private TipoDocumento tipodocumento;
    private Date fechaCliente;
    private Date fechaScan;
    private boolean extranjeria;
    private List<EstadoScan> estadosScanList;

    private Boolean botonVincular = false;
    private Boolean botonDesvincular = false;

    private String nombreGrupoReservado = Constantes.NOMBRE_RESERVADO_GRUPO;

    private Long valorSelectTipoGrupoEco;

    private Long valorGrupoEconomicoSelected;

    private Boolean botonGuardar = false;
    private Boolean botonGuardarSecundario = true;
    private Boolean disableBoton = true;

    //ClienteRelacionGrupo del Modal de Datos adicionales al crear cliente.
    RelacionClienteGrupo relacionCG = new RelacionClienteGrupo();

    /**
     * Initialize the concrete Clientes controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.desembolso = new Desembolsos();
        this.limite = new Limitesautorizados();
        //this.codigoSubmodulo();
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        this.clientes = this.ejbFacade.clientesTotal();
        // CupoAutDual2018 - inicio
        this.clientesNoPermitidosAutorizar = ejbAccionesUsuarioFacade.clientesNoPermitidos(this.clientes, JsfUtil.Usuario());
        // CupoAutDual2018 - fin
        //this.reportepr=LoadDataReportecuposDerivados();
        getPermisos();
        this.muestra = true;

        this.isRatingVencido = true;
        this.paramSelected = new NombreValor();
        this.estadosScanList = ejbEstadoScanFacade.findAll();
        InicializarParametros();

    }

    public ClientesFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ClientesFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Clientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    // CupoAut2018 -Inicio
    public List<Clientes> getClientesEstadoCupo() {
        List<Clientes> clintesAuxi = new ArrayList();
        if (clientes.size() >= 1) {
            for (Clientes cliente : clientes) {
                // CupoAutDual2018 - inicio
                if (cliente.getCupoEstado().equals("Pendiente") && !clientesNoPermitidosAutorizar.contains(cliente)) {
                    // CupoAutDual2018 - fin
                    clintesAuxi.add(cliente);
                }
            }
        }
        return clintesAuxi;
    }
    // CupoAut2018 -Fin

    public CuposFacade getEjbFacadec() {
        return ejbFacadec;
    }

    public void setEjbFacadec(CuposFacade ejbFacadec) {
        this.ejbFacadec = ejbFacadec;
    }

    public Cupos getCupos() {
        return cupos;
    }

    public void setCupos(Cupos cupos) {
        this.cupos = cupos;
    }

    public Limitesautorizados getLimite() {
        return this.limite;
    }

    public void setLimite(Limitesautorizados limite) {
        this.limite = limite;
    }

    public ClientesController() {
        // Inform the Abstract parent controller of the concrete Clientes?cap_first Entity
        super(Clientes.class);
    }

    public Desembolsos getDesembolso() {
        return desembolso;
    }

    public void setDesembolso(Desembolsos desembolso) {
        this.desembolso = desembolso;
    }

    public boolean isExtranjeria() {
        return extranjeria;
    }

    public void setExtranjeria(boolean extranjeria) {
        this.extranjeria = extranjeria;
    }

    public RelacionClienteGrupo getRelacionCG() {
        return relacionCG;
    }

    public void setRelacionCG(RelacionClienteGrupo relacionCG) {
        this.relacionCG = relacionCG;
    }

    public void seleccionar() {
        if (this.getSelected() != null) {
            this.cupos = this.ejbFacadec.CuposXCliente(this.getSelected());
        }

    }

    public void cogio() {
        ELContext contextoEL = FacesContext.getCurrentInstance().getELContext();
        Application apli = FacesContext.getCurrentInstance().getApplication();

        ExpressionFactory ef = apli.getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(contextoEL, "#{desembolsosController}", DesembolsosController.class);

        DesembolsosController bean = (DesembolsosController) ve.getValue(contextoEL);
        bean.setSelected(getDesembolso());
        bean.getSelected().setLimite(this.getLimite());

    }

    public void getPermisos(String buscarCodigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void verificaNit() {
        List<Clientes> clientes = this.ejbFacade.clientesNit(this.getSelected().getNit());
        if (clientes.size() > 0) {
            this.setSelected(clientes.get(0));
            this.muestra = false;
            JsfUtil.addSuccessMessage("El cliente ya existe por favor para modificar seleccione en la grilla y oprima editar");
        } else {
            if (!this.isExtranjeria()) {
                if (JsfUtil.isNumero(this.getSelected().getNit())) {
                    // if (Long.parseLong(this.getSelected().getNit()) < 800000) {
                    //     JsfUtil.addErrorMessage("El numero de nit ingresado no es valido por favor inserte un nit valido");
                    //  } else {

                    if (this.tipodocumento.getNombre().equals("NIT")) {
                        this.getSelected().setDigitochequeo(String.valueOf(DV.generarDv(Long.parseLong(this.getSelected().getNit()))));
                    } else {
                        this.getSelected().setDigitochequeo("0");
                    }
                    //  }
                } else {
                    this.getSelected().setNit("");
                    JsfUtil.addErrorMessage("El  nit ingresado no es un valor númerico");
                }
            }
        }
    }

    public void cargartipodocumento() {
        tipodocumento = this.ejbtipodocumentoFacadec.buscar(getSelected().getTipo_documento().getCodigo());
        if (!this.getSelected().getNit().equals("")) {
            verificaNit();
        }
    }

    public void borrar() {
        this.ejbFacade.borrar(this.getSelected());
        this.clientes = this.ejbFacade.clientesTotal();
        this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se desactivo el cliente: " + this.getSelected().getNit()));
        JsfUtil.addSuccessMessage("Registro Cancelado con exito!");
    }

    // FIXPACK1 - inicio
    public void guardar(boolean edicion) {
        // FIXPACK1 - fin
        int uno = 0;
        int dos = 0;
        if (this.estado != null && this.fechaCliente != null) {
            if (!this.getSelected().getEstadocliente().getId().equalsIgnoreCase(this.estado.getId())) {
                uno = 1;
            }
            if (!this.getSelected().getFechaestado().equals(this.fechaCliente)) {
                uno = 1;
            }
        } else {
            uno = 1;
        }
        if (this.scan != null && this.fechaScan != null) {
            if (!this.getSelected().getScan().getCodigo().equalsIgnoreCase(this.scan.getCodigo())) {
                dos = 1;
            }
            if (!this.getSelected().getFechascan().equals(this.fechaScan)) {
                dos = 1;
            }
        } else {
            dos = 1;
        }
        // FIXPACK1 - inicio
        if (this.getSelected().getReconduccion() == null) {
            this.getSelected().setReconduccion(ejbReconduccionFacade.ReconduccionXNombre("NA"));
        }
        // FIXPACK1 - fin  

        GruposEconomicos grupos = null;
        if (edicion == false) {
            grupos = ejbgruposFacade.find(valorGrupoEconomicoSelected);
            this.getSelected().setGrupo(grupos);
        }

        if (edicion) {
            RelacionClienteGrupo relacion = ejbClienteGrupoFacade.relacionByClient(this.getSelected());
            relacion.setFacturacion(this.getSelected().getVentas());
            relacion.setTotalActivo(this.getSelected().getValoractivo());
            this.ejbClienteGrupoFacade.edit(relacion);
        }

        //Estado SubStandar Asignado al cliente
        EstadoSubstandar estadoNormal = this.ejbEstadoSubStandarFacade.estadoPorNombre(Constantes.ESTADO_SUBSTANDAR_DEFAULT);
        this.getSelected().setEstadoSubstandar(estadoNormal);

        this.ejbFacade.guardar(this.getSelected(), JsfUtil.Usuario().getNombre(), uno, dos);
        this.clientes = this.ejbFacade.clientesTotal();

        //Crear relacion cliente estado Substandar.
        this.ejbEstadosSubStandarFacade.guardar(new Date(), estadoNormal, Constantes.DESCRIPCION_SUBSTANDAR_CW, this.getSelected());

        //Creacion de RelacionClienteGrupo
        if (edicion == false) {
            if (grupos.getNombre().equalsIgnoreCase(nombreGrupoReservado)) {
                RelacionClienteGrupo relacion = new RelacionClienteGrupo();
                relacion.setCliente(this.getSelected());
                relacion.setGruposEconomico(grupos);
                relacion.setTipoVinculo(null);
                relacion.setTipoRelacion(null);
                relacion.setPorcParticipacion(null);
                relacion.setCantidadEmpleado(null);
                relacion.setRolJerarquico(null);
                relacion.setFacturacion(null);
                relacion.setTotalActivo(null);

                this.ejbClienteGrupoFacade.create(relacion);

            }

            if (!grupos.getNombre().equalsIgnoreCase(nombreGrupoReservado)) {
                this.relacionCG.setFacturacion(this.getSelected().getVentas());
                this.relacionCG.setTotalActivo(this.getSelected().getValoractivo());
                this.relacionCG.setCliente(this.getSelected());
                this.relacionCG.setGruposEconomico(grupos);
                this.ejbClienteGrupoFacade.create(relacionCG);
            }

        }

        // FIXPACK1 - inicio
        //this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(),"Se Creo o modifico el cliente: " + this.getSelected().getNit()));
        // FIXPACK1 - fin
        //JsfUtil.addSuccessMessage("Registro Procesado con exito!");
        // FIXPACK1 - inicio
        if (edicion) {

            HistoricoRating historicoultimamodificacionrating = null;
            historicoultimamodificacionrating = this.ejbHistoricoratingFacade.Ultimamodificacionrating(this.getSelected().getNit());
            if (historicoultimamodificacionrating == null) {
                JsfUtil.addSuccessMessage("No existe historico del rating");
                this.ejbHistoricoratingFacade.guardar(new HistoricoRating(this.getSelected().getNit(), this.getSelected().getNombre(), this.getSelected().getRating(), 0.00, this.getSelected().getRating(), this.getSelected().getFecharating()));
                JsfUtil.addSuccessMessage("se creo un registro apartir del rating actual");
            } else {
                if (historicoultimamodificacionrating.getFecharating().before(this.getSelected().getFecharating())) {
                    this.ejbHistoricoratingFacade.guardar(new HistoricoRating(this.getSelected().getNit(), this.getSelected().getNombre(), historicoultimamodificacionrating.getRatinginicial(), this.getSelected().getRating(), this.getSelected().getRating(), this.getSelected().getFecharating()));
                }
            }

            HistoricoEstadoScan historicostedoscan = null;
            historicostedoscan = this.ejbHistoricoestadoscanFacade.Ultimamodificacionestadoscan(this.getSelected().getNit());
            if (historicostedoscan == null) {
                JsfUtil.addSuccessMessage("No existe historico del Estado Scan");
                this.ejbHistoricoestadoscanFacade.guardar(new HistoricoEstadoScan(this.getSelected().getNit(), this.getSelected().getNombre(), this.getSelected().getScan().getNombre(), this.getSelected().getSubestado_scan().getNombre(), this.getSelected().getFechascan()));
                JsfUtil.addSuccessMessage("se creo un registro apartir del Estado Scan actual");
            } else {
                if (historicostedoscan.getFechaestadoscan().before(this.getSelected().getFechascan())) {
                    this.ejbHistoricoestadoscanFacade.guardar(new HistoricoEstadoScan(this.getSelected().getNit(), this.getSelected().getNombre(), this.getSelected().getScan().getNombre(), this.getSelected().getSubestado_scan().getNombre(), this.getSelected().getFechascan()));
                }
            }

            this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), getMyBundle().getString("ClientesUpdated") + ": NIT: " + this.getSelected().getNit() + ": Nombre: " + this.getSelected().getNombre() + " Estado Cliente: " + this.getSelected().getEstadocliente().getNombre() + " Fecha Estado: " + this.getSelected().getFechaestado() + " Estado Scan: " + this.getSelected().getScan().getNombre() + " Sub-Estado Scan: " + this.getSelected().getSubestado_scan().getNombre() + " Fecha Estado Scan: " + this.getSelected().getFechascan() + " Rating: " + this.getSelected().getRating() + " Fecha Rating: " + this.getSelected().getFecharating() + " Valor Activos: " + this.getSelected().getValoractivo() + " Valor Ventas: " + this.getSelected().getVentas() + " Fecha Balance: " + this.getSelected().getFechabalance()));
            JsfUtil.addSuccessMessage(getMyBundle().getString("ClientesUpdated"));
        } else {

            this.ejbHistoricoratingFacade.guardar(new HistoricoRating(this.getSelected().getNit(), this.getSelected().getNombre(), this.getSelected().getRating(), 0.00, this.getSelected().getRating(), this.getSelected().getFecharating()));
            this.ejbHistoricoestadoscanFacade.guardar(new HistoricoEstadoScan(this.getSelected().getNit(), this.getSelected().getNombre(), this.getSelected().getScan().getNombre(), this.getSelected().getSubestado_scan().getNombre(), this.getSelected().getFechascan()));
            this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), getMyBundle().getString("ClientesCreated") + ": NIT: " + this.getSelected().getNit() + ": Nombre: " + this.getSelected().getNombre() + " Estado Cliente: " + this.getSelected().getEstadocliente().getNombre() + " Fecha Estado: " + this.getSelected().getFechaestado() + " Estado Scan: " + this.getSelected().getScan().getNombre() + " Sub-Estado Scan: " + this.getSelected().getSubestado_scan().getNombre() + " Fecha Estado Scan: " + this.getSelected().getFechascan() + " Rating: " + this.getSelected().getRating() + " Fecha Rating: " + this.getSelected().getFecharating() + " Valor Activos: " + this.getSelected().getValoractivo() + " Valor Ventas: " + this.getSelected().getVentas() + " Fecha Balance: " + this.getSelected().getFechabalance()));
            JsfUtil.addSuccessMessage(getMyBundle().getString("ClientesCreated"));
        }
        // FIXPACK1 - fin
        //Borra todo lo referente al formulario, para evitar posibles errores con datos almacenados en selected.
        this.setSelected(null);
        this.valorGrupoEconomicoSelected = null;
        relacionCG = new RelacionClienteGrupo();

    }

    public boolean isMuestra() {
        return muestra;
    }

    public void setMuestra(boolean muestra) {
        this.muestra = muestra;
    }

    public void reiniciar() {
        this.muestra = true;
        this.setSelected(new Clientes());
    }

    public void validarFechaBalance() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(this.getSelected().getFechabalance());
        cal2.setTime(new Date());
        if (cal1.getTimeInMillis() - cal2.getTimeInMillis() > 24) {
            this.getSelected().setFechabalance(new Date());
            JsfUtil.addSuccessMessage("La fecha del balance no puede ser mayor a la del día de hoy");
        }

        cal2.add(cal2.YEAR, -1);
        if (cal1.before(cal2)) {
            this.getSelected().setFechabalance(new Date());
            JsfUtil.addErrorMessage("La fecha del balance no puede ser menor a la de un año");
        }
    }

    public void actualizacionautomaticafecharating() {
        Clientes clienterating = ejbFacade.find(this.getSelected().getId());
        Double ratingBD = clienterating.getRating();
        if (!this.getSelected().getRating().equals(ratingBD)) {
            this.getSelected().setFecharating(new Date());
        }
    }

    public void validarFechaBalanceModificar() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(this.getSelected().getFechabalance());
        cal2.setTime(new Date());

        Calendar cal3 = Calendar.getInstance();
        Clientes clienteFecha = ejbFacade.find(this.getSelected().getId());
        Date fechaBD = clienteFecha.getFechabalance();
        cal3.setTime(fechaBD);
        if (cal1.getTimeInMillis() - cal2.getTimeInMillis() > 24) {
            this.getSelected().setFechabalance(fechaBD);
            JsfUtil.addSuccessMessage("La fecha del balance no puede ser mayor a la del día de hoy");
        }

        if (cal1.before(cal3)) {
            this.getSelected().setFechabalance(fechaBD);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormato = formato.format(fechaBD);
            JsfUtil.addErrorMessage("La fecha del balance no puede ser menor a la guardada en base de datos: ".
                    concat(fechaFormato));
        }
    }

    public void validarRating() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.setTime(this.getSelected().getFecharating());
        cal2.setTime(new Date());
        cal3.setTime(this.getSelected().getAlta());
        cal3.add(cal3.YEAR, -1);
        if (cal1.getTimeInMillis() - cal2.getTimeInMillis() > 24) {
            this.getSelected().setFecharating(new Date());
            JsfUtil.addSuccessMessage("La fecha del rating no puede ser mayor a la del día de hoy");
        }
        if (cal1.before(cal3)) {
            this.getSelected().setFecharating(new Date());
            JsfUtil.addSuccessMessage("la fecha de rating no puede ser menor a un año de la fecha de alta");
        }

    }

    public void validarRatingmodificacion() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.setTime(this.getSelected().getFecharating());
        cal2.setTime(new Date());
        cal3.setTime(this.ejbFacade.clientesNitfecrating(this.getSelected().getNit()));
        if (cal1.getTimeInMillis() - cal2.getTimeInMillis() > 24) {
            this.getSelected().setFecharating(this.ejbFacade.clientesNitfecrating(this.getSelected().getNit()));
            JsfUtil.addSuccessMessage("La fecha del rating no puede ser mayor a la del día de hoy");
        }
        if (cal1.before(cal3)) {
            this.getSelected().setFecharating(this.ejbFacade.clientesNitfecrating(this.getSelected().getNit()));
            JsfUtil.addSuccessMessage("La fecha del rating no puede ser menor a la del ultimo rating");
        }
    }

    public void validarDesde() {
        if (JsfUtil.compararFechasSinTiempo(this.getSelected().getDesde(), new Date()) == 2) {
            this.getSelected().setDesde(new Date());
            JsfUtil.addSuccessMessage("La fecha desde debe ser menor o igual a la de hoy");
        }
    }

    public void validarFechaEstado() {
        if (JsfUtil.compararFechasSinTiempo(this.getSelected().getFechaestado(), new Date()) == 2) {
            this.getSelected().setDesde(new Date());
            JsfUtil.addSuccessMessage("La fecha del estado del cliente debe ser menor o igual a la de hoy");
        }
        if (JsfUtil.compararFechasSinTiempo(this.getSelected().getFechaestado(), this.getSelected().getDesde()) == 1) {
            this.getSelected().setDesde(new Date());
            JsfUtil.addSuccessMessage("La fecha del estado No puede ser menor a la desde del cliente ");
        }
    }

    public void validarScan() {
        if (JsfUtil.compararFechasSinTiempo(this.getSelected().getFechascan(), new Date()) == 2) {
            this.getSelected().setDesde(new Date());
            JsfUtil.addSuccessMessage("La fecha del estado Scan debe ser menor o igual a la de hoy");
        }
        if (JsfUtil.compararFechasSinTiempo(this.getSelected().getFechascan(), this.getSelected().getDesde()) == 1) {
            this.getSelected().setDesde(new Date());
            JsfUtil.addSuccessMessage("La fecha del estado Scan no puede ser menor a la desde del cliente ");
        }
    }

    public void desembolsoExcedido() {
        System.out.println("maravilloso entro aqui");
        List<Clientes> clientes = this.ejbFacade.clientesTotal();
        List<CupoExcedido> cupos = new ArrayList<CupoExcedido>();
        // FIXPACK1 - inicio
        Double TotalExcedente = 0.0;
        // FIXPACK1 - fin
        for (Clientes cliente : clientes) {
            boolean candidato = false;
            try {
                Double totalCupo = 0.0;
                for (Limitesautorizados limite : cliente.getCuposList().get(0).getLimitesautorizadosList()) {
                    totalCupo += limite.getConsumido();
                    if (limite.getConsumido() > limite.getSublimiteautorizado()) {
                        candidato = true;
                    }
                }
                candidato = true; // borrar.
                if (candidato) {
                    for (Limitesautorizados limite : cliente.getCuposList().get(0).getLimitesautorizadosList()) {
                        // FIXPACK1 - inicio
                        TotalExcedente += limite.getSublimiteautorizado() - limite.getConsumido();
                        // FIXPACK1 - fin
                        cupos.add(new CupoExcedido(cliente.getNombre(), cliente.getCuposList().get(0).getLimitetotal(), cliente.getCuposList().get(0).getLimitetotal() - totalCupo, cliente.getNit() + "-" + cliente.getDigitochequeo(), totalCupo, limite.getModalidad().getNombre(), limite.getSublimiteautorizado(), limite.getConsumido(), limite.getSublimiteautorizado() - limite.getConsumido()));
                    }
                }

            } catch (Exception ex) {
                System.out.printf("Excepcion");
            }
        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(cupos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/desembolsose.jasper");
        JasperPrint jasperPrint;
        try {
            System.out.println("Entro al reporte");
            HashMap has = new HashMap();
            has.put("patrimonio", Double.parseDouble(this.ejbFacade.limiteGlobal().getValor()));
            //FIXPACK1 - inicio
            has.put("totalexcedente", TotalExcedente);
            String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
            has.put("url", url);
            //FIXPACK1 - fin
            jasperPrint = JasperFillManager.fillReport(reportPath, has, beanCollectionDataSource);
            HttpServletResponse response = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"cuposExcedido.pdf\"");
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

                outputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(LimitesautorizadosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException ex) {
            Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void patrimonioExcedido() {
        List<GruposEconomicos> grupos = this.ejbgruposFacade.findAll();
        List<Patrimonio> patrimonios = new ArrayList<Patrimonio>();
        // FIXPACK1 - inicio
        Double TotalExcedentes = 0.0;
        // FIXPACK1 - fin
        for (GruposEconomicos grupo : grupos) {
            double valor = 0.0;
            for (Clientes cliente : grupo.getClientesList()) {
                try {
                    valor += cliente.getCuposList().get(0).getLimiteconsumido();
                } catch (Exception ex) {
                    System.out.printf("Excepcion");
                }
            }

            if (valor > Double.parseDouble(this.ejbFacade.limiteGlobal().getValor())) {
                // FIXPACK1 - inicio
                TotalExcedentes += Double.parseDouble(this.ejbFacade.limiteGlobal().getValor()) - valor;
                // FIXPACK1 - fin
                patrimonios.add(new Patrimonio(grupo.getNombre(), valor, Double.parseDouble(this.ejbFacade.limiteGlobal().getValor()) - valor));
            }

        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(patrimonios);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/patrimonioe.jasper");

        try {
            System.out.println("Entro al reporte");
            HashMap has = new HashMap();
            has.put("patrimonio", Double.parseDouble(this.ejbFacade.limiteGlobal().getValor()));
            // FIXPACK1 - inicio
            String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
            has.put("url", url);
            has.put("totalexcedente", TotalExcedentes);
            // FIXPACK1 - fin
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, has, beanCollectionDataSource);
            HttpServletResponse response = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"PatrimonioExcedido.pdf\"");
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

                outputStream.flush();
            } catch (IOException ex) {
                Logger.getLogger(LimitesautorizadosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JRException ex) {
            Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void patrimonioExcedidoXls() {
        List<GruposEconomicos> grupos = this.ejbgruposFacade.findAll();
        List<Patrimonio> patrimonios = new ArrayList<Patrimonio>();
        // FIXPACK1 - inicio
        Double TotalExcedentes = 0.0;
        // FIXPACK1 - fin
        for (GruposEconomicos grupo : grupos) {
            double valor = 0.0;
            for (Clientes cliente : grupo.getClientesList()) {
                try {
                    valor += cliente.getCuposList().get(0).getLimiteconsumido();
                } catch (Exception ex) {
                    System.out.printf("Excepcion");
                }
            }

//                  if(true){
            if (valor > Double.parseDouble(this.ejbFacade.limiteGlobal().getValor())) {
                // FIXPACK1 - inicio
                TotalExcedentes += Double.parseDouble(this.ejbFacade.limiteGlobal().getValor()) - valor;
                // FIXPACK1 - fin
                patrimonios.add(new Patrimonio(grupo.getNombre(), valor, Double.parseDouble(this.ejbFacade.limiteGlobal().getValor()) - valor));
            }

        }
//               if(true){
        if (patrimonios.size() > 0) {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(patrimonios);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/patrimonioe.jasper");
            HashMap has = new HashMap();
            // FIXPACK1 - inicio
            has.put("totalexcedente", TotalExcedentes);
            String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
            has.put("url", url);
            //FIXPACK1 - fin
            has.put("patrimonio", Double.parseDouble(this.ejbFacade.limiteGlobal().getValor()));
            // FIXPACK1 - inicio
            JsfUtil.reporteXLS(beanCollectionDataSource, has, reportPath, "PatrimonioExcedido");
            // FIXPACK1 - fin
        } else {
            JsfUtil.addSuccessMessage("No hay ocurrencias para este reporte");
        }
    }

    public void desembolsoExcedidoXLS() {
        System.out.println("maravilloso entro aqui");
        List<Clientes> clientes = this.ejbFacade.clientesTotal();
        List<CupoExcedido> cupos = new ArrayList<CupoExcedido>();
        // FIXPACK1 - inicio
        Double TotalExcedente = 0.0;
        // FIXPACK1 - fin
        for (Clientes cliente : clientes) {
            boolean candidato = false;
            try {
                Double totalCupo = 0.0;
                for (Limitesautorizados limite : cliente.getCuposList().get(0).getLimitesautorizadosList()) {
                    totalCupo += limite.getConsumido();
                    if (limite.getConsumido() > limite.getSublimiteautorizado()) {
                        candidato = true;
                    }
                }
                candidato = true; // borrar.
                if (candidato) {
                    for (Limitesautorizados limite : cliente.getCuposList().get(0).getLimitesautorizadosList()) {
                        // FIXPACK1 - inicio
                        TotalExcedente += limite.getSublimiteautorizado() - limite.getConsumido();
                        // FIXPACK1 - fin
                        cupos.add(new CupoExcedido(cliente.getNombre(), cliente.getCuposList().get(0).getLimitetotal(), cliente.getCuposList().get(0).getLimitetotal() - totalCupo, cliente.getNit() + "-" + cliente.getDigitochequeo(), totalCupo, limite.getModalidad().getNombre(), limite.getSublimiteautorizado(), limite.getConsumido(), limite.getSublimiteautorizado() - limite.getConsumido()));
                    }
                }

            } catch (Exception ex) {
                System.out.printf("Excepcion");
            }
        }
        if (cupos.size() > 0) {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(cupos);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/desembolsose.jasper");
            JasperPrint jasperPrint;
            HashMap has = new HashMap();
            //FIXPACK1 - inicio
            has.put("totalexcedente", TotalExcedente);
            String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
            has.put("url", url);
            //FIXPACK1 - fin
            JsfUtil.reporteXLS(beanCollectionDataSource, has, reportPath, "cuposExcedido");
        }
    }

    public void estados(int tipo) {

        String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nit_input");

        List<HistoricoEstadosclientes> historico = this.ejbFacade.historicos(nombre, 1);

        if (historico.size() > 0) {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(historico);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/estados.jasper");
            HashMap has = new HashMap();
            //FIXPACK1 - inicio
            String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
            has.put("url", url);
            has.put("titulo", "Historico de Estado Cliente");
            //FIXPACK1- fin
            if (tipo == 1) {
                // FIXPACK1 - inicio
                JsfUtil.reporteXLS(beanCollectionDataSource, has, reportPath, "EstadosClientes");
                // FIXPACK1 - fin
            } else {
                // FIXPACK1 - inicio
                JsfUtil.reportePdf(beanCollectionDataSource, has, reportPath, "EstadosClientes");
                // FIXPACK1 - fin
            }
        } else {
            JsfUtil.addSuccessMessage("No hay ocurrencias para este reporte");
        }
    }

    public void scan(int tipo) {

        String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nit_input");

        List<HistoricoEstadosclientes> historicoscan = this.ejbFacade.historicos(nombre, 2);

        if (historicoscan.size() > 0) {

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(historicoscan);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/estados.jasper");
            HashMap has = new HashMap();
            //FIXPACK1 - inicio
            String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
            has.put("url", url);
            has.put("titulo", "Historico de Estado Scan");
            //FIXPACK1- fin
            if (tipo == 1) {
                // FIXPACK1 - inicio
                JsfUtil.reporteXLS(beanCollectionDataSource, has, reportPath, "EstadosScans");
                // FIXPACK1 - fin
            } else {
                // FIXPACK1 - inicio
                JsfUtil.reportePdf(beanCollectionDataSource, has, reportPath, "EstadosScans");
                // FIXPACK1 - fin
            }
        } else {
            JsfUtil.addSuccessMessage("No hay ocurrencias para este reporte");
        }
    }

    public void Historicoratingpdf() {

    }

    public void historicoratingxls() {
        String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nit_input");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            List<HistoricoRating> historicorating = this.ejbHistoricoratingFacade.HistoricoRatingreporte(nombre);
            if (!historicorating.isEmpty()) {
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(historicorating);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/HistoricoRating.jasper");

                HashMap has = new HashMap();
                String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
                has.put("url", url);
                has.put("fecha", "Fecha de Generación: " + format.format(new Date()));
                has.put("titulo", "Listado del Historico del Rating por Cliente");
                JsfUtil.reporteXLS(beanCollectionDataSource, has, reportPath, "HistoricoRating");
            } else {
                JsfUtil.addSuccessMessage("No hay ocurrencias para este reporte");
            }
        } catch (Exception ex) {
            System.out.println("Excepcion en el reporte de rating vencidos en XLS: "
                    + ex.getMessage());
        }
    }

    public void copiar() {
        this.estado = this.getSelected().getEstadocliente();
        this.scan = this.getSelected().getScan();
        this.getSelected().getSubestado_scan();
        this.fechaCliente = this.getSelected().getFechaestado();
        this.fechaScan = this.getSelected().getFechascan();
    }

    public void extranjero() {
        this.getSelected().setDigitochequeo("E");
    }

    public Double getConsumidoTotal(Cupos cupos) {
        List<Limitesautorizados> limites = cupos.getLimitesautorizadosList();
        Double total = 0.0;
        for (Limitesautorizados lim : limites) {
            total += lim.getConsumido();
        }
        return total;
    }

    public void serviceChange() {
        scan = this.getSelected().getScan();
        listsubestadoscan = ejbSubEstadoScanFacade.buscar(scan);
        subestadoscanFlag = true;
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

    public Date getCalcularFechaEstado(Clientes clientes) {
        List<EstadosCliente> estadosClienteList = ejbEstadosClientesFacade.loadEstado(clientes);

        DateTime dataTimePivote = new DateTime(clientes.getFechaestado());
        if (!estadosClienteList.isEmpty()) {
            dataTimePivote = new DateTime(estadosClienteList.get(0).getFecha());
            for (EstadosCliente temp : estadosClienteList) {
                if (dataTimePivote.isBefore(temp.getFecha().getTime())) {
                    dataTimePivote = new DateTime(temp.getFecha().getTime());
                }
            }
        }

        return dataTimePivote.toDate();
    }

    public boolean isIsRatingVencido() {
        return isRatingVencido;
    }

    public void setIsRatingVencido(boolean isRatingVencido) {
        this.isRatingVencido = isRatingVencido;
    }

    public NombreValor getParamSelected() {
        return paramSelected;
    }

    public void setParamSelected(NombreValor paramSelected) {
        this.paramSelected = paramSelected;
    }

    public List<NombreValor> getRangoFechas() {
        return rangoFechas;
    }

    public void setRangoFechas(List<NombreValor> rangoFechas) {
        this.rangoFechas = rangoFechas;
    }

    public void InicializarParametros() {
        this.rangoFechas = new ArrayList<>();
        String parametro = ResourceBundle.getBundle("/MyBundle").getString("ArrayMesesVencer");
        String aux[] = parametro.split(";");
        for (int i = 0; i < aux.length; i++) {
            NombreValor data = new NombreValor();
            data.setNombre(aux[i]);
            data.setValor(Integer.parseInt(aux[++i]));
            this.rangoFechas.add(data);
        }
    }

    /**
     * Metodo utilizado para imprimir el reporte del listado de clientes con
     * rating vencido o por vencer en un archivo en formato PDF.
     */
    public void reporteRatingVencidoPDF() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            List<RatingVencido> data = LoadDataRating();
            if (!data.isEmpty()) {
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(data);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/RatingVencido.jasper");

                HashMap has = new HashMap();
                String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
                has.put("url", url);
                has.put("restante", isRatingVencido ? "Vencido aprox. desde" : "Vence aprox. en");
                has.put("fecha", "Fecha de Generación: " + format.format(new Date()));
                has.put("titulo", isRatingVencido ? "Listado de Clientes con Rating Vencido"
                        : "Listado de Clientes con Rating por Vencer");
                JsfUtil.reportePdf(beanCollectionDataSource, has, reportPath, isRatingVencido ? "RatingVencidos" : "RatingPorVencer");
            } else {
                JsfUtil.addSuccessMessage("No hay ocurrencias para este reporte");
            }
        } catch (Exception ex) {
            System.out.println("Excepcion en el reporte de rating vencidos PDF: "
                    + ex.getMessage());
        }
    }

    /**
     * Metodo utilizado para imprimir el reporte del listado de clientes con
     * rating vencido o por vencer en un archivo en formato Excel.
     */
    public void reporteRatingVencidoXLS() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            List<RatingVencido> data = LoadDataRating();
            if (!data.isEmpty()) {
                JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(data);
                String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/RatingVencido.jasper");

                HashMap has = new HashMap();
                String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
                has.put("url", url);
                has.put("restante", isRatingVencido ? "Vencido desde" : "Vence en");
                has.put("fecha", "Fecha de Generación: " + format.format(new Date()));
                has.put("titulo", isRatingVencido ? "Listado de Clientes con Rating Vencido"
                        : "Listado de Clientes con Rating por Vencer");
                JsfUtil.reporteXLS(beanCollectionDataSource, has, reportPath, isRatingVencido ? "RatingVencidos" : "RatingPorVencer");
            } else {
                JsfUtil.addSuccessMessage("No hay ocurrencias para este reporte");
            }
        } catch (Exception ex) {
            System.out.println("Excepcion en el reporte de rating vencidos en XLS: "
                    + ex.getMessage());
        }
    }

    /**
     * Mtodo utilizado en reporteRatingVencidoXLS() y en
     * reporteRatingVencidoPDF() encargado de cargar la data necesaria para los
     * reportes del listado de clientes con ratings vecidos o por vencer.
     *
     * @return el listado de los clientes con rating vencidos o por vencer que
     * cumplan con los parametros de busqueda
     */
    public List<RatingVencido> LoadDataRating() {
        List<RatingVencido> ratings = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<Clientes> clientes;
        try {
            if (isRatingVencido) {
                Calendar cal = Calendar.getInstance();
                Date fecha = format.parse("01/01/" + cal.get(Calendar.YEAR));
                clientes = this.ejbFacade.findRatingByFechas(CalcularFechas(fecha, -1, 0),
                        CalcularFechas(new Date(), -1, 0));
            } else {
                Date fecha = CalcularFechas(new Date(), -1, paramSelected.getValor());
                clientes = this.ejbFacade.findRatingByFechasAbierto(CalcularFechas(new Date(), -1, 0), fecha);
            }
            for (Clientes cliente : clientes) {
                RatingVencido rating = new RatingVencido();
                rating.setNit(cliente.getNit());
                rating.setCliente(cliente.getNombre());
                rating.setRating(cliente.getRating());
                rating.setFechaRating(cliente.getFecharating());
                rating.setActivo(cliente.getValoractivo());
                rating.setDiasRestantes(JsfUtil.DiasRestantes(CalcularFechas(new Date(), 0, 0),
                        CalcularFechas(cliente.getFecharating(), 1, 0)));
                ratings.add(rating);
            }
        } catch (Exception ex) {
            System.err.println("Error Cargando data"
                    + " para los Ratings Vencidos: " + ex.getMessage());
        }
        return ratings;

    }

    /**
     * Metodo que se utiliza para calcular la fecha segun ciertos parametros se
     * envia.
     *
     * @param fecha Fecha actual.
     * @param meses cantidad de meses que se van restar a la fecha actual.
     * @param anios cantidad de años que se vana restar a la fecha actual.
     * @return El calculo de la fecha segun los parametros enviados.
     */
    public Date CalcularFechas(Date fecha, int anios, int meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MONTH, meses);
        calendar.add(Calendar.YEAR, anios);
        return calendar.getTime();
    }

    public List<EstadoScan> getEstadosScanList() {
        return estadosScanList;
    }

    public void setEstadosScanList(List<EstadoScan> estadosScanList) {
        this.estadosScanList = estadosScanList;
    }

    public void prepararCrear() {
        this.setSelected(new Clientes());
        this.estadosScanList = ejbEstadoScanFacade.findAll();
        scan = estadosScanList.get(0);
        listsubestadoscan = ejbSubEstadoScanFacade.buscar(scan);
    }

    public Boolean getBotonVincular() {
        return botonVincular;
    }

    public void setBotonVincular(Boolean botonVincular) {
        this.botonVincular = botonVincular;
    }

    public Boolean getBotonDesvincular() {
        return botonDesvincular;
    }

    public void setBotonDesvincular(Boolean botonDesvincular) {
        this.botonDesvincular = botonDesvincular;
    }

    public String getNombreGrupoReservado() {
        return nombreGrupoReservado;
    }

    public void setNombreGrupoReservado(String nombreGrupoReservado) {
        this.nombreGrupoReservado = nombreGrupoReservado;
    }

    public void prepararEditar() {
        Clientes clienteById = ejbFacade.find(this.getSelected().getId());
        listsubestadoscan = ejbSubEstadoScanFacade.buscar(clienteById.getScan());
    }

    public void submitOperation() {
        Clientes cliente = ejbFacade.find(this.getSelected().getId());
        this.getSelected().setAlta(cliente.getAlta());
        this.getSelected().setBanca(cliente.getBanca());
        this.getSelected().setBancaCorporativaBean(cliente.getBancaCorporativaBean());
        this.getSelected().setCiiu(cliente.getCiiu());
        this.getSelected().setDesde(cliente.getDesde());
        this.getSelected().setFechabalance(cliente.getFechabalance());
        this.getSelected().setEstadocliente(cliente.getEstadocliente());
        this.getSelected().setFecharating(cliente.getFecharating());
        this.getSelected().setFechaestado(cliente.getFechaestado());
        this.getSelected().setFechascan(cliente.getFechascan());
        this.getSelected().setNombre(cliente.getNombre());
        this.getSelected().setScan(cliente.getScan());
        this.getSelected().setRating(cliente.getRating());
        this.getSelected().setSubestado(cliente.getSubestado());
        this.getSelected().setSubestado_scan(cliente.getSubestado_scan());
        this.getSelected().setTipo_documento(cliente.getTipo_documento());
        this.getSelected().setVentas(cliente.getVentas());
        this.getSelected().setValoractivo(cliente.getValoractivo());
        this.getSelected().setGestorcomercial(cliente.getGestorcomercial());
        this.getSelected().setGrupo(cliente.getGrupo());
    }

    public List<ReporteCuposDerivados> getClientesCupoDerivados() {
        List<ReporteCuposDerivados> reportecuposderivados = new ArrayList();
        reportecuposderivados = LoadDataReportecuposDerivados();
        // reportecuposderivados=this.reportepr;
        return reportecuposderivados;
    }

    public List<ReporteCuposDerivados> LoadDataReportecuposDerivados() {
        String nomModalidad = "Derivados";
        Modalidades modalida = ejblimitesautorizadosFacade.BuscarModalida(nomModalidad);
        List<ReporteCuposDerivados> reportecuposderivados = new ArrayList<>();
        List<Limitesautorizados> limitesautorizados;
        try {
            limitesautorizados = ejblimitesautorizadosFacade.Limitesautorizadosmodalida(modalida);
            if (limitesautorizados.size() >= 1) {
                for (Limitesautorizados limiteautorizado : limitesautorizados) {
                    ReporteCuposDerivados reportecuposderivado = new ReporteCuposDerivados();
                    reportecuposderivado.setNit(limiteautorizado.getCupo().getCliente().getNit() + limiteautorizado.getCupo().getCliente().getDigitochequeo());
                    reportecuposderivado.setCliente(limiteautorizado.getCupo().getCliente().getNombre());
                    reportecuposderivado.setUsocupo(UsoCupo(limiteautorizado.getSublimiteautorizado(), limiteautorizado.getConsumido()));
                    reportecuposderivado.setNivel(nivelAlerta(UsoCupo(limiteautorizado.getSublimiteautorizado(), limiteautorizado.getConsumido())));
                    reportecuposderivado.setCupototal(limiteautorizado.getSublimiteautorizado());
                    reportecuposderivado.setCupoconsumido(limiteautorizado.getConsumido());
                    reportecuposderivado.setCupodisponible(limiteautorizado.getDisponible());
                    reportecuposderivado.setPlazo(limiteautorizado.getPlazo_forwards());
                    reportecuposderivado.setFechavencimiento(limiteautorizado.getCupo().getFechavencimiento());
                    reportecuposderivado.setBandamaxima("");
                    reportecuposderivado.setDiasparavencimiento(NumDias(limiteautorizado.getCupo().getFechavencimiento()));
                    reportecuposderivado.setEstado(estadoCupoDerivado(limiteautorizado.getCupo().getFechavencimiento(), limiteautorizado.getCupo().isEstado()));
                    reportecuposderivados.add(reportecuposderivado);
                }
            } else {
                System.err.println("No se encuentra datos para la linea Derivados");
            }

        } catch (Exception ex) {
            System.err.println("Error Cargando data"
                    + " para los Ratings Vencidos: " + ex.getMessage());
        }
        return reportecuposderivados;

    }

    public static Double UsoCupo(Double cupolimite, Double consumido) {
        Double porcentage = 100.0;
        Double usocupo;
        usocupo = (consumido * porcentage) / cupolimite;
        return usocupo;
    }

    public static String NumDias(Date fechaVencimiento) {
        Date fechaActual = new Date();
        fechaVencimiento = setMidnight(fechaVencimiento); // seteamos la fecha a medianoche (00:00:00.0000)
        fechaActual = setMidnight(fechaActual); // seteamos la fecha a medianoche (00:00:00.0000)
        int dias = (int) ((fechaVencimiento.getTime() - fechaActual.getTime()) / 86400000);
        String result = Integer.toString(dias);
        return result;
    }

    public static Date setMidnight(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static String estadoCupoDerivado(Date fechaVencimiento, Boolean estadocupo) {
        String estado = "NA";
        Date fechaActual = new Date();
        if (fechaVencimiento.after(fechaActual)) {
            if (estadocupo) {
                estado = "ACTIVO";
            } else {
                estado = "INACTIVO";
            }
        } else {
            estado = "VENCIDO";
        }

        return estado;
    }

    public String nivelAlerta(Double usocupo) {
        String nivelalerta = "NA";
        List<ParametrosAlertasMx> listaParametroalerta;
        listaParametroalerta = ejbparametrosAlertasMxFacade.findAll();
        if (listaParametroalerta != null && !listaParametroalerta.isEmpty()) {
            for (ParametrosAlertasMx parametrosalerta : listaParametroalerta) {
                if (usocupo >= parametrosalerta.getInicioporcentaje() && usocupo < parametrosalerta.getFinporcentaje()) {
                    nivelalerta = parametrosalerta.getNombre();
                    break;
                }
            }
        } else {
            nivelalerta = "NA";
        }

        return nivelalerta;
    }

    public List<ReporteExposicionCrediticaDerivados> getExposicionCrediticiaDerivados() {
        List<ReporteExposicionCrediticaDerivados> reporteexposicioncrediticiaderivados = new ArrayList();
        reporteexposicioncrediticiaderivados = LoadDataReportExposicionCrediticiaDerivados();

        return reporteexposicioncrediticiaderivados;
    }

    public List<ReporteExposicionCrediticaDerivados> LoadDataReportExposicionCrediticiaDerivados() {
        String nomModalidad = "Derivados";
        Modalidades modalida = ejblimitesautorizadosFacade.BuscarModalida(nomModalidad);
        List<ReporteExposicionCrediticaDerivados> reporteexpocisioncreditiaderivados = new ArrayList<>();
        List<Limitesautorizados> limitesautorizados;
        ValoresActCupoMx valoractuacupomx = new ValoresActCupoMx();
        Date fechaActual = new Date();
        Date fechaDiaAnterior = new Date(fechaActual.getTime() - 86400000);
        try {
            limitesautorizados = ejblimitesautorizadosFacade.Limitesautorizadosmodalida(modalida);
            if (limitesautorizados.size() >= 1) {
                for (Limitesautorizados limiteautorizado : limitesautorizados) {
                    valoractuacupomx = ejbvaloresActuaCupoMxFacade.valoresActuacupocliente(limiteautorizado.getCupo().getCliente().getNit() + limiteautorizado.getCupo().getCliente().getDigitochequeo(), fechaActual, fechaDiaAnterior);
                    ReporteExposicionCrediticaDerivados reporteexposicioncrediticaderivado = new ReporteExposicionCrediticaDerivados();
                    reporteexposicioncrediticaderivado.setNit(limiteautorizado.getCupo().getCliente().getNit() + limiteautorizado.getCupo().getCliente().getDigitochequeo());
                    reporteexposicioncrediticaderivado.setCliente(limiteautorizado.getCupo().getCliente().getNombre());
                    reporteexposicioncrediticaderivado.setTiposfc(limiteautorizado.getTiposfc());
                    reporteexposicioncrediticaderivado.setMaxcontrolrisk(limiteautorizado.getConsumido());
                    reporteexposicioncrediticaderivado.setSfcacumulado(AcumuladoSfc(limiteautorizado.getCupo().getCliente().getNit() + limiteautorizado.getCupo().getCliente().getDigitochequeo()));
                    reporteexposicioncrediticaderivado.setSfcneteo(NeteoSfc(limiteautorizado.getCupo().getCliente().getNit() + limiteautorizado.getCupo().getCliente().getDigitochequeo()));
                    reporteexposicioncrediticaderivado.setConsumosfc(CalcularConsumo(limiteautorizado.getTiposfc(), reporteexposicioncrediticaderivado.getSfcneteo(), reporteexposicioncrediticaderivado.getSfcacumulado()));
                    reporteexposicioncrediticaderivado.setCorpacumulado(AcumuladoCorp(limiteautorizado.getCupo().getCliente().getNit() + limiteautorizado.getCupo().getCliente().getDigitochequeo()));
                    reporteexposicioncrediticaderivado.setCorpneteo(NeteoCorp(limiteautorizado.getCupo().getCliente().getNit() + limiteautorizado.getCupo().getCliente().getDigitochequeo()));
                    reporteexposicioncrediticaderivado.setConsumocorp(CalcularConsumo(limiteautorizado.getTiposfc(), reporteexposicioncrediticaderivado.getCorpneteo(), reporteexposicioncrediticaderivado.getCorpacumulado()));
                    reporteexpocisioncreditiaderivados.add(reporteexposicioncrediticaderivado);
                }
            } else {
                System.err.println("No se encuentra datos para la linea Derivados");
            }

        } catch (Exception ex) {
            System.err.println("Error Cargando data"
                    + " para los Ratings Vencidos: " + ex.getMessage());
        }
        return reporteexpocisioncreditiaderivados;

    }

    public Double Maxcontrolrisk(String tiposfc, String cliente) {
        Date fechaActual = new Date();
        Date fechaDiaAnterior = new Date(fechaActual.getTime() - 86400000);
        //ValoresActuaCupoMxDAO valoractuacupoDAO = new ValoresActuaCupoMxDAO();
        Double maxcontrolrisk = 0.00;
        ValoresActCupoMx valoractuacupomx = new ValoresActCupoMx();
        valoractuacupomx = ejbvaloresActuaCupoMxFacade.valoresActuacupocliente(cliente, fechaActual, fechaDiaAnterior);
        if (valoractuacupomx != null) {
            if (tiposfc.equals("NETEO")) {

            } else {
                //  maxcontrolrisk = valoractuacupomx.getAcumulado();
            }
        } else {
            maxcontrolrisk = 0.00;
        }
        return maxcontrolrisk;
    }

    public Double AcumuladoSfc(String cliente) {
        Date fechaActual = new Date();
        Date fechaDiaAnterior = new Date(fechaActual.getTime() - 86400000);
        //ModeloSfcMxDAO modeloSfcMxDAO = new ModeloSfcMxDAO();
        Double consumosfc = 0.00;
        ValoresActCupoMx valoractuacupomx = new ValoresActCupoMx();
        valoractuacupomx = ejbvaloresActuaCupoMxFacade.valoresActuacupocliente(cliente, fechaActual, fechaDiaAnterior);
        if (valoractuacupomx != null) {
            consumosfc = valoractuacupomx.getAcumulado_sfc();
        } else {
            consumosfc = 0.00;
        }
        return consumosfc;
    }

    public Double NeteoSfc(String cliente) {
        Date fechaActual = new Date();
        Date fechaDiaAnterior = new Date(fechaActual.getTime() - 86400000);
        //ModeloSfcMxDAO modeloSfcMxDAO = new ModeloSfcMxDAO();
        Double neteofc = 0.00;
        ValoresActCupoMx valoractuacupomx = new ValoresActCupoMx();
        valoractuacupomx = ejbvaloresActuaCupoMxFacade.valoresActuacupocliente(cliente, fechaActual, fechaDiaAnterior);
        if (valoractuacupomx != null) {
            neteofc = valoractuacupomx.getNeteo_sfc();
        }
        return neteofc;
    }

    public Double AcumuladoCorp(String cliente) {
        Date fechaActual = new Date();
        Date fechaDiaAnterior = new Date(fechaActual.getTime() - 86400000);
        //AcumuladoCorpMxDAO acumuladoCorpMxDAO = new AcumuladoCorpMxDAO();
        Double acumuladocorp = 0.00;
        ValoresActCupoMx valoractuacupomx = new ValoresActCupoMx();
        valoractuacupomx = ejbvaloresActuaCupoMxFacade.valoresActuacupocliente(cliente, fechaActual, fechaDiaAnterior);
        if (valoractuacupomx != null) {
            acumuladocorp = valoractuacupomx.getAcumulado_corp();
        }
        return acumuladocorp;
    }

    public Double NeteoCorp(String cliente) {
        Date fechaActual = new Date();
        Date fechaDiaAnterior = new Date(fechaActual.getTime() - 86400000);
        //NeteoCorpMxDAO neteoCorpMxDAO = new NeteoCorpMxDAO();
        Double neteocorp = 0.00;
        ValoresActCupoMx valoractuacupomx = new ValoresActCupoMx();
        valoractuacupomx = ejbvaloresActuaCupoMxFacade.valoresActuacupocliente(cliente, fechaActual, fechaDiaAnterior);
        if (valoractuacupomx != null) {
            neteocorp = valoractuacupomx.getNeteo_corp();
        }
        return neteocorp;
    }

    public Double CalcularConsumo(String tipoSfc, Double neteo, Double acumulado) {
        Double consumo = 0.00;
        if (tipoSfc != null) {
            if (tipoSfc.equals("NETEO")) {
                consumo = neteo;
            } else {
                consumo = acumulado;
            }
        } else {
            consumo = 0.00;
        }
        return consumo;
    }

    public List<Clientes> clientesGrupo(Long codigo) {

        List<Clientes> clientesGrupo = new ArrayList<>();
        try {
            clientesGrupo = ejbFacade.clientesByCodigo(codigo);
            return clientesGrupo;
        } catch (Exception e) {
            return null;
        }
    }

    /*
    Grupo Economicos Controladores.
     */
    public void validarbotonVincular(String grupo) {
        if (grupo.equalsIgnoreCase(nombreGrupoReservado)) {
            botonVincular = true;
            botonDesvincular = false;
        } else {
            botonVincular = false;
            botonDesvincular = true;
        }

    }

    public void desvincularClienteGrupo() {

        try {
            GruposEconomicos grupo = ejbgruposFacade.grupoByNombre(nombreGrupoReservado);
            ejbClienteGrupoFacade.updateRowByClient(grupo, null, null, null, null, null, null, null, this.getSelected());
            ejbFacade.updateById(grupo, this.getSelected().getId());

            // Update Cupos/Estado y GruposEconomicos --> Disponible/Consumido
            if (!this.getSelected().getCuposList().isEmpty()) {

                ejbFacadec.updateCupoByCliente(this.getSelected(), false);
                Double valorCupoDisponible = this.getSelected().getGrupo().getDisponibleGrupo() + this.getSelected().getCuposList().get(0).getLimitetotal();
                Double valorCupoConsumido = this.getSelected().getGrupo().getConsumido() - this.getSelected().getCuposList().get(0).getLimitetotal();
                this.getSelected().getGrupo().setDisponibleGrupo(valorCupoDisponible);
                this.getSelected().getGrupo().setConsumido(valorCupoConsumido);
                ejbgruposFacade.edit(this.getSelected().getGrupo());

            }
            // Update 
            this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "El cliente con el nombre: " + this.getSelected().getNombre() + " Nit: " + this.getSelected().getNit() + " desvinculado del grupo: " + this.getSelected().getGrupo().getNombre() + " Con codigo de Grupo: " + this.getSelected().getGrupo().getCodigoGrupo()));
            this.setSelected(null);

            JsfUtil.addSuccessMessage("Desvinculacion del cliente correctamente");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al desvincular cliente");
        }
    }

    public void vincularClienteGrupo() {

        try {

            GruposEconomicos grupos = ejbgruposFacade.find(valorSelectTipoGrupoEco);
            Clientes clienteSelected = this.getSelected();
            if (grupos.getDisponibleGrupo() == 0) {
                JsfUtil.addErrorMessage("El grupo " + grupos.getNombre() + " no tiene cupo");
            } else if (!clienteSelected.getCuposList().isEmpty()) {

                Cupos cupo = clienteSelected.getCuposList().get(0);

                Double valorOcupar = grupos.getDisponibleGrupo() - cupo.getLimitetotal();

                if (valorOcupar < 0) {

                    JsfUtil.addErrorMessage("El cupo que posee el cliente con el valor " + cupo.getLimitetotal() + " supera el cupo disponible del grupo " + grupos.getDisponibleGrupo());

                } else {

                    Double consumido = grupos.getConsumido() + cupo.getLimitetotal();
                    grupos.setDisponibleGrupo(valorOcupar);
                    grupos.setConsumido(consumido);
                    ejbgruposFacade.edit(grupos);

                    String vinculo = this.getSelected().getRelacionClienteGrupos().get(0).getTipoVinculo();
                    String relacion = this.getSelected().getRelacionClienteGrupos().get(0).getTipoRelacion();
                    Double porcentaje = this.getSelected().getRelacionClienteGrupos().get(0).getPorcParticipacion();
                    Long empleados = this.getSelected().getRelacionClienteGrupos().get(0).getCantidadEmpleado();
                    String jerarquia = this.getSelected().getRelacionClienteGrupos().get(0).getRolJerarquico();
                    Double facturacion = this.getSelected().getRelacionClienteGrupos().get(0).getFacturacion();
                    Double activo = this.getSelected().getRelacionClienteGrupos().get(0).getTotalActivo();
                    ejbClienteGrupoFacade.updateRowByClient(grupos, vinculo, relacion, porcentaje, empleados, jerarquia, facturacion, activo, this.getSelected());
                    ejbFacade.updateById(grupos, this.getSelected().getId());
                    JsfUtil.addSuccessMessage("Se vinculo el cliente correctamente.");
                    this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "El cliente con el nombre: " + this.getSelected().getNombre() + " Nit: " + this.getSelected().getNit() + " se vinculo al grupo: " + grupos.getNombre() + " Con codigo de grupo: " + grupos.getCodigoGrupo()));

                }
            } else {

                String vinculo = this.getSelected().getRelacionClienteGrupos().get(0).getTipoVinculo();
                String relacion = this.getSelected().getRelacionClienteGrupos().get(0).getTipoRelacion();
                Double porcentaje = this.getSelected().getRelacionClienteGrupos().get(0).getPorcParticipacion();
                Long empleados = this.getSelected().getRelacionClienteGrupos().get(0).getCantidadEmpleado();
                String jerarquia = this.getSelected().getRelacionClienteGrupos().get(0).getRolJerarquico();
                Double facturacion = this.getSelected().getRelacionClienteGrupos().get(0).getFacturacion();
                Double activo = this.getSelected().getRelacionClienteGrupos().get(0).getTotalActivo();
                ejbClienteGrupoFacade.updateRowByClient(grupos, vinculo, relacion, porcentaje, empleados, jerarquia, facturacion, activo, this.getSelected());
                ejbFacade.updateById(grupos, this.getSelected().getId());
                JsfUtil.addSuccessMessage("Se vinculo el cliente correctamente.");
                this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "El cliente con el nombre: " + this.getSelected().getNombre() + " Nit: " + this.getSelected().getNit() + " se vinculo al grupo: " + grupos.getNombre() + " Con codigo de grupo: " + grupos.getCodigoGrupo()));
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al vincular cliente.\n" + e.toString());
        }

    }

    public void validatePorcEdit() {

        Double porcentaje = this.getSelected().getRelacionClienteGrupos().get(0).getPorcParticipacion();

        RelacionClienteGrupo relacionAnt = ejbClienteGrupoFacade.relacionGrupoAnt(this.getSelected().getGrupo(), this.getSelected());
        Double porcentajeGrupo = (ejbClienteGrupoFacade.sumPorcParticionGroup(this.getSelected().getGrupo().getCodigoGrupo())) - relacionAnt.getPorcParticipacion();

        if (porcentaje > 100) {
            this.getSelected().getRelacionClienteGrupos().get(0).setPorcParticipacion(0.0);
            JsfUtil.addErrorMessage("El porcentaje de participacion no puede superar el 100%");
        } else if (porcentajeGrupo == 100) {
            this.getSelected().getRelacionClienteGrupos().get(0).setPorcParticipacion(0.0);
            JsfUtil.addErrorMessage("El porcentaje de participacion del grupo ya es del 100%, por favor edite los otros miembros");
        } else if (porcentaje + porcentajeGrupo > 100) {
            Double restante = porcentaje - porcentajeGrupo;
            JsfUtil.addErrorMessage("El porcentaje de participacion supera el 100%. " + "Asigne un valor inferior a " + restante + " o edite los mienbros del grupo.");
            this.getSelected().getRelacionClienteGrupos().get(0).setPorcParticipacion(0.0);

        }
    }

    public void editVinculoCliente() {

        try {
            Double porcentaje = this.getSelected().getRelacionClienteGrupos().get(0).getPorcParticipacion();
            String vinculo = this.getSelected().getRelacionClienteGrupos().get(0).getTipoVinculo();
            String relacion = this.getSelected().getRelacionClienteGrupos().get(0).getTipoRelacion();
            Long empleados = this.getSelected().getRelacionClienteGrupos().get(0).getCantidadEmpleado();
            String jerarquia = this.getSelected().getRelacionClienteGrupos().get(0).getRolJerarquico();
            Double facturacion = this.getSelected().getRelacionClienteGrupos().get(0).getFacturacion();
            Double activo = this.getSelected().getRelacionClienteGrupos().get(0).getTotalActivo();

            this.getSelected().setValoractivo(this.getSelected().getRelacionClienteGrupos().get(0).getTotalActivo());
            this.getSelected().setVentas(this.getSelected().getRelacionClienteGrupos().get(0).getFacturacion());

            ejbFacade.edit(this.getSelected());
            ejbClienteGrupoFacade.updateRowByClient(this.getSelected().getGrupo(), vinculo, relacion, porcentaje, empleados, jerarquia, facturacion, activo, this.getSelected());
            this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se edito la relacion del cliente: " + this.getSelected().getNombre() + " Nit: " + this.getSelected().getNit() + " Relacionado al grupo: " + this.getSelected().getGrupo().getNombre() + " Codigo de grupo: " + this.getSelected().getGrupo().getCodigoGrupo() + " La relaciòn editada tiene el id: " + this.getSelected().getRelacionClienteGrupos().get(0).getId()));
            JsfUtil.addSuccessMessage("El vinculo del cliente se actualizo correctamente");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al actualizar el vinculo del cliente " + e.toString());
        }

    }

    public void vaciarSelected() {
        this.setSelected(null);
    }

    public List<GruposEconomicos> allGrupos() {
        return ejbgruposFacade.allGroupsAsc();
    }

    public Long getValorGrupoEconomicoSelected() {
        return valorGrupoEconomicoSelected;
    }

    public void setValorGrupoEconomicoSelected(Long valorGrupoEconomicoSelected) {
        this.valorGrupoEconomicoSelected = valorGrupoEconomicoSelected;
    }

    public Long getValorSelectTipoGrupoEco() {
        return valorSelectTipoGrupoEco;
    }

    public void setValorSelectTipoGrupoEco(Long valorSelectTipoGrupoEco) {
        this.valorSelectTipoGrupoEco = valorSelectTipoGrupoEco;
    }

    public Boolean getBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(Boolean botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public Boolean getBotonGuardarSecundario() {
        return botonGuardarSecundario;
    }

    public void setBotonGuardarSecundario(Boolean botonGuardarSecundario) {
        this.botonGuardarSecundario = botonGuardarSecundario;
    }

    public Boolean getDisableBoton() {
        return disableBoton;
    }

    public void setDisableBoton(Boolean disableBoton) {
        this.disableBoton = disableBoton;
    }

    public void enableBotonGuardar() {
        Long valor = valorGrupoEconomicoSelected;
        GruposEconomicos grupos = null;
        try {
            grupos = ejbgruposFacade.find(valor);

            if (grupos.getNombre().equalsIgnoreCase(nombreGrupoReservado)) {
                botonGuardar = false;
                botonGuardarSecundario = true;
                disableBoton = false;
            } else {
                botonGuardar = true;
                botonGuardarSecundario = false;
            }

        } catch (Exception e) {
            botonGuardar = false;
            botonGuardarSecundario = true;
            disableBoton = true;

        }
    }

    public boolean desactivarBotones() {
        String nombre = this.getSelected().getNombre();
        if (nombre == null || nombre.equals("")) {
            return true;
        } else {
            return false;
        }

    }

    public void validarPorcentajeParticipacion(Boolean crear) {
        Double participacionGrupo = 0.0;
        Double porcentajeForm = 0.0;
        if (crear) {
            participacionGrupo = ejbClienteGrupoFacade.sumPorcParticionGroup(valorGrupoEconomicoSelected);
            porcentajeForm = relacionCG.getPorcParticipacion();
        } else {
            participacionGrupo = ejbClienteGrupoFacade.sumPorcParticionGroup(valorSelectTipoGrupoEco);
            porcentajeForm = this.getSelected().getRelacionClienteGrupos().get(0).getPorcParticipacion();
        }
        if (participacionGrupo == 100) {
            if (crear) {
                relacionCG.setPorcParticipacion(0.0);
            } else {
                this.getSelected().getRelacionClienteGrupos().get(0).setPorcParticipacion(0.0);
            }
            JsfUtil.addErrorMessage("El grupo tiene un 100% de participacion, por favor edite la participacion de los integrantes antes de agregar otro");
        } else {
            if (porcentajeForm > 100) {
                if (crear) {
                    relacionCG.setPorcParticipacion(0.0);
                } else {
                    this.getSelected().getRelacionClienteGrupos().get(0).setPorcParticipacion(0.0);
                }
                JsfUtil.addErrorMessage("El porcentaje no puedo superar el 100%");
            } else {
                if (porcentajeForm + participacionGrupo > 100) {
                    Double restante = participacionGrupo - porcentajeForm;
                    JsfUtil.addErrorMessage("El porcentaje de participacion actual en el grupo es " + participacionGrupo + " debe asignar un porcentaje igual o menor a " + restante + ".\n"
                            + "              Para asignar un valor mayor debe modificar los integrantes del grupo");
                    relacionCG.setPorcParticipacion(0.0);
                    if (crear) {
                        relacionCG.setPorcParticipacion(0.0);
                    } else {
                        this.getSelected().getRelacionClienteGrupos().get(0).setPorcParticipacion(0.0);
                    }

                }
            }
        }
    }
}
