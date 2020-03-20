package com.sbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

 

@SpringBootApplication
@EnableScheduling
public class SBatchwithBootApplication {

	@Autowired
    JobLauncher jobLauncher;
    
    /*
     * @Autowired JobOperator jobOperator;
     */

	 @Autowired
	 Job job;

   
	
	public static void main(String[] args) {
		SpringApplication.run(SBatchwithBootApplication.class, args);
	}

	@Scheduled(fixedDelay = 50000000)
    public void doJob() {
        JobParameters parameters=new JobParametersBuilder()
                .addString("JobId", String.valueOf(System.currentTimeMillis()))
                .addString("JobName", job.getName())
                .toJobParameters();
        
        
            try {
                org.springframework.batch.core.JobExecution execution = jobLauncher.run(job,parameters);
            } catch (JobExecutionAlreadyRunningException | org.springframework.batch.core.repository.JobRestartException
                    | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
                
    }
	
	
	
}
