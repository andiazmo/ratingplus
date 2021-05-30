/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import entidades.TipoDocumento;
import fachadas.TipoDocumentoFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author 0000
 */
@ManagedBean(name = "tipodocumentocontroller")
@ViewScoped
public class TipoDocumentoController extends AbstractController<TipoDocumento>{
    @EJB
    private TipoDocumentoFacade ejbFacade;
    
  @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
    }

    public TipoDocumentoController() {
        // Inform the Abstract parent controller of the concrete EstadoFeve?cap_first Entity
        super(TipoDocumento.class);
    }
}




