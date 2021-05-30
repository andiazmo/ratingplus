/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Date;

/**
 *
 * @author User
 */
public class RunTimeMemoryUtil {
    
    public static void main(String args[]){
        printMemoryAnalize();
    }
    
    public static void printMemoryAnalize(){
        System.out.println(new Date());
        System.out.println(getMemoryAnalize());
    }
    
    public static String getMemoryAnalize(){
        
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        
        int mb = 1024*1024;
        
        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();
        
        line= "##### Estadisticas Memoria Java Heap [MB] #####";
        stringBuilder.append(line).append("\n");
        
         line="Memoria Usada:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb;
        stringBuilder.append(line).append("\n");
        
        //Print free memory
        line= "Memoria Libre:"
                + runtime.freeMemory() / mb;
        stringBuilder.append(line).append("\n");
        
        //Print total available memory
         line = "Memoria Total:" + runtime.totalMemory() / mb ;
         stringBuilder.append(line).append("\n");
        
        //Print Maximum available memory
         line = "Memoria Maxima:" + runtime.maxMemory() / mb;
         stringBuilder.append(line).append("\n");
        
         line= "##### Fin #####";
        stringBuilder.append(line).append("\n");
         
        return stringBuilder.toString();
    }
    
}
