/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import entidades.HistoricoClave;
import entidades.RpUsuarios;
import fachadas.HistoricoAccesoUsuarioFacade;
import fachadas.HistoricoClaveFacade;
import fachadas.RpUsuariosFacade;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author User
 */
public class CambiarClavesMes implements Job{
    private HistoricoAccesoUsuarioFacade ejbHistoricoAcesso;
    private HistoricoClaveFacade ejbHistoricoClaveFacade;
    private RpUsuariosFacade ejbRpUsuarios;
            
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        ejbHistoricoAcesso = (HistoricoAccesoUsuarioFacade) jec.getJobDetail().
                getJobDataMap().get("ejbHistoricoAcesso");
        ejbRpUsuarios = (RpUsuariosFacade) jec.getJobDetail().getJobDataMap().
                get("ejbRpUsuarios");
        ejbHistoricoClaveFacade = (HistoricoClaveFacade) jec.getJobDetail().
                getJobDataMap().get("ejbHistoricoClaveFacade");
        for(RpUsuarios rpUsuarios: ejbRpUsuarios.findAll()){
            List<HistoricoClave> historicoClaves = ejbHistoricoClaveFacade.
                    buscarActiva(rpUsuarios);
            if(historicoClaves.size()>0){
                HistoricoClave historicoClave = historicoClaves.get(0);
                if(compararFechas(historicoClave.getFechaCreacion(), new Date())){
                    desactivarClave(historicoClave);
                }
            }
        }
    }
    
    private void desactivarClave(HistoricoClave historicoClave){
        historicoClave.setActiva(false);
        ejbHistoricoClaveFacade.modificar(historicoClave);
    }

    private boolean compararFechas(Date fechaCreacion, Date actual) {
        return ( fechaCreacion.equals(actual) ) || 
                ( fechaCreacion.before(new Date()) );
    }
    
}
