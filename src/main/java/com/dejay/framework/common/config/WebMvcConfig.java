package com.dejay.framework.common.config;

import com.dejay.framework.common.interceptor.LoggerInterceptor;
import com.dejay.framework.common.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoggerInterceptor loggerInterceptor;
    private final LoginInterceptor loginInterceptor;

    private final List<String> loggerPattern = Arrays.asList("/**");
    private final List<String> loginPattern = Arrays.asList("/member/**", "/test/authentication-info");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor)
                .addPathPatterns(loggerPattern)
                .excludePathPatterns("/error")
                .order(1);

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(loginPattern)
                .order(2);
    }

}
