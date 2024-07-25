package com.thesis.sofen.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Sofen Swagger")
                        .description("Sofen Apis")
                        .version("v0.0.1"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
//                        .addParameters("Accept-Language", new Parameter().in("header").name("Accept-Language").description("Language header").required(true))
                        .addParameters("Accept-Language", new Parameter()
                                .in("header")
                                .name("Accept-Language")
                                .description("Language header")
                                .required(true)
                                .schema(new io.swagger.v3.oas.models.media.StringSchema()))
                );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
