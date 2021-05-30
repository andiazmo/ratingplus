/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package utilidades;

/**
 *
 * @author User
 */
public class PropertyTuple {
    
    private String key;
    private String value;
    
    public PropertyTuple() {}
    
    public PropertyTuple(String secuence, String separator) {
        String strFragment[] = secuence.split(separator);
        key = strFragment[0].trim();
        value = strFragment[1].trim();
    }
    
    public String getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }
    
}
