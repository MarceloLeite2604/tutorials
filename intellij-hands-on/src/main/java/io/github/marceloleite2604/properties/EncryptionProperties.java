package io.github.marceloleite2604.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(PropertiesPath.ENCRYPTION)
@Validated
public class EncryptionProperties {

    /**
     * Cryptographic algorithm to use for encryption.
     */
    @NotBlank
    private String cryptographicAlgorithm;

    /**
     * Feedback mode to user for encryption.
     */
    @NotBlank
    private String feedbackMode;

    /**
     * Padding scheme to user for encryption.
     */
    @NotBlank
    private String paddingScheme;

    /**
     * Key used for encryption.
     */
    private String key;

    /**
     * Environment variable name which contains the encryption key.
     */
    private String keyEnvironmentVariableName;

    public String getCryptographicAlgorithm() {
        return cryptographicAlgorithm;
    }

    public void setCryptographicAlgorithm(String cryptographicAlgorithm) {
        this.cryptographicAlgorithm = cryptographicAlgorithm;
    }

    public String getFeedbackMode() {
        return feedbackMode;
    }

    public void setFeedbackMode(String feedbackMode) {
        this.feedbackMode = feedbackMode;
    }

    public String getPaddingScheme() {
        return paddingScheme;
    }

    public void setPaddingScheme(String paddingScheme) {
        this.paddingScheme = paddingScheme;
    }

    public String getKeyEnvironmentVariableName() {
        return keyEnvironmentVariableName;
    }

    public void setKeyEnvironmentVariableName(String keyEnvironmentVariableName) {
        this.keyEnvironmentVariableName = keyEnvironmentVariableName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

