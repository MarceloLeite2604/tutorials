package com.github.marceloleite2604.tutorials.spring.batch.job;

import javax.inject.Inject;

import com.github.marceloleite2604.tutorials.spring.batch.mapper.PersonRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CompletionNotificationJobListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory
			.getLogger(CompletionNotificationJobListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Inject
	private PersonRowMapper personRowMapper;

	@Inject
	public CompletionNotificationJobListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("Job finished.");
			checkResults();
		}
	}

	private void checkResults() {
		jdbcTemplate.query("SELECT first_name, last_name FROM people", personRowMapper)
				.forEach(person -> log.info("Found {} in the database.", person));
	}
}