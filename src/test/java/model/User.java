package model;

public class User {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}
