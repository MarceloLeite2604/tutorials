package com.github.marceloleite2604.spring.batch.remote.chunking.manager;

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

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

		JobExecution jobExecution = executarJob(applicationContext);

		aguardarConclusaoJob(jobExecution);

		applicationContext.stop();
	}

	private static JobExecution executarJob(ConfigurableApplicationContext context) {
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job job = context.getBean(Job.class);
		JobParameters jobParameters = new JobParametersBuilder().toJobParameters();

		try {
			return jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException | JobParametersInvalidException excecao) {
			throw new RuntimeException("Erro ao solicitar a execução do job.", excecao);
		}
	}

	private static void aguardarConclusaoJob(JobExecution jobExecution) {
		while (jobExecution.isRunning()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException excecao) {
				throw new RuntimeException("Erro ao aguardar a execução do job.", excecao);
			}
		}
	}
}