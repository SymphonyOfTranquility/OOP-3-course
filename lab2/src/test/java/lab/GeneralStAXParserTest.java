package lab;

import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GeneralStAXParserTest {
    @Test
    void test_xml_parsing() throws XMLStreamException {
        InputStream xmlFile = GeneratorForTests.generateXml(true);
        String interestNode = "Candy";

        GeneralParserInterface parser = new GeneralStAXParser(xmlFile, interestNode);

        Map<String, String> expected = new HashMap<>();
        expected.put("id", "ID-1");
        expected.put("Name", "Slivki linivki vaflya");
        expected.put("Energy", "420");
        expected.put("filling", "milk cream");
        expected.put("Type", "Chocolate with filling");
        expected.put("Water", "15");
        expected.put("Sugar", "30");
        expected.put("Fructose", "15");
        expected.put("ChocolateType", "Black chocolate");
        expected.put("Vanilla", "5");
        expected.put("Proteins", "15");
        expected.put("Carbon", "80");
        expected.put("Production", "Roshen");

        Map<String, String> actual = parser.getNextMap();

        for(Map.Entry<String, String> entry : expected.entrySet()) {
            assertEquals(actual.get(entry.getKey()), entry.getValue());
        }
    }

    @Test
    void test_is_next_map_func() throws XMLStreamException {
        InputStream xmlFile = GeneratorForTests.generateXml(true);
        String interestNode = "Candy";

        GeneralParserInterface parser = new GeneralStAXParser(xmlFile, interestNode);

        assertTrue(parser.isNextMap());

        parser.getNextMap();
        parser.getNextMap(); //parser has to reach end of stream to set flag to false

        assertFalse(parser.isNextMap());
    }
}