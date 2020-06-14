package TableElements;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean approved;
    private int discount;

    User(int id, String username, String password, String email, boolean approved, int discount)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.approved = approved;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
