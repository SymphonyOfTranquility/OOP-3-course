package tableElements;

public class Court {
    private final int id;
    private final String cityName;
    private final String address;

    Court(int id, String cityName, String address) {
        this.id = id;
        this.cityName = cityName;
        this.address = address;
    }

    public int getId() { return id; }

    public String getCityName() { return cityName; }

    public String getAddress() { return address; }
}
