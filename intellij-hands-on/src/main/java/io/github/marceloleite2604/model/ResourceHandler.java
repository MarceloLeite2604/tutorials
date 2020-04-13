package io.github.marceloleite2604.model;

import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public enum ResourceHandler {
    IMAGE(new String[]{"/img/**"}, new String[]{"/img/"}),
    CSS(new String[]{"/css/**"}, new String[]{"/css/"}),
    JAVASCRIPT(new String[]{"/js/**"}, new String[]{"/js/"});

    private static final CacheControl DEFAULT_CACHE_CONTROL = CacheControl.maxAge(7, TimeUnit.DAYS);

    private String[] resourceHandlersPathPatterns;

    private String[] resourceLocations;

    private Optional<CacheControl> optionalCacheControl;

    private ResourceHandler(String[] resourceHandlersPathPatterns, String[] resourceLocations) {
        this(resourceHandlersPathPatterns, resourceLocations, null);
    }

    ResourceHandler(String[] resourceHandlersPathPatterns, String[] resourceLocations, CacheControl cacheControl) {
        this.resourceHandlersPathPatterns = resourceHandlersPathPatterns;
        this.resourceLocations = resourceLocations;
        this.optionalCacheControl = Optional.ofNullable(cacheControl);
    }

    public void addResourceHandler(ResourceHandlerRegistry resourceHandlerRegistry, String resourceLocationPrefix) {
        String[] completeResourceLocations = completeResourceLocations(resourceLocationPrefix);
        CacheControl cacheControl = optionalCacheControl.orElse(DEFAULT_CACHE_CONTROL);
        resourceHandlerRegistry.addResourceHandler(resourceHandlersPathPatterns).addResourceLocations(completeResourceLocations).setCacheControl(cacheControl);
    }

    private String[] completeResourceLocations(String resourceLocationPrefix) {
        return Arrays
                .stream(resourceLocations)
                .map(resourceLocation -> resourceLocationPrefix + resourceLocation)
                .toArray(String[]::new);
    }
}
