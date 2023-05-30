package com.AluraChallenge.Foro.Infra;

import com.AluraChallenge.Foro.DTO.Usuarios.DatosUsuarioActualizar;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Vladimir Joya",
                        email = "vladimirjoya148@gmail.com",
                        url = "https://github.com/vladimirjoya147"),
                description = "Api documentation for Spring Security",
                title = "Alura Foro - API", version = "1.0"),
        servers = {
                @Server(description = "Local", url = "http://localhost:8080"),},
        security = {
                @SecurityRequirement(name = "bearerAuth")})
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)

public class openApiConfig {
}
