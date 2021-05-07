package structure.users;

public abstract class User {
    protected int id;
    protected String email;
    protected String lastName;
    protected String firstName;
    protected String hashedPassword;

    public User(int id, String email, String lastName, String firstName, String hashedPassword) {
        this.id = id;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.hashedPassword = hashedPassword;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
