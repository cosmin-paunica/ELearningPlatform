package structure.users;

public class Professor extends EducationalUser {
    private TeachingDegree degree;

    public Professor(String email, String lastName, String firstName, TeachingDegree degree) {
        super(email, lastName, firstName);
        this.degree = degree;
    }
}
