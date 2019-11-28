package lab;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class GeneralSAXParser implements GeneralParserInterface {

    private List<Map<String, String>> result;
    private int elementNum = 0;

    GeneralSAXParser(InputStream file, String interestNode) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        SAXParser parser = factory.newSAXParser();
        GeneralSAXHandler handler = new GeneralSAXHandler(interestNode);

        parser.parse(file, handler);

        result = handler.getResult();
    }

    @Override
    public Map<String, String> getNextMap() {
        if (!isNextMap())
            return null;

        Map<String, String> element = result.get(elementNum);

        elementNum++;

        return element;
    }

    @Override
    public boolean isNextMap() {
        return result.size() > elementNum;
    }
}
