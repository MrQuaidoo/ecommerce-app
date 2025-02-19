import com.ecommerce.EcommerceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Integration test for the EcommerceApplication.
 * Ensures that the Spring Boot application context loads without errors.
 */
@SpringBootTest(classes = EcommerceApplication.class)
class EcommerceApplicationTest {

    /**
     * Tests if the application context loads successfully.
     * The test will pass if no exceptions are thrown during the application startup.
     */
    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> EcommerceApplication.main(new String[]{}),
                "Application context should load without exceptions");
    }
}
