package tableElements;

public class Employee extends Person {
    private final Integer courtId;
    private final String profession;

    public Employee(Integer id, String name, String surname, String login, Integer courtId, String profession) {
        super(id, name, surname, login);
        this.courtId = courtId;
        this.profession = profession;
    }

    public Integer getCourtId() { return courtId; }

    public String getProfession() { return profession; }

    public String toTable() {
        String ans = "<th>" + getId() + "</th>" +
                "<th>" + getName() + "</th>" +
                "<th>" + getSurname() + "</th>" +
                "<th>" + getProfession() + "</th>";
        if (getLogin() != null)
            ans += "<th>" + getLogin() + "</th>";
        return ans;
    }

}
