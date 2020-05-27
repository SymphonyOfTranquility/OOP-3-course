package lab;

import java.util.Map;

public class Paper {
    String id;
    String title;
    boolean monthly;
    String type;
    Map<String, Integer> chars;
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Title: ").append(title).append("\n");
        result.append("ID: ").append(id).append("\n");
        if (monthly)
            result.append("Monthly: no\n");
        else
            result.append("Monthly: yes\n");
        result.append("Type: ").append(type).append("\n");

        result.append("Chars\n");
        for (Map.Entry<String, Integer> entry : chars.entrySet()) {
            String key = entry.getKey();
            int val = entry.getValue();
            if (key == "Size")
                result.append("\t").append(key).append(": ").append(val).append("\n");
            else if (val == 0)
                result.append("\t").append(key).append(": no").append("\n");
            else
                result.append("\t").append(key).append(": yes").append("\n");

        }
        return result.toString();
    }

    public boolean equalsTo(Paper other) {
        boolean isEquals = true;
        if (!this.id.equals(other.id))
            isEquals = false;

        if (!this.title.equals(other.title))
            isEquals = false;

        if (!this.type.equals(other.type))
            isEquals = false;

        for (Map.Entry<String, Integer> entry : this.chars.entrySet()) {
            if (!other.chars.get(entry.getKey()).equals(entry.getValue()))
                isEquals = false;
        }

        return isEquals;
    }
}
