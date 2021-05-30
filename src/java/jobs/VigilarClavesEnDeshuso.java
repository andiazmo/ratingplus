/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import entidades.HistoricoAccesoUsuario;
import entidades.RpUsuarios;
import fachadas.HistoricoAccesoUsuarioFacade;
import fachadas.RpUsuariosFacade;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author User
 */
public class VigilarClavesEnDeshuso implements Job{
    private HistoricoAccesoUsuarioFacade ejbHistoricoAcesso;
    private RpUsuariosFacade ejbRpUsuarios;
            
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        ejbHistoricoAcesso = (HistoricoAccesoUsuarioFacade) jec.getJobDetail().getJobDataMap().get("ejbHistoricoAcesso");
        ejbRpUsuarios = (RpUsuariosFacade) jec.getJobDetail().getJobDataMap().get("ejbRpUsuarios");
        
        for(RpUsuarios rpUsuarios: ejbRpUsuarios.findAll()){
            List<HistoricoAccesoUsuario> historicoAccesoUsuarios = ejbHistoricoAcesso.buscarUltimoAcceso(rpUsuarios);
            if(historicoAccesoUsuarios.size()>0){
                HistoricoAccesoUsuario historicoAccesoUsuario = historicoAccesoUsuarios.get(0);
                validar45dias(historicoAccesoUsuario, rpUsuarios);
                validar60dias(historicoAccesoUsuario, rpUsuarios);
            }
        }
    }

    private void validar45dias(HistoricoAccesoUsuario historicoAccesoUsuario, RpUsuarios rpUsuarios) {
        if(compararFechas(historicoAccesoUsuario.getFecha(), new Date(), 45)){
            bloquearUsuario(rpUsuarios);
        }
    }

    private void validar60dias(HistoricoAccesoUsuario historicoAccesoUsuario, 
            RpUsuarios rpUsuarios) {
        
        if(compararFechas(historicoAccesoUsuario.getFecha(), new Date(), 60)){
            deshabilitarUsuario(rpUsuarios);
        }
    }

    private boolean compararFechas(Date fechaUltimoAceso, Date fechaActual, 
            int incremento) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaUltimoAceso);
        calendar.add(Calendar.DAY_OF_MONTH, incremento);
        
        return ( calendar.getTime().equals(new Date()) ) || 
                ( calendar.getTime().before(new Date()) );
    }
    
    private void deshabilitarUsuario(RpUsuarios usuario) {
        if(!usuario.getActivado()){
                    usuario.setActivado(false);
                    ejbRpUsuarios.modificar(usuario);
        }
    }
    
    private void bloquearUsuario(RpUsuarios usuario) {
        if(!usuario.getBloqueado()){
            usuario.setBloqueado(true);
            ejbRpUsuarios.modificar(usuario);
        }
    }
    
}
