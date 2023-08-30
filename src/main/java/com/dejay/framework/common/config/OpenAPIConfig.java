package com.dejay.framework.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "Spring REST API Doc",
        version = "v.1.0.0",
        description = "REST API 문서"
        )
)
@Configuration
public class OpenAPIConfig {
}
