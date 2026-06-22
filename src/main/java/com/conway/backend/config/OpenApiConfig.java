package com.conway.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI conwayOpenAPI() {

        final String securitySchemeName =
                "bearerAuth";

        return new OpenAPI()

                .info(
                        new Info()
                                .title(
                                        "Conway Backend API"
                                )
                                .version(
                                        "1.0"
                                )
                                .description(
                                        "Logistics enquiry and admin management backend built using Spring Boot."
                                )
                                .contact(
                                        new Contact()
                                                .name(
                                                        "Sushaan Mehta"
                                                )
                                )
                )

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(
                                        securitySchemeName
                                )
                )

                .schemaRequirement(
                        securitySchemeName,
                        new SecurityScheme()
                                .name(
                                        securitySchemeName
                                )
                                .type(
                                        SecurityScheme.Type.HTTP
                                )
                                .scheme(
                                        "bearer"
                                )
                                .bearerFormat(
                                        "JWT"
                                )
                );
    }
}