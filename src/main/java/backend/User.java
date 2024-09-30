package backend;

public class User {
    private String username;
    private String email;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(int int1, String string, String string2, String string3, String string4) {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
