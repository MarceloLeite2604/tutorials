package io.github.marceloleite2604.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(PropertiesPath.APPLICATION)
@Validated
public class ApplicationProperties {

    private String locale = "en";

    @NotBlank
    private String webMvcResourceLocationPrefix;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getWebMvcResourceLocationPrefix() {
        return webMvcResourceLocationPrefix;
    }

    public void setWebMvcResourceLocationPrefix(String webMvcResourceLocationPrefix) {
        this.webMvcResourceLocationPrefix = webMvcResourceLocationPrefix;
    }
}
