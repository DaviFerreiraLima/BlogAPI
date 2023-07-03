package com.compassuol.springbootblog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Blog App Rest APIs",
                description = "Spring Boot Blog App Rest APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Davi Ferreira",
                        email = "daviferreilima@gmail.com"
                )
        )
)
public class SpringbootBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogApplication.class, args);
    }

}
