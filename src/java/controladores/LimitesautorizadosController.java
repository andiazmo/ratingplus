package controladores;

import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.Clientes;
import entidades.Desembolsos;
import entidades.Limitesautorizados;
import entidades.Pagos;
import entidades.Vclientes;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.CuposFacade;
import fachadas.DesembolsosFacade;
import fachadas.LimitesautorizadosFacade;
import fachadas.PagosFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "limitesautorizadosController")
@ViewScoped
public class LimitesautorizadosController extends AbstractController<Limitesautorizados> {

    @EJB
    private LimitesautorizadosFacade ejbFacade;
    @EJB
    private CuposFacade ejbFacadeCupo;
    @EJB
    private ClientesFacade ejbFacadeCliente;
    @EJB
    private DesembolsosFacade ejbFacadeDesembolso;
    @EJB
    private PagosFacade ejbFacadePago;
    @EJB
    private AuditoriaFacade ejbAuditoria;

    private GruposEconomicosController gruposEconomicosController;

    private Clientes cliente;

    private List<Limitesautorizados> limites;

    private Desembolsos desembolso;

    private Pagos pago;

    private String tipobusqueda;

    private Double limiteGlobal;

    private Double limiteGlobalGeneral;

    /**
     * Initialize the concrete Limitesautorizados controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.cliente = new Clientes();
        this.limites = new ArrayList<Limitesautorizados>();
        this.tipobusqueda = "nombre";
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public LimitesautorizadosController() {
        // Inform the Abstract parent controller of the concrete Limitesautorizados?cap_first Entity
        super(Limitesautorizados.class);
    }

    public String getTipobusqueda() {
        return tipobusqueda;
    }

    public void setTipobusqueda(String tipobusqueda) {
        this.tipobusqueda = tipobusqueda;
    }

    public void guardar() {
        if (this.ejbFacade.LimiteXCuposXModalidad(this.cliente.getCuposList().get(0), this.getSelected().getModalidad()).size() == 0) {
            if ((this.cliente.getCuposList().get(0).getLimitetotal() - this.cliente.getCuposList().get(0).getLimiteconsumido()) >= this.getSelected().getSublimiteautorizado()) {
                this.getSelected().setDisponible(this.getSelected().getSublimiteautorizado());
                this.getSelected().setCupo(this.cliente.getCuposList().get(0));
                this.ejbFacade.guardar(this.getSelected());
                this.cliente.getCuposList().get(0).setLimiteconsumido(this.cliente.getCuposList().get(0).getLimiteconsumido() + this.getSelected().getSublimiteautorizado());
                this.cliente.getCuposList().get(0).getLimitesautorizadosList().add(this.getSelected());
                this.ejbFacadeCupo.modificar(this.cliente.getCuposList().get(0));
                this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Limite creado correctamente  "));

                JsfUtil.addSuccessMessage("Limite Creado Correctamente");
            } else {
                JsfUtil.addErrorMessage("El limite autorizado supera el limite disponible");
            }
        } else {
            JsfUtil.addErrorMessage("Existe un limite vigente para esta modalidad aun");
        }
    }

    public void modificar() {
        Double reponer = this.cliente.getCuposList().get(0).getLimiteconsumido() - this.ejbFacade.find(this.getSelected().getId()).getSublimiteautorizado();
        this.cliente.getCuposList().get(0).setLimiteconsumido(reponer);
        if ((this.cliente.getCuposList().get(0).getLimitetotal() - this.cliente.getCuposList().get(0).getLimiteconsumido()) >= this.getSelected().getSublimiteautorizado()) {
            this.getSelected().setDisponible(this.getSelected().getSublimiteautorizado() - this.getSelected().getConsumido());
            this.cliente.getCuposList().get(0).setLimiteconsumido(this.cliente.getCuposList().get(0).getLimiteconsumido() + this.getSelected().getSublimiteautorizado());
            for (int i = 0; i < this.cliente.getCuposList().get(0).getLimitesautorizadosList().size(); i++) {
                if (this.cliente.getCuposList().get(0).getLimitesautorizadosList().get(i).getId().equalsIgnoreCase(this.getSelected().getId())) {
                    this.cliente.getCuposList().get(0).getLimitesautorizadosList().set(i, this.getSelected());
                }
            }
            this.ejbFacadeCupo.modificar(this.cliente.getCuposList().get(0));
            this.ejbFacade.modificar(this.getSelected());
            JsfUtil.addSuccessMessage("El limite se ha modifiocado con exito");
        } else {
            JsfUtil.addErrorMessage("El limite autorizado supera el limite disponible");
        }
    }

    public void borrar() {
        if (this.getSelected().getConsumido() == 0.0) {
            this.cliente.getCuposList().get(0).setLimiteconsumido(this.cliente.getCuposList().get(0).getLimiteconsumido() - this.getSelected().getSublimiteautorizado());

            this.ejbFacade.borrar(this.getSelected());
            for (int i = 0; i < this.cliente.getCuposList().get(0).getLimitesautorizadosList().size(); i++) {
                if (this.cliente.getCuposList().get(0).getLimitesautorizadosList().get(i).getId().equalsIgnoreCase(this.getSelected().getId())) {
                    this.cliente.getCuposList().get(0).getLimitesautorizadosList().remove(i);
                }
            }

            this.setSelected(new Limitesautorizados());
            this.ejbFacadeCupo.modificar(this.cliente.getCuposList().get(0));
            JsfUtil.addSuccessMessage("Limite Borrado con exito");
        } else {
            JsfUtil.addErrorMessage("El limite ya ha sido utilisado no se puede borrar");
        }
    }

    public List<Clientes> clientesAutocompletar(int tipo) {
        String nombre = "";
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:dd_input") != null) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:dd_input"));
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosListForm:nit_input") != null && tipo == 1) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosListForm:nit_input"));
            tipobusqueda = "nit";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosListForm:nombre_input") != null && tipo == 2) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosListForm:nombre_input"));
            tipobusqueda = "nombre";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosConListForm:nit_input") != null && tipo == 1) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosConListForm:nit_input"));
            tipobusqueda = "nit";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosConListForm:nombre_input") != null && tipo == 2) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosConListForm:nombre_input"));
            tipobusqueda = "nombre";
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DesembolsosListForm:nit_input") != null && tipo == 1) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DesembolsosListForm:nit_input"));
            tipobusqueda = "nit";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DesembolsosListForm:nombre_input") != null && tipo == 2) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DesembolsosListForm:nombre_input"));
            tipobusqueda = "nombre";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("CuposListForm:nit_input") != null && tipo == 1) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("CuposListForm:nit_input"));
            tipobusqueda = "nit";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("CuposListForm:nombre_input") != null && tipo == 2) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("CuposListForm:nombre_input"));
            tipobusqueda = "nombre";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nit_input") != null && tipo == 1) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nit_input"));
            tipobusqueda = "nit";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nombre_input") != null && tipo == 2) {
            nombre = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nombre_input"));
            tipobusqueda = "nombre";
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:tipo") != null) {
            tipobusqueda = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:tipo"));
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DesembolsosListForm:tipo") != null) {
            tipobusqueda = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DesembolsosListForm:tipo"));
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosListForm:tipo") != null) {
            tipobusqueda = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PagosListForm:tipo"));
        }

        if (nombre.isEmpty()) {
            return this.ejbFacadeCliente.findAll();
        } else if (tipobusqueda.equals("nombre")) {
            return this.ejbFacadeCliente.clientesXNombre(nombre);
        } else if (tipobusqueda.equals("nit")) {
            return this.ejbFacadeCliente.clientesXNit(nombre);
        }

        return null;

    }

    public void cargaDatos() {
        System.out.println("hola");
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public List<Limitesautorizados> getLimites() {
        return limites;
    }

    public void setLimites(List<Limitesautorizados> limites) {
        this.limites = limites;
    }

    public Desembolsos getDesembolso() {
        return desembolso;
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public void setDesembolso(Desembolsos desembolso) {
        this.desembolso = desembolso;
    }

    public void preparardesembolso() {
        this.desembolso = new Desembolsos();
        this.desembolso.setLimite(this.getSelected());
    }

    public void guardarDesembolso() {
        if (this.desembolso.getValor() > 0) {
            if ((this.getSelected().getConsumido() + this.desembolso.getValor()) <= this.getSelected().getSublimiteautorizado()) {
                if (this.desembolso.getValor() <= this.getLimiteGlobal()) {
                    if ((this.desembolso.getValor() + this.consumidoActualCupo()) <= this.getLimiteGlobal()) {

                        this.getSelected().setConsumido(this.getSelected().getConsumido() + this.desembolso.getValor());
                        this.getSelected().setDisponible(this.getSelected().getDisponible() - this.desembolso.getValor());
                        this.ejbFacade.modificar(this.getSelected());
                        this.ejbFacadeDesembolso.guardar(this.desembolso);
                        this.listaSublimites();
                        this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Desembolso realizado por valor de:  " + this.desembolso.getValor() + " para el sublimite de la modalidad " + this.getSelected().getModalidad().getNombre() + " del cliente " + this.getSelected().getCupo().getCliente().getNit()));

                        JsfUtil.addSuccessMessage("Registro Guardado con Exito");
                    } else {
                        JsfUtil.addErrorMessage("El valor insertado hace que el total consumido del cupo supere el valor de patrimonio legal");
                    }

                } else {
                    JsfUtil.addErrorMessage("El valor insertado para desembolso excede el valor del limite legal distribuido");
                }

            } else {
                JsfUtil.addErrorMessage("El valor insertado para desembolso excede el sublimite disponible");
            }
        } else {
            JsfUtil.addErrorMessage("Desembolso debe ser mayor a cero");
        }

    }

    public void guardarPagos() {
        if (this.pago.getValor() > 0) {
            if (this.validarPago()) {
                if ((this.getSelected().getConsumido() >= this.pago.getValor())) {
                    this.getSelected().setConsumido(this.getSelected().getConsumido() - this.pago.getValor());
                    this.getSelected().setDisponible(this.getSelected().getDisponible() + this.pago.getValor());
                    this.ejbFacade.modificar(this.getSelected());
                    this.ejbFacadePago.guardar(this.pago);
                    this.listaSublimites();
                    this.listaPagos();
                    this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Pago realizado correctamente por valor " + this.pago.getValor() + " para la modalidad " + this.getSelected().getModalidad().getNombre() + " del cliente " + this.getSelected().getCupo().getCliente().getNit()));

                    JsfUtil.addSuccessMessage("Registro Guardado con Exito");
                } else {
                    JsfUtil.addErrorMessage("El Pago supera el valor consumido");
                }
            } else {
                JsfUtil.addErrorMessage("El valor del pago excede el valor desembolsado");
            }
        } else {
            this.pago.setValor(0.0);
            JsfUtil.addErrorMessage("El valor del pago debe ser mayor a cero");
        }
    }

    public void listaSublimites() {
        if (this.getSelected().getSublimiteautorizado() > 0.0) {
            this.getSelected().setDesembolsosList(this.ejbFacadeDesembolso.desembolsosXLimite(this.getSelected()));
        } else {
            JsfUtil.addSuccessMessage("El Sublimite autorizado esta en cero no se pueden realizar operaciones");
        }
    }

    public void listaPagos() {
        if (this.desembolso != null) {
            this.desembolso.setPagosList(this.ejbFacadePago.pagosXLimite(this.desembolso));
        }
    }

    public void modificarDesembolso() {
        if (this.desembolso.getValor() > 0) {
            if (this.desembolso.getValor() <= this.getLimiteGlobal()) {
                Desembolsos des = this.ejbFacadeDesembolso.find(this.desembolso.getId());
                this.getSelected().setConsumido(this.getSelected().getConsumido() - des.getValor());
                this.getSelected().setDisponible(this.getSelected().getDisponible() + des.getValor());

                if ((this.getSelected().getConsumido() + this.desembolso.getValor()) <= this.getSelected().getSublimiteautorizado()) {
                    this.getSelected().setConsumido(this.getSelected().getConsumido() + this.desembolso.getValor());
                    this.getSelected().setDisponible(this.getSelected().getDisponible() - this.desembolso.getValor());
                    this.ejbFacade.modificar(this.getSelected());
                    this.ejbFacadeDesembolso.modificar(this.desembolso);
                    /*for(int i=0; i<this.cliente.getCuposList().get(0).getLimitesautorizadosList().size(); i++){
                     if(this.cliente.getCuposList().get(0).getLimitesautorizadosList().get(i).getId().equalsIgnoreCase(this.getSelected().getId()))
                     this.cliente.getCuposList().get(0).getLimitesautorizadosList().set(i, this.getSelected());
                     }*/
                    this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Desembolso modificado por valor de: " + this.desembolso.getValor() + " para la modalidad " + this.getSelected().getModalidad().getNombre() + " del cliente " + this.getSelected().getCupo().getCliente().getNit()));

