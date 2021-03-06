package lab;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SAXParserTest {
    @Test
    void testXmlParsing() throws IOException, SAXException, ParserConfigurationException {
        InputStream xmlFile = GeneratorForTests.generateXml(true);
        String interestNode = "Paper";

        ParserInterface parser = new SAXParser(xmlFile, interestNode);

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
    void testIsNextMapFunc() throws IOException, SAXException, ParserConfigurationException {
        InputStream xmlFile = GeneratorForTests.generateXml(true);
        String interestNode = "Paper";

        ParserInterface parser = new SAXParser(xmlFile, interestNode);

        assertTrue(parser.isNextMap());

        parser.getNextMap();

        assertFalse(parser.isNextMap());
    }
}