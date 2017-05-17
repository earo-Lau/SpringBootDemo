package com.earo.test.service.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Created by lauearo on 16/05/2017.
 */
public class CSVJobListener implements JobExecutionListener {
    long startTime;
    long endTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        System.out.println("Job Start >>>>>> >>>>>> >>>>>> >>>>>> >>>>>>");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println("Job End <<<<<< <<<<<< <<<<<< <<<<<< <<<<<<");
        System.out.println("time span : " + (endTime - startTime) + "ms");
    }
}
