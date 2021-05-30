/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *Proyecto : Cupos Auditoria 2018
 *Programados: Juan Herrera
 *Tag de cambio: CupoAut2018
 *Fecha del cambio : 22-05-2018
 --------------------------------------------------------------------------------
 *Proyecto : BSNC-18-0119 - Cupos Auditoria Dual 2018
 *Programador: Wittman Gutiérrez
 *Tag de cambio: CupoAutDual2018
 *Fecha del cambio : 26-07-2018
 --------------------------------------------------------------------------------
 *Proyecto : 17_0170
 *Programados: Julio Beltran
 *Tag de cambio: CWS_SIAE_BOT
 *Fecha del cambio : 21-05-2018
 ---------------------------------------------------------------------------------
 */
package controladores;

import static constantes.Constantes.*;
import controladores.util.JsfUtil;
import entidades.AccionesUsuario;
import entidades.Auditoria;
import entidades.Clientes;
import entidades.Cupos;
import entidades.GruposEconomicos;
import entidades.Limitesautorizados;
import entidades.Modalidades;
import fachadas.AccionesUsuarioFacade;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.CuposFacade;
import fachadas.GruposEconomicosFacade;
import fachadas.LimitesautorizadosFacade;
import fachadas.ModalidadesFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import ws_cliente_siae.ResultadoWS;
import ws_cliente_siae.WS_Cliente_SIAE;

@ManagedBean(name = "cuposController")
@ViewScoped
public class CuposController extends AbstractController<Cupos> {

    @EJB
    private CuposFacade ejbFacade;

    @EJB
    private LimitesautorizadosFacade ejbLimite;
    @EJB
    private ModalidadesFacade ejbModalidad;
    @EJB
    private AuditoriaFacade ejbAuditoria;
    @EJB
    private ClientesFacade ejbCliente;
    // CupoAut2018 -Inicio
    @EJB
    private ClientesFacade ejbClienteFacade;
    // CupoAut2018 -Fin
    // CupoAutDual2018 - inicio
    @EJB
    private AccionesUsuarioFacade ejbAccionesUsuarioFacade;
    // CupoAutDual2018 - fin
    @EJB
    private GruposEconomicosFacade ejbGruposFacade;

    private List<Cupos> cupos;
    private Clientes clienteNit;
    private Clientes clienteNombre;
    private Limitesautorizados limiteActual;
    private boolean creacion;
    private boolean modificacion;
    private boolean cupoVencido;
    private Date fechaActual;

    // CupoAutDual2018 - inicio
    private Clientes clienteNoPermitidoAutorizar;
    // CupoAutDual2018 - fin    

    // CupoAut2018 -Inicio
    private int flagGuardar = 0;
    public Date autorizaFechaVencimiento;
    public double autorizaLimiteTotal;

    private boolean flagDerivados = false;
    //BSNC-19-0119 Operaciones Especiales - ACT - INI
    private boolean flagOpEspeciales = false;
    //BSNC-19-0119 Operaciones Especiales - ACT - FIN

    
    
    public Date getAutorizaFechaVencimiento() {
        return autorizaFechaVencimiento;
    }

    public void setAutorizaFechaVencimiento(Date autorizaFechaVencimiento) {
        this.autorizaFechaVencimiento = autorizaFechaVencimiento;
    }

    public double getAutorizaLimiteTotal() {
        return autorizaLimiteTotal;
    }

    public void setAutorizaLimiteTotal(double autorizaLimiteTotal) {
        this.autorizaLimiteTotal = autorizaLimiteTotal;
    }
    // CupoAut2018 -Fin

