/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author User
 */
public class CuposToIFRS9Jar implements Job{
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        String fullPath = (String) jec.getJobDetail().getJobDataMap().get("fullPath");
        ExecuteJar executeJar = new ExecuteJar();
        executeJar.executeJar(fullPath);
    }
    
}
