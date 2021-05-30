/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package jobs;

import controladores.util.JsfUtil;
import dao.ArchivosDAO;
import dao.AuditoriaDAO;
import dao.ClientesDAO;
import dao.CuposDAO;
import dao.DesembolsosDAO;
import dao.LimitesautorizadosDAO;
import dao.LogarchivosDAO;
import dao.ParametrosDAO;
import entidades.Archivos;
import entidades.Auditoria;
import entidades.Clientes;
import entidades.Desembolsos;
import entidades.Limitesautorizados;
import entidades.Logarchivos;
import fileutil.FileUtil;
import java.io.File;
import java.util.Date;
import java.util.List;import org.quartz.JobExecutionContext;
import org.quartz.Job;
import org.quartz.JobExecutionException;
import sftputil.Propiedades;
import sftputil.SftpUtil;
import utilidades.FileHelper;
import utilidades.RunTimeMemoryUtil;
import utilidades.sftp.SFTPHelper;



/**
 *
 * @author GCOCOL0281
 */
public class LecturaDerivados implements Job {
    private SFTPHelper sFTPHelper;
    private ArchivosDAO archivosDAO;
    private AuditoriaDAO auditoriaDAO;
    private ClientesDAO clientesDAO;
    private CuposDAO cuposDAO;
    private DesembolsosDAO desembolsosDAO;
    private LimitesautorizadosDAO limitesautorizadosDAO;
    private LogarchivosDAO logarchivosDAO;
    private ParametrosDAO parametrosDAO;
    
    
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        RunTimeMemoryUtil.printMemoryAnalize();
        
        archivosDAO = new ArchivosDAO();
        auditoriaDAO = new AuditoriaDAO();
        clientesDAO = new ClientesDAO();
        cuposDAO = new CuposDAO();
        desembolsosDAO = new DesembolsosDAO();
        limitesautorizadosDAO = new LimitesautorizadosDAO();
        logarchivosDAO = new LogarchivosDAO();
        parametrosDAO = new ParametrosDAO();
        
        SftpUtil ssh = new SftpUtil();
        FileUtil fUtil = new FileUtil();
        
        String host = parametrosDAO.buscarXClave("ip_ftp");
        int port = Integer.valueOf(parametrosDAO.buscarXClave("port_ftp"));
        String user = parametrosDAO.buscarXClave("user_ftp");
        String passw = parametrosDAO.buscarXClave("passw_ftp");
        String rutaSFTP = parametrosDAO.buscarXClave("path_ftp");
        String rutaLocal = parametrosDAO.buscarXClave("path_local");
        
        sFTPHelper = new SFTPHelper(user, host , port, passw, rutaLocal, rutaSFTP);
        
