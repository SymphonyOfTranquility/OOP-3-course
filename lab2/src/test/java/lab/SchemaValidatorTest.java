package lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchemaValidatorTest {
    @Test
    void testValidXml() {
        assertTrue(SchemaValidator.validateXml("publication.xsd", GeneratorForTests.generateXml(true)));
    }

    @Test
    void testInvalidXml() {
        assertFalse(SchemaValidator.validateXml("publication.xsd", GeneratorForTests.generateXml(false)));
    }
}