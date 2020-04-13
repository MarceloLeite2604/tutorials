package io.github.marceloleite2604.configuration;

import io.github.marceloleite2604.controller.SitePaths;
import io.github.marceloleite2604.service.ThymeleafModelAttributeNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassConfiguration {

    @Bean(BeanNames.SITE_PATHS_CLASS)
    public Class<SitePaths> createSitePathsClassBean() {
        return SitePaths.class;
    }

    @Bean(BeanNames.BEAN_NAMES_CLASS)
    public Class<BeanNames> createBeanNamesClassBean() {
        return BeanNames.class;
    }

    @Bean(BeanNames.THYMELEAF_MODEL_ATTRIBUTE_NAMES_CLASS)
    public Class<ThymeleafModelAttributeNames> createThymeleafModelAttributeNamesClass() {
        return ThymeleafModelAttributeNames.class;
    }
}
