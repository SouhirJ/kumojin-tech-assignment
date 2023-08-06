package com.kumojin.eventsmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Souhir Jedidi",
                        email = "souhirdjedidi@gmail.com",
                        url = ""
                ),
                description = "REST service for managing events",
                title = "Events manager",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://kumojineventsmanager.com"
                )

        }
)
public class OpenApiConfig {
}

