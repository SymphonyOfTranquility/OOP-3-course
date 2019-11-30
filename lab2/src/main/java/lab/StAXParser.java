package lab;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class StAXParser implements ParserInterface {

    private String thisElement;
    private XMLEventReader eventReader;
    private String interestNode;

    @Override
    public Map<String, String> getNextMap() {
        Map<String, String> result = new HashMap<>();

        while(eventReader.hasNext()) {

            XMLEvent event;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                Logger.log(e.toString());
                return null;
            }

            switch (event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    thisElement = startElement.getName().getLocalPart();

                    if (interestNode.equals(thisElement))
                        thisElement = "";

                    Iterator<Attribute> attributes = startElement.getAttributes();
                    while (attributes.hasNext()) {
                        Attribute attribute = attributes.next();
                        String key = attribute.getName().toString();
                        String val = attribute.getValue();

                        result.put(key, val);
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    String val = characters.getData();
                    result.put(thisElement, val);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    thisElement = "";

                    if (interestNode.equals(endElement.getName().toString())) {
                        return result;
                    }
                    break;
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public boolean isNextMap() {
        return eventReader.hasNext();
    }

    StAXParser(InputStream file, String interestNode) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        eventReader = factory.createXMLEventReader(new InputStreamReader(file));

        this.interestNode = interestNode;
    }
}
