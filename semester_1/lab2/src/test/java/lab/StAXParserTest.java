package lab;

import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StAXParserTest {
    @Test
    void testXmlParsing() throws XMLStreamException {
        InputStream xmlFile = GeneratorForTests.generateXml(true);
        String interestNode = "Paper";

        ParserInterface parser = new StAXParser(xmlFile, interestNode);

        Map<String, String> expected = new HashMap<>();
        expected.put("id", "ID-1");
        expected.put("Title", "Mriya");
        expected.put("Type", "newspaper");
        expected.put("Monthly", "true");
        expected.put("Colored", "true");
        expected.put("Size", "30");
        expected.put("Glossy", "false");
        expected.put("SubscriptionIndex", "true");

        Map<String, String> actual = parser.getNextMap();

        for(Map.Entry<String, String> entry : expected.entrySet()) {
            assertEquals(actual.get(entry.getKey()), entry.getValue());
        }
    }

    @Test
    void testIsNextMapFunc() throws XMLStreamException {
        InputStream xmlFile = GeneratorForTests.generateXml(true);
        String interestNode = "Paper";

        ParserInterface parser = new StAXParser(xmlFile, interestNode);

        assertTrue(parser.isNextMap());

        parser.getNextMap();
        parser.getNextMap(); //parser has to reach end of stream to set flag to false

        assertFalse(parser.isNextMap());
    }
}