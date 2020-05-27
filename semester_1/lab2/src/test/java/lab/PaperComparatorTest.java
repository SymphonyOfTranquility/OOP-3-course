package lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaperComparatorTest {
    @Test
    void testPaperComparator() {
        Paper a = GeneratorForTests.generatePaper();

        Paper b = GeneratorForTests.generatePaper();

        PaperComparator paperComp = new PaperComparator();

        b.title = "F";
        assertTrue(paperComp.compare(a, b) > 0);

        b.title = "V";
        assertTrue(paperComp.compare(a, b) < 0);

        b.title = a.title;
        assertEquals(0, paperComp.compare(a, b));
    }
}