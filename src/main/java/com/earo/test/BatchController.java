package com.earo.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lauearo on 16/05/2017.
 */
@RestController
public class BatchController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job importJob;

    @RequestMapping("/import")
    public String imp(String fileName) throws Exception{
        String path = fileName + ".csv";
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", path)
                .toJobParameters();
        jobLauncher.run(importJob, jobParameters);

        return "ok";
    }

}
