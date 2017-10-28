package com.telkom.recruitment.configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SwaggerDefinition(
        info = @Info(
                description = "API Documentation",
                title = "Platform Recruitmen Telkom",
                version = "1.0.0",
                termsOfService = "Terms of service",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                ),
                contact = @Contact(
                        name = "Irman Juliansyah",
                        email = "irmanjuliansyah@gmail.com",
                        url = "https://irmanjuliansyah.github.io/"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build();
    }

}


