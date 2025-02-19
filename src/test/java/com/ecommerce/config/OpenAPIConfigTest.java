package com.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for OpenAPIConfig.
 * Ensures that the OpenAPI configuration is correctly initialized.
 */
class OpenAPIConfigTest {

    /**
     * Tests the OpenAPI configuration to ensure the correct metadata and server details are set.
     */
    @Test
    void testMyOpenAPI() {
        OpenAPIConfig openAPIConfig = new OpenAPIConfig();
        String devUrl = "http://localhost:8080/dev";
        String prodUrl = "http://localhost:8080/prod";

        // Inject test values into the OpenAPIConfig fields
        ReflectionTestUtils.setField(openAPIConfig, "devUrl", devUrl);
        ReflectionTestUtils.setField(openAPIConfig, "prodUrl", prodUrl);

        // Generate the OpenAPI configuration
        OpenAPI openAPI = openAPIConfig.myOpenAPI();
        Info info = openAPI.getInfo();

        // Assertions for OpenAPI metadata
        assertNotNull(openAPI, "OpenAPI object should not be null");
        assertNotNull(info, "API info should not be null");
        assertEquals("E-Commerce API", info.getTitle(), "API title should be 'E-Commerce API'");
        assertEquals("2.0", info.getVersion(), "API version should be '2.0'");

        // Assertions for contact details
        assertNotNull(info.getContact(), "Contact information should not be null");
        assertEquals("John Doe", info.getContact().getName(), "Contact name should be 'John Doe'");
        assertEquals("johndoe@gmail.com", info.getContact().getEmail(), "Contact email should be 'johndoe@gmail.com'");
        assertEquals("https://www.example.com", info.getContact().getUrl(), "Contact URL should be 'https://www.example.com'");

        // Assertions for server configurations
        List<Server> servers = openAPI.getServers();
        assertNotNull(servers, "Server list should not be null");
        assertEquals(2, servers.size(), "There should be exactly 2 server configurations");

        // Verify development server details
        assertEquals(devUrl, servers.get(0).getUrl(), "Dev server URL should match");
        assertEquals("Server URL in Development environment", servers.get(0).getDescription(), "Dev server description should match");

        // Verify production server details
        assertEquals(prodUrl, servers.get(1).getUrl(), "Prod server URL should match");
        assertEquals("Server URL in Production environment", servers.get(1).getDescription(), "Prod server description should match");
    }
}
