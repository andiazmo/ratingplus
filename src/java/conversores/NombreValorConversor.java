/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

import DTO.NombreValor;
import controladores.util.JsfUtil;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class NombreValorConversor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        NombreValor item = findValue(value);
        return item;
    }
/**
 * Metodo que se encarga de obtener los valores definido por una 
 * propiedad en el archivo .properties y buscar un Objeto de tipo NombreValor 
 * segun un parametro de busqueda
 * 
 * @param value valor que se busca.
 * @return Si consigue el objeto que conincida con el parametro enviado se
 * retorna el objeto con los valores, en caso contrario se retorna
 * un objeto vacio
 */
    public NombreValor findValue(String value) {
        NombreValor result = new NombreValor();
        String parametro = ResourceBundle.getBundle("/MyBundle").getString("ArrayMesesVencer");
        String aux[] = parametro.split(";");
        for (int i = 0; i < aux.length; i++) {
            int temp = i;
            if (value == null ? aux[i] == null : value.equals(aux[i])) {
                NombreValor data = new NombreValor(aux[--temp], Integer.parseInt(aux[i]));
                return data;
            }
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null
                || (value instanceof String && ((String) value).length() == 0)) {
            return null;
        }
        if (value instanceof NombreValor) {
            NombreValor o = (NombreValor) value;
            return String.valueOf(o.getValor());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{value, value.getClass().getName(), NombreValor.class.getName()});
            return null;
        }
    }

}
