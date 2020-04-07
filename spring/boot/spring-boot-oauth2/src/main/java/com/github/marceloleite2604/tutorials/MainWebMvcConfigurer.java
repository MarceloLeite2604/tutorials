package com.github.marceloleite2604.tutorials;

import org.springframework.http.CacheControl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Component
public class MainWebMvcConfigurer implements WebMvcConfigurer {

    private static final CacheControl DEFAULT_CACHE_CONTROL = CacheControl.maxAge(7, TimeUnit.DAYS);

    public static final String[] JS_PATH_PATTERNS = {"/js/**"};

    private static final String[] JS_RESOURCE_LOCATIONS = {"file:src/main/resources/static/js/"};

    public static final String[] IMAGE_PATH_PATTERNS = {"/image/**"};

    private static final String[] IMAGE_RESOURCE_LOCATIONS = {"file:src/main/resources/static/image/"};

    public static final String[] CSS_PATH_PATTERNS = {"/css/**"};

    private static final String[] CSS_RESOURCE_LOCATIONS = {"file:src/main/resources/static/css/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler(JS_PATH_PATTERNS)
                .addResourceLocations(JS_RESOURCE_LOCATIONS)
                .setCacheControl(DEFAULT_CACHE_CONTROL);

        resourceHandlerRegistry.addResourceHandler(IMAGE_PATH_PATTERNS)
                .addResourceLocations(IMAGE_RESOURCE_LOCATIONS)
                .setCacheControl(DEFAULT_CACHE_CONTROL);

        resourceHandlerRegistry.addResourceHandler(CSS_PATH_PATTERNS)
                .addResourceLocations(CSS_RESOURCE_LOCATIONS)
                .setCacheControl(DEFAULT_CACHE_CONTROL);
    }
}
