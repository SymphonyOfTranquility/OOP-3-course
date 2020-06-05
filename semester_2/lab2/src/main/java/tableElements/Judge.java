package tableElements;

public class Judge extends Person{
    private final Integer courtId;

    public Judge(Integer id, String name, String surname, String login, Integer courtId) {
        super(id, name, surname, login);
        this.courtId = courtId;
    }

    public Integer getCourtId() { return courtId; }

    public String toTable() {
        return  "<th>" + getId() + "</th>" +
                "<th>" + getName() + "</th>" +
                "<th>" + getSurname() + "</th>" +
                "<th>" + getLogin() + "</th>";
    }
}
