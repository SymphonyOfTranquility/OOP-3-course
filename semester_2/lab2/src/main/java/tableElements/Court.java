package tableElements;

public class Court {
    private final Integer id;
    private final String cityName;
    private final String address;

    public Court(Integer id, String cityName, String address) {
        this.id = id;
        this.cityName = cityName;
        this.address = address;
    }

    public Integer getId() { return id; }

    public String getCityName() { return cityName; }

    public String getAddress() { return address; }
}
