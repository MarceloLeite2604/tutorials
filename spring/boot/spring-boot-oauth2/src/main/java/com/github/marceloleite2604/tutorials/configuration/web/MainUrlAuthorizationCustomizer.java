package com.github.marceloleite2604.tutorials.configuration.web;

import com.github.marceloleite2604.tutorials.MainWebMvcConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class MainUrlAuthorizationCustomizer implements Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> {

    public static final String[] DEFAULT_ANT_PATTERNS = {"/", "/error", "/webjars/**", "/js/**"};
    public static final String[] PERMIT_ALL_ANT_PATTERNS = Stream.of(DEFAULT_ANT_PATTERNS, MainWebMvcConfigurer.JS_PATH_PATTERNS, MainWebMvcConfigurer.IMAGE_PATH_PATTERNS, MainWebMvcConfigurer.CSS_PATH_PATTERNS)
            .flatMap(Stream::of)
            .toArray(String[]::new);

    @Override
    public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        expressionInterceptUrlRegistry.antMatchers(PERMIT_ALL_ANT_PATTERNS)
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
