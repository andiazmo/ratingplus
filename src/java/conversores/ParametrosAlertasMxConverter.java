/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

import controladores.util.JsfUtil;
import entidades.ParametrosAlertasMx;
import fachadas.ParametrosAlertasMxFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author x217287
 */
@ManagedBean
public class ParametrosAlertasMxConverter {
    
    @EJB
    private  ParametrosAlertasMxFacade ejbFacade;
    
    

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.String value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof ParametrosAlertasMx) {
            ParametrosAlertasMx o = (ParametrosAlertasMx) object;
            return String.valueOf(o.getIdparametroalerta());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, 
                    "object {0} is of type {1}; expected type: {2}", 
                    new Object[]{object, object.getClass().getName(), ParametrosAlertasMx.class.getName()});
            return null;
        }
    }
}
