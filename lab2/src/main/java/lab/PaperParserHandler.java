package lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PaperParserHandler {

    private ParserInterface parser;

    PaperParserHandler(ParserInterface parser) {
        this.parser = parser;
    }

    List<Paper> parse() {

        List<Paper> papers = new ArrayList<>();

        Paper paper;

        while (parser.isNextMap()) {
            Map<String, String> paperMap = parser.getNextMap();

            if (paperMap == null)
                continue;

            paper = new Paper();
            Map<String, Integer> chars = new HashMap<>();

            for(Map.Entry<String, String> entry : paperMap.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                switch (key) {
                    case "id":
                        paper.id = val;
                        break;
                    case "Title":
                        paper.title = val;
                        break;
                    case "Type":
                        paper.type = val;
                        break;
                    case "Monthly":
                        if (val == "true")
                            paper.monthly = true;
                        else
                            paper.monthly = false;
                        break;
                    case "Colored":
                        if (val == "true")
                            chars.put("Colored", 1);
                        else
                            chars.put("Colored", 0);
                        break;
                    case "Size":
                        chars.put("Size", Integer.valueOf(val));
                        break;
                    case "Glossy":
                        if (val == "true")
                            chars.put("Glossy", 1);
                        else
                            chars.put("Glossy", 0);
                        break;
                    case "SubscriptionIndex":
                        if (val == "true")
                            chars.put("SubscriptionIndex", 1);
                        else
                            chars.put("SubscriptionIndex", 0);
                        break;
                    default:
                        break;
                }
            }
            paper.chars = chars;

            papers.add(paper);
        }

        return papers;
    }
}
