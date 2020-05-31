package tableElements;

public class Employee extends Person {
    private final int courtId;
    private final String profession;

    Employee(int id, String name, String surname, int courtId, String profession) {
        super(id, name, surname);
        this.courtId = courtId;
        this.profession = profession;
    }

    public int getCourtId() { return courtId; }

    public String getProfession() { return profession; }
}
