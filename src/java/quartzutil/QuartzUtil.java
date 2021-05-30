package quartzutil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

public class QuartzUtil {

    public static void main(String[] args) {
        QuartzUtil q = new QuartzUtil();
        JobDetail job = q.creaJob(TestJob.class, "prueba", new JobDataMap());
        q.lanzarTarea(job, "0/10 * * * * ?");
        System.out.println("Se relanza la tarea");
        q.reLanzarTarea(job, "0/15 * * * * ?", "prueba");
        q.borrar("prueba");
    }

    public JobDetail creaJob(Class clase, String identidad, JobDataMap parametros) {
        JobDetail job = JobBuilder.newJob(clase).withIdentity(identidad).build();
        job.getJobDataMap().putAll(parametros);
        return job;
    }

    public boolean lanzarTarea(JobDetail job, String cron) {
        try {
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();

            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            sch.start();
            sch.scheduleJob(job, trigger);
            return true;
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean reLanzarTarea(JobDetail job, String cron, String keyJob) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            borrar(keyJob);
            lanzarTarea(job, cron);
            return true;
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean borrar(String keyJob) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            JobKey key = new JobKey(keyJob);
            scheduler.deleteJob(key);
            return true;
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean getEstadoJob(String keyJob) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
//          scheduler.start();
            JobKey key = new JobKey(keyJob);
            return scheduler.checkExists(key);
        } catch (SchedulerException ex) {
            Logger.getLogger(QuartzUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<String> listarJobs() {
        List<String> jobsNames = new ArrayList<>();

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();

            for (String groupName : scheduler.getJobGroupNames()) {

                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();

                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    Date nextFireTime = triggers.get(0).getNextFireTime();

                    System.out.println("[jobName] : " + jobName + " [groupName] : "
                            + jobGroup + " - " + nextFireTime);

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
