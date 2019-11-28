package lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchemaValidatorTest {
    @Test
    void test_valid_xml() {
        assertTrue(SchemaValidator.validateXml("publication.xsd", GeneratorForTests.generateXml(true)));
    }

    @Test
    void test_invalid_xml() {
        assertFalse(SchemaValidator.validateXml("publication.xsd", GeneratorForTests.generateXml(false)));
    }
}