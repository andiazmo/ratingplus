/*
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web 2
 *Programador: Wittman Gutiérrez
 *Tag de creación: FIXPACK2
 *Fecha de creación : 06-08-2018
 --------------------------------------------------------------------------------
 */
package onstartup;

import controladores.ProcesosController;
import entidades.Procesos;
import fachadas.ProcesosFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import quartzutil.QuartzUtil;


/**
 * @author Wittman Gutierrez
 */
@SuppressWarnings("serial")
public class ProcesosAutomaticosServlet extends HttpServlet {

    @EJB
    private ProcesosFacade ejbFacade;

    @Override
    public void init() throws ServletException {
        boolean flag = false;
        String resultado;
        System.out.println("----------");
        System.out.println("---------- INICIANDO PROCESOS AUTOMÁTICOS ----------");
        System.out.println("----------");
        List<Procesos> procesosList = ejbFacade.buscarTodosOrdenados();
        validarEstadosJobs(procesosList);

        for (Procesos proceso : procesosList) {
            if (proceso.isAutomatico()) {
                flag = true;
                System.out.println("---- Proceso \"" + proceso.getNombre() + "\" programado para ejecución AUTOMÁTICA con Cupos Web");
                resultado = ProcesosController.lanzarJob(proceso);
                if ("".equals(resultado)) {
                    proceso.setEstado(true);
                    ejbFacade.modificar(proceso);
                    System.out.println("---- \"" + proceso.getNombre() + "\" en estado activo y en ejecución!");
                } else {
                    System.err.println("ATENCIÓN! - Error al iniciar el proceso " + proceso.getNombre());
                    System.err.println(resultado);
                }
            }
        }
        
        if(!flag)
            System.out.println("---- No hay procesos automàticos en BOT");

        System.out.println("---------- FIN PROCESOS AUTOMÁTICOS ----------");
    }

    private void validarEstadosJobs(List<Procesos> procesosList) {
        QuartzUtil.listarJobs();
        for (Procesos proceso : procesosList) {
            proceso.setEstado(QuartzUtil.getEstadoJob(proceso.getIdentificador().toString()));
            ejbFacade.modificar(proceso);
        }
    }

}
