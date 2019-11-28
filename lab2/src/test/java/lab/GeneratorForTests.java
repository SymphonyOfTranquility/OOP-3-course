package lab;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

final class GeneratorForTests {
    private GeneratorForTests(){}
    static InputStream generateXml(boolean bySchema) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Candies xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "xsi:noNamespaceSchemaLocation=\"candy.xsd\">" +
                "<Candy id=\"ID-1\">" +
                "<Name>Slivki linivki vaflya</Name>";
        if (bySchema)
            xml += "<Energy>420</Energy>";
        xml += "<Type filling=\"milk cream\">Chocolate with filling</Type>" +
                "<Ingredients>"+
                "<Water>15</Water>" +
                "<Sugar>30</Sugar>" +
                "<Fructose>15</Fructose>" +
                "<ChocolateType>Black chocolate</ChocolateType>" +
                "<Vanilla>5</Vanilla>"+
                "</Ingredients>" +
                "<Value>" +
                "<Proteins>15</Proteins>" +
                "<Fat>5</Fat>" +
                "<Carbon>80</Carbon>" +
                "</Value>" +
                "<Production>Roshen</Production>\n" +
                "</Candy>" +
                "</Candies>";
        return new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
    }

    static Candy generateCandy() {
        Candy candy = new Candy();
        candy.id = "ID-1";
        candy.name = "Slivki linivki vaflya";
        candy.energy = "420";
        candy.type = "Chocolate with filling";
        candy.filling = "milk cream";

        HashMap<String, String> ingredients = new HashMap<>();
        ingredients.put("Water", "15");
        ingredients.put("Sugar", "30");
        ingredients.put("ChocolateType", "Black chocolate");
        ingredients.put("Vanilla", "5");

        candy.ingredients = ingredients;

        HashMap<String, Integer> value = new HashMap<>();
        value.put("Proteins", 15);
        value.put("Fat", 5);
        value.put("Carbon", 80);

        candy.value = value;

        candy.production = "Roshen";

        return candy;
    }
}
