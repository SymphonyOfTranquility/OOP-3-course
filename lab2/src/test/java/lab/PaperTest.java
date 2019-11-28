package lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaperTest {
    @Test
    void test_class_constructor() {
        Paper paper = GeneratorForTests.generatePaper();

        assertEquals("ID-1", paper.id);
        assertEquals("Mriya", paper.title);
        assertEquals("newspaper", paper.type);
        assertEquals(true, paper.monthly);
        assertEquals(1, (int) paper.chars.get("Colored"));
        assertEquals(30, (int) paper.chars.get("Size"));
        assertEquals(0, (int) paper.chars.get("Glossy"));
        assertEquals(1, (int) paper.chars.get("SubscriptionIndex"));
    }

    @Test
    void test_equals() {
        Paper a = GeneratorForTests.generatePaper();

        Paper b = GeneratorForTests.generatePaper();

        assertTrue(a.equalsTo(b));

        a.title = "G";

        assertFalse(a.equalsTo(b));
    }
}