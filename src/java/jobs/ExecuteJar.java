/*
--------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Julio Beltran
 *Tag de creación: FIXPACK2
 *Fecha de creación : 10-10-2018
 --------------------------------------------------------------------------------
 */
package jobs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author User
 */
public class ExecuteJar {

    public static void main(String args[]) {
        ExecuteJar executeJar = new ExecuteJar();
        executeJar.executeJar("C:\\BOT\\Transmisiones_FS_AXON\\TX_SFTP_GET_SRV2SRV.jar");
    }

    public void executeJar(String path, String fileName) {
        executeJar(path + fileName);
    }

    public void executeJar(String fullPath) {
        // FIXPACK2 - inicio
        String error = "";
        // FIXPACK2 - FIN 
        try {
            // String cmdStr = "\"C:\\Program Files\\Java\\jre1.8.0_211\\bin\\java.exe\"  -jar "+fullPath;
            String cmdStr = "java  -jar " + fullPath;
            //String cmdStr = System.getenv("JAVA_CUPOS") + "\\java  -jar " + fullPath;
            Process proc = Runtime.getRuntime().exec(cmdStr);
            // Then retreive the process output
            InputStream in = proc.getInputStream();
            InputStream err = proc.getErrorStream();

            String myString = IOUtils.toString(in, StandardCharsets.UTF_8);
            System.out.println(myString);

            myString = IOUtils.toString(err, StandardCharsets.UTF_8);
            System.out.println(myString);

            // FIXPACK2 - inicio
            error = IOUtils.toString(err, StandardCharsets.UTF_8);
            // FIXPACK2 - fin 

        } catch (IOException ex) {

            Logger.getLogger(ExecuteJar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!"".equals(error)) {
                System.out.println("ERROR en proceso: " + fullPath + ": " + error);
                Logger.getLogger(ExecuteJar.class.getName()).log(Level.SEVERE, null, "ERROR en proceso: " + fullPath + ": " + error);
            } else {
                System.out.println("Proceso Iniciado con exito: " + fullPath);
                Logger.getLogger(ExecuteJar.class.getName()).log(Level.INFO, null, "Proceso Iniciado con exito: " + fullPath);
            }
        }
        // FIXPACK2 - fin
    }

    public void executeJarWithDate(String fullPath, String fecha) {
        
        String error = "";
        
        try {
            String cmdStr = "java  -jar " + fullPath + " " + fecha;
            
            Process proc = Runtime.getRuntime().exec(cmdStr);
            InputStream in = proc.getInputStream();
            InputStream err = proc.getErrorStream();

            String myString = IOUtils.toString(in, StandardCharsets.UTF_8);
            System.out.println(myString);

            myString = IOUtils.toString(err, StandardCharsets.UTF_8);
            System.out.println(myString);

            error = IOUtils.toString(err, StandardCharsets.UTF_8);

        } catch (IOException ex) {

            Logger.getLogger(ExecuteJar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!"".equals(error)) {
                System.out.println("ERROR en proceso: " + fullPath + ": " + error);
                Logger.getLogger(ExecuteJar.class.getName()).log(Level.SEVERE, null, "ERROR en proceso: " + fullPath + ": " + error);
            } else {
                System.out.println("Proceso Iniciado con exito: " + fullPath);
                Logger.getLogger(ExecuteJar.class.getName()).log(Level.INFO, null, "Proceso Iniciado con exito: " + fullPath);
            }
        }
    
    }

}
