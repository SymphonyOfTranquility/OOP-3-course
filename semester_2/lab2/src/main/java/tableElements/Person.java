package tableElements;

public class Person {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String login;

    Person(Integer id, String name, String surname, String login) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getLogin() { return login; }
}
