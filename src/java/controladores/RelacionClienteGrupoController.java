/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entidades.RelacionClienteGrupo;
import fachadas.RelacionClienteGrupoFacade;

/**
 *
 * @author x302266
 */
@ManagedBean(name = "relacionClienteGrupoController")
@RequestScoped
public class RelacionClienteGrupoController extends AbstractController<RelacionClienteGrupo> {

    @EJB
    private RelacionClienteGrupoFacade relacionFacade;

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(relacionFacade);

    }
    
    public void resetSelected(){
        this.setSelected(null);
    }
    
}
