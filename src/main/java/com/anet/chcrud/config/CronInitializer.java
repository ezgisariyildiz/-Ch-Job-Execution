package com.anet.chcrud.config;

import com.anet.chcrud.schedular.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
public class CronInitializer {

    @PostConstruct
    public void setup(){
        try {

            Properties quartzProperties = new Properties();
            quartzProperties.put("org.quartz.threadPool.threadCount", "50");

            // Scheduler oluşturma
            StdSchedulerFactory factory = new StdSchedulerFactory(quartzProperties);
            Scheduler scheduler = factory.getScheduler();

            // 100 task oluşturma
            for (int i = 0; i < 50; i++) {

                String jobName = "job"+ i;
                String groupName = "group"+ i;
                JobDetail job = JobBuilder.newJob(MyJob.class)
                        .withIdentity("jobName" +i, "groupName" +i)
                        .build();

                // task'ın zamanlaması
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("trigger"+ i, "group"+ i)
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                        .build();

                scheduler.scheduleJob(job, trigger);

            }// Scheduler'ı başlatma
            scheduler.start();

            //Saniyede 500 insert querysi atma :)
            for (int a = 0; a < 50; a++) {
                String insertJob = "insert job"+ a;
                String insertGroup = "insert group" +a;
                JobDetail job = JobBuilder.newJob(MyJob.class)
                        .withIdentity("insertJob" , "insertGroup")
                        .build();

                // task'ın zamanlaması
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("insertTrigger" +a, "insertGroup" +a)
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                        .build();

            }// Scheduler'ı başlatma
            scheduler.start();

             for (int b = 0; b < 50; b++) {
                 String mvData = "mv_data job"+ b;
                 String mvGroup = "mv_data group" +b;
                 JobDetail job = JobBuilder.newJob(MyJob.class)
                        .withIdentity("mv_data" , "mv_dataGroup")
                        .build();

            // task'ın zamanlaması
                 Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("mv_dataTrigger" +b, "mv_dataGroup" +b)
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                        .build();

             }// Scheduler'ı başlatma
            scheduler.start();
        }catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
