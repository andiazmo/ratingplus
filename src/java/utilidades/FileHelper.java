/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 03-08-2018
 --------------------------------------------------------------------------------
*/
package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class FileHelper {
    
    public static List<String> leerArchivo(String fileName) {
        File f = new File(fileName);
        
        try(FileReader fr = new FileReader(f); BufferedReader bufferedReader = new BufferedReader(fr);){
            
            List<String> lineasfile = new ArrayList<String>();
            String str = "";
            while( (str=bufferedReader.readLine()) != null){
                lineasfile.add(str);
            }
            bufferedReader.close();
            fr.close();
            
            return lineasfile;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<String>();
        } catch (IOException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<String>();
        }
    }
    
     
    // FIXPACK2 - inicio
    public static boolean verificarRuta(String ruta) {
        boolean result = false;
        if (ruta != null) {
            if (new File(ruta).exists()) {
                result = true;
            }
        }
        return result;
    }
    // FIXPACK2 - fin

    
}
