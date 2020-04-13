package io.github.marceloleite2604.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@ConfigurationProperties(PropertiesPath.DATABASE)
@Validated
public class DatabaseProperties {

    /**
     * Driver class name for database connection.
     */
    @NotBlank
    private String driverClassName;

    /**
     * Database connection URL.
     */
    @NotBlank
    private String url;

    /**
     * Username for database connection.
     */
    @NotBlank
    private String username;

    /**
     * Encrypted password for database connection.
     */
    @NotBlank
    private String password;

    /**
     * Minimum connection pool size.
     */
    @Min(1L)
    private Integer minPoolSize = 4;

    /**
     * Maximum connection pool size.
     */
    @Min(1L)
    private Integer maxPoolSize = 20;

    /**
     * Connection pool increment size.
     */
    @Min(1L)
    private Integer acquirementIncrement = 4;

    /**
     * Other connection properties.
     */
    private Map<String, String> otherConnectionProperties;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getOtherConnectionProperties() {
        return otherConnectionProperties;
    }

    public void setOtherConnectionProperties(Map<String, String> otherConnectionProperties) {
        this.otherConnectionProperties = otherConnectionProperties;
    }

    public Integer getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(Integer minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getAcquirementIncrement() {
        return acquirementIncrement;
    }

    public void setAcquirementIncrement(Integer acquirementIncrement) {
        this.acquirementIncrement = acquirementIncrement;
    }
}

