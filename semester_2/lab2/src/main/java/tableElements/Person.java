package tableElements;

public class Person {
    private final int id;
    private final String name;
    private final String surname;

    Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getSurname() { return surname; }
}
