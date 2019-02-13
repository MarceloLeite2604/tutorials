package org.marceloleite.tutorials.spring.job.configuration.job;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.marceloleite.tutorials.spring.job.configuration.configuration.JobConfiguration;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.stereotype.Component;

@Component
public class MeuJobExecutionListener implements JobExecutionListener {

	@Inject
	JobExplorer jobExplorer;

	@Override
	public void beforeJob(JobExecution jobExecution) {
//		List<JobInstance> jobInstances = jobExplorer.getJobInstances(JobConfiguration.NOME_DO_JOB, 0, 1);
//		
//		if (!CollectionUtils.isEmpty(jobInstances)) {
//			
//			JobInstance jobInstance = jobInstances.get(0);
//			List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(jobInstance);
//			
//			if ( jobExecutions.size() > 1) {
//				System.out.println("Retomando execução.");
//			}
//		}
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("AfterJob");
	}

}
