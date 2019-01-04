package org.marceloleite.tutorials.spring.batch.configuration;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.marceloleite.tutorials.spring.batch.job.step.one.PersonItemProcessor;
import org.marceloleite.tutorials.spring.batch.mapper.PersonBeanWrapperFieldSetMapper;
import org.marceloleite.tutorials.spring.batch.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class StepConfiguration {

	@Inject
	private StepBuilderFactory stepBuilderFactory;
	
	@Inject
	private PersonItemProcessor personItemProcessor;

	@Bean("stepOne")
	public Step createStepOne(TaskExecutor taskExecutor, FlatFileItemReader<Person> reader,
			JdbcBatchItemWriter<Person> writer) {
		return stepBuilderFactory.get("stepOne")
				.<Person, Person>chunk(10)
				.reader(reader)
				.processor(personItemProcessor)
				.writer(writer)
				.taskExecutor(taskExecutor)
				.build();
	}

	@Bean
	public FlatFileItemReader<Person> createReader() {
		return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names(new String[] { "firstName", "lastName" })
				.fieldSetMapper(new PersonBeanWrapperFieldSetMapper())
				.build();
	}

	@Bean
	public JdbcBatchItemWriter<Person> createWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
				.dataSource(dataSource)
				.build();
	}
}
