package quartzutil;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob
  implements Job
{
  public void execute(JobExecutionContext jec)
    throws JobExecutionException
  {
    SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
    System.out.println("Tarea invocada a la hora: " + formato.format(new Date()));
  }
}
