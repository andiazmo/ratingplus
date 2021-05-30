/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package jobs;

import controladores.util.JsfUtil;
import dao.ArchivosDAO;
import dao.AuditoriaDAO;
import dao.ParametrosDAO;
import entidades.Archivos;
import fachadas.ArchivosFacade;
import fachadas.ParametrosFacade;
import java.util.Calendar;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author GCOCOL0281
 */
public class CreadorArchivoDia implements Job {
    
//    private ArchivosFacade ejbArchivo;
    private ArchivosDAO archivosDAO;
//    private ParametrosFacade ejbParametro;
    private ParametrosDAO parametrosDAO;
    private int numeroCreacionArchivoDias;
    
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        archivosDAO = new ArchivosDAO();
        parametrosDAO = new ParametrosDAO();
        
        numeroCreacionArchivoDias = cargarnumeroCreacionArchivoDias();
        crearArchivos();
    }
    
    private void crearArchivos(){
        Calendar calendar = Calendar.getInstance();
        for(int i=0; i<numeroCreacionArchivoDias; i++){
            String fecha
                    = JsfUtil.fechaFormateada(calendar.getTime(), "ddMMYYYY");
            String nombre = "ADCD" + fecha + ".txt";
            if(existeArchivoRegistrado(nombre)){
                continue;
            }
            crearArchivo(nombre);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        
    }
    
    private void crearArchivo(String nombre){
        Archivos archivo = new Archivos();
        archivo.setNombre(nombre);
        archivo.setProcesado(false);
        archivo.setRprocesados(0);
        archivo.setRnprocesados(0);
        archivosDAO.createEntity(archivo);
    }
    
    private boolean existeArchivoRegistrado(String nombre){
        return archivosDAO.existeResgistro(nombre);
    }
    
    private int cargarnumeroCreacionArchivoDias(){
        String tempNumeroCreacionArchivoDias =
                parametrosDAO.buscarXClave("numeroCreacionArchivoDias");
        
        if(tempNumeroCreacionArchivoDias.isEmpty()){
            return 5;
        }

        return Integer.valueOf(tempNumeroCreacionArchivoDias);
    }
    
}
