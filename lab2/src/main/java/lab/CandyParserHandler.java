package lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CandyParserHandler {

    private GeneralParserInterface parser;

    CandyParserHandler(GeneralParserInterface parser) {
        this.parser = parser;
    }

    List<Candy> parse() {

        List<Candy> candies = new ArrayList<>();

        Candy candy;

        while (parser.isNextMap()) {
            Map<String, String> candyMap = parser.getNextMap();

            if (candyMap == null)
                continue;

            candy = new Candy();
            Map<String, String> ingredients = new HashMap<>();
            Map<String, Integer> value = new HashMap<>();

            for(Map.Entry<String, String> entry : candyMap.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                switch (key) {
                    case "id":
                        candy.id = val;
                        break;
                    case "filling":
                        candy.filling = val;
                        break;
                    case "Name":
                        candy.name = val;
                        break;
                    case "Energy":
                        candy.energy = val;
                        break;
                    case "Type":
                        candy.type = val;
                        break;
                    case "Water":
                        ingredients.put("Water", val);
                        break;
                    case "Sugar":
                        ingredients.put("Sugar", val);
                        break;
                    case "Fructose":
                        ingredients.put("Fructose", val);
                        break;
                    case "ChocolateType":
                        ingredients.put("ChocolateType", val);
                        break;
                    case "Vanilla":
                        ingredients.put("Vanilla", val);
                        break;
                    case "Proteins":
                        value.put("Proteins", Integer.valueOf(val));
                        break;
                    case "Fat":
                        value.put("Fat", Integer.valueOf(val));
                        break;
                    case "Carbon":
                        value.put("Carbon", Integer.valueOf(val));
                        break;
                    case "Production":      //production element is the last, according to schema
                        candy.production = val;
                        break;
                    default:
                        break;
                }
            }
            candy.ingredients = ingredients;
            candy.value = value;

            candies.add(candy);
        }

        return candies;
    }
}