        for(Archivos archivo : archivosDAO.noProcesados()){
            ssh.propiedades = new Propiedades(host,user, passw, port, rutaLocal, rutaSFTP,archivo.getNombre(),archivo.getNombre());
            proceso( ssh, fUtil, archivo, rutaLocal);
        }
    }
    
    private boolean procesarLinea(String nit, Date fecha, Double valor, 
            int registro, String folderLocal, Archivos archivo){
        
        //valido cliente
        Integer t = Integer.parseInt(nit);
        List<Clientes> clientes = clientesDAO.clientesNit(""+t);
        String linea = "";
        File file =  new File (folderLocal + "LogADCD" + 
                archivo.getNombre().substring(4, 12) + ".txt");
        
        if(clientes.size() == 0){
            // guardar log de no existencia del
            linea = String.format("%05d",registro) + "3" + String.format(
                    "%1$200s","El cliente " + nit + " no esta registrado");
            
            FileUtil futil = new FileUtil();
            if(futil.Crear(file)){
                futil.escribir(file, linea);
            }
            Logarchivos log1 = new Logarchivos();
            log1.setArchivo(archivo);
            log1.setLinea(registro);
            log1.setLog(linea);
            logarchivosDAO.createEntity(log1);
            
            return false;
        }
        
        if(JsfUtil.compararFechasSinTiempo(fecha, new Date()) == 2){
            // guardar log de no existencia del
            linea = String.format("%05d",registro) + "3" + String.format("%1$200s","La fecha Es mayor a la de hoy");
            FileUtil futil = new FileUtil();
            if(futil.Crear(file)){
                futil.escribir(file, linea);
            }
            Logarchivos log1 = new Logarchivos();
            log1.setArchivo(archivo);
            log1.setLinea(registro);
            log1.setLog(linea);
            logarchivosDAO.createEntity(log1);
            return false;
        }
        
        if(valor <= 0 ){
            // guardar log de no existencia del
            linea = String.format("%05d",registro) + "3" + String.format("%1$200s","El valor debe ser mayor a cero");
            FileUtil futil = new FileUtil();
            if(futil.Crear(file)){
                futil.escribir(file, linea);
            }
            Logarchivos log1 = new Logarchivos();
            log1.setArchivo(archivo);
            log1.setLinea(registro);
            log1.setLog(linea);
            logarchivosDAO.createEntity(log1);
            return false;
        }
        if(clientes.get(0).getCuposList().size() == 0){
            linea = String.format("%05d",registro) + "3" + String.format("%1$200s","Cliente: " + nit + " no tiene Cupo asignado");
            FileUtil futil = new FileUtil();
            if(futil.Crear(file)){
                futil.escribir(file, linea);
            }
            Logarchivos log1 = new Logarchivos();
            log1.setArchivo(archivo);
            log1.setLinea(registro);
            log1.setLog(linea);
            logarchivosDAO.createEntity(log1);
            return false;
        }
        
        boolean noLimite = true;
        for(Limitesautorizados limite : clientes.get(0).getCuposList().get(0).getLimitesautorizadosList()){
            if(limite.getModalidad().getId().equalsIgnoreCase(clientesDAO.Derivado().getValor()) && limite.getSublimiteautorizado() > 0){
                noLimite = false;
//                if(valor > limite.getConsumido()){ //eliminar.
                    Double desembolso =  valor -  limite.getConsumido();
                    limite.setConsumido(valor);
                    limite.setDisponible(limite.getDisponible() - desembolso);
                    Desembolsos desembolsoe = new Desembolsos();
                    desembolsoe.setFecha(fecha);
                    desembolsoe.setComentarios("Desembolso automatico proceso bat dialogo");
                    desembolsoe.setLimite(limite);
                    desembolsoe.setValor(desembolso);
                    desembolsosDAO.createEntity(desembolsoe);
                    clientes.get(0).getCuposList().get(0).setLimiteconsumido( clientes.get(0).getCuposList().get(0).getLimiteconsumido() + desembolso);
                    cuposDAO.createEntity(clientes.get(0).getCuposList().get(0));
                    limitesautorizadosDAO.editEntity(limite);
                    Auditoria aud = new Auditoria();
                    aud.setAccion("Actualizacion Cupo Derivado");
                    aud.setFecha(fecha);
                    aud.setUsuario("Batch");
                    auditoriaDAO.createEntity(aud);
//                }
                break;
            }
        }
        
        if(noLimite){
            linea = String.format("%05d",registro) + "3" + String.format("%1$200s","No tiene asignado limite para esta modalidad");
            FileUtil futil = new FileUtil();
            if(futil.Crear(file)){
                futil.escribir(file, linea);
            }
            Logarchivos log1 = new Logarchivos();
            log1.setArchivo(archivo);
            log1.setLinea(registro);
            log1.setLog(linea);
            logarchivosDAO.createEntity(log1);
            return false;
        }
        
        return true;
    }
    
    private boolean enviarArchivo(SftpUtil ssh,Archivos archivos){
        String archivo = ssh.propiedades.getFolderLocal() + "ANCW" +  archivos.getNombre().substring(4, 12) + ".txt";
        FileUtil fUtil = new FileUtil();
        File file = new File(archivo);
        if(fUtil.Crear(file)){
            for(Clientes cliente : clientesDAO.clientesTotal()){
                int tipo = 2;
                if(JsfUtil.compararFechasSinTiempo(cliente.getFechaestado(), new Date())==0){
                    if(cliente.getEstadocliente().getId().equalsIgnoreCase(clientesDAO.estadoInactivo())){
                        tipo = 3;
                    }else{
                        tipo = 1;
                    }
                }
                Double valor = 0.0;
                if(cliente.getCuposList()!= null  && cliente.getCuposList().size()>0){
                    for(Limitesautorizados limite : cliente.getCuposList().get(0).getLimitesautorizadosList()){
                        if(limite.getModalidad().getId().equalsIgnoreCase(clientesDAO.Derivado().getValor()) && limite.getSublimiteautorizado() > 0){
                            valor = limite.getSublimiteautorizado();
                        }
                    }
                    String linea = String.format("%1$15s",cliente.getNit()).replace(' ', '0') + JsfUtil.fechaFormateada(new Date(), "dd/MM/YYYY") + String.format("%1$20s",valor.longValue()).replace(' ', '0') +  JsfUtil.fechaFormateada(cliente.getCuposList().get(0).getFechavencimiento(),"dd/MM/YYYY") + "0000" + tipo;
                    fUtil.escribir(file, linea);
                }
                
                
                
            }
            ssh.propiedades.setFileLocal(file.getName());
            ssh.propiedades.setFileRemoto(file.getName());
            
//            if(ssh.conectar()){
//                if(ssh.subir()){
//                    return true;
//                }
//            }
            sFTPHelper.sendLite(file.getName());
            
        }
        return false;
    }
    
    private void proceso(SftpUtil ssh, FileUtil fUtil, Archivos archivo, String rutaLocal) {
        if(sFTPHelper.receive(archivo.getNombre())){
            List<String> lineas = FileHelper.leerArchivo(rutaLocal + archivo.getNombre());
            procesarArchivoLocal(ssh, archivo, lineas);
        }
    }

    private void procesarArchivoLocal(SftpUtil ssh, Archivos archivo, List<String> lineas) {
        int i=1;
        boolean log = false;
        for(String linea :lineas){
            if(linea.length() == 45){
                // proceso linea
                if(!procesarLinea(linea.substring(0, 15),
                        JsfUtil.fechaApartirDeTexto(linea.substring(16, 25)), 
                        JsfUtil.doubleApartirDeTexto(linea.substring(25, 45)),i, 
                        ssh.propiedades.getFolderLocal(),archivo)){
                    
                    log=true;
                    archivo.setRnprocesados( archivo.getRnprocesados() + 1);
                }else{
                    archivo.setRprocesados(archivo.getRprocesados()+ 1);
                }
            }else{
                archivo.setRnprocesados( archivo.getRnprocesados() + 1);
                Logarchivos log1 = new Logarchivos();
                log1.setArchivo(archivo);
                log1.setLinea(i);
                log1.setLog("La linea no cumple con el estandar" + linea);
                logarchivosDAO.createEntity(log1);
            }
            i++;
            System.out.println(i);
        }
        if(archivo.getRnprocesados()>0){
            String archivo1 = "LogADCD" + archivo.getNombre().substring(4, 12) + ".txt";
            ssh.propiedades.setFileLocal(archivo1);
            ssh.propiedades.setFileRemoto(archivo1);
//            if(ssh.conectar()){
//                ssh.subir();
//            }
//            if(sFTPHelper.connect()){
//                sFTPHelper.send(archivo1);
//            }
            sFTPHelper.sendLite(archivo1);
        }
        archivo.setProcesado(true);
        archivosDAO.editar(archivo);
        
        //archivo de salida la alta baja y modificacion ex apartir del historico de estado con fecha coincidente
        if(enviarArchivo(ssh,archivo)){
        }
    }
    
}

