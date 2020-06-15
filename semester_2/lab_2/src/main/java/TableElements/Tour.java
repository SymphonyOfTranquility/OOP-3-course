package TableElements;

public class Tour {
    private int id;
    private String type;
    private String name;
    private boolean isHot;

    public Tour(int id, String type, String name, boolean isHot)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.isHot = isHot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public String toTable() {
        String res =  "<th>" + getId() + "</th>" +
                        "<th>" + getName() + "</th>" +
                        "<th>" + getType() + "</th>";
        if (isHot())
            res += "<th> Hot tour! </th>";
        else
            res += "<th></th>";
        return res;
    }}
