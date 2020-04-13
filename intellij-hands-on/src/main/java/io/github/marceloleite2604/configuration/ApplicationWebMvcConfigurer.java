package io.github.marceloleite2604.configuration;

import io.github.marceloleite2604.controller.SitePaths;
import io.github.marceloleite2604.model.ResourceHandler;
import io.github.marceloleite2604.properties.ApplicationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Component
public class ApplicationWebMvcConfigurer implements WebMvcConfigurer {

    private final ApplicationProperties applicationProperties;

    public ApplicationWebMvcConfigurer(ApplicationProperties applicationProperties) {
        super();
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:" + SitePaths.INDEX);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        for (ResourceHandler resourceHandler : ResourceHandler.values()) {
            resourceHandler.addResourceHandler(registry, applicationProperties.getWebMvcResourceLocationPrefix());
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(createLocaleChangeInterceptor());
    }

    private LocaleChangeInterceptor createLocaleChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
}

