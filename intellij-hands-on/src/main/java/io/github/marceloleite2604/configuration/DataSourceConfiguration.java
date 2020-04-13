package io.github.marceloleite2604.configuration;

import com.figtreelake.sled.Sled;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.github.marceloleite2604.properties.DatabaseProperties;
import io.github.marceloleite2604.properties.EncryptionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DataSourceConfiguration {

    @Bean(BeanNames.DATA_SOURCE)
    public DataSource createDataSource(EncryptionProperties encryptionProperties, DatabaseProperties databaseProperties, Sled sled) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setDriverClass(databaseProperties.getDriverClassName());
        comboPooledDataSource.setUser(databaseProperties.getUsername());
        comboPooledDataSource.setJdbcUrl(databaseProperties.getUrl());
        comboPooledDataSource.setMinPoolSize(databaseProperties.getMinPoolSize());
        comboPooledDataSource.setInitialPoolSize(databaseProperties.getMinPoolSize());
        comboPooledDataSource.setMaxPoolSize(databaseProperties.getMaxPoolSize());
        comboPooledDataSource.setAcquireIncrement(databaseProperties.getAcquirementIncrement());

        String password = sled.decrypt(databaseProperties.getPassword(), encryptionProperties.getKey());
        comboPooledDataSource.setPassword(password);

        return comboPooledDataSource;
    }
}
