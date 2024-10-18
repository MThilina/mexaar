package com.mexeer.todo.configure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ToDo List Management API")
                        .version("1.0")
                        .description("This API provides operations to manage users, ToDo lists, and tasks. It allows for creating, updating, deleting, and viewing user-specific ToDo lists and their tasks, ensuring seamless task management.")
                        .termsOfService("https://www.mexeer.com/terms")
                        .contact(new Contact()
                                .name("Mexeer Team")
                                .url("https://www.mexeer.com")
                                .email("contact@mexeer.com"))
                        .license(new License()
                                .name("API License")
                                .url("https://www.mexeer.com/license")));
    }
}
