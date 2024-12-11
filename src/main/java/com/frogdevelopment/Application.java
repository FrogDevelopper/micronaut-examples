package com.frogdevelopment;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Hello World",
                version = "${demo.version}",
                description = "Application demo for bug report",
                license = @License(name = "Apache 2.0", url = "https://github.com/FrogDevelopment"),
                contact = @Contact(url = "https://github.com/FrogDevelopper", name = "FrogDevelopper", email = "frog.development.it@gmail.com")
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
