package lab;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

final class GeneratorForTests {
    private GeneratorForTests(){}
    static InputStream generateXml(boolean bySchema) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Papers xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "xsi:noNamespaceSchemaLocation=\"publication.xsd\">" +
                "<Paper id=\"ID-1\">" +
                "<Title>Mriya</Title>";
        if (bySchema)
            xml += "<Type>newspaper</Type>";
        xml += "<Monthly>true</Monthly>" +
                "<Chars>"+
                "<Colored>true</Colored>" +
                "<Size>30</Size>" +
                "<Glossy>false</Glossy>" +
                "<SubscriptionIndex>true</SubscriptionIndex>" +
                "</Chars>" +
                "</Paper>" +
                "</Papers>";
        return new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
    }

    static Paper generatePaper() {
        Paper paper = new Paper();
        paper.id = "ID-1";
        paper.title = "Mriya";
        paper.monthly = true;
        paper.type = "newspaper";

        HashMap<String, Integer> chars = new HashMap<>();
        chars.put("Colored", 1);
        chars.put("Size", 30);
        chars.put("Glossy", 0);
        chars.put("SubscriptionIndex", 1);

        paper.chars = chars;
        return paper;
    }
}
