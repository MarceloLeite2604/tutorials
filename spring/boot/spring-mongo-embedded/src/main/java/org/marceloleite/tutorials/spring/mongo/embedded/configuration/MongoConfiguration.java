package org.marceloleite.tutorials.spring.mongo.embedded.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

@Configuration
public class MongoConfiguration {

	private static final String ENDERECO = "localhost";

	private static final int PORTA = 27017;

	private static final String NOME_BD = "pccm";
	
	@Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean embeddedMongoFactoryBean = new EmbeddedMongoFactoryBean();
        embeddedMongoFactoryBean.setVersion("3.4.3");
        embeddedMongoFactoryBean.setBindIp(ENDERECO);
        MongoClient mongoClient = embeddedMongoFactoryBean.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, NOME_BD);
        return mongoTemplate;
    }

	/*@Bean(destroyMethod = "stop")
	public MongodExecutable criarMongodExecutable() throws IOException {
		System.out.println("Criando MongodExecutable.");
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.V3_4)
				.net(new Net(ENDERECO, PORTA, Network.localhostIsIPv6()))
				.build();

		MongodStarter starter = MongodStarter.getDefaultInstance();
		MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
		mongodExecutable.start();
		return mongodExecutable;
	}

	@Bean("MyMongoTemplate")
	public MongoTemplate criarMongoTemplate(MongodExecutable mongodExecutable) {
		System.out.println("Criando MongoTemplate.");
		return new MongoTemplate(new MongoClient(ENDERECO, PORTA), NOME_BD);
	}*/
}
