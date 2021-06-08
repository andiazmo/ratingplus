/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Anyelo
 */
public class ResultadoLazyDataModel extends LazyDataModel<RatingInfo> {
   
    private List<RatingInfo> dataSource;
    
    public ResultadoLazyDataModel(List<RatingInfo> dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public RatingInfo getRowData(String rowKey) {
        for(RatingInfo result : dataSource) {
            if(result.getNit().equals(rowKey))
                return result;
        }

        return null;
    }
    
    @Override
    public Object getRowKey(RatingInfo result) {
        return result.getNit();
    }
    
    @Override
    public List<RatingInfo> load(int first, int pageSize, final String sortField, final SortOrder sortOrder, Map<String, Object> filters) {
        
        List<RatingInfo> data = new ArrayList<>();
  
        for(RatingInfo result : dataSource) {
            boolean match = true;

            for(String filterProperty : filters.keySet()) {
                try {
                    
                    String filterValue = (String)filters.get(filterProperty);
                    String fieldValue = String.valueOf(result.getClass().getField(filterProperty).get(result));
                    
                    if(filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    }
                    else {
                        match = false;
                        break;
                    }
                } catch(Exception e) {
                    match = false;
                }
            }
            if(match) {
                data.add(result);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new Comparator<RatingInfo>() {
                @Override
                public int compare(RatingInfo result1, RatingInfo result2) {
                    Object value1 = null;
                    try {
                        value1 = RatingInfo.class.getField(sortField).get(result1);
                        Object value2 = RatingInfo.class.getField(sortField).get(result2);
                        int value = ((Comparable)value1).compareTo(value2);
                        return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            );
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
