package lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandyComparatorTest {
    @Test
    void test_candy_comparator() {
        Candy a = GeneratorForTests.generateCandy();

        Candy b = GeneratorForTests.generateCandy();

        CandyComparator candyc = new CandyComparator();

        b.name = "F";
        assertTrue(candyc.compare(a, b) > 0);

        b.name = "V";
        assertTrue(candyc.compare(a, b) < 0);

        b.name = a.name;
        assertEquals(0, candyc.compare(a, b));
    }
}