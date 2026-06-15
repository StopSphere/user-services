package com.shopsphere.userservice.user_services.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import static java.awt.SystemColor.info;

@Configuration
@OpenAPIDefinition(
        info =@Info(
                title ="User Service Api",
                version = "1.0",
                description = "API documentation for User Service",
                contact= @Contact(
                        name="Monu Dhakad"
                )
        ),

security = {
@SecurityRequirement(name = "Bearer Authentication")
    }
            )


@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenAPIConfig {
}
