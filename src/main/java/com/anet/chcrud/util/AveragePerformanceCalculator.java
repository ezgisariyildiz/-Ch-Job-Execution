package com.anet.chcrud.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AveragePerformanceCalculator {

    private static int workerCount;
    private static long averageCpuTime;
    private static long averageMemoryUsage;
    private static long averageExecutionTime;

    private static int workerCountMV;
    private static long averageCpuTimeMV;
    private static long averageMemoryUsageMV;
    private static long averageExecutionTimeMV;

    public static void addSingleExecutionMetrics(long cpuTime, long memoryUsage, long executionTime) {

        averageCpuTime += cpuTime;
        averageMemoryUsage += memoryUsage;
        averageExecutionTime += executionTime;
        workerCount++;


        if (workerCount >= 100) {
            averageCpuTime = averageCpuTime / 100L;
            averageMemoryUsage = averageMemoryUsage / 100L;
            averageExecutionTime = averageExecutionTime / 100L;

            log.info("Average task CPU time : " + averageCpuTime + " nanoseconds");
            log.info("Average memory usage :  " + averageMemoryUsage + " bytes");
            log.info("Average execution time :  " + averageExecutionTime + " milliseconds");

            showMetricsCollected();
        }
    }

    public static void addSingleExecutionMetricsMV(long cpuTime, long memoryUsage, long executionTime) {

        averageCpuTimeMV += cpuTime;
        averageMemoryUsageMV += memoryUsage;
        averageExecutionTimeMV += executionTime;
        workerCountMV++;

        if (workerCountMV >= 100) {
            averageCpuTimeMV = averageCpuTimeMV / 100L;
            averageMemoryUsageMV = averageMemoryUsageMV / 100L;
            averageExecutionTimeMV = averageExecutionTimeMV / 100L;

            log.info("....................................................................................................");
            log.info("Average task CPU time FROM mv_data:  Task CPU time: " + averageCpuTimeMV + " nanoseconds");
            log.info("Average memory usage FROM mv_data:  " + averageMemoryUsageMV + " bytes");
            log.info("Average execution time FROM mv_data:  " + averageExecutionTimeMV + " milliseconds");
            log.info("....................................................................................................");
            showMetricsCollected();
        }


    }
    private static void showMetricsCollected() {
        if (workerCount >= 100 && workerCountMV >= 100) {
            log.info("############################################################################################################");
            log.info("Average cpu time:  From data_table:   " + averageCpuTime + " FROM mv_data:  " + averageCpuTimeMV + " nanoseconds");
            log.info("Average memory usage: FROM data_table:   " + averageMemoryUsage + "FROM mv_data:  " + averageMemoryUsageMV + " bytes");
            log.info("Average execution time: FROM data_table:   " + averageExecutionTime + "FROM mv_data:  " + averageExecutionTimeMV + " milliseconds");
            log.info("############################################################################################################");
        }
    }


}
