package com.mpena.jobtrackerv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                    .title("Job Tracker V2")
                    .description("REST API for keeping track of jobs I've applied to.")
                    .version("v1.0.0")
                    .contact(new Contact()                
                        .name("@michael-pena")
                        .url("https://github.com/michael-pena")
                        )
                    .license(new License()
                        .name("GPL 3.0")
                        .url("https://www.gnu.org/licenses/gpl-3.0.en.html")))
                .externalDocs(new ExternalDocumentation()
                    .url("https://github.com/michael-pena/job-tracker-v2")
                    .description("Job Tracker v2 REST API Documentation"))
                .components(new Components()
                .addSecuritySchemes(securitySchemeName, 
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
