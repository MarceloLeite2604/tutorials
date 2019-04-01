package com.github.marceloleite2604.spring.batch.remote.chunking;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class Main {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException,
			InterruptedException {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

		JobExecution jobExecution = executarJob(applicationContext);
		aguardarConclusaoJob(jobExecution);

		applicationContext.stop();
	}

	private static JobExecution executarJob(ConfigurableApplicationContext context)
			throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job job = context.getBean(Job.class);
		JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		return jobExecution;
	}

	private static void aguardarConclusaoJob(JobExecution jobExecution)
			throws InterruptedException {
		while (jobExecution.isRunning()) {
			Thread.sleep(100);
		}
	}
}