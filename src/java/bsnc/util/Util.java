/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsnc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class Util {
 
        public static Double round(double value, int places) {
//        places = 8;
        if (places < 0){
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
        
    
    public static String roundStr(Double resultNumber, int i) {
        resultNumber = Util.round(resultNumber,i);
        String result = String.format("%."+i+"f", resultNumber);
//        result =  result.replace(",", ParameterHelper.decimal);
//        result =  result.replace(".", ParameterHelper.decimal);
        return result;
    }
    
    public static String getFecha(Date date) {
        if(date==null){
            return "";
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.format(date);
    }
    
     public static String validateNull(Object input) {
        if(input==null){
            return "";
        }
        return String.valueOf(input);
    }
     
}
