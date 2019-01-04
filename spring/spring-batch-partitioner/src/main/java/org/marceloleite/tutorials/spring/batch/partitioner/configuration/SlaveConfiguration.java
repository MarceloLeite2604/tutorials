package org.marceloleite.tutorials.spring.batch.partitioner.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.batch.partitioner.RecordFieldSetMapper;
import org.marceloleite.tutorials.spring.batch.partitioner.model.Transaction;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SlaveConfiguration {

	@StepScope
	@Bean("slaveItemReader")
	public FlatFileItemReader<Transaction> itemReader(
			@Value("#{stepExecutionContext[fileName]}") String filename) {

		FlatFileItemReader<Transaction> flatFileItemReader = new FlatFileItemReader<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		String[] tokens = { "username", "userid", "transactiondate", "amount" };
		delimitedLineTokenizer.setNames(tokens);
		flatFileItemReader
				.setResource(new PathResource(Paths.get("src/main/resources/input/" + filename)));
		DefaultLineMapper<Transaction> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(new RecordFieldSetMapper());
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(defaultLineMapper);
		return flatFileItemReader;
	}

	@Bean
	public Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound((Class[]) new Class[] { Transaction.class });
		return marshaller;
	}

	@Bean("slaveItemWriter")
	@StepScope
	public StaxEventItemWriter<Transaction> itemWriter(Marshaller marshaller,
			@Value("#{stepExecutionContext[opFileName]}") String filename) throws IOException {
		StaxEventItemWriter<Transaction> itemWriter = new StaxEventItemWriter<>();
		itemWriter.setMarshaller(marshaller);
		itemWriter.setRootTagName("transactionRecord");
		Path outputFilePath = Paths.get("src/main/resources/output/" + filename);
		if (!outputFilePath.toFile()
				.exists()) {
			Files.createFile(outputFilePath);
		}
		itemWriter.setResource(new PathResource(outputFilePath));
		return itemWriter;
	}

	@Bean("slaveStep")
	public Step slaveStep(StepBuilderFactory stepBuilderFactory,
			@Named("slaveItemReader") ItemReader<Transaction> itemReader,
			@Named("slaveItemWriter") ItemWriter<Transaction> itemWriter, Marshaller marshaller) {
		return stepBuilderFactory.get("slaveStep")
				.<Transaction, Transaction>chunk(1000)
				.reader(itemReader)
				.writer(itemWriter)
				.build();
	}
}
