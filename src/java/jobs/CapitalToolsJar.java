/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import java.time.LocalDate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import utilidades.DateMaxUtil;

/**
 *
 * @author x332015
 */
public class CapitalToolsJar implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        String fullPath = (String) jec.getJobDetail().getJobDataMap().get("fullPath");
        System.out.println("Path: " +" Fecha: "+fullPath + DateMaxUtil.calculateDayMaxMonth(LocalDate.now()));
        ExecuteJar executeJar = new ExecuteJar();
        executeJar.executeJarWithDate(fullPath, DateMaxUtil.calculateDayMaxMonth(LocalDate.now()));
    }

}
