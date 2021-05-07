package structure.users;

public abstract class EducationalUser extends User {

    public EducationalUser(int id, String email, String lastName, String firstName, String hashedPassword) {
        super(id, email, lastName, firstName, hashedPassword);
    }
}
