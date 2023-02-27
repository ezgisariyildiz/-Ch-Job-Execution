package com.anet.chcrud.schedular;

import com.anet.chcrud.model.DataTable;
import com.anet.chcrud.service.DataTableService;
import com.anet.chcrud.util.AveragePerformanceCalculator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
@Slf4j
public class MyJob implements Job {

    // Task'ın yapması gereken işlemler burada yer alır.
    @SneakyThrows
    public void execute(JobExecutionContext context) throws JobExecutionException {

        // task'ın çalışma zamanını ölçmek için system time bean'i al
        long taskStartTime = System.currentTimeMillis();
        long mvStartTime = System.nanoTime();
        long startInsertTime= System.currentTimeMillis();

        // JVM'deki thread mx bean'i al
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        // task'ın CPU kullanımını ölçmek için task'ı çalıştır
        long taskCpuTime = bean.getCurrentThreadCpuTime();

        log.info("Job Started!");
        log.info("Insert query started!");
        log.info("Materialized view table started!");

        DataTableService dataTableService = DataTableService.instance;
        dataTableService.findAll();
        dataTableService.materializedView();
        dataTableService.insertQuery();
        /*Random random = new Random();
        int tmp = random.nextInt();
        System.out.println(tmp);
        log.info(""+Math.pow(random.nextDouble(), 2));*/

        // taskCpuTime = bean.getCurrentThreadCpuTime() - taskCpuTime;
        taskCpuTime = bean.getThreadCpuTime(Thread.currentThread().getId());

        // task'ın RAM kullanımını ölçmek için memory usage bean'i al
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        long taskMemoryUsage = memoryUsage.getUsed();
        long taskEndTime = System.currentTimeMillis();
        long endInsertTime = System.currentTimeMillis();
        long taskExecutionTime = taskEndTime - taskStartTime;
        long mvEndTime = System.nanoTime();
        long duration  = mvEndTime - mvStartTime;

        // task'ın kullandığı CPU, RAM ve çalışma zamanını yazdır
        log.info(context.getJobDetail().getKey().getName() + " - Task memory usage: " + taskMemoryUsage + " bytes");
        log.info(context.getJobDetail().getKey().getName() + " - Task execution time: " + taskExecutionTime + " milliseconds");
        log.info(context.getJobDetail().getKey().getName() + " - Task CPU time: " + taskCpuTime + " nanoseconds");
        log.info(context.getJobDetail().getKey().getName() + " - INSERT query: " + (endInsertTime - startInsertTime) + " milliseconds");
        log.info(context.getJobDetail().getKey().getName() + " - Materialized view SELECT query: " + duration + " nanoseconds");

        AveragePerformanceCalculator.addSingleExecutionMetrics(taskCpuTime,taskMemoryUsage,taskExecutionTime);
        AveragePerformanceCalculator.addSingleExecutionMetricsMV(taskCpuTime,taskMemoryUsage,taskExecutionTime);


    }

}
