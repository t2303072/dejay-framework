package com.dejay.framework.common.config;

import com.dejay.framework.common.interceptor.AuthorityInterceptor;
import com.dejay.framework.common.interceptor.LoggerInterceptor;
import com.dejay.framework.common.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SEC = 3600L;

    private final LoggerInterceptor loggerInterceptor;
    private final LoginInterceptor loginInterceptor;
    private final AuthorityInterceptor authorityInterceptor;

    // Logging
    private final List<String> loggerIncludePattern = Arrays.asList("/**");

    // Login
    private final List<String> loginIncludePattern = Arrays.asList("/login/**", "/member/**", "/token/authentication-info");
    private final List<String> loginExcludePattern = Arrays.asList("/login", "/member/sign-up");

    // Authority
    private final List<String> authorityIncludePattern = Arrays.asList("/auth/**", "/test/authorized-only");
    private final List<String> authorityExcludePattern = Arrays.asList("/login/**", "/member/**", "/test/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor)
                .addPathPatterns(loggerIncludePattern)
                .excludePathPatterns("/error")
                .order(1);

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(loginIncludePattern)
                .excludePathPatterns(loginExcludePattern)
                .order(2);

        registry.addInterceptor(authorityInterceptor)
                .excludePathPatterns(authorityExcludePattern)
                .order(3);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SEC);
    }
}
