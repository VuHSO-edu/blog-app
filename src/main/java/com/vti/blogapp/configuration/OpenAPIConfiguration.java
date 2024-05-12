package com.vti.blogapp.configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.annotations.OpenAPI30;

@OpenAPIDefinition(info = @Info(
        title = "Blog Application REST APIs",
        description = "Blog Application REST APIs Documentation",
        termsOfService = "https://github.com/khoa-omega/blog-app",
        contact = @Contact(
                name = "Nguyễn Duy Vux",
                url = "https://github.com/VuHSO-edu",
                email = "nguyenduyvu01051976@gmail.com"
        ),
        license = @License(
                name = "Apache 2.0",
                url = "https://www.apache.org/licenses/LICENSE-2.0"
        ),
        version = "v1.0.0"
),
        servers = @Server(
                description = "Local ENV",
                url = "http://localhost:8080"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Blog Application Github",
                url = "https://github.com/VuHSO-edu"
        )
)
public class OpenAPIConfiguration {

}
