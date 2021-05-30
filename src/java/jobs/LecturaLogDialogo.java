/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import controladores.util.JsfUtil;
import entidades.Auditoria;
import entidades.Clientes;
import entidades.Desembolsos;
import entidades.Limitesautorizados;
import entidades.Procesos;
import fachadas.AuditoriaFacade;
import fachadas.ClientesFacade;
import fachadas.CuposFacade;
import fachadas.DesembolsosFacade;
import fachadas.LimitesautorizadosFacade;
import fachadas.ProcesosFacade;
import fileutil.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import quartzutil.QuartzUtil;
import sftputil.Propiedades;
import sftputil.SftpUtil;

/**
 *
 * @author GCOCOL0281
 */
public class LecturaLogDialogo implements Job {

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        String fechaActual = JsfUtil.fechaFormateada(new Date(), "ddMMYYYY");
        String archivo = "LOGANCW" + fechaActual + ".txt";
        SftpUtil ssh = new SftpUtil();
        FileUtil fUtil = new FileUtil();
        ssh.propiedades = new Propiedades("180.26.136.36", "root", "temporoot", 22, "C:\\cupos\\", "/home/cupos/", archivo, archivo);
        List<String> lineas = new ArrayList<String>();
        if (ssh.conectar()) {
            if (ssh.bajar()) {
                try {
                    lineas = fUtil.LeerFichero(new File(ssh.propiedades.getFolderLocal() + archivo));
                    //empieza proceso por cada linea
                    int i = 1;
                    boolean log = false;
                    for (String linea : lineas) {
                        // proceso linea
                        if (!procesarLinea((ClientesFacade) jec.getJobDetail().getJobDataMap().get("ejbClientes"), linea.substring(0, 15), JsfUtil.fechaApartirDeTexto(linea.substring(16, 25)), JsfUtil.doubleApartirDeTexto(linea.substring(25, 45)), i, (DesembolsosFacade) jec.getJobDetail().getJobDataMap().get("ejbDesembolsos"), (LimitesautorizadosFacade) jec.getJobDetail().getJobDataMap().get("ejbLimites"), (CuposFacade) jec.getJobDetail().getJobDataMap().get("ejbCupos"), (AuditoriaFacade) jec.getJobDetail().getJobDataMap().get("ejbAuditoria"), ssh.propiedades.getFolderLocal())) {
                            log = true;
                        }
                        i++;
                    }
                    if (log) {
                        archivo = "LogADCD" + fechaActual + ".txt";
                        ssh.propiedades.setFileLocal(archivo);
                        ssh.propiedades.setFileRemoto(archivo);
                        ssh.subir();
                    }
                    //reprogramar el log
                    ProcesosFacade ejbProceso = (ProcesosFacade) jec.getJobDetail().getJobDataMap().get("ejbProcesos");
                    String idJob = (String) jec.getJobDetail().getJobDataMap().get("identificador");
                    String cron = "0 0/10 22-23 " + (Integer.parseInt(fechaActual.substring(0, 1)) + 1) + " * ?";
                    JobDataMap map = new JobDataMap();
                    map.put("ejbAuditoria", jec.getJobDetail().getJobDataMap().get("ejbAuditoria"));
                    map.put("ejbCupos", jec.getJobDetail().getJobDataMap().get("ejbCupos"));
                    map.put("ejbLimites", jec.getJobDetail().getJobDataMap().get("ejbLimites"));
                    map.put("ejbDesembolsos", jec.getJobDetail().getJobDataMap().get("ejbDesembolsos"));
                    map.put("ejbClientes", jec.getJobDetail().getJobDataMap().get("ejbClientes"));
                    map.put("ejbProcesos", jec.getJobDetail().getJobDataMap().get("ejbProcesos"));
                    map.put("identificador", idJob);
                    Procesos proceso = ejbProceso.find(idJob);
                    proceso.setCron(cron);
                    Class clase = Class.forName(proceso.getClase());
                    QuartzUtil q = new QuartzUtil();
                    q.lanzarTarea(q.creaJob(clase, idJob, map), cron);
                    ejbProceso.modificar(proceso);
                    //archivo de salida la alta baja y modificacion ex apartir del historico de estado con fecha coincidente
                    if (enviarArchivo((ClientesFacade) jec.getJobDetail().getJobDataMap().get("ejbClientes"), (LimitesautorizadosFacade) jec.getJobDetail().getJobDataMap().get("ejbLimites"), (CuposFacade) jec.getJobDetail().getJobDataMap().get("ejbCupos"), (AuditoriaFacade) jec.getJobDetail().getJobDataMap().get("ejbAuditoria"), ssh)) {

                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LecturaLogDialogo.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                //se guarda auditoria de intento fallido
            }
        }
        ssh.desconectar();
    }

    private boolean procesarLinea(ClientesFacade ejbCliente, String nit, Date fecha, Double valor, int registro, DesembolsosFacade ejbDesembolso, LimitesautorizadosFacade ejbLimite, CuposFacade ejbCupo, AuditoriaFacade ejbAuditoria, String folderLocal) {
        //valido cliente
        List<Clientes> clientes = ejbCliente.clientesNit(nit);
        String linea = "";
        File file = new File(folderLocal + "LogADCD" + JsfUtil.fechaFormateada(new Date(), "ddMMYYYY") + ".txt");
        if (clientes.size() == 0) {
            // guardar log de no existencia del
            linea = String.format("%05d", registro) + "3" + String.format("%1$200s", "El cliente " + nit + " no esta registrado");
            FileUtil futil = new FileUtil();
            if (futil.Crear(file)) {
                futil.escribir(file, linea);
            }
            return false;
        }

        if (JsfUtil.compararFechasSinTiempo(fecha, new Date()) == 2) {
            // guardar log de no existencia del
            linea = String.format("%05d", registro) + "3" + String.format("%1$200s", "La fecha Es mayor a la de hoy");
            FileUtil futil = new FileUtil();
            if (futil.Crear(file)) {
                futil.escribir(file, linea);
            }
            return false;
        }

        if (valor <= 0) {
            // guardar log de no existencia del
            linea = String.format("%05d", registro) + "3" + String.format("%1$200s", "El valor debe ser mayor a cero");
            FileUtil futil = new FileUtil();
            if (futil.Crear(file)) {
                futil.escribir(file, linea);
            }
            return false;
        }

        if (clientes.get(0).getCuposList().size() == 0) {
            linea = String.format("%05d", registro) + "3" + String.format("%1$200s", "Cliente: " + nit + " no tiene Cupo asignado");
            FileUtil futil = new FileUtil();
            if (futil.Crear(file)) {
                futil.escribir(file, linea);
            }
            return false;
        }

        boolean noLimite = true;
        for (Limitesautorizados limite : clientes.get(0).getCuposList().get(0).getLimitesautorizadosList()) {
            if (limite.getModalidad().getId().equalsIgnoreCase(ejbCliente.Derivado().getValor()) && limite.getSublimiteautorizado() > 0) {
                noLimite = false;
                if (valor > limite.getConsumido()) {
                    Double desembolso = valor - limite.getConsumido();
                    limite.setConsumido(valor);
                    limite.setDisponible(limite.getDisponible() + desembolso);
                    Desembolsos desembolsoe = new Desembolsos();
                    desembolsoe.setFecha(fecha);
                    desembolsoe.setComentarios("Desembolso automatico proceso bat dialogo");
                    desembolsoe.setLimite(limite);
                    desembolsoe.setValor(desembolso);
                    ejbDesembolso.guardar(desembolsoe);
                    clientes.get(0).getCuposList().get(0).setLimiteconsumido(clientes.get(0).getCuposList().get(0).getLimiteconsumido() + desembolso);
                    ejbCupo.guardar(clientes.get(0).getCuposList().get(0));
                    ejbLimite.guardar(limite);
                    Auditoria aud = new Auditoria();
                    aud.setAccion("Actualizacion Cupo Derivado");
                    aud.setFecha(fecha);
                    aud.setUsuario("Batch");
                    ejbAuditoria.guardar(aud);
                }
                break;
            }
        }

        if (noLimite) {
            linea = String.format("%05d", registro) + "3" + String.format("%1$200s", "No tiene asignado limite para esta modalidad");
            FileUtil futil = new FileUtil();
            if (futil.Crear(file)) {
                futil.escribir(file, linea);
            }
            return false;
        }

        return true;
    }

    private boolean enviarArchivo(ClientesFacade ejbCliente, LimitesautorizadosFacade ejbLimite, CuposFacade ejbCupo, AuditoriaFacade ejbAuditoria, SftpUtil ssh) {
        String archivo = ssh.propiedades.getFolderLocal() + "ANCW" + JsfUtil.fechaFormateada(new Date(), "ddMMYYYY") + ".txt";
        FileUtil fUtil = new FileUtil();
        File file = new File(archivo);
        if (fUtil.Crear(file)) {
            for (Clientes cliente : ejbCliente.clientesTotal()) {
                int tipo = 2;
                if (JsfUtil.compararFechasSinTiempo(cliente.getFechaestado(), new Date()) == 0) {
                    if (cliente.getEstadocliente().getId().equalsIgnoreCase(ejbCliente.estadoInactivo())) {
                        tipo = 3;
                    } else {
                        tipo = 1;
                    }
                }
                Double valor = 0.0;
                if (cliente.getCuposList() != null && cliente.getCuposList().size() > 0) {
                    for (Limitesautorizados limite : cliente.getCuposList().get(0).getLimitesautorizadosList()) {
                        if (limite.getModalidad().getId().equalsIgnoreCase(ejbCliente.Derivado().getValor()) && limite.getSublimiteautorizado() > 0) {
                            valor = limite.getSublimiteautorizado();
                        }
                    }
                    String linea = String.format("%1$15s", cliente.getNit()).replace(' ', '0') + JsfUtil.fechaFormateada(new Date(), "dd/MM/YYYY") + String.format("%1$20s", valor.longValue()).replace(' ', '0') + JsfUtil.fechaFormateada(cliente.getCuposList().get(0).getFechavencimiento(), "dd/MM/YYYY") + "0000" + tipo;
                    fUtil.escribir(file, linea);
                }

            }
            ssh.propiedades.setFileLocal(file.getName());
            ssh.propiedades.setFileRemoto(file.getName());
            if (ssh.subir()) {
                return true;
            }
        }
        return false;
    }

}
