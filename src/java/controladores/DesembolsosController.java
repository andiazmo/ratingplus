package controladores;

import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.Desembolsos;
import entidades.Limitesautorizados;
import fachadas.AuditoriaFacade;
import fachadas.DesembolsosFacade;
import fachadas.LimitesautorizadosFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "desembolsosController")
@ViewScoped
public class DesembolsosController extends AbstractController<Desembolsos> {

    @EJB
    private DesembolsosFacade ejbFacade;
    @EJB
    private LimitesautorizadosFacade ejbFacadeLimite;
    @EJB
    private AuditoriaFacade ejbAuditoria;
 
    private Desembolsos desembolso;
    
    private Limitesautorizados limite;
    
 

    
    /**
     * Initialize the concrete Desembolsos controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public DesembolsosController() {
        // Inform the Abstract parent controller of the concrete Desembolsos?cap_first Entity
        super(Desembolsos.class);
    }

    public Desembolsos getDesembolso() {
        return desembolso;
    }

    public void setDesembolso(Desembolsos desembolso) {
        this.desembolso = desembolso;
    }

    public DesembolsosFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DesembolsosFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Limitesautorizados getLimite() {
        return limite;
    }

    public void setLimite(Limitesautorizados limite) {
        this.limite = limite;
    }


    public void guardarDesembolso(ActionEvent event) {
        if (this.getSelected() != null) {
            if (this.getSelected().getLimite().getDisponible() >= this.getSelected().getValor()) {
                this.ejbFacade.create(this.getSelected());
                this.getSelected().getLimite().setDisponible(this.getSelected().getLimite().getDisponible()-this.getSelected().getValor());
                this.getSelected().getLimite().setConsumido(this.getSelected().getLimite().getConsumido()+ this.getSelected().getValor());
                this.ejbFacadeLimite.modificar(this.getSelected().getLimite());
                if (!isValidationFailed()) {
                   this.ejbAuditoria.guardar(new Auditoria(JsfUtil.Usuario().getNombre(),"Se realizo un desembolso por valor de  " + this.getSelected().getValor() + " para el sublimite de la modalidad " + this.getSelected().getLimite().getModalidad().getNombre()));
                    String successMessage = "El Desembolso fue creado";
                    JsfUtil.addSuccessMessage(successMessage);
                }else{
                String successMessage = "No se pudo crear el desembolso";
                    JsfUtil.addSuccessMessage(successMessage);
                }
            }else{
                    String successMessage = "El Valor desembolsado surepa el  Limite disponible";
                    JsfUtil.addSuccessMessage(successMessage);
            }
        }
    }
 }
    
    

