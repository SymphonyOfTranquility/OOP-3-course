package tableElements;

public class Judge extends Person{
    private final int courtId;

    Judge(int id, String name, String surname, int courtId) {
        super(id, name, surname);
        this.courtId = courtId;
    }

    public int getCourtId() { return courtId; }
}
