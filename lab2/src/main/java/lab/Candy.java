package lab;

import java.util.Map;

public class Candy {
    String id;
    String name;
    String energy;
    String type;
    String filling;
    Map<String, String> ingredients;
    Map<String, Integer> value;
    String production;
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Name: ").append(name).append("\n");
        result.append("ID: ").append(id).append("\n");
        result.append("Energy: ").append(energy).append("\n");
        result.append("Type: ").append(type).append("\n");
        if (filling != null)
            result.append("Filling: ").append(filling).append("\n");
        result.append("Ingredients\n");
        for (Map.Entry<String, String> entry : ingredients.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            result.append("\t").append(key).append(": ").append(val).append("\n");
        }
        result.append("Value\n");
        for (Map.Entry<String, Integer> entry : value.entrySet()) {
            String key = entry.getKey();
            int val = entry.getValue();
            result.append("\t").append(key).append(": ").append(val).append("\n");
        }
        result.append("Production: ").append(production).append("\n");

        return result.toString();
    }

    public boolean equalsTo(Candy other) {
        boolean isEquals = true;
        if (!this.id.equals(other.id))
            isEquals = false;

        if (!this.name.equals(other.name))
            isEquals = false;

        if (!this.energy.equals(other.energy))
            isEquals = false;

        if (!this.type.equals(other.type))
            isEquals = false;

        if (!this.filling.equals(other.filling))
            isEquals = false;

        for (Map.Entry<String, String> entry : this.ingredients.entrySet()) {
            if (!other.ingredients.get(entry.getKey()).equals(entry.getValue()))
                isEquals = false;
        }

        for (Map.Entry<String, Integer> entry : this.value.entrySet()) {
            if (!other.value.get(entry.getKey()).equals(entry.getValue()))
                isEquals = false;
        }

        if (!this.production.equals(other.production))
            isEquals = false;

        return isEquals;
    }
}