    /**
     * Initialize the concrete Cupos controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.cupos = this.ejbFacade.findAll();
        this.clienteNit = new Clientes();
        this.clienteNombre = new Clientes();
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
        this.fechaActual = new Date();
        // CupoAut2018 -Inicio
        FacesContext context = FacesContext.getCurrentInstance();
        getIdParam(context);
        // CupoAut2018 -Fin
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<Cupos> getCupos() {
        return cupos;
    }

    public void setCupos(List<Cupos> cupos) {
        this.cupos = cupos;
    }

    public Clientes getClienteNit() {
        System.out.println("Nit del cliente que llega por sesión");
        return clienteNit;
    }

    public void setClienteNit(Clientes clienteNit) {
        this.clienteNit = clienteNit;
    }

    public Clientes getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(Clientes clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public CuposController() {
        // Inform the Abstract parent controller of the concrete Cupos?cap_first Entity
        super(Cupos.class);
    }

    public Limitesautorizados getLimiteActual() {
        return limiteActual;
    }

    public void setLimiteActual(Limitesautorizados limiteActual) {
        this.limiteActual = limiteActual;
    }

    public boolean isCupoVencido() {
        return cupoVencido;
    }

    public void setCupoVencido(boolean cupoVencido) {
        this.cupoVencido = cupoVencido;
    }

    public void guardar(Boolean modificacion) {
        Double patrimonioTotal = this.getSelected().getCliente().getGrupo().getCupo();
        Double patrimonioConsumido = this.getSelected().getCliente().getGrupo().getConsumido();

        if (modificacion) {
            Cupos cuposDatosAnt = ejbFacade.find(this.getSelected().getId());
            GruposEconomicos grupoDato = this.getSelected().getCliente().getGrupo();

            patrimonioConsumido = this.getSelected().getCliente().getGrupo().getConsumido() - cuposDatosAnt.getLimitetotal();

        }

        if (this.getSelected().getLimitetotal() + patrimonioConsumido <= patrimonioTotal) {
            if (this.nivelar() > 0) {
                if (this.distribuido()) {
                    if (this.comentarios()) {
                        if ((this.getSelected().getLimitetotal() + patrimonioConsumido) <= patrimonioTotal) {
                            if (this.ejbFacade.find(this.getSelected().getId()) == null) {

                                //Seteo de valores a grupo economico.
                                try {

                                    GruposEconomicos gruposUpdate = this.getSelected().getCliente().getGrupo();
                                    Double disponible = gruposUpdate.getDisponibleGrupo() - this.getSelected().getLimitetotal();
                                    Double consumido = gruposUpdate.getConsumido() + this.getSelected().getLimitetotal();
                                    gruposUpdate.setDisponibleGrupo(disponible);
                                    gruposUpdate.setConsumido(consumido);
                                    this.ejbGruposFacade.edit(gruposUpdate);
                                    this.ejbFacade.guardar(this.getSelected());
                                } catch (Exception e) {
                                    JsfUtil.addErrorMessage("Error al registrar el cupo");
                                }

                            } else {
                                Cupos cupoEdit = this.ejbFacade.find(this.getSelected().getId());
                                GruposEconomicos grupoModified = this.getSelected().getCliente().getGrupo();
                                Double disponible = (grupoModified.getDisponibleGrupo() + cupoEdit.getLimitetotal()) - this.getSelected().getLimitetotal();
                                Double Consumido = (grupoModified.getConsumido() - cupoEdit.getLimitetotal()) + this.getSelected().getLimitetotal();
                                grupoModified.setDisponibleGrupo(disponible);
                                grupoModified.setConsumido(Consumido);
                                this.ejbGruposFacade.edit(grupoModified);
                                this.ejbFacade.modificar(this.getSelected());
                            }
                            for (Limitesautorizados limite : this.getSelected().getLimitesautorizadosList()) {
                                if (this.ejbLimite.find(limite.getId()) != null) {
                                    this.ejbLimite.modificar(limite);
                                    //CWS_SIAE_BOT -inicio
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
                                    //CWS_SIAE_BOT -fin 
                                } else {
                                    this.ejbLimite.guardar(limite);
                                    //CWS_SIAE_BOT -inicio

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
                                    //CWS_SIAE_BOT -fin 
                                }
                            }
                            // CupoAut2018 -Inicio
                            logBDMensajes();
                            // CupoAut2018 -Fin                                                                                   
                        } else {
                            JsfUtil.addErrorMessage("El valor del cupo haria que se supera al valor del patrimonio tecnico disponible");
                        }

                    } else {
                        JsfUtil.addErrorMessage("Aun hay sublimites con valor asignado y sin comentarios");
                    }

                } else {
                    JsfUtil.addErrorMessage("El valor del cupo no esta totalmente distribuido");
                }
            } else {
                JsfUtil.addErrorMessage("El valor del cupo no se ha distribuido totalmente");
            }
        } else {

            if (this.ejbFacade.find(this.getSelected().getId()) != null) {
                if (this.ejbFacade.find(this.getSelected().getId()).getLimitetotal() == this.getSelected().getLimitetotal()) {
                    this.ejbFacade.modificar(this.getSelected());
                    JsfUtil.addErrorMessage("El cupo se ha modificado con exito aunque excede el patrimonio tecnico disponible");
                } else {

                    JsfUtil.addErrorMessage("El valor del cupo Supera al valor del patrimonio tecnico disponible");
                }

            } else {
                JsfUtil.addErrorMessage("El valor del cupo Supera al valor del patrimonio tecnico disponible");
            }
        }

    }

    // CupoAut2018 -Inicio    
    public void guardarModificacion(Boolean modificacion) {
        flagGuardar = 2;
        this.getSelected().setEstado(false);
        guardar(modificacion);
    }

    public void guardarCreacion(Boolean modificacion) {
        flagGuardar = 1;
        guardar(modificacion);
    }

    public void logBDMensajes() {
        // CupoAutDual2018 - inicio
        AccionesUsuario accionesUsuario = new AccionesUsuario(this.getSelected().getCliente().getNit(), JsfUtil.Usuario(), new Date());
        // CupoAutDual2018 - fin
        switch (flagGuardar) {
            case 1:
                this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se Creo el cupo y la asignación de las lineas para el cliente: NIT: " + this.getSelected().getCliente().getNit() + " Nombre: " + this.getSelected().getCliente().getNombre() + " Cupo: " + this.getSelected().getLimitetotal() + " Fecha Vencimiento: " + this.getSelected().getFechavencimiento() + " Estado Cupo: " + this.getSelected().getEstadoActualCupo()));
                // CupoAutDual2018 - inicio
                accionesUsuario.setAccionYSubmodulo(ACCION_CREA, MODULO_CREA_CUPOS);
                this.ejbAccionesUsuarioFacade.guardar(accionesUsuario);
                // CupoAutDual2018 - fin
                JsfUtil.addSuccessMessage("Cupo pendiente por autorizar, Guardado con éxito");
                break;
            case 2:
                this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se Modifico el cupo y la asignación de las lineas para el cliente:  NIT: " + this.getSelected().getCliente().getNit() + " Nombre: " + this.getSelected().getCliente().getNombre() + " Cupo: " + this.getSelected().getLimitetotal() + " Fecha Vencimiento: " + this.getSelected().getFechavencimiento() + " Estado Cupo: " + this.getSelected().getEstadoActualCupo()));
                // CupoAutDual2018 - inicio
                accionesUsuario.setAccionYSubmodulo(ACCION_MODIFICA, MODULO_MODIF_CUPOS);
                this.ejbAccionesUsuarioFacade.guardar(accionesUsuario);
                // CupoAutDual2018 - fin
                JsfUtil.addSuccessMessage("Cupo pendiente por autorizar, Guardado con éxito");
                break;
            case 3:
                this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Se Autorizo el cupo y la asignación de las lineas para el cliente:  NIT: " + this.getSelected().getCliente().getNit() + " Nombre: " + this.getSelected().getCliente().getNombre() + " Cupo: " + this.getSelected().getLimitetotal() + " Fecha Vencimiento: " + this.getSelected().getFechavencimiento() + " Estado Cupo: " + this.getSelected().getEstadoActualCupo()));
                // CupoAutDual2018 - inicio
                accionesUsuario.setAccionYSubmodulo(ACCION_AUTORIZA, MODULO_AUTOR_CUPOS);
                this.ejbAccionesUsuarioFacade.guardar(accionesUsuario);
                // CupoAutDual2018 - fin
                JsfUtil.addSuccessMessage("Cupo autorizado, Guardado con éxito");
                break;
        }
    }
    // CupoAut2018 -Fin

    public void clienteSeleccion(int cual) {
        switch (cual) {
            case 1:
                this.clienteNombre = this.clienteNit;
                break;
            case 2:
                this.clienteNit = this.clienteNombre;
                break;
        }
        Calendar fechalimite = Calendar.getInstance();
        Calendar fecharating = Calendar.getInstance();
        Calendar fechabalance = Calendar.getInstance();
        fechalimite.add(fechalimite.YEAR, -1);
        fecharating.setTime(this.clienteNit.getFecharating());
        fechabalance.setTime(this.clienteNit.getFechabalance());

        if (this.clienteNit.getGrupo().getNombre().equalsIgnoreCase(constantes.Constantes.NOMBRE_RESERVADO_GRUPO)) {
            this.setSelected(null);
            JsfUtil.addErrorMessage("El usuario debe tener un grupo asignado diferente a " + constantes.Constantes.NOMBRE_RESERVADO_GRUPO);
        } else if (this.clienteNit.getEstadocliente().getId().equalsIgnoreCase(this.ejbCliente.estadoInactivo())) {
            this.setSelected(null);
            JsfUtil.addErrorMessage("El usuario esta Inactivo");
        } else {
            if (fechabalance.before(fechalimite)) {
                JsfUtil.addErrorMessage("La Fecha de Balance del Cliente Se Encuentra Vencida");
            } else {
                if (fecharating.before(fechalimite)) {
                    JsfUtil.addErrorMessage("La Fecha Rating del Cliente Se Encuentra Vencida");
                } else {
                    this.setSelected(this.ejbFacade.CuposXCliente(this.clienteNit));
                    List<Modalidades> modalidades = this.ejbModalidad.findAll();

                    if (this.ejbFacade.find(this.getSelected().getId()) != null) {
                        // ya existia el cupo
                        this.creacion = true;
                        this.modificacion = false;

                        if (JsfUtil.compararFechasSinTiempo(this.getSelected().getFechavencimiento(), new Date()) == 1) {
                            this.cupoVencido = true;
                            this.getSelected().setEstado(false);
                            JsfUtil.addErrorMessage("El cupo esta vencido");
                        } else {
                            this.cupoVencido = false;
                        }
                        List<Limitesautorizados> limites = this.ejbLimite.LimiteXCupos(this.getSelected());
                        for (Modalidades modalidad : modalidades) {
                            boolean bandera = true;
                            for (Limitesautorizados limite : limites) {

                                if (modalidad.getId().equalsIgnoreCase(limite.getModalidad().getId())) {
                                    bandera = false;
                                }

                            }
                            if (bandera) {
                                Limitesautorizados limiteNuevo = new Limitesautorizados();
                                limiteNuevo.setCupo(this.getSelected());
                                limiteNuevo.setConsumido(0.0);
                                limiteNuevo.setDisponible(0.0);
                                limiteNuevo.setSublimiteautorizado(0.0);
                                limiteNuevo.setModalidad(modalidad);
                                //BSNC-19-0119 Operaciones Especiales - ACT - INI
                                limiteNuevo.setFechavencimiento(this.getSelected().getFechavencimiento());
                                //BSNC-19-0119 Operaciones Especiales - ACT - FIN
                                //this.ejbLimite.guardar(limiteNuevo);
                                this.getSelected().getLimitesautorizadosList().add(limiteNuevo);
                            }
                        }
                    } else {
                        // nuevo cupo
                        this.creacion = false;
                        this.modificacion = true;
                        this.getSelected().setLimitesautorizadosList(new ArrayList<Limitesautorizados>());
                        for (Modalidades modalidad : modalidades) {
                            Limitesautorizados limiteNuevo = new Limitesautorizados();
                            limiteNuevo.setCupo(this.getSelected());
                            limiteNuevo.setConsumido(0.0);
                            limiteNuevo.setDisponible(0.0);
                            limiteNuevo.setSublimiteautorizado(0.0);
                            limiteNuevo.setModalidad(modalidad);
                            //BSNC-19-0119 Operaciones Especiales - ACT - INI
                            limiteNuevo.setFechavencimiento(this.getSelected().getFechavencimiento());
                            //BSNC-19-0119 Operaciones Especiales - ACT - FIN
                            this.getSelected().getLimitesautorizadosList().add(limiteNuevo);
                        }
                    }
                }
            }
        }

    }

    public void clienteSeleccionConsultar(int cual) {
        switch (cual) {
            case 1:
                this.clienteNombre = this.clienteNit;
                break;
            case 2:
                this.clienteNit = this.clienteNombre;
                break;
        }

        if (this.clienteNit.getEstadocliente().getId().equalsIgnoreCase(this.ejbCliente.estadoInactivo())) {
            this.setSelected(null);
            JsfUtil.addErrorMessage("El usuario esta Inactivo");
        } else {
            this.setSelected(this.ejbFacade.CuposXCliente(this.clienteNit));
            List<Modalidades> modalidades = this.ejbModalidad.findAll();

            if (this.ejbFacade.find(this.getSelected().getId()) != null) {
                // ya existia el cupo
                this.creacion = true;
                this.modificacion = false;

                if (JsfUtil.compararFechasSinTiempo(this.getSelected().getFechavencimiento(), new Date()) == 1) {
                    this.cupoVencido = true;
                    this.getSelected().setEstado(false);
                    JsfUtil.addErrorMessage("El cupo esta vencido");
                } else {
                    this.cupoVencido = false;
                }
                List<Limitesautorizados> limites = this.ejbLimite.LimiteXCupos(this.getSelected());
                for (Modalidades modalidad : modalidades) {
                    boolean bandera = true;
                    for (Limitesautorizados limite : limites) {

                        if (modalidad.getId().equalsIgnoreCase(limite.getModalidad().getId())) {
                            bandera = false;
                        }

                    }
                    if (bandera) {
                        Limitesautorizados limiteNuevo = new Limitesautorizados();
                        limiteNuevo.setCupo(this.getSelected());
                        limiteNuevo.setConsumido(0.0);
                        limiteNuevo.setDisponible(0.0);
                        limiteNuevo.setSublimiteautorizado(0.0);
                        limiteNuevo.setModalidad(modalidad);
                        //BSNC-19-0119 Operaciones Especiales - ACT - INI                        
                        limiteNuevo.setFechavencimiento(this.getSelected().getFechavencimiento());
                                //BSNC-19-0119 Operaciones Especiales - ACT - FIN
                        //this.ejbLimite.guardar(limiteNuevo);
                        this.getSelected().getLimitesautorizadosList().add(limiteNuevo);
                    }
                }
            } else {
                // nuevo cupo
                this.creacion = false;
                this.modificacion = true;
                this.getSelected().setLimitesautorizadosList(new ArrayList<Limitesautorizados>());
                for (Modalidades modalidad : modalidades) {
                    Limitesautorizados limiteNuevo = new Limitesautorizados();
                    limiteNuevo.setCupo(this.getSelected());
                    limiteNuevo.setConsumido(0.0);
                    limiteNuevo.setDisponible(0.0);
                    limiteNuevo.setSublimiteautorizado(0.0);
                    limiteNuevo.setModalidad(modalidad);
                    //BSNC-19-0119 Operaciones Especiales - ACT - INI
                    limiteNuevo.setFechavencimiento(this.getSelected().getFechavencimiento());
                    //BSNC-19-0119 Operaciones Especiales - ACT - FIN
                    this.getSelected().getLimitesautorizadosList().add(limiteNuevo);
                }
            }
        }
    }

    public void validarFechas() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.setTime(new Date());
        if (this.getSelected().getFechaalta() == null) {
            this.getSelected().setFechaalta(new Date());
        }
        if (this.getSelected().getFechavencimiento() == null) {
            this.getSelected().setFechavencimiento(this.getSelected().getFechaalta());
        }
        cal2.setTime(this.getSelected().getFechaalta());
        cal3.setTime(this.getSelected().getFechavencimiento());
        // calcular la diferencia en dias

        if ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (60 * 60 * 1000) >= 24) {
            JsfUtil.addSuccessMessage("La fecha de alta debe ser igual o mayor a la de hoy");
            this.getSelected().setFechaalta(new Date());
        }
        if ((cal2.getTimeInMillis() - cal3.getTimeInMillis()) / (60 * 60 * 1000) >= 24) {
            JsfUtil.addSuccessMessage("La fecha de Vencimiento no puede ser menor a la del cupo");
            this.getSelected().setFechavencimiento(this.getSelected().getFechaalta());
        }
    }
    //BSNC-19-0119 Operaciones Especiales - ACT - INI
   public void validarFechaVencimientoOpEspeciales() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.setTime(new Date());
        if (this.limiteActual.getFechavencimiento() == null) {
            this.limiteActual.setFechavencimiento(this.getSelected().getFechavencimiento());
        }
        cal2.setTime(this.limiteActual.getFechavencimiento());
        cal3.setTime(this.getSelected().getFechavencimiento());
        // calcular la diferencia en dias

        if ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (60 * 60 * 1000) >= 24) {
            JsfUtil.addSuccessMessage("La fecha de vencimiento de la linea operaciones especiales debe ser igual o mayor a la de hoy");
            this.getSelected().setFechaalta(new Date());
        }
        if ((cal2.getTimeInMillis() - cal3.getTimeInMillis()) / (60 * 60 * 1000) <= 24) {
            JsfUtil.addSuccessMessage("La fecha de vencimiento de la linea no puede ser mayor a la del cupo");
            this.getSelected().setFechavencimiento(this.getSelected().getFechaalta());
        }
    }
    //BSNC-19-0119 Operaciones Especiales - ACT - FIN

    public void validarFechasModificacion() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        cal1.setTime(new Date());
        if (this.getSelected().getFechaalta() == null) {
            this.getSelected().setFechaalta(new Date());
        }
        if (this.getSelected().getFechavencimiento() == null) {
            this.getSelected().setFechavencimiento(this.getSelected().getFechaalta());
        }
        cal2.setTime(this.getSelected().getFechaalta());
        cal3.setTime(this.getSelected().getFechavencimiento());
        // calcular la diferencia en dias

        if ((cal2.getTimeInMillis() - cal3.getTimeInMillis()) / (60 * 60 * 1000) >= 24) {
            JsfUtil.addSuccessMessage("La fecha de Vencimiento debe ser mayor a la fecha de alta");
            this.getSelected().setFechavencimiento(this.getSelected().getFechaalta());
        }
//BSNC-19-0119 Operaciones Especiales - ACT - INI
//        if(((cal3.getTimeInMillis() - cal1.getTimeInMillis())  / (60 * 60 * 1000) >= 24) && !this.getSelected().isEstado()){
//            this.getSelected().setEstado(true);
//            this.cupoVencido=false;
//        }
    }

    public void llenar() {
        if (this.limiteActual.getComentarios().equals("<br>")) {
            this.limiteActual.setComentarios("");
        }

        if (this.limiteActual.getModalidad().getNombre().equals("Derivados")) {
            this.limiteActual.setPlazo_forwards(this.limiteActual.getPlazo_forwards());
            this.limiteActual.setPlazo_swaps(this.limiteActual.getPlazo_swaps());
            this.limiteActual.setTiposfc(this.limiteActual.getTiposfc());
        } else {
            this.limiteActual.setPlazo_forwards("0");
            this.limiteActual.setPlazo_swaps("0");
            this.limiteActual.setTiposfc("");
        }
        //BSNC-19-0119 Operaciones Especiales - ACT - INI
        if (this.limiteActual.getModalidad().getNombre().equals("Operaciones Especiales")) {
            this.limiteActual.setFechavencimiento(this.limiteActual.getFechavencimiento());
        } else {
            this.limiteActual.setFechavencimiento(this.getSelected().getFechavencimiento());
        }
        //BSNC-19-0119 Operaciones Especiales - ACT - FIN
        this.getSelected().getLimitesautorizadosList().set(this.getSelected().getLimitesautorizadosList().lastIndexOf(this.limiteActual), this.limiteActual);
    }

    public void calcular(Limitesautorizados limiteActual) {
        if (limiteActual.getSublimiteautorizado() >= 0) {
            if (limiteActual.getSublimiteautorizado() >= limiteActual.getConsumido()) {
                Double valor = this.nivelar();
                if (valor > 0) {
                    limiteActual.setDisponible(limiteActual.getSublimiteautorizado() - limiteActual.getConsumido());
                    this.getSelected().setLimiteconsumido(valor);
                    if (this.distribuido()) {
                        JsfUtil.addSuccessMessage("Se Ha distribuido completamente el cupo");
                    }
                } else {
                    JsfUtil.addErrorMessage("El Valor ingresado hace que se exceda el cupo asignado");
                    limiteActual.setSublimiteautorizado(0.0);
                }
            } else {
                limiteActual.setSublimiteautorizado(limiteActual.getConsumido() + limiteActual.getDisponible());
                JsfUtil.addErrorMessage("No se puede bajar el limite autorizado este ya ha sido distribuido");
            }
        } else {
            limiteActual.setSublimiteautorizado(0.0);
            JsfUtil.addErrorMessage("El sublimite no debe ser cero ni menor que cero");
        }

    }

    public void selecionarLimite(Limitesautorizados limiteActual) {
        this.limiteActual = limiteActual;
        if (this.limiteActual.getModalidad().getNombre().equals("Derivados")) {
            flagDerivados = true;
        } else {
            flagDerivados = false;
        }
        //BSNC-19-0119 Operaciones Especiales - ACT - INI
        if (this.limiteActual.getModalidad().getNombre().equals("Operaciones Especiales")) {
            flagOpEspeciales = true;
        } else {
            flagOpEspeciales = false;
        }
        //BSNC-19-0119 Operaciones Especiales - ACT - FIN

    }

    public boolean isFlagDerivados() {
        return flagDerivados;
    }

    public void setFlagDerivados(boolean flagDerivados) {
        this.flagDerivados = flagDerivados;
    }

    //BSNC-19-0119 Operaciones Especiales - ACT - INI
    public boolean isFlagOpEspeciales() {
        return flagOpEspeciales;
    }

    public void setFlagOpEspeciales(boolean flagOpEspeciales) {
        this.flagOpEspeciales = flagOpEspeciales;
    }
    //BSNC-19-0119 Operaciones Especiales - ACT - FIN

    private Double nivelar() {
        Double valor = 0.0;
        for (Limitesautorizados limite : this.getSelected().getLimitesautorizadosList()) {
            valor += limite.getSublimiteautorizado();
        }
        if (valor > this.getSelected().getLimitetotal()) {
            return 0.0;
        }
        return valor;
    }

    private boolean distribuido() {
        Double valor = 0.0;
        for (Limitesautorizados limite : this.getSelected().getLimitesautorizadosList()) {
            valor += limite.getSublimiteautorizado();
        }
        if (valor != this.getSelected().getLimitetotal()) {
            return false;
        }
        return true;
    }

    public boolean isCreacion() {
        return creacion;
    }

    public void setCreacion(boolean creacion) {
        this.creacion = creacion;
    }

    public boolean isModificacion() {
        return modificacion;
    }

    public void setModificacion(boolean modificacion) {
        this.modificacion = modificacion;
    }

    public Double getConsumidoTotal() {
        List<Limitesautorizados> limites = this.ejbLimite.LimiteXCupos(this.getSelected());
        Double total = 0.0;
        for (Limitesautorizados lim : limites) {
            total += lim.getConsumido();
        }
        return total;
    }

    public void verificaPatrimonio(Boolean modifiedCupo) {
        Double consumidoGrupo = this.getSelected().getCliente().getGrupo().getConsumido();
        Double disponibleGrupo = this.getSelected().getCliente().getGrupo().getDisponibleGrupo();
        Double patrimoGrupo = this.getSelected().getCliente().getGrupo().getCupo();

        if (!modifiedCupo) {
            if (this.getSelected().getLimitetotal() > 0) {
                if (this.getSelected().getLimitetotal() + consumidoGrupo > patrimoGrupo) {
                    this.getSelected().setLimitetotal(0.0);
                    JsfUtil.addErrorMessage("El valor asignado para el cupo supera el patrimonio tecnico disponible");
                }
            } else {
                this.getSelected().setLimitetotal(0.0);
                JsfUtil.addErrorMessage("El valor del limite total del cupo debe ser mayor a cero");
            }
        } else {
            if (this.getSelected().getLimitetotal() > 0) {

                Cupos cuposDatosAnt = ejbFacade.find(this.getSelected().getId());
                GruposEconomicos grupoDato = this.getSelected().getCliente().getGrupo();

                Double disponible = grupoDato.getDisponibleGrupo() + cuposDatosAnt.getLimitetotal();

                if (this.getSelected().getLimitetotal() > disponible) {
                    this.getSelected().setLimitetotal(0.0);
                    JsfUtil.addErrorMessage("El valor del limite total supera el patrimonio tecnico disponible");
                }

            } else {
                this.getSelected().setLimitetotal(0.0);
                JsfUtil.addErrorMessage("El valor del limite total del cupo debe ser mayor a cero");
            }
        }
    }

    private boolean comentarios() {
        try {
            for (Limitesautorizados limite : this.getSelected().getLimitesautorizadosList()) {
                if (limite.getSublimiteautorizado() > 0.0 && (limite.getComentarios().isEmpty() || limite.getComentarios().equals("<br>"))) {
                    return false;
                } else {
                    if (limite.getSublimiteautorizado() > 0.0 && limite.getComentarios() == null) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private Double totalGrupo() {
        try {
            Cupos cupo = this.ejbFacade.cupoXId(this.getSelected().getId());
            Double resta = 0.0;
            if (cupo != null) {
                resta = cupo.getLimitetotal();
            }
            Double valor = this.ejbCliente.clientesXGrupos(this.clienteNit.getGrupo());
            if (valor == null) {
                valor = 0.0;
            }
            return valor - resta;
        } catch (Exception ex) {
            return 0.0;
        }
    }

    // CupoAut2018 -Inicio
    public void autorizaCupoGuardar() {
        // CupoAutDual2018 - inicio
        if (validaAutorizarCupoUsuario()) {
            // CupoAutDual2018 - fin
            if (validaFechaVencimiento() && validaLimiteTotal()) {
                clienteSeleccion(1);
                if (!cupoVencido) {
                    this.getSelected().setEstado(true);
                    flagGuardar = 3;
                    guardar(false);
                }
            }
        }
    }

    // CupoAutDual2018 - inicio
    public boolean validaAutorizarCupoUsuario() {
        this.clienteNoPermitidoAutorizar = ejbAccionesUsuarioFacade.clienteNoPermitido(this.getSelected().getCliente(), JsfUtil.Usuario());
        if (this.clienteNoPermitidoAutorizar != null) {
            JsfUtil.addErrorMessage("No puede ser autorizado por el mismo usuario que creo o modifico el cupo");
            return false;
        }
        return true;
    }
    // CupoAutDual2018 - fin

    public boolean validaFechaVencimiento() {
        if (autorizaFechaVencimiento.compareTo(this.getSelected().getFechavencimiento()) != 0) {
            JsfUtil.addErrorMessage("Fecha de vencimiento no coincide, Favor validar ");
            return false;
        }
        return true;
    }

    public boolean validaLimiteTotal() {
        if (autorizaLimiteTotal != this.getSelected().getLimitetotal()) {
            JsfUtil.addErrorMessage("Limite total no coincide, Favor validar");
            return false;
        }
        return true;
    }

    public void getIdParam(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if (params.containsKey("id")) {
            String id = params.get("id");
            load(id);
        }
    }

    public void load(String id) {
        clienteNit = ejbClienteFacade.find(id);
        clienteSeleccion(1);
    }

    public boolean validaEstadoFlagActivo(boolean estadoActualFlag) {
        boolean estadoActualFlagAux = true;
        if (estadoActualFlag) {
            estadoActualFlagAux = false;
        }
        return estadoActualFlagAux;
    }
    // CupoAut2018 -Fin   
}
