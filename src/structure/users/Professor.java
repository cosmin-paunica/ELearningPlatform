package structure.users;

import structure.classes.Timetable;

public class Professor extends EducationalUser {
    private TeachingDegree degree;

    public Professor(int id, String email, String lastName, String firstName, TeachingDegree degree, String hashedPassword) {
        super(id, email, lastName, firstName, hashedPassword);
        this.degree = degree;
    }

    public Timetable getTimetable() {
        return new Timetable();
    }
}
