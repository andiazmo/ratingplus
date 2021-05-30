/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajesUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author x302266
 */
public class mensajesUtil {
    
    public static void mensaje(String codigo, String mensaje, String detalle, FacesMessage.Severity severity){
        FacesMessage mensajeF = new FacesMessage(severity, mensaje, detalle);
        FacesContext.getCurrentInstance().addMessage(codigo, mensajeF);
    }
    
    public static void errorMensaje(String codigo, String mensaje, String detalle){
        mensaje(codigo, mensaje, detalle, FacesMessage.SEVERITY_ERROR);
    };
    
}
