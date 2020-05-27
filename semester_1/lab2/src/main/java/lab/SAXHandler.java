package lab;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SAXHandler extends DefaultHandler {

    private String thisElement;
    private String interestNode;
    private Map<String, String> resultEntry = new HashMap<>();
    private List<Map<String, String>> result = new ArrayList<>();

    SAXHandler(String interestNode) {
        this.interestNode = interestNode;
    }

    List<Map<String, String>> getResult() {
        return result;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) {
        thisElement = qName;

        if (interestNode.equals(qName)) {
            resultEntry = new HashMap<>();
            thisElement = "";
        }

        int attributeLength = attrs.getLength();

        for (int i = 0; i < attributeLength; i++) {
            // Get attribute names and values
            String attrName = attrs.getQName(i);
            String attrVal = attrs.getValue(i);

            resultEntry.put(attrName, attrVal);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String val = new String(ch, start, length);
        if (!thisElement.equals("")) {
            resultEntry.put(thisElement, val);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        thisElement = "";

        if (interestNode.equals(qName)) {
            result.add(resultEntry);

            resultEntry = new HashMap<>();
        }
    }
}