                    this.listaSublimites();
                    JsfUtil.addSuccessMessage("Registro Modificado con Exito");
                } else {
                    JsfUtil.addErrorMessage("El valor insertado para desembolso excede el sublimite disponible");
                }
            } else {
                JsfUtil.addErrorMessage("El valor insertado para desembolso excede el valor del limite legal distribuido");
            }
        } else {
            JsfUtil.addErrorMessage("El valor del desembolso debe ser mayor a cero");
        }

    }

    public void modificarPagos() {
        if (this.validarPago()) {
            Pagos pag = this.ejbFacadePago.find(this.pago.getId());
            this.getSelected().setConsumido(this.getSelected().getConsumido() + pag.getValor());
            this.getSelected().setDisponible(this.getSelected().getDisponible() - pag.getValor());
            if (this.getSelected().getConsumido() >= this.pago.getValor()) {
                this.getSelected().setConsumido(this.getSelected().getConsumido() - this.pago.getValor());
                this.getSelected().setDisponible(this.getSelected().getDisponible() + this.pago.getValor());
                this.ejbFacade.modificar(this.getSelected());
                this.ejbFacadePago.modificar(this.pago);
                /*for(int i=0; i<this.cliente.getCuposList().get(0).getLimitesautorizadosList().size(); i++){
                 if(this.cliente.getCuposList().get(0).getLimitesautorizadosList().get(i).getId().equalsIgnoreCase(this.getSelected().getId()))
                 this.cliente.getCuposList().get(0).getLimitesautorizadosList().set(i, this.getSelected());
                 }*/
                this.listaPagos();
                this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Pago realizado correctamente por valor " + this.pago.getValor() + " para la modalidad " + this.getSelected().getModalidad().getNombre() + " del cliente " + this.getSelected().getCupo().getCliente().getNit()));

                JsfUtil.addSuccessMessage("Registro Modificado con Exito");
            } else {
                JsfUtil.addErrorMessage("El valor insertado Para el pago excede el limite consumido");
            }
        } else {
            JsfUtil.addErrorMessage("El valor del pago excede el valor que se deberia pagar por el desembolso");
        }

    }

    public void borrarDesembolso() {
        this.getSelected().setConsumido(this.getSelected().getConsumido() - this.desembolso.getValor());
        this.getSelected().setDisponible(this.getSelected().getDisponible() + this.desembolso.getValor());
        this.ejbFacade.modificar(this.getSelected());
        this.ejbFacadeDesembolso.borrar(this.desembolso);
        if (this.getSelected().getCupo().getLimitesautorizadosList().size() > 0) {
            for (int i = 0; i < this.getSelected().getCupo().getLimitesautorizadosList().size(); i++) {
                if (this.getSelected().getCupo().getLimitesautorizadosList().get(i).getId().equalsIgnoreCase(this.getSelected().getId())) {
                    this.getSelected().getCupo().getLimitesautorizadosList().set(i, this.getSelected());
                }
            }
        }
        this.listaSublimites();
        this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "desembolso borrado por valor " + this.desembolso.getValor() + " para la modalidad " + this.getSelected().getModalidad().getNombre() + " del cliente " + this.getSelected().getCupo().getCliente().getNit()));

        JsfUtil.addSuccessMessage("Registro Borrado con Exito");
    }

    public void borrarPago(Pagos pago) {
        this.getSelected().setConsumido(this.getSelected().getConsumido() + pago.getValor());
        this.getSelected().setDisponible(this.getSelected().getDisponible() - pago.getValor());
        this.ejbFacade.modificar(this.getSelected());
        this.ejbFacadePago.borrar(pago);
        for (int i = 0; i < this.getSelected().getCupo().getLimitesautorizadosList().size(); i++) {
            if (this.getSelected().getCupo().getLimitesautorizadosList().get(i).getId().equalsIgnoreCase(this.getSelected().getId())) {
                this.getSelected().getCupo().getLimitesautorizadosList().set(i, this.getSelected());
            }
        }
        this.listaPagos();
        this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(), "Pago borrado correctamente por valor " + pago.getValor() + " para la modalidad " + this.getSelected().getModalidad().getNombre() + " del cliente " + this.getSelected().getCupo().getCliente().getNit()));

        JsfUtil.addSuccessMessage("Registro Borrado con Exito");
    }

    public void prepararpago(Desembolsos desembolso) {
        this.pago = new Pagos();
        this.pago.setDesembolso(desembolso);
        this.desembolso = desembolso;

    }

    public void reporte() {
        String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nit_input");

        List<Vclientes> resultados = new ArrayList<Vclientes>();
        if (!nombre.isEmpty()) {
            resultados = ejbFacade.reporte(nombre);
        } else {
            resultados = ejbFacade.reporteTodos();
        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(resultados);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/limite.jasper");
        JasperPrint jasperPrint;
        try {
            System.out.println("Entro al reporte");
            //FIXPACK1 - inicio
            HashMap has = new HashMap();
            String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
            has.put("url", url);
            jasperPrint = JasperFillManager.fillReport(reportPath, has, beanCollectionDataSource);
            //FIXPAC1 - fin
            HttpServletResponse response = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"limites.pdf\"");
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

    public void reporteXLS() {
        String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("LimitesautorizadosListForm:nit_input");

        List<Vclientes> resultados = new ArrayList<Vclientes>();
        if (!nombre.isEmpty()) {
            resultados = ejbFacade.reporte(nombre);
        } else {
            resultados = ejbFacade.reporteTodos();
        }
        //FIXPACK1 - inicio
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(resultados);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/limite.jasper");
        HashMap has = new HashMap();
        String url = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/logo.jpg");
        has.put("url", url);
        JsfUtil.reporteXLS(beanCollectionDataSource, has, reportPath, "limites");
        //FIXPACK1 - fin

        // JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(resultados);
        // String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/limite.jasper");
        // JsfUtil.reporteXLS(beanCollectionDataSource, new HashMap(), reportPath, "limites");
        //ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String realPath = ctx.getRealPath("/") + "limites";
        //JsfUtil.downloadFile(resultados, realPath);
    }

    private boolean validarPago() {
        Double valor = 0.0;
        for (Pagos pag : this.pago.getDesembolso().getPagosList()) {
            valor += pag.getValor();
        }
        valor += this.pago.getValor();
        if (valor > this.pago.getDesembolso().getValor()) {
            return false;
        }
        return true;

    }

    public void limpiaDesembolso() {
        this.desembolso = new Desembolsos();
        this.listaSublimites();
    }

    public void validarFechasDesembolso() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(this.getSelected().getCupo().getFechaalta());
        cal2.setTime(this.desembolso.getFecha());
        if ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (60 * 60 * 1000) >= 24) {
            JsfUtil.addSuccessMessage("La fecha del desembolso no puede ser mayor a la del cupo");
            this.desembolso.setFecha(this.getSelected().getCupo().getFechaalta());
        }
        cal1.setTime(new Date());
        if ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (60 * 60 * 1000) > 0) {
            JsfUtil.addSuccessMessage("La fecha del desembolso no puede ser mayor a la de hoy");
            this.desembolso.setFecha(new Date());
        }

    }

    public void validarFechasPago() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(this.desembolso.getFecha());
        cal2.setTime(this.pago.getFecha());
        if ((cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (60 * 60 * 1000) >= 24) {
            JsfUtil.addSuccessMessage("La fecha del pago debe ser mayor a la del Desembolso");
            this.pago.setFecha(this.desembolso.getFecha());
        }
        cal1.setTime(new Date());
        if ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (60 * 60 * 1000) >= 24) {
            JsfUtil.addSuccessMessage("La fecha no puede ser mayor a la del día actual");
            this.pago.setFecha(new Date());
        }

    }

    public void seleccionarPago(Pagos pago) {
        this.pago = pago;
    }

    public Double getLimiteGlobalGeneral() {
        this.limiteGlobal = Double.parseDouble(this.ejbFacade.limiteGlobal().getValor());
        return this.limiteGlobal;
    }

    public void setLimiteGlobalGeneral(Double limiteGlobalGeneral) {
        this.limiteGlobalGeneral = limiteGlobalGeneral;
    }

    public Double getLimiteGlobal() {
        this.limiteGlobal = this.getSelected().getCupo().getCliente().getGrupo().getCupo();
        return this.limiteGlobal;
    }

    public void setLimiteGlobal(Double limiteGlobal) {
        this.limiteGlobal = limiteGlobal;
    }

    public void guardarGlobal() {

        FacesContext context = FacesContext.getCurrentInstance();
        gruposEconomicosController = context.getApplication().
                evaluateExpressionGet(context, "#{gruposEconomicosController}",
                        GruposEconomicosController.class);

        String valorAnterior = this.ejbFacade.guardarGlobal(limiteGlobalGeneral.toString());
        JsfUtil.addSuccessMessage("Limite distribuido legal modificado con exito");

        actualizarGruposEconomicos(valorAnterior, limiteGlobalGeneral.toString());
    }

    private void actualizarGruposEconomicos(String valorAnterior, String valorActual) {
        FacesContext context = FacesContext.getCurrentInstance();
        gruposEconomicosController = context.getApplication().
                evaluateExpressionGet(context, "#{gruposEconomicosController}",
                        GruposEconomicosController.class);
        gruposEconomicosController.modificarPatrimonio(valorAnterior, valorActual);

    }

    private Double consumidoActualCupo() {
        Double valor = 0.0;
        for (Limitesautorizados limite : this.getSelected().getCupo().getLimitesautorizadosList()) {
            valor += limite.getConsumido();
        }
        return valor;
    }

    public void validarDesembolso() {
        if (this.desembolso.getValor() > 0) {
            if (this.getSelected().getDisponible() < this.desembolso.getValor()) {
                this.desembolso.setValor(0);
                JsfUtil.addErrorMessage("El valor del desembolso supera el disponible dela linea");
            } else {
                if (this.desembolso.getValor() > this.getLimiteGlobal()) {
                    this.desembolso.setValor(0);

                    JsfUtil.addErrorMessage("El valor del desembolso supera el patrimonio tecnico");
                }

                if (this.getSelected().getConsumido() + this.desembolso.getValor() > this.getLimiteGlobal()) {
                    this.desembolso.setValor(0);
                    JsfUtil.addErrorMessage("El valor del Consumido superaría el patrimonio tecnico");
                }

                if ((this.desembolso.getValor() + consumidoGrupo()) > this.getLimiteGlobal()) {
                    this.desembolso.setValor(0);
                    JsfUtil.addErrorMessage("El Consumido  del grupo economico superaría el patrimonio tecnico");
                }

            }
        } else {
            this.desembolso.setValor(0.0);
            JsfUtil.addErrorMessage("El valor no puede ser menor que cero");
        }
    }

    private Double consumidoGrupo() {
        Double valor = 0.0;
        for (Clientes cliente : this.cliente.getGrupo().getClientesList()) {
            valor += cliente.getCuposList().get(0).getLimiteconsumido();
        }
        return valor;
    }

    public void validarDesembolsoEdicion() {
        if (this.desembolso.getValor() > 0) {
            Desembolsos desem = this.ejbFacadeDesembolso.buscaDesembolso(this.desembolso.getId());
            if ((this.getSelected().getDisponible() + desem.getValor()) < this.desembolso.getValor()) {
                this.desembolso.setValor(0);
                JsfUtil.addErrorMessage("El valor del desembolso supera el disponible de la linea");
            }
            if (this.desembolso.getValor() > this.getLimiteGlobal()) {
                this.desembolso.setValor(desem.getValor());
                JsfUtil.addErrorMessage("El valor del desembolso supera el patrimonio tecnico");
            }

            if ((this.desembolso.getValor() + consumidoGrupo() - desem.getValor()) > this.getLimiteGlobal()) {
                this.desembolso.setValor(desem.getValor());
                JsfUtil.addErrorMessage("El valor del desembolso haria que el consumido del grupo supere  el patrimonio tecnico");
            }

        } else {
            this.desembolso.setValor(0.0);

            JsfUtil.addErrorMessage("El valor no puede ser menor que cero");
        }

    }

    public void seteoDesembolso(Desembolsos desembolso) {
        this.desembolso = desembolso;
    }

    public void selecionarLimite(Limitesautorizados limite) {
        this.setSelected(limite);
    }

}
