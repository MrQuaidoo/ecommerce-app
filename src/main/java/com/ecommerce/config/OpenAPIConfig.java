package com.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for setting up OpenAPI (Swagger) documentation.
 * This class defines the API documentation details, including server URLs, contact information and API metadata.
 */
@Configuration
public class OpenAPIConfig {

  // Development environment server URL, injected from application properties
  @Value("${test-openapi.dev-url}")
  private String devUrl;

  // Production environment server URL, injected from application properties
  @Value("${test-openapi.prod-url}")
  private String prodUrl;

  /**
   * Configures the OpenAPI documentation with server details, contact information and API metadata.
   *
   * @return an OpenAPI object representing the API documentation configuration
   */
  @Bean
  public OpenAPI myOpenAPI() {
    // Define the development server
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    // Define the production server
    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    // Define contact information
    Contact contact = new Contact();
    contact.setEmail("johndoe@gmail.com");
    contact.setName("John Doe");
    contact.setUrl("https://www.example.com");

    // Define API metadata
    Info info = new Info()
            .title("E-Commerce API")
            .version("2.0")
            .contact(contact)
            .description("API Swagger endpoints")
            .termsOfService("https://www.example.com/terms");

    // Return the OpenAPI configuration with metadata and server details
    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}
