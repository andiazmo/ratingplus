/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Anyelo
 */
public class LazyRespuestaDataModel extends LazyDataModel<VariablesRating>{
    
    public VariablesRating getRowData(String rowKey) {
       // return loadUserFromDBByUserId(rowKey);
       return null;
    }
    
    public Object getRowKey(VariablesRating variable) {
       // return user.getId();
       return null;
    }
    
   
    public List<VariablesRating> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters){
        return null;
        
    }
    
}
