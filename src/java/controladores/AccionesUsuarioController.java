/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : BSNC-18-0119 - Cupos Auditoria Dual 2018
 *Programador: Wittman Gutiérrez
 *Fecha del creacion : 26-07-2018
 --------------------------------------------------------------------------------
 */
package controladores;

import entidades.AccionesUsuario;
import fachadas.AccionesUsuarioFacade;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wittman Gutiérrez
 */
public class AccionesUsuarioController extends AbstractController<AccionesUsuario> {

    @EJB
    private AccionesUsuarioFacade ejbFacade;

    @Override
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
    }

    public AccionesUsuarioController() {
        super(AccionesUsuario.class);
    }

}
