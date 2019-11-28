package lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandyTest {
    @Test
    void test_class_constructor() {
        Candy candy = GeneratorForTests.generateCandy();

        assertEquals("ID-1", candy.id);
        assertEquals("Slivki linivki vaflya", candy.name);
        assertEquals("420", candy.energy);
        assertEquals("Chocolate with filling", candy.type);
        assertEquals("milk cream", candy.filling);
        assertEquals("15", candy.ingredients.get("Water"));
        assertEquals("30", candy.ingredients.get("Sugar"));
        assertEquals("Black chocolate", candy.ingredients.get("ChocolateType"));
        assertEquals("5", candy.ingredients.get("Vanilla"));
        assertEquals(15, (int) candy.value.get("Proteins"));
        assertEquals(5, (int) candy.value.get("Fat"));
        assertEquals(80, (int) candy.value.get("Carbon"));
        assertEquals("Roshen", candy.production);
    }

    @Test
    void test_equals() {
        Candy a = GeneratorForTests.generateCandy();

        Candy b = GeneratorForTests.generateCandy();

        assertTrue(a.equalsTo(b));

        a.name = "F";

        assertFalse(a.equalsTo(b));
    }
}