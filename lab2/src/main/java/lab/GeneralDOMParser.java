package lab;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings(value="unchecked")

public class GeneralDOMParser implements GeneralParserInterface {

    private NodeList elementsList;
    private int elementNum = 0;

    GeneralDOMParser(InputStream file, String interestNode) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        elementsList = document.getDocumentElement().getElementsByTagName(interestNode);
    }

    @Override
    public Map<String, String> getNextMap() {
        if (!isNextMap())
            return null;

        Node element = elementsList.item(elementNum);
        NodeList items = element.getChildNodes();
        Map<String, String> result = getKeyValMap(items);
        if (element.hasAttributes()) {
            NamedNodeMap attrs = element.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                result.put(attrs.item(i).getNodeName(), attrs.item(i).getTextContent());
            }
        }

        elementNum++;
        return result;
    }

    @Override
    public boolean isNextMap() {
        return elementsList.getLength() > elementNum;
    }

    private Map<String, String> getKeyValMap(NodeList items) {

        Map result = new HashMap<String, String>(){};

        for(int i = 0; i < items.getLength(); i++) {
            Node child = items.item(i);

            if (child.getNodeType() != Node.ELEMENT_NODE)
                continue;

            if (child.hasAttributes()) {
                NamedNodeMap attrs = child.getAttributes();
                for (int j = 0; j < attrs.getLength(); j++) {
                    result.put(attrs.item(j).getNodeName(), attrs.item(j).getTextContent());
                }
            }

            NodeList childNodes = child.getChildNodes();
            result.putAll(getKeyValMap(childNodes));
            boolean allTextNodes = true;

            for(int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() != Node.TEXT_NODE) {
                    allTextNodes = false;
                    break;
                }
            }

            if (allTextNodes) {
                result.put(child.getNodeName(), child.getTextContent());
            }
        }

        return result;
    }
}
